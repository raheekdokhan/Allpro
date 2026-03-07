package com.example.allpro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MatchesFragment extends Fragment {

    private RecyclerView recyclerView;
    private MatchAdapter adapter;
    private List<Match> matchList;

    public MatchesFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_matches, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewLiveMatches);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        matchList = new ArrayList<>();
        // أمثلة على المباريات
        matchList.add(new Match("Camp Nou", "FC Barcelona", "Real Madrid", "2026-05-10 20:00", "", "https://www.fcbarcelona.com/tickets"));
        matchList.add(new Match("Anfield", "Liverpool FC", "Manchester United", "2026-04-20 18:30", "1 - 0", "https://www.liverpoolfc.com/tickets"));
        matchList.add(new Match("Allianz Arena", "Bayern Munich", "Dortmund", "2026-03-30 19:00", "", "https://fcbayern.com/tickets"));

// إضافة 4 مباريات جديدة
        matchList.add(new Match("Old Trafford", "Manchester United", "Chelsea FC", "2026-06-05 21:00", "", "https://www.manutd.com/tickets"));
        matchList.add(new Match("Santiago Bernabeu", "Real Madrid", "Atletico Madrid", "2026-06-12 20:30", "", "https://www.realmadrid.com/tickets"));
        matchList.add(new Match("Stamford Bridge", "Chelsea FC", "Arsenal FC", "2026-06-18 19:45", "", "https://www.chelseafc.com/tickets"));
        matchList.add(new Match("Juventus Stadium", "Juventus", "Inter Milan", "2026-06-25 20:15", "", "https://www.juventus.com/tickets"));

        adapter = new MatchAdapter(getContext(), matchList);
        recyclerView.setAdapter(adapter);
    }
}