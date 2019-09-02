package in.org.celesta.iitp.Auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import in.org.celesta.iitp.R;

public class LoginFragment extends Fragment {
    TextView register_textview;
    EditText login_celesta_id_editext,login_password_edittext;
    Button login_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        register_textview=rootView.findViewById(R.id.register_textview);
        login_celesta_id_editext=rootView.findViewById(R.id.login_celesta_Id_edittext);
        login_password_edittext=rootView.findViewById(R.id.login_password_edittext);
        login_button=rootView.findViewById(R.id.login_button);
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

        login_celesta_id_editext.addTextChangedListener(loginTextWatcher);
        login_password_edittext.addTextChangedListener(loginTextWatcher);
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
}
