package com.ns.aco.sp.view.setModel;

import com.ns.aco.sp.R;
import com.ns.aco.sp.da.DataAccessContract;
import com.ns.aco.sp.da.OperateDataBase;
import com.ns.aco.sp.dialog.DialogContract;
import com.ns.aco.sp.dialog.SelectObjFileDialog;
import com.ns.aco.sp.dialog.SelectPngFileDialog;
import com.ns.aco.sp.extent.MyFragmentPagerAdapter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class ActivitySetModel extends AppCompatActivity {

	SetModelContract.SetModelView[] _pageFragmentList = null;
	private DataAccessContract _dataAccess;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		_dataAccess = new OperateDataBase(getApplicationContext());
		_dataAccess.open();

		SetModelContract.SetModelView setModelView1 = new SetModelFragment1();
		SetModelContract.SetModelView setModelView2 = new SetModelFragment2();
		SetModelContract.SetModelView setModelView3 = new SetModelFragment3();

		DialogContract.SelectObjFile selectObjFile = new SelectObjFileDialog();
		DialogContract.SelectPngFile selectPngFile = new SelectPngFileDialog();

		SetModelContract.Presenter presenter1 = new PresenterSetModel(setModelView1, selectObjFile, selectPngFile);
		SetModelContract.Presenter presenter2 = new PresenterSetModel(setModelView2, selectObjFile, selectPngFile);
		SetModelContract.Presenter presenter3 = new PresenterSetModel(setModelView3, selectObjFile, selectPngFile);

		setModelView1.setPresenter(presenter1);
		setModelView2.setPresenter(presenter2);
		setModelView3.setPresenter(presenter3);

		// レイアウトを設定する
		setContentView(R.layout.activity_setmodel);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
		ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

		// ViewPagerの中身を設定
		String[] pageTitleList = new String[]{getString(R.string.value0016), getString(R.string.value0017), getString(R.string.value0018)};
		Fragment[] pageFragmentList = new Fragment[]{setModelView1.get_fragment(), setModelView2.get_fragment(), setModelView3.get_fragment()};
		MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), pageFragmentList, pageTitleList);

		viewPager.setAdapter(myFragmentPagerAdapter);
		tabLayout.setupWithViewPager(viewPager);
	}

    @Override
    public void onResume() {
        super.onResume();
    }

	@Override
	public void onPause() {
		super.onPause();
	}

	// 画面の回転を抑止する
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
    }

	@Override
	public void onDestroy() {
		super.onDestroy();
		_dataAccess.close();
	}
}