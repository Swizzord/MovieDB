package com.pablosantos.moviedb.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Pablo Santos on 31/01/2018.
 */

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MovieModel movie);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<MovieModel> movies);

    @Delete
    void delete(MovieModel movie);

    @Query("DELETE FROM MOVIES")
    void clear();

    @Query("SELECT * FROM MOVIES")
    List<MovieModel> getMovies();

    @Query("SELECT * FROM MOVIES WHERE favourite = 1")
    List<MovieModel> getFavouriteMovies();

    @Query("SELECT favourite FROM MOVIES WHERE id = :movieId")
    boolean isFavourite(int movieId);

    @Query("UPDATE MOVIES SET favourite = :bool WHERE id = :movieId")
    void setFavourite(int movieId, boolean bool);
}
