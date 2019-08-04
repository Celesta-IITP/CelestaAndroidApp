package in.org.celesta.iitp.events;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.org.celesta.iitp.R;

public class EventsFragment extends Fragment {

    private static final String ARG_PARAM1 = "category";

    private int category;
    private EventsRecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private View emptyView;
    private EventsViewModel viewModel;

    public EventsFragment() {
    }

    public static EventsFragment newInstance(int category) {
        EventsFragment fragment = new EventsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getInt(ARG_PARAM1);
        }
        viewModel = ViewModelProviders.of(this).get(EventsViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        recyclerView = view.findViewById(R.id.rv_feed_single_day);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new EventsRecyclerAdapter(getContext(), (EventsRecyclerAdapter.OnFeedSelectedListener) getActivity());
        recyclerView.setAdapter(adapter);

        observeAll();

        return view;
    }

    private void observeAll() {
        viewModel.loadAllEvents().observe(this, new Observer<List<EventItem>>() {
            @Override
            public void onChanged(List<EventItem> eventItems) {

                List<EventItem> newList = new ArrayList<>();
                for (EventItem n : eventItems) {
                    if (n.getType() == category) newList.add(n);
                }
                adapter.setEventItemList(newList);
                if (newList.size() == 0) {
                    recyclerView.setVisibility(View.INVISIBLE);
//                  emptyView.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
//                  emptyView.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
}
