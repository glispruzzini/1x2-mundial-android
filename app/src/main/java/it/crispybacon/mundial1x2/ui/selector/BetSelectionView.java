package it.crispybacon.mundial1x2.ui.selector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import it.crispybacon.mundial1x2.R;
import it.crispybacon.mundial1x2.core.apimodels.Bet;

/**
 * Created by itscap on 15/06/2018.
 */

public class BetSelectionView extends RelativeLayout {

    private SectionView mLeftSection;
    private CircularButton mCentralSection;
    private SectionView mRightSection;

    private IBetSelection mBetListener;
    private int mWidth;
    private int mHeight;


    public interface IBetSelection{
        void onBetChosen(Bet.BetResult aBetResult);
    }


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

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mLeftSection.setWidth(mWidth/2);
        mRightSection.setWidth(mWidth/2);
        mCentralSection.setSize(mHeight/2);
    }

    private void init(AttributeSet attrs) {

        setupLeftSection();
        setupRightSection();
        setupCentralSection();
    }

    private void setupLeftSection() {
        mLeftSection = new SectionView(getContext());
        mLeftSection.setId(View.generateViewId());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(ALIGN_PARENT_START,TRUE);
        mLeftSection.setLayoutParams(params);
        mLeftSection.setBackgroundColor(getResources().getColor(R.color.colorLightBlue));
        mLeftSection.setText("1");
        mLeftSection.setTextColor(R.color.white);//TODO Porterduff
        addView(mLeftSection);

        mLeftSection.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBetListener!=null)
                    mBetListener.onBetChosen(Bet.BetResult.HOME);
            }
        });

    }

    private void setupCentralSection() {

        mCentralSection = new CircularButton(getContext());
        mCentralSection.setId(View.generateViewId());
        mCentralSection.setText("X");
        mCentralSection.setTextColor(R.color.white);//TODO Porterduff
        mCentralSection.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBetListener!=null)
                    mBetListener.onBetChosen(Bet.BetResult.TIE);
            }
        });
        addView(mCentralSection);

    }

    private void setupRightSection() {
        mRightSection = new SectionView(getContext());
        mRightSection.setId(View.generateViewId());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(ALIGN_PARENT_END,TRUE);
        mRightSection.setLayoutParams(params);
        mRightSection.setBackgroundColor(getResources().getColor(R.color.colorDarkBlue));
        mRightSection.setText("2");
        mRightSection.setTextColor(R.color.white);//TODO Porterduff
        addView(mRightSection);

        mRightSection.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBetListener!=null)
                    mBetListener.onBetChosen(Bet.BetResult.AWAY);
            }
        });

    }

    public void setBetListener(IBetSelection aBetListener){
        mBetListener = aBetListener;
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

            mTextSize = Math.round(getMeasuredWidth() * 0.12f);
            mTextView.setTextSize(mTextSize);
        }

        private void init() {

            BetSelectionView.this.setWillNotDraw(false);

            mTextView = new AppCompatTextView(getContext());
            mTextView.setId(View.generateViewId());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
            mTextView.setLayoutParams(params);
            mTextView.setTypeface(ResourcesCompat.getFont(getContext(), R.font.montserrat_bold));

            this.addView(mTextView);
        }

        private SectionView setText(String aText) {
            mTextView.setText(aText);
            return this;
        }

        private SectionView setTextColor(@ColorRes int aColor) {
            mTextView.setTextColor(getContext().getResources().getColor(aColor));
            return this;
        }

        private SectionView setWidth(int width) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) this.getLayoutParams();
            params.width = width;
            setLayoutParams(params);
            return this;
        }
    }


}
