package com.example.allpro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class StadiumFragment extends Fragment {

    private EditText editTeamName, editStadiumName, editLocation, editCapacity;
    private ImageView imgStadium;
    private Button btnChoosePhoto, btnSubmit;
    private Uri selectedImageUri;
    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stadium, container, false);

        editTeamName = view.findViewById(R.id.editTextTeamName);
        editStadiumName = view.findViewById(R.id.editTextStadiumName);
        editLocation = view.findViewById(R.id.editTextStadiumLocation);
        editCapacity = view.findViewById(R.id.editTextStadiumCapacity);
        imgStadium = view.findViewById(R.id.imgStadium);
        btnChoosePhoto = view.findViewById(R.id.buttonChoosePhoto);
        btnSubmit = view.findViewById(R.id.buttonSubmit);

        db = FirebaseFirestore.getInstance();

        ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == requireActivity().RESULT_OK && result.getData() != null) {
                        selectedImageUri = result.getData().getData();
                        imgStadium.setImageURI(selectedImageUri);
                    }
                });

        btnChoosePhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickImageLauncher.launch(intent);
        });

        btnSubmit.setOnClickListener(v -> saveStadium());

        return view;
    }

    private void saveStadium() {
        String teamName = editTeamName.getText().toString().trim();
        String stadiumName = editStadiumName.getText().toString().trim();
        String location = editLocation.getText().toString().trim();
        String capacityStr = editCapacity.getText().toString().trim();

        if (teamName.isEmpty() || stadiumName.isEmpty() || location.isEmpty() || capacityStr.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedImageUri == null) {
            Toast.makeText(getContext(), "Please choose a stadium image", Toast.LENGTH_SHORT).show();
            return;
        }

        int capacity;
        try {
            capacity = Integer.parseInt(capacityStr);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Capacity must be a number", Toast.LENGTH_SHORT).show();
            return;
        }

        // رفع الصورة على Firebase Storage
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        String fileName = "stadiums/" + UUID.randomUUID().toString() + ".jpg";
        StorageReference imageRef = storageRef.child(fileName);

        imageRef.putFile(selectedImageUri)
                .addOnSuccessListener(taskSnapshot -> imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    String imageUrl = uri.toString();

                    Stadium stadium = new Stadium(teamName, stadiumName, location, capacity, imageUrl);

                    db.collection("stadiums")
                            .add(stadium)
                            .addOnSuccessListener(doc -> {
                                Toast.makeText(getContext(), "Stadium added successfully!", Toast.LENGTH_SHORT).show();
                                clearFields();
                            })
                            .addOnFailureListener(e -> Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());

                }))
                .addOnFailureListener(e -> Toast.makeText(getContext(), "Failed to upload image: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void clearFields() {
        editTeamName.setText("");
        editStadiumName.setText("");
        editLocation.setText("");
        editCapacity.setText("");
        imgStadium.setImageResource(android.R.color.transparent);
        selectedImageUri = null;
    }
}
