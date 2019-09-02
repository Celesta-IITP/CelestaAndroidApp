package in.org.celesta.iitp.home;

import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;

import in.org.celesta.iitp.R;

public class EventCategoryFragment extends Fragment implements View.OnClickListener {

    public EventCategoryFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.fade));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        CardView maintenance = view.findViewById(R.id.event_coding_cv);
        CardView sharingPortal = view.findViewById(R.id.event_robotics_cv);

        ImageView lost = view.findViewById(R.id.layout2);
        Glide.with(this)
                .load("")
                .thumbnail(Glide.with(this).load(R.raw.gif_abc))
                .into(lost);
        ImageView bus = view.findViewById(R.id.layout1);
        Glide.with(this)
                .load("")
                .thumbnail(Glide.with(this).load(R.raw.gif_abc))
                .into(bus);

        maintenance.setOnClickListener(this);
        sharingPortal.setOnClickListener(this);


//        View mainView = view.findViewById(pair.getKey());
//        mainView.setOnClickListener(v -> {
//            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
//            Bundle b = new Bundle();
//            b.putInt("category", 1);
//            navController.navigate(pair.getValue(), b);
//        });

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View view) {

        Bundle b = new Bundle();

        switch (view.getId()) {
            case R.id.event_coding_cv:
                b.putInt("category", 1);
                break;

            case R.id.event_robotics_cv:
                b.putInt("category", 2);
                break;
        }

        Navigation.findNavController(view).navigate(R.id.action_nav_events_cat_to_eventsFragment, b);

    }
}
