package com.tourism.wiproapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Model class for Country
 */
public class Country implements Serializable {

    @SerializedName("rows")
    private List<Rows> mData;

    public List<Rows> getmData() {
        return mData;
    }

    public void setmData(List<Rows> mData) {
        this.mData = mData;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    @SerializedName("title")
    private String CountryName;

}
