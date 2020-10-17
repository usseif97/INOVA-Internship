package com.inovaeg.androidtrainingpharos2020

import com.google.gson.annotations.SerializedName

data class Movie(@SerializedName("title") var name: String,
                 @SerializedName("poster_path") var posterUrl: String,
                 @SerializedName("overview") var overview: String,
                 @SerializedName("id") var id: Int = 0)