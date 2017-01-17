package com.rowtransaction;

import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.Row;

final class RemoveCommand implements Command {
    Row row;
    int index;
    long rowId;

    RemoveCommand() {
        index = -1;
        rowId = -1;
    }

    @Override
    public void execute(ArrayObjectAdapter adapter) {
        if (row != null) {
            removeByValue(adapter);
        } else if (index != -1) {
            removeByIndex(adapter);
        } else if (rowId != -1) {
            removeById(adapter);
        }
    }

    private void removeByIndex(ArrayObjectAdapter adapter) {
        adapter.removeItems(index, 1);
    }

    private void removeById(ArrayObjectAdapter adapter) {
        for (int i = 0; i < adapter.size(); ++i) {
            Object o = adapter.get(i);
            if (o instanceof Row && ((Row) o).getId() == rowId) {
                removeByIndex(adapter);
                return;
            }
        }
    }

    private void removeByValue(ArrayObjectAdapter adapter) {
        for (int i = 0; i < adapter.size(); ++i) {
            Object o = adapter.get(i);
            if (o.equals(row)) {
                removeByIndex(adapter);
                return;
            }
        }
    }
}
