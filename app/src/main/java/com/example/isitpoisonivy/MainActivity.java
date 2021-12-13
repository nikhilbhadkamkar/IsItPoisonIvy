package com.example.isitpoisonivy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String userName;
    private static FragmentManager fragmentManager;
    private NavigationBarView bottomNavigationView;
    private static ActionBar actionBar;
    private static Profile user;
    private static ArrayList<Plant> plants;
    private static ArrayList<Report> allReports;

    public static Profile getUser() {
        return user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        plants = new ArrayList<Plant>();
        allReports = new ArrayList<Report>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        actionBar = getSupportActionBar();
        actionBar.hide();
        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnItemSelectedListener(bottomnavFunction);
        openFragment(new DatabaseFragment(), false);

        //initialize user, report, and plant data from database
        try {
            user = DataBaseHelper.initUser(userName);
        } catch (SQLException e) {
            Log.e("SQL", "Error with initUser");
            e.printStackTrace();
        }
        initPlants();
        initReports();
        initAllReports();
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

    /**
     * Initializes all reports associated with the
     * userName of the current user. Does so via use of the DataBaseHelper class.
     */
    private void initReports() {
        try {

            // Establishes a connection with the database.
            DataBaseHelper.establishConnection();

            // Associates reports with the user.
            user.getReports().addAll(DataBaseHelper.getReports(userName.toString()));


        }
        // If a SQL exception occurs, logs the error message.
        catch (SQLException excpt) {
            Log.e("ERROR:", excpt.getMessage());

        }
        // If an unexpected exception occurs, logs the error message.
        catch (Exception excpt) {
            Log.e("ERROR:", excpt.getMessage());

        }
    }

    /**
     * Initializes all reports in the database. Does so via use of the DataBaseHelper class.
     */
    private void initAllReports() {
        try {

            // Establishes a connection with the database.
            DataBaseHelper.establishConnection();

            // Associates reports with the user.
            allReports.addAll(DataBaseHelper.getReports(userName.toString()));


        }
        // If a SQL exception occurs, logs the error message.
        catch (SQLException excpt) {
            Log.e("ERROR:", excpt.getMessage());

        }
        // If an unexpected exception occurs, logs the error message.
        catch (Exception excpt) {
            Log.e("ERROR:", excpt.getMessage());

        }
    }

    /**
     * getter method for all reports
     * @return arraylist of all reports on database
     */
    public static ArrayList<Report> getAllReports(){return allReports;}

    /**
     * Initializes all plants  Does so via use of the DataBaseHelper class.
     */
    private void initPlants() {
        try {

            // Establishes a connection with the database.
            DataBaseHelper.establishConnection();

            // add plants to
            plants.addAll(DataBaseHelper.getPlants());

        }
        // If a SQL exception occurs, logs the error message.
        catch (SQLException excpt) {
            Log.e("ERROR:", excpt.getMessage());

        }
        // If an unexpected exception occurs, logs the error message.
        catch (Exception excpt) {
            Log.e("ERROR:", excpt.getMessage());

        }
    }

    public static ArrayList<Plant> getPlants() {return plants;}

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