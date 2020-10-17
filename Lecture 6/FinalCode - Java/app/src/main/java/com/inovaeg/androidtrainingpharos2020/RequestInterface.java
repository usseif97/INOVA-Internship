package com.inovaeg.androidtrainingpharos2020;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

// TODO (6.3) define requests interface
public interface RequestInterface {

    @GET("authentication/guest_session/new")
    Call<GuestSession> createGuestSession(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<Movies> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") int pageNumber);

    @POST("movie/{movie_id}/rating")
    Call<RatingResponse> rateMovie(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Body RatingRequest request, @Query("guest_session_id") String sessionId);
}
