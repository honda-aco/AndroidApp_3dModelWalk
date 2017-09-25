package com.ns.aco.sp.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.ns.aco.sp.BuildConfig;
import com.ns.aco.sp.R;

import java.util.Locale;

import jp.maru.mrd.astawall.MrdAstaWallActivity;

public class ProgressDialog extends DialogFragment implements DialogContract.Progress{

    private boolean _dissmiss = false;
    private Button _astaButton = null;
    private Button _cancelButton = null;
    private TextView _textPercent = null;
    private TextView _textProgress = null;
    private ProgressBar _progressBar = null;
//    private AdView _adViewDialog = null;
    private LinearLayout _linearAd = null;
    private LinearLayout _linearAstaButton = null;
    private int _progress = 0;
    private int _maxValue = 0;
    private Presenter _presenter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = inflater.inflate(R.layout.dialog_progress, null);

        _astaButton = (Button)root.findViewById(R.id.AstaButton);
        _astaButton.setBackgroundResource(R.drawable.mrd_asta_wall_icon_recommend);
        _astaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ウォール広告呼出しボタンクリック時の動作
                Intent intent = new Intent(getActivity(), MrdAstaWallActivity.class);
                intent.putExtra("id", BuildConfig.asta_wall_ad_id);
                startActivity(intent);
            }
        });

        _cancelButton = (Button)root.findViewById(R.id.CancelButton);
        _cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _presenter.result();
                dismiss();
            }
        });

        _textPercent = (TextView)root.findViewById(R.id.TextPercent);
        _textProgress = (TextView)root.findViewById(R.id.TextProgress);
        _progressBar = (ProgressBar)root.findViewById(R.id.ProgressBar);
        _linearAd = (LinearLayout)root.findViewById(R.id.linearAd);
        _linearAstaButton = (LinearLayout)root.findViewById(R.id.LinearAstaButton);

        // XMLで指定しても動作しないため、ソースで設定（動的に配置を指定しているから？）
        _textPercent.setGravity(Gravity.LEFT);
        _textProgress.setGravity(Gravity.RIGHT);

        // 外国向けバナー広告オブジェクトを取得
        AdView adViewDialog = new AdView(getActivity());
        adViewDialog.setAdUnitId(BuildConfig.banner_ad_unit_id_dialog);
        adViewDialog.setAdSize(AdSize.BANNER);
        adViewDialog.loadAd(new AdRequest.Builder().build());

        LinearLayout linearAd = (LinearLayout) root.findViewById(R.id.linearAd);
        linearAd.addView(adViewDialog,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));

        // 言語によって広告表示を変更する
        if (Locale.getDefault().equals(Locale.JAPAN)){
            // 言語が日本語設定ならアスタのボタン広告を表示する
            _linearAd.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        }else{
            // 言語が日本語設定以外ならAdMobを表示する
            _astaButton.setLayoutParams(new LinearLayout.LayoutParams(0, 50));
        }

        builder.setView(root);
        builder.setTitle(getActivity().getString(R.string.value0012));
        // builder.setCancelable(false)←この書き方では戻るボタンを無効にできない
        this.setCancelable(false);

        return builder.create();
    }

    @Override
    public void onResume(){
        super.onResume();
        // アスタのウォール広告を開いた状態でdismissすると例外となるため
        if (_dissmiss){
            dismiss();
        }
    }

    @Override
    public void dismiss(){
        if (isResumed()){
            _dissmiss = false;
            super.dismiss();
        }else{
            _dissmiss = true;
        }
    }

    @Override
    public void setMax(int value){
        _maxValue = value;
        updateTextValue();
        _progressBar.setMax(value);
    }

    @Override
    public void incrementProgressBy(int value){
        _progress += value;
        updateTextValue();
        _progressBar.incrementProgressBy(value);
    }

    @Override
    public void setProgress(int value){
        _progress = value;
        updateTextValue();
        _progressBar.setProgress(value);
    }

    private void updateTextValue(){
        _textProgress.setText(_progress + " / " + _maxValue + "  ");
        if (_progress == 0){
            _textPercent.setText("  0 % ");
        }else{
            int percent = (_progress  * 100) / _maxValue;
            String space = "";
            if (percent < 10){
                space = "  ";
            }else if (percent < 100){
                space = " ";
            }
            // 桁数によってスペースを変更する
            _textPercent.setText("  " + space + percent + " %");
        }
    }

    @Override
    public void setPresenter(Presenter presenter) {
        _presenter = presenter;
    }
}
