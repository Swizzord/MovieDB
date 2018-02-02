package com.pablosantos.moviedb.data.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Pablo Santos on 31/01/2018.
 */

public class MovieResponse {

    @SerializedName("vote_count")
    public Integer voteCount;
    @SerializedName("id")
    public Integer id;
    @SerializedName("video")
    public Boolean video;
    @SerializedName("vote_average")
    public Double voteAverage;
    @SerializedName("title")
    public String title;
    @SerializedName("popularity")
    public Double popularity;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("original_language")
    public String originalLanguage;
    @SerializedName("original_title")
    public String originalTitle;
    @SerializedName("genre_ids")
    public List<Integer> genreIds = null;
    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("adult")
    public Boolean adult;
    @SerializedName("overview")
    public String overview;
    @SerializedName("release_date")
    public String releaseDate;

    @Override
    public String toString() {
        return "MovieResponse{" +
                "voteCount=" + voteCount +
                ", id=" + id +
                ", video=" + video +
                ", voteAverage=" + voteAverage +
                ", title='" + title + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", genreIds=" + genreIds +
                ", backdropPath='" + backdropPath + '\'' +
                ", adult=" + adult +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}