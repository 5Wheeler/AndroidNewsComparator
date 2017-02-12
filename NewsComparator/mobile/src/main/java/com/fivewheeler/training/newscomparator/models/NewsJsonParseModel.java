package com.fivewheeler.training.newscomparator.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 22/01/16.
 * <p/>
 * This class is used to parse the Json model data parsed from
 * json feeds.
 */
public class NewsJsonParseModel {
    @SerializedName("responseData")
    public ResponseData mResponseData;

}
