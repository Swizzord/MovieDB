package com.pablosantos.moviedb.data.remote;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pablo Santos on 31/01/2018.
 */

public class ApiService {

    private final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    private Api api;

    public ApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }

    public Api getApi() {
        return api;
    }
}
