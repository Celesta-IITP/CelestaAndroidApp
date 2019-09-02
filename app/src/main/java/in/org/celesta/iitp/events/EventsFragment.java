package in.org.celesta.iitp.events;


import android.os.Bundle;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.org.celesta.iitp.R;

public class EventsFragment extends Fragment {

    private static final String ARG_PARAM1 = "category";

    private int category;
    private EventsRecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private View emptyView;
    private EventsViewModel viewModel;
    private SwipeRefreshLayout swipeRefreshLayout;

    public EventsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getInt(ARG_PARAM1);
        }
        viewModel = ViewModelProviders.of(this).get(EventsViewModel.class);

        setEnterTransition(TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.fade));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_events);
        swipeRefreshLayout.setOnRefreshListener(this::updateData);

        recyclerView = view.findViewById(R.id.rv_feed_single_type);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new EventsRecyclerAdapter(requireContext(), (EventsRecyclerAdapter.OnEventSelectedListener) requireContext());
        recyclerView.setAdapter(adapter);

        observeAll();

        viewModel.deleteEvents();

        List<EventItem> eventItems = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            EventItem n = new EventItem();
            n.setBio("bio");
            n.setContact("1414141414");
            n.setDay(1);
            n.setDescription("description");
            n.setEndTime(new Date().getTime());
            n.setImage("image");
            n.setName("New Event");
            n.setOrganiser("organiser");
            n.setVenue("venue");
            n.setStartTime(new Date().getTime());
            n.setType(1);
            String id = "new_id_" + i;
            n.setId(id);
            eventItems.add(n);
        }

        for (int i = 0; i <= 7; i++) {
            viewModel.insert(eventItems.get(i));
            Log.e("item instertes" + i, eventItems.get(i).getId());

        }
//


        super.onViewCreated(view, savedInstanceState);
    }

    private void observeAll() {
        viewModel.loadAllEvents().observe(this, eventItems -> {

            List<EventItem> newList = new ArrayList<>();
            for (EventItem n : eventItems) {
                if (n.getType() == category) newList.add(n);
            }
            adapter.setEventItemList(newList);
//            if (newList.size() == 0) {
//                recyclerView.setVisibility(View.INVISIBLE);
//                  emptyView.setVisibility(View.VISIBLE);
//            } else {
//                recyclerView.setVisibility(View.VISIBLE);
//                  emptyView.setVisibility(View.INVISIBLE);
//            }
        });

    }

    private void updateData() {

//        String token = PreferenceManager.getDefaultSharedPreferences(getContext()).getString(USER_TOKEN, "0");
//        Log.e("token", token);

//        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//
//        Call<EventItem> call = service.getNewFeed(token, latest);
//        call.enqueue(new Callback<EventItem.FeedItemSuper1>() {
//            @Override
//            public void onResponse(Call<EventItem.FeedItemSuper1> call, Response<EventItem.FeedItemSuper1> response) {
//
//                if (response.isSuccessful()) {

//                    viewModel.deleteEvents();

//                    List<EventItem> allItems = response.body().getLatestFeeds();
//
//                    for (EventItem newItem : allItems) {
//                        if (feedViewModel.getFeedCount(newItem.getId()) == 0)
//                            feedViewModel.insert(newItem);
//                        Log.e("feed", newItem.getEventName());
//                    }
//
//                }
//                swipeRefreshLayout.setRefreshing(false);
//            }
//
//            @Override
//            public void onFailure(Call<EventItem.FeedItemSuper1> call, Throwable t) {
//                Log.e("failure", t.getMessage());
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });


    }


}
