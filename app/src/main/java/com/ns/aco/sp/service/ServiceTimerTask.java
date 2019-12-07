package com.ns.aco.sp.service;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.WindowManager;

import com.ns.aco.sp.da.DataAccessContract;
import com.ns.aco.sp.renderer.RendererInfo;

import java.util.Random;
import java.util.TimerTask;

public class ServiceTimerTask extends TimerTask {

	private final ServiceContract.Service _service;
	private DataAccessContract _dataAccess = null;
	private RendererInfo _rendererInfo;

	private static Boolean _stopMoveView = false;
	private final WindowManager.LayoutParams _layoutParams;
	private int _glViewSizePart = 80;	// 移動割合（（画面サイズ / 移動割合）を基本として引数で調整することを可能とする）

	// 移動方向を360度可能に変更
	private int _south;
	private int _southEast;
	private int _east;
	private int _northEast;
	private int _north;
	private int _northWest;
	private int _west;
	private int _southWest;
	// 移動方向設定配列
	private Integer[] _forwardDirection;
	// ランダム値取得用オブジェクト
	private Random _random = new Random();
	// 同一方向に対して何回目のループであるか、繰返した回数を保持
	private int _stepCount = 0;
	// 同一方向に対するループを何回で終了するか、上限回数を保持
	private int _maxStepCount = 0;
	// 次に移動する方向（角度）
	private int _nextDirection = 0;
	// ウインドウの移動距離の割合（割合のため、初期値は1）
	// 移動方向によってX軸、Y軸の移動距離に下記の移動割合を掛ける
	private double _moveXPercent = 1;
	private double _moveYPercent = 1;
	// 移動距離
	private int _moveLength = 0;
	// トーク間隔カウント用変数
	private int _talkCount = 0;
	// トークタイミング設定定数
	private int _talkTiming = 5;
	// ポーズ表示中セット数
	private int _poseCount = 0;
	private boolean _runIntervalSW = true;

	public ServiceTimerTask(ServiceContract.Service service,
							RendererInfo rendererInfo,
							DataAccessContract dataAccess,
							WindowManager.LayoutParams layoutParams,
							double movePercent
	){
		_service = service;
		_rendererInfo = rendererInfo;
		_dataAccess = dataAccess;
		_dataAccess.open();
		_layoutParams = layoutParams;
		// 移動距離を計算して保持
		if (_service.get_windowWidth() > _service.get_windowHeight()){
			_moveLength = (int)_service.get_windowHeight() / (_glViewSizePart);
		}else{
			_moveLength = (int)_service.get_windowWidth() / (_glViewSizePart);
		}
		_moveLength = (int) (_moveLength * movePercent);

		// 省エネモード時は進行方向を減らす
		if (_service.is_savingEnergy()){
			_forwardDirection = new Integer[]{
				0, 45, 90, 135, 180, 225, 270, 315};

			// 画面端時の進行方向を設定
			_south = 0;
			_southEast = 45;
			_east = 90;
			_northEast = 135;
			_north = 180;
			_northWest = 225;
			_west = 270;
			_southWest = 315;
		}else{
			_forwardDirection = new Integer[]{
//				0, 15, 30, 45, 60, 75, 90, 105, 120,
//				135, 150, 165, 180, 195, 210, 225, 240,
//				255, 270, 285, 300, 315, 330, 345};
				0, 30, 60, 90, 120, 150, 180, 210, 240, 270, 300, 330};

			// 画面端時の進行方向を設定
			_south = 0;
			_southEast = 60;
			_east = 90;
			_northEast = 120;
			_north = 180;
			_northWest = 240;
			_west = 270;
			_southWest = 300;
		}
	}

	// X座標の範囲チェック
	// ViewのX座標と画面幅を比較し、次にViewが移動すべきX座標を返す
	// ※ViewのX座標の絶対値が画面幅の半分より大きいかを確認する
	private int checkRangeX(){
		if (_layoutParams.x >= 0){
			if ((int)(_service.get_windowWidth() / 2) <= _layoutParams.x + (_service.get_glViewWidth() / 2)){
				return -1;
			}
		}else{
			if ((int)(_service.get_windowWidth() / 2) <= ((-1 * _layoutParams.x) + (_service.get_glViewWidth() / 2))){
				return 1;
			}
		}
		return 0;
	}

	// Y座標の範囲チェック
	// ViewのY座標と画面縦幅を比較し、次にViewが移動すべきY座標を返す
	// ※ViewのY座標の絶対値が画面幅の半分より大きいかを確認する
	private int checkRangeY(){
		if (_layoutParams.y >= 0){
			// windowManageLPのXY座標はViewの中心座標の模様
			// そのため、画面の範囲内か確認する際に加算するViewサイズはViewサイズの半分でよい
			if ((int)(_service.get_windowHeight() / 2) <= _layoutParams.y + (_service.get_glViewHeight() / 2)){
				return -1;
			}
		}else{
			if ((int)(_service.get_windowHeight() / 2) <= ((-1 * _layoutParams.y) + (_service.get_glViewHeight() / 2))){
				return 1;
			}
		}
		return 0;
	}

	@Override
	public void run(){
		Log.d(new Throwable().getStackTrace()[0].getMethodName(),"まだいるよ");

		if (_rendererInfo.isFinishCashSet() == false) {
			return;
		}

		if (_runIntervalSW == false){
			_runIntervalSW = true;
			return;
		}

		if (_service.get_transparency() == 0){
			if (_stopMoveView){
				return;
			}else{
				_stopMoveView = true;
			}
		}else{
			_stopMoveView = false;
		}

		Bitmap bitmapCash = null;
		// ポーズ用ビットマップを取得
		if (_rendererInfo.isPose()){
			bitmapCash = getBitmapPose();
		}

		if (bitmapCash != null){
			//ポーズの動作は速過ぎると違和感あるので2回に1回動作するようスイッチを切り替える
			_runIntervalSW = false;
		}else{
			bitmapCash = getBitmapWalk();
			// 歩く動作に緩急をつける
			if (_rendererInfo.getPendulumCount3() == 1){
				_runIntervalSW = false;
			}
		}
		// ウィンドウの表示内容を更新する
		_service.updateWindow(bitmapCash, (float) _service.get_transparency() / 100, _layoutParams);
		// トークを表示
		if (_service.is_talkAction()){
			talk();
			Log.d(new Throwable().getStackTrace()[0].getMethodName(), "吹き出しトースト表示");
		}
	}

	private Bitmap getBitmapPose(){
		Bitmap bitmapCash = null;
		if (_poseCount > 0){
			// 渡すべきBitmapはRendererInfo側が判断し、
			// 2コマの画像で構成されているポーズなら3枚目を要求した際にNULLを返す
			bitmapCash = _rendererInfo.getBitmapPoseDB();
			if (bitmapCash == null){
//				// 1回だと寂しいため、ポーズは2セット行う
//				if (_poseCount == 1){
//					bitmapCash = _rendererInfo.getBitmapPoseDB();
//					_poseCount += 1;
//				}else {
					_poseCount = 0;
//				}
			}
		}

		if (bitmapCash == null){
			// 30歩に1回程度、ポーズを表示する
			if (_random.nextInt(30) == 0) {
				bitmapCash = _rendererInfo.getBitmapPoseDB();
				_poseCount = 1;
			}
		}
		return bitmapCash;
	}

	private Bitmap getBitmapWalk(){
		// ウィンドウ位置を計算・設定
		int direct = setPointWindowManager();
		return  _rendererInfo.getRendererBitmapDB(direct);
	}

    private int setPointWindowManager(){
		int compulsoryDirection = getCompulsoryDirection();
		if (compulsoryDirection != -1) {
			// XYの範囲を確認し、端に寄っている場合はステップカウントを初期化の上、中央方向へ強制的に戻る
			// 移動する方向は端と逆方向に固定
			_nextDirection = compulsoryDirection;
			_maxStepCount = 5;
			// 三角関数を用いて、角度からX軸、Y軸の移動割合を算出
			double radians = Math.toRadians(_nextDirection);
			_moveXPercent = Math.sin(radians);
			_moveYPercent = Math.cos(radians);
		}else {
			if (_stepCount == 0){
				// 移動する方向をランダムで決定
				// [北：0度、東：90度、南：180度、西：270度]
				_nextDirection = _forwardDirection[_random.nextInt(_forwardDirection.length)];
				// 同一方向へ5回から20回連続で移動させる
				_maxStepCount = 5 + _random.nextInt(15);
				// 三角関数を用いて、角度からX軸、Y軸の移動割合を算出
				double radians = Math.toRadians(_nextDirection);
				_moveXPercent = Math.sin(radians);
				_moveYPercent = Math.cos(radians);
			}
		}

		// 規定回数を超えたら進行方向を変更するべくカウンタをリセットする
		if (_maxStepCount < _stepCount){
			_stepCount = 0;
		}else{
			_layoutParams.x += ((int) (_moveLength * _moveXPercent));
			_layoutParams.y += ((int) (_moveLength * _moveYPercent));
			_stepCount += 1;
		}

		return _nextDirection;
    }

	private int getCompulsoryDirection(){
    	int rtnDirection = -1;
		int xDirection = checkRangeX();
		int yDirection = checkRangeY();

    	switch (xDirection){
			case -1:
				// Xは西方向固定
		    	switch (yDirection){
					case -1:
						rtnDirection =  _northWest;
						break;
					case 1:
						rtnDirection =  _southWest;
						break;
					case 0:
						rtnDirection =  _west;
						break;
		    	}
		    	break;
			case 1:
				// Xは東方向固定
		    	switch (yDirection){
					case -1:
						rtnDirection =  _northEast;
						break;
					case 1:
						rtnDirection =  _southEast;
						break;
					case 0:
						rtnDirection =  _east;
						break;
		    	}
		    	break;
			case 0:
		    	switch (yDirection){
					case -1:
						rtnDirection =  _north;
						break;
					case 1:
						rtnDirection =  _south;
						break;
		    	}
		    	break;
    	}
    	return rtnDirection;
	}
	// 角度を8方向に丸める
	private int roundDirect(int direct){
		if (direct <= 25){
			return _south;
		}else if(direct <= 70){
			return _southEast;
		}else if(direct <= 115){
			return _east;
		}else if(direct <= 160){
			return _northEast;
		}else if(direct <= 205){
			return _north;
		}else if(direct <= 250){
			return _northWest;
		}else if(direct <= 295){
			return _west;
		}else if(direct <= 340){
			return _southWest;
		}else{
			return _south;
		}
	}

	private void talk(){
		if (_talkCount >= _talkTiming){
			String[] talkText = _dataAccess.getRandom_CHARA_TALK_YUCCO();
			_service.showFukidashiToast(talkText[_service.get_language()]);
			_talkTiming = 30 + _random.nextInt(150);
			_talkCount = 0;
		}else{
			_talkCount += 1;
		}
	}

	@Override
	public boolean cancel(){
		_dataAccess.close();
		return super.cancel();
	};
}