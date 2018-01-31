package com.pablosantos.moviedb.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pablosantos.moviedb.R;
import com.pablosantos.moviedb.data.local.MovieModel;

import java.util.List;

/**
 * Created by Pablo Santos on 31/01/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<MovieModel> movieList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, date, overview, rating;

        private MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.lblTitle);
            date = view.findViewById(R.id.lblDate);
            overview = view.findViewById(R.id.lblOverview);
            rating = view.findViewById(R.id.lblRating);
        }
    }

    public MoviesAdapter(List<MovieModel> movieList) {
        this.movieList = movieList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MovieModel movie = movieList.get(position);
        holder.title.setText(movie.getOriginalTitle());
        holder.date.setText(movie.getReleaseDate());
        holder.overview.setText(movie.getOverview());
        holder.rating.setText(movie.getVoteAverage().toString() + "⋆");
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
