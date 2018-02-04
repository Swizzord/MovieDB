package com.pablosantos.moviedb.data.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pablo Santos on 02/02/2018.
 */

public class ProductionCompany {

    @SerializedName("name")
    public String companyName;

    public ProductionCompany() {
    }

    public ProductionCompany(String companyName) {
        this.companyName = companyName;

    }
}
