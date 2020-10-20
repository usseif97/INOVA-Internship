package com.inovaeg.androidtrainingpharos2020

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MoviesAdapter(private val movies: ArrayList<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {
    // movies is Arraylist and put on the constructor above

    // used to create viewholders till it fill the screen
    // parent is the RecyclerView here
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val cellID : Int
        if(viewType == 0)
            cellID = R.layout.cell_header
        else
            cellID = R.layout.cell_movie

        // Inflater create object from the xml file
        // can use the same ViewHolder for the two viewTypes
        // because they have the same components with the same IDs
        val view = LayoutInflater.from(parent.context).inflate(cellID, parent, false)
        return MovieViewHolder(view)
    }

    // bind the viewholders in the recyclerview
    // if there's 20 elements, it will be called 20 times
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    // return the ViewType according to the position in the recyclerView
    override fun getItemViewType(position: Int): Int {
        if(position == 0)
            return 0
        else
            return 1
    }

    public fun appendData(newData: ArrayList<Movie>){
        movies.addAll(newData)
        notifyDataSetChanged() // notify the RecyclerView about the added Data
    }

}