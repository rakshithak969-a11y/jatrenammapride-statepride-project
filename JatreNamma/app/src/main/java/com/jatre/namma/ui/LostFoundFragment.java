package com.jatre.namma.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jatre.namma.R;
import com.jatre.namma.adapter.LostFoundAdapter;
import com.jatre.namma.model.LostFoundItem;
import java.util.ArrayList;
import java.util.List;

public class LostFoundFragment extends Fragment {

    private RecyclerView recyclerView;
    private LostFoundAdapter adapter;
    private List<LostFoundItem> itemList;
    private TabLayout tabLayout;
    private FloatingActionButton fabAdd;
    private DatabaseReference dbRef;
    private String currentFilter = "all";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lost_found, container, false);

        recyclerView = view.findViewById(R.id.rv_lost_found);
        tabLayout = view.findViewById(R.id.tab_layout);
        fabAdd = view.findViewById(R.id.fab_add);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemList = new ArrayList<>();
        adapter = new LostFoundAdapter(itemList, requireActivity());
        recyclerView.setAdapter(adapter);

        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), AddLostFoundActivity.class);
            startActivity(intent);
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0: currentFilter = "all"; break;
                    case 1: currentFilter = "lost"; break;
                    case 2: currentFilter = "found"; break;
                    case 3: currentFilter = "resolved"; break;
                }
                loadItems();
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });

        loadItems();
        return view;
    }

    private void loadItems() {
        populateDemoItems();
        try {
            dbRef = FirebaseDatabase.getInstance().getReference("lost_found");
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        itemList.clear();
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            LostFoundItem item = snap.getValue(LostFoundItem.class);
                            if (item != null) {
                                if (currentFilter.equals("all") || item.getType().equals(currentFilter)) {
                                    itemList.add(item);
                                }
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });
        } catch (Exception ignored) {}
    }

    private void populateDemoItems() {
        itemList.clear();
        itemList.add(new LostFoundItem("id1", "lost", "Child Lost", "5-year-old boy, red shirt, near Ratha route", "Ramesh Kumar", "9876543210", System.currentTimeMillis(), "pending", null));
        itemList.add(new LostFoundItem("id2", "lost", "Blue Wallet", "Lost near cattle fair area, has Aadhaar card inside", "Suresh B", "9845001122", System.currentTimeMillis() - 3600000, "pending", null));
        itemList.add(new LostFoundItem("id3", "found", "Gold Earring", "Found near main temple entrance, one gold jhumka", "Lakshmi Devi", "9900112233", System.currentTimeMillis() - 7200000, "pending", null));
        itemList.add(new LostFoundItem("id4", "found", "Mobile Phone (Samsung)", "Samsung Galaxy S23, found near wrestling ground", "Prasad M", "9988776655", System.currentTimeMillis() - 10800000, "resolved", null));

        List<LostFoundItem> filtered = new ArrayList<>();
        for (LostFoundItem item : itemList) {
            if (currentFilter.equals("all") || item.getType().equals(currentFilter)) {
                filtered.add(item);
            }
        }
        itemList.clear();
        itemList.addAll(filtered);
        adapter.notifyDataSetChanged();
    }
}
