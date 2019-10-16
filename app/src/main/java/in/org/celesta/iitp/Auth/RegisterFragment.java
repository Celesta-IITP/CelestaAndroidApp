package in.org.celesta.iitp.Auth;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView login_textview;
    Button register_button;
    EditText first_name_edittext, last_name_edittext, phone_edittext, email_edittext, college_edittext, password_editttext, confirm_password_edittext, referral_edittext, gender_edittext;
    private AuthApi authApi;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        login_textview = rootView.findViewById(R.id.login_textview);
        register_button = rootView.findViewById(R.id.register_button);
        first_name_edittext = rootView.findViewById(R.id.register_first_name_edittext);
        last_name_edittext = rootView.findViewById(R.id.register_last_name_edittext);
        phone_edittext = rootView.findViewById(R.id.register_phone_edittext);
        email_edittext = rootView.findViewById(R.id.register_email_edittext);
        college_edittext = rootView.findViewById(R.id.register_school_college_edittext);
        password_editttext = rootView.findViewById(R.id.register_password_edittext);
        confirm_password_edittext = rootView.findViewById(R.id.register_confirm_password_edittext);
        referral_edittext = rootView.findViewById(R.id.register_referral_edittext);
        gender_edittext = rootView.findViewById(R.id.register_gender_edittext);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        login_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new LoginFragment());
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!CheckNetwork.isNetworkConnected(getContext()))
                    Toast.makeText(getContext(), "Check your network properly", Toast.LENGTH_SHORT).show();
                else
                    register();
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_auth_container, fragment);
            ft.commit();
            return true;
        }
        return false;
    }

    private void register() {
        String first_name, last_name, phone, email, college, password, confirm_password, referral, gender;
        first_name = first_name_edittext.getText().toString().trim();
        last_name = last_name_edittext.getText().toString().trim();
        phone = phone_edittext.getText().toString().trim();
        email = email_edittext.getText().toString().trim();
        college = college_edittext.getText().toString().trim();
        password = password_editttext.getText().toString().trim();
        confirm_password = confirm_password_edittext.getText().toString().trim();
        referral = confirm_password_edittext.getText().toString().trim();
        if (referral.length() == 0) referral = "CLST1504";
        gender = gender_edittext.getText().toString().toLowerCase().trim();

        Keyboard.closeKeyboard(getView(), getContext());

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Registering...");
        progressDialog.show();

        authApi = RetrofitClientInstance.getAuthRetrofitInstance().create(AuthApi.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("f", "register_user")
                .addFormDataPart("first_name", first_name)
                .addFormDataPart("last_name", last_name)
                .addFormDataPart("phone", phone)
                .addFormDataPart("college", college)
                .addFormDataPart("email", email)
                .addFormDataPart("password", password)
                .addFormDataPart("confirm_password", confirm_password)
                .addFormDataPart("gender", gender)
                .addFormDataPart("referral_id", referral)
                .build();

        Call<RegisterResponse> call = authApi.register(requestBody);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                progressDialog.dismiss();
                Log.e("success", "onResponse: " + response.code());
                if (response.body().getStatus() == 200) {
                    Toast.makeText(getContext(), "Registration successful.Check your email for activation link", Toast.LENGTH_LONG).show();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadFragment(new LoginFragment());
                        }
                    }, 2000);
                } else if (response.body().getStatus() == 400) {
                    List<String> messages = response.body().getMessage();
                    String err = "";
                    for (String temp : messages) {
                        err = err.concat(temp + "\n");
                        Log.e("messages", "onResponse: " + temp);
                    }
                    Log.e("messages", "onResponse: err= " + err);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(err);
                    builder.setTitle("Error:");
                    builder.setCancelable(true);
                    builder.create().show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("Error", "onFailure: " + t.toString());
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
