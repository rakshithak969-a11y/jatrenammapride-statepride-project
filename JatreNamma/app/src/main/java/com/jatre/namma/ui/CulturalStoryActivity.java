package com.jatre.namma.ui;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jatre.namma.R;
import com.jatre.namma.adapter.StoryAdapter;
import com.jatre.namma.model.CulturalStory;
import java.util.ArrayList;
import java.util.List;

public class CulturalStoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultural_story);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Cultural Stories");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView rv = findViewById(R.id.rv_stories);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<CulturalStory> stories = new ArrayList<>();
        stories.add(new CulturalStory(
            "The Legend of Rathotsava",
            "The Ratha Yatra (Chariot Festival) is one of the most ancient and sacred traditions of Indian culture. " +
            "The massive wooden chariot, decorated with flowers and silk, carries the deity through the streets. " +
            "Pulling the ratha's rope is believed to wash away all sins and bring prosperity to the family. " +
            "The tradition dates back over 1,000 years in our region.",
            "🏛️", "#FF6B35"
        ));
        stories.add(new CulturalStory(
            "Kushti — The Ancient Art of Wrestling",
            "Kushti (traditional Indian wrestling) has been practiced in akharas (wrestling pits) for centuries. " +
            "Young wrestlers train for years under a guru, following strict diet and discipline. " +
            "The Jatre wrestling competition is a prestigious event where champions from nearby villages compete " +
            "for honor and a gold medal.",
            "🤼", "#4CAF50"
        ));
        stories.add(new CulturalStory(
            "The Cattle Fair Tradition",
            "The cattle fair (Janawar Mela) has been an economic backbone of rural Karnataka for generations. " +
            "Farmers travel from distant villages to buy, sell, and trade cattle. " +
            "The fair also showcases indigenous breeds of cattle, many of which are now rare. " +
            "It is both a marketplace and a cultural celebration of the agrarian way of life.",
            "🐄", "#8D6E63"
        ));
        stories.add(new CulturalStory(
            "Folk Drama — Stories from the Epics",
            "The Jatre drama troupe performs stories from the Mahabharata and Ramayana through the night. " +
            "This tradition of Yakshagana and Bayalata has been passed down through generations. " +
            "Local artists spend months preparing elaborate costumes and memorizing verses. " +
            "For many villagers, this is the only theatrical performance they witness all year.",
            "🎭", "#9C27B0"
        ));
        stories.add(new CulturalStory(
            "Why We Celebrate — The Founding Story",
            "According to local legend, the village Jatre was started 300 years ago by the founder of the village " +
            "as a vow to the village deity after a terrible drought ended. " +
            "The first rains came on the same day as the puja, and from that day forward, " +
            "the entire village gathers every year to give thanks, celebrate life, and strengthen community bonds.",
            "✨", "#FF9800"
        ));

        StoryAdapter adapter = new StoryAdapter(stories);
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
