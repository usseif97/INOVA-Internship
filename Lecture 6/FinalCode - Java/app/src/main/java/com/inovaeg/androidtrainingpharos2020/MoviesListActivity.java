package com.inovaeg.androidtrainingpharos2020;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesListActivity extends AppCompatActivity {

    private Boolean loadMore = true;
    private final String API_KEY = "2679cb447a37eabd0bd4031296f5b843";
    private final String BASE_URL = "https://api.themoviedb.org/3/";
    private int pageNumber = 1;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        // TODO (6.1.1) save guest session id in shared
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface response = retrofit.create(RequestInterface.class);
        Call<GuestSession> call = response.createGuestSession(API_KEY);
        call.enqueue(new Callback<GuestSession>() {
            @Override
            public void onResponse(@NonNull Call<GuestSession> call, @NonNull retrofit2.Response<GuestSession> response) {
                SharedPreferences prefs = getSharedPreferences("main", Context.MODE_PRIVATE);
                if (response.body() != null) {
                    prefs.edit()
                            .putString("session", response.body().getSessionId())
                            .apply();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GuestSession> call, @NonNull Throwable t) {
                Toast.makeText(MoviesListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // TODO (6.2) remove static data and replace with remote
//        ArrayList<Movie> firstPageMovies = new ArrayList<>();
//        firstPageMovies.add(new Movie("Shaun of the dead", R.drawable.poster_shaun_of_the_dead, new ArrayList<>(Arrays.asList("action", "zombie", "comedy"))));
//        firstPageMovies.add(new Movie("Fight club", R.drawable.poster_fight_club, new ArrayList<>(Arrays.asList("Drama", "Thriller"))));
//        firstPageMovies.add(new Movie("Mr. Nobody", R.drawable.poster_mr_nobody, new ArrayList<>(Arrays.asList("Sci-fi", "Drama"))));
//        firstPageMovies.add(new Movie("End Game", R.drawable.poster_end_game, new ArrayList<>(Arrays.asList("action", "Sci-fi"))));
//        firstPageMovies.add(new Movie("Sherlock Holmes", R.drawable.poster_sherlock_holmes, new ArrayList<>(Arrays.asList("action", "zombie", "comedy"))));
//        firstPageMovies.add(new Movie("Knives Out", R.drawable.poster_knives_out, new ArrayList<>(Arrays.asList("action", "zombie", "comedy"))));
//
//        final ArrayList<Movie> secondPageMovies = new ArrayList<>();
//        secondPageMovies.add(new Movie("The Dark Knight", R.drawable.poster_the_dark_knight, new ArrayList<>(Arrays.asList("action", "zombie", "comedy"))));
//        secondPageMovies.add(new Movie("Inception", R.drawable.poster_inception, new ArrayList<>(Arrays.asList("action", "zombie", "comedy"))));
//        secondPageMovies.add(new Movie("The Matrix ", R.drawable.poster_the_matrix, new ArrayList<>(Arrays.asList("action", "zombie", "comedy"))));
//        secondPageMovies.add(new Movie("Spirited Away", R.drawable.poster_spirited_away, new ArrayList<>(Arrays.asList("action", "zombie", "comedy"))));
//        secondPageMovies.add(new Movie("Interstellar", R.drawable.poster_interstellar, new ArrayList<>(Arrays.asList("action", "zombie", "comedy"))));
//        secondPageMovies.add(new Movie("The Lion King", R.drawable.poster_the_lion_king, new ArrayList<>(Arrays.asList("action", "zombie", "comedy"))));


        moviesAdapter = new MoviesAdapter(new ArrayList<Movie>());

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        RecyclerView recyclerView = findViewById(R.id.rv_movies);
        recyclerView.setAdapter(moviesAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadMoreData();

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
                            loadMore = false;
                            loadMoreData();
//                            moviesAdapter.appendDate(secondPageMovies);
                        }
                    }
            }
        });
    }

    void loadMoreData() {
        // TODO (6.5) make the call and control pagination there
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface response = retrofit.create(RequestInterface.class);
        Call<Movies> call = response.getTopRatedMovies(API_KEY, pageNumber);
        findViewById(R.id.progress).setVisibility(View.VISIBLE);
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(@NonNull Call<Movies> call, @NonNull retrofit2.Response<Movies> response) {
                ArrayList<Movie> list = response.body().getMovies();
                moviesAdapter.appendData(list);
                if (list.size() < 20)
                    loadMore = false;
                else {
                    loadMore = true;
                    pageNumber++;
                }
                findViewById(R.id.progress).setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<Movies> call, @NonNull Throwable t) {
                Toast.makeText(MoviesListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                findViewById(R.id.progress).setVisibility(View.GONE);
            }
        });
    }
}