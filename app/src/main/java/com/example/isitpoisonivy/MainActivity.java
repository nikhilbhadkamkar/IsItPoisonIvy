package com.example.isitpoisonivy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    public static String userName;
    private static FragmentManager fragmentManager;
    private NavigationBarView bottomNavigationView;
    private static ActionBar actionBar;
    private static Profile user;

    public static Profile getUser() {
        return user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        actionBar = getSupportActionBar();
        actionBar.hide();
        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnItemSelectedListener(bottomnavFunction);
        openFragment(new DatabaseFragment(), false);
    }

    public static void openFragment(Fragment fragment, boolean actionBar) {
        if(actionBar) {
            MainActivity.actionBar.show();
        }
        else {
            MainActivity.actionBar.hide();
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, "visible_fragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private NavigationBarView.OnItemSelectedListener bottomnavFunction = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.database:
                    fragment = new DatabaseFragment();
                    break;
                case R.id.camera:
                    fragment = new CameraFragment();
                    break;
                case R.id.map:
                    fragment = new MapFragment();
                    break;
                case R.id.profile:
                    fragment = new ProfileFragment();
                    break;
            }
            openFragment(fragment, false);
            return true;
        }
    };
}