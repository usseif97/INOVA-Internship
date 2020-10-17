package com.inovaeg.androidtrainingpharos2020;

import com.google.gson.annotations.SerializedName;

public class GuestSession {
    private @SerializedName("guest_session_id")
    String sessionId;

    GuestSession(String id) {
        this.sessionId = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
