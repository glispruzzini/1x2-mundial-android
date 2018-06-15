package it.crispybacon.mundial1x2.ui.section;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import it.crispybacon.mundial1x2.R;
import it.crispybacon.mundial1x2.ui.MeasureHelper;

/**
 * Created by Enrico Cappozzo on 15/06/2018.
 */

public class BentBackgroundLayout extends RelativeLayout {

    private static final String TAG = "BentBackgroundLayout";

    private Paint mFillerPaint;
    private Path mFillPath;
    private Point mTopLeft;
    private Point mTopRight;
    private Point mBottomRight;
    private Point mBottomLeft;
    private int mWidth;
    private int mHeight;
    private int mArcHeight;
    private int translationY;

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
        /*
        initPoints(canvas);
        mFillPath.reset();
        mFillPath.moveTo(mTopLeft.x, mTopLeft.y);
        mFillPath.lineTo(mTopRight.x, mTopRight.y);
        mFillPath.lineTo(mBottomRight.x, mBottomRight.y);
        mFillPath.lineTo(mBottomLeft.x, mBottomLeft.y);
        mFillPath.close();

        canvas.drawPath(mFillPath, mFillerPaint);
        */
    }

    private void init(AttributeSet attrs) {

        setWillNotDraw(false);
        mArcHeight = MeasureHelper.getPointsValue(getContext(), 120);
        translationY = mArcHeight/4;

        mFillerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFillerPaint.setColor(getResources().getColor(R.color.white));

        setTranslationY(-translationY);
        mFillPath = new Path();
        mTopLeft = new Point();
        mTopRight = new Point();
        mBottomRight = new Point();
        mBottomLeft = new Point();

    }

    private void initPoints(Canvas canvas) {

        mTopLeft.set(0,mArcHeight/2);
        mTopRight.set(canvas.getWidth(), mArcHeight/2);
        mBottomRight.set(canvas.getWidth(), canvas.getHeight()+mArcHeight/2);
        mBottomLeft.set(0, canvas.getHeight()+mArcHeight/2);
    }

    public void setupPosition(){

        setTranslationY(-translationY);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
        params.height = mHeight+translationY;
        setLayoutParams(params);

        invalidate();
    }
}
