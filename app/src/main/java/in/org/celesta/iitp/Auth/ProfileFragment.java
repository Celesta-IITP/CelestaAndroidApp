package in.org.celesta.iitp.Auth;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.bumptech.glide.Glide;

import in.org.celesta.iitp.R;
import in.org.celesta.iitp.network.RetrofitClientInstance;
import in.org.celesta.iitp.utils.CheckNetwork;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private TextView celestaIdTextView, firstNameTextView;
    private ImageView qrImage;
    private SharedPreferences preferences;
    private ProgressDialog progressDialog;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContext() != null)
            this.context = getContext();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button logout_button = view.findViewById(R.id.profile_logout_button);
        celestaIdTextView = view.findViewById(R.id.profile_celestaid);
        firstNameTextView = view.findViewById(R.id.profile_first_name);
        qrImage = view.findViewById(R.id.profile_qrcode);

        logout_button.setOnClickListener(view1 -> {
            if (!CheckNetwork.isNetworkConnected(context))
                Toast.makeText(getContext(), "Check Internet connection", Toast.LENGTH_SHORT).show();
            else logout();
        });

        showOfflineProfile();
    }

    private void logout() {
        logoutApi();
        clearSharedPreference();
    }

    private void clearSharedPreference() {
        SharedPreferences.Editor sharedPreferenceEditor = preferences.edit();
        sharedPreferenceEditor.putBoolean("login_status", false);
        sharedPreferenceEditor.putString("celestaid", "");
        sharedPreferenceEditor.putString("access_token", "");
        sharedPreferenceEditor.putString("first_name", "");
        sharedPreferenceEditor.putString("qrcode", "");
        sharedPreferenceEditor.apply();
    }

    private void logoutApi() {
        String celestaId = preferences.getString("celesta_id", "");
        String accessToken = preferences.getString("access_token", "");

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Logging out...");
        progressDialog.show();

        AuthApi authApi = RetrofitClientInstance.getRetrofitInstance().create(AuthApi.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("f", "logout_user")
                .addFormDataPart("celestaid", celestaId)
                .addFormDataPart("access_token", accessToken)
                .build();

        Call<LogoutResponse> call = authApi.logout(requestBody);

        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(@NonNull Call<LogoutResponse> call, @NonNull Response<LogoutResponse> response) {
                if (progressDialog != null) progressDialog.dismiss();

                if (response.isSuccessful() && response.body() != null) {

                    LogoutResponse logoutResponse = response.body();

                    if (logoutResponse.getStatus() == 202) {
                        Toast.makeText(context, response.body().getMessage().get(0), Toast.LENGTH_SHORT).show();

                        if (getActivity() != null)
                            getActivity().finish();
                    } else {
                        Toast.makeText(getContext(), response.body().getMessage().get(0), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Something went wrong!!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LogoutResponse> call, @NonNull Throwable t) {
                if (progressDialog != null) progressDialog.dismiss();
                Log.e("failed", "onFailure: " + t.getMessage());
                Toast.makeText(getContext(), "Something went wrong!!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showOfflineProfile() {
        String qrCode = "https://celesta.org.in/backend/user/assets/qrcodes/" + preferences.getString("celesta_id", "") + ".png";
        Glide.with(context)
                .load(qrCode)
                .thumbnail(Glide.with(context).load(R.raw.load))
                .into(qrImage);

        String celestaId = "Celesta ID: " + preferences.getString("celesta_id", "");
        celestaIdTextView.setText(celestaId);

        String firstName = "Hello " + preferences.getString("first_name", "");
        firstNameTextView.setText(firstName);
    }

}
