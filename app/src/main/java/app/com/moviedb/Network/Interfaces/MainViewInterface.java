package app.com.moviedb.Network.Interfaces;

import app.com.moviedb.Models.MovieResponse;

/**
 * Created by Kashish on 22-07-2018.
 */

public interface MainViewInterface {
    void showToast(String s);
    void displayMovies(MovieResponse movieResponse);
    void displayError(String s);
}
