package com.example.isitpoisonivy;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 *
 * create an instance of this fragment.
 */
public class CameraFragment extends Fragment {
    private Button identifyButton;
    private Button reportSightingButton;


    Context mcontext;

    public CameraFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment CameraFragment.
     */


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
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        identifyButton = view.findViewById(R.id.identify_button);
        reportSightingButton = view.findViewById(R.id.report_button);

        identifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.openFragment(new IdentifyFragment(), true);
            }
        });
        reportSightingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dispatchTakePictureIntent();
                MainActivity.openFragment(new FirstReportFragment(), true);
            }
        });
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        mcontext = getContext();
    }

}