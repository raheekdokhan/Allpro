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

public class TicketsFragment extends Fragment {

    private RecyclerView recyclerView;
    private TicketAdapter adapter;
    private List<Ticket> tickets;

    public TicketsFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ticket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewTickets);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tickets = new ArrayList<>();
        tickets = new ArrayList<>();

        tickets.add(new Ticket("FC Barcelona vs Real Madrid", "2026-05-10", "VIP", 250.0, "https://www.fcbarcelona.com/tickets"));
        tickets.add(new Ticket("Liverpool FC vs Manchester United", "2026-04-20", "Regular", 120.0, "https://www.liverpoolfc.com/tickets"));
        tickets.add(new Ticket("Bayern Munich vs Dortmund", "2026-03-30", "Premium", 180.0, "https://www.bvb.de/de/en/tickets.html"));
        tickets.add(new Ticket("Manchester United vs Chelsea", "2026-06-05", "VIP", 300.0, "https://www.chelseafc.com/en/tickets/mens-tickets"));
        tickets.add(new Ticket("Real Madrid vs Atletico Madrid", "2026-05-15", "Regular", 150.0, "https://en.atleticodemadrid.com/listado-de-entradas"));
        tickets.add(new Ticket("Arsenal vs Tottenham", "2026-04-25", "Premium", 200.0, "https://www.arsenal.com/tickets"));
        tickets.add(new Ticket("Juventus vs Inter Milan", "2026-05-20", "VIP", 280.0, "https://www.juventus.com/tickets"));
        tickets.add(new Ticket("Paris Saint-Germain vs Marseille", "2026-06-10", "Regular", 170.0, "https://www.psg.fr/tickets"));
        tickets.add(new Ticket("Juventus vs AC Milan", "2026-06-15", "VIP", 220.0, "https://www.juventus.com/tickets"));
        tickets.add(new Ticket("Paris Saint-Germain vs Lyon", "2026-07-05", "Regular", 150.0, "https://www.psg.fr/tickets"));

        adapter = new TicketAdapter(getContext(), tickets);
        recyclerView.setAdapter(adapter);
    }
}