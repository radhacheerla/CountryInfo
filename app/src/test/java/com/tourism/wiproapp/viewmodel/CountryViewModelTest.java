package com.tourism.wiproapp.viewmodel;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.tourism.wiproapp.model.Country;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.FileReader;
import java.net.URL;

import static org.junit.Assert.*;

public class CountryViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();

    @Mock
    public MutableLiveData<Country> countryMutableLiveData;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCountryInfo() throws Exception {
        Gson gObject = new Gson();
        File file = getFileFromPath(this, "country_info_response.json");

        Country responseBody = gObject.fromJson(new FileReader(file), Country.class);

        countryMutableLiveData = new MutableLiveData<Country>();

        countryMutableLiveData.postValue(responseBody);

        assertEquals(responseBody.getCountryName(), countryMutableLiveData.getValue().getCountryName());

    }

    private static File getFileFromPath(Object obj, String fileName) {
        ClassLoader classLoader = obj.getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        return new File(resource.getPath());
    }
}