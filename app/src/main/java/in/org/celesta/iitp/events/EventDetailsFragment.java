package in.org.celesta.iitp.events;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import in.org.celesta.iitp.R;

public class EventDetailsFragment extends BottomSheetDialogFragment {

    public EventDetailsFragment() {
    }

    private EventsViewModel viewModel;
    private EventItem current;
    private Context context;
    private int[] color;

    @Override
    public int getTheme() {
        return R.style.BaseBottomSheetDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(this.requireContext(), this.getTheme());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(EventsViewModel.class);

        if (getContext() != null)
            this.context = getContext();
        else this.dismiss();

        if (getArguments() != null) {
            String id = getArguments().getString("data");
            color = getArguments().getIntArray("color");
            current = viewModel.getEventById(id);
            if (current == null) this.dismiss();
        } else this.dismiss();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        ImageView poster = view.findViewById(R.id.poster);
        Glide.with(context)
                .asBitmap()
                .load(current.getEvPosterUrl())
                .centerCrop()
                .placeholder(R.drawable.events_icon_2)
                .into(poster);

        CardView cardPrimary = view.findViewById(R.id.card_event_details_primary);
        CardView cardSecondary = view.findViewById(R.id.card_event_details_sec);
        CardView cardTer = view.findViewById(R.id.card_event_details_ter);
        CardView cardQua = view.findViewById(R.id.card_event_details_qua);
        CardView cardPen = view.findViewById(R.id.card_event_details_pen);

        Button register = view.findViewById(R.id.button_register);

        if (color[0] != 0) {
            cardPrimary.setCardBackgroundColor(color[0]);
            cardSecondary.setCardBackgroundColor(color[0]);
            cardTer.setCardBackgroundColor(color[0]);
            cardQua.setCardBackgroundColor(color[0]);
            cardPen.setCardBackgroundColor(color[0]);
            register.setBackgroundTintList(ColorStateList.valueOf(color[0]));
        }

        TextView name = view.findViewById(R.id.name);
        TextView date = view.findViewById(R.id.date);
        TextView venue = view.findViewById(R.id.venue);
        ImageButton map = view.findViewById(R.id.button_map);
        TextView time = view.findViewById(R.id.time);
        TextView description = view.findViewById(R.id.description);
        TextView organisers = view.findViewById(R.id.organisers);
        ImageView organiserPhone = view.findViewById(R.id.organiser_phone);
        TextView rulebook = view.findViewById(R.id.rulebook);
        ImageView rulebookImg = view.findViewById(R.id.rulebook_img);

        if (color[2] != 0) {
            name.setTextColor(color[2]);
            date.setTextColor(color[2]);
            venue.setTextColor(color[2]);
            map.setImageTintList(ColorStateList.valueOf(color[2]));
            time.setTextColor(color[2]);
            description.setTextColor(color[2]);
            organisers.setTextColor(color[2]);
            organiserPhone.setImageTintList(ColorStateList.valueOf(color[2]));
            rulebook.setTextColor(color[2]);
            rulebookImg.setImageTintList(ColorStateList.valueOf(color[2]));
            register.setTextColor(color[2]);
        }

        name.setText(current.getEvName());

        if (current.getEvDescription() != null && !current.getEvDescription().isEmpty())
            description.setText(current.getEvDescription());
        else cardSecondary.setVisibility(View.GONE);

        if (current.getEvDate() != null && !current.getEvDate().isEmpty())
            date.setText(String.format("Date : %s", current.getEvDate()));

        if (current.getEvVenue() != null && !current.getEvVenue().isEmpty())
            venue.setText(String.format("Location : %s", current.getEvVenue()));

        if (current.getMapUrl() != null && !current.getMapUrl().isEmpty()) {
            map.setOnClickListener(view13 -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(current.getMapUrl()));
                startActivity(intent);
            });
        }

        if (current.getEvStartTime() != null && !current.getEvStartTime().isEmpty())
            time.setText(String.format("Timing : %s  -  %s", current.getEvStartTime(), current.getEvEndTime()));

        if (current.getEvOrganiser() != null && !current.getEvOrganiser().isEmpty())
            organisers.setText(String.format("Organisers : %s", current.getEvOrganiser()));

        if (current.getEvOrgPhone() != null && !current.getEvOrgPhone().isEmpty()) {
            cardQua.setOnClickListener(view1 -> {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + current.getEvOrgPhone().split(",")[0]));
                startActivity(intent);
            });
        } else {
            organiserPhone.setVisibility(View.GONE);
        }

        if (current.getEvRuleBookUrl() != null && !current.getEvRuleBookUrl().isEmpty()) {
            cardPen.setOnClickListener(view12 -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(current.getEvRuleBookUrl()));
                startActivity(intent);
            });
        } else {
            cardPen.setVisibility(View.GONE);
        }

        if (current.getEvAmount() != null && !current.getEvAmount().isEmpty()) {
            register.setText(String.format("Register (₹ %s)", current.getEvAmount()));
        }

        super.onViewCreated(view, savedInstanceState);
    }
}
