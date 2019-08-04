package in.org.celesta.iitp.home;

import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import in.org.celesta.iitp.R;
import in.org.celesta.iitp.events.EventsFragment;
import in.org.celesta.iitp.events.EventsRecyclerAdapter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnItemSelectedListener, EventCategoryFragment.OnEventCategorySelectedListener,
        EventsRecyclerAdapter.OnEventSelectedListener {

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getWindow().setNavigationBarColor(getResources().getColor(android.R.color.transparent));
        getWindow().setBackgroundDrawableResource(R.drawable.img1);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

//        startActivity(new Intent(this, AccountActivity.class));

        frameLayout = findViewById(R.id.main_frame_layout);

        setBaseFragment();

    }

    private void setBaseFragment() {
        if (findViewById(R.id.main_frame_layout) != null) {
            HomeFragment firstFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_frame_layout, firstFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void transactFragment(Fragment newFragment) {

        Fragment current = getSupportFragmentManager().findFragmentById(R.id.main_frame_layout);

        if (current != null) {
            current.setExitTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.slide_left));
            newFragment.setEnterTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.slide_right));
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, newFragment)
                .addToBackStack(newFragment.getTag()).commit();
    }

    @Override
    public void onItemSelected(Fragment newFragment) {
        transactFragment(newFragment);
    }

    @Override
    public void onEventCategorySelected(int category) {
        Fragment nextFragment = EventsFragment.newInstance(category);
        transactFragment(nextFragment);
    }

    @Override
    public void onEventSelected(String id) {

    }
}
