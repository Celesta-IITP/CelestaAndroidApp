package in.org.celesta.iitp.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;

import in.org.celesta.iitp.R;
import in.org.celesta.iitp.events.EventsFragment;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    private OnItemSelectedListener callback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Map<Integer, Fragment> map = new HashMap<>();

        map.put(R.id.main_pronite_ll, new EventCategoryFragment());
        map.put(R.id.main_event_ll, new EventCategoryFragment());
        map.put(R.id.main_gallery_ll, new EventCategoryFragment());
        map.put(R.id.main_schedule_ll, new EventCategoryFragment());
        map.put(R.id.main_team_ll, new EventCategoryFragment());
        map.put(R.id.main_sponsor_ll, new EventCategoryFragment());

        for (final Map.Entry<Integer, Fragment> pair : map.entrySet()) {
            View mainView = view.findViewById(pair.getKey());
            mainView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onItemSelected(pair.getValue());
                }
            });
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            callback = (OnItemSelectedListener) context;
        }
    }

    interface OnItemSelectedListener {
        void onItemSelected(Fragment newFragment);
    }
}
