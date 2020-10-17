package com.inovaeg.androidtrainingpharos2020.Retrofit

import com.google.gson.annotations.SerializedName

// Class With it's Constructor, Setters & Getters
data class RatingMovieRequestResult(@SerializedName("status_code") val statusCode : String,
                                    @SerializedName("status_message") val statusMessage : String) {

    override fun toString(): String {
        return "RatingMovieRequestResult{" + "status_code='" + statusCode  + '\'' +
                ", status_message='" + statusMessage + "}"
    }
}