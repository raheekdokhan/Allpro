package com.example.allpro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;

public class AdminFragment extends Fragment {

    public AdminFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnAdd = view.findViewById(R.id.btnAdd);
        Button btnAll = view.findViewById(R.id.btnAll);
        CardView cardUserProfile = view.findViewById(R.id.cardUserProfile);

        btnAdd.setOnClickListener(v -> getParentFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new StadiumFragment())
                .addToBackStack(null)
                .commit());

        btnAll.setOnClickListener(v -> getParentFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new AllFragment())
                .addToBackStack(null)
                .commit());

        cardUserProfile.setOnClickListener(v -> getParentFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new UserProfileFragment())
                .addToBackStack(null)
                .commit());
    }
}
