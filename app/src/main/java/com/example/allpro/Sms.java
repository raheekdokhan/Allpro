package com.example.allpro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Sms extends Fragment {

    private EditText etPhoneNumber;
    private EditText etMessage;
    private Button btnSend;

    public Sms() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sms, container, false);

        // 🔹 ربط العناصر
        etPhoneNumber = view.findViewById(R.id.etPhoneNumber);
        etMessage = view.findViewById(R.id.etMessage);
        btnSend = view.findViewById(R.id.btnSendSMS);

        // 🔹 حدث الزر
        btnSend.setOnClickListener(v -> {

            String phone = etPhoneNumber.getText().toString().trim();
            String message = etMessage.getText().toString().trim();

            if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(message)) {
                sendSmsWithIntent(phone, message);
            } else {
                Toast.makeText(getContext(),
                        "Some fields are empty",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    // ✅ إرسال عبر تطبيق الرسائل (المفضل)
    private void sendSmsWithIntent(String phone, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phone));
        intent.putExtra("sms_body", message);

        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getContext(),
                    "SMS failed",
                    Toast.LENGTH_SHORT).show();
        }
    }
}