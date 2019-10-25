package in.org.celesta.iitp.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

import in.org.celesta.iitp.R;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView imageView = view.findViewById(R.id.main_logo_iv);
        Glide.with(requireContext()).load(R.drawable.celesta_logo_long_2).into(imageView);

        Map<Integer, Integer> map = new HashMap<>();

        map.put(R.id.main_pronite_ll, R.id.nav_pronite);
        map.put(R.id.main_event_ll, R.id.nav_events_cat);
        map.put(R.id.main_gallery_ll, R.id.nav_gallery);
        map.put(R.id.main_special_ll, R.id.nav_special_cat);
        map.put(R.id.main_team_ll, R.id.nav_team);
        map.put(R.id.main_sponsor_ll, R.id.nav_sponsors);

        for (final Map.Entry<Integer, Integer> pair : map.entrySet()) {
            View mainView = view.findViewById(pair.getKey());
            mainView.setOnClickListener(v -> {
                if (getActivity() != null) {
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    navController.navigate(pair.getValue());
                }
            });
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        if (getActivity() != null)
            getActivity().getMenuInflater().inflate(R.menu.menu_home, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_contact) {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.option_contact);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
