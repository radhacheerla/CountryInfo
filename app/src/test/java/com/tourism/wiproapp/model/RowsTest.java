package com.tourism.wiproapp.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;


public class RowsTest {

    public static final String Title = "Beavers";
    public static final String Description = "Beavers are second only to humans in their ability to manipulate and change their environment";
    public static final String ImageHref = "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg";

    @Mock
    private Rows mCountryInfo;


    @Before
    public void setup() {
        mCountryInfo = new Rows();

        mCountryInfo.setmTitle(Title);
        mCountryInfo.setmDescription(Description);
        mCountryInfo.setmLink(ImageHref);


    }

    @Test
    public void title() {
        Assert.assertEquals(mCountryInfo.getmTitle(), Title);
    }


    @Test
    public void description() {
        Assert.assertEquals(mCountryInfo.getmDescription(), Description);
    }

    @Test
    public void imageRef() {
        Assert.assertEquals(mCountryInfo.getmLink(), ImageHref);
    }
}