package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get value from ListActivity.java after clicking "view"
        Intent receiver = getIntent();
        User user = (User) receiver.getSerializableExtra("user");

        TextView txtHeader = findViewById(R.id.textViewHeader);
        txtHeader.setText("Name " + user.name);
        TextView txtDescription = findViewById(R.id.textViewSomeText);
        txtDescription.setText("Description " + user.description);

        // initialize text in follow button
        initFollowBtnTxt(user);

        // show toast message, update user followed variable, change button text - follow button
        Button btnFollow = findViewById(R.id.buttonFollow);

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnFollow.getText() == "Follow"){
                    btnFollow.setText("Unfollow");
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                }
                else {
                    btnFollow.setText("Follow");
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
                }
                user.followed = !user.followed;

                DBHandler dbhandler = new DBHandler(MainActivity.this);
                dbhandler.updateUser(user);
            }
        });

        // launch MessageGroup - message button
        Button btnMessage = findViewById(R.id.buttonMessage);

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMessageGroup = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(toMessageGroup);
            }
        });


    }

    public void initFollowBtnTxt(User user) {
        Button followBtn = findViewById(R.id.buttonFollow);
        if (!user.followed){
            followBtn.setText("Follow");
        }
        else {
            followBtn.setText("Unfollow");
        }
    }
}