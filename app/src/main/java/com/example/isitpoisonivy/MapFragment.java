package com.example.isitpoisonivy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.fragment_map);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                //googleMap.addMarker(new MarkerOptions().position(new LatLng(45.6366, -89.4061951)).title("Poison Ivy"));
                for (int i=0; i<MainActivity.getAllReports().size(); i++) {
                    googleMap.addMarker(new MarkerOptions().position(MainActivity.getAllReports().get(i).getmLocation()).title(MainActivity.getPlants().get(MainActivity.getAllReports().get(i).getPlant_id()).getPlant_name()).zIndex(i));

                }
                googleMap.setOnMarkerClickListener(new OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {
                        int reportNum = Math.round(marker.getZIndex());
                        MainActivity.getAllReports().get(reportNum).getReportid();
                        Fragment fragment = new ReportFragment(reportNum);
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        //transaction.replace(R.id.nav_host_fragment, fragment);
                        //transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                        transaction.commit();
                        MainActivity.openFragment(fragment, true);


                        Log.w("Click", "test");
                        return false;
                    }
                });
            }
        });

        return view;
    }
}