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
import com.jatre.namma.model.CulturalStory;
import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    private List<CulturalStory> stories;

    public StoryAdapter(List<CulturalStory> stories) {
        this.stories = stories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_story, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CulturalStory story = stories.get(position);
        holder.tvEmoji.setText(story.getEmoji());
        holder.tvTitle.setText(story.getTitle());
        holder.tvContent.setText(story.getContent());
        try {
            holder.card.setCardBackgroundColor(Color.parseColor(story.getColorHex() + "22"));
            holder.tvEmoji.setBackgroundColor(Color.parseColor(story.getColorHex()));
        } catch (Exception ignored) {}
    }

    @Override
    public int getItemCount() { return stories.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView tvEmoji, tvTitle, tvContent;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card_story);
            tvEmoji = itemView.findViewById(R.id.tv_story_emoji);
            tvTitle = itemView.findViewById(R.id.tv_story_title);
            tvContent = itemView.findViewById(R.id.tv_story_content);
        }
    }
}
