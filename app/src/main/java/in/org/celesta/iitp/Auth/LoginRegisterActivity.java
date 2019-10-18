package in.org.celesta.iitp.Auth;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import in.org.celesta.iitp.R;

public class LoginRegisterActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        getWindow().setBackgroundDrawableResource(R.drawable.background_image_2);
        setContentView(R.layout.activity_login_signup);

        if (!sharedPreferences.getBoolean("login_status", false))
            loadFragment(new LoginFragment());
        else loadFragment(new ProfileFragment());
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_auth_container, fragment)
                .commit();
    }
}
