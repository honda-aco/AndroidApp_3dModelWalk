package com.ns.aco.sp.renderer;

import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.graphics.Bitmap;

import com.ns.aco.sp.common.delegate.DelegateIF;
import com.ns.aco.sp.service.ServiceContract;

public class RendererInfoDroidPose extends RendererInfoDroid {
	// コンストラクタ
	public RendererInfoDroidPose(GL11 gl11, ServiceContract.Service service) {
		super(gl11, service);
//		_getBitmapBanzai.add(getBitmapMotionLessPoze());
//		_getBitmapBanzai.add(getBitmapBanzaiPoze());
		_getBitmapRaiseHand.add(getBitmapRaiseHandRPoze());
		_getBitmapRaiseHand.add(getBitmapRaiseHandLPoze());
	}

//	// ビットマップ取得関数格納用
//	ArrayList<DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>>
//		_getBitmapBanzai = new ArrayList<DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>>();

	// ビットマップ取得関数格納用
	ArrayList<DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>>
		_getBitmapRaiseHand = new ArrayList<DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>>();

	//頂点ファイル名（頂点ファイル名、法線ファイル名、テクスチャファイル名）
//	//全身（万歳ポーズ）
//	private final VboInfo _vboInfoBodyBanzai = new VboInfo (
//		"01_Droid/Body/V_BodyBanzai.txt",
//		"01_Droid/Body/VN_BodyBanzai.txt",
//		"01_Droid/Body/VT_BodyBanzai.txt"
//	);
	//全身（右手上げポーズ）
	private final VboInfo _vboInfoBodyRaiseHandR = new VboInfo (
		"01_Droid/Body/V_BodyRaiseHandR.txt",
		"01_Droid/Body/VN_BodyRaiseHandR.txt",
		"01_Droid/Body/VT_BodyRaiseHandR.txt"
	);
	//全身（左手上げポーズ）
	private final VboInfo _vboInfoBodyRaiseHandL = new VboInfo (
		"01_Droid/Body/V_BodyRaiseHandL.txt",
		"01_Droid/Body/VN_BodyRaiseHandL.txt",
		"01_Droid/Body/VT_BodyRaiseHandL.txt"
	);

	//ファイル名一覧取得
	private VboInfo[] _vboInfos = new VboInfo[]{
//		// 万歳ポーズ
//		_vboInfoBodyBanzai,
//		// 立ちポーズ
//		_vboInfoBodyMotionLess,
		// 右手上げ
		_vboInfoBodyRaiseHandR,
		// 左手上げ
		_vboInfoBodyRaiseHandL,
	};

	// 描画するVBO数取得
	@Override
	public int getDrawBitmapCount(){
		int setCount = _vboInfos.length;
		int drawCount = _getBitmapRaiseHand.size();
		return super.getDrawBitmapCount() + setCount + drawCount;
	}

	@Override
	public boolean isPose() {
		return true;
	}
	
	// キャッシュを使用したビットマップ取得（生成）
	@Override
	public void setRendererBitmapCash(boolean init,int pixelWidth, int pixelHeight) {
		// テクスチャおよびモデルデータを読込む
		if (init){
			// プログレスバーの最大値を設定
			_service.setMaxValue_progressDialog(getDrawBitmapCount());
			loadRendererModel();
		}
		boolean success = createBitmapList(init, pixelWidth, pixelHeight);
		// 描画が成功した場合のみ、親クラスの処理を行う
		if (success){
			super.setRendererBitmapCash(false, pixelWidth, pixelHeight);
		}
		// VBOの削除処理を中継関数を介して行う
		deleteVboInfos(_vboInfos);
	}

	// 3Dモデルの描画
	private boolean createBitmapList(boolean initPixels, int pixelWidth, int pixelHeight){
		// VBOへの登録処理を中継関数を介して行う
		for (int i = 0; i < _vboInfos.length; i++){
			_vboInfos[i] = setVboInfos(_vboInfos[i], true);
			// VBOへの登録処理の結果を判定
			if (_vboInfos == null){
				return false;
			}else{
				// プログレスバーの現在値を更新
				_service.increment_progressDialog(1);
			}
		}

//		// ☆☆☆万歳ポーズ格納☆☆☆
//		Bitmap[] banzaiPoseList = new Bitmap[_getBitmapBanzai.size()];
//		for (int i = 0; i < _getBitmapBanzai.size(); i++){
//			banzaiPoseList[i] = _getBitmapBanzai.get(i).invoke(initPixels, pixelWidth, pixelHeight);
//			if (banzaiPoseList[i] == null){
//				return false;
//			}else{
//				// プログレスバーの現在値を更新
//				GlobalHandler.HandlerMainActivity.increment_progressDialog(1);
//			}
//			initPixels = false;
//		}

		// ☆☆☆片手上げポーズ格納☆☆☆
		Bitmap[] raiseHandPoseList = new Bitmap[_getBitmapRaiseHand.size()];
		for (int i = 0; i < _getBitmapRaiseHand.size(); i++){
			raiseHandPoseList[i] = _getBitmapRaiseHand.get(i).invoke(initPixels, pixelWidth, pixelHeight);
			if (raiseHandPoseList[i] == null){
				return false;
			}else{
				// プログレスバーの現在値を更新
				_service.increment_progressDialog(1);
			}
			initPixels = false;
		}

		// ポーズの画像を設定
//		setBitmapPoseCash(banzaiPoseList);
		setBitmapPoseDB(raiseHandPoseList);
		return true;
	}

//	private DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap> getBitmapBanzaiPoze(){
//		return new DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>(){
//			public Bitmap invoke(Boolean initPixels, Integer pixelWidth, Integer pixelHeight){
//				// 描画情報を初期化
//				_gl11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
//				_gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
//
//				boolean resultDraw = _draw3DObject.draw3D(_bodyTexture, _vboInfoBodyBanzai);
//
//				if (resultDraw){
//					return capture(initPixels, pixelWidth, pixelHeight);
//				}else{
//					return null;
//				}
//			}
//		};
//	}
//
//	private DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap> getBitmapMotionLessPoze(){
//		return new DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>(){
//			public Bitmap invoke(Boolean initPixels, Integer pixelWidth, Integer pixelHeight){
//				// 描画情報を初期化
//				_gl11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
//				_gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
//
//				boolean resultDraw = _draw3DObject.draw3D(_bodyTexture, _vboInfoBodyMotionLess);
//
//				if (resultDraw){
//					return capture(initPixels, pixelWidth, pixelHeight);
//				}else{
//					return null;
//				}
//			}
//		};
//	}

	private DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap> getBitmapRaiseHandRPoze(){
		return new DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>(){
			public Bitmap invoke(Boolean initPixels, Integer pixelWidth, Integer pixelHeight){
				// 描画情報を初期化
				_gl11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
				_gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

				boolean resultDraw = _draw3DObject.draw3D(_bodyTexture, _vboInfoBodyRaiseHandR);

				if (resultDraw){
					return capture(initPixels, pixelWidth, pixelHeight);
				}else{
					return null;
				}
			}
		};
	}

	private DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap> getBitmapRaiseHandLPoze(){
		return new DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>(){
			public Bitmap invoke(Boolean initPixels, Integer pixelWidth, Integer pixelHeight){
				// 描画情報を初期化
				_gl11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
				_gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

				boolean resultDraw = _draw3DObject.draw3D(_bodyTexture, _vboInfoBodyRaiseHandL);

				if (resultDraw){
					return capture(initPixels, pixelWidth, pixelHeight);
				}else{
					return null;
				}
			}
		};
	}
}
