package com.inovaeg.androidtrainingpharos2020

import com.google.gson.annotations.SerializedName

class RatingResponse {
    @SerializedName("status_code")
    var statusCode: String? = null

    @SerializedName("status_message")
    var statusMessage: String? = null
}