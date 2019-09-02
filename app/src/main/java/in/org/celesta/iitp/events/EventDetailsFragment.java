package in.org.celesta.iitp.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.org.celesta.iitp.R;
import in.org.celesta.iitp.utils.RoundedModalBottom;

public class EventDetailsFragment extends RoundedModalBottom {

    public EventDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_details, container, false);
    }

}
