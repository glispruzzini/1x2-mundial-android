package it.crispybacon.mundial1x2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import it.crispybacon.mundial1x2.core.apimodels.Match;
import it.crispybacon.mundial1x2.core.macthes.MatchesApiService;
import it.crispybacon.mundial1x2.ui.imageview.FlagImageView;
import it.crispybacon.mundial1x2.ui.text.DateTextView;

public class HomeActivity extends Activity1x2 {

    public static Intent getStartIntent(final Context context) {
        Intent startIntent = new Intent(context, HomeActivity.class);
        return startIntent;
    }

    private FlagImageView mFlagImageLeft;
    private FlagImageView mFlagImageRight;
    private DateTextView mDateTextView;
    private AppCompatTextView mHourTextView;

    private Disposable mDisposableMatches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mFlagImageLeft = findViewById(R.id.img_left_team);
        mFlagImageRight = findViewById(R.id.img_right_team);
        mDateTextView = findViewById(R.id.text_date);
        mHourTextView = findViewById(R.id.text_hour);

        getMatches();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposableMatches != null && !mDisposableMatches.isDisposed()) {
            mDisposableMatches.dispose();
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
            Match vTestMatch = matches.get(0);
            mFlagImageLeft.withFlag(R.drawable.flag_russia)
                    .andText(vTestMatch.team1.name);

            mFlagImageRight.withFlag(R.drawable.flag_russia)
                    .andText(vTestMatch.team2.name);

            mDateTextView.setDate(vTestMatch.date);
            SimpleDateFormat vSimpleDateFormat = new SimpleDateFormat("HH:mm", Locale.ITALY);
            mHourTextView.setText(vSimpleDateFormat.format(vTestMatch.date));
        } else {
            //TODO: show placeholder
        }
    }
}
