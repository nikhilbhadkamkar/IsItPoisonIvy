package com.example.isitpoisonivy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class DatabaseFragment extends Fragment {

    private RecyclerView plantRecyclerView;
    private PlantAdapter plantAdapter;
    private RecyclerView.LayoutManager plantLayoutManager;

    public DatabaseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_database, container, false);
        plantRecyclerView = view.findViewById(R.id.plantRecycle);
        plantRecyclerView.setHasFixedSize(true);
        plantLayoutManager = new LinearLayoutManager(getActivity());
        plantAdapter = new PlantAdapter(MainActivity.getPlants());

        plantRecyclerView.setLayoutManager(plantLayoutManager);
        plantRecyclerView.setAdapter(plantAdapter);

        plantAdapter.setOnItemClickListener(new PlantAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Fragment fragment = new PlantFragment(position);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //transaction.replace(R.id.nav_host_fragment, fragment);
                //transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
                MainActivity.openFragment(fragment, true);
            }
        });

        return view;
    }
}