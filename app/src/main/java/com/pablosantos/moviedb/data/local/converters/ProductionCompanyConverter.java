package com.pablosantos.moviedb.data.local.converters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pablosantos.moviedb.data.remote.ProductionCompany;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Pablo Santos on 02/02/2018.
 */

public class ProductionCompanyConverter {
    @TypeConverter
    public static List<ProductionCompany> fromString(String value) {
        Type listType = new TypeToken<List<ProductionCompany>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(List<ProductionCompany> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
