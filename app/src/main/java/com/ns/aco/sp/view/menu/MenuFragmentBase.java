package com.ns.aco.sp.view.menu;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import com.ns.aco.sp.R;
import com.ns.aco.sp.common.util.UtilityService;
import com.ns.aco.sp.common.util.UtilityString;
import com.ns.aco.sp.service.ServiceHaconiwa;

abstract public class MenuFragmentBase extends Fragment implements MenuContract.View {

    protected Button _startButton;
    protected Button _stopButton;
    protected Button _sizeButton;
    protected Button _positionButton;
    protected CheckBox _savingEnergy;
    protected CheckBox _talkAction;
    protected SeekBar _seekBarTransparency;
    protected TextView _textSeekProgress;
    protected EditText _rangeEdit;
    protected EditText _intervalEdit;
    protected Button _setModelButton;
    protected Button _helpButton;

    @Override
    public void setEnabled_startButton(boolean value){
        _startButton.setEnabled(value);
    }

    @Override
    public void setEnabled_stopButton(boolean value){
        _stopButton.setEnabled(value);
    }

    @Override
    public void setChecked_savingEnergyCheckBox(boolean value){
        _savingEnergy.setChecked(value);
    }

    @Override
    public void setText_sizeButton(int value){
        String size = UtilityString.replace(getActivity().getString(R.string.value0005), String.valueOf(value));
        _sizeButton.setText(size);
    }

    @Override
    public void setTag_sizeButton(Object value){
        _sizeButton.setTag(value);
    }

    @Override
    public void setText_positionButton(int value){
        String positon = null;
        switch (value){
            case 0:
                positon = getString(R.string.value0001);
                break;
            case 1:
                positon = getString(R.string.value0002);
                break;
            case 2:
                positon = getString(R.string.value0003);
                break;
            case 3:
                positon = getString(R.string.value0004);
                break;
        }
        _positionButton.setText(positon);
    }

    @Override
    public void setTag_positionButton(Object value){
        _positionButton.setTag(value);
    }

    @Override
    public void setEnabled_talkCheckBox(boolean value){
        _talkAction.setEnabled(value);
    }

    @Override
    public void stopService(){
        if(UtilityService.existsService(getActivity(), getString(R.string.service_name))) {
            getActivity().stopService(new Intent(getActivity(), ServiceHaconiwa.class));
        }
        _seekBarTransparency.setEnabled(false);
    }

    @Override
    public void sendBroadcast_transparency(){
        Intent intent = new Intent();
        intent.putExtra(getString(R.string.service_intent9), _seekBarTransparency.getProgress());
        intent.setAction(getString(R.string.service_intent_action));
        getActivity().getBaseContext().sendBroadcast(intent);
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public boolean isServiceRunning(){
        return UtilityService.existsService(getActivity(), getString(R.string.service_name));
    }
}
