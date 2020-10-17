package com.inovaeg.androidtrainingpharos2020;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    private final String API_KEY = "2679cb447a37eabd0bd4031296f5b843";
    private final String BASE_URL = "https://api.themoviedb.org/3/";
    ImageView posterImageView;
    TextView nameTextView;
    TextView overviewTextView;
    SimpleRatingBar ratingView;

    Movie clickedMovie;

    public MovieViewHolder(@NonNull View itemView, final Context context) {
        super(itemView);

        posterImageView = itemView.findViewById(R.id.iv_movie_poster);
        nameTextView = itemView.findViewById(R.id.tv_movie_name);
        overviewTextView = itemView.findViewById(R.id.tv_movie_genres);
        ratingView = itemView.findViewById(R.id.rating);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, clickedMovie.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        ratingView.setOnRatingBarChangeListener(new SimpleRatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(SimpleRatingBar simpleRatingBar, float rating, boolean fromUser) {
                rateMovie(clickedMovie, rating);
            }
        });
    }

    public void rateMovie(Movie movie, float rate) {
        // TODO (6.7) rating POST request
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface response = retrofit.create(RequestInterface.class);
        SharedPreferences prefs = itemView.getContext().getSharedPreferences("main", Context.MODE_PRIVATE);
        Call<RatingResponse> call =
                response.rateMovie(movie.getId(), API_KEY, new RatingRequest(rate), prefs.getString("session", ""));
        call.enqueue(new Callback<RatingResponse>() {
            @Override
            public void onResponse(@NonNull Call<RatingResponse> call, @NonNull retrofit2.Response<RatingResponse> response) {
                if (response.body() != null) {
                    Toast.makeText(itemView.getContext(), response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Toast.makeText(itemView.getContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RatingResponse> call, @NonNull Throwable t) {
                Toast.makeText(itemView.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void bind(Movie movie) {
        clickedMovie = movie;

        // TODO (6.6) use poster url to load image via glide instead of a static drawable
//        posterImageView.setImageResource(movie.getPosterUrl());
        Glide.with(itemView.getContext())
                .load("http://image.tmdb.org/t/p/w185" + movie.getPosterUrl())
                .placeholder(R.drawable.ic_play)
                .error(R.drawable.ic_launcher_background)
                .into(posterImageView);
        nameTextView.setText(movie.getName());
        overviewTextView.setText(movie.getOverview());

    }
}
