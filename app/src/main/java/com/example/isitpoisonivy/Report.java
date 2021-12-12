package com.example.isitpoisonivy;

import android.provider.ContactsContract;

import com.google.android.gms.maps.model.LatLng;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class represents a Hive in the HiveManagement system.
 */
public class Report {
    private LatLng mLocation;
    private Timestamp timestamp;
    private String username;
    private int reportid;
    private int plant_id;


    public Report(int reportid) { //TODO should we always require at least an ID is entered upon object construction
        this.reportid = reportid;
    }

    public Report(LatLng mLocation, Timestamp timestamp, String username,
                int reportid, int plant_id) {
        this.mLocation = mLocation;
        this.timestamp = timestamp;
        this.username = username;
        this.reportid = reportid;
        this.plant_id = plant_id;
    }


    public LatLng getmLocation() {
        return mLocation;
    }

    public void setmLocation(LatLng mLocation) {
        this.mLocation = mLocation;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getReportid() {
        return reportid;
    }

    public void setReportid(int reportid) {
        this.reportid = reportid;
    }

    public int getPlant_id() {
        return plant_id;
    }

    public void setPlant_id(int plant_id) {
        this.plant_id = plant_id;
    }






}