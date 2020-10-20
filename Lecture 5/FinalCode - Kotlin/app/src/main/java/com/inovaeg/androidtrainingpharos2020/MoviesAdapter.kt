package com.inovaeg.androidtrainingpharos2020

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MoviesAdapter(private val movies: ArrayList<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {
    private val TYPE_HEADER = 0
    private val TYPE_MOVIE = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val cellId: Int
        cellId = if (viewType == TYPE_HEADER) {
            R.layout.cell_header
        } else {
            R.layout.cell_movie
        }
        val view = LayoutInflater.from(parent.context).inflate(cellId, parent, false)
        return MovieViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun appendDate(newData: ArrayList<Movie>) {
        movies.addAll(newData)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER else TYPE_MOVIE
    }

}