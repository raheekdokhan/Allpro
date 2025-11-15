package com.example.allpro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SignupFragment extends Fragment {

    private EditText etUsername, etPassword;
    private Button btnSignup, btnLogin;
    private FirebaseServices fbs;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fbs = FirebaseServices.getInstance();
        etUsername = view.findViewById(R.id.etUsernameSignup);
        etPassword = view.findViewById(R.id.etPasswordSignup);
        btnSignup = view.findViewById(R.id.btnSignupSignup);
        btnLogin = view.findViewById(R.id.btnLoginSignup);

        // Signup → AdminFragment
        btnSignup.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(getActivity(), "Some fields are empty", Toast.LENGTH_SHORT).show();
                return;
            }

            fbs.getAuth().createUserWithEmailAndPassword(username, password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "Signup successful!", Toast.LENGTH_SHORT).show();
                                getParentFragmentManager().beginTransaction()
                                        .replace(R.id.frameLayout, new AdminFragment())
                                        .commit();
                            } else {
                                Toast.makeText(getActivity(), "Signup failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });

        // Login → LoginFragment
        btnLogin.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, new AdminFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }
}
