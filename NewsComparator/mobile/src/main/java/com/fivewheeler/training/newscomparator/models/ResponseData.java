package com.fivewheeler.training.newscomparator.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 22/01/16.
 */
public class ResponseData {
    @SerializedName("results")
    public List<ItemModel> mItemModel;


}
