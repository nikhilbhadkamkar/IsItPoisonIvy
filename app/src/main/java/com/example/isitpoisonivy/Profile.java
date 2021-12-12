package com.example.isitpoisonivy;

import android.graphics.Bitmap;
import android.media.Image;
import android.provider.ContactsContract;
import android.widget.ImageView;


import java.sql.SQLException;
import java.util.ArrayList;

public class Profile {
    private String username;
    private String password; //not sure if we want this...
    private String email;
    private ArrayList<Report> reports;


    Profile() {
        username = null;
        password = null;
        email = null;
        reports = new ArrayList<Report>();

    }

    public ArrayList<Report> getReports() {
        return reports;
    }

    public String getUsername() {
        return username;
    }


    public String getEmail() {
        return email;
    }


    Profile(String username, String password, String email, Bitmap profilePhoto, ArrayList<Report> reports) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.reports = reports;
    }




    /**
     * @param report the report to be associated with this Profile.
     */
    public void addReport(Report report) {

        try {
            DataBaseHelper.addReport(MainActivity.getUser().getUsername(), report.getmLocation(),
                    report.getReportid(), report.getPlant_id());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        reports.add(report);

    }



    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setReports(ArrayList<Report> reports) {
        this.reports = reports;
    }



}