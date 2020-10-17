package com.inovaeg.androidtrainingpharos2020;

import com.google.gson.annotations.SerializedName;

public class Movie {
    private @SerializedName("id")
    int id;
    private @SerializedName("title")
    String name;
    private @SerializedName("poster_path")
    String posterUrl;
    private @SerializedName("overview")
    String overview;

    public Movie(String name, String posterUrl, String overview) {
        this.name = name;
        this.posterUrl = posterUrl;
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getOverview() {
        return overview;
    }

    public void getOverview(String overview) {
        this.overview = overview;
    }
}