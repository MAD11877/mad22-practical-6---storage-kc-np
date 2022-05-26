package sg.edu.np.mad.madpractical;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder>  {

    ArrayList<User> data;

    public Adapter(ArrayList<User> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        User user = data.get(position);
        return (user.name.substring(user.name.length() - 1).equals("7")) ? 0 : 1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;
        if (viewType == 1) {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout, parent, false);
        }
        else {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout_second, parent, false);
        }
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = data.get(position);
        holder.name.setText("Name " + user.name);
        holder.description.setText("Description " + user.description);

        holder.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Profile");
                builder.setMessage(holder.name.getText());
                builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent toMain = new Intent(view.getContext(), MainActivity.class);
                        toMain.putExtra("user", user);
                        view.getContext().startActivity(toMain);
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
