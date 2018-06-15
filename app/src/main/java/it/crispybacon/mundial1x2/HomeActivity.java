package it.crispybacon.mundial1x2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import it.crispybacon.mundial1x2.core.apimodels.Bet;
import it.crispybacon.mundial1x2.core.apimodels.Match;
import it.crispybacon.mundial1x2.core.apimodels.SimpleResponse;
import it.crispybacon.mundial1x2.core.bets.BetsApiService;
import it.crispybacon.mundial1x2.core.macthes.MatchesApiService;
import it.crispybacon.mundial1x2.ui.imageview.FlagImageView;
import it.crispybacon.mundial1x2.ui.section.BentBackgroundLayout;
import it.crispybacon.mundial1x2.ui.selector.BetSelectionView;
import it.crispybacon.mundial1x2.ui.selector.BetSelectionView;
import it.crispybacon.mundial1x2.ui.text.DateTextView;

public class HomeActivity extends Activity1x2 implements BetSelectionView.IBetSelection {

    public static Intent getStartIntent(final Context context) {
        Intent startIntent = new Intent(context, HomeActivity.class);
        return startIntent;
    }

    private FlagImageView mFlagImageLeft;
    private FlagImageView mFlagImageRight;
    private DateTextView mDateTextView;
    private AppCompatTextView mHourTextView;
    private BentBackgroundLayout mBentBackgroundLayout;

    private static final String TAG = "HomeActivity";
    private BetSelectionView mBetSelectionView;

    private Disposable mDisposableMatches;
    private Disposable mDisposableBet;

    private Match mShownMatch;


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
        mBetSelectionView = findViewById(R.id.bet_selection_view);

        getMatches();
        mBetSelectionView.setBetListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposableMatches != null && !mDisposableMatches.isDisposed()) {
            mDisposableMatches.dispose();
        }
        if (mDisposableBet != null && !mDisposableBet.isDisposed()) {
            mDisposableBet.dispose();
        }
    }

    private void getMatches() {
        showLoadingDialog();
        mDisposableMatches = MatchesApiService.get()
                .getMatches()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Match>>() {
                    @Override
                    public void accept(List<Match> matches) throws Exception {
                        onMatchesLoaded(matches);
                        hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showSnackBar(findViewById(R.id.view_root), throwable.getMessage());
                        hideLoadingDialog();
                    }
                });
    }

    private void onMatchesLoaded(List<Match> matches) {
        if (matches != null && matches.size() > 0) {
            mShownMatch = matches.get(0);
            mFlagImageLeft.withFlag(R.drawable.flag_russia)
                    .andText(mShownMatch.team1.name);

            mFlagImageRight.withFlag(R.drawable.flag_russia)
                    .andText(mShownMatch.team2.name);

            mDateTextView.setDate(mShownMatch.date);
            SimpleDateFormat vSimpleDateFormat = new SimpleDateFormat("HH:mm", Locale.ITALY);
            mHourTextView.setText(vSimpleDateFormat.format(mShownMatch.date));
        } else {
            //TODO: show placeholder
        }
    }

    private void placeBet(final Match match, final Bet.BetResult betResult) {
        if (match != null) {
            showLoadingDialog();
            mDisposableBet = BetsApiService.get()
                    .placeBet(match, betResult)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<SimpleResponse>() {
                        @Override
                        public void accept(SimpleResponse response) throws Exception {
                            showSnackBar(findViewById(R.id.view_root), getString(R.string.bet_placed, betResult.toString()));
                            hideLoadingDialog();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            showSnackBar(findViewById(R.id.view_root), throwable.getMessage());
                            hideLoadingDialog();
                        }
                    });
        }
    }

    @Override
    public void onBetChoosen(Bet.BetResult aBetResult) {
        placeBet(mShownMatch, aBetResult);
    }
}
