package com.rowtransaction;

import android.support.v17.leanback.widget.ArrayObjectAdapter;

final class ClearCommand implements Command {
    @Override
    public void execute(ArrayObjectAdapter adapter) {
        adapter.removeItems(0, adapter.size());
    }
}
