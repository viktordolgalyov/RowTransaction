package com.rowtransaction;

import android.support.v17.leanback.widget.Row;

interface IRowManager {
    RowTransaction beginTransaction();

    Row getRowById(long id);

    Row getRowAt(int index);

    int indexOf(Row row);

    int indexOf(int rowId);

    int getRowsCount();
}
