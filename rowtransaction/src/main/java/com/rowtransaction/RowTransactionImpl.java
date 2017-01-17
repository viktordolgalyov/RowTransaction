package com.rowtransaction;

import android.support.v17.leanback.widget.Row;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

final class RowTransactionImpl implements RowTransaction {
    private final RowManager rowManager;
    private final Queue<Command> commands;

    RowTransactionImpl(RowManager rowManager) {
        this.rowManager = rowManager;
        this.commands = new LinkedList<>();
    }

    @Override
    public RowTransaction add(Row row) {
        AddCommand cmd = new AddCommand();
        cmd.row = row;
        commands.offer(cmd);
        return this;
    }

    @Override
    public RowTransaction add(int index, Row row) {
        AddCommand cmd = new AddCommand();
        cmd.row = row;
        cmd.index = index;
        commands.offer(cmd);
        return this;
    }

    @Override
    public RowTransaction addAll(Collection<Row> rows) {
        AddAllCommand cmd = new AddAllCommand();
        cmd.rows = rows;
        commands.offer(cmd);
        return this;
    }

    @Override
    public RowTransaction addAll(int index, Collection<Row> rows) {
        AddAllCommand cmd = new AddAllCommand();
        cmd.index = index;
        cmd.rows = rows;
        commands.offer(cmd);
        return this;
    }

    @Override
    public RowTransaction replace(Row previousRow, Row newRow) {
        ReplaceCommand cmd = new ReplaceCommand();
        cmd.newRow = newRow;
        cmd.previousRow = previousRow;
        commands.offer(cmd);
        return this;
    }

    @Override
    public RowTransaction replace(long previousRowId, Row newRow) {
        ReplaceCommand cmd = new ReplaceCommand();
        cmd.newRow = newRow;
        cmd.previousRowId = previousRowId;
        commands.offer(cmd);
        return this;
    }

    @Override
    public RowTransaction replace(int previousRowIndex, Row newRow) {
        ReplaceCommand cmd = new ReplaceCommand();
        cmd.newRow = newRow;
        cmd.previousRowIndex = previousRowIndex;
        commands.offer(cmd);
        return this;
    }

    @Override
    public RowTransaction remove(int rowIndex) {
        RemoveCommand cmd = new RemoveCommand();
        cmd.index = rowIndex;
        commands.offer(cmd);
        return this;
    }

    @Override
    public RowTransaction remove(long rowId) {
        RemoveCommand cmd = new RemoveCommand();
        cmd.rowId = rowId;
        commands.offer(cmd);
        return this;
    }

    @Override
    public RowTransaction remove(Row row) {
        RemoveCommand cmd = new RemoveCommand();
        cmd.row = row;
        commands.offer(cmd);
        return this;
    }

    @Override
    public RowTransaction removeAll(int startPosition) {
        RemoveAllCommand cmd = new RemoveAllCommand();
        cmd.position = startPosition;
        cmd.count = Integer.MAX_VALUE;
        commands.offer(cmd);
        return this;
    }

    @Override
    public RowTransaction removeAll(int position, int count) {
        RemoveAllCommand cmd = new RemoveAllCommand();
        cmd.position = position;
        cmd.count = count;
        commands.offer(cmd);
        return this;
    }

    @Override
    public boolean isEmpty() {
        return commands.size() == 0;
    }

    @Override
    public void commit() {
        rowManager.executeCommands(commands);
    }
}
