package com.inovaeg.androidtrainingpharos2020

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MovieViewHolder(itemView: View, context: Context?) : ViewHolder(itemView) {
    var posterImageView: ImageView = itemView.findViewById(R.id.iv_movie_poster)
    var nameTextView: TextView = itemView.findViewById(R.id.tv_movie_name)
    var genresTextView: TextView = itemView.findViewById(R.id.tv_movie_genres)
    var clickedMovie: Movie? = null

    fun bind(movie: Movie) {
        clickedMovie = movie
        posterImageView.setImageResource(movie.posterDrawableId)
        nameTextView.text = movie.name

        genresTextView.text = movie.genres.joinToString(" | ")
    }

    init {
        itemView.setOnClickListener { Toast.makeText(context, clickedMovie!!.name, Toast.LENGTH_SHORT).show() }
    }
}