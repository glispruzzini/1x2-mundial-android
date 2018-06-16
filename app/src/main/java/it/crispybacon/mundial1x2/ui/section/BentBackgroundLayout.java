package it.crispybacon.mundial1x2.ui.section;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import it.crispybacon.mundial1x2.R;
import it.crispybacon.mundial1x2.ui.MeasureHelper;

/**
 * Created by itscap on 15/06/2018.
 */

public class BentBackgroundLayout extends RelativeLayout {

    private static final String TAG = "BentBackgroundLayout";

    private Paint mFillerPaint;
    private int mWidth;
    private int mHeight;
    private int mArcHeight;

    public BentBackgroundLayout(Context context) {
        super(context);
        init(null);
    }

    public BentBackgroundLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(
                -mArcHeight/2,
                0,
                mWidth + mArcHeight/2,
                mArcHeight,
                180,
                180,
                true,
                mFillerPaint
        );
        canvas.drawRect(0,mArcHeight/2, mWidth,mHeight,mFillerPaint);
    }

    private void init(AttributeSet attrs) {

        setWillNotDraw(false);
        mArcHeight = MeasureHelper.getPointsValue(getContext(), 120);

        mFillerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFillerPaint.setColor(getResources().getColor(R.color.white));

    }


}
