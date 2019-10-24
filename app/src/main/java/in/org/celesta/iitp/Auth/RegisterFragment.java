package in.org.celesta.iitp.Auth;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

public class RegisterFragment extends Fragment {

    private EditText firstNameInput, lastNameInput, phoneInput, emailInput, collegeInput, passwordInput, confirmPasswordInput, referralInput, genderInput;
    private ProgressDialog progressDialog;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContext() != null)
            this.context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firstNameInput = view.findViewById(R.id.register_first_name_edittext);
        lastNameInput = view.findViewById(R.id.register_last_name_edittext);
        phoneInput = view.findViewById(R.id.register_phone_edittext);
        emailInput = view.findViewById(R.id.register_email_edittext);
        collegeInput = view.findViewById(R.id.register_school_college_edittext);
        passwordInput = view.findViewById(R.id.register_password_edittext);
        confirmPasswordInput = view.findViewById(R.id.register_confirm_password_edittext);
        referralInput = view.findViewById(R.id.register_referral_edittext);
        genderInput = view.findViewById(R.id.register_gender_edittext);

        TextView loginTextView = view.findViewById(R.id.login_textview);
        loginTextView.setOnClickListener(view12 -> loadFragment(new LoginFragment()));

        Button registerButton = view.findViewById(R.id.register_button);
        registerButton.setOnClickListener(view1 -> {
            if (!CheckNetwork.isNetworkConnected(context))
                Toast.makeText(getContext(), "Check your internet connection!!!", Toast.LENGTH_LONG).show();
            else register();
        });
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null && getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_auth_container, fragment)
                    .commit();
        }
    }

    private void register() {
        String firstName, lastName, phone, email, college, password, confirmPassword, referral, gender;
        firstName = firstNameInput.getText().toString().trim();
        lastName = lastNameInput.getText().toString().trim();
        phone = phoneInput.getText().toString().trim();
        email = emailInput.getText().toString().trim();
        college = collegeInput.getText().toString().trim();
        password = passwordInput.getText().toString().trim();
        confirmPassword = confirmPasswordInput.getText().toString().trim();
        gender = genderInput.getText().toString().toLowerCase().trim();

        if (password.isEmpty() || confirmPassword.isEmpty() || !password.equals(confirmPassword)) {
            confirmPasswordInput.setError("Passwords don't match!!!");
            return;
        }

        if (!("m".equals(gender) || "f".equals(gender))) {
            genderInput.setError("Enter a valid gender!!!");
            return;
        }

        if (phone.isEmpty()) {
            phoneInput.setError("Enter a valid phone number!!!");
            return;
        }

        if (firstName.length() < 3) {
            firstNameInput.setError("Enter a valid name!!!");
            return;
        }

        if (lastName.isEmpty()) {
            lastNameInput.setError("Enter a valid name!!!");
            return;
        }

        referral = referralInput.getText().toString().trim();
        if (referral.isEmpty()) referral = "CLST1504";

        Keyboard.closeKeyboard(getView(), context);

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Registering...");
        progressDialog.show();

        AuthApi authApi = RetrofitClientInstance.getRetrofitInstance().create(AuthApi.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("f", "register_user")
                .addFormDataPart("first_name", firstName)
                .addFormDataPart("last_name", lastName)
                .addFormDataPart("phone", phone)
                .addFormDataPart("college", college)
                .addFormDataPart("email", email)
                .addFormDataPart("password", password)
                .addFormDataPart("confirm_password", confirmPassword)
                .addFormDataPart("gender", gender)
                .addFormDataPart("referral_id", referral)
                .build();

        Call<RegisterResponse> call = authApi.register(requestBody);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                if (progressDialog != null) progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {

                    RegisterResponse registerResponse = response.body();

                    if (registerResponse.getStatus() == 200) {
                        Toast.makeText(getContext(), "Registration successful.Check your email for activation link", Toast.LENGTH_LONG).show();
                        new Handler().postDelayed(() -> loadFragment(new LoginFragment()), 1000);
                    } else if (registerResponse.getStatus() == 400) {
                        List<String> messages = registerResponse.getMessage();

                        StringBuilder err = new StringBuilder();
                        for (String temp : messages) err = err.append(temp).append("\n");
                        Log.e("messages", "onResponse: err= " + err.toString());

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage(err.toString());
                        builder.setTitle("Alert!!!");
                        builder.show();
                    }
                } else
                    Toast.makeText(getContext(), "Something went wrong!!!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<RegisterResponse> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Log.e("Error", "onFailure: " + t.getMessage());
                Toast.makeText(getContext(), "Something went wrong!!!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
