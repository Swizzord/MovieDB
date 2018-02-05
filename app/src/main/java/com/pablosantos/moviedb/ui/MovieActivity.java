package com.pablosantos.moviedb.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pablosantos.moviedb.R;
import com.pablosantos.moviedb.data.local.MovieDao;
import com.pablosantos.moviedb.data.local.MovieDataBaseConnection;

public class MovieActivity extends AppCompatActivity {

    // Data.
    private final String basePosterUrl = "https://image.tmdb.org/t/p/w500";
    private int movieID;
    private MovieDao movieDao;

    // UI elements.
    private ImageView ivPoster;
    private TextView tvTitle;
    private TextView tvOverview;
    private TextView tvGenres;
    private TextView tvProductionCompanies;
    private TextView tvReleaseDate;
    private TextView tvRating;
    private FloatingActionButton fabFavourite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        getParent();

        setUpUI();
        movieDao = MovieDataBaseConnection.getInstance(this).getDb().getMovieDao();
        fillData();
    }

    private void setUpUI() {
        ivPoster = findViewById(R.id.ivPosterDetail);
        tvTitle = findViewById(R.id.tvTitleMovieActivity);
        tvOverview = findViewById(R.id.tvOverviewMovieActivity);
        tvGenres = findViewById(R.id.tvGenresMovieActivity);
        tvProductionCompanies = findViewById(R.id.tvProductionCompaniesMovieActivity);
        tvReleaseDate = findViewById(R.id.tvReleaseDateMovieActivity);
        tvRating = findViewById(R.id.tvRating);
        fabFavourite = findViewById(R.id.fabFavourite);

        fabFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabPressed();
            }
        });
    }

    private void fillData() {
        Intent intent = getIntent();
        Glide.with(ivPoster.getContext())
                .load(basePosterUrl + intent.getStringExtra("poster"))
                .into(ivPoster);
        tvTitle.setText(intent.getStringExtra("title"));
        tvOverview.setText(intent.getStringExtra("overview"));
        tvGenres.setText(intent.getStringExtra("genres"));
        tvProductionCompanies.setText(intent.getStringExtra("companies"));
        tvReleaseDate.setText(intent.getStringExtra("releaseDate"));
        tvRating.setText(intent.getStringExtra("rating"));
        movieID = intent.getIntExtra("movieId", 0);

        new checkFavourite().execute(movieID);
    }

    private void fabPressed() {
        boolean fav = !(boolean) fabFavourite.getTag();
        new setFavourite().execute(movieID, fav);
        fabFavourite.setTag(fav);
    }

    private void updateFabFavourite() {
        if ((boolean) fabFavourite.getTag())
            fabFavourite.setImageResource(android.R.drawable.btn_star_big_on);
        else fabFavourite.setImageResource(android.R.drawable.btn_star_big_off);
    }

    private class checkFavourite extends AsyncTask<Integer, Boolean, Boolean> {
        @Override
        protected Boolean doInBackground(Integer... movieId) {
            return movieDao.isFavourite(movieId[0]);
        }

        protected void onPostExecute(Boolean result) {
            if (result) fabFavourite.setTag(true);
            else fabFavourite.setTag(false);
            updateFabFavourite();
        }
    }

    private class setFavourite extends AsyncTask<Object, Void, Void> {
        @Override
        protected Void doInBackground(Object... params) {
            movieDao.setFavourite((int) params[0], (boolean) params[1]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            updateFabFavourite();
        }
    }
}
