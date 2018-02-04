package com.pablosantos.moviedb.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.pablosantos.moviedb.R;
import com.pablosantos.moviedb.data.local.MovieDao;
import com.pablosantos.moviedb.data.MovieMapper;
import com.pablosantos.moviedb.data.local.MovieDataBaseConnection;
import com.pablosantos.moviedb.data.local.MovieModel;
import com.pablosantos.moviedb.data.remote.Api;
import com.pablosantos.moviedb.data.remote.ApiService;
import com.pablosantos.moviedb.data.remote.MovieResponse;
import com.pablosantos.moviedb.data.remote.Response;
import com.pablosantos.moviedb.ui.adapters.MoviesAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    // Data.
    private MovieDao movieDao;
    private boolean showingFavourites = false;

    // UI.
    private RecyclerView recyclerView;
    private MenuItem miFavourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
        setUpDB();

        getPopularMovies();

    }

    private void getPopularMovies() {
        Api a = new ApiService().getApi();
        a.getPopularMovies()
                .subscribeOn(Schedulers.io())
                .map(new Function<Response, List<MovieResponse>>() {
                    @Override
                    public List<MovieResponse> apply(@NonNull Response result) throws Exception {
                        return result.results;
                    }
                })
                .map(new Function<List<MovieResponse>, List<MovieModel>>() {
                    @Override
                    public List<MovieModel> apply(@NonNull List<MovieResponse> movies) throws Exception {
                        List<MovieModel> movieList = new ArrayList<>();
                        for (MovieResponse movieResponse : movies)
                            movieList.add(MovieMapper.apiToModel(movieResponse));

                        movieDao.insert(movieList);
                        return movieDao.getMovies();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MovieModel>>() {
                    @Override
                    public void accept(@NonNull List<MovieModel> movieList) throws Exception {
                        Log.e("Prueba", movieList.toString());
                        swapAdapter(movieList);
                    }
                });
    }

    void swapAdapter(List<MovieModel> movieList) {
        MoviesAdapter adapter = new MoviesAdapter(movieList, this);
        recyclerView.swapAdapter(adapter, false);
    }

    private void setUpUI() {
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    private void setUpDB() {
        movieDao = MovieDataBaseConnection.getInstance(getApplicationContext()).getDb().getMovieDao();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        miFavourite = menu.findItem(R.id.action_favorite);
        return true;
    }

    @Override
    public void onItemClick(MovieModel item, int position) {
        Api a = new ApiService().getApi();
        a.getMovie(item.id.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(@NonNull MovieResponse movie) throws Exception {
                        seeMovieDetails(movie);
                    }
                });

    }

    @Override
    protected void onResume() {
        if (showingFavourites)
            new getFavouriteMovies().execute();
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                showingFavourites = !showingFavourites;
                if (showingFavourites)
                    miFavourite.setIcon(android.R.drawable.btn_star_big_on);
                else miFavourite.setIcon(android.R.drawable.btn_star_big_off);
                swapMovieList();
            default:
                // Only if user's action was not recognized.
                return super.onOptionsItemSelected(item);
        }
    }

    private void swapMovieList() {
        if (showingFavourites)
            new getFavouriteMovies().execute();
        else getPopularMovies();
    }

    public void seeMovieDetails(MovieResponse movie) {
        Intent intent = new Intent(this, MovieActivity.class);

        // Room/Rxjava doesn't get along very well with the Serializable interface,
        // so I'll put the needed info one by one...

        // Merge the genres.
        String genres = "";
        for (int i = 0; i < movie.genres.size(); i++) {
            genres += movie.genres.get(i).genreName;
            if (i + 1 < movie.genres.size()) genres += ", ";
        }

        // Merge the companies.
        String companies = "";
        for (int i = 0; i < movie.productionCompanies.size(); i++) {
            companies += movie.productionCompanies.get(i).companyName;
            if (i + 1 < movie.productionCompanies.size()) companies += ", ";
        }

        intent.putExtra("movieId", movie.id);
        intent.putExtra("title", movie.title);
        intent.putExtra("overview", movie.overview);
        intent.putExtra("genres", genres);
        intent.putExtra("companies", companies);
        intent.putExtra("poster", movie.posterPath);
        intent.putExtra("releaseDate", movie.releaseDate);
        intent.putExtra("rating", String.valueOf(movie.voteAverage));

        startActivity(intent);
    }


    private class getFavouriteMovies extends AsyncTask<Void, Boolean, List<MovieModel>> {
        @Override
        protected List<MovieModel> doInBackground(Void... args) {
            return movieDao.getFavouriteMovies();
        }

        protected void onPostExecute(List<MovieModel> movies) {
            swapAdapter(movies);
        }
    }
}
