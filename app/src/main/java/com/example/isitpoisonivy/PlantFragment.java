package com.example.isitpoisonivy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PlantFragment extends Fragment {

    private int plantPosition;
    Plant mplant;
    private TextView plantIdText;
    private TextView plantNameText;
    private TextView botanicalNameText;
    private TextView symptomsText;

    public PlantFragment(Integer position) {
        this.plantPosition = position;
    }

    public PlantFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_plant, container, false);
        mplant = MainActivity.getPlants().get(plantPosition);
        plantIdText = view.findViewById(R.id.plantId);
        plantNameText = view.findViewById(R.id.plantName);
        botanicalNameText = view.findViewById(R.id.botanicalName);
        symptomsText = view.findViewById(R.id.symptoms);

        plantIdText.setText("Plant Id: "+mplant.getPlant_id());
        plantNameText.setText("Plant Id: "+mplant.getPlant_name());
        botanicalNameText.setText("Plant Id: "+mplant.getBotanical_name());
        symptomsText.setText("Plant Id: "+mplant.getSymptoms());

        return view;
    }
}