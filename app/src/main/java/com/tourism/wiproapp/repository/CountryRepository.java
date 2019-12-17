package com.tourism.wiproapp.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.tourism.wiproapp.network.RestApiService;
import com.tourism.wiproapp.network.RetrofitInstance;
import com.tourism.wiproapp.model.Country;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class helps manage communication from repository to ViewModels
 */

public class CountryRepository {

    private MutableLiveData<String> mCountryName = new MutableLiveData<>();
    private MutableLiveData<Country> mCountry = new MutableLiveData<>();
    private Application application;

    public CountryRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<Country> getCountryInfo() {

        RestApiService apiService = RetrofitInstance.getApiService();

        Call<Country> call = apiService.getCountryDetails();

        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {
                Country mCountryValue = response.body();
                if (mCountry != null) {

                    mCountry.setValue(mCountryValue);
                }
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {

            }
        });


        return mCountry;
    }
}
