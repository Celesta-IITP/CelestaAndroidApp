package in.org.celesta.iitp.ongoing;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import in.org.celesta.iitp.R;
import in.org.celesta.iitp.events.EventsRecyclerAdapter;

public class OngoingEventsFragment extends Fragment {

    private EventsRecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private View emptyView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public OngoingEventsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_events);
        swipeRefreshLayout.setOnRefreshListener(this::updateData);

        recyclerView = view.findViewById(R.id.rv_feed_single_type);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new EventsRecyclerAdapter(requireContext(), (EventsRecyclerAdapter.OnEventSelectedListener) requireContext());
        recyclerView.setAdapter(adapter);

        updateData();

        return view;
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

//                    List<EventItem> allItems = response.body().getLatestFeeds();
//

//            List<EventItem> newList = new ArrayList<>();
//
//            adapter.setEventItemList(newList);
//            if (newList.size() == 0) {
//                recyclerView.setVisibility(View.INVISIBLE);
////                  emptyView.setVisibility(View.VISIBLE);
//            } else {
//                recyclerView.setVisibility(View.VISIBLE);
////                  emptyView.setVisibility(View.INVISIBLE);
//            }
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
