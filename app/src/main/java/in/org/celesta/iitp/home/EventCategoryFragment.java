package in.org.celesta.iitp.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import in.org.celesta.iitp.R;

public class EventCategoryFragment extends Fragment {

    private OnEventCategorySelectedListener callback;

    public EventCategoryFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_category, container, false);


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEventCategorySelectedListener) {
            callback = (OnEventCategorySelectedListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    public interface OnEventCategorySelectedListener {
        void onEventCategorySelected(int id);
    }
}
