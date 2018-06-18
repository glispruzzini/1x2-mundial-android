package it.crispybacon.mundial1x2.sections.score;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import it.crispybacon.mundial1x2.Activity1x2;
import it.crispybacon.mundial1x2.R;
import it.crispybacon.mundial1x2.core.apimodels.User;
import it.crispybacon.mundial1x2.core.user.UserApiService;

public class ScoreActivity extends Activity1x2 {

    private AppCompatTextView mTextPoints;
    private AppCompatTextView mTextPositionGlobal;
    private AppCompatTextView mTextPositionFriends;
    private AppCompatTextView mTextPlaceHolder;
    private RecyclerView mRecyclerView;

    private ScoreAdapter mScoreAdapter;

    private Disposable mScoresDisposable;
    private Disposable mUserDisposable;

    public static Intent getStartIntent(final Context context) {
        Intent startIntent = new Intent(context, ScoreActivity.class);
        return startIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mTextPoints = findViewById(R.id.text_points);
        mTextPositionGlobal = findViewById(R.id.text_global_position);
        mTextPositionFriends = findViewById(R.id.text_friends_position);
        mTextPlaceHolder = findViewById(R.id.text_placeholder);
        mRecyclerView = findViewById(R.id.recycler_scores);

        init();
    }

    @Override
    protected void init() {
        super.init();

        getUser();

        mTextPlaceHolder.setVisibility(View.GONE);

        LinearLayoutManager vLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mScoreAdapter = new ScoreAdapter(this);
        mRecyclerView.setLayoutManager(vLayoutManager);
        mRecyclerView.setAdapter(mScoreAdapter);

        getScoresList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mScoresDisposable != null && !mScoresDisposable.isDisposed()) {
            mScoresDisposable.dispose();
        }
        if (mUserDisposable != null && !mUserDisposable.isDisposed()) {
            mUserDisposable.dispose();
        }
    }

    private void getScoresList() {
        mScoresDisposable = UserApiService.get()
                .getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> scores) throws Exception {
                        onScoresReceived(scores);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showSnackBar(findViewById(R.id.view_root), throwable.getMessage());
                    }
                });
    }

    private void onScoresReceived(List<User> scores) {
        if (scores != null && scores.size() > 0) {
            mTextPlaceHolder.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);

            mScoreAdapter.updateData(scores);
        } else {
            mTextPlaceHolder.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }

    private void getUser() {
        mUserDisposable = UserApiService.get()
                .getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        //TODO: fill data eventually
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showSnackBar(findViewById(R.id.view_root), throwable.getMessage());
                    }
                });
    }
}
