package com.ns.aco.sp.view.setModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.ns.aco.sp.R;

public class SetModelFragment2 extends SetModelFragment{

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        if (savedInstanceState != null){
            return null;
        }

        View root = inflater.inflate(R.layout.fragment_setmodel_tabitem2, container, false);

        _linearLayout = (LinearLayout) root.findViewById(R.id.linearCenter);
        _addFileButton = (Button) root.findViewById(R.id.btnAddObjFile);
        _addFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _presenter.addFragmentFileSelect();
                _presenter.updateFileSelectNo();
            }
        });
        _saveButton = (Button) root.findViewById(R.id.btnSaveObjFile);
        _saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _presenter.saveSelectFiles(_fileSelectList, getDirectoryPath());
                _presenter.updateFileSelectNo();
            }
        });

        _presenter.initialize(getDirectoryPath());
        return root;
    }

    @Override
    protected String getDirectoryPath(){
        return getString(R.string.convert_directory).replaceFirst("\\?", "0").replaceFirst("\\?", getString(R.string.value0017));
    }

    @Override
    protected String getSaveFinishedMessage(){
        return getString(R.string.value0033).replaceFirst("\\?", getString(R.string.value0017));
    }
}
