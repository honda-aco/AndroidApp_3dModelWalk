package com.ns.aco.sp.common.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

/**
 * Created by ns on 2016/12/18.
 */
public class UtilityView {

    private final Context _context;

    public UtilityView(Context context){
        _context = context;
    }

    public float convertDpToPixel(float dp){
        DisplayMetrics metrics = _context.getResources().getDisplayMetrics();
        return dp * metrics.density;
    }

    public float convertPixelToDp(int px){
        DisplayMetrics metrics = _context.getResources().getDisplayMetrics();
        return px / metrics.density;
    }

    public static int getStatusBarHeight(Activity activity){
        final Rect rect = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    public static int getCoordinateX_topLeft(int windowWidth, int viewWidth){
        return (-1) * (windowWidth / 2) + (viewWidth / 2);
    }

    public static int getCoordinateY_topLeft(int windowHeight, int viewHeight){
        return (-1) * (windowHeight / 2) + (viewHeight / 2);
    }

    public static int getCoordinateX_topRight(int windowWidth, int viewWidth){
        return (windowWidth / 2) - (viewWidth / 2);
    }

    public static int getCoordinateY_topRight(int windowHeight, int viewHeight){
        return (-1) * (windowHeight / 2) + (viewHeight / 2);
    }

    public static int getCoordinateX_bottomLeft(int windowWidth, int viewWidth){
        return (-1) * (windowWidth / 2) + (viewWidth / 2);
    }

    public static int getCoordinateY_bottomLeft(int windowHeight, int viewHeight){
        return (windowHeight / 2) - (viewHeight / 2);
    }

    public static int getCoordinateX_bottomRight(int windowWidth, int viewWidth){
        return  (windowWidth / 2) - (viewWidth / 2);
    }

    public static int getCoordinateY_bottomRight(int windowHeight, int viewHeight){
        return (windowHeight / 2) - (viewHeight / 2);
    }

    public static void changeBackgroundColor(final View targetView,
                                                final int plusA,
                                                final int plusR,
                                                final int plusG,
                                                final int plusB){
        //ColorDrawableから色を取得・文字列に変換
        ColorDrawable backgroundColor = (ColorDrawable) targetView.getBackground();
        String colorString = "#" + Integer.toHexString(backgroundColor.getColor());
        //色（文字列）をColorクラスでint型に変換
        float[] hsv = new float[3];
        int color = Color.parseColor(colorString);
        int alpha = plusA + Color.alpha(color);
        int red = plusR + Color.red(color);
        int green = plusG + Color.green(color);
        int blue = plusB + Color.blue(color);
        targetView.setBackgroundColor(Color.argb(alpha, red, green, blue));
    }

    public static void setScaleAmimation(final View targetView, final ScaleAnimation scaleAnima, final long duration) {
        AnimationSet animaSet = new AnimationSet(true);
        animaSet.addAnimation(scaleAnima);
        animaSet.setDuration(duration);
        targetView.startAnimation(animaSet);
    }
}
