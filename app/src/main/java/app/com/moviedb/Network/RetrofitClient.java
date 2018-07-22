package app.com.moviedb.Network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static app.com.moviedb.Utils.Constants.BASE_URL;

/**
 * Created by Kashish on 22-07-2018.
 */

public class RetrofitClient {
    private static Retrofit ourInstance;

    public static Retrofit getInstance() {

        if (ourInstance == null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient okHttpClient = builder.build();

            ourInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return ourInstance;
    }

    private RetrofitClient() {
    }
}
