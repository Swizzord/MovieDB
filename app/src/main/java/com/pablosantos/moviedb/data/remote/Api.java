package com.pablosantos.moviedb.data.remote;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Pablo Santos on 31/01/2018.
 */

public interface Api {
    @GET ("popular?api_key=b66ffea8276ce576d60df52600822c88&language=en-US&page=1")
    Single<Response> getPopularMovies();

    @GET ("{movieId}?api_key=b66ffea8276ce576d60df52600822c88&language=en-US&page=1")
    Single<Response> getMovie(@Path("movieId")String movieId);
}
