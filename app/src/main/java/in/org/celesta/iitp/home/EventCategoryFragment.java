package in.org.celesta.iitp.home;

import android.content.Context;
import android.content.SharedPreferences;
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
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import in.org.celesta.iitp.R;
import in.org.celesta.iitp.events.EventItem;
import in.org.celesta.iitp.events.EventsViewModel;
import in.org.celesta.iitp.network.EventsRoutes;
import in.org.celesta.iitp.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventCategoryFragment extends Fragment {

    public EventCategoryFragment() {
    }

    private SwipeRefreshLayout swipeRefreshLayout;
    private EventsViewModel viewModel;

    private EventsCategoryAdapter adapter;
    private RecyclerView recyclerView;
    private View emptyView;
    private SharedPreferences preferences;

    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.fade));
        viewModel = ViewModelProviders.of(this).get(EventsViewModel.class);
        if (getContext() != null)
            this.context = getContext();
        else
            NavHostFragment.findNavController(this).navigateUp();

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_event_cat);
        swipeRefreshLayout.setOnRefreshListener(this::updateData);

        if (preferences.getLong("last_event_update_time", 0) < System.currentTimeMillis() - 10 * 60 * 1000) {
            swipeRefreshLayout.setRefreshing(true);
            updateData();
        }

        emptyView = view.findViewById(R.id.empty_view);

        recyclerView = view.findViewById(R.id.rv_event_category);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));

        adapter = new EventsCategoryAdapter(context);
        recyclerView.setAdapter(adapter);

        observeAll();

        super.onViewCreated(view, savedInstanceState);
    }

    private void observeAll() {
        viewModel.loadAllClubs().observe(this, strings -> {

            List<String> temp = new ArrayList<>();

            for (String s : strings)
                if (!temp.contains(s))
                    temp.add(s);

            adapter.setEventCategoryList(temp);

            if (strings.size() == 0) {
                recyclerView.setVisibility(View.INVISIBLE);
                emptyView.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void updateData() {

        EventsRoutes service = RetrofitClientInstance.getRetrofitInstance().create(EventsRoutes.class);

        Call<List<EventItem>> call = service.getAllEvents();

        call.enqueue(new Callback<List<EventItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<EventItem>> call, @NonNull Response<List<EventItem>> response) {
                if (viewModel != null) {
                    if (response.isSuccessful()) {

                        viewModel.deleteEvents();

                        List<EventItem> allItems = response.body();

                        if (allItems != null && allItems.size() > 0) {
                            for (EventItem newItem : allItems) {
                                viewModel.insert(newItem);
                            }
                        }

                        preferences.edit().putLong("last_event_update_time", System.currentTimeMillis()).apply();

                    } else {
                        Log.e(getClass().getSimpleName(), "no data");
                    }
                }
                if (swipeRefreshLayout != null)
                    swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<EventItem>> call, @NonNull Throwable t) {
                Log.e(getClass().getSimpleName(), t.getMessage());
                if (swipeRefreshLayout != null)
                    swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
