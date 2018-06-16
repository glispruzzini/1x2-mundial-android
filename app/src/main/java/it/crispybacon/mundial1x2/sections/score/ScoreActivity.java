package it.crispybacon.mundial1x2.sections.score;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import it.crispybacon.mundial1x2.R;
import it.crispybacon.mundial1x2.sections.results.ResultsActivity;

public class ScoreActivity extends AppCompatActivity {


    public static Intent getStartIntent(final Context context) {
        Intent startIntent = new Intent(context, ScoreActivity.class);
        return startIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
    }
}
