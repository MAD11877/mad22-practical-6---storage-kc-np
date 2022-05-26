package sg.edu.np.mad.madpractical;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        FirebaseDatabase db = FirebaseDatabase.getInstance("https://madpractical-a38d2-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = db.getReference("Users");

        EditText nameInput = findViewById(R.id.editTextGetName);
        EditText passwordInput = findViewById(R.id.editTextGetPassword);
        Button loginBtn = findViewById(R.id.buttonLogin);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString();
                String password = passwordInput.getText().toString();

                nameInput.setText("");
                passwordInput.setText("");

                final Boolean[] checkFields = {false, false};  // {check for password, check for name}

                myRef.child("mad").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String correctPass = snapshot.child("password").getValue(String.class);
                        String correctName = snapshot.child("username").getValue(String.class);

                        // check for correct password and username
                        if (correctPass.equals(password)) {
                            checkFields[0] = true;
                        }

                        if (correctName.equals(name)) {
                            checkFields[1] = true;
                        }

                        // go to ListActivity if both fields are valid
                        if (checkFields[0] && checkFields[1]) {
                            Intent toList = new Intent(LoginPage.this, ListActivity.class);
                            startActivity(toList);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}