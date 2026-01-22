package com.example.allpro;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import androidx.annotation.NonNull;
import com.google.firebase.firestore.DocumentSnapshot;

public class FirebaseServices {

    private static FirebaseServices instance;

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private FirebaseFirestore firestore;

    private FirebaseServices() {
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }

    public static synchronized FirebaseServices getInstance() {
        if (instance == null) {
            instance = new FirebaseServices();
        }
        return instance;
    }

    public FirebaseAuth getAuth() { return auth; }
    public FirebaseDatabase getDatabase() { return database; }
    public FirebaseStorage getStorage() { return storage; }
    public FirebaseFirestore getFirestore() { return firestore; }

    // ===== جلب المستخدم الحالي =====
    public FirebaseUser getFirebaseUser() {
        return auth.getCurrentUser();
    }

    // ===== تحديث بيانات المستخدم في Firestore =====
    public void updateUser(User user, OnCompleteListener<Void> listener) {
        FirebaseUser firebaseUser = getFirebaseUser();
        if (firebaseUser != null) {
            firestore.collection("Users") // <--- توحيد الاسم
                    .document(firebaseUser.getUid())
                    .set(user)
                    .addOnCompleteListener(listener);
        }
    }

    // ===== جلب بيانات المستخدم من Firestore =====
    public void getUserData(OnCompleteListener<DocumentSnapshot> listener) {
        FirebaseUser firebaseUser = getFirebaseUser();
        if (firebaseUser != null) {
            firestore.collection("Users") // <--- نفس الاسم
                    .document(firebaseUser.getUid())
                    .get()
                    .addOnCompleteListener(listener);
        }
    }
}