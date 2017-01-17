package com.rowtransaction;

import android.support.v17.leanback.widget.ArrayObjectAdapter;

final class RemoveAllCommand implements Command {
    int position;
    int count;

    @Override
    public void execute(ArrayObjectAdapter adapter) {
        adapter.removeItems(position, count);
    }
}
