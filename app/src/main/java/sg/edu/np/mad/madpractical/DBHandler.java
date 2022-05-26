package sg.edu.np.mad.madpractical;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(Context c) {
        super(c, "Users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE User (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Description TEXT, Followed BOOLEAN CHECK(Followed IN (0, 1)))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        onCreate(db);
    }

    public void insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO User (Name, Description, Followed) VALUES(\"" + user.name + "\", \"" + user.description + "\", \"" + ((user.followed)?1:0) + "\")");

        db.close();
    }

    public ArrayList<User> getUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<User> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);
        while (cursor.moveToNext()) {
            User user = new User();
            user.id = cursor.getInt(0);
            user.name = cursor.getString(1);
            user.description = cursor.getString(2);
            user.followed = (cursor.getInt(3) == 1);

            list.add(user);
        }

        cursor.close();

        return list;
    }

    public void updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE USER SET Followed = \"" + ((user.followed)?1:0) + "\" WHERE ID = \"" + user.id + "\"");

        db.close();
    }
}
