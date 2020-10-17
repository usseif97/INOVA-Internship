package com.inovaeg.androidtrainingpharos2020;

import com.google.gson.annotations.SerializedName;

public class RatingRequest {
    private @SerializedName("value")
    float rate;

    RatingRequest(float rate) {
        this.rate = rate;
    }
}
