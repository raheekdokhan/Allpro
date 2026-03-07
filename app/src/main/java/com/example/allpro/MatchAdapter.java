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

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    private final List<Match> matches;
    private final Context context;

    public MatchAdapter(Context context, List<Match> matches) {
        this.context = context;
        this.matches = matches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_match_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Match match = matches.get(position);

        holder.tvStadium.setText("Stadium: " + match.getStadium());
        holder.tvTeams.setText(match.getHomeTeam() + " vs " + match.getAwayTeam());
        holder.tvDate.setText("Date & Time: " + match.getDateTime());
        holder.tvScore.setText("Live Score: " + (match.getLiveScore().isEmpty() ? "N/A" : match.getLiveScore()));

        holder.btnReminder.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_INSERT)
                    .setData(android.provider.CalendarContract.Events.CONTENT_URI)
                    .putExtra(android.provider.CalendarContract.Events.TITLE,
                            match.getHomeTeam() + " vs " + match.getAwayTeam())
                    .putExtra(android.provider.CalendarContract.Events.DESCRIPTION,
                            "Match at " + match.getStadium())
                    .putExtra(android.provider.CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                            System.currentTimeMillis() + 3600000); // بعد ساعة
            context.startActivity(intent);
        });

        holder.btnLive.setOnClickListener(v -> {
            if (!match.getStreamUrl().isEmpty()) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(match.getStreamUrl()));
                context.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStadium, tvTeams, tvDate, tvScore;
        Button btnReminder, btnLive;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStadium = itemView.findViewById(R.id.tvStadium);
            tvTeams = itemView.findViewById(R.id.tvTeams);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvScore = itemView.findViewById(R.id.tvScore);
            btnReminder = itemView.findViewById(R.id.btnReminder);
            btnLive = itemView.findViewById(R.id.btnLive);
        }
    }
}