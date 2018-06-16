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
import it.crispybacon.mundial1x2.core.apimodels.Bet;
import it.crispybacon.mundial1x2.ui.MeasureHelper;

/**
 * Created by itscap on 15/06/2018.
 */

public class BetSelectionView extends LinearLayout {

    private SectionView mLeftSection;
    private RoundedSection mCentralSection;
    private SectionView mRightSection;

    private IBetSelection mBetListener;
    private int mWidth;
    private int mHeight;


    public interface IBetSelection{
        void onBetChoosen(Bet.BetResult aBetResult);
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
    }

    private void init(AttributeSet attrs) {
        setOrientation(HORIZONTAL);

        setupLeftSection();
        setupCentralSection();
        setupRightSection();
    }

    private void setupLeftSection() {
        mLeftSection = new SectionView(getContext());
        mLeftSection.setId(View.generateViewId());
        mLeftSection.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mLeftSection.setBackgroundColor(getResources().getColor(R.color.colorLightBlue));
        mLeftSection.setText("1");
        mLeftSection.setTextColor(R.color.white);//TODO Porterduff
        addView(mLeftSection);

        mLeftSection.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBetListener!=null)
                    mBetListener.onBetChoosen(Bet.BetResult.HOME);
            }
        });

    }

    private void setupCentralSection() {

        mCentralSection = new RoundedSection(getContext(),50);
        mCentralSection.setId(View.generateViewId());
        mCentralSection.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        mCentralSection.setText("X");
        mCentralSection.setTextColor(R.color.white);//TODO Porterduff
        mCentralSection.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBetListener!=null)
                    mBetListener.onBetChoosen(Bet.BetResult.TIE);
            }
        });
        addView(mCentralSection);

    }

    private void setupRightSection() {
        mRightSection = new SectionView(getContext());
        mRightSection.setId(View.generateViewId());
        mRightSection.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mRightSection.setBackgroundColor(getResources().getColor(R.color.colorDarkBlue));
        mRightSection.setText("2");
        mRightSection.setTextColor(R.color.white);//TODO Porterduff
        addView(mRightSection);

        mRightSection.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBetListener!=null)
                    mBetListener.onBetChoosen(Bet.BetResult.AWAY);
            }
        });

    }

    public void setBetListener(IBetSelection aBetListener){
        mBetListener = aBetListener;
    }


    private class RoundedSection extends RelativeLayout {

        private AppCompatTextView mTextView;
        private int mSize;
        private int mTextSize;

        public RoundedSection(Context context,int sizeDp) {
            super(context);
            mSize = MeasureHelper.getPointsValue(getContext(),sizeDp);
            init();
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            mTextSize = Math.round(getMeasuredWidth() * 0.1f);
            mTextView.setTextSize(mTextSize);
        }

        private void init() {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mSize, mSize);
            //TODO: setLayoutParams(params);

            mTextView = new AppCompatTextView(getContext());
            mTextView.setId(View.generateViewId());
            RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            textParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
            mTextView.setLayoutParams(params);
            mTextView.setTypeface(ResourcesCompat.getFont(getContext(), R.font.montserrat_bold));

            this.addView(mTextView);
        }

        private RoundedSection setText(String aText) {
            mTextView.setText(aText);
            return this;
        }

        private RoundedSection setTextColor(@ColorRes int aColor) {
            mTextView.setTextColor(getContext().getResources().getColor(aColor));
            return this;
        }



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
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) this.getLayoutParams();
            params.width = width;
            setLayoutParams(params);
            return this;
        }
    }


}
