package com.example.allpro;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

public class ProfileFragment extends Fragment {

    private EditText etFirstName, etLastName, etEmail, etPhone, etAddress;
    private EditText etPassword, etConfirmPassword;
    private Button btnUpdate;

    private FirebaseServices fbs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
    }

    private void init() {
        fbs = FirebaseServices.getInstance();

        etFirstName = getView().findViewById(R.id.etFirstNameProfile);
        etLastName = getView().findViewById(R.id.etLastNameProfile);
        etEmail = getView().findViewById(R.id.etEmailProfile);
        etPhone = getView().findViewById(R.id.etPhoneProfile);
        etAddress = getView().findViewById(R.id.etAddressProfile);
        etPassword = getView().findViewById(R.id.etPasswordProfile);
        etConfirmPassword = getView().findViewById(R.id.etConfirmPasswordProfile);

        btnUpdate = getView().findViewById(R.id.btnUpdateProfile);
        btnUpdate.setOnClickListener(v -> updateUser());

        loadUserData();
    }

    private void loadUserData() {
        FirebaseUser firebaseUser = fbs.getFirebaseUser();
        if (firebaseUser == null) return;

        etEmail.setText(firebaseUser.getEmail());

        fbs.getUserData(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot doc = task.getResult();
                if (doc != null && doc.exists()) {
                    User user = doc.toObject(User.class);
                    if (user != null) {
                        etFirstName.setText(user.getFirstName());
                        etLastName.setText(user.getLastName());
                        etPhone.setText(user.getPhone());
                        etAddress.setText(user.getAddress());
                    }
                }
            } else {
                Toast.makeText(getActivity(), "Failed to load user data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUser() {
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) ||
                TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(address)) {
            Toast.makeText(getActivity(), "Some fields are empty", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseUser firebaseUser = fbs.getFirebaseUser();
        if (firebaseUser == null) return;

        if (!firebaseUser.getEmail().equals(email)) {
            firebaseUser.updateEmail(email).addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Failed to update email", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (!password.isEmpty()) {
            if (!password.equals(confirmPassword)) {
                Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }
            firebaseUser.updatePassword(password).addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Failed to update password. Re-login may be required.", Toast.LENGTH_LONG).show();
                }
            });
        }

        User user = new User(firstName, lastName, email, address, phone, "");
        fbs.updateUser(user, task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getActivity(), "Data updated successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Failed to update user data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}