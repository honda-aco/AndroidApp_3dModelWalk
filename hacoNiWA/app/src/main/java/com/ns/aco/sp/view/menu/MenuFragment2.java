package com.ns.aco.sp.view.menu;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import com.ns.aco.sp.R;
import com.ns.aco.sp.service.ServiceHaconiwa;
import com.ns.aco.sp.view.help.ActivityHelp;
import com.ns.aco.sp.view.setModel.ActivitySetModel;

public class MenuFragment2 extends MenuFragmentBase implements MenuContract.View_Fragment2 {

    private MenuContract.Presenter_Fragment2 _presenter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_menu_tabitem2, container, false);

        _textSeekProgress = (TextView) root.findViewById(R.id.textSeekProgress);
        _textSeekProgress.setText("100");
        _seekBarTransparency = (SeekBar) root.findViewById(R.id.barTransparency);
        _seekBarTransparency.setEnabled(false);
        _seekBarTransparency.setMax(100);
        _seekBarTransparency.setProgress(100);
        _seekBarTransparency.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                _presenter.progressChanged_transparency(seekBar, _textSeekProgress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBar.requestFocusFromTouch();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        _savingEnergy = (CheckBox) root.findViewById(R.id.chkSavingEnergy);
        _savingEnergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.requestFocusFromTouch();
                _presenter.check_savingEnergy((CheckBox) v);
            }
        });

        // サイズ表示ボタンを設定
        _sizeButton = (Button) root.findViewById(R.id.btnSize);
        _sizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.requestFocusFromTouch();
                _presenter.showSizePositionDialog(_sizeButton.getText().toString(), _positionButton.getText().toString(), getActivity().getFragmentManager());
            }
        });

        // ドアポジション表示ボタンを設定
        _positionButton = (Button) root.findViewById(R.id.btnDoorPosition);
        _positionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.requestFocusFromTouch();
                _presenter.showSizePositionDialog(_sizeButton.getText().toString(), _positionButton.getText().toString(), getActivity().getFragmentManager());
            }
        });

        _startButton = (Button) root.findViewById(R.id.btnStart);
        _startButton.setEnabled(true);
        _startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.requestFocusFromTouch();
                _presenter.startService();
                _presenter.showProgressDialog(getActivity().getFragmentManager());
            }
        });

        _stopButton  = (Button) root.findViewById(R.id.btnStop);
        _stopButton.setEnabled(false);
        _stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.requestFocusFromTouch();
                _presenter.stopService();
            }
        });

        _rangeEdit = (EditText) root.findViewById(R.id.editRange);
        _rangeEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                _presenter.onFocusChange_rangeEdit((EditText) view, b);
            }
        });

        _intervalEdit =(EditText) root.findViewById(R.id.editInterval);
        _intervalEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                _presenter.onFocusChange_intervalEdit((EditText) view, b);
            }
        });

        _setModelButton = (Button) root.findViewById(R.id.btnSetModel);
        _setModelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _presenter.startActivity(ActivitySetModel.class);
            }
        });

        _helpButton = (Button) root.findViewById(R.id.btnHelp);
        _helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _presenter.startActivity(ActivityHelp.class);
            }
        });

        _presenter.initializeComponents();
        Log.d("test", "onCreate");
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        _presenter.onResume();
        Log.d("test", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        _presenter.onPause();
        Log.d("test", "onPause");
    }

    // 画面の回転を抑止する
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        _presenter.onConfigurationChanged(newConfig);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        _presenter.onDestroy();
    }

    @Override
    public void setPresenter(@NonNull MenuContract.Presenter_Fragment2 presenter) {
        _presenter = presenter;
    }

    @Override
    public void setText_rangeEdit(float value) {
        _rangeEdit.setText(String.valueOf(value));
    }

    @Override
    public void setText_intervalEdit(float value) {
        _intervalEdit.setText(String.valueOf(value));
    }

    @Override
    public void startService(){
        _seekBarTransparency.setEnabled(true);

        // サービスを起動
        Intent intent = new Intent(getActivity(), ServiceHaconiwa.class);
        intent.putExtra(getString(R.string.service_intent1), 0);

        intent.putExtra(getString(R.string.service_intent2), _savingEnergy.isChecked());
        intent.putExtra(getString(R.string.service_intent5), Float.valueOf(_rangeEdit.getText().toString()));
        intent.putExtra(getString(R.string.service_intent6), Float.valueOf(_intervalEdit.getText().toString()));
        intent.putExtra(getString(R.string.service_intent7), (int)_sizeButton.getTag());
        intent.putExtra(getString(R.string.service_intent8), (int)_positionButton.getTag());

        WindowManager windowManager = getActivity().getWindowManager();
        Display disp = windowManager.getDefaultDisplay();
        Point point = new Point();
        disp.getSize(point);

        getActivity().startService(intent);
    }

    @Override
    public void startActivity(Class<?> aClass){
        // インテントのインスタンス生成
        Intent intent = new Intent(getActivity(), aClass);
        // 次画面のアクティビティ起動
        startActivity(intent);
    }
}
