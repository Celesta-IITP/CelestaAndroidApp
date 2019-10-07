package in.org.celesta.iitp.events;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import in.org.celesta.iitp.R;

public class EventsRecyclerAdapter extends RecyclerView.Adapter<EventsRecyclerAdapter.FeedViewHolder> {

    private Context context;
    private List<EventItem> eventItemList;
    private OnEventSelectedListener callback;

    public EventsRecyclerAdapter(Context context, OnEventSelectedListener listener) {
        this.context = context;
        this.callback = listener;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_events, parent, false);

        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FeedViewHolder holder, int position) {

        if (eventItemList != null) {
            final EventItem current = eventItemList.get(position);

            holder.title.setText(current.getEvName());
            if (current.getEvVenue() != null)
                holder.venue.setText(current.getEvVenue());
            Glide.with(context)
                    .load(current.getEvPosterUrl())
                    .centerCrop()
                    .placeholder(R.drawable.events_icon_2)
                    .into(holder.imageView);

            holder.time.setText(String.format("%s  -  %s", current.getEvStartTime(), current.getEvEndTime()));
            holder.venue.setText(current.getEvDate() == null ? "" : current.getEvDate());

            holder.rootLayout.setOnClickListener(v -> {
                Palette palette = Palette.from(holder.imageView.getDrawingCache()).generate();
                Palette.Swatch swatch = palette.getDominantSwatch();

                int[] color = {0,0,0};
                if (swatch != null) {
                    color[0] = swatch.getRgb();
                    color[1] = swatch.getTitleTextColor();
                    color[2] = swatch.getBodyTextColor();
                }

                callback.onEventSelected(current.getId(), color);
            });


        } else {
            holder.title.setText("Loading ...");
        }
    }

    @Override
    public int getItemCount() {
        if (eventItemList != null)
            return eventItemList.size();
        else return 0;
    }

    class FeedViewHolder extends RecyclerView.ViewHolder {

        View availableIndicator;
        TextView title;
        TextView time;
        TextView venue;
        LinearLayout rootLayout;
        ImageView imageView;

        FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            availableIndicator = itemView.findViewById(R.id.item_available_indicator);
            time = itemView.findViewById(R.id.item_time_text);
            title = itemView.findViewById(R.id.item_title_text);
            venue = itemView.findViewById(R.id.item_venue_text);
            rootLayout = itemView.findViewById(R.id.item_root_layout);
            imageView = itemView.findViewById(R.id.item_event_image);
            imageView.setDrawingCacheEnabled(true);
        }
    }

    void setEventItemList(List<EventItem> feeds) {
        eventItemList = feeds;
        notifyDataSetChanged();
    }

    public interface OnEventSelectedListener {
        void onEventSelected(String id, int[] color);
    }
}
