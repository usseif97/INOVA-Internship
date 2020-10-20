package com.inovaeg.androidtrainingpharos2020

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var posterImageView : ImageView = itemView.findViewById(R.id.iv_movie_poster)
    var nameTextView : TextView = itemView.findViewById(R.id.tv_movie_name)
    var genresTextView : TextView = itemView.findViewById(R.id.tv_movie_genres)
    var rateTextView : TextView = itemView.findViewById(R.id.tv_movie_rate)

    fun bind(movie : Movie) {
        posterImageView.setImageResource(movie.posterDrawableId)
        nameTextView.text = movie.name
        genresTextView.text = movie.genres.joinToString(" | ")
        rateTextView.text = movie.rate.toString()
    }

}