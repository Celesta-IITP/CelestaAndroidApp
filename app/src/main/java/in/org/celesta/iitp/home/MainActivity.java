package in.org.celesta.iitp.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import in.org.celesta.iitp.Auth.LoginRegisterActivity;
import in.org.celesta.iitp.R;
import in.org.celesta.iitp.events.EventDetailsFragment;
import in.org.celesta.iitp.events.EventsRecyclerAdapter;

public class MainActivity extends AppCompatActivity implements EventsRecyclerAdapter.OnEventSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;
    private MenuItem navAccount;
    private SharedPreferences prefs;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.background_image_2);
        setContentView(R.layout.activity_main);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        Menu menu = navigationView.getMenu();
        navAccount = menu.findItem(R.id.nav_account);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_events_cat, R.id.nav_ongoing, R.id.nav_pronite, R.id.nav_special_cat,
                R.id.nav_gallery, R.id.nav_team, R.id.nav_sponsors, R.id.nav_maps, R.id.nav_developers, R.id.nav_account)
                .setDrawerLayout(drawer)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        FirebaseMessaging.getInstance().subscribeToTopic("all");
    }

    private void handleIntent(Intent appLinkIntent) {
        String appLinkAction = appLinkIntent.getAction();
        String appLinkData = appLinkIntent.getDataString();

        if (Intent.ACTION_VIEW.equals(appLinkAction) && appLinkData != null) {

            if (appLinkData.contains("/notification/")) {
                String time = appLinkData.substring(appLinkData.lastIndexOf("/") + 1);

                Bundle bundle = new Bundle();
                bundle.putString("time", time);

            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
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


    @Override
    public void onEventSelected(String id, int[] color) {
        Bundle b = new Bundle();
        b.putString("data", id);
        b.putIntArray("color", color);

        EventDetailsFragment fragment = new EventDetailsFragment();
        fragment.setArguments(b);
        fragment.setRetainInstance(true);
        fragment.show(getSupportFragmentManager(), fragment.getTag());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("login_status", false))
            navAccount.setTitle("Profile");
        else navAccount.setTitle("Login/Register");
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(this::populateHeaderView, 200);
    }

    private void populateHeaderView() {

        View v = navigationView.getHeaderView(0);

        if (v != null) {
            String name = prefs.getString("first_name", "");
            ((TextView) v.findViewById(R.id.name)).setText(name.isEmpty() ? "Celesta IITP" : name);
            String id = prefs.getString("celesta_id", "");
            ((TextView) v.findViewById(R.id.celesta_id)).setText(id.isEmpty() ? "Guest User" : id);
            ImageView profileImage = v.findViewById(R.id.image);
            Glide.with(this)
                    .load(prefs.getString("qr_code", ""))
                    .centerCrop()
                    .placeholder(R.mipmap.celesta_icon_round)
                    .into(profileImage);
            profileImage.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, LoginRegisterActivity.class)));
        }
    }
}
