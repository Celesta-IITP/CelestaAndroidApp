package in.org.celesta.iitp.Auth;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import androidx.preference.PreferenceManager;

import com.bumptech.glide.Glide;

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

public class LoginFragment extends Fragment {

    private EditText celestaIdInput, passwordInput;
    private Button loginButton;
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView registerTextView = view.findViewById(R.id.register_textview);
        registerTextView.setOnClickListener(view1 -> loadFragment(new RegisterFragment()));

        TextView resendActivationTextView = view.findViewById(R.id.resend_activation_email_textview);
        resendActivationTextView.setOnClickListener(view12 -> loadFragment(new ResendActivationFragment()));

        celestaIdInput = view.findViewById(R.id.login_celesta_Id_edittext);
        passwordInput = view.findViewById(R.id.login_password_edittext);
        loginButton = view.findViewById(R.id.login_button);

        imageView=view.findViewById( R.id.login_image );
        Glide.with( requireContext() ).load( R.drawable.celesta_logo_long_2 ).into( imageView );

        loginButton.setOnClickListener(view13 -> {
            if (!CheckNetwork.isNetworkConnected(context))
                Toast.makeText(getContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
            else login();
        });

        celestaIdInput.addTextChangedListener(loginTextWatcher);
        passwordInput.addTextChangedListener(loginTextWatcher);
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null && getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_auth_container, fragment)
                    .commit();
        }
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String celestaIdInput = LoginFragment.this.celestaIdInput.getText().toString().trim();
            String passwordInput = LoginFragment.this.passwordInput.getText().toString().trim();

            loginButton.setEnabled(!celestaIdInput.isEmpty() && !passwordInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private void login() {
        Keyboard.closeKeyboard(getView(), context);

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        AuthApi authApi = RetrofitClientInstance.getRetrofitInstance().create(AuthApi.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("f", "login_user")
                .addFormDataPart("celestaid", celestaIdInput.getText().toString().trim())
                .addFormDataPart("password", passwordInput.getText().toString().trim())
                .build();

        Call<LoginResponse> call = authApi.login(requestBody);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (progressDialog != null) progressDialog.dismiss();

                if (response.isSuccessful() && response.body() != null) {

                    LoginResponse loginResponse = response.body();

                    if (loginResponse.getStatus() == 202) {
                        Log.e("success", "access_token: " + loginResponse.getAccessToken());
                        storeData(loginResponse);
                        Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show();

                        if (getActivity() != null)
                            getActivity().finish();

                    } else if (loginResponse.getStatus() == 403) {
                        List<String> message = loginResponse.getMessage();
                        Toast.makeText(context, message.get(0), Toast.LENGTH_LONG).show();
                    }

                } else Toast.makeText(context, "Something went wrong!!!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                if (progressDialog != null) progressDialog.dismiss();
                Log.e("Error", "onFailure: " + t.getMessage());
                Toast.makeText(context, "Something went wrong!!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void storeData(LoginResponse response) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putBoolean("login_status", true)
                .putString("celesta_id", response.getCelestaId())
                .putString("access_token", response.getAccessToken())
                .putString("first_name", response.getFirstName())
                .putString("qr_code", response.getQrCode())
                .apply();
    }

}
