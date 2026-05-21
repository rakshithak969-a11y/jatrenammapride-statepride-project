package com.jatre.namma.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.jatre.namma.R;
import com.jatre.namma.model.LostFoundItem;
import com.jatre.namma.ui.LostFoundDetailActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LostFoundAdapter extends RecyclerView.Adapter<LostFoundAdapter.ViewHolder> {

    private List<LostFoundItem> items;
    private Context context;

    public LostFoundAdapter(List<LostFoundItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lost_found, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LostFoundItem item = items.get(position);

        holder.tvTitle.setText(item.getTitle());
        holder.tvDesc.setText(item.getDescription());
        holder.tvContact.setText("👤 " + item.getContactName());
        holder.tvTime.setText(formatTime(item.getTimestamp()));

        if ("lost".equals(item.getType())) {
            holder.tvType.setText("🔍 LOST");
            holder.tvType.setBackgroundColor(Color.parseColor("#FF5252"));
        } else {
            holder.tvType.setText("✅ FOUND");
            holder.tvType.setBackgroundColor(Color.parseColor("#4CAF50"));
        }

        if ("resolved".equals(item.getStatus())) {
            holder.tvStatus.setText("RESOLVED");
            holder.tvStatus.setVisibility(View.VISIBLE);
            holder.card.setAlpha(0.6f);
        } else {
            holder.tvStatus.setVisibility(View.GONE);
            holder.card.setAlpha(1f);
        }

        holder.card.setOnClickListener(v -> {
            Intent intent = new Intent(context, LostFoundDetailActivity.class);
            intent.putExtra("id", item.getId());
            intent.putExtra("type", item.getType());
            intent.putExtra("title", item.getTitle());
            intent.putExtra("description", item.getDescription());
            intent.putExtra("name", item.getContactName());
            intent.putExtra("phone", item.getContactPhone());
            intent.putExtra("status", item.getStatus());
            context.startActivity(intent);
        });
    }

    private String formatTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a, dd MMM", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView tvType, tvTitle, tvDesc, tvContact, tvTime, tvStatus;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card_lf);
            tvType = itemView.findViewById(R.id.tv_lf_type);
            tvTitle = itemView.findViewById(R.id.tv_lf_title);
            tvDesc = itemView.findViewById(R.id.tv_lf_desc);
            tvContact = itemView.findViewById(R.id.tv_lf_contact);
            tvTime = itemView.findViewById(R.id.tv_lf_time);
            tvStatus = itemView.findViewById(R.id.tv_lf_status);
        }
    }
}
