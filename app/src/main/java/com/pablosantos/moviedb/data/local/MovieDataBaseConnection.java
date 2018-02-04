package com.pablosantos.moviedb.data.local;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by Pablo Santos on 04/02/2018.
 */

public class MovieDataBaseConnection {

    private static MovieDataBaseConnection instance;
    private MovieDataBase db;

    private MovieDataBaseConnection(Context context) {
        db = Room.databaseBuilder(context,
                MovieDataBase.class, "movieDB")
                .fallbackToDestructiveMigration()
                .build();
        instance = this;
    }

    public static MovieDataBaseConnection getInstance(Context context) {
        if (instance == null) new MovieDataBaseConnection(context);
        return instance;
    }

    public MovieDataBase getDb(){
        return db;
    }
}
