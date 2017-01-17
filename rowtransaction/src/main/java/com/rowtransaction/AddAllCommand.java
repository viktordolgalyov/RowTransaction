package com.rowtransaction;

import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.Row;

import java.util.Collection;

final class AddAllCommand implements Command {
    Collection<Row> rows;
    int index;

    AddAllCommand() {
        this.index = -1;
    }

    @Override
    public void execute(ArrayObjectAdapter adapter) {
        if (index >= 0) {
            adapter.addAll(index, rows);
        } else {
            adapter.addAll(adapter.size(), rows);
        }
    }
}
