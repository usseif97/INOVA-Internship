package com.inovaeg.androidtrainingpharos2020;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    ImageView posterImageView;
    TextView nameTextView;
    TextView genresTextView;

    Movie clickedMovie;

    public MovieViewHolder(@NonNull View itemView, final Context context) {
        super(itemView);

        posterImageView = itemView.findViewById(R.id.iv_movie_poster);
        nameTextView = itemView.findViewById(R.id.tv_movie_name);
        genresTextView = itemView.findViewById(R.id.tv_movie_genres);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, clickedMovie.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void bind(Movie movie) {
        clickedMovie = movie;

        posterImageView.setImageResource(movie.getPosterDrawableId());
        nameTextView.setText(movie.getName());
        String generes = "";
        for (String i : movie.getGenres()) {
            generes = generes.concat("|").concat(i);
        }
        genresTextView.setText(generes);

    }
}
