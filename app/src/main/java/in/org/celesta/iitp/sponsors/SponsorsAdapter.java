package in.org.celesta.iitp.sponsors;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import in.org.celesta.iitp.R;

public class SponsorsAdapter extends RecyclerView.Adapter<SponsorsAdapter.FeedViewHolder> {

    private Context context;
    private List<SponsorItem> sponsorItemList;

    public SponsorsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_sponsors, parent, false);

        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FeedViewHolder holder, int position) {

        if (sponsorItemList != null) {
            final SponsorItem current = sponsorItemList.get(position);

            holder.name.setText(current.getName());
            Glide.with(context)
                    .load(current.getImage())
                    .centerCrop()
                    .placeholder(R.drawable.events_icon_2)
                    .into(holder.image);

            holder.root.setOnClickListener(v -> {
                NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment);
//                    navController.navigate(pair.getValue());
            });


        } else {
            holder.name.setText("Loading ...");
        }
    }

    @Override
    public int getItemCount() {
        if (sponsorItemList != null)
            return sponsorItemList.size();
        else return 0;
    }

    class FeedViewHolder extends RecyclerView.ViewHolder {
        
        View root;
        TextView name;
        ImageView image;

        FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            root = itemView.findViewById(R.id.cv_sponsors);
            image = itemView.findViewById(R.id.image);
        }
    }

    void setSponsorItemList(List<SponsorItem> sponsors) {
        sponsorItemList = sponsors;
        (new Handler()).postDelayed(this::notifyDataSetChanged, 200);
    }
}
