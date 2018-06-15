package it.crispybacon.mundial1x2.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by Enrico Cappozzo on 15/06/2018.
 */

public class MeasureHelper {

    private Context mContext;


    public MeasureHelper(Context context) {
        mContext = context;
    }

    public static int getPointsValue(Context context, float dp) {
        return getPointsValue(context.getResources(), dp);
    }

    public static int getPointsValue(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        int pointsFromDp = (int) (dp * scale);
        return pointsFromDp;
    }

    public static float getPointsValueOfRes(Context context, @DimenRes int dimenRes) {
        return context.getResources().getDimension(dimenRes);
    }

    public int getPointsValue(float dp) {

        final float scale = mContext.getResources().getDisplayMetrics().density;
        int pointsFromDp = (int) (dp * scale);
        return pointsFromDp;
    }

    public int getDpValue(float points) {

        final float scale = mContext.getResources().getDisplayMetrics().density;
        int dpFromPoints = (int) (points / scale);
        return dpFromPoints;
    }

    static public int[] getLocationRelativeToParentView(View parentView, View view) {

        Rect offsetViewBounds = new Rect();
        //returns the visible bounds
        view.getDrawingRect(offsetViewBounds);
        // calculates the relative coordinates to the parent
        ((ViewGroup) parentView).offsetDescendantRectToMyCoords(view, offsetViewBounds);

        int relativeLeft = offsetViewBounds.left;
        int relativeTop = offsetViewBounds.top;
        int relativeRight = offsetViewBounds.right;
        int relativeBottom = offsetViewBounds.bottom;

        return new int[]{relativeLeft, relativeTop, relativeRight, relativeBottom};
    }

    static public int getAbsoluteXLocation(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return location[0];
    }

    static public int getAbsoluteYLocation(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return location[1];
    }


    public String getScreenDensity() {

        int dpi = mContext.getResources().getDisplayMetrics().densityDpi;
        String density = String.valueOf(dpi);
        switch (dpi) {
            case DisplayMetrics.DENSITY_LOW:
                //ldpi
                density = "DENSITY_LOW ldpi - " + dpi;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                //mdpi
                density = "DENSITY_MEDIUM mdpi - " + dpi;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                //hdpi
                density = "DENSITY_HIGH hdpi - " + dpi;
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                //xhdpi
                density = "DENSITY_XHIGH: xhdpi - " + dpi;
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                //xxhdpi
                density = "DENSITY_XHIGH: xxhdpi - " + dpi;
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                //xxxhdpi
                density = "DENSITY_XHIGH: xxxhdpi - " + dpi;
                break;
        }

        return density;
    }

    public static int getScreenWidth(Context context) {

        int deviceWidth;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        deviceWidth = size.x;

        return deviceWidth;
    }

    public static int getScreenHeight(Context context) {

        int deviceHeight;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        deviceHeight = size.y;

        return deviceHeight;
    }

    /**
     * @param view the view you want to measure
     * @return view's width and height
     */
    public static int[] getViewMeasureBeforeRender(Context context, View view) {

        int deviceWidth = getScreenWidth(context);

        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
        return new int[]{view.getMeasuredWidth(), view.getMeasuredHeight()};
    }
}
