package com.inovaeg.androidtrainingpharos2020.Retrofit

import com.google.gson.annotations.SerializedName

// Class With it's Constructor, Setters & Getters
data class GuestSessionIDRequestResult(@SerializedName("guest_session_id")var guestSessionID : String) {

    override fun toString(): String {
        return "GuestSessionIDRequestResult{ guest_session_id= ( " + guestSessionID + " ) }"
    }
}