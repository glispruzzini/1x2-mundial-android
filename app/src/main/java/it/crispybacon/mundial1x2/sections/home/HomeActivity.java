package it.crispybacon.mundial1x2.sections.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.MenuItem;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import it.crispybacon.mundial1x2.Activity1x2;
import it.crispybacon.mundial1x2.R;
import it.crispybacon.mundial1x2.core.apimodels.Bet;
import it.crispybacon.mundial1x2.core.apimodels.Match;
import it.crispybacon.mundial1x2.core.apimodels.SimpleResponse;
import it.crispybacon.mundial1x2.core.bets.BetsApiService;
import it.crispybacon.mundial1x2.core.macthes.MatchesApiService;
import it.crispybacon.mundial1x2.sections.results.ResultsActivity;
import it.crispybacon.mundial1x2.ui.imageview.FlagImageView;
import it.crispybacon.mundial1x2.ui.section.BentBackgroundLayout;
import it.crispybacon.mundial1x2.ui.selector.BetSelectionView;
import it.crispybacon.mundial1x2.ui.text.DateTextView;

public class HomeActivity extends Activity1x2 implements BetSelectionView.IBetSelection,
    MatchesAdapter.OnItemClickListener {

    public static Intent getStartIntent(final Context context) {
        Intent startIntent = new Intent(context, HomeActivity.class);
        return startIntent;
    }


    private BentBackgroundLayout mBentBackgroundLayout;

    private RecyclerView mRecyclerView;
    private MatchesAdapter mMatchesAdapter;


    private static final String TAG = "HomeActivity";
    private BetSelectionView mBetSelectionView;

    private Disposable mDisposableMatches;
    private Disposable mDisposableBet;

    private Match mShownMatch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mBentBackgroundLayout = findViewById(R.id.bottom_container);
        mBetSelectionView = findViewById(R.id.bet_selection_view);
        mRecyclerView = findViewById(R.id.rv_matches);

        init();

        mBetSelectionView.setBetListener(this);
    }

    @Override
    protected void init() {
        super.init();

        mMatchesAdapter = new MatchesAdapter(this);
        mMatchesAdapter.setOnItemClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(mMatchesAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        new PagerSnapHelper().attachToRecyclerView(mRecyclerView);


        getMatches();
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
            mMatchesAdapter.updateData(matches);
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

    @Override
    public void onMatchClicked(Match aMatch) {
        Log.d(TAG, "onMatchClicked: "+aMatch);
    }

}
