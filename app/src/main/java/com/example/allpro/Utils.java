package com.example.allpro;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Utils {

    private static Utils instance;

    public static Utils getInstance() {
        if (instance == null)
            instance = new Utils();
        return instance;
    }

    public interface UploadCallback {
        void onUploaded(String downloadUrl);
    }

    // رفع صورة وإرجاع الرابط
    public void uploadImage(Context context, Uri imageUri, UploadCallback callback) {

        if (imageUri == null) {
            Toast.makeText(context, "Image URI is null", Toast.LENGTH_SHORT).show();
            return;
        }

        StorageReference storageRef = FirebaseStorage.getInstance()
                .getReference("stadiums/" + System.currentTimeMillis() + ".jpg");

        storageRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot ->
                        storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                            String downloadUrl = uri.toString();
                            callback.onUploaded(downloadUrl);
                        }).addOnFailureListener(e ->
                                Toast.makeText(context, "Error getting download URL: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                        ))
                .addOnFailureListener(e ->
                        Toast.makeText(context, "Upload Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }
}


