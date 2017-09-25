package com.ns.aco.sp.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.ns.aco.sp.R;
import com.ns.aco.sp.common.extent.MyNumberPicker;
import com.ns.aco.sp.common.util.UtilityNumberPicker;
import com.ns.aco.sp.common.util.UtilityString;

public class SizePositionDialog extends DialogFragment implements DialogContract.SizePosition {

    private SizePosition.Presenter _presenter;
    private MyNumberPicker _sizePicker;
    private MyNumberPicker _positionPicker;
    private Button _buttonOK;
    private Button _buttonCancel;
    private String _defaultSize;
    private String _defaultPosition;

    public void setDefaultSizeId(String value){
        _defaultSize = value;
    }

    public void setDefaultPositionId(String value){
        _defaultPosition = value;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = inflater.inflate(R.layout.dialog_size_position, null);

        _sizePicker = (MyNumberPicker) root.findViewById(R.id.SizePicker);
        String[] sizeValues = new String[]{
                convertSizeToText(3),  convertSizeToText(4),  convertSizeToText(5),  convertSizeToText(6),  convertSizeToText(7),
                convertSizeToText(8),  convertSizeToText(9),  convertSizeToText(10), convertSizeToText(11), convertSizeToText(12),
                convertSizeToText(13), convertSizeToText(14), convertSizeToText(15), convertSizeToText(16), convertSizeToText(17),
                convertSizeToText(18), convertSizeToText(19), convertSizeToText(20)
        };
        _sizePicker.setMaxValue(20);
        _sizePicker.setMinValue(3);
        //****************************************
        //[debug]
        //String[] sizeValues = new String[] {
        //convIdToSizeText(1), convIdToSizeText(2), convIdToSizeText(3)
        //};
        //_sizePicker.setMaxValue(3);
        //_sizePicker.setMinValue(1);
        //****************************************
        _sizePicker.setHorizontalScrollBarEnabled(true);
        _sizePicker.setDisplayedValues(sizeValues);
        _sizePicker.setFocusable(true);
        _sizePicker.setFocusableInTouchMode(true);

        _positionPicker = (MyNumberPicker) root.findViewById(R.id.PositionPicker);
        _positionPicker.setMaxValue(3);
        _positionPicker.setMinValue(0);
        _positionPicker.setDisplayedValues(
                new String[]{
                        getString(R.string.value0001), getString(R.string.value0002), getString(R.string.value0003),
                        getString(R.string.value0004)});
        _positionPicker.setFocusable(true);
        _positionPicker.setFocusableInTouchMode(true);

        _buttonOK = (Button) root.findViewById(R.id.OK_SizeAndPosition);
        _buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _presenter.result(_sizePicker.getValue(), _positionPicker.getValue());
                SizePositionDialog.this.dismiss();
            }
        });

        _buttonCancel = (Button) root.findViewById(R.id.Cancel_SizeAndPosition);
        _buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SizePositionDialog.this.dismiss();
            }
        });

        // ボタンの文字列と同じ値をピッカーに設定する
        Integer sizeId = UtilityNumberPicker.getIdInNumberPicker(_sizePicker, _defaultSize);
        Integer positionId = UtilityNumberPicker.getIdInNumberPicker(_positionPicker, _defaultPosition);

        if (sizeId != null) {
            _sizePicker.setValue(sizeId);
        } else {
            _sizePicker.setValue(6);
        }

        if (positionId != null) {
            _positionPicker.setValue(positionId);
        } else {
            _positionPicker.setValue(0);
        }

        builder.setView(root);
        // builder.setCancelable(false)←この書き方では戻るボタンを無効にできない
        this.setCancelable(false);
        return builder.create();
    }

    @Override
    public void setPresenter(SizePosition.Presenter presenter) {
        _presenter = presenter;
    }

    private String convertSizeToText(int value){
        return UtilityString.replace(getActivity().getString(R.string.value0005), String.valueOf(value));
    }
}
