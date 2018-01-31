package com.pablosantos.moviedb.ui;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.pablosantos.moviedb.R;
import com.pablosantos.moviedb.data.local.MovieDao;
import com.pablosantos.moviedb.data.local.MovieDataBase;
import com.pablosantos.moviedb.data.MovieMapper;
import com.pablosantos.moviedb.data.local.MovieModel;
import com.pablosantos.moviedb.data.remote.Api;
import com.pablosantos.moviedb.data.remote.ApiService;
import com.pablosantos.moviedb.data.remote.Movie;
import com.pablosantos.moviedb.data.remote.Result;
import com.pablosantos.moviedb.ui.adapters.MoviesAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private MovieDao movieDao;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up UI.
        setUpUI();

        Api a = new ApiService().getApi();
        a.getPopularMovies()
                .subscribeOn(Schedulers.io()) // Esta línea hace que sea asíncrono.
                .map(new Function<Result, List<Movie>>() {
                    @Override
                    public List<Movie> apply(@NonNull Result result) throws Exception {
                        return result.results;
                    }
                })
                .map(new Function<List<Movie>, List<MovieModel>>() {
                    @Override
                    public List<MovieModel> apply(@NonNull List<Movie> movies) throws Exception {
                        List<MovieModel> movieList = new ArrayList<>();
                        for (Movie movie : movies)
                            movieList.add(MovieMapper.apiToModel(movie));

                        movieDao = setUpDB();
                        movieDao.insert(movieList);
                        return movieDao.getMovies();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MovieModel>>() {
                    @Override
                    public void accept(@NonNull List<MovieModel> movieList) throws Exception {
                        swapAdapter(movieList);
                    }
                });
    }

    void swapAdapter(List<MovieModel> movieList){
        MoviesAdapter adapter = new MoviesAdapter(movieList);
        recyclerView.swapAdapter(adapter, false);
    }

    private void setUpUI() {
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private MovieDao setUpDB() {
        MovieDataBase db = Room.databaseBuilder(getApplicationContext(),
                MovieDataBase.class, "movieDB")
                .fallbackToDestructiveMigration()
                .build();
        return db.getMovieDao();
    }
}
