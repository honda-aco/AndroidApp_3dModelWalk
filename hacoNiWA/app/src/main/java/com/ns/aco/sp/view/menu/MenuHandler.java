package com.ns.aco.sp.view.menu;

import android.os.Handler;
import android.os.Looper;
import com.ns.aco.sp.dialog.DialogContract;

//スレッド間通信用クラス
public class MenuHandler extends Thread implements MenuContract.Handler{

	private Looper threadUI = Looper.getMainLooper();
	private final DialogContract.Progress _progressDialog;

	public MenuHandler(DialogContract.Progress progressDialog){
		_progressDialog = progressDialog;
	}

	@Override
	public void setMaxValue_progressDialog(final int value) {
		new Handler(threadUI).post(new Runnable() {
			public void run() {
				_progressDialog.setMax(value);
				_progressDialog.setProgress(0);
			}
		});
	}

	@Override
	public void increment_progressDialog(final int value) {
		new Handler(threadUI).post(new Runnable() {
			public void run() {
				_progressDialog.incrementProgressBy(value);
			}
		});
	}

	@Override
	public void close_progressDialog() {
		new Handler(threadUI).post(new Runnable() {
			public void run() {
				_progressDialog.dismiss();
			}
		});
	}
}
