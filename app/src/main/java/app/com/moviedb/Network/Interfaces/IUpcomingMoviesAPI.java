package app.com.moviedb.Network.Interfaces;

import app.com.moviedb.Models.MovieResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static app.com.moviedb.Utils.Constants.API_KEY;


public interface IUpcomingMoviesAPI {
    @GET("movie/upcoming")
    Observable<MovieResponse> getUpcomingMovies(@Query("api_key") String api_key);
}
