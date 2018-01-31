package com.pablosantos.moviedb.data.remote;

import java.util.List;

/**
 * Created by Teo on 31/01/2018.
 */

public class Result {
    public List<Movie> results;

    @Override
    public String toString() {
        return "Result{" +
                "results=" + results +
                '}';
    }
}
