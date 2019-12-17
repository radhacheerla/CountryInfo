package com.tourism.wiproapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tourism.wiproapp.model.Country;
import com.tourism.wiproapp.repository.CountryRepository;

/**
 * This class will hold country data  and will hold copy of data when screen orientation happen also
 */
public class CountryViewModel extends AndroidViewModel {
    private CountryRepository countryRepository;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        countryRepository = new CountryRepository(application);
    }

    public LiveData<Country> getCountryInfo() {
        return countryRepository.getCountryInfo();
    }
}
