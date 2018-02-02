package com.pablosantos.moviedb.ui.adapters;

import android.content.Context;
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

import java.util.List;

/**
 * Created by Pablo Santos on 31/01/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<MovieModel> moviesList;
    private Context context;

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


    public MoviesAdapter(List<MovieModel> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
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
        holder.overview.setText(movie.overview);
//        holder.poster.setImageDrawable(???);

        // Image.
        String url = "https://image.tmdb.org/t/p/w500" + movie.posterPath;
        Glide.with(context)
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
