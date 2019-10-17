package in.org.celesta.iitp.Auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import java.util.List;

import in.org.celesta.iitp.R;
import in.org.celesta.iitp.home.MainActivity;
import in.org.celesta.iitp.network.RetrofitClientInstance;
import in.org.celesta.iitp.utils.CheckNetwork;
import in.org.celesta.iitp.utils.Keyboard;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    private TextView register_textview, resend_activation_textview;
    private EditText login_celesta_id_editext, login_password_edittext;
    private Button login_button;
    private AuthApi authApi;
    private ProgressDialog progressDialog;
    private SharedPreferences.Editor sharedPreferenceEditor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        register_textview = rootView.findViewById(R.id.register_textview);
        login_celesta_id_editext = rootView.findViewById(R.id.login_celesta_Id_edittext);
        login_password_edittext = rootView.findViewById(R.id.login_password_edittext);
        login_button = rootView.findViewById(R.id.login_button);
        resend_activation_textview = rootView.findViewById(R.id.resend_activation_email_textview);
        return rootView;
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

        resend_activation_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new ResendActivationFragment());
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!CheckNetwork.isNetworkConnected(getContext()))
                    Toast.makeText(getContext(), "Check your network properly", Toast.LENGTH_SHORT).show();
                else
                    login();
            }
        });

        login_celesta_id_editext.addTextChangedListener(loginTextWatcher);
        login_password_edittext.addTextChangedListener(loginTextWatcher);
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

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String celestaIdInput = login_celesta_id_editext.getText().toString().trim();
            String passwordInput = login_password_edittext.getText().toString().trim();

            login_button.setEnabled(!celestaIdInput.isEmpty() && !passwordInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void login() {
        Keyboard.closeKeyboard(getView(), getContext());

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        authApi = RetrofitClientInstance.getRetrofitInstance().create(AuthApi.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("f", "login_user")
                .addFormDataPart("celestaid", login_celesta_id_editext.getText().toString().trim())
                .addFormDataPart("password", login_password_edittext.getText().toString().trim())
                .build();

        Call<LoginResponse> call = authApi.login(requestBody);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();
                Log.e("success", "onResponse: " + response.code());
                if (response.body().getStatus() == 202) {
                    Log.e("success", "access_token: "+ response.body().getAccess_token() );
                    storeDatas(response.body().getCelestaid(), response.body().getAccess_token(), response.body().getFirst_name(), response.body().getQrcode());
                    Toast.makeText(getContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else if (response.body().getStatus() == 403) {
                    List<String> message = response.body().getMessage();
                    Toast.makeText(getContext(), message.get(0), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("Error", "onFailure: " + t.toString());
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void storeDatas(String celestaid, String access_token, String first_name, String qrCode) {
        sharedPreferenceEditor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
        sharedPreferenceEditor.putBoolean("login_status", true);
        sharedPreferenceEditor.putString("celestaid", celestaid);
        sharedPreferenceEditor.putString("access_token", access_token);
        sharedPreferenceEditor.putString("first_name", first_name);
        sharedPreferenceEditor.putString("qrcode", qrCode);
        sharedPreferenceEditor.apply();
    }

}
