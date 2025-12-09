package com.example.allpro;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class StadiumAdapter extends RecyclerView.Adapter<StadiumAdapter.ViewHolder> {

    private Context context;
    private List<Stadium> list;

    public StadiumAdapter(Context context, List<Stadium> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.fragment_item_stadium, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Stadium stadium = list.get(position);


        holder.teamName.setText("Team: " + stadium.getTeamName());
        holder.stadiumName.setText("Stadium: " + stadium.getStadiumName());
        holder.location.setText("Location: " + stadium.getStadiumLocation());
        holder.capacity.setText("Capacity: " + stadium.getStadiumCapacity());

        // ⭐ تحميل الصورة بطريقة آمنة
        if (stadium.getPhoto() != null && !stadium.getPhoto().isEmpty()) {
            Picasso.get()
                    .load(stadium.getPhoto())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.imageView);
        } else {
            holder.imageView.setImageResource(R.drawable.ic_launcher_foreground);
        }
    }

    @Override

    public int getItemCount() {
        return list.size();
    }

    // ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView teamName, stadiumName, location, capacity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivStadiumImage);
            teamName = itemView.findViewById(R.id.tvTeamName);
            stadiumName = itemView.findViewById(R.id.tvStadiumName);
            location = itemView.findViewById(R.id.tvLocation);
            capacity = itemView.findViewById(R.id.tvCapacity);
        }
    }
} 













