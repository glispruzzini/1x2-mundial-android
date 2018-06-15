package it.crispybacon.mundial1x2.ui.imageview;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import it.crispybacon.mundial1x2.R;
import it.crispybacon.mundial1x2.ui.MeasureHelper;

/**
 * Created by Enrico Cappozzo on 15/06/2018.
 */

public class FlagImageView extends LinearLayout {

    private AppCompatImageView mImageView;
    private AppCompatTextView mTextView;

    public FlagImageView(Context context) {
        super(context);
        init();
    }

    public FlagImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {

        setOrientation(VERTICAL);

        setupImageView();
        setupTextView();
    }

    private void setupImageView() {
        mImageView = new AppCompatImageView(getContext());
        int width = (int) getResources().getDimension(R.dimen.icon_flag_width);
        int height = (int) getResources().getDimension(R.dimen.icon_flag_height);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        mImageView.setLayoutParams(params);

        addView(mImageView);
    }

    private void setupTextView() {
        mTextView = new AppCompatTextView(getContext());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            params.topMargin = (int) MeasureHelper.getPointsValueOfRes(getContext(),R.dimen.smaller_margin);
        mTextView.setLayoutParams(params);

        addView(mTextView);
    }

    public FlagImageView withFlag(@DrawableRes int aFlagDrawable){
        mImageView.setImageDrawable(getResources().getDrawable(aFlagDrawable));
        return this;
    }

    public FlagImageView andText(String aString){
        mTextView.setText(aString.toUpperCase());
        return this;
    }



}
