package com.inovaeg.androidtrainingpharos2020;

import com.google.gson.annotations.SerializedName;

public class RatingResponse {
    private @SerializedName("status_code")
    String statusCode;
    private @SerializedName("status_message")
    String statusMessage;

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
