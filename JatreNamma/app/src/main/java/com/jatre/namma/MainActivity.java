package com.jatre.namma;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jatre.namma.ui.HomeFragment;
import com.jatre.namma.ui.ScheduleFragment;
import com.jatre.namma.ui.LostFoundFragment;
import com.jatre.namma.ui.MapFragment;
import com.jatre.namma.ui.SafetyFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_navigation);

        // Load default fragment
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                fragment = new HomeFragment();
            } else if (id == R.id.nav_schedule) {
                fragment = new ScheduleFragment();
            } else if (id == R.id.nav_lost_found) {
                fragment = new LostFoundFragment();
            } else if (id == R.id.nav_map) {
                fragment = new MapFragment();
            } else if (id == R.id.nav_safety) {
                fragment = new SafetyFragment();
            }
            return loadFragment(fragment);
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
