package com.fivewheeler.training.newscomparator.fragments.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    private List<DummyItem> mItems = new ArrayList<DummyItem>();


    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String title;
        public String content;
        public String linkUrl;

        public DummyItem(String title, String content, String linkUrl) {
            this.title = title;
            this.content = content;
            this.linkUrl = linkUrl;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
