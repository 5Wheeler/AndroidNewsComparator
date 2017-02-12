package com.fivewheeler.training.newscomparator.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by admin on 22/01/16.
 *
 * This class is used to get the news models data parsed from XML feeds.
 */
@Root
public class NewsXmlParseModel {

    @Element(required = true)
    public Channel channel;

}
