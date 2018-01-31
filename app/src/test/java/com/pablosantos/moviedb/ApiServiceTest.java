package com.pablosantos.moviedb;

import com.pablosantos.moviedb.data.remote.Api;
import com.pablosantos.moviedb.data.remote.ApiService;
import com.pablosantos.moviedb.data.remote.Result;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Teo on 31/01/2018.
 */
public class ApiServiceTest {
    Api api;

    @Before
    public void setUp() throws Exception {
        api = new ApiService().getApi();
    }

    @Test
    public void getPopularMovies(){
        Result result = api.getPopularMovies().blockingGet();
        System.out.println(result);
        assertNotEquals(null, result);
    }

}