package in.org.celesta.iitp.events;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import in.org.celesta.iitp.R;

public class EventsActivity extends AppCompatActivity implements EventsRecyclerAdapter.OnFeedSelectedListener {

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_events);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                updateData();
            }
        });

        int category = getIntent().getIntExtra("category", 0);

        setBaseFragment(category);
    }

    private void setBaseFragment(int category) {
        if (findViewById(R.id.events_frame_layout) != null) {
            EventsFragment firstFragment = EventsFragment.newInstance(category);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.events_frame_layout, firstFragment).commit();
        }
    }

    @Override
    public void onFeedSelected(String id, View view, int position) {

    }

    //    private void updateData() {
//
//        long latest = feedViewModel.getMaxEventId();
//        Log.e("maxid", String.valueOf(latest));
//
//        String token = PreferenceManager.getDefaultSharedPreferences(getContext()).getString(USER_TOKEN, "0");
//        Log.e("token", token);
//
//        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//
//        Call<EventItem.FeedItemSuper1> call = service.getNewFeed(token, latest);
//        call.enqueue(new Callback<EventItem.FeedItemSuper1>() {
//            @Override
//            public void onResponse(Call<EventItem.FeedItemSuper1> call, Response<EventItem.FeedItemSuper1> response) {
//
//                if (response.isSuccessful()) {
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
//
//
//    }
//

}
