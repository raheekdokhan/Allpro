package com.example.allpro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AllNewFragment extends Fragment {

    public AllNewFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_new, container, false);

        Button btnQuiz = view.findViewById(R.id.btnQuiz);
        Button btnTickets = view.findViewById(R.id.btnTickets);
        Button btnMatches = view.findViewById(R.id.btnMatches);

        btnQuiz.setOnClickListener(v -> {
            FragmentTransaction ft = getParentFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new QuizFragment());
            ft.addToBackStack(null);
            ft.commit();
        });

        btnTickets.setOnClickListener(v -> {
            FragmentTransaction ft = getParentFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new TicketsFragment());
            ft.addToBackStack(null);
            ft.commit();
        });

        btnMatches.setOnClickListener(v -> {
            FragmentTransaction ft = getParentFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new MatchesFragment()); // MatchesFragment = جدول المباريات
            ft.addToBackStack(null);
            ft.commit();
        });

        return view;
    }
}