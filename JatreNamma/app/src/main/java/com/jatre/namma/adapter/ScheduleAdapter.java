package com.jatre.namma.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.jatre.namma.R;
import com.jatre.namma.model.ScheduleEvent;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private List<ScheduleEvent> events;

    public ScheduleAdapter(List<ScheduleEvent> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleEvent event = events.get(position);
        holder.tvEmoji.setText(event.getEmoji() != null ? event.getEmoji() : "🎪");
        holder.tvName.setText(event.getName());
        holder.tvTime.setText(event.getTime());
        holder.tvLocation.setText("📍 " + event.getLocation());

        // Color code by status
        switch (event.getStatus()) {
            case "ongoing":
                holder.tvStatus.setText("🔴 LIVE NOW");
                holder.tvStatus.setTextColor(Color.parseColor("#FF0000"));
                holder.card.setCardBackgroundColor(Color.parseColor("#FFF3E0"));
                holder.card.setCardElevation(8f);
                break;
            case "completed":
                holder.tvStatus.setText("✅ Completed");
                holder.tvStatus.setTextColor(Color.parseColor("#4CAF50"));
                holder.card.setCardBackgroundColor(Color.parseColor("#F5F5F5"));
                holder.tvName.setAlpha(0.6f);
                break;
            case "upcoming":
            default:
                holder.tvStatus.setText("⏳ Upcoming");
                holder.tvStatus.setTextColor(Color.parseColor("#FF9800"));
                holder.card.setCardBackgroundColor(Color.WHITE);
                break;
        }
    }

    @Override
    public int getItemCount() { return events.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView tvEmoji, tvName, tvTime, tvLocation, tvStatus;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card_event);
            tvEmoji = itemView.findViewById(R.id.tv_event_emoji);
            tvName = itemView.findViewById(R.id.tv_event_name);
            tvTime = itemView.findViewById(R.id.tv_event_time);
            tvLocation = itemView.findViewById(R.id.tv_event_location);
            tvStatus = itemView.findViewById(R.id.tv_event_status);
        }
    }
}
