package com.pablosantos.moviedb.data.remote;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by Teo on 31/01/2018.
 */

public interface Api {
    @GET ("popular?api_key=b66ffea8276ce576d60df52600822c88&language=en-US&page=1")
    Single<Result> getPopularMovies();
}
