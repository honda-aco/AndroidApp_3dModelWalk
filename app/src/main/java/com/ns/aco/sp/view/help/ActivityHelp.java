package com.ns.aco.sp.view.help;

import com.ns.aco.sp.R;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ImageView;

public class ActivityHelp extends Activity {

	private HelpContract.Presenter _presenter = null;
	private ImageView _helpImageViews[] = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// タイトルバーを非表示にする
		requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_help);

		_presenter = new PresenterHelp(this);

		_helpImageViews = new ImageView[]{
				(ImageView) findViewById(R.id.helpDroidLeft),
				(ImageView) findViewById(R.id.helpDroidCenter),
				(ImageView) findViewById(R.id.helpDroidRight),
				(ImageView) findViewById(R.id.helpArrowLeft1),
				(ImageView) findViewById(R.id.helpArrowLeft2),
				(ImageView) findViewById(R.id.helpArrowRight1),
				(ImageView) findViewById(R.id.helpArrowRight2)
		};
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

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// 画面の回転を抑止する
        super.onConfigurationChanged(newConfig);
		_presenter.onConfigurationChanged(newConfig);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
		_presenter.onWindowFocusChanged();
		_presenter.setViewAfterFocuced(_helpImageViews, _helpImageViews[0].getWidth());
    }

	@Override
	public void onDestroy() {
		super.onDestroy();
		_presenter.onDestroy();
	}
}