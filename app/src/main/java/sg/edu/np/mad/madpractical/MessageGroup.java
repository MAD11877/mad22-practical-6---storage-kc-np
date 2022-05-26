package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MessageGroup extends AppCompatActivity {

    FragmentGrpOne fragOne;
    FragmentGrpTwo fragTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);

        // create new instance of fragment when app is launched
        if (savedInstanceState == null) {
            fragOne = new FragmentGrpOne();
            fragTwo = new FragmentGrpTwo();
        }

        // show fragments when corresponding buttons are clicked and hide the other fragment
        Button btnOne = findViewById(R.id.buttonGrpOne);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if (fragOne.isAdded()) {
                    ft.show(fragOne);
                }
                else {
                    ft.add(R.id.frameLayout, fragOne);
                }

                if (fragTwo.isAdded()) {
                    ft.hide(fragTwo);
                }

                ft.commit();
            }
        });

        Button btnTwo = findViewById(R.id.buttonGrpTwo);

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if (fragTwo.isAdded()) {
                    ft.show(fragTwo);
                }
                else {
                    ft.add(R.id.frameLayout, fragTwo);
                }

                if (fragOne.isAdded()) {
                    ft.hide(fragOne);
                }

                ft.commit();
            }
        });

    }
}