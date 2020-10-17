package com.inovaeg.androidtrainingpharos2020

import com.google.gson.annotations.SerializedName

data class GuestSession(@SerializedName("guest_session_id") var sessionId: String)