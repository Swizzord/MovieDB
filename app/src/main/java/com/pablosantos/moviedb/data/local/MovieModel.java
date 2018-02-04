package com.pablosantos.moviedb.data.local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.pablosantos.moviedb.data.remote.Genre;
import com.pablosantos.moviedb.data.remote.ProductionCompany;

import java.util.List;

/**
 * Created by Pablo Santos on 31/01/2018.
 */

@Entity(tableName = "MOVIES")
public class MovieModel {

    @PrimaryKey
    @NonNull
    public Integer id;
    public Double voteAverage;
    public String title;
    public Double popularity;
    public String posterPath;
    public String originalTitle;
    public String overview;
    public String releaseDate;
    public String status;
    public int bugdet;
    public String homepage;
    public Boolean favourite;
    public List<Genre> genres;
    public List<ProductionCompany> productionCompanies;

    @Override
    public String toString() {
        return "MovieModel{" +
                "id=" + id +
                ", voteAverage=" + voteAverage +
                ", title='" + title + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", status='" + status + '\'' +
                ", bugdet=" + bugdet +
                ", homepage='" + homepage + '\'' +
                ", favourite=" + favourite +
                ", genres=" + genres +
                ", productionCompanies=" + productionCompanies +
                '}';
    }
}