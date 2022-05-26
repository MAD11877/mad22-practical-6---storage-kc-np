package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Random random = new Random();

        DBHandler dbhandler = new DBHandler(this);

        // initialize users in the database if there are no records
        if (dbhandler.getUsers().size() == 0) {
            for (int i = 0; i < 20; i++) {
                User user = new User();
                user.name = Long.toString(random.nextLong());
                user.description = Long.toString(random.nextLong());
                user.followed = random.nextBoolean();

                dbhandler.insertUser(user);
            }
        }

        ArrayList<User> users = dbhandler.getUsers();

        RecyclerView rv = findViewById(R.id.recyclerView);
        Adapter adapter = new Adapter(users);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
    }
}