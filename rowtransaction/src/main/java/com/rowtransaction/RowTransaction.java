package com.rowtransaction;

import android.support.v17.leanback.widget.Row;

import java.util.Collection;

public interface RowTransaction {
    /**
     * Inserts a row into adapter at the end.
     *
     * @param row The row to insert into the adapter.
     */
    RowTransaction add(Row row);

    /**
     * Inserts an item into the adapter at the specified index.
     * If the index is >= adapter size an exception will be thrown.
     *
     * @param index The index at which the item should be inserted.
     * @param row   The row to insert into the adapter.
     */
    RowTransaction add(int index, Row row);

    /**
     * Adds the objects in the given collection to the adapter at the end.
     *
     * @param rows A {@link Collection} of items to insert.
     */
    RowTransaction addAll(Collection<Row> rows);

    /**
     * Adds the rows in the given collection to the adapter, starting at the
     * given index.  If the index is >= adapter size an exception will be thrown.
     *
     * @param index The index at which the items should be inserted.
     * @param rows  A {@link Collection} of rows to insert.
     */
    RowTransaction addAll(int index, Collection<Row> rows);

    /**
     * Replaces row with a new row and calls notifyItemRangeChanged().
     * Note that this method does not compare new item to existing item.
     *
     * @param previous The row which should be replaced.
     * @param newRow   The row to be placed instead previous.
     */
    RowTransaction replace(Row previous, Row newRow);

    /**
     * Replaces item at position with a new item and calls notifyItemRangeChanged().
     * Note that this method does not compare new item to existing item.
     *
     * @param previousRowId The id of item to replace.
     * @param newRow        The new row to be placed instead the row with given id.
     */
    RowTransaction replace(long previousRowId, Row newRow);

    /**
     * Replaces item at position with a new item and calls notifyItemRangeChanged()
     * at the given position.  Note that this method does not compare new item to
     * existing item.
     *
     * @param previousRowIndex The index of item to replace.
     * @param newRow           The new row to be placed at given position.
     */
    RowTransaction replace(int previousRowIndex, Row newRow);

    /**
     * Removes the row at the given index from the adapter.
     *
     * @param rowIndex The index of the row to remove.
     */
    RowTransaction remove(int rowIndex);

    /**
     * Removes the row with the given id from the adapter.
     *
     * @param rowId The id of the row to remove.
     */
    RowTransaction remove(long rowId);

    /**
     * Removes the row from the adapter.
     *
     * @param row The row to remove.
     */
    RowTransaction remove(Row row);

    /**
     * Removes a range of items from the adapter. The range is [startPosition, adapter size]
     *
     * @param startPosition The index of the first item to remove.
     */
    RowTransaction removeAll(int startPosition);

    /**
     * Removes a range of items from the adapter. The range is specified by giving
     * the starting position and the number of elements to remove.
     *
     * @param position The index of the first item to remove.
     * @param count    The number of items to remove.
     */
    RowTransaction removeAll(int position, int count);

    /**
     * Returns <tt>true</tt> if this transaction contains no operations.
     *
     * @return is current transaction empty
     */
    boolean isEmpty();

    /**
     * Apply the transaction synchronously.
     */
    void commit();
}
