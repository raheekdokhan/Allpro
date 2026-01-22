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
    private TextView tvSignupLinkLogin, tvForgotPasswordLogin, tvProfileLinkLogin, tvSmsLinkLogin;
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

        // ربط عناصر الواجهة
        fbs = FirebaseServices.getInstance();
        etUsername = view.findViewById(R.id.etUsernameLogin);
        etPassword = view.findViewById(R.id.etPasswordLogin);
        btnLogin = view.findViewById(R.id.btnLoginLogin);
        tvSignupLinkLogin = view.findViewById(R.id.tvSignupLinkLogin);
        tvForgotPasswordLogin = view.findViewById(R.id.tvForgotPasswordLogin);
        tvProfileLinkLogin = view.findViewById(R.id.tvProfileLinkLogin);
        tvSmsLinkLogin = view.findViewById(R.id.tvSmsLinkLogin); // <- ربط Send Message

        // الانتقالات
        tvSignupLinkLogin.setOnClickListener(v -> gotoSignupFragment());
        tvForgotPasswordLogin.setOnClickListener(v -> gotoForgotPasswordFragment());
        tvProfileLinkLogin.setOnClickListener(v -> gotoProfileFragment());
        tvSmsLinkLogin.setOnClickListener(v -> gotoSmsFragment()); // <- ربط Send Message

        // تسجيل الدخول
        btnLogin.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getActivity(), "Some fields are empty", Toast.LENGTH_SHORT).show();
            return;
        }

        fbs.getAuth().signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Logged in successfully!", Toast.LENGTH_SHORT).show();
                            gotoAdminFragment(); // بعد تسجيل الدخول → AdminFragment أو AllFragment
                        } else {
                            Toast.makeText(getActivity(),
                                    "Login failed: " + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
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

    // الانتقال إلى Admin بعد تسجيل الدخول
    private void gotoAdminFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new AdminFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    // الانتقال إلى Profile (صفحة التعديل)
    private void gotoProfileFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new ProfileFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    // الانتقال إلى Sms
    private void gotoSmsFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new Sms());
        ft.addToBackStack(null);
        ft.commit();
    }
}