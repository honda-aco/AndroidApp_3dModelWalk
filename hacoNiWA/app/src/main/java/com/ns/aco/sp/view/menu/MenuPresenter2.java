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
    public void onFocusChange(EditText view, boolean b) {
        // 0.0の場合は0.1にする
        float value = Float.valueOf(view.getText().toString());
        if(value == 0){
            value = 0.1f;
        }
        view.setText(String.valueOf(value));
    }

    @Override
    public void startActivity(Class<?> aClass) {
        _viewFragment2.startActivity(aClass);
    }
}