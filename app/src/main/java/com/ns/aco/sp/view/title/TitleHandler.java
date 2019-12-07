package com.ns.aco.sp.view.title;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

//スレッド間通信用クラス
public class TitleHandler extends Thread implements TitleContract.Handler {

	private Looper threadUI = Looper.getMainLooper();

	@Override
	public void setBackColor(final ImageView imageView, final int setColor){
		new Handler(threadUI).post(new Runnable() {
			public void run() {
				imageView.setBackgroundColor(setColor);
			}
		});
	}

	@Override
	public void setBackImage(final ImageView imageView, final Bitmap bitmap){
		new Handler(threadUI).post(new Runnable() {
			public void run() {
				imageView.setImageBitmap(bitmap);
			}
		});
	}
}
