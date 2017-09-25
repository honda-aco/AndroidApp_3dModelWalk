package com.ns.aco.sp.view.menu;

import android.app.FragmentManager;
import android.content.res.Configuration;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import com.ns.aco.sp.R;
import com.ns.aco.sp.da.DataAccessContract;
import com.ns.aco.sp.dialog.DialogContract;

public abstract class MenuPresenterBase implements MenuContract.Presenter, DialogContract.SizePosition.Presenter, DialogContract.Progress.Presenter{

    private final MenuContract.View _view;
    protected final DataAccessContract _dataAccess;
    protected final DialogContract.SizePosition _sizePositionDialog;
    protected final DialogContract.Progress _progressDialog;

    public MenuPresenterBase(MenuContract.View view, DataAccessContract dataAccess, DialogContract.SizePosition sizePositionDialog, DialogContract.Progress progressDialog) {
        _view = view;
        _dataAccess = dataAccess;
        _sizePositionDialog = sizePositionDialog;
        _sizePositionDialog.setPresenter(this);
        _progressDialog = progressDialog;
        _progressDialog.setPresenter(this);
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    }

    @Override
    public void onWindowFocusChanged() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void initializeComponents(){
        // 省エネモードのチェックを設定する
        _view.setChecked_savingEnergyCheckBox(_dataAccess.get_SAVINGENERGY());

        // サイズボタンの初期値を設定する
        int size = _dataAccess.get_SIZE();
        _view.setText_sizeButton(size);
        _view.setTag_sizeButton(size);

        // 位置ボタンの初期値を設定する
        int position = _dataAccess.get_DOORPOSITION();
        _view.setText_positionButton(position);
        _view.setTag_positionButton(position);

        // 開始ボタン、終了ボタンの活性・非活性を設定する
        if (_view.isServiceRunning()){
            _view.setEnabled_startButton(false);
            _view.setEnabled_stopButton(true);
        }
    }

    @Override
    public void progressChangedTransparency(SeekBar seekBar, TextView textView){
        int progressValue = seekBar.getProgress();
        textView.setText(String.valueOf(1000 + progressValue).substring(1, 4));
        _view.sendBroadcast_transparency();
    }

    @Override
    public void checkSavingEnergy(CheckBox checkBox){
        _dataAccess.set_SAVINGENERGY(checkBox.isChecked());
    }

    @Override
    public void showSizePositionDialog(String size, String position, FragmentManager fragmentManager) {
        _sizePositionDialog.setDefaultSizeId(size);
        _sizePositionDialog.setDefaultPositionId(position);
        _sizePositionDialog.show(fragmentManager, null);
    }

    @Override
    public void result(int size, int position) {
        _dataAccess.set_SIZE(size);
        _dataAccess.set_DOORPOSITION(position);
        _view.setText_sizeButton(size);
        _view.setText_positionButton(position);
        _view.setTag_sizeButton(size);
        _view.setTag_positionButton(position);
    }

    @Override
    public void showProgressDialog(FragmentManager fragmentManager) {
        _progressDialog.show(fragmentManager, null);
    }

    @Override
    public void result() {
        _view.stopService();
    }


    @Override
    public void startService(){
        // 先に動作中のサービスを停止する
        _view.stopService();
        _view.startService();
    }

    @Override
    public void stopService(){
        _view.stopService();
    }

    public void countUpStartCount(int resId){

        switch (resId){
            case R.drawable.gate_droid1_top:
            case R.drawable.gate_droid2_top:
            case R.drawable.gate_droid3_top:
                _dataAccess.countUpSTART_COUNT_DROID();
                break;
            case R.drawable.gate_kohaku1_top:
            case R.drawable.gate_kohaku2_top:
            case R.drawable.gate_kohaku3_top:
            case R.drawable.gate_misaki1_top:
            case R.drawable.gate_yuko1_top:
                _dataAccess.countUp_START_COUNT_UNITY();
                break;
            case R.drawable.gate_onepiece1_top:
            case R.drawable.gate_onepiece2_top:
                _dataAccess.countUp_START_COUNT_ONEPIECE();
                break;
            case R.drawable.gate_waso1_top:
            case R.drawable.gate_waso2_top:
                _dataAccess.countUp_START_COUNT_WASO();
                break;
            case R.drawable.gate_cardigan1_top:
            case R.drawable.gate_cardigan2_top:
                _dataAccess.countUp_START_COUNT_CARDIGAN();
                break;
            case R.drawable.gate_jersey1_top:
            case R.drawable.gate_jersey2_top:
            case R.drawable.gate_cosplay1_top:
                _dataAccess.countUp_START_COUNT_JERSEY();
                break;
            case R.drawable.gate_witch1_top:
            case R.drawable.gate_witch2_top:
            case R.drawable.gate_witch3_top:
                _dataAccess.countUp_START_COUNT_WITCH();
                break;
            case R.drawable.gate_frog1_top:
            case R.drawable.gate_frog2_top:
                _dataAccess.countUp_START_COUNT_FROG();
                break;
            case R.drawable.gate_frograin1_top:
            case R.drawable.gate_frograin2_top:
                _dataAccess.countUp_START_COUNT_FROGRAIN();
                break;
        }
    }
}
