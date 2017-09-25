package com.ns.aco.sp.service;

import java.util.Locale;
import java.util.Timer;

import com.ns.aco.sp.da.OperateDataBase;
import com.ns.aco.sp.renderer.MyRenderer;
import com.ns.aco.sp.R;
import com.ns.aco.sp.common.util.UtilityImageView;
import com.ns.aco.sp.renderer.RendererInfo;
import com.ns.aco.sp.toast.FukidashiToast;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.os.IBinder;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

public class ServiceHaconiwa extends Service implements ServiceContract.Service{

	private ServiceContract.Handler _serviceHandler;
	private WindowManager.LayoutParams _layoutParams;
	private String _tag = "LocalService";
	private Timer _timer = null;

	private final int _charaNo_original = 0;
	private int _charaNo;
	private boolean _savingEnergy;
	private boolean _talkAction;
	private int _language;
	private int _transparency;

	private int _windowWidth;
	private int _windowHeight;
	private int _glViewWidth;
	private int _glViewHeight;

	private boolean _serviceStop = false;

	@Override
	public int get_charaNo() {
		return _charaNo;
	}

	@Override
	public boolean is_savingEnergy() {
		return _savingEnergy;
	}

	@Override
	public boolean is_talkAction() {
		return _talkAction;
	}

	@Override
	public int get_language() {
		return _language;
	}

	@Override
	public int get_transparency(){
		return _transparency;
	}

	@Override
	public int get_windowWidth() {
		return _windowWidth;
	}

	@Override
	public int get_windowHeight() {
		return _windowHeight;
	}

	@Override
	public int get_glViewWidth() {
		return _glViewWidth;
	}

	@Override
	public int get_glViewHeight() {
		return _glViewHeight;
	}

	@Override
	public Service get_service(){
		return this;
	}

	@Override
	public boolean is_serviceStop() {
		return _serviceStop;
	}

    @Override
    public void onCreate() {
    	super.onCreate();
        Log.i(_tag, "onCreate");

        // サービスをforegroundとして登録する
//        Notification notification = new Notification(R.drawable.icon,
//        											 getText(R.string.app_name),
//        											 System.currentTimeMillis());
//
//        Intent notificationIntent = new Intent(this, MenuActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//        notification.setLatestEventInfo(this,
//        								getText(R.string.notification_title),
//                						getText(R.string.notification_message),
//                						pendingIntent);
//        startForeground(1, notification);

        // 通知なくForegroundにする場合は、下記のコードを使用
        startForeground(1, new Notification());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    	super.onStartCommand(intent, flags, startId);
    	Log.i(_tag, "onStartCommand Received start id " + startId + ": " + intent);

    	// 呼出し元からの引数を取得
		_charaNo = intent.getIntExtra(getString(R.string.service_intent1), 0);
		_savingEnergy = intent.getBooleanExtra(getString(R.string.service_intent2), false);
		_talkAction = intent.getBooleanExtra(getString(R.string.service_intent3), false);
		float coordinateRange = intent.getFloatExtra(getString(R.string.service_intent5), 4.0f);
		float interval = intent.getFloatExtra(getString(R.string.service_intent6), 0.3f);
		int size = intent.getIntExtra(getString(R.string.service_intent7), 6);
		int position = intent.getIntExtra(getString(R.string.service_intent8), 0);
		_transparency = 100;
		if (Locale.getDefault().equals(Locale.JAPAN)){
			_language = 0;
		}else{
			_language = 1;
		}

		MyRenderer renderer = new MyRenderer(this);

		// 独自モデル登録の場合は座標範囲を設定
		float magnification = 1.25f;
		if (_charaNo == _charaNo_original){
			renderer.setOrthof(-1 * (coordinateRange / 2), (coordinateRange / 2),
							                             0, coordinateRange,
							    -1 * (coordinateRange / 2), (coordinateRange / 2));
			magnification = 1.0f;
		}

		// GLSurfaceViewにMyRendererを適用
		GLSurfaceView glSurfaceView = new GLSurfaceView(this);
		glSurfaceView.setRenderer(renderer);

		// onDrawFrameの自動呼出しを抑止する
		glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
		glSurfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);

		WindowManager windowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
		// Displayのサイズを取得
		Display disp = windowManager.getDefaultDisplay();
		Point point = new Point();
		disp.getSize(point);

		_windowWidth = point.x;
		_windowHeight = point.y;

		if (_windowWidth > _windowHeight){
			_glViewWidth = _windowHeight / size;
			_glViewHeight = (int)((_windowHeight / size) * magnification);
		}else{
			_glViewWidth = _windowWidth / size;
			_glViewHeight = (int)((_windowWidth / size) * magnification);
		}

//		// TODO 仮追記-20140408-大きくして見栄え確認
//		Global.glViewSizeX = Global.glWindowWidth;
//		Global.glViewSizeY = Global.glWindowHeight;
//		// TODO 仮追記-20140408-大きくして見栄え確認

		// Viewのサイズを設定する
		_layoutParams = new WindowManager.LayoutParams(
				(int)_glViewWidth,
				(int)_glViewHeight,
				WindowManager.LayoutParams.TYPE_TOAST,
			//				WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
				// [TODO]クリックイベントを追加する場合はコメントアウトを解除
				//			WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
				//        	WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSPARENT);

		// Viewの初期表示位置を取得
		setFirstWindowCoordinate(position, _layoutParams);

		// OpenGL描画用のViewをサービスに追加
		windowManager.addView(glSurfaceView, _layoutParams);

		// OpenGL描画用Viewに被せるための画像のViewをサービスに追加
		Bitmap bitmapFront = UtilityImageView.getBitmapSize(
				getBaseContext(),
				R.drawable.gate_default,
				(int)_glViewWidth,
				(int)_glViewHeight);

		ImageView ImageFrontDoor = new ImageView(this);
		ImageFrontDoor.setScaleType(ImageView.ScaleType.FIT_XY);
		ImageFrontDoor.setImageBitmap(bitmapFront);

		windowManager.addView(ImageFrontDoor, _layoutParams);

		// OpenGLで描画した内容のビットマップを貼り付けるViewをサービスに追加
		ImageView imageGlSurface = new ImageView(this);
		// [TODO]クリックイベントを追加する場合はコメントアウトを解除
		// GlobalWindowManager.glSurfaceImage.setOnClickListener(glSurfaceListener);
//		GlobalWindowManager.glSurfaceImage.setOnTouchListener(
//				new OnTouchListener() {
//					@Override
//					public boolean onTouch(View_Fragment1 view, MotionEvent event) {
//				        if (event.getAction() == MotionEvent.ACTION_MOVE) {
//				        	ClipData data = ClipData.newPlainText("","");
//				        	view.startDrag(data, new DragShadowBuilder(view), null, 0);
//				            return true;
//				        }
//				        return false;
//					}
//				});
//		GlobalWindowManager.glSurfaceImage.setOnd(
//				new OnTouchListener() {
//					@Override
//					public boolean onTouch(View_Fragment1 view, MotionEvent event) {
//						switch (event.getAction()) {
//							case MotionEvent.ACTION_MOVE:
//								GlobalWindowManager.setNextMoveX((int) event.getRawX());
//								GlobalWindowManager.setNextMoveY((int) event.getRawY());
//								break;
//						}
//						return false;
//					}
//				});
		windowManager.addView(imageGlSurface, _layoutParams);

		_serviceHandler = new ServiceHandler(new FukidashiToast(this), windowManager, glSurfaceView, ImageFrontDoor, imageGlSurface);

		// Activityから送られた情報を受信するレシーバの設定
		BroadcastReceiver upReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				_transparency = intent.getIntExtra(getString(R.string.service_intent9), 1);
			}
		};
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(getString(R.string.service_intent_action));
		registerReceiver(upReceiver, intentFilter);

		// ステータスバーに通知を表示
		// GlobalHandler.HandlerMainActivity.showStatusBar();

		//明示的にサービスの起動、停止が決められる場合の返り値
        // return START_STICKY; 			// IntentがNULLの可能性がある
		return START_REDELIVER_INTENT; // IntentがNULLにならないが再起動までの待機時間が長い
    }

    @Override
    public void onDestroy() {
    	super.onDestroy();
        Log.i(_tag, "onDestroy");
        // foregroundを解除し、サービスを終了できる状態にする
        stopForeground(true);

		if (_timer != null){
			_timer.cancel();
		}
		removeView_gLSurfaceView();
		removeView_imageFrontDoor();
		removeView_imageGlSurface();

		_serviceStop = true;
    }

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void startServiceTimerTask(RendererInfo rendererInfo){
		if (_serviceStop == false){
			ServiceTimerTask _timerTask = new ServiceTimerTask(this, rendererInfo, new OperateDataBase(this), _layoutParams, 0.55);
			_timer = new Timer(true);
			_timer.schedule(_timerTask, 250, 250);
		}
	}

	@Override
	public void updateWindow(Bitmap bitmap, float alpha, WindowManager.LayoutParams layoutParams) {
		if (_serviceStop == false) {
			_serviceHandler.updateWindow(bitmap, alpha, layoutParams);
		}
	}

	@Override
	public void showFukidashiToast(String message) {
		if (_serviceStop == false) {
			_serviceHandler.showFukidashiToast(message);
		}
	}

	@Override
	public void removeView_gLSurfaceView() {
		if (_serviceStop == false) {
			_serviceHandler.removeView_gLSurfaceView();
		}
	}

	@Override
	public void removeView_imageGlSurface() {
		if (_serviceStop == false) {
			_serviceHandler.removeView_imageGlSurface();
		}
	}

	@Override
	public void removeView_imageFrontDoor() {
		if (_serviceStop == false) {
			_serviceHandler.removeView_imageFrontDoor();
		}
	}

	@Override
	public void setMaxValueProgressDialog(int value) {
		if (_serviceStop == false) {
			Intent intent = new Intent();
			intent.putExtra(getString(R.string.activity_intent1), 0);
			intent.putExtra(getString(R.string.activity_intent2), value);
			intent.setAction(getString(R.string.activity_intent_action));
			getBaseContext().sendBroadcast(intent);
		}
	}

	@Override
	public void incrementProgressDialog(int value) {
		if (_serviceStop == false) {
			Intent intent = new Intent();
			intent.putExtra(getString(R.string.activity_intent1), 1);
			intent.putExtra(getString(R.string.activity_intent2), value);
			intent.setAction(getString(R.string.activity_intent_action));
			getBaseContext().sendBroadcast(intent);
		}
	}

	@Override
	public void closeProgressDialog() {
		if (_serviceStop == false) {
			Intent intent = new Intent();
			intent.putExtra(getString(R.string.activity_intent1), 2);
			intent.setAction(getString(R.string.activity_intent_action));
			getBaseContext().sendBroadcast(intent);
		}
	}

	// ウインドウの初期表示位置
	private void setFirstWindowCoordinate(int position, WindowManager.LayoutParams layoutParams){
		switch (position){
			case 0:
				layoutParams.x = (int) (-1 * (_windowWidth - _glViewWidth) / 2);
				layoutParams.y = (int) (-1 * (_windowHeight - _glViewHeight) / 2);
				break;
			case 1:
				layoutParams.x = (int) (_windowWidth - _glViewWidth) / 2;
				layoutParams.y = (int) (-1 * (_windowHeight - _glViewHeight) / 2);
				break;
			case 2:
				layoutParams.x = (int) (-1 * (_windowWidth - _glViewWidth) / 2);
				layoutParams.y = (int) (_windowHeight - _glViewHeight) / 2;
				break;
			case 3:
				layoutParams.x = (int) (_windowWidth - _glViewWidth) / 2;
				layoutParams.y = (int) (_windowHeight - _glViewHeight) / 2;
				break;
		}
	}
}