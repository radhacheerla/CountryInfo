package com.tourism.wiproapp.network;

import com.tourism.wiproapp.model.Country;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * This is interface for Country Details
 */
public interface RestApiService {

    @GET("s/2iodh4vg0eortkl/facts.json")
    Call<Country> getCountryDetails();
}
