package in.org.celesta.iitp.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import in.org.celesta.iitp.R;

public class EventCategoryFragment extends Fragment {

    public EventCategoryFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_category, container, false);

//        View mainView = view.findViewById(pair.getKey());
//        mainView.setOnClickListener(v -> {
//            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
//            Bundle b = new Bundle();
//            b.putInt("category", 1);
//            navController.navigate(pair.getValue(), b);
//        });

        return view;
    }

}
