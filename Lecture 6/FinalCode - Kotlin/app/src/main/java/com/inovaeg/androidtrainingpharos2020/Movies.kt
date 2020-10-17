package com.inovaeg.androidtrainingpharos2020

import com.google.gson.annotations.SerializedName
import java.util.*

// TODO (6.4) add a POJO for the request
data class Movies(@SerializedName("results") var movies: ArrayList<Movie>? = null)