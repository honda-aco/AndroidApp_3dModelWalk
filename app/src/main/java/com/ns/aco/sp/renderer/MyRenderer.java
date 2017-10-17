package com.ns.aco.sp.renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import com.ns.aco.sp.R;
import com.ns.aco.sp.service.ServiceContract;

import android.opengl.GLSurfaceView;

public class MyRenderer implements GLSurfaceView.Renderer {

	private final ServiceContract.Service _service;
	private GL11 _gl11 = null;
	// OpenGLの座標範囲
	private float _left = -3.28f;
	private float _right = 3.28f;
	private float _bottom = -2.8f;
	private float _top = 5.4f;
	private float _zNear = -4.0f;
	private float _zFar = 4.0f;
	private final int _charaNo_original = 0;

	public MyRenderer(ServiceContract.Service service) {
		_service = service;
	}

	public void setOrthof(float left, float right, float bottom, float top, float zNear, float zFar) {
		 _left = left;
		 _right = right;
		 _bottom = bottom;
		 _top = top;
		 _zNear = zNear;
		 _zFar = zFar;
	}

	// 描画を行う部分を記述するメソッドを追加する
	private RendererInfo rendererMain() {
		RendererInfo rendererInfo;
		// 対象オブジェクトの描画クラスを設定
		switch (_service.get_charaNo()){
			case R.drawable.gate_kohaku1_top:
			case R.drawable.gate_kohaku2_top:
			case R.drawable.gate_kohaku3_top:
			case R.drawable.gate_misaki1_top:
			case R.drawable.gate_yuko1_top:
				rendererInfo = new RendererInfoUnity(_gl11, _service);
				break;
			case R.drawable.gate_droid1_top:
			case R.drawable.gate_droid2_top:
			case R.drawable.gate_droid3_top:
					rendererInfo = new RendererInfoDroidPose(_gl11, _service);
				break;
			case R.drawable.gate_onepiece1_top:
			case R.drawable.gate_onepiece2_top:
				rendererInfo = new RendererInfoOnepiecePose(_gl11, _service);
				break;
			case R.drawable.gate_waso1_top:
			case R.drawable.gate_waso2_top:
				rendererInfo = new RendererInfoWasoPose(_gl11, _service);
				break;
			case R.drawable.gate_cardigan1_top:
			case R.drawable.gate_cardigan2_top:
				rendererInfo = new RendererInfoCardiganPose(_gl11, _service);
				break;
			case R.drawable.gate_jersey1_top:
			case R.drawable.gate_jersey2_top:
			case R.drawable.gate_cosplay1_top:
				rendererInfo = new RendererInfoJerseyPose(_gl11, _service);
				break;
			case R.drawable.gate_witch1_top:
			case R.drawable.gate_witch2_top:
			case R.drawable.gate_witch3_top:
				rendererInfo = new RendererInfoWitchPose(_gl11, _service);
				break;
			case R.drawable.gate_frog1_top:
			case R.drawable.gate_frog2_top:
				rendererInfo = new RendererInfoFrogPose(_gl11, _service);
				break;
			case R.drawable.gate_frograin1_top:
			case R.drawable.gate_frograin2_top:
				rendererInfo = new RendererInfoFrogRaincoatPose(_gl11, _service);
				break;
			case _charaNo_original:
				rendererInfo = new RendererInfoOriginal(_gl11, _service);
				break;
			default:
				rendererInfo = new RendererInfoDroidPose(_gl11, _service);
		}
		rendererInfo.setLight();
		rendererInfo.setRendererBitmapCash(true, _service.get_glViewWidth(), _service.get_glViewHeight());
		return rendererInfo;
	}

	@Override
	public void onDrawFrame(GL10 gl) {

		if (_gl11 == null){
			_gl11 = (GL11)gl;
			_gl11.glMatrixMode(GL11.GL_PROJECTION); //射影変換で描画開始
			_gl11.glLoadIdentity();

			// ViewのX、Y、Z座標の最大値（視体積）を設定する
			// _gl11.glOrthof(-3.28f, 3.28f, -2.8f, 5.4f, -4.0f, 4.0f);
			_gl11.glOrthof(_left, _right, _bottom, _top, _zNear, _zFar);
			_gl11.glMatrixMode(GL11.GL_MODELVIEW);
			_gl11.glLoadIdentity();

			//TODO 透明度を設定する
			_gl11.glEnable(GL11.GL_ALPHA_TEST); 	// 透明度の設定を有効にする
			_gl11.glEnable(GL11.GL_BLEND); 	 	// 透明度に基づく色の重ね合わせを有効にする
			_gl11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			//TODO

			_gl11.glEnable(GL11.GL_CULL_FACE);	// 片面表示を有効化
		    _gl11.glFrontFace(GL11.GL_CCW);
			_gl11.glCullFace(GL11.GL_BACK);		// 裏面を描画しない
			_gl11.glEnable(GL11.GL_TEXTURE_2D);	// テクスチャの有効化
		}
		// 背景色の初期化
		_gl11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		_gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		// OpenGLのピクセルデータからビットマップを生成
		RendererInfo rendererInfo = rendererMain();
		// プログレスバーを消去
		_service.close_progressDialog();
		// WindowManagerからViewを削除
		_service.removeView_gLSurfaceView();
		_service.removeView_imageFrontDoor();
		_service.startServiceTimerTask(rendererInfo);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		GL11 gl11 = (GL11)gl;
		gl11.glEnable(GL11.GL_CULL_FACE); // ポリゴンの裏面は描画しない設定
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		GL11 gl11 = (GL11)gl;
		gl11.glDisable(GL11.GL_DITHER);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_FASTEST);
	}
}
