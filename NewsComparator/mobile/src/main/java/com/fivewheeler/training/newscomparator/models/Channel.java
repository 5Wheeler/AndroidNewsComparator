package com.fivewheeler.training.newscomparator.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by admin on 22/01/16.
 */
@Element
public class Channel {

    @ElementList(entry = "item", inline = true)
    public List<NewsItem> itemList;

    @Element(required = false)
    public String title;
}
