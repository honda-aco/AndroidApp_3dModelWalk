package com.ns.aco.sp.common.extent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.ns.aco.sp.common.util.UtilityView;

/**
 * Created by ns on 2016/12/18.
 */
public class MyNumberPicker extends NumberPicker {

    public MyNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        updateView(child);
    }

    @Override
    public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }

    @Override
    public void addView(View child, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, params);
        updateView(child);
    }

    private void updateView(View view) {
        if(view instanceof EditText){
            UtilityView utilityView = new UtilityView(getContext());
            ((EditText) view).setTextSize(utilityView.convertDpToPixel(7));
        }
    }
}
