package com.example.allpro;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginFragment extends Fragment {

    private EditText etUsername, etPassword;
    private TextView tvSignupLinkLogin, tvForgotPasswordLogin;
    private Button btnLogin;
    private FirebaseServices fbs;

    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ✅ ربط عناصر الواجهة
        fbs = FirebaseServices.getInstance();
        etUsername = view.findViewById(R.id.etUsernameLogin);
        etPassword = view.findViewById(R.id.etPasswordLogin);
        btnLogin = view.findViewById(R.id.btnLoginLogin);
        tvSignupLinkLogin = view.findViewById(R.id.tvSignupLinkLogin);
        tvForgotPasswordLogin = view.findViewById(R.id.tvForgotPasswordLogin);

        // 🔹 الانتقال إلى SignupFragment
        tvSignupLinkLogin.setOnClickListener(v -> gotoSignupFragment());

        // 🔹 الانتقال إلى ForgotPasswordFragment
        tvForgotPasswordLogin.setOnClickListener(v -> gotoForgotPasswordFragment());

        // 🔹 تسجيل الدخول
        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(getActivity(), "Some fields are empty", Toast.LENGTH_SHORT).show();
                return;
            }

            fbs.getAuth().signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getActivity(), "You have successfully logged in!", Toast.LENGTH_SHORT).show();
                                // TODO: انتقل إلى الصفحة الرئيسية هنا
                                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.frameLayout, new AdminFragment());
                                ft.commit();

                            } else {
                                Toast.makeText(getActivity(), "Failed to login! Check email or password!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }

    // الانتقال إلى Signup
    private void gotoSignupFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new SignupFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    // الانتقال إلى Forgot Password
    private void gotoForgotPasswordFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new ForgotPasswordFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
}