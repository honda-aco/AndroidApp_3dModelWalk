package com.ns.aco.sp.view.setModel;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ns.aco.sp.R;
import java.io.File;

public class FileSelectFragment extends Fragment implements SetModelContract.FileSelectView{

    private SetModelContract.Presenter _presenter;
    private LinearLayout _linearSelection = null;
    private int _no = -1;
    private TextView _textNo = null;
    private Button _btnDelete = null;
    private Button _btnObjPath = null;
    private Button _btnPngPath = null;
    private EditText _editText_ObjFileName = null;
    private EditText _editText_PngFileName = null;
    private File _objFile = null;
    private File _pngFile = null;
    private String _objFilePath = null;
    private String _pngFilePath = null;
    private boolean _statusReferenceOnly = false;
    private boolean _statusDeleted = false;
    private int _referenceFileNo = -1;

    @Override
    public void setStatusReferenceOnly(boolean statusReferenceOnly){
        _statusReferenceOnly = statusReferenceOnly;
    }

    @Override
    public boolean getStatusReferenceOnly(){
        return _statusReferenceOnly;
    }

    @Override
    public boolean getStatusDeleted(){
        return _statusDeleted;
    }

    @Override
    public void setReferenceFileNo(int referenceFileNo){
        _referenceFileNo = referenceFileNo;
    }

    @Override
    public int getReferenceFileNo(){
        return _referenceFileNo;
    }

    @Override
    public void setObjFilePath(String objFilePath){
        _objFilePath = objFilePath;
    }

    @Override
    public void setPngFilePath(String pngFilePath){
        _pngFilePath = pngFilePath;
    }

    @Override
    public void setObjFileName_editText(String value){
        _editText_ObjFileName.setText(value);
    };

    @Override
    public String getObjFileName_editText(){
        return _editText_ObjFileName.getText().toString();
    };

    @Override
    public void setPngFileName_editText(String value){
        _editText_PngFileName.setText(value);
    };

    @Override
    public String getPngFileName_editText(){
        return _editText_PngFileName.getText().toString();
    };

    @Override
    public void setObjFile(File objFile){
        _objFile = objFile;
    };

    @Override
    public File getObjFile(){
        return _objFile;
    };

    @Override
    public void setPngFile(File pngFile){
        _pngFile = pngFile;
    };

    @Override
    public File getPngFile(){
        return _pngFile;
    };

    @Override
    public void setTextNo(int no){
        _no = no;

        if (_textNo != null){
            _textNo.setText(String.valueOf(no));
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {

        if (savedInstanceState != null){
            return null;
        }

        View root = inflater.inflate(R.layout.view_file_select, container, false);

        _linearSelection = (LinearLayout)root.findViewById(R.id.linearSelection);
        _textNo = (TextView) root.findViewById(R.id.textNo);
        _btnDelete = (Button) root.findViewById(R.id.btnDelete);
        _btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _presenter.hideFragmentFileSelect(FileSelectFragment.this);
                _presenter.updateFileSelectNo();
            }
        });
        _btnObjPath = (Button) root.findViewById(R.id.btnObjPath);
        _btnObjPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _presenter.showDialogSelectObjFile(FileSelectFragment.this, getActivity().getFragmentManager());
            }
        });
        _btnPngPath = (Button) root.findViewById(R.id.btnPngPath);
        _btnPngPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _presenter.showDialogSelectPngFile(FileSelectFragment.this, getActivity().getFragmentManager());
            }
        });
        _editText_ObjFileName = (EditText) root.findViewById(R.id.textObjPath);
        _editText_PngFileName = (EditText) root.findViewById(R.id.textPngPath);

        // 前回保存したファイルを表示するだけの場合
        if (_statusReferenceOnly){
            _editText_ObjFileName.setText(_objFilePath);
            _editText_ObjFileName.setTextColor(Color.RED);
            _btnObjPath.setEnabled(false);
            _editText_PngFileName.setText(_pngFilePath);
            _editText_PngFileName.setTextColor(Color.RED);
            _btnPngPath.setEnabled(false);
        }

        if (_no != -1){
            _textNo.setText(String.valueOf(_no));
        }
        return root;
    }

    @Override
    public void setPresenter(SetModelContract.Presenter presenter) {
        _presenter = presenter;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setAllControlsEnabled(boolean enabled){
        if (_statusReferenceOnly == false){
            _btnDelete.setEnabled(enabled);
            _btnObjPath.setEnabled(enabled);
            _btnPngPath.setEnabled(enabled);
        }
    }

    @Override
    public Fragment get_fragment() {
        return this;
    }

    @Override
    public void hide(){
        _linearSelection.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        _statusDeleted = true;
    }
}
