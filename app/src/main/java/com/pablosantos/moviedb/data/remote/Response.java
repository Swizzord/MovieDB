package com.pablosantos.moviedb.data.remote;

import java.util.List;

/**
 * Created by Pablo Santos on 31/01/2018.
 */

public class Response {
    private List<MovieResponse> movies;

    public List<MovieResponse> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieResponse> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Response{" +
                "movies=" + movies +
                '}';
    }
}
