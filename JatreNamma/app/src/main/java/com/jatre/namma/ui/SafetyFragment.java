package com.jatre.namma.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.jatre.namma.R;

public class SafetyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_safety, container, false);

        Button btnCallAmbulance = view.findViewById(R.id.btn_call_ambulance);
        Button btnCallPolice = view.findViewById(R.id.btn_call_police);
        Button btnCallFire = view.findViewById(R.id.btn_call_fire);
        Button btnCallOrganizer = view.findViewById(R.id.btn_call_organizer);

        btnCallAmbulance.setOnClickListener(v -> dialNumber("108"));
        btnCallPolice.setOnClickListener(v -> dialNumber("100"));
        btnCallFire.setOnClickListener(v -> dialNumber("101"));
        btnCallOrganizer.setOnClickListener(v -> dialNumber("9876543210"));

        return view;
    }

    private void dialNumber(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }
}
