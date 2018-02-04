package com.pablosantos.moviedb.data;

import com.pablosantos.moviedb.data.local.MovieModel;
import com.pablosantos.moviedb.data.remote.MovieResponse;

/**
 * Created by Pablo Santos on 31/01/2018.
 */

public class MovieMapper {
    public static MovieModel apiToModel(MovieResponse input) {
        MovieModel output = new MovieModel();
        output.id = input.id;
        output.voteAverage = input.voteAverage;
        output.title = input.title;
        output.popularity = input.popularity;
        output.posterPath = input.posterPath;
        output.originalTitle = input.originalTitle;
        output.genres = input.genres;
        output.productionCompanies = input.productionCompanies;
        output.overview = input.overview;
        output.releaseDate = input.releaseDate;
        output.bugdet = input.bugdet;
        output.status = input.status;
        output.homepage = input.homepage;

        return output;
    }
}
