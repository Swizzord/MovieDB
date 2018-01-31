package com.pablosantos.moviedb;

import com.pablosantos.moviedb.data.remote.Api;
import com.pablosantos.moviedb.data.remote.ApiService;
import com.pablosantos.moviedb.data.remote.Response;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pablo Santos on 31/01/2018.
 */
public class ApiServiceTest {
    Api api;

    @Before
    public void setUp() throws Exception {
        api = new ApiService().getApi();
    }

    @Test
    public void getPopularMovies(){
        Response response = api.getPopularMovies().blockingGet();
        System.out.println(response);
        assertNotEquals(null, response);
    }

}