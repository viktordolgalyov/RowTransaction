package com.rowtransaction;

import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.Row;

final class AddCommand implements Command {
    Row row;
    int index;

    AddCommand() {
        index = -1;
    }

    @Override
    public void execute(ArrayObjectAdapter adapter) {
        if (row == null) {
            throw new IllegalArgumentException("Row which must be added can not be null");
        }
        if (index >= 0) {
            adapter.add(index, row);
        } else {
            adapter.add(row);
        }
    }
}
