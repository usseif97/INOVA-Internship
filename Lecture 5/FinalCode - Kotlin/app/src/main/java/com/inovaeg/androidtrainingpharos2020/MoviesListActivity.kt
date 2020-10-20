package com.inovaeg.androidtrainingpharos2020

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_movies_list.*
import java.util.*

class MoviesListActivity : AppCompatActivity() {
    private var loadMore = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        //data
        val firstPageMovies = ArrayList<Movie>()
        firstPageMovies.add(Movie("Shaun of the dead", R.drawable.poster_shaun_of_the_dead, arrayListOf("action", "zombie", "comedy")))
        firstPageMovies.add(Movie("Fight club", R.drawable.poster_fight_club, arrayListOf("Drama", "Thriller")))
        firstPageMovies.add(Movie("Mr. Nobody", R.drawable.poster_mr_nobody, arrayListOf("Sci-fi", "Drama")))
        firstPageMovies.add(Movie("End Game", R.drawable.poster_end_game, arrayListOf("action", "Sci-fi")))
        firstPageMovies.add(Movie("Sherlock Holmes", R.drawable.poster_sherlock_holmes, arrayListOf("action", "zombie", "comedy")))
        firstPageMovies.add(Movie("Knives Out", R.drawable.poster_knives_out, arrayListOf("action", "zombie", "comedy")))

        val secondPageMovies = ArrayList<Movie>()
        secondPageMovies.add(Movie("The Dark Knight", R.drawable.poster_the_dark_knight, arrayListOf("action", "zombie", "comedy")))
        secondPageMovies.add(Movie("Inception", R.drawable.poster_inception, arrayListOf("action", "zombie", "comedy")))
        secondPageMovies.add(Movie("The Matrix ", R.drawable.poster_the_matrix, arrayListOf("action", "zombie", "comedy")))
        secondPageMovies.add(Movie("Spirited Away", R.drawable.poster_spirited_away, arrayListOf("action", "zombie", "comedy")))
        secondPageMovies.add(Movie("Interstellar", R.drawable.poster_interstellar, arrayListOf("action", "zombie", "comedy")))
        secondPageMovies.add(Movie("The Lion King", R.drawable.poster_the_lion_king, arrayListOf("action", "zombie", "comedy")))

        val moviesAdapter = MoviesAdapter(firstPageMovies)
        val linearLayoutManager = LinearLayoutManager(this)

        rv_movies.apply {
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
                                moviesAdapter.appendDate(secondPageMovies)
                                loadMore = false
                            }
                        }
                }
            })
        }
    }
}
