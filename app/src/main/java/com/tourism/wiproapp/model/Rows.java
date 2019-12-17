package com.tourism.wiproapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Model class for Country Details
 */
public class Rows implements Serializable {

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setmLink(String mLink) {
        this.mLink = mLink;
    }

    @SerializedName("title")
    private String mTitle;

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmLink() {
        return mLink;
    }

    @SerializedName("description")
    private String mDescription;
    @SerializedName("imageHref")
    private String mLink;
}
