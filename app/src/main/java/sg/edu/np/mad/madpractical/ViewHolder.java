package sg.edu.np.mad.madpractical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView description;
    ImageView profile;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.textViewName);
        description = itemView.findViewById(R.id.textViewDesc);
        profile = itemView.findViewById(R.id.imageViewProfile);
    }
}
