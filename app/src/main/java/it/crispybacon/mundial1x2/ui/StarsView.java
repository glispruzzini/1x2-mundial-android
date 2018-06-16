package it.crispybacon.mundial1x2.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import it.crispybacon.mundial1x2.R;

/**
 * Created by Jameido on 16/06/2018.
 */
public class StarsView extends View {

    private int mMaxStars = 5;
    private int mCurrentStars = 0;

    public StarsView(Context context) {
        super(context);
    }

    public StarsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StarsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawStars(canvas);
    }

    public void setCurrentStars(final int currentStars){
        if(currentStars > mMaxStars){
            mCurrentStars = mMaxStars;
        }else if(currentStars < 0){
            mCurrentStars = 0;
        }else {
            mCurrentStars = currentStars;
        }
        invalidate();
    }

    private void drawStars(Canvas canvas) {
        for (int i = 0; i < mMaxStars; i++) {
            boolean isEmpty = i >= mCurrentStars;
            Drawable starDrawable = ContextCompat.getDrawable(getContext(), isEmpty ? R.drawable.star_empty : R.drawable.star);

            int leftBound = canvas.getWidth() / 2 - starDrawable.getIntrinsicWidth() * mMaxStars / 2 + starDrawable.getIntrinsicWidth() * getDrawStarIndex(i);
            starDrawable.setBounds(leftBound, canvas.getHeight() - starDrawable.getIntrinsicWidth(), leftBound + starDrawable.getIntrinsicWidth(), canvas.getHeight());
            starDrawable.draw(canvas);
        }
    }

    private int getDrawStarIndex(int index) {
        boolean hasEvenStars = mMaxStars % 2 == 0; //If even max stars start from half -1

        int startIndex = mMaxStars / 2;
        if (hasEvenStars) {
            startIndex--;
        }

        if (index == 0) {
            return startIndex;
        }

        boolean toRight = index % 2 > 0;
        int shift = toRight ? index / 2 + 1 : -(index / 2);

        return startIndex + shift;
    }

}
