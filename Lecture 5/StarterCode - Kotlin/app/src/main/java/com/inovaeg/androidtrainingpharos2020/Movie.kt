package com.inovaeg.androidtrainingpharos2020

// make the class with it's Constructor and Setters & Getters
data class Movie(var name: String, var posterDrawableId: Int, var rate: Double? = null, var genres:ArrayList<String>)


/*
public class Movie {
    private String name;
    private int posterDrawableId;
    private ArrayList<String> genres;

    public Movie(String name, int posterDrawableId, ArrayList<String> genres) {
        this.name = name;
        this.posterDrawableId = posterDrawableId;
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosterDrawableId() {
        return posterDrawableId;
    }

    public void setPosterDrawableId(int posterDrawableId) {
        this.posterDrawableId = posterDrawableId;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }
 */