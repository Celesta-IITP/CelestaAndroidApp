package in.org.celesta.iitp.Auth;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import in.org.celesta.iitp.R;
import in.org.celesta.iitp.home.MainActivity;
import in.org.celesta.iitp.network.RetrofitClientInstance;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private Button logout_button;
    private SharedPreferences.Editor sharedPreferenceEditor;
    private SharedPreferences preferences;
    private AuthApi authApi;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        preferences=PreferenceManager.getDefaultSharedPreferences(getContext());
        logout_button=rootView.findViewById(R.id.profile_logout_button);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void logout(){
        logoutApi();
        clearSharedPreference();
    }

    private void clearSharedPreference(){
        sharedPreferenceEditor = preferences.edit();
        sharedPreferenceEditor.putBoolean("login_status",false);
        sharedPreferenceEditor.putString("celestaid","");
        sharedPreferenceEditor.putString("access_token","");
        sharedPreferenceEditor.putString("first_name","");
        sharedPreferenceEditor.putString("qrcode","");
        sharedPreferenceEditor.apply();
    }

    private void logoutApi(){
        String celestaid=preferences.getString("celestaid","");
        String access_token=preferences.getString("access_token","");

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Logging out...");
        progressDialog.show();

        authApi= RetrofitClientInstance.getAuthRetrofitInstance().create(AuthApi.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("f", "logout_user")
                .addFormDataPart("celestaid", celestaid)
                .addFormDataPart("access_token", access_token)
                .build();

        Call<LogoutResponse> call = authApi.logout(requestBody);

        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                progressDialog.dismiss();
                if(response.body().getStatus()==202){
                    Toast.makeText(getContext(),response.body().getMessage().get(0),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                else{
                    Toast.makeText(getContext(),response.body().getMessage().get(0),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("failed", "onFailure: "+t.toString() );
                Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
