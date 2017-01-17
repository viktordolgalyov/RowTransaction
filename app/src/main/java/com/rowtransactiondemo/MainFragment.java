package com.rowtransactiondemo;

import android.os.Bundle;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;

import com.rowtransaction.RowManager;
import com.rowtransaction.RowTransaction;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainFragment extends BrowseFragment implements OnItemViewClickedListener {
    private static final int NUM_ROWS = 6;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setAdapter(new ArrayObjectAdapter(new ListRowPresenter()));
        setupUi();
        loadRows();
    }

    private void loadRows() {
        List<Movie> movies = MovieList.setupMovies();
        RowTransaction transaction = RowManager.with(((ArrayObjectAdapter) getAdapter())).beginTransaction();
        for (int i = 0; i < NUM_ROWS; i++) {
            transaction.add(createMoviesRow(movies));
        }
        transaction.add(0, createActionsRow());
        transaction.commit();
    }

    private void setupUi() {
        setTitle(getString(R.string.browse_title));
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);
        setOnItemViewClickedListener(this);
    }

    private Row createMoviesRow(List<Movie> items) {
        ArrayObjectAdapter rowAdapter = new ArrayObjectAdapter(new CardPresenter());
        rowAdapter.addAll(0, items);
        int categoryIndex = new Random().nextInt() % (MovieList.MOVIE_CATEGORY.length - 1);
        HeaderItem header = new HeaderItem(0, MovieList.MOVIE_CATEGORY[Math.abs(categoryIndex)]);
        return new ListRow(header, rowAdapter);
    }

    private Row createActionsRow() {
        HeaderItem actionsHeader = new HeaderItem(0, getString(R.string.actions));
        GridItemPresenter actionsPresenter = new GridItemPresenter();
        ArrayObjectAdapter actionsAdapter = new ArrayObjectAdapter(actionsPresenter);
        actionsAdapter.add(getString(R.string.add));
        actionsAdapter.add(getString(R.string.replace));
        actionsAdapter.add(getString(R.string.remove));
        actionsAdapter.add(getString(R.string.remove_and_add));
        ListRow actionsRow = new ListRow(actionsHeader, actionsAdapter);
        actionsRow.setId(R.id.row_actions);
        return actionsRow;
    }

    private void checkActionsRow(RowManager manager) {
        if (manager.getRowById(R.id.row_actions) == null) {
            manager.beginTransaction()
                    .add(0, createActionsRow())
                    .commit();
        }
    }

    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                              RowPresenter.ViewHolder rowViewHolder, Row row) {
        if (item instanceof String) {
            RowManager manager = RowManager.with(((ArrayObjectAdapter) getAdapter()));
            RowTransaction transaction = manager.beginTransaction();
            List<Movie> movies = MovieList.setupMovies();
            Collections.shuffle(movies);

            if (item.equals(getString(R.string.add))) {
                transaction.add(createMoviesRow(movies));
            } else if (item.equals(getString(R.string.replace))) {
                transaction.replace(Math.round(manager.getRowsCount() / 2), createMoviesRow(movies));
            } else if (item.equals(getString(R.string.remove))) {
                if (manager.getRowsCount() > 1) {//because you can't(unfortunately) remove last row in the adapter if the row is focused
                    transaction.remove(Math.round(manager.getRowsCount() / 2));
                }
            } else if (item.equals(getString(R.string.remove_and_add))) {
                if (manager.getRowsCount() > 1) {//because you can't(unfortunately) remove last row in the adapter if the row is focused
                    transaction.remove(Math.round(manager.getRowsCount() / 2));
                }
                transaction.add(0, createMoviesRow(movies));
            }
            transaction.commit();
            checkActionsRow(manager);
        }
    }
}
