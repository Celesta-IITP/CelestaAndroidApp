package in.org.celesta.iitp.Auth;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

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
    private EditText emailInput;
    private ProgressDialog progressDialog;
    private Context context;
    private ImageView imageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContext() != null)
            this.context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_resend_activation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailInput = view.findViewById(R.id.resend_activation_email_edittext);

        TextView registerTextView = view.findViewById(R.id.resend_activation_register_textview);
        registerTextView.setOnClickListener(view1 -> loadFragment(new RegisterFragment()));

        imageView=view.findViewById( R.id.resend_image );
        Glide.with( requireContext() ).load( R.drawable.celesta_logo_long_2 ).into( imageView );

        Button resendActivationButton = view.findViewById(R.id.resend_activation_button);
        resendActivationButton.setOnClickListener(view12 -> {
            if (!CheckNetwork.isNetworkConnected(context))
                Toast.makeText(context, "Check your internet connection...", Toast.LENGTH_LONG).show();
            else resend_activation();
        });
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null && getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_auth_container, fragment)
                    .commit();
        }
    }

    private void resend_activation() {
        Keyboard.closeKeyboard(getView(), context);
        String email = emailInput.getText().toString().trim();

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Resending link in email...");
        progressDialog.show();

        AuthApi authApi = RetrofitClientInstance.getRetrofitInstance().create(AuthApi.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("f", "resend_activation_link")
                .addFormDataPart("email", email)
                .build();

        Call<ResendActivationResponse> call = authApi.resend_activation(requestBody);

        call.enqueue(new Callback<ResendActivationResponse>() {
            @Override
            public void onResponse(@NonNull Call<ResendActivationResponse> call, @NonNull Response<ResendActivationResponse> response) {
                if (progressDialog != null) progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {

                    ResendActivationResponse activationResponse = response.body();

                    if (activationResponse.getStatus() == 404) {
                        Toast.makeText(context, "Email not found! Please register again.", Toast.LENGTH_LONG).show();
                    } else if (activationResponse.getStatus() == 200) {
                        Toast.makeText(context, "Check your email for activation link...", Toast.LENGTH_LONG).show();
                        loadFragment(new LoginFragment());
                    } else if (activationResponse.getStatus() == 208) {
                        Toast.makeText(context, "Account already activated! Login again.", Toast.LENGTH_LONG).show();
                        loadFragment(new LoginFragment());
                    } else {
                        Toast.makeText(context, "Something went wrong!!! " + activationResponse.getMessage().get(0), Toast.LENGTH_LONG).show();
                    }
                    for (String temp : activationResponse.getMessage())
                        Log.e("messages", "onResponse: " + temp);
                } else
                    Toast.makeText(context, "Something went wrong!!!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<ResendActivationResponse> call, @NonNull Throwable t) {
                if (progressDialog != null) progressDialog.dismiss();
                Log.e("failed", "onFailure: " + t.getMessage());
                Toast.makeText(context, "Could not resend email verification!!!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
