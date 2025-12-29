package com.example.recycleheroes;


import android.location.Location;

import java.sql.Blob;
import java.util.List;

public class RecyclingPointModel {
    public int id;
    public int user_id; // Change this to String
    public double latitude;
    public double longitude;
    public String type;
    public String category;
    public String images;
    public String creation_date;

    // Constructors, getters, and setters
}
