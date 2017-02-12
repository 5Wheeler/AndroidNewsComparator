package com.fivewheeler.training.newscomparator;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by rvarm on 1/21/17.
 */

public class OverlapDecoration extends RecyclerView.ItemDecoration {

    //TODO: This should be to the height of the screen
    public static int vertOverlap = -760;

    //TODO: This should be to the width of the screen
    private final static int horOverlap = -300;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        final int itemPosition = parent.getChildAdapterPosition(view);

        if (itemPosition == 0) {
            return;
        }
        outRect.set(0, vertOverlap, 0, 0);

    }
}
