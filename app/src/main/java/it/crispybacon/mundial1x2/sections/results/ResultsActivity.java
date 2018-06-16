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

import it.crispybacon.mundial1x2.Activity1x2;
import it.crispybacon.mundial1x2.R;

public class ResultsActivity extends Activity1x2 {

    public static Intent getStartIntent(final Context context) {
        Intent startIntent = new Intent(context, ResultsActivity.class);
        return startIntent;
    }

    private ResultsAdapter mResultsAdapter = new ResultsAdapter();
    private RecyclerView mRecyclerResults;
    private AppCompatTextView mTvPlaceholder;

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



    private void getResults() {
        //TODO: call API via Core
        onResultReceived(new ArrayList<Result>());
    }


    private void onResultReceived(ArrayList<Result> results){
        if(results!=null && results.size()>0){
            mTvPlaceholder.setVisibility(View.GONE);
            mRecyclerResults.setVisibility(View.VISIBLE);

            mResultsAdapter.setResults(results);

        }else {
            mTvPlaceholder.setVisibility(View.VISIBLE);
            mRecyclerResults.setVisibility(View.GONE);
        }
    }

}
