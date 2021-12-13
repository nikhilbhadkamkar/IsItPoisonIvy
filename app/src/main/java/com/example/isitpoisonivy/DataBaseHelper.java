package com.example.isitpoisonivy;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.text.Editable;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DataBaseHelper {
    static Connection con;

    /**
     * Connects to the database. Should be initialized exactly once.
     * If called when connection is already initialized, will return the existing connection.
     *
     * @return con a connection to the database.
     */
    public static Connection establishConnection() {
        // Returns con if it already exists.
        if (con != null) return con;

        // Thread policy.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Establishes a connection with the SQL server.
        con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://harmfulplantdatabase.c276elrnqxaz.us-east-2.rds.amazonaws.com:3306/harmfulPlantDB", "admin", "CS407poison");

        }
        // Returns an error message if connection fails.
        catch (Exception e) {
            Log.e("SQL Connection Error : ", e.getMessage());
        }
        // Returns the connection.
        return con;
    }


    //returns an ArrayList of all the user fields in the format[username,firstname,email,lastname,phone_number,ppr,password,address,zipcode]
    public static ArrayList getUserData(String userName) throws SQLException {
        Statement stmt;
        Connection con = establishConnection();

        String sql = "SELECT * FROM user_table WHERE username = '" + userName.toString() + "'";
        stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        ArrayList userlist = new ArrayList();
        while (rs.next()) {

            String username = rs.getString("Username");
            userlist.add(username);
            String email = rs.getString("Email");
            userlist.add(email);
        }
        return userlist;
    }

    public static Profile initUser(String userName) throws SQLException {
        Profile user = new Profile();
        Statement stmt;
        Connection con = establishConnection();

        String sql = "SELECT * FROM user_table WHERE username = '" + userName.toString() + "'";
        stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
        }
        return user;
    }

    /**
     * Returns a list of reports associated with the inputed user.
     *
     * The reports will be initiated with all data relevant to report
     *
     * @param user the user to retrieve reports for.
     * @return a list of reports associated with the inputed user.
     * @throws SQLException if a SQL query fails.
     */
    public static ArrayList<Report> getReports(String user) throws SQLException {
        String sql;
        Statement stmt;
        ResultSet results;
        ArrayList<Report> reports = new ArrayList<>();

        // Issues a SQL query to find all Apiaries associated with user.
        sql = "SELECT *, ST_Longitude(latLng) AS lat, ST_Latitude(latLng) AS lng " +
                "FROM report_table " +
                "WHERE username = \"" + user + "\""
        ;
        stmt = con.createStatement();
        results = stmt.executeQuery(sql);

        // Fills the ArrayList of Apiaries.
        while (results.next()) {
            LatLng latlng = new LatLng(results.getDouble("lat"), results.getDouble("lng"));
            reports.add(new Report(latlng,
                    results.getTimestamp("timestamp"), results.getString("username"),
            results.getInt("reportid"), results.getInt("plant_id")));

        }

        // Returns the list of reports associated with user.
        return reports;

    }

    /**
     * Returns a list of reports in the database
     *
     * The reports will be initiated with all data relevant to report
     *
     * @return a list of reports associated with the inputed user.
     * @throws SQLException if a SQL query fails.
     */
    public static ArrayList<Report> getAllReports() throws SQLException {
        String sql;
        Statement stmt;
        ResultSet results;
        ArrayList<Report> reports = new ArrayList<>();

        // Issues a SQL query to find all Apiaries associated with user.
        sql = "SELECT *, ST_Longitude(latLng) AS lat, ST_Latitude(latLng) AS lng " +
                "FROM report_table "
        ;
        stmt = con.createStatement();
        results = stmt.executeQuery(sql);

        // Fills the ArrayList of Apiaries.
        while (results.next()) {
            LatLng latlng = new LatLng(results.getDouble("lat"), results.getDouble("lng"));
            reports.add(new Report(latlng,
                    results.getTimestamp("timestamp"), results.getString("username"),
                    results.getInt("reportid"), results.getInt("plant_id")));

        }

        // Returns the list of reports associated with user.
        return reports;

    }

    /**
     * Returns a list of all plants in database
     *
     * @return a list of all plants
     * @throws SQLException if a SQL query fails.
     */
    public static ArrayList<Plant> getPlants() throws SQLException {
        String sql;
        Statement stmt;
        ResultSet results;
        ArrayList<Plant> plants = new ArrayList<>();

        // Issues a SQL query to find all Apiaries associated with user.
        sql = "SELECT *" +
                "FROM plant_table "
        ;
        stmt = con.createStatement();
        results = stmt.executeQuery(sql);

        // Fills the ArrayList of Apiaries.
        while (results.next()) {
            plants.add(new Plant(results.getInt("plant_id"), results.getString("Plant_Name"),
                    results.getString("Botanical_Name"), results.getString("Species_Afflicted"),
                    results.getString("Continents"), results.getString("Toxicity_Cause"),
                    results.getString("Symptoms"), results.getString("Plant_Distribution")));

        }

        // Returns the list of reports associated with user.
        return plants;

    }

    //TODO
    //adds an report into the database, CANNOT ADD INTO APIARY IF THERE IS NO USER WITH THE SAME USERNAME
    public static void addReport(String username, LatLng mLocation, int reportid, int plantid) throws SQLException {
        Statement stmt;
        Connection con = establishConnection();

        String sql = "INSERT INTO Apiary VALUES ('" + username + "','" + username + "','" + username + "')";

        stmt = con.createStatement();
        stmt.executeUpdate(sql);

    }

    public static void createUser(String username, String email, String password) throws SQLException {
        Connection con;
        Statement stmt;

        con = establishConnection();
        String sql = "INSERT INTO user_table VALUES ('" + username + "','" + email + "','" + password +"')";
        stmt = con.createStatement();
        stmt.executeUpdate(sql);

    }


}