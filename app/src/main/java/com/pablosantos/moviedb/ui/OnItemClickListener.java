package com.pablosantos.moviedb.ui;

import com.pablosantos.moviedb.data.local.MovieModel;

/**
 * Created by Pablo Santos on 02/02/2018.
 */

public interface OnItemClickListener {
    void onItemClick(MovieModel item, int position);
}