package in.org.celesta.iitp.team;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import in.org.celesta.iitp.R;
import in.org.celesta.iitp.utils.IntentUtils;

public class TeamRecyclerAdapter extends RecyclerView.Adapter<TeamRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<TeamItem> teamItemList;

    public TeamRecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_team, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        if (teamItemList != null) {
            final TeamItem current = teamItemList.get(position);

            holder.name.setText(current.getName());
            holder.post.setText(current.getPost());
            Glide.with(context)
                    .load(current.getImage())
                    .thumbnail(Glide.with(context).load(R.raw.load))
                    .centerCrop()
                    .into(holder.image);

            holder.facebook.setOnClickListener(v -> IntentUtils.openWebBrowser(context, current.getFacebook()));
            holder.phone.setOnClickListener(v -> {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + current.getPhone()));
                context.startActivity(i);
            });

        } else {
            holder.name.setText("Loading ...");
        }
    }

    @Override
    public int getItemCount() {
        if (teamItemList != null)
            return teamItemList.size();
        else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView post;
        CardView facebook;
        CardView phone;
        ImageView image;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            post = itemView.findViewById(R.id.post);
            facebook = itemView.findViewById(R.id.card_facebook);
            phone = itemView.findViewById(R.id.card_call);
            image = itemView.findViewById(R.id.image);
        }
    }

    public void setTeamItemList(List<TeamItem> feeds) {
        teamItemList = feeds;
        notifyDataSetChanged();
    }

}
