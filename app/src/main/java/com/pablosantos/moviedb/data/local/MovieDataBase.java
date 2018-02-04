package com.pablosantos.moviedb.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.pablosantos.moviedb.data.local.converters.GenreConverter;
import com.pablosantos.moviedb.data.local.converters.ProductionCompanyConverter;

import java.io.Serializable;

/**
 * Created by Pablo Santos on 31/01/2018.
 */

@Database(entities = {MovieModel.class}, version = 2)
@TypeConverters({GenreConverter.class, ProductionCompanyConverter.class})
public abstract class MovieDataBase extends RoomDatabase {
    public abstract MovieDao getMovieDao();
}
