package com.pablosantos.moviedb.data.local.converters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pablosantos.moviedb.data.remote.Genre;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Pablo Santos on 02/02/2018.
 */

public class GenreConverter {

    @TypeConverter
    public static List<Genre> fromString(String value) {
        Type listType = new TypeToken<List<Genre>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(List<Genre> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

}

