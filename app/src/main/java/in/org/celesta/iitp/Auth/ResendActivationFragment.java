package in.org.celesta.iitp.Auth;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;

import java.util.List;

import in.org.celesta.iitp.R;
import in.org.celesta.iitp.network.RetrofitClientInstance;
import in.org.celesta.iitp.utils.CheckNetwork;
import in.org.celesta.iitp.utils.Keyboard;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResendActivationFragment extends Fragment {
    TextView register_textview;
    EditText resend_activation_edittext;
    Button resend_activation_button;
    private AuthApi authApi;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_resend_activation, container, false);
        register_textview = rootview.findViewById(R.id.resend_activation_register_textview);
        resend_activation_edittext = rootview.findViewById(R.id.resend_activation_email_edittext);
        resend_activation_button = rootview.findViewById(R.id.resend_activation_button);
        return rootview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        register_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new RegisterFragment());
            }
        });

        resend_activation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!CheckNetwork.isNetworkConnected(getContext()))
                    Toast.makeText(getContext(), "Check your network properly", Toast.LENGTH_SHORT).show();
                else
                    resend_activation();
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_auth_container, fragment);
            ft.addToBackStack(fragment.getTag());
            ft.commit();
            return true;
        }
        return false;
    }

    private void resend_activation() {
        Keyboard.closeKeyboard(getView(), getContext());
        String email = resend_activation_edittext.getText().toString().trim();

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Resending link in email...");
        progressDialog.show();

        authApi = RetrofitClientInstance.getAuthRetrofitInstance().create(AuthApi.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("f", "resend_activation_link")
                .addFormDataPart("email", email)
                .build();

        Call<ResendActivationResponse> call = authApi.resend_activation(requestBody);

        call.enqueue(new Callback<ResendActivationResponse>() {
            @Override
            public void onResponse(Call<ResendActivationResponse> call, Response<ResendActivationResponse> response) {
                progressDialog.dismiss();
                Log.e("success", "onResponse: " + response.body().getStatus());
                if (response.body().getStatus() == 404) {
                    Toast.makeText(getContext(), "Email not found. Register again", Toast.LENGTH_LONG).show();
                } else if (response.body().getStatus() == 200) {
                    Toast.makeText(getContext(), "Check your email for activation link", Toast.LENGTH_LONG).show();
                    loadFragment(new LoginFragment());
                } else if (response.body().getStatus() == 208) {
                    Toast.makeText(getContext(), "Account already activated. Login again", Toast.LENGTH_LONG).show();
                    loadFragment(new LoginFragment());
                } else
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                List<String> messages=response.body().getMessage();
                for(String temp : messages)
                    Log.e("messages", "onResponse: "+temp );
            }

            @Override
            public void onFailure(Call<ResendActivationResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("failed", "onFailure: " + t.toString());
                Toast.makeText(getContext(), "Could not resend email verification", Toast.LENGTH_LONG).show();
            }
        });
    }
}
