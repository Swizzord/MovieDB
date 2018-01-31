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
 * Created by Teo on 31/01/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<MovieModel> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date, overview, rating;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.lblTitle);
            date = (TextView) view.findViewById(R.id.lblDate);
            overview = (TextView) view.findViewById(R.id.lblOverview);
            rating = (TextView) view.findViewById(R.id.lblRating);
        }
    }


    public MoviesAdapter(List<MovieModel> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MovieModel movie = moviesList.get(position);
        holder.title.setText(movie.originalTitle);
        holder.date.setText(movie.releaseDate);
        holder.overview.setText(movie.overview);
        holder.rating.setText(movie.voteAverage.toString()+"⋆");
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
