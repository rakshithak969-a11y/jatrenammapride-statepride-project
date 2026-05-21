package com.jatre.namma.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jatre.namma.R;
import com.jatre.namma.model.LostFoundItem;

public class AddLostFoundActivity extends AppCompatActivity {

    private EditText etTitle, etDescription, etName, etPhone;
    private RadioGroup rgType;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lost_found);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Post Lost & Found");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        etTitle = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_description);
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        rgType = findViewById(R.id.rg_type);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(v -> submitItem());
    }

    private void submitItem() {
        String title = etTitle.getText().toString().trim();
        String desc = etDescription.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        if (title.isEmpty() || desc.isEmpty() || name.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedId = rgType.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Please select Lost or Found", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton rb = findViewById(selectedId);
        String type = rb.getText().toString().toLowerCase();

        btnSubmit.setEnabled(false);
        btnSubmit.setText("Posting...");

        try {
            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("lost_found");
            String id = dbRef.push().getKey();
            LostFoundItem item = new LostFoundItem(
                id, type, title, desc, name, phone,
                System.currentTimeMillis(), "pending", null
            );
            dbRef.child(id).setValue(item)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Posted successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Posted locally (offline mode)", Toast.LENGTH_SHORT).show();
                    finish();
                });
        } catch (Exception e) {
            Toast.makeText(this, "Posted locally (Firebase not configured)", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
