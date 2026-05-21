package com.jatre.namma.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jatre.namma.R;
import com.jatre.namma.adapter.ScheduleAdapter;
import com.jatre.namma.model.ScheduleEvent;
import java.util.ArrayList;
import java.util.List;

public class ScheduleFragment extends Fragment {

    private RecyclerView recyclerView;
    private ScheduleAdapter adapter;
    private List<ScheduleEvent> eventList;
    private DatabaseReference dbRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        recyclerView = view.findViewById(R.id.rv_schedule);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        eventList = new ArrayList<>();
        adapter = new ScheduleAdapter(eventList);
        recyclerView.setAdapter(adapter);

        loadSchedule();
        return view;
    }

    private void loadSchedule() {
        // Demo data — will be overridden by Firebase if configured
        populateDemoData();

        try {
            dbRef = FirebaseDatabase.getInstance().getReference("schedule");
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        eventList.clear();
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            ScheduleEvent event = snap.getValue(ScheduleEvent.class);
                            if (event != null) {
                                eventList.add(event);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) { /* use demo data */ }
            });
        } catch (Exception ignored) { /* use demo data */ }
    }

    private void populateDemoData() {
        eventList.clear();
        eventList.add(new ScheduleEvent("Puja & Mangalacharana", "7:00 AM", "completed", "Ganesha Temple", "🙏"));
        eventList.add(new ScheduleEvent("Cattle Fair & Exhibition", "10:00 AM", "completed", "South Ground", "🐄"));
        eventList.add(new ScheduleEvent("Drama — Mahabharata", "1:00 PM", "completed", "Main Stage", "🎭"));
        eventList.add(new ScheduleEvent("Rathotsava (Ratha)", "4:00 PM", "ongoing", "Main Street", "🏛️"));
        eventList.add(new ScheduleEvent("Wrestling (Kushti)", "6:00 PM", "upcoming", "Akhada Ground", "🤼"));
        eventList.add(new ScheduleEvent("Folk Music & Dance", "8:00 PM", "upcoming", "Cultural Stage", "🎶"));
        eventList.add(new ScheduleEvent("Fireworks Display", "10:00 PM", "upcoming", "Temple Premises", "🎆"));
        adapter.notifyDataSetChanged();
    }
}
