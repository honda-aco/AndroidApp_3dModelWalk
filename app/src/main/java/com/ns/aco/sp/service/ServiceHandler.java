package com.ns.aco.sp.service;

import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ns.aco.sp.toast.FukidashiToast;

//スレッド間通信用クラス
public class ServiceHandler extends Thread implements ServiceContract.Handler{

	private Looper threadUI = Looper.getMainLooper();
	private final FukidashiToast _fukidashiToast;
	private final WindowManager _windowManager;
	private final GLSurfaceView _gLSurfaceView;
	private final ImageView _imageGlSurface;
	private final ImageView _imageFrontDoor;


	public ServiceHandler(FukidashiToast fukidashiToast, WindowManager windowManager, GLSurfaceView glSurfaceView,
						   ImageView imageFrontDoor, ImageView imageGlSurface){

		_fukidashiToast = fukidashiToast;
		_windowManager = windowManager;
		_gLSurfaceView = glSurfaceView;
		_imageGlSurface = imageGlSurface;
		_imageFrontDoor = imageFrontDoor;
	}

	@Override
	public void updateWindow(final Bitmap bitmap, final float alpha, final WindowManager.LayoutParams layoutParams){
		new Handler(threadUI).post(new Runnable() {
			public void run() {
				_imageGlSurface.setImageBitmap(bitmap);
				_imageGlSurface.setAlpha(alpha);
				_windowManager.updateViewLayout(_imageGlSurface, layoutParams);
			}
		});
	}

	@Override
	public void showFukidashiToast(final String message){
		new Handler(threadUI).post(new Runnable() {
			public void run() {
				Log.d(new Throwable().getStackTrace()[0].getMethodName(), "吹き出しトースト表示");
				_fukidashiToast.setMessage(message);
				_fukidashiToast.show();
			}
		});
	}

	@Override
	public void removeView_gLSurfaceView(){
		try{
			_windowManager.removeView(_gLSurfaceView);
		}catch(Exception e){
			Log.d("ServiceHandler","removeView_gLSurfaceViewで例外");
		}
	}

	@Override
	public void removeView_imageGlSurface(){
		try{
			_windowManager.removeView(_imageGlSurface);
		}catch(Exception e){
			Log.d("ServiceHandler","removeView_imageGlSurfaceで例外");
		}
	}

	@Override
	public void removeView_imageFrontDoor(){
		try{
			_windowManager.removeView(_imageFrontDoor);
		}catch(Exception e){
			Log.d("ServiceHandler","removeView_imageFrontDoorで例外");
		}
	}
}
