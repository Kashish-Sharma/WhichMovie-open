package app.com.moviedb.UI.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.com.moviedb.Adapters.MovieAdapter;
import app.com.moviedb.Models.MovieResponse;
import app.com.moviedb.Models.Result;
import app.com.moviedb.Network.Interfaces.IUpcomingMoviesAPI;
import app.com.moviedb.Network.Interfaces.MainViewInterface;
import app.com.moviedb.R;
import app.com.moviedb.Network.RetrofitClient;
import app.com.moviedb.UI.MainPresenter;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingMoviesFragment extends Fragment implements MainViewInterface {


    public UpcomingMoviesFragment() {
        // Required empty public constructor
    }
    private static final String TAG = "UPCOMING_MOVIE_FRAGMENT";

    private View mMainView;
    RecyclerView mMoviewRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    MovieAdapter mMovieAdapter;
    MainPresenter mainPresenter;
    List<Result> mUpcomingList = new ArrayList<Result>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_upcoming_movies, container, false);

        setupMVP();
        initRecyclerView(container);
        getUpcomingMovies();

        return mMainView;
    }

    private void setupMVP() {
        mainPresenter = new MainPresenter(this);
    }

    private void getUpcomingMovies() {
        mainPresenter.getUpcomingMovies();
    }

    private void initRecyclerView(ViewGroup container){
        mLinearLayoutManager = new LinearLayoutManager(container.getContext());
        mMoviewRecyclerView = mMainView.findViewById(R.id.upcoming_movie_recycler_view);
        mMoviewRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMoviewRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayMovies(MovieResponse movieResponse) {
        if (movieResponse != null){
            Log.d(TAG,movieResponse.getResults().get(1).getTitle());
            mUpcomingList = movieResponse.getResults();
            mMovieAdapter = new MovieAdapter(mUpcomingList);
            mMoviewRecyclerView.setAdapter(mMovieAdapter);
        } else{
            Log.d(TAG,"Movies response null");
        }
    }

    @Override
    public void displayError(String s) {
        showToast(s);
    }

}
