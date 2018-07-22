package app.com.moviedb.UI;

import android.util.Log;

import app.com.moviedb.Models.MovieResponse;
import app.com.moviedb.Network.Interfaces.ITopRatedMoviesAPI;
import app.com.moviedb.Network.Interfaces.IUpcomingMoviesAPI;
import app.com.moviedb.Network.Interfaces.MainPresenterInterface;
import app.com.moviedb.Network.Interfaces.MainViewInterface;
import app.com.moviedb.Network.RetrofitClient;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static app.com.moviedb.Utils.Constants.API_KEY;

public class MainPresenter implements MainPresenterInterface {

    MainViewInterface mainViewInterface;
    private String TAG = "MainPresenter";
    DisposableObserver<MovieResponse> mDisposableObserver;

    public MainPresenter(MainViewInterface mvi) {
        this.mainViewInterface = mvi;
    }

    @Override
    public void getUpcomingMovies() {
        getUpcomingObservable().subscribeWith(getObserver());
    }

    @Override
    public void getTopRatedMovies() {
        getTopRatedObservable().subscribeWith(getObserver());
    }

    public Observable<MovieResponse> getUpcomingObservable(){
        return RetrofitClient.getInstance().create(IUpcomingMoviesAPI.class)
                .getUpcomingMovies(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<MovieResponse> getTopRatedObservable(){
        return RetrofitClient.getInstance().create(ITopRatedMoviesAPI.class)
                .getUpcomingMovies(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<MovieResponse> getObserver(){
        mDisposableObserver = new DisposableObserver<MovieResponse>() {
            @Override
            public void onNext(MovieResponse movieResponse) {
                Log.d(TAG,"OnNext"+movieResponse.getTotalResults());
                mainViewInterface.displayMovies(movieResponse);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                mainViewInterface.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
            }
        };

        return  mDisposableObserver;
    }

}
