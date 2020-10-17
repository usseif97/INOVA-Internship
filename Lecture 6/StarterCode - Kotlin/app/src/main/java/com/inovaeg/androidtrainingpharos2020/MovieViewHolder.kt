package com.inovaeg.androidtrainingpharos2020

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.iarcuschin.simpleratingbar.SimpleRatingBar
import com.inovaeg.androidtrainingpharos2020.Retrofit.RatingMovieRequestBody
import com.inovaeg.androidtrainingpharos2020.Retrofit.RatingMovieRequestResult
import com.inovaeg.androidtrainingpharos2020.Retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewHolder(itemView: View, context: Context?) : ViewHolder(itemView) {
    var posterImageView: ImageView = itemView.findViewById(R.id.iv_movie_poster)
    var nameTextView: TextView = itemView.findViewById(R.id.tv_movie_name)
    var overviewTextView: TextView = itemView.findViewById(R.id.tv_movie_overview)
    var ratingView: SimpleRatingBar = itemView.findViewById(R.id.rating)

    var clickedMovie: Movie? = null
    private val API_KEY = "2679cb447a37eabd0bd4031296f5b843"

    fun bind(movie: Movie) {
        clickedMovie = movie

        nameTextView.text = movie.name
        overviewTextView.text = movie.overview
        Glide.with(itemView)
                .load("http://image.tmdb.org/t/p/w185" + movie.posterUrl)
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .into(posterImageView)
    }

    init {
        itemView.setOnClickListener { Toast.makeText(context, clickedMovie!!.name, Toast.LENGTH_SHORT).show() }
        ratingView.setOnRatingBarChangeListener { simpleRatingBar, rating, fromUser -> rateMovie(clickedMovie!!, rating) }
    }


    private fun rateMovie(movie : Movie, rate : Float){

        val guestSessionID = itemView.context.getSharedPreferences("MoviesListActivity", Context.MODE_PRIVATE).getString("GuestID", "")

        RetrofitBuilder.getInstance().api
                .rateMovie(movie.id, API_KEY, guestSessionID, RatingMovieRequestBody(rate))
                .enqueue(object : Callback<RatingMovieRequestResult> {
                    override fun onResponse(call: Call<RatingMovieRequestResult>, response: Response<RatingMovieRequestResult>) {
                        if(response.isSuccessful){
                            if(response.body() != null){
                                showToast(response.body()!!.statusMessage + "Your Rating is " + rate.toString())
                            }
                        } else {
                            showToast(response.errorBody().toString())
                        }
                    }

                    override fun onFailure(call: Call<RatingMovieRequestResult>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
    }



    private fun showToast(message: String) {
        Toast.makeText(itemView.context, message, Toast.LENGTH_LONG).show()
    }

}