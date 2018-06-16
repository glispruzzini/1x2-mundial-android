package it.crispybacon.mundial1x2.sections.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import it.crispybacon.mundial1x2.Activity1x2;
import it.crispybacon.mundial1x2.R;
import it.crispybacon.mundial1x2.core.apimodels.Bet;
import it.crispybacon.mundial1x2.core.apimodels.Match;
import it.crispybacon.mundial1x2.core.apimodels.SimpleResponse;
import it.crispybacon.mundial1x2.core.apimodels.User;
import it.crispybacon.mundial1x2.core.authentication.Authentication;
import it.crispybacon.mundial1x2.core.bets.BetsApiService;
import it.crispybacon.mundial1x2.core.macthes.MatchesApiService;
import it.crispybacon.mundial1x2.ui.StarsView;
import it.crispybacon.mundial1x2.ui.section.BentBackgroundLayout;
import it.crispybacon.mundial1x2.ui.selector.BetSelectionView;

public class HomeActivity extends Activity1x2 implements BetSelectionView.IBetSelection,

        MatchesAdapter.OnItemClickListener {

    private static final String TAG = "HomeActivity";

    public static Intent getStartIntent(final Context context) {
        Intent startIntent = new Intent(context, HomeActivity.class);
        return startIntent;
    }

    private BentBackgroundLayout mBentBackgroundLayout;

    private RecyclerView mRecyclerView;
    private MatchesAdapter mMatchesAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private StarsView mStarsLives;

    private BetSelectionView mBetSelectionView;

    private Disposable mDisposableMatches;
    private Disposable mDisposableBet;
    private Disposable mDisposableUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mBentBackgroundLayout = findViewById(R.id.bottom_container);
        mBetSelectionView = findViewById(R.id.bet_selection_view);
        mRecyclerView = findViewById(R.id.rv_matches);
        mStarsLives = findViewById(R.id.view_stars);

        init();

        mBetSelectionView.setBetListener(this);
    }


    @Override
    protected void init() {
        super.init();
        getUser();

        mMatchesAdapter = new MatchesAdapter(this);
        mMatchesAdapter.setOnItemClickListener(this);
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
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
        if (mDisposableUser != null && !mDisposableUser.isDisposed()) {
            mDisposableUser.dispose();
        }
    }

    private void getUser() {
        mDisposableUser = Authentication.get()
                .getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) {
                        mStarsLives.setCurrentStars(user.life);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        mStarsLives.setCurrentStars(0);
                        showSnackBar(findViewById(R.id.view_root), throwable.getMessage());
                    }
                });
    }

    private void getMatches() {
        showLoadingDialog();
        mDisposableMatches = MatchesApiService.get()
                .getMatches()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Match>>() {
                    @Override
                    public void accept(List<Match> matches) {
                        onMatchesLoaded(matches);
                        hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
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
                        public void accept(SimpleResponse response) {
                            //Refresh the stars count
                            getUser();
                            showSnackBar(findViewById(R.id.view_root), getString(R.string.bet_placed, betResult.toString()));
                            hideLoadingDialog();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) {
                            showSnackBar(findViewById(R.id.view_root), throwable.getMessage());
                            hideLoadingDialog();
                        }
                    });
        }
    }

    @Override
    public void onBetChosen(Bet.BetResult aBetResult) {
        Match vShownMatch = mMatchesAdapter.getMatch(mLinearLayoutManager.findFirstCompletelyVisibleItemPosition());
        placeBet(vShownMatch, aBetResult);
    }

    @Override
    public void onMatchClicked(Match aMatch) {
        Log.d(TAG, "onMatchClicked: " + aMatch);
    }

}
