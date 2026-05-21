package com.jatre.namma.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jatre.namma.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private TextView tvCurrentEvent, tvNextEvent, tvLiveCount, tvDate;
    private DatabaseReference dbRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvCurrentEvent = view.findViewById(R.id.tv_current_event);
        tvNextEvent = view.findViewById(R.id.tv_next_event);
        tvLiveCount = view.findViewById(R.id.tv_live_count);
        tvDate = view.findViewById(R.id.tv_date);

        // Set today's date
        String today = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()).format(new Date());
        tvDate.setText(today);

        // Quick action cards
        CardView cardSchedule = view.findViewById(R.id.card_schedule);
        CardView cardLostFound = view.findViewById(R.id.card_lost_found);
        CardView cardMap = view.findViewById(R.id.card_map);
        CardView cardSafety = view.findViewById(R.id.card_safety);
        CardView cardStories = view.findViewById(R.id.card_stories);

        cardSchedule.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new ScheduleFragment())
                        .commit());

        cardLostFound.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new LostFoundFragment())
                        .commit());

        cardMap.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new MapFragment())
                        .commit());

        cardSafety.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new SafetyFragment())
                        .commit());

        cardStories.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), CulturalStoryActivity.class);
            startActivity(intent);
        });

        // Load live data from Firebase
        loadLiveSchedule();

        return view;
    }

    private void loadLiveSchedule() {
        try {
            dbRef = FirebaseDatabase.getInstance().getReference("schedule");
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // Find current ongoing event
                    boolean foundCurrent = false;
                    for (DataSnapshot eventSnap : snapshot.getChildren()) {
                        String status = eventSnap.child("status").getValue(String.class);
                        String name = eventSnap.child("name").getValue(String.class);
                        String time = eventSnap.child("time").getValue(String.class);
                        if ("ongoing".equals(status) && name != null) {
                            tvCurrentEvent.setText("🔴 LIVE: " + name + " — " + time);
                            foundCurrent = true;
                        }
                        if ("upcoming".equals(status) && name != null) {
                            tvNextEvent.setText("⏳ Next: " + name + " at " + time);
                            break;
                        }
                    }
                    if (!foundCurrent) {
                        tvCurrentEvent.setText("No event currently live");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    tvCurrentEvent.setText("Ratha Starts at 4 PM (Demo)");
                    tvNextEvent.setText("⏳ Next: Wrestling at 6 PM");
                }
            });
        } catch (Exception e) {
            // Demo fallback if Firebase not configured
            tvCurrentEvent.setText("🔴 LIVE: Rathotsava (Ratha) — 4:00 PM");
            tvNextEvent.setText("⏳ Next: Wrestling at 6:00 PM");
            tvLiveCount.setText("1,240 attendees today");
        }
    }
}
