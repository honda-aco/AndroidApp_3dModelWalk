package com.ns.aco.sp.view.menu;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.ns.aco.sp.BuildConfig;
import com.ns.aco.sp.R;
import com.ns.aco.sp.service.ServiceHaconiwa;
import jp.maru.mrd.IconCell;
import jp.maru.mrd.IconLoader;

public class MenuFragment1 extends MenuFragmentBase implements MenuContract.View_Fragment1 {

    private MenuContract.Presenter_Fragment1 _presenter;
    private ImageView _imageCharacter;
	private IconCell _iconCell1;
	private IconCell _iconCell2;
	private IconCell _iconCell3;
	private IconCell _iconCell4;
	private IconLoader<Integer> _iconLoader;

    private int _colorPrimary;

    private ImageView[] _imageLeftList;
    private ImageView[] _imageTopList;

    @Override
    public View onCreateView(LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            _colorPrimary = getResources().getColor(R.color.colorPrimaryDark, null);
        } else {
            _colorPrimary = getResources().getColor(R.color.colorPrimaryDark);
        }

        View root = inflater.inflate(R.layout.fragment_menu_tabitem1, container, false);

        // メニュー左のImageView
        _imageLeftList = new ImageView[]{
                (ImageView) root.findViewById(R.id.imageLeft1), (ImageView) root.findViewById(R.id.imageLeft2), (ImageView) root.findViewById(R.id.imageLeft3),
                (ImageView) root.findViewById(R.id.imageLeft4), (ImageView) root.findViewById(R.id.imageLeft5), (ImageView) root.findViewById(R.id.imageLeft6),
                (ImageView) root.findViewById(R.id.imageLeft7), (ImageView) root.findViewById(R.id.imageLeft8), (ImageView) root.findViewById(R.id.imageLeft9)};

        for (ImageView imageView : _imageLeftList) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _presenter.selectImageView(_imageLeftList, (ImageView) v, _colorPrimary);
                }
            });
        }

        // メニュー上部のImageView
        _imageTopList = new ImageView[]{
            (ImageView) root.findViewById(R.id.imageTop1), (ImageView) root.findViewById(R.id.imageTop2), (ImageView) root.findViewById(R.id.imageTop3),
            (ImageView) root.findViewById(R.id.imageTop4), (ImageView) root.findViewById(R.id.imageTop5)};

        _imageTopList[0].setTag((int)R.drawable.gate_droid1_top);
        _imageTopList[1].setTag((int)R.drawable.gate_droid2_top);
        _imageTopList[2].setTag((int)R.drawable.gate_droid3_top);

        for (ImageView imageView : _imageTopList) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _presenter.selectImageView(_imageTopList, (ImageView) v, _colorPrimary);
                }
            });
        }

        // 選択キャラクタ表示用Image
        _imageCharacter = (ImageView) root.findViewById(R.id.imageCharacter);

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

        _talkAction = (CheckBox) root.findViewById(R.id.chkTalkAction);
        _talkAction.setEnabled(false);
        _talkAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.requestFocusFromTouch();
                _presenter.check_talkAction((CheckBox) v);
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

        // 日本向け広告アイコンオブジェクトを取得
		_iconLoader = new IconLoader<Integer>(BuildConfig.asta_icon_ad_id, getActivity());
		_iconLoader.setRefreshInterval(30);

		_iconCell1 = (IconCell)root.findViewById(R.id.cell1);
		_iconCell2 = (IconCell)root.findViewById(R.id.cell2);
		_iconCell3 = (IconCell)root.findViewById(R.id.cell3);
		_iconCell4 = (IconCell)root.findViewById(R.id.cell4);
		_iconCell1.addToIconLoader(_iconLoader);
		_iconCell2.addToIconLoader(_iconLoader);
		_iconCell3.addToIconLoader(_iconLoader);
		_iconCell4.addToIconLoader(_iconLoader);
		_iconCell2.setTitleColor(Color.RED);

        _presenter.initializeComponents();
        _presenter.initialize_talkActionCheckBox(_talkAction);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        _iconLoader.startLoading();
        _presenter.onResume();
    }

    @Override
    public void onPause() {
        _iconLoader.stopLoading();
        super.onPause();
        _presenter.onPause();
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
    public void setPresenter(@NonNull MenuContract.Presenter_Fragment1 presenter) {
        _presenter = presenter;
    }

    @Override
    public void setResource_characterImageView(int resourceId){
        _imageCharacter.setImageResource(resourceId);
    }

    @Override
    public void setResource_topImageView(int[] resourceList){
        int topImageViewSize = _imageTopList[0].getWidth();

        for (int i = 0; i < _imageTopList.length; i++){
            if (i < resourceList.length){
                _imageTopList[i].setTag(resourceList[i]);
                _imageTopList[i].setImageResource(resourceList[i]);
                _imageTopList[i].setLayoutParams(new LinearLayout.LayoutParams(topImageViewSize, topImageViewSize));
            }else{
                _imageTopList[i].setLayoutParams(new LinearLayout.LayoutParams(0, 0));
            }
        }
        _presenter.selectTopImageView(_imageTopList, _imageTopList[0], _colorPrimary);
    }

    @Override
    public void startService(){
        _seekBarTransparency.setEnabled(true);

        // サービスを起動
        Intent intent = new Intent(getActivity(), ServiceHaconiwa.class);
        for (ImageView imageView : _imageTopList) {
            if (((ColorDrawable) imageView.getBackground()).getColor() == _colorPrimary){
                intent.putExtra(getString(R.string.service_intent1), (int)imageView.getTag());
                // 起動回数のカウントアップ
                _presenter.countUpStartCount((int)imageView.getTag());
                break;
            }
        }

        if (_talkAction.isEnabled()){
            intent.putExtra(getString(R.string.service_intent3), _talkAction.isChecked());
        }else{
            intent.putExtra(getString(R.string.service_intent3), false);
        }

        intent.putExtra(getString(R.string.service_intent2), _savingEnergy.isChecked());
        intent.putExtra(getString(R.string.service_intent7), (int)_sizeButton.getTag());
        intent.putExtra(getString(R.string.service_intent8), (int)_positionButton.getTag());

        WindowManager windowManager = getActivity().getWindowManager();
        Display disp = windowManager.getDefaultDisplay();
        Point point = new Point();
        disp.getSize(point);

        getActivity().startService(intent);
    }
}
