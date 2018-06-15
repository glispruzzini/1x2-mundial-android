package it.crispybacon.mundial1x2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;

import java.util.Date;

import it.crispybacon.mundial1x2.ui.imageview.FlagImageView;
import it.crispybacon.mundial1x2.ui.section.BentBackgroundLayout;
import it.crispybacon.mundial1x2.ui.selector.BetSelectionView;
import it.crispybacon.mundial1x2.ui.text.DateTextView;

public class HomeActivity extends AppCompatActivity implements BetSelectionView.IBetSelection {

    public static Intent getStartIntent(final Context context) {
        Intent startIntent = new Intent(context, HomeActivity.class);
        return startIntent;
    }

    private FlagImageView mFlagImageLeft;
    private FlagImageView mFlagImageRight;
    private DateTextView mDateTextView;
    private AppCompatTextView mHourTextView;
    private BetSelectionView mBetSelectionView;
    private BentBackgroundLayout mBentBackgroundLayout;

    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mFlagImageLeft = findViewById(R.id.img_left_team);
        mFlagImageRight = findViewById(R.id.img_right_team);
        mDateTextView = findViewById(R.id.text_date);
        mHourTextView = findViewById(R.id.text_hour);
        mBetSelectionView = findViewById(R.id.bet_selection_view);
        mBentBackgroundLayout = findViewById(R.id.bottom_container);

        init();

        mBetSelectionView.setBetListener(this);
        mBentBackgroundLayout.post(new Runnable() {
            @Override
            public void run() {
                //mBentBackgroundLayout.setupPosition();
            }
        });
    }

    private void init() {

        mFlagImageLeft.withFlag(R.drawable.flag_russia)
                .andText("Russia");

        mFlagImageRight.withFlag(R.drawable.flag_russia)
                .andText("Russia");

        mDateTextView.setDate(new Date());
        mHourTextView.setText("20:00");
    }

    @Override
    public void onBetChoosen(int selectedSection) {
        Log.d(TAG, "onBetChoosen: "+selectedSection);
    }
}
