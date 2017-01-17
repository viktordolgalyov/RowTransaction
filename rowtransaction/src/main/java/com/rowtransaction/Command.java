package com.rowtransaction;

import android.support.v17.leanback.widget.ArrayObjectAdapter;

interface Command {
    void execute(ArrayObjectAdapter adapter);
}
