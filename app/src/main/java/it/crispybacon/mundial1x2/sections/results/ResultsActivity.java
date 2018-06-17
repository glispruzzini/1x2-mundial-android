package it.crispybacon.mundial1x2.sections.results;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import it.crispybacon.mundial1x2.Activity1x2;
import it.crispybacon.mundial1x2.R;
import it.crispybacon.mundial1x2.core.apimodels.Bet;
import it.crispybacon.mundial1x2.core.bets.BetsApiService;

public class ResultsActivity extends Activity1x2 {

    public static Intent getStartIntent(final Context context) {
        Intent startIntent = new Intent(context, ResultsActivity.class);
        return startIntent;
    }

    private ResultsAdapter mResultsAdapter = new ResultsAdapter();
    private RecyclerView mRecyclerResults;
    private AppCompatTextView mTvPlaceholder;

    private Disposable mResultsDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        mRecyclerResults = findViewById(R.id.recycler_results);
        mTvPlaceholder = findViewById(R.id.text_placeholder);

        init();
    }

    @Override
    protected void init() {
        super.init();
        LinearLayoutManager vLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerResults.setLayoutManager(vLayoutManager);
        mRecyclerResults.setAdapter(mResultsAdapter);

        getResults();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mResultsDisposable != null && !mResultsDisposable.isDisposed()) {
            mResultsDisposable.dispose();
        }
    }

    private void getResults() {
        mResultsDisposable = BetsApiService.get()
                .myBets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Bet>>() {
                    @Override
                    public void accept(List<Bet> bets) {
                        onResultReceived(bets);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        showSnackBar(findViewById(R.id.view_root), throwable.getMessage());
                    }
                });
    }


    private void onResultReceived(List<Bet> results) {
        if (results != null && results.size() > 0) {
            mTvPlaceholder.setVisibility(View.GONE);
            mRecyclerResults.setVisibility(View.VISIBLE);
        } else {
            mTvPlaceholder.setVisibility(View.VISIBLE);
            mRecyclerResults.setVisibility(View.GONE);
        }
        mResultsAdapter.setResults(results);
    }

}
