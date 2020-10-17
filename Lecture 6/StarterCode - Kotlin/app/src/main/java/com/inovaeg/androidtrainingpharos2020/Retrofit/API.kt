package com.inovaeg.androidtrainingpharos2020.Retrofit

import retrofit2.Call
import retrofit2.http.*

interface API {


    @GET("authentication/guest_session/new")
    fun getGuestSessionID(@Query("api_key") apikey: String) : Call<GuestSessionIDRequestResult>

    @GET("movie/popular")
    fun getMovies(@Query("api_key") apikey: String,
                  @Query("page") pageNumber: Int) : Call<MoviesRequestResult>

    @POST("movie/{movie_id}/rating")
    fun rateMovie(@Path("movie_id") movieID: Int,
                  @Query("api_key") apikey: String,
                  @Query("guest_session_id") guestSessionID: String?,
                  @Body request: RatingMovieRequestBody?) : Call<RatingMovieRequestResult>

    @GET("search/movie")
    fun searchForMovie(@Query("api_key") apikey: String,
                       @Query("page") pageNumber: Int,
                       @Query("query") queryStr: String) : Call<MoviesRequestResult>

}