package com.pablosantos.moviedb.data;

import com.pablosantos.moviedb.data.local.MovieModel;
import com.pablosantos.moviedb.data.remote.MovieResponse;

/**
 * Created by Pablo Santos on 31/01/2018.
 */

public class MovieMapper {
    public static MovieModel apiToModel(MovieResponse input){
        MovieModel output = new MovieModel();
        output.id = input.id;
        output.voteCount = input.voteCount;
        output.video = input.video;
        output.voteAverage = input.voteAverage;
        output.title = input.title;
        output.popularity = input.popularity;
        output.posterPath = input.posterPath;
        output.originalLanguage = input.originalLanguage;
        output.originalTitle = input.originalTitle;
//        output.genreIds = input.genreIds;
        output.backdropPath = input.backdropPath;
        output.adult = input.adult;
        output.overview = input.overview;
        output.releaseDate = input.releaseDate;
        return  output;
    }
}
