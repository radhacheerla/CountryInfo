package com.tourism.wiproapp.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;


public class CountryTest {

    public static final String Title = "Beavers";
    public static final String Description = "Beavers are second only to humans in their ability to manipulate and change their environment";
    public static final String ImageHref = "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg";
    public static final String CountryName = "About Canada";
    @Mock
    private Rows mCountryInfo;

    private List<Rows> objList;

    @Mock
    private Country mCountry;

    @Before
    public void setup() {
        mCountryInfo = new Rows();
        mCountry = new Country();
        mCountryInfo.setmTitle(Title);
        mCountryInfo.setmDescription(Description);
        mCountryInfo.setmLink(ImageHref);

        objList = new ArrayList<Rows>();
        objList.add(mCountryInfo);
        mCountry.setmData(objList);
        mCountry.setCountryName(CountryName);

    }

    @Test
    public void countryName() {
        Assert.assertEquals(mCountry.getCountryName(), CountryName);
    }

    @Test
    public void countryList() {
        Assert.assertEquals(mCountry.getmData(), objList);
    }


}