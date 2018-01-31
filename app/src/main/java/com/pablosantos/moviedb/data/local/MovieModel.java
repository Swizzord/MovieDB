package com.pablosantos.moviedb.data.local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Pablo Santos on 31/01/2018.
 */

@Entity (tableName = "MOVIES")
public class MovieModel {
    @PrimaryKey @NonNull
    public Integer id;
    public Integer voteCount;
    public Boolean video;
    public Double voteAverage;
    public String title;
    public Double popularity;
    public String posterPath;
    public String originalLanguage;
    public String originalTitle;
//    public List<Integer> genreIds = null;
    public String backdropPath;
    public Boolean adult;
    public String overview;
    public String releaseDate;
    public Boolean favourite;

    @Override
    public String toString() {
        return "MovieModel{" +
                "id=" + id +
                ", voteCount=" + voteCount +
                ", video=" + video +
                ", voteAverage=" + voteAverage +
                ", title='" + title + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
//                ", genreIds=" + genreIds +
                ", backdropPath='" + backdropPath + '\'' +
                ", adult=" + adult +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", favourite=" + favourite +
                '}';
    }
}