package com.example.allpro;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.Map;

public class SignupFragment extends Fragment {

    private EditText etUsername, etPassword, etConfirmPassword;
    private EditText etFirstName, etLastName, etPhone, etAddress;
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
        etConfirmPassword = view.findViewById(R.id.etConfirmPasswordSignup);
        etFirstName = view.findViewById(R.id.etFirstNameSignup);
        etLastName = view.findViewById(R.id.etLastNameSignup);
        etPhone = view.findViewById(R.id.etPhoneSignup);
        etAddress = view.findViewById(R.id.etAddressSignup);

        btnSignup = view.findViewById(R.id.btnSignupSignup);
        btnLogin = view.findViewById(R.id.btnLoginSignup);

        btnSignup.setOnClickListener(v -> {

            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();
            String firstName = etFirstName.getText().toString().trim();
            String lastName = etLastName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String address = etAddress.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)
                    || TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName)) {
                Toast.makeText(getActivity(), "Some fields are empty", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            fbs.getAuth().createUserWithEmailAndPassword(username, password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String uid = fbs.getAuth().getCurrentUser().getUid();

                                User user = new User(firstName, lastName, username, address, phone, "");
                                fbs.getFirestore()
                                        .collection("Users")
                                        .document(uid)
                                        .set(user);

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

        btnLogin.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, new AdminFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }
}