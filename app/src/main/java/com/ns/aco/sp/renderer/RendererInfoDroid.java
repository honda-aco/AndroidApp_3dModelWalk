package com.ns.aco.sp.renderer;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import com.ns.aco.sp.R;
import com.ns.aco.sp.common.delegate.DelegateIF;
import com.ns.aco.sp.service.ServiceContract;

import android.graphics.Bitmap;

public class RendererInfoDroid extends RendererInfoBase {

	// コンストラクタ
	public RendererInfoDroid(GL11 gl11, ServiceContract.Service service) {
		super(gl11, service, true);
		_getBitmapWalk.add(getBitmapWalkR());
		_getBitmapWalk.add(getBitmapMotionLess());
		_getBitmapWalk.add(getBitmapWalkL());
	}

	// ビットマップ取得関数格納用
	ArrayList<DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>>
		_getBitmapWalk = new ArrayList<DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>>();


	//頂点ファイル名（頂点ファイル名、法線ファイル名、テクスチャファイル名）
	//歩く（右足上げ）ポーズ
	protected final VboInfo _vboInfoBodyWalkR = new VboInfo(
		"01_Droid/Body/V_BodyWalkR.txt",
		"01_Droid/Body/VN_BodyWalkR.txt",
		"01_Droid/Body/VT_BodyWalkR.txt"
	);
	//立ちポーズ
	protected final VboInfo _vboInfoBodyMotionLess = new VboInfo(
		"01_Droid/Body/V_BodyMotionLess.txt",
		"01_Droid/Body/VN_BodyMotionLess.txt",
		"01_Droid/Body/VT_BodyMotionLess.txt"
	);
	//歩く（左足上げ）ポーズ
	protected final VboInfo _vboInfoBodyWalkL = new VboInfo(
		"01_Droid/Body/V_BodyWalkL.txt",
		"01_Droid/Body/VN_BodyWalkL.txt",
		"01_Droid/Body/VT_BodyWalkL.txt"
	);

	// 描画するVBO数取得
	@Override
	public int getDrawBitmapCount(){
		int setCount = _vboInfos.length;
		return 3 * forwardDirection.length + setCount;
	}

	@Override
	public boolean isPose() {
		return false;
	}

	//ファイル名一覧取得
	private VboInfo[] _vboInfos = new VboInfo[]{
		// 歩く（右足上げ）ポーズ
		_vboInfoBodyWalkR,
		// 立ちポーズ
		_vboInfoBodyMotionLess,
		// 歩く（左足上げ）ポーズ
		_vboInfoBodyWalkL,
	};

	// 3Dモデルを描画
	// 頂点ファイル名（頂点ファイル名、法線ファイル名、テクスチャファイル名）
	// Mは負数（マイナス）を表し、負数の方が前方へ伸びた状態を示す
	protected void loadRendererModel(){
		// 起動されたタイプによってテクスチャを変更する
		if (_service.get_charaNo() == R.drawable.gate_droid1_top){
			_bodyTexture = _draw3DObject.loadTexture(R.drawable.bodylayout_droid1, _service.get_windowWidth(), _service.get_windowHeight());
		}else if (_service.get_charaNo() == R.drawable.gate_droid2_top){
			_bodyTexture = _draw3DObject.loadTexture(R.drawable.bodylayout_droid2, _service.get_windowWidth(), _service.get_windowHeight());
		}else{
			_bodyTexture = _draw3DObject.loadTexture(R.drawable.bodylayout_droid3, _service.get_windowWidth(), _service.get_windowHeight());
		}
	}

	// ビットマップ生成・キャッシュ化
	@Override
	public void setRendererBitmapCash(boolean init, int pixelWidth, int pixelHeight) {
		// テクスチャおよびモデルデータを読込む
		if (init){
			// プログレスバーの最大値を設定
			_service.setMaxValue_progressDialog(getDrawBitmapCount());
			loadRendererModel();
		}
		// レンダリングを実施しキャッシュに登録する
		boolean success = createRendererBitmap(init, pixelWidth, pixelHeight);
		// VBOの削除処理を中継関数を介して行う
		deleteVboInfos(_vboInfos);
		// OpenGLに取り込んだテクスチャを削除する
		_draw3DObject.deleteTexture(_textures);
		// 描画が成功した場合のみ、キャッシュ設定フラグを立てる
		if (success){
			// キャッシュ設定フラグを立てる
			_finishCashSet = true;
		}else{
			return;
		}
	}

	// 3Dモデルの描画
	public boolean createRendererBitmap(boolean initPixels, int pixelWidth, int pixelHeight){
		// VBOへの登録処理を中継関数を介して行う
		for (int i = 0; i < _vboInfos.length; i++){
			_vboInfos[i] = setVboInfos(_vboInfos[i], true);
			// VBOへの登録処理の結果を判定
			if (_vboInfos[i] == null){
				return false;
			}else{
				// プログレスバーの現在値を更新
				_service.increment_progressDialog(1);
			}
		}

		// モデルを回転する
		_gl11.glRotatef(-0.5f, 0.0f, 0.0f, 1.0f );
		for (int i = 0; i < forwardDirection.length; i++){
			// モデルを回転
			_gl11.glRotatef(forwardDirection[i], 0, 1, 0);

			CashListNo[] cashListNo = CashListNo.values();
			for (int j = 0; j < _getBitmapWalk.size(); j++){
				Bitmap bitmap = _getBitmapWalk.get(j).invoke(initPixels, pixelWidth, pixelHeight);
				if (bitmap != null){
					setDirectBitmapDB(
							convDirectToDirectForCash(forwardDirection[i]),
							cashListNo[j],
							CashItemNo.no0,
							bitmap);
				}else{
					return false;
				}
				initPixels = false;
				// プログレスバーの現在値を更新
				_service.increment_progressDialog(1);
			}
			// モデルの回転を終了する
			_gl11.glRotatef(-forwardDirection[i], 0, 1, 0);
		}
		_gl11.glRotatef(0.5f, 0.0f, 0.0f, 1.0f );
		return true;
	}

	@Override
	public void setLight() {
		// ライトの設定
		_gl11.glEnable(GL11.GL_LIGHTING);	// 光源の設定を有効にする

	    // ライトの光源の設定
		// 「GL_POSITION」は、「同次座標系」で記述する
		// つまり、４つの要素は(x, y, z, w)の順番になっている。
		// 通常の座標を求めたい場合は、(x/w, y/w, z/w)で得られる。
		// [例]----------------------------------------------------------------------------
		// (45,30,90,3) → (45/3, 30/3, 90/3) → (15, 10, 30) ※これは(15, 10, 30 ,1)と等しい。
		// もし、w=0の場合は原点(0,0,0)から、(x,y,z)に向かって直線を引いた無限遠方の点となっている。
		// --------------------------------------------------------------------------------
		// つまり、w = 0 → 「無限遠方」　→　「太陽のような光源(並行光源)」
		// それ以外 → 「位置が決まる」　→　「点光源」となる。

		float[] position0 = { 0.0f, 1.5f, 3.0f, 0.0f };
		float[] ambient = { 1.0f, 1.0f, 1.0f, 1.0f };

		_gl11.glEnable(GL11.GL_LIGHT0);		// ライト０を使用する
	    _gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_POSITION, position0, 0);
	    _gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_AMBIENT, ambient, 0);
	}

	private DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap> getBitmapWalkR(){
		return new DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>(){
			public Bitmap invoke(Boolean initPixels, Integer pixelWidth, Integer pixelHeight){
				// 描画情報を初期化
				_gl11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
				_gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

				// 歩く（右足上げ）ポーズ
				boolean resultDraw = _draw3DObject.draw3D(_bodyTexture, _vboInfoBodyWalkR);

				if (resultDraw){
					return capture(initPixels, pixelWidth, pixelHeight);
				}else{
					return null;
				}
			}
		};
	}

	private DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap> getBitmapMotionLess(){
		return new DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>(){
			public Bitmap invoke(Boolean initPixels, Integer pixelWidth, Integer pixelHeight){
				// 描画情報を初期化
				_gl11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
				_gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

				// 立ちポーズ
				boolean resultDraw = _draw3DObject.draw3D(_bodyTexture, _vboInfoBodyMotionLess);

				if (resultDraw){
					return capture(initPixels, pixelWidth, pixelHeight);
				}else{
					return null;
				}
			}
		};
	}

	private DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap> getBitmapWalkL(){
		return new DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>(){
			public Bitmap invoke(Boolean initPixels, Integer pixelWidth, Integer pixelHeight){
				// 描画情報を初期化
				_gl11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
				_gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

				// 歩く（左足上げ）ポーズ
				boolean resultDraw = _draw3DObject.draw3D(_bodyTexture, _vboInfoBodyWalkL);

				if (resultDraw){
					return capture(initPixels, pixelWidth, pixelHeight);
				}else{
					return null;
				}
			}
		};
	}
}
