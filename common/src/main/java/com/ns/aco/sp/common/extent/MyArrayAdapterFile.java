package com.ns.aco.sp.common.extent;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.util.List;

import com.ns.aco.sp.common.R;

public class MyArrayAdapterFile extends MyArrayAdapter<File> {

    private int _resourceId = 0;
    private List<File> _files = null;
    private File _parentDirectory = null;
    private LayoutInflater _inflater = null;

    public MyArrayAdapterFile(Context context, int resource, List<File> files) {
        super(context, resource, R.id.textFileName, files);
        initialize(context, resource, files, null);
    }

    public MyArrayAdapterFile(Context context, int resource, List<File> files, File parentDirectory) {
        super(context, resource, R.id.textFileName, files);
        initialize(context, resource, files, parentDirectory);
    }

    private void initialize(Context context, int resource, List<File> files, File parentDirectory){
        _resourceId = resource;
        _files = files;
        _parentDirectory = parentDirectory;
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

        TextView textFileName = (TextView) root.findViewById(R.id.textFileName);
        ImageView imageFileIcon = (ImageView)root.findViewById(R.id.imageFileIcon);

        if (_textColor != null){
            textFileName.setTextColor(_textColor);
        }

        if (_backgroundColor != null){
            root.setBackgroundColor(_backgroundColor);
        }

        File file = _files.get(position);

        if (file == _parentDirectory){
            textFileName.setText("..");
        }else{
            textFileName.setText(file.getName());
        }

        setThumbnail(file, imageFileIcon);
        return root;
    }

    private void setThumbnail(File file, ImageView imageView){
        if (file.isDirectory()){
            imageView.setImageResource(R.drawable.directory);
        }else{
            imageView.setImageResource(R.drawable.file);
        }
    }
}
