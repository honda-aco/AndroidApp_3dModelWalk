package com.ns.aco.sp.common.extent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ns.aco.sp.common.R;

import java.util.List;

public class MyArrayAdapterResources extends MyArrayAdapter<Integer> {

    private int _resourceId = 0;
    private List<Integer> _resIdList = null;
    private LayoutInflater _inflater = null;

    public MyArrayAdapterResources(Context context, int resource, List<Integer> resIdList) {
        super(context, resource, R.id.textFileName, resIdList);
        initialize(context, resource, resIdList);
    }

    private void initialize(Context context, int resource, List<Integer> resIdList){
        _resourceId = resource;
        _resIdList = resIdList;
        _inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root;
        if (convertView != null) {
            root = convertView;
        } else {
            root = this._inflater.inflate(this._resourceId, null);
        }

        if (_backgroundColor != null){
            root.setBackgroundColor(_backgroundColor);
        }

        ImageView imageFileIcon = (ImageView)root.findViewById(R.id.imageFileIcon);
        imageFileIcon.setImageResource(_resIdList.get(position));

        return root;
    }
}
