package com.inovaeg.androidtrainingpharos2020

import retrofit2.Call
import retrofit2.http.*

// TODO (6.3) define requests interface
interface RequestInterface {
    @GET("authentication/guest_session/new")
    fun createGuestSession(@Query("api_key") apiKey: String?): Call<GuestSession?>?

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String?, @Query("page") pageNumber: Int): Call<Movies?>?

    @POST("movie/{movie_id}/rating")
    fun rateMovie(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String?, @Body request: RatingRequest?, @Query("guest_session_id") sessionId: String?): Call<RatingResponse?>?
}