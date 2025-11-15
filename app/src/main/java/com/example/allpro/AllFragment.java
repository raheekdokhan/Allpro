package com.example.allpro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class AllFragment extends Fragment {

    private RecyclerView recyclerView;
    private StadiumAdapter adapter;
    private List<Stadium> stadiumList = new ArrayList<>();
    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new StadiumAdapter(getContext(), stadiumList);
        recyclerView.setAdapter(adapter);

        // ✔ التصليح الحقيقي
        db = FirebaseFirestore.getInstance();

        loadData();
    }

    private void loadData() {
        db.collection("stadiums")
                .get()
                .addOnSuccessListener(query -> {
                    stadiumList.clear();
                    for (DocumentSnapshot doc : query.getDocuments()) {
                        Stadium s = doc.toObject(Stadium.class);
                        if (s != null) stadiumList.add(s);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }
}





