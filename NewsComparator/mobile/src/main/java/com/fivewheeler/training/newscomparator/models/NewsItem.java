package com.fivewheeler.training.newscomparator.models;

import org.simpleframework.xml.Element;

/**
 * Created by admin on 22/01/16.
 */
@Element
public class NewsItem{

    @Element(required = false)
    public String title;

    @Element(required = false )
    public String description;

    @Element(required = false )
    public String link;
}
