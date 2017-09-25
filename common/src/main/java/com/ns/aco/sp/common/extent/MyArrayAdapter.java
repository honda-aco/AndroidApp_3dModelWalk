package com.ns.aco.sp.common.extent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.ArrayAdapter;

import java.util.List;

public class MyArrayAdapter<T> extends ArrayAdapter<T> {

    protected Integer _textColor = null;
    protected Integer _backgroundColor = null;

    public MyArrayAdapter(Context context, int resource) {
        super(context, resource);
    }

    public MyArrayAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public MyArrayAdapter(Context context, int resource, T[] objects) {
        super(context, resource, objects);
    }

    public MyArrayAdapter(Context context, int resource, int textViewResourceId, T[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public MyArrayAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
    }

    public MyArrayAdapter(Context context, int resource, int textViewResourceId, List<T> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public void setTextColor(int color){
        _textColor = color;
    }

    public void setBackgroundColor(int color){
        _backgroundColor = color;
    }
}
