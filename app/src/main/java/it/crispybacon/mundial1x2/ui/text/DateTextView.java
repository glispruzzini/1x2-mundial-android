package it.crispybacon.mundial1x2.ui.text;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import it.crispybacon.mundial1x2.R;
import it.crispybacon.mundial1x2.ui.MeasureHelper;


/**
 * Created by Enrico Cappozzo on 15/06/2018.
 */

public class DateTextView extends LinearLayout {

    private AppCompatTextView mDayTextView;
    private AppCompatTextView mDateTextView;

    private SimpleDateFormat mSimpleDateFormat;

    public DateTextView(Context context) {
        super(context);
        init();
    }

    public DateTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {

        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        mSimpleDateFormat = new SimpleDateFormat("dd / MM", Locale.ITALY);

        setupDay();
        setupDate();
    }


    private void setupDay() {
        mDayTextView = new AppCompatTextView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        mDayTextView.setLayoutParams(params);
        mDayTextView.setTypeface(ResourcesCompat.getFont(getContext(), R.font.montserrat_regular));

        addView(mDayTextView);
    }

    private void setupDate() {
        mDateTextView = new AppCompatTextView(getContext());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.topMargin = (int) MeasureHelper.getPointsValueOfRes(getContext(),R.dimen.small_margin)/2;
        mDateTextView.setLayoutParams(params);
        mDateTextView.setTypeface(ResourcesCompat.getFont(getContext(), R.font.montserrat_regular));

        addView(mDateTextView);
    }

    public void setDate(Date aDate){
        //TODO
        mDayTextView.setText("Day".toUpperCase());
        mDateTextView.setText(mSimpleDateFormat.format(aDate));
    }

}
