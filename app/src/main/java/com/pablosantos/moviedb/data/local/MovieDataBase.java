package com.pablosantos.moviedb.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Pablo Santos on 31/01/2018.
 */

@Database(entities = {MovieModel.class}, version = 1)
public abstract class MovieDataBase extends RoomDatabase{
    public abstract MovieDao getMovieDao();
}
