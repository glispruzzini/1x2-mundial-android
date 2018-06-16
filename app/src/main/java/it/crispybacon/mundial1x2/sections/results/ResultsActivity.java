package it.crispybacon.mundial1x2.sections.results;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import it.crispybacon.mundial1x2.R;

public class ResultsActivity extends AppCompatActivity {

    public static Intent getStartIntent(final Context context) {
        Intent startIntent = new Intent(context, ResultsActivity.class);
        return startIntent;
    }

    private ResultsAdapter mResultsAdapter = new ResultsAdapter();
    private RecyclerView mRecyclerResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        mRecyclerResults = findViewById(R.id.recycler_results);
        initList();
        getResults();
    }

    private void initList() {
        LinearLayoutManager vLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerResults.setLayoutManager(vLayoutManager);
        mRecyclerResults.setAdapter(mResultsAdapter);
    }

    private void getResults() {
        //TODO: call API via Core
        mResultsAdapter.setResults(new ArrayList<Result>());
    }

}
