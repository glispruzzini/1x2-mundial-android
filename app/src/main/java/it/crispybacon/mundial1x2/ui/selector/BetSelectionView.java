package it.crispybacon.mundial1x2.ui.selector;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import it.crispybacon.mundial1x2.R;
import it.crispybacon.mundial1x2.ui.MeasureHelper;

/**
 * Created by Enrico Cappozzo on 15/06/2018.
 */

public class BetSelectionView extends LinearLayout {

    private SectionView mLeftSection;
    private SectionView mCentralSection;
    private SectionView mRightSection;

    private int mSectionWidth;

    public BetSelectionView(Context context) {
        super(context);
        init(null);
    }

    public BetSelectionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mSectionWidth = getMeasuredWidth()/3;

        mLeftSection.setWidth(mSectionWidth);
        mCentralSection.setWidth(mSectionWidth);
        mRightSection.setWidth(mSectionWidth);
    }

    private void init(AttributeSet attrs){

        setOrientation(HORIZONTAL);

        setupLeftSection();
        setupCentralSection();
        setupRightSection();
    }

    private void setupLeftSection(){
        mLeftSection = new SectionView(getContext());
        mLeftSection.setId(View.generateViewId());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_START,RelativeLayout.TRUE);
        mLeftSection.setLayoutParams(params);
        //TODO get background res
        mLeftSection.setBackground(getResources().getDrawable(R.drawable.flag_russia));
        addView(mLeftSection);

    }

    private void setupCentralSection(){
        mCentralSection = new SectionView(getContext());
        mCentralSection.setId(View.generateViewId());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.END_OF,mLeftSection.getId());
        mCentralSection.setLayoutParams(params);
        mCentralSection.setText("X");
        mCentralSection.setTextColor(R.color.white);
        //TODO get background res
        mCentralSection.setBackground(getResources().getDrawable(R.drawable.flag_russia));

        addView(mCentralSection);

    }

    private void setupRightSection(){
        mRightSection = new SectionView(getContext());
        mRightSection.setId(View.generateViewId());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.END_OF,mCentralSection.getId());
        mRightSection.setLayoutParams(params);
        //TODO get background res
        mRightSection.setBackground(getResources().getDrawable(R.drawable.flag_russia));

        addView(mRightSection);

    }


    private class SectionView extends RelativeLayout {

        private AppCompatTextView mTextView;
        private int mTextSize;

        public SectionView(Context context) {
            super(context);
            init();
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            mTextSize = Math.round(getMeasuredWidth()*0.15f);
            mTextView.setTextSize(mTextSize);
        }

        private void init(){

            mTextView = new AppCompatTextView(getContext());
            mTextView.setId(View.generateViewId());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
            mTextView.setLayoutParams(params);
            mTextView.setTypeface(ResourcesCompat.getFont(getContext(), R.font.montserrat_bold));

            this.addView(mTextView);
        }

        private SectionView setText(String aText){
            mTextView.setText(aText);
            return this;
        }

        private SectionView setTextColor(@ColorRes int aColor){
            mTextView.setTextColor(getContext().getResources().getColor(aColor));
            return this;
        }

        private SectionView setWidth(int width){
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) this.getLayoutParams();
            params.width = width;
            setLayoutParams(params);
            return this;
        }
    }

}
