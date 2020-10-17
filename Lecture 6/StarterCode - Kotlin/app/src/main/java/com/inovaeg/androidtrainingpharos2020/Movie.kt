package com.inovaeg.androidtrainingpharos2020

import com.google.gson.annotations.SerializedName
import java.util.*

// Class With it's Constructor, Setters & Getters
data class Movie(@SerializedName("original_title") var name: String,
                 @SerializedName("poster_path") var posterUrl: String,
                 @SerializedName("overview") var overview: String,
                 @SerializedName("id") var id: Int = 0) {

    override fun toString(): String {
        return "Movie{" + "name='" + name  + '\'' +
                ", image='" + posterUrl  + '\'' +
                ", overview='" + overview  + '\'' +
                ", id='" + id  + '\'' + "}"
    }

}