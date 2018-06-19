package it.crispybacon.mundial1x2.ui.selector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorRes;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import it.crispybacon.mundial1x2.R;


/**
 * Created by Enrico Cappozzo on 19/06/2018.
 */

public class CircularButton extends RelativeLayout {

    private AppCompatTextView mTextView;
    protected int mButtonSize;
    protected Paint mBackgroundFillerPaint;


    public CircularButton(Context context) {
        super(context);
        mButtonSize = 0;

        setWillNotDraw(false);
        setClipToPadding(false);
        setClipToOutline(false);

        mBackgroundFillerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundFillerPaint.setColor(getResources().getColor(R.color.colorAccent));

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(CENTER_IN_PARENT,TRUE);
        setLayoutParams(params);

        initTextView();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mButtonSize = getMeasuredHeight();
        //mTextView.setTextSize(mButtonSize/4);
        //setupTextView(mButtonSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBaseBackgroundCircle(canvas);
        super.onDraw(canvas);
    }

    protected void drawBaseBackgroundCircle(Canvas canvas){
        canvas.drawCircle(mButtonSize / 2, mButtonSize / 2, mButtonSize / 2, mBackgroundFillerPaint);
    }

    private void initTextView(){
        mTextView = new AppCompatTextView(getContext());
        mTextView.setId(View.generateViewId());
        mTextView.setTypeface(ResourcesCompat.getFont(getContext(), R.font.montserrat_bold));
        RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams.addRule(TEXT_ALIGNMENT_CENTER,TRUE);
        textParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        mTextView.setLayoutParams(textParams);
        addView(mTextView);
    }

    private void setupTextView(int size){

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
        params.width = size;
        params.height = size;
        mTextView.setLayoutParams(params);
    }


    public void setSize(int size){
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
            params.width = size;
            params.height = size;
        setLayoutParams(params);
        requestLayout();
    }

    public void setText(String aText) {
        mTextView.setText(aText);
    }

    public void setTextColor(@ColorRes int aColor) {
        mTextView.setTextColor(getContext().getResources().getColor(aColor));
    }

}

