package com.ns.aco.sp.view.menu;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import androidx.annotation.RequiresApi;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;

import com.ns.aco.sp.R;
import com.ns.aco.sp.da.DataAccessContract;
import com.ns.aco.sp.da.OperateDataBase;
import com.ns.aco.sp.dialog.DialogContract;
import com.ns.aco.sp.dialog.ProgressDialog;
import com.ns.aco.sp.dialog.SizePositionDialog;
import com.ns.aco.sp.extent.MyFragmentPagerAdapter;

public class ActivityMenu extends AppCompatActivity{

	private int _pageNo = 0;
	private MenuContract.View_Fragment1 _viewFragment1;
	private MenuContract.View_Fragment2 _viewFragment2;
	private MenuContract.Handler _handler1;
	private MenuContract.Handler _handler2;
	private DataAccessContract _dataAccess;
	private DialogContract.SizePosition _sizePositionDialog1;
	private DialogContract.SizePosition _sizePositionDialog2;
	private DialogContract.Progress _progressDialog1;
	private DialogContract.Progress _progressDialog2;
	private MenuContract.Presenter_Fragment1 _presenterFragment1;
	private MenuContract.Presenter_Fragment2 _presenterFragment2;
	private final int _OVERLAY_PERMISSION_REQ_CODE = 1000;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, _OVERLAY_PERMISSION_REQ_CODE);
            }
        }

        _viewFragment1 = new MenuFragment1();
		_viewFragment2 = new MenuFragment2();
		_dataAccess = new OperateDataBase(getApplicationContext());
		_dataAccess.open();
		_dataAccess.initialize();
		_sizePositionDialog1 = new SizePositionDialog();
		_sizePositionDialog2 = new SizePositionDialog();
		_progressDialog1 = new ProgressDialog();
		_progressDialog2 = new ProgressDialog();

		_handler1 = new MenuHandler(_progressDialog1);
		_handler2 = new MenuHandler(_progressDialog2);

		_presenterFragment1 = new MenuPresenter1(_viewFragment1, _dataAccess, _sizePositionDialog1, _progressDialog1);
		_presenterFragment2 = new MenuPresenter2(_viewFragment2, _dataAccess, _sizePositionDialog2, _progressDialog2);

		// レイアウトを設定する
		setContentView(R.layout.activity_menu_tab);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
		ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

		// ViewPagerの中身を設定
		String[] pageTitleList = new String[]{getString(R.string.value0046), getString(R.string.value0047)};
		Fragment[] pageFragmentList = new Fragment[]{_viewFragment1.getFragment(), _viewFragment2.getFragment()};
		MyFragmentPagerAdapter myFragmentPagerAdapter =
				new MyFragmentPagerAdapter(getSupportFragmentManager(), pageFragmentList, pageTitleList);

		viewPager.setOffscreenPageLimit(1);
		viewPager.setAdapter(myFragmentPagerAdapter);
		tabLayout.setupWithViewPager(viewPager);

		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int state) {}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

			@Override
			public void onPageSelected(int position) {
				_pageNo = position;
			}
		});

//		// 言語設定により表示する広告を切り替える
//		if (Locale.getDefault() != Locale.JAPAN)) {
//			// 外国向けバナー広告オブジェクトを取得
//			AdView adViewHome = new AdView(this);
//			adViewHome.setAdUnitId(BuildConfig.banner_ad_unit_id_home);
//			adViewHome.setAdSize(AdSize.SMART_BANNER);
//			adViewHome.loadAd(new AdRequest.Builder().build());
//
//			LinearLayout linearAd = (LinearLayout) findViewById(R.id.linearAd);
//			linearAd.addView(
//					adViewHome,
//					new LinearLayout.LayoutParams(
//							LinearLayout.LayoutParams.WRAP_CONTENT,
//							LinearLayout.LayoutParams.WRAP_CONTENT));
//
//			// 言語が日本語設定ならアイコン広告を表示する
//			linearAd.setLayoutParams(
//					new LinearLayout.LayoutParams(
//							LinearLayout.LayoutParams.MATCH_PARENT,
//							LinearLayout.LayoutParams.WRAP_CONTENT));
//		}

		// サービスから送られた情報を受信するレシーバの設定
		BroadcastReceiver upReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				MenuContract.Handler handler;
				if (_pageNo == 0){
					handler = _handler1;
				}else{
					handler = _handler2;
				}

				switch (intent.getIntExtra(getString(R.string.activity_intent1), -1) ){
					case 0:
						handler.setMaxValue_progressDialog(intent.getIntExtra(getString(R.string.activity_intent2), 0));
						break;
					case 1:
						handler.increment_progressDialog(intent.getIntExtra(getString(R.string.activity_intent2), 0));
						break;
					case 2:
						handler.close_progressDialog();
						break;
				}
			}
		};
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(getString(R.string.activity_intent_action));
		registerReceiver(upReceiver, intentFilter);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		return super.dispatchKeyEvent(e);
	}

	// 画面の回転を抑止する
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

	@Override
	public void onDestroy() {
		super.onDestroy();
		_dataAccess.close();
	}

	@Override
	public ComponentName startService(Intent intent){
		_viewFragment1.setEnabled_startButton(false);
		_viewFragment1.setEnabled_stopButton(true);
		_viewFragment2.setEnabled_startButton(false);
		_viewFragment2.setEnabled_stopButton(true);
		return super.startService(intent);
	}

	@Override
	public boolean stopService(Intent intent){
		_viewFragment1.setEnabled_startButton(true);
		_viewFragment1.setEnabled_stopButton(false);
		_viewFragment2.setEnabled_startButton(true);
		_viewFragment2.setEnabled_stopButton(false);
		return super.stopService(intent);
	}

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == _OVERLAY_PERMISSION_REQ_CODE) {
            if (!Settings.canDrawOverlays(this)) {
                Log.i("requestCode:" + requestCode, "SYSTEM_ALERT_WINDOW permission not granted...");
            }
        }
    }
}