package com.pablosantos.moviedb.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pablosantos.moviedb.R;
import com.pablosantos.moviedb.data.local.MovieModel;
import com.pablosantos.moviedb.ui.OnItemClickListener;

import java.util.List;

/**
 * Created by Pablo Santos on 31/01/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<MovieModel> moviesList;
    private OnItemClickListener listener;

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, overview;
        private ImageView poster;

        private MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tvTitle);
            overview = view.findViewById(R.id.tvOverview);
            poster = view.findViewById(R.id.ivPoster);
        }
    }

    public MoviesAdapter(List<MovieModel> moviesList, OnItemClickListener listener) {
        this.moviesList = moviesList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final MovieModel movie = moviesList.get(position);
        holder.title.setText(movie.originalTitle);
        holder.overview.setText(movie.overview);

        // Image.
        String url = "https://image.tmdb.org/t/p/w500" + movie.posterPath;
        Glide.with(holder.poster.getContext())
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.poster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(movie, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
