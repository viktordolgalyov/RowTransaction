package com.rowtransaction;

import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.Row;

final class ReplaceCommand implements Command {
    Row previousRow;
    Row newRow;
    long previousRowId;
    int previousRowIndex;

    ReplaceCommand() {
        previousRowIndex = -1;
        previousRowId = -1;
    }

    @Override
    public void execute(ArrayObjectAdapter adapter) {
        if (newRow == null) {
            throw new IllegalArgumentException("Row which must be replaced can not be null");
        }
        if (previousRow != null) {
            replaceRowByValue(adapter);
        } else if (previousRowId != -1) {
            replaceRowById(adapter);
        } else if (previousRowIndex != -1) {
            replaceRowByIndex(adapter);
        }
    }

    private void replaceRowByIndex(ArrayObjectAdapter adapter) {
        adapter.replace(previousRowIndex, newRow);
    }

    private void replaceRowById(ArrayObjectAdapter adapter) {
        for (int i = 0; i < adapter.size(); ++i) {
            Object o = adapter.get(i);
            if (o instanceof Row && ((Row) o).getId() == previousRowId) {
                this.previousRowIndex = i;
                replaceRowByIndex(adapter);
                return;
            }
        }
    }

    private void replaceRowByValue(ArrayObjectAdapter adapter) {
        for (int i = 0; i < adapter.size(); ++i) {
            Object o = adapter.get(i);
            if (o.equals(previousRow)) {
                previousRowIndex = i;
                replaceRowByIndex(adapter);
                return;
            }
        }
    }
}
