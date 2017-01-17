package com.rowtransaction;

import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.Row;

import java.util.Queue;

public final class RowManager implements IRowManager {
    private final ArrayObjectAdapter adapter;

    public static RowManager with(ArrayObjectAdapter adapter) {
        if (adapter == null) {
            throw new IllegalArgumentException("Adapter can not be null!");
        }
        return new RowManager(adapter);
    }

    private RowManager(ArrayObjectAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public RowTransaction beginTransaction() {
        return new RowTransactionImpl(this);
    }

    /**
     * Returns the item for the given id.
     *
     * @return {@link Row} with the given id or {@code null} if adapter doesn't contains a row with given id.
     */
    @Override
    public Row getRowById(long id) {
        for (int i = 0; i < adapter.size(); ++i) {
            Object o = adapter.get(i);
            if (o instanceof Row) {
                if (((Row) o).getId() == id) {
                    return (Row) o;
                }
            }
        }
        return null;
    }

    /**
     * Returns the row for the given position.
     *
     * @return {@link Row} at the given index or {@code null} if item in the adapter
     * isn't instance of {@link Row} or index bigger than the adapter size
     */
    @Override
    public Row getRowAt(int index) {
        if (index >= adapter.size()) {
            return null;
        }
        Object o = adapter.get(index);
        if (o instanceof Row) {
            return (Row) o;
        }
        return null;
    }

    /**
     * Returns the index for the first occurrence of row in the adapter, or -1 if
     * not found.
     *
     * @param row The row to find in the adapter.
     * @return Index of the first occurrence of the item in the adapter, or -1
     * if not found.
     */
    @Override
    public int indexOf(Row row) {
        return adapter.indexOf(row);
    }

    /**
     * Returns the index for the first occurrence of row in the adapter, or -1 if
     * not found.
     *
     * @param rowId The id of the row to find in the adapter.
     * @return Index of the first occurrence of the item in the adapter, or -1
     * if not found.
     */
    @Override
    public int indexOf(int rowId) {
        return adapter.indexOf(getRowById(rowId));
    }

    /**
     * Returns the number of rows in the adapter.
     */
    @Override
    public int getRowsCount() {
        return adapter.size();
    }

    void executeCommands(Queue<Command> commands) {
        while (commands.size() != 0) {
            commands.poll().execute(adapter);
        }
    }
}
