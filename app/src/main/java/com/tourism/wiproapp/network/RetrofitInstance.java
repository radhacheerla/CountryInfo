package com.tourism.wiproapp.network;

import com.tourism.wiproapp.network.RestApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class will create retofit instance and return
 */
public class RetrofitInstance {

    private static Retrofit retrofit = null;

    public static RestApiService getApiService() {

        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl("https://dl.dropboxusercontent.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

        }
        return retrofit.create(RestApiService.class);

    }

}
