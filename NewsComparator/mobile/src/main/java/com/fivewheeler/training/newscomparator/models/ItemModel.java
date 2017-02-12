package com.fivewheeler.training.newscomparator.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 22/01/16.
 */

public class ItemModel {

    @SerializedName("content")
    public String mContent;

    @SerializedName("url")
    public String mUrl;

    @SerializedName("title")
    public String mTitle;

}