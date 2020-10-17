package com.inovaeg.androidtrainingpharos2020;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private ArrayList<Movie> movies;
    private final int TYPE_HEADER = 0;
    private final int TYPE_MOVIE = 1;

    public MoviesAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int cellId;
        if (viewType == TYPE_HEADER) {
            cellId = R.layout.cell_header;
        } else {
            cellId = R.layout.cell_movie;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(cellId, parent, false);
        return new MovieViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void appendData(ArrayList<Movie> newData) {
        movies.addAll(newData);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
//        if (position == 0)
//            return TYPE_HEADER;
//        else
        return TYPE_MOVIE;
    }
}
