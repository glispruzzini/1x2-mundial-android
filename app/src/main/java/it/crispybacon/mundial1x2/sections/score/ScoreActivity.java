package it.crispybacon.mundial1x2.sections.score;

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
import it.crispybacon.mundial1x2.sections.results.Result;

public class ScoreActivity extends Activity1x2 {

    private AppCompatTextView mTextPoints;
    private AppCompatTextView mTextPointsGlobal;
    private AppCompatTextView mTextPointsFriends;
    private AppCompatTextView mTextPlaceHolder;
    private RecyclerView mRecyclerView;

    private ScoreAdapter mScoreAdapter;

    public static Intent getStartIntent(final Context context) {
        Intent startIntent = new Intent(context, ScoreActivity.class);
        return startIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mTextPoints = findViewById(R.id.text_points);
        mTextPointsGlobal = findViewById(R.id.text_global_points);
        mTextPointsFriends = findViewById(R.id.text_friends_points);
        mTextPlaceHolder = findViewById(R.id.text_placeholder);
        mRecyclerView = findViewById(R.id.recycler_scores);

    }

    @Override
    protected void init() {
        super.init();

        mTextPlaceHolder.setVisibility(View.GONE);

        LinearLayoutManager vLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mScoreAdapter = new ScoreAdapter(this);
        mRecyclerView.setLayoutManager(vLayoutManager);
        mRecyclerView.setAdapter(mScoreAdapter);

        getScoresList();
    }

    private void getScoresList() {
        //TODO: call API via Core
        onScoresReceived(new ArrayList<Score>());
    }


    private void onScoresReceived(ArrayList<Score> results){
        if(results!=null && results.size()>0){
            mTextPlaceHolder.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);

            mScoreAdapter.updateData(results);

        }else {
            mTextPlaceHolder.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }
}
