package com.jatre.namma.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jatre.namma.R;

public class LostFoundDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_found_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Item Detail");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String id = getIntent().getStringExtra("id");
        String type = getIntent().getStringExtra("type");
        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("description");
        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        String status = getIntent().getStringExtra("status");

        TextView tvType = findViewById(R.id.tv_type);
        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvDesc = findViewById(R.id.tv_description);
        TextView tvName = findViewById(R.id.tv_contact_name);
        TextView tvStatus = findViewById(R.id.tv_status);

        Button btnCall = findViewById(R.id.btn_call);
        Button btnResolve = findViewById(R.id.btn_resolve);

        tvType.setText(type != null ? type.toUpperCase() : "");
        tvTitle.setText(title);
        tvDesc.setText(desc);
        tvName.setText("Contact: " + name);
        tvStatus.setText("Status: " + status);

        btnCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phone));
            startActivity(intent);
        });

        btnResolve.setOnClickListener(v -> {
            try {
                DatabaseReference ref = FirebaseDatabase.getInstance()
                        .getReference("lost_found").child(id).child("status");
                ref.setValue("resolved")
                        .addOnSuccessListener(unused -> {
                            Toast.makeText(this, "Marked as Resolved ✅", Toast.LENGTH_SHORT).show();
                            finish();
                        });
            } catch (Exception e) {
                Toast.makeText(this, "Marked as Resolved ✅ (offline)", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        if ("resolved".equals(status)) {
            btnResolve.setEnabled(false);
            btnResolve.setText("Already Resolved");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
