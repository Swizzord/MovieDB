package com.pablosantos.moviedb.data;

import com.pablosantos.moviedb.data.local.MovieModel;
import com.pablosantos.moviedb.data.remote.MovieResponse;

/**
 * Created by Pablo Santos on 31/01/2018.
 */

public class MovieMapper {
    public static MovieModel responseToModel(MovieResponse input){
        MovieModel output = new MovieModel();
        output.setId(input.getId());
        output.setVoteCount(input.getVoteCount());
        output.setVideo(input.getVideo());
        output.setVoteAverage(input.getVoteAverage());
        output.setTitle(input.getTitle());
        output.setPopularity(input.getPopularity());
        output.setPosterPath(input.getPosterPath());
        output.setOriginalLanguage(input.getOriginalLanguage());
        output.setOriginalTitle(input.getOriginalTitle());
//        output.genreIds = input.genreIds;
        output.setBackdropPath(input.getBackdropPath());
        output.setAdult(input.getAdult());
        output.setOverview(input.getOverview());
        output.setReleaseDate(input.getReleaseDate());
        return  output;
    }
}
