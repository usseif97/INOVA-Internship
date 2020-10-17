package com.inovaeg.androidtrainingpharos2020.Retrofit

import com.google.gson.annotations.SerializedName
import com.inovaeg.androidtrainingpharos2020.Movie

// Class With it's Constructor, Setters & Getters
data class MoviesRequestResult(@SerializedName("page")var pageNumber : Int,
                               @SerializedName("results") var movies: ArrayList<Movie>) {

    override fun toString(): String {
        return "MoviesRequestResult{" + "page='" + pageNumber  + '\'' +
                ", results='" + movies + "}"
    }

}