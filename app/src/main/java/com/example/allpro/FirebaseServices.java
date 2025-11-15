package com.example.allpro;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseServices {

    // Singleton instance
    private static FirebaseServices instance;

    // Firebase services
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private FirebaseFirestore firestore;

    // Private constructor
    private FirebaseServices() {
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }

    // Get a single shared instance (Thread Safe)
    public static synchronized FirebaseServices getInstance() {
        if (instance == null) {
            instance = new FirebaseServices();
        }
        return instance;
    }

    // Getters
    public FirebaseAuth getAuth() {
        return auth;
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public FirebaseStorage getStorage() {
        return storage;
    }

    public FirebaseFirestore getFirestore() {
        return firestore;
    }
}
