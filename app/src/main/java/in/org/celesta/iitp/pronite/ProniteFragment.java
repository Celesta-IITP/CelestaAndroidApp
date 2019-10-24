package in.org.celesta.iitp.pronite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.bumptech.glide.Glide;

import in.org.celesta.iitp.R;
import in.org.celesta.iitp.utils.ImageViewerActivity;

public class ProniteFragment extends Fragment {

    public ProniteFragment() {
    }

    private Context context;
    private int[] color;
    private SharedPreferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContext() != null)
            this.context = getContext();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pronite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        ImageView poster = view.findViewById(R.id.poster);
        CardView contact = view.findViewById(R.id.card_event_details_qua);
        Button register = view.findViewById(R.id.button_register);
        ImageButton map = view.findViewById(R.id.button_map);

        String posterUrl = "https://celesta.org.in/backend/admin/events/posters/ATM1245_Anubhav%20Singh%20Bassi.jpg";
        Glide.with(context)
                .asBitmap()
                .load(posterUrl)
                .centerCrop()
                .into(poster);

        poster.setOnClickListener(view14 -> {
            Intent i = new Intent(context, ImageViewerActivity.class);
            i.putExtra("image_url", posterUrl);
            startActivity(i);
        });


        map.setOnClickListener(view13 -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(""));
            startActivity(intent);
        });

        contact.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+918058501770"));
            startActivity(intent);
        });

        register.setOnClickListener(view15 -> {

        });

        super.onViewCreated(view, savedInstanceState);
    }


}
