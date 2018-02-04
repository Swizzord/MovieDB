package com.pablosantos.moviedb.data.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pablo Santos on 02/02/2018.
 */

public class Genre {

    @SerializedName("name")
    public String genreName;

    public Genre() {
    }

    public Genre(String genreName) {
        this.genreName = genreName;
    }
}
