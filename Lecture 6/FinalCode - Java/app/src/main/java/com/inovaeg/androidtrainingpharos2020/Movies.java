package com.inovaeg.androidtrainingpharos2020;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

// TODO (6.4) add a POJO for the request
public class Movies {
    private @SerializedName("results")
    ArrayList<Movie> movies;

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
