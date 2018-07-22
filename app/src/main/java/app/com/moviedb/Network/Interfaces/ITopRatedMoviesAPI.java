package app.com.moviedb.Network.Interfaces;

import app.com.moviedb.Models.MovieResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kashish on 22-07-2018.
 */

public interface ITopRatedMoviesAPI {
    @GET("movie/top_rated")
    Observable<MovieResponse> getUpcomingMovies(@Query("api_key") String api_key);
}
