package it.crispybacon.mundial1x2.ui.imageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import it.crispybacon.mundial1x2.R;

/**
 * Created by Enrico Cappozzo on 15/06/2018.
 */


public class RoundedImageView extends AppCompatImageView {

    private int DEF_BORDER_SIZE = 5;

    private Path mPath;
    private RectF mRect;
    protected Paint mBorderPaint;
    @ColorInt
    private int mBorderColor = Color.LTGRAY;
    protected int mBorderSize = DEF_BORDER_SIZE;

    public RoundedImageView(Context context) {
        super(context);
        init();
    }

    public RoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttrs(attrs, 0);
        init();
    }

    public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseAttrs(attrs, defStyle);
        init();
    }

    /**
     * Parses all the styleable attributes of the control and assigns te values to
     * the dedicated variables
     *
     * @param attrs
     * @param defStyleAttr
     */
    private void parseAttrs(AttributeSet attrs, int defStyleAttr) {
        TypedArray vTypedArray = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.RoundedImageView,
                defStyleAttr, 0);
        try {
            int colorNormalResId = vTypedArray.getResourceId(R.styleable.RoundedImageView_borderColor, 0);
            if (colorNormalResId != 0)
                mBorderColor = ContextCompat.getColor(getContext(), colorNormalResId);
            else
                mBorderColor = vTypedArray.getColor(R.styleable.RoundedImageView_borderColor, Color.LTGRAY);

            mBorderSize = vTypedArray.getDimensionPixelSize(R.styleable.RoundedImageView_borderSize, DEF_BORDER_SIZE);

        } finally {
            vTypedArray.recycle();
        }
    }

    protected void init() {
        mPath = new Path();
        mBorderPaint = initBorderPant();
        mRect = new RectF(0, 0, getWidth(), getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mRect.set(mBorderSize / 2,
                mBorderSize / 2,
                canvas.getWidth() - mBorderSize / 2,
                canvas.getHeight() - mBorderSize / 2
        );
        mPath.addRoundRect(mRect,
                canvas.getWidth() / 2,
                canvas.getHeight() / 2,
                Path.Direction.CW
        );
        canvas.clipPath(mPath);
        super.onDraw(canvas);
        drawBorder(canvas);
    }

    protected Paint initBorderPant() {
        Paint border = new Paint();
            border.setColor(mBorderColor);
            border.setStyle(Paint.Style.STROKE);
            border.setAntiAlias(true);
            border.setStrokeWidth(mBorderSize);
        return border;

    }

    protected void drawBorder(Canvas canvas) {
        canvas.drawCircle(canvas.getWidth() / 2,
                canvas.getHeight() / 2,
                (canvas.getHeight() / 2f) - mBorderSize, mBorderPaint
        );
    }

    public void setBorderColor(int borderColor) {
        mBorderColor = borderColor;
    }
}

