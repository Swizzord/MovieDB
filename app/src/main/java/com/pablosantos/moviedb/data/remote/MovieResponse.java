package com.pablosantos.moviedb.data.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Pablo Santos on 31/01/2018.
 */

public class MovieResponse {

    @SerializedName("vote_count")
    public Integer voteCount;
    public Integer id;
    @SerializedName("vote_average")
    public Double voteAverage;
    public String title;
    public Double popularity;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("original_language")
    public String originalLanguage;
    @SerializedName("original_title")
    public String originalTitle;
    public List<Genre> genres;
    @SerializedName("production_companies")
    public List<ProductionCompany> productionCompanies;
    public String overview;
    @SerializedName("release_date")
    public String releaseDate;
    public String status;
    public int bugdet;
    public String homepage;

    @Override
    public String toString() {
        return "MovieResponse{" +
                "voteCount=" + voteCount +
                ", id=" + id +
                ", voteAverage=" + voteAverage +
                ", title='" + title + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", genres=" + genres +
                ", productionCompanies=" + productionCompanies +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", status='" + status + '\'' +
                ", bugdet=" + bugdet +
                ", homepage='" + homepage + '\'' +
                '}';
    }
}