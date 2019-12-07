package com.ns.aco.sp.view.title;

import com.ns.aco.sp.R;
import com.ns.aco.sp.view.menu.ActivityMenu;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;

public class TitleActivity extends Activity implements TitleContract.View{

	private TitleContract.Presenter _presenter;
	private Button _policyButton;
	private ImageView _imageView;
	private Timer _timer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		_presenter = new TitlePresenter(this);

		// タイトルバーを非表示にする
		setContentView(R.layout.activity_title);
		_imageView = (ImageView) findViewById(R.id.startImage_Start);
		_imageView.setOnClickListener(
				new OnClickListener() {
					public void onClick(View clickView) {
						_presenter.startActivity();
					}
				}
		);

		_policyButton = (Button) findViewById(R.id.btnPolicy);
		_policyButton.setOnClickListener(
				new OnClickListener() {
					public void onClick(View clickView) {
						_presenter.movePolicyPage();
					}
				}
		);
	}

    @Override
    public void onResume() {
        super.onResume();
		_presenter.onResume();
    }

	@Override
	public void onPause() {
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
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
		_presenter.onWindowFocusChanged();

		if (_timer == null)
		{
			_presenter.setViewAfterFocuced(getApplicationContext(), _imageView);
		}
    }

	@Override
	public void onDestroy() {
		super.onDestroy();
		_presenter.onDestroy();
	}

	@Override
	public void startActivity(){
		if (_timer != null){
			_timer.cancel();
		}
		// インテントのインスタンス生成
		Intent intent = new Intent(TitleActivity.this, ActivityMenu.class);
		// 次画面のアクティビティ起動
		startActivity(intent);
		finish();
	}

	@Override
	public void movePolicyPage(){
/*		if (_timer != null){
			_timer.cancel();
		}*/

		// インテントのインスタンス生成
		Uri uri = Uri.parse("https://honda-aco.github.io/honda-aco-apps/privacy-policy-hacoNiWA");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		// ポリシーページを起動
		startActivity(intent);
/*		finish();*/
	}

	@Override
	public void setBackImage(Bitmap bitmap) {
		_imageView.setImageBitmap(bitmap);
		_imageView.setScaleType(ImageView.ScaleType.CENTER);
	}

	@Override
	public void startTimerTask(Bitmap[] bitmapList) {
		// タイマーによる背景色の変更処理を開始する
		TimerTask timerTask = new TitleTimerTask(new TitleHandler(), _imageView, bitmapList);
		_timer = new Timer(true);
		_timer.schedule(timerTask, 100, 100);
	}
}