package com.inovaeg.androidtrainingpharos2020;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class MoviesListActivity extends AppCompatActivity {

    private Boolean loadMore = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);

        //data
        ArrayList<Movie> firstPageMovies = new ArrayList<>();
        firstPageMovies.add(new Movie("Shaun of the dead", R.drawable.poster_shaun_of_the_dead, new ArrayList<>(Arrays.asList("action", "zombie", "comedy"))));
        firstPageMovies.add(new Movie("Fight club", R.drawable.poster_fight_club, new ArrayList<>(Arrays.asList("Drama", "Thriller"))));
        firstPageMovies.add(new Movie("Mr. Nobody", R.drawable.poster_mr_nobody, new ArrayList<>(Arrays.asList("Sci-fi", "Drama"))));
        firstPageMovies.add(new Movie("End Game", R.drawable.poster_end_game, new ArrayList<>(Arrays.asList("action", "Sci-fi"))));
        firstPageMovies.add(new Movie("Sherlock Holmes", R.drawable.poster_sherlock_holmes, new ArrayList<>(Arrays.asList("action", "zombie", "comedy"))));
        firstPageMovies.add(new Movie("Knives Out", R.drawable.poster_knives_out, new ArrayList<>(Arrays.asList("action", "zombie", "comedy"))));

        final ArrayList<Movie> secondPageMovies = new ArrayList<>();
        secondPageMovies.add(new Movie("The Dark Knight", R.drawable.poster_the_dark_knight, new ArrayList<>(Arrays.asList("action", "zombie", "comedy"))));
        secondPageMovies.add(new Movie("Inception", R.drawable.poster_inception, new ArrayList<>(Arrays.asList("action", "zombie", "comedy"))));
        secondPageMovies.add(new Movie("The Matrix ", R.drawable.poster_the_matrix, new ArrayList<>(Arrays.asList("action", "zombie", "comedy"))));
        secondPageMovies.add(new Movie("Spirited Away", R.drawable.poster_spirited_away, new ArrayList<>(Arrays.asList("action", "zombie", "comedy"))));
        secondPageMovies.add(new Movie("Interstellar", R.drawable.poster_interstellar, new ArrayList<>(Arrays.asList("action", "zombie", "comedy"))));
        secondPageMovies.add(new Movie("The Lion King", R.drawable.poster_the_lion_king, new ArrayList<>(Arrays.asList("action", "zombie", "comedy"))));


        final MoviesAdapter moviesAdapter = new MoviesAdapter(firstPageMovies);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        RecyclerView recyclerView = findViewById(R.id.rv_movies);
        recyclerView.setAdapter(moviesAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (loadMore)
                    if (dy > 0) {
                        int visibleItemCount = linearLayoutManager.getChildCount();
                        int totalItemCount = linearLayoutManager.getItemCount();
                        int pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            moviesAdapter.appendDate(secondPageMovies);
                            loadMore = false;
                        }
                    }
            }
        });

    }
}