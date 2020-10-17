package com.inovaeg.androidtrainingpharos2020

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MoviesListActivity : AppCompatActivity() {
    private var loadMore = true
    private val API_KEY = "2679cb447a37eabd0bd4031296f5b843"
    private val BASE_URL = "https://api.themoviedb.org/3/"
    private var pageNumber = 1
    private var moviesAdapter: MoviesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)
        // TODO (6.1.1) save guest session id in shared
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val response = retrofit.create(RequestInterface::class.java)
        val call = response.createGuestSession(API_KEY)
        call!!.enqueue(object : Callback<GuestSession?> {
            override fun onResponse(call: Call<GuestSession?>, response: Response<GuestSession?>) {
                val prefs = getSharedPreferences("main", MODE_PRIVATE)
                if (response.body() != null) {
                    prefs.edit()
                            .putString("session", response.body()?.sessionId)
                            .apply()
                }
            }

            override fun onFailure(call: Call<GuestSession?>, t: Throwable) {
                Toast.makeText(this@MoviesListActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })

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
        moviesAdapter = MoviesAdapter(ArrayList())
        val linearLayoutManager = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_movies)
        recyclerView.adapter = moviesAdapter
        recyclerView.layoutManager = linearLayoutManager
        loadMoreData()
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (loadMore) if (dy > 0) {
                    val visibleItemCount = linearLayoutManager.childCount
                    val totalItemCount = linearLayoutManager.itemCount
                    val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()
                    if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                        loadMore = false
                        loadMoreData()
//                            moviesAdapter.appendDate(secondPageMovies);
                    }
                }
            }
        })
    }

    fun loadMoreData() {
        // TODO (6.5) make the call and control pagination there
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val response = retrofit.create(RequestInterface::class.java)
        val call = response.getTopRatedMovies(API_KEY, pageNumber)
        findViewById<View>(R.id.progress).visibility = View.VISIBLE
        call!!.enqueue(object : Callback<Movies?> {
            override fun onResponse(call: Call<Movies?>, response: Response<Movies?>) {
                val list = response.body()?.movies
                moviesAdapter!!.appendData(list)
                if (list!!.size < 20) loadMore = false else {
                    loadMore = true
                    pageNumber++
                }
                findViewById<View>(R.id.progress).visibility = View.GONE
            }

            override fun onFailure(call: Call<Movies?>, t: Throwable) {
                Toast.makeText(this@MoviesListActivity, t.message, Toast.LENGTH_SHORT).show()
                findViewById<View>(R.id.progress).visibility = View.GONE
            }
        })
    }
}