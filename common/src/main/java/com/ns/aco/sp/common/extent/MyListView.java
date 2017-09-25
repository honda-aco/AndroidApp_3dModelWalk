package com.ns.aco.sp.common.extent;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

// Android4.0でListViewのOnItemClickListenerが動作しないことがあるため、力技で対処する
public class MyListView extends ListView {

    private OnItemClickListener _onItemClicklistener = null;

    public MyListView(Context context) {
        this(context, null);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
    }

    public void setAdapter(MyArrayAdapterResolveInfo adapter) {
        super.setAdapter(adapter);
        if (adapter != null){
            adapter.setParentListView(this);
        }
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener listener) {
         super.setOnItemClickListener(listener);
        _onItemClicklistener = listener;
    }

    public void onItemClick(View view, int position, long id){
        _onItemClicklistener.onItemClick(this, view, position, id);
    }
}
