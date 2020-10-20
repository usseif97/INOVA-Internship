package com.inovaeg.androidtrainingpharos2020

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MoviesListActivity : AppCompatActivity() {
    private var loadMore = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        // Data
        val firstPageMovies = arrayListOf(Movie("Shaun of the dead", R.drawable.poster_shaun_of_the_dead, 3.0, arrayListOf("action", "zombie", "comedy")),
                Movie("Fight club", R.drawable.poster_fight_club, 3.5, arrayListOf("Drama", "Thriller")),
                Movie("Mr. Nobody", R.drawable.poster_mr_nobody, 4.0, arrayListOf("Sci-fi", "Drama")),
                Movie("End Game", R.drawable.poster_end_game, 3.0, arrayListOf("action", "Sci-fi")),
                Movie("Sherlock Holmes", R.drawable.poster_sherlock_holmes, 4.7, arrayListOf("action", "zombie", "comedy")),
                Movie("Knives Out", R.drawable.poster_knives_out, 5.0, arrayListOf("action", "zombie", "comedy"))
        )

        val secondPageMovies = arrayListOf(Movie("The Dark Knight", R.drawable.poster_the_dark_knight, 3.0, arrayListOf("action", "zombie", "comedy")),
                Movie("Inception", R.drawable.poster_inception, 8.8, arrayListOf("action", "zombie", "comedy")),
                Movie("The Matrix ", R.drawable.poster_the_matrix, 8.7, arrayListOf("action", "zombie", "comedy")),
                Movie("Spirited Away", R.drawable.poster_spirited_away, 3.0, arrayListOf("action", "zombie", "comedy")),
                Movie("Interstellar", R.drawable.poster_interstellar, 4.7, arrayListOf("action", "zombie", "comedy")),
                Movie("The Lion King", R.drawable.poster_the_lion_king, 5.0, arrayListOf("action", "zombie", "comedy"))
        )


        //TODO create an (Adapter) that responsible for the Data with the (ViewHolder)
        // the Adapter is a collection of ViewHolders
        val moviesAdapter : MoviesAdapter = MoviesAdapter(firstPageMovies)

        //TODO create a layout manger
        val linearLayoutManager = LinearLayoutManager(this)

        //TODO attach both the adapter and layout manger to the recycler view
        val recyclerView : RecyclerView = findViewById(R.id.rv_movies)
        recyclerView.apply {
            adapter = moviesAdapter
            layoutManager = linearLayoutManager

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (loadMore)
                        if (dy > 0) {
                            val visibleItemCount = linearLayoutManager.childCount
                            val totalItemCount = linearLayoutManager.itemCount
                            val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()
                            if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                                moviesAdapter.appendData(secondPageMovies)
                                loadMore = false
                            }
                        }
                }
            })

        }



        //TODO implement pagination logic
    }
}