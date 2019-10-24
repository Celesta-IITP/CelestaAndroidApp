package in.org.celesta.iitp.Auth;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.org.celesta.iitp.R;
import in.org.celesta.iitp.network.RetrofitClientInstance;
import in.org.celesta.iitp.utils.CheckNetwork;
import in.org.celesta.iitp.utils.ImageViewerActivity;
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
        profileApi();
    }

    private void logout() {
        logoutApi();
        clearSharedPreference();
    }

    private void clearSharedPreference() {
        SharedPreferences.Editor sharedPreferenceEditor = preferences.edit();
        sharedPreferenceEditor.putBoolean("login_status", false);
        sharedPreferenceEditor.putString("celesta_id", "");
        sharedPreferenceEditor.putString("access_token", "");
        sharedPreferenceEditor.putString("first_name", "");
        sharedPreferenceEditor.putString("qr_code", "");
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
        String qrCode = preferences.getString("qr_code", "");
        Glide.with(context)
                .load(qrCode)
                .thumbnail(Glide.with(context).load(R.raw.load))
                .into(qrImage);

        qrImage.setOnClickListener(view12 -> {
            Intent i = new Intent(context, ImageViewerActivity.class);
            i.putExtra("image_url", qrCode);
            startActivity(i);
        });

        String celestaId = preferences.getString("celesta_id", "");
        celestaIdTextView.setText(celestaId);

        String firstName = "Hello " + preferences.getString("first_name", "");
        firstNameTextView.setText(firstName);
    }

    private void profileApi() {
        String celestaId = preferences.getString("celesta_id", "");
        String accessToken = preferences.getString("access_token", "");

        AuthApi authApi = RetrofitClientInstance.getRetrofitInstance().create(AuthApi.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("f", "user_profile")
                .addFormDataPart("celestaid", celestaId)
                .addFormDataPart("access_token", accessToken)
                .build();

        Call<ProfileResponse> call = authApi.getProfile(requestBody);

        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProfileResponse> call, @NonNull Response<ProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    ProfileResponse profileResponse = response.body();

                    if (profileResponse.getStatus() == 202) {
                        populateProfile(profileResponse);
                    } else {
                        Toast.makeText(getContext(), response.body().getMessage().get(0), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Something went wrong!!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProfileResponse> call, @NonNull Throwable t) {
                Log.e("failed", "onFailure: " + t.getMessage());
                Toast.makeText(getContext(), "Something went wrong!!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateProfile(ProfileResponse response) {

        if (getView() != null && response != null) {

            ((TextView)getView().findViewById(R.id.profile_loading_events)).setText("Registered Events : ");

            SpannableStringBuilder spn = new SpannableStringBuilder(response.getProfile().getFirstName() + " " + response.getProfile().getLastName());
            spn.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.medBlue)), 0, spn.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            firstNameTextView.setText("");
            firstNameTextView.append("Hello  ");
            firstNameTextView.append(spn);
            firstNameTextView.append("\nEmail :  " + response.getProfile().getEmail());
            firstNameTextView.append("\nPhone :  " + response.getProfile().getPhone());
            firstNameTextView.append("\nAmount Paid : ₹ " + response.getProfile().getAmountPaid());

            RecyclerView recyclerView = getView().findViewById(R.id.recycler_profile_events);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            RegisteredEventsAdapter adapter = new RegisteredEventsAdapter(getContext());
            recyclerView.setAdapter(adapter);

            List<RegisteredEventItem> allRegisteredEvents = new ArrayList<>();

            String registered = response.getProfile().getEventsRegistered();

            if (registered != null && !registered.equals("null") && !registered.isEmpty()) {

                List<ProfileResponse.Event> allEvents = response.getEvents();

                String regMod = "{\"events\":" + registered + "}";

                Gson gson = new Gson();
                try {
                    JSONObject object = new JSONObject(regMod);

                    JSONArray a = object.getJSONArray("events");

                    for (int i = 0; i < a.length(); i++) {

                        RegisteredEventItem item = gson.fromJson(a.getJSONObject(i).toString(), RegisteredEventItem.class);

                        for (ProfileResponse.Event e : allEvents) {
                            if (e.getEvId().equals(item.getEventId())) {
                                item.setAmountToBePaid(Integer.parseInt(e.getEvAmount()));
                                break;
                            }
                        }

                        allRegisteredEvents.add(item);
                    }

                } catch (JSONException e) {
                    Toast.makeText(context, "Failed to parse events data!!!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
            adapter.setEventList(allRegisteredEvents);

        }

    }

}
