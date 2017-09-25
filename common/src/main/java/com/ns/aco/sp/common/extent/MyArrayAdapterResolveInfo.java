package com.ns.aco.sp.common.extent;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ns.aco.sp.common.R;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;

public class MyArrayAdapterResolveInfo extends MyArrayAdapter<ResolveInfo> {

    private MyListView _parentListView = null;
    private int _resourceId = 0;
    private List<ResolveInfo> _resolveInfoList = null;
    private LayoutInflater _inflater = null;
    private PackageManager _packageManager = null;
    private List<Integer> _positionList = null;
    private boolean _existCheckbox = false;
    private List<ResolveInfo> _selectedPackageList = null;
    private Dictionary<String, Boolean> _selectedItemDic = null;
    private final int _tag_position = 0;
//    private final int _tag_id = 1;

    public void setSelectedPackageList(List<ResolveInfo> selectedPackageList){
        _selectedPackageList = selectedPackageList;
        _selectedItemDic = new Hashtable<String, Boolean>();

        for (ResolveInfo resolveInfo : _resolveInfoList) {
            for (int i = 0; i < _selectedPackageList.size(); i++){
                if (_selectedPackageList.get(i).activityInfo.name.equals(resolveInfo.activityInfo.name)) {
                    _selectedItemDic.put(resolveInfo.activityInfo.name, true);
                }
            }
        }
    }

    public MyArrayAdapterResolveInfo(Context context, int resource, List<ResolveInfo> resolveInfoList, PackageManager packageManager) {
        super(context, resource, R.id.textFileName, resolveInfoList);

        if (resource == R.layout.listview_check_item){
            _existCheckbox = true;
        }

        _positionList = new ArrayList<Integer>();
        _resourceId = resource;
        _resolveInfoList = resolveInfoList;
        _inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _packageManager = packageManager;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root;
        if (convertView != null) {
            root = convertView;
        } else {
            root = this._inflater.inflate(this._resourceId, null);
        }

        root.setTag(position);
        root.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LinearLayout linearLayout = (LinearLayout) v;
                        for (int i = 0; i < linearLayout.getChildCount(); i++){
                            if (linearLayout.getChildAt(i).getId() == R.id.textFileName){
                                if (_parentListView != null){
                                    _parentListView.onItemClick(v, (int)v.getTag(), v.getId());
                                }
                            }
                        }
                    }
                }
        );

        TextView textFileName = (TextView) root.findViewById(R.id.textFileName);
        ImageView imageFileIcon = (ImageView)root.findViewById(R.id.imageFileIcon);

        if (_textColor != null){
            textFileName.setTextColor(_textColor);
        }

        if (_backgroundColor != null){
            root.setBackgroundColor(_backgroundColor);
        }

        ResolveInfo resolveInfo = _resolveInfoList.get(position);
        textFileName.setText((String)resolveInfo.loadLabel(_packageManager));
        imageFileIcon.setImageDrawable(resolveInfo.loadIcon(_packageManager));

        if (_existCheckbox) {
            CheckBox checkApp = (CheckBox) root.findViewById(R.id.checkApp);
            checkApp.setTag(position);
            checkApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeChecked((CheckBox) v);
                }
            });
            initCheckbox(position, checkApp, resolveInfo);
        }
        return root;
    }

    private void changeChecked(CheckBox checkBox){
        Integer position = (Integer)checkBox.getTag();
        if (checkBox.isChecked()){
            _positionList.add(position);
            checkBox.setChecked(true);
        }else{
            for (int i = 0; i < _positionList.size(); i++) {
                if (_positionList.get(i) == position){
                    _positionList.remove(i);
                    break;
                }
            }
            checkBox.setChecked(false);
        }
    }

    private void initCheckbox(int position, CheckBox checkBox, ResolveInfo resolveInfo){
        if (_selectedItemDic != null && _selectedItemDic.size() > 0) {
            Boolean selected = _selectedItemDic.get(resolveInfo.activityInfo.name);
            if (selected != null && selected){
                _selectedItemDic.remove(resolveInfo.activityInfo.name);
                _positionList.add(position);
                checkBox.setChecked(true);
                return;
            }
        }

        for (int item : _positionList) {
            if (item == position){
                checkBox.setChecked(true);
                return;
            }
        }
        checkBox.setChecked(false);
    }

    public List<ResolveInfo> get_CheckedResolveInfoList(){
        List<ResolveInfo> resolveInfoList = new ArrayList<>();
        for (int index : _positionList){
            resolveInfoList.add(_resolveInfoList.get(index));
        }

        // これをやらないとアプリ一覧をスクロールせずにOKされた場合、1ページ目のチェック以外取りこぼす
        if (_selectedItemDic != null && _selectedItemDic.size() > 0) {
            for (ResolveInfo resolveInfo : _resolveInfoList){
                Boolean selected = _selectedItemDic.get(resolveInfo.activityInfo.name);
                if (selected != null && selected){
                    resolveInfoList.add(resolveInfo);
                }
            }
        }
        return resolveInfoList;
    }

    public ResolveInfo getResolveInfo(int position){
        return _resolveInfoList.get(position);
    }
//
//    public List<Integer> getPositionList(){
//        return _positionList;
//    }
    public void setParentListView(MyListView parentListView){
        _parentListView = parentListView;
    }
}
