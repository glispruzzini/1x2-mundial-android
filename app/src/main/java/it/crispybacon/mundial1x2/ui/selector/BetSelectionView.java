package it.crispybacon.mundial1x2.ui.selector;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
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

    public BetSelectionView(Context context) {
        super(context);
        init(null);
    }

    public BetSelectionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
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

        public SectionView(Context context) {
            super(context);
            init();
        }

        private void init(){

            mTextView = new AppCompatTextView(getContext());
            mTextView.setId(View.generateViewId());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
            mTextView.setLayoutParams(params);
            mTextView.setTextSize(MeasureHelper.getPointsValue(getContext(),50));
            mTextView.setTypeface(ResourcesCompat.getFont(getContext(), R.font.montserrat_bold));

            this.addView(mTextView);
        }

        private void setText(String aText){
            mTextView.setText(aText);
        }
    }

}
