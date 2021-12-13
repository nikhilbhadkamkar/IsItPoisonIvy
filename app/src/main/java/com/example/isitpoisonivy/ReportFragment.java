package com.example.isitpoisonivy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class ReportFragment extends Fragment {

    private int reportId;
    private Report report;
    private Button openPlant;
    private TextView reportLocText;
    private TextView usernameText;
    private TextView timeText;

    public ReportFragment(Integer position) {
        this.reportId = position;
    }

    public ReportFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        report = MainActivity.getAllReports().get(reportId);
        openPlant = view.findViewById(R.id.plantFragmentButton);
        reportLocText = view.findViewById(R.id.report_loc);
        usernameText = view.findViewById(R.id.report_username);
        timeText = view.findViewById(R.id.report_timestamp);
        openPlant.setText(MainActivity.getPlants().get(report.getPlant_id()).getPlant_name());
        openPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.openFragment(new PlantFragment(report.getPlant_id()), true);
            }
        });

        reportLocText.setText("Location of Report: " + report.getmLocation().toString());
        usernameText.setText("Reported by user: " + report.getUsername());
        timeText.setText("Reported at time: " + report.getTimestamp().toString());

        return view;
    }
}