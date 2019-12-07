package com.ns.aco.sp.view.menu;

import android.widget.EditText;
import com.ns.aco.sp.da.DataAccessContract;
import com.ns.aco.sp.dialog.DialogContract;

public class MenuPresenter2 extends MenuPresenterBase implements MenuContract.Presenter_Fragment2{

    private final MenuContract.View_Fragment2 _viewFragment2;

    public MenuPresenter2(MenuContract.View_Fragment2 view, DataAccessContract dataAccess, DialogContract.SizePosition sizePositionDialog, DialogContract.Progress progressDialog) {
        super(view, dataAccess, sizePositionDialog, progressDialog);
        _viewFragment2 = view;
        _viewFragment2.setPresenter(this);
    }

    @Override
    public void initializeComponents(){
        super.initializeComponents();

        _viewFragment2.setText_rangeEdit(_dataAccess.get_COORDINATE_RANGE());
        _viewFragment2.setText_intervalEdit(_dataAccess.get_INTERVAL());
    }

    @Override
    public void onFocusChange_rangeEdit(EditText view, boolean b) {
        String value = onFocusChange(view);
        _dataAccess.set_COORDINATE_RANGE(Float.valueOf(value));
        view.setText(value);
    }

    @Override
    public void onFocusChange_intervalEdit(EditText view, boolean b) {
        String value = onFocusChange(view);
        _dataAccess.set_INTERVAL(Float.valueOf(value));
        view.setText(value);
    }

    private String onFocusChange(EditText view) {
        // 0.0の場合は0.1にする
        float value = Float.valueOf(view.getText().toString());
        if(value == 0){
            value = 0.1f;
        }
        return String.valueOf(value);
    }

    @Override
    public void startActivity(Class<?> aClass) {
        _viewFragment2.startActivity(aClass);
    }
}