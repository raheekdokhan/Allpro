package com.example.allpro;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {

    private final List<Ticket> tickets;
    private final Context context;

    public TicketAdapter(Context context, List<Ticket> tickets) {
        this.context = context;
        this.tickets = tickets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_ticket_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket ticket = tickets.get(position);
        holder.tvMatchName.setText(ticket.getMatchName());
        holder.tvDate.setText(ticket.getDate());
        holder.tvCategory.setText("Category: " + ticket.getCategory());
        holder.tvPrice.setText("Price: $" + ticket.getPrice());

        holder.btnBook.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ticket.getBookingUrl()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() { return tickets.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMatchName, tvDate, tvCategory, tvPrice;
        Button btnBook;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMatchName = itemView.findViewById(R.id.tvMatchName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            btnBook = itemView.findViewById(R.id.btnBook);
        }
    }
}