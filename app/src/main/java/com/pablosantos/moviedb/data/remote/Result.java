package com.pablosantos.moviedb.data.remote;

import java.util.List;

/**
 * Created by Pablo Santos on 31/01/2018.
 */

public class Result {
    public List<MovieResponse> results;

    @Override
    public String toString() {
        return "Result{" +
                "results=" + results +
                '}';
    }
}
