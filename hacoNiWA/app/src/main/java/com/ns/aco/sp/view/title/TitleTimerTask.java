package com.ns.aco.sp.view.title;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;
import java.util.TimerTask;

public class TitleTimerTask extends TimerTask {

	private TitleContract.Handler _handler;
	private ImageView _imageView;
	private Bitmap[] _bitmapList;
	private int _imageCount = 0;
	private int _r = 255;
	private int _g = 255;
	private int _b = 255;
	private int _a = 255;
	private boolean _increase = false;
	private int _runCount = 0;

	public TitleTimerTask(TitleContract.Handler handler, ImageView imageView, Bitmap[] bitmapList){
		_handler = handler;
		_imageView = imageView;
		_bitmapList = bitmapList;
	}

	@Override
	public void run(){
		_runCount += 1;
		// 色は毎回、画像は5回に一回変更する
		if (_runCount > 5){
			changeImage(_imageCount);
			// イメージ画像切り替え判定
			if (_imageCount == 0){
				_imageCount = 1;
			}else{
				_imageCount = 0;
			}
			// 処理回数リセット
			_runCount = 0;
		}
		changeColor();
	}

	private void changeColor(){
		if (_increase){
			if (_b != 255){
				_b += 5;
			}else if (_g != 255){
				_g += 5;
			}else if (_r != 255){
				_r += 5;
			}else{
				_increase = false;
			}
		}else{
			if (_r != 220){
				_r -= 5;
			}else if (_g != 220){
				_g -= 5;
			}else if (_b != 220){
				_b -= 5;
			}else{
				_increase = true;
			}
		}
		_handler.setBackColor(_imageView, Color.argb(_a, _r, _g, _b));
	}

	private void changeImage(int imageCount){
		if (_bitmapList != null){
			// Imageが設定されている場合は画像を変更する
			_handler.setBackImage(_imageView, _bitmapList[imageCount]);
		}
	}
}