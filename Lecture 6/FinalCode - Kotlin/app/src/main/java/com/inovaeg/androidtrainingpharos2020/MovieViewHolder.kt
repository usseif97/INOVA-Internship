package com.inovaeg.androidtrainingpharos2020

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.iarcuschin.simpleratingbar.SimpleRatingBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MovieViewHolder(itemView: View, context: Context?) : ViewHolder(itemView) {
    private val API_KEY = "2679cb447a37eabd0bd4031296f5b843"
    private val BASE_URL = "https://api.themoviedb.org/3/"
    var posterImageView: ImageView
    var nameTextView: TextView
    var overviewTextView: TextView
    var ratingView: SimpleRatingBar
    var clickedMovie: Movie? = null

    fun rateMovie(movie: Movie?, rate: Float) {
        // TODO (6.7) rating POST request
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val response = retrofit.create(RequestInterface::class.java)
        val prefs = itemView.context.getSharedPreferences("main", Context.MODE_PRIVATE)
        val call = response.rateMovie(movie!!.id, API_KEY, RatingRequest(rate), prefs.getString("session", ""))
        call!!.enqueue(object : Callback<RatingResponse?> {
            override fun onResponse(call: Call<RatingResponse?>, response: Response<RatingResponse?>) {
                if (response.body() != null) {
                    Toast.makeText(itemView.context, response.body()?.statusMessage, Toast.LENGTH_SHORT).show()
                } else {
                    try {
                        Toast.makeText(itemView.context, response.errorBody()!!.string(), Toast.LENGTH_SHORT).show()
                    } catch (e: IOException) {
                    }
                }
            }

            override fun onFailure(call: Call<RatingResponse?>, t: Throwable) {
                Toast.makeText(itemView.context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun bind(movie: Movie?) {
        clickedMovie = movie

        // TODO (6.6) use poster url to load image via glide instead of a static drawable
//        posterImageView.setImageResource(movie.getPosterUrl());
        Glide.with(itemView.context)
                .load("http://image.tmdb.org/t/p/w185" + movie?.posterUrl)
                .placeholder(R.drawable.ic_play)
                .error(R.drawable.ic_launcher_background)
                .into(posterImageView)
        nameTextView.text = movie?.name
        overviewTextView.text = movie!!.overview
    }

    init {
        posterImageView = itemView.findViewById(R.id.iv_movie_poster)
        nameTextView = itemView.findViewById(R.id.tv_movie_name)
        overviewTextView = itemView.findViewById(R.id.tv_movie_genres)
        ratingView = itemView.findViewById(R.id.rating)
        itemView.setOnClickListener { Toast.makeText(context, clickedMovie?.name, Toast.LENGTH_SHORT).show() }
        ratingView.setOnRatingBarChangeListener { _, rating, _ -> rateMovie(clickedMovie, rating) }
    }
}