package com.jatre.namma.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.jatre.namma.R;

public class MapFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        // Map markers info cards
        CardView[] markers = {
            view.findViewById(R.id.marker_temple),
            view.findViewById(R.id.marker_ratha),
            view.findViewById(R.id.marker_wrestling),
            view.findViewById(R.id.marker_cattle),
            view.findViewById(R.id.marker_firstaid),
            view.findViewById(R.id.marker_parking),
            view.findViewById(R.id.marker_food),
        };

        TextView tvMarkerInfo = view.findViewById(R.id.tv_marker_info);

        String[] markerInfo = {
            "🏛️ Main Temple\nPuja & Rathotsava start here.\nOpen: 6 AM – 11 PM",
            "🚩 Ratha Route\nRatha procession from temple to\nKoppal Circle and back.",
            "🤼 Akhada Ground\nKushti (Wrestling) starts 6 PM.\nFree entry for all.",
            "🐄 Cattle Fair Ground\nBuying/selling of cattle.\nOpen: 9 AM – 4 PM",
            "🏥 First Aid Post\n24/7 Medical Help Available.\nCall: 108",
            "🅿️ Parking Zone\nDesignated parking for bikes\nand cars. Free of charge.",
            "🍡 Food Stalls Area\nSweets, snacks, and traditional\nfood. 50+ stalls available."
        };

        for (int i = 0; i < markers.length; i++) {
            final int index = i;
            if (markers[i] != null) {
                markers[i].setOnClickListener(v ->
                    tvMarkerInfo.setText(markerInfo[index]));
            }
        }

        return view;
    }
}
