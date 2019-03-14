package com.example.beerapplication;

import retrofit2.http.GET;

public class Beer {
    private int id;
    private String name;
    private String tagline;
    private String description;
    private float ph;
    private float volume;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTagline() {
        return tagline;
    }

    public String getDescription() {
        return description;
    }

    public float getPh() {
        return ph;
    }

    public float getVolume() {
        return volume;
    }
}
