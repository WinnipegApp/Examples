package com.winnipegapp.examples.Notifications;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.winnipegapp.examples.HomeAdapter;

/**
 * Created by Amari on 2016-07-29.
 */
public class HomeSwipeHelper extends ItemTouchHelper.SimpleCallback {

    HomeAdapter adapter;

    public HomeSwipeHelper(int dragDirs, int swipeDirs, HomeAdapter adapter) {
        super(dragDirs, swipeDirs);

        this.adapter = adapter;
    }

    public HomeSwipeHelper(HomeAdapter adapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }


    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.dismissItem(viewHolder.getAdapterPosition());
    }
}