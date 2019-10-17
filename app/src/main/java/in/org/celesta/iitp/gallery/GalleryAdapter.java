package in.org.celesta.iitp.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import in.org.celesta.iitp.R;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.FeedViewHolder> {

    private Context context;
    private List<Image> imageList;
    private OnImageSelectedListener callback;

    public GalleryAdapter(Context context, OnImageSelectedListener listener) {
        this.context = context;
        this.callback = listener;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallery, parent, false);

        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FeedViewHolder holder, int position) {

        if (imageList != null) {
            final Image current = imageList.get(position);

            Glide.with(context)
                    .load(current.getReduced())
                    .thumbnail(Glide.with(context).load(R.raw.load))
                    .centerCrop()
                    .into(holder.imageView);

            callback.onEventSelected(current.getReduced(), current.getNormal());
        }
    }

    @Override
    public int getItemCount() {
        if (imageList != null)
            return imageList.size();
        else return 0;
    }

    class FeedViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    void setImageList(List<Image> images) {
        imageList = images;
        notifyDataSetChanged();
    }

    public interface OnImageSelectedListener {
        void onEventSelected(String reduced, String url);
    }
}
