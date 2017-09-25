package com.ns.aco.sp.renderer;

import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import com.ns.aco.sp.R;
import com.ns.aco.sp.common.delegate.DelegateIF;
import com.ns.aco.sp.service.ServiceContract;

import android.graphics.Bitmap;

public class RendererInfoUnity extends RendererInfoBase {

	// コンストラクタ
	public RendererInfoUnity(GL11 gl11, ServiceContract.Service service) {
		super(gl11, service, false);
		_getBitmapWalk.add(getBitmapWalkR());
		_getBitmapWalk.add(getBitmapMotionLess());
		_getBitmapWalk.add(getBitmapWalkL());
	}

	// ビットマップ取得関数格納用
	ArrayList<DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>>
		_getBitmapWalk = new ArrayList<DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>>();

	//頂点ファイル名（頂点ファイル名、法線ファイル名、テクスチャファイル名）
	//歩く（右足上げ）ポーズ
	protected final VboInfo _vboInfoBodyKohakuWalkR = new VboInfo(
		"03_Unity/Body/Kohaku/V_BodyKohakuWalkR.txt",
		"03_Unity/Body/Kohaku/VN_BodyKohakuWalkR.txt",
		"03_Unity/Body/Kohaku/VT_BodyKohakuWalkR.txt"
	);

	protected final VboInfo _vboInfoBodyUniformWalkR = new VboInfo(
		"03_Unity/Body/Uniform/V_BodyUniformWalkR.txt",
		"03_Unity/Body/Uniform/VN_BodyUniformWalkR.txt",
		"03_Unity/Body/Uniform/VT_BodyUniformWalkR.txt"
	);

	protected final VboInfo _vboInfoHeadKohakuWalkR = new VboInfo(
		"03_Unity/Head/Kohaku/V_HeadKohakuWalkR.txt",
		"03_Unity/Head/Kohaku/VN_HeadKohakuWalkR.txt",
		"03_Unity/Head/Kohaku/VT_HeadKohakuWalkR.txt"
	);

	protected final VboInfo _vboInfoHeadMisakiWalkR = new VboInfo(
		"03_Unity/Head/Misaki/V_HeadMisakiWalkR.txt",
		"03_Unity/Head/Misaki/VN_HeadMisakiWalkR.txt",
		"03_Unity/Head/Misaki/VT_HeadMisakiWalkR.txt"
	);

	protected final VboInfo _vboInfoHeadYukoWalkR = new VboInfo(
		"03_Unity/Head/Yuko/V_HeadYukoWalkR.txt",
		"03_Unity/Head/Yuko/VN_HeadYukoWalkR.txt",
		"03_Unity/Head/Yuko/VT_HeadYukoWalkR.txt"
	);

	//立ちポーズ
	protected final VboInfo _vboInfoBodyKohakuMotionLess = new VboInfo(
		"03_Unity/Body/Kohaku/V_BodyKohakuMotionLess.txt",
		"03_Unity/Body/Kohaku/VN_BodyKohakuMotionLess.txt",
		"03_Unity/Body/Kohaku/VT_BodyKohakuMotionLess.txt"
	);

	protected final VboInfo _vboInfoBodyUniformMotionLess = new VboInfo(
		"03_Unity/Body/Uniform/V_BodyUniformMotionLess.txt",
		"03_Unity/Body/Uniform/VN_BodyUniformMotionLess.txt",
		"03_Unity/Body/Uniform/VT_BodyUniformMotionLess.txt"
	);

	protected final VboInfo _vboInfoHeadKohakuMotionLess = new VboInfo(
		"03_Unity/Head/Kohaku/V_HeadKohakuMotionLess.txt",
		"03_Unity/Head/Kohaku/VN_HeadKohakuMotionLess.txt",
		"03_Unity/Head/Kohaku/VT_HeadKohakuMotionLess.txt"
	);

	protected final VboInfo _vboInfoHeadMisakiMotionLess = new VboInfo(
		"03_Unity/Head/Misaki/V_HeadMisakiMotionLess.txt",
		"03_Unity/Head/Misaki/VN_HeadMisakiMotionLess.txt",
		"03_Unity/Head/Misaki/VT_HeadMisakiMotionLess.txt"
	);

	protected final VboInfo _vboInfoHeadYukoMotionLess = new VboInfo(
		"03_Unity/Head/Yuko/V_HeadYukoMotionLess.txt",
		"03_Unity/Head/Yuko/VN_HeadYukoMotionLess.txt",
		"03_Unity/Head/Yuko/VT_HeadYukoMotionLess.txt"
	);

	//歩く（左足上げ）ポーズ
	protected final VboInfo _vboInfoBodyKohakuWalkL = new VboInfo(
		"03_Unity/Body/Kohaku/V_BodyKohakuWalkL.txt",
		"03_Unity/Body/Kohaku/VN_BodyKohakuWalkL.txt",
		"03_Unity/Body/Kohaku/VT_BodyKohakuWalkL.txt"
	);

	protected final VboInfo _vboInfoBodyUniformWalkL = new VboInfo(
		"03_Unity/Body/Uniform/V_BodyUniformWalkL.txt",
		"03_Unity/Body/Uniform/VN_BodyUniformWalkL.txt",
		"03_Unity/Body/Uniform/VT_BodyUniformWalkL.txt"
	);

	protected final VboInfo _vboInfoHeadKohakuWalkL = new VboInfo(
		"03_Unity/Head/Kohaku/V_HeadKohakuWalkL.txt",
		"03_Unity/Head/Kohaku/VN_HeadKohakuWalkL.txt",
		"03_Unity/Head/Kohaku/VT_HeadKohakuWalkL.txt"
	);

	protected final VboInfo _vboInfoHeadMisakiWalkL = new VboInfo(
		"03_Unity/Head/Misaki/V_HeadMisakiWalkL.txt",
		"03_Unity/Head/Misaki/VN_HeadMisakiWalkL.txt",
		"03_Unity/Head/Misaki/VT_HeadMisakiWalkL.txt"
	);

	protected final VboInfo _vboInfoHeadYukoWalkL = new VboInfo(
		"03_Unity/Head/Yuko/V_HeadYukoWalkL.txt",
		"03_Unity/Head/Yuko/VN_HeadYukoWalkL.txt",
		"03_Unity/Head/Yuko/VT_HeadYukoWalkL.txt"
	);

	// 描画するVBO数取得
	@Override
	public int getDrawBitmapCount(){
		int setCount = 0;
		if (_service.get_charaNo() == R.drawable.gate_kohaku1_top || _service.get_charaNo() == R.drawable.gate_kohaku2_top){
			setCount = _vboInfos_kohaku.length;
		}else if (_service.get_charaNo() == R.drawable.gate_kohaku3_top){
			setCount = _vboInfos_kohaku_uniform.length;
		}else if (_service.get_charaNo() == R.drawable.gate_misaki1_top){
			setCount = _vboInfos_misaki.length;
		}else if (_service.get_charaNo() == R.drawable.gate_yuko1_top){
			setCount = _vboInfos_yuko.length;
		}else{
			setCount = _vboInfos_kohaku.length;
		}
		return 3 * forwardDirection.length + setCount;
	}

	@Override
	public boolean isPose() {
		return false;
	}

	//ファイル名一覧取得
	private VboInfo[] _vboInfos_kohaku = new VboInfo[]{
		// 歩く（右足上げ）ポーズ
		_vboInfoBodyKohakuWalkR,
		_vboInfoHeadKohakuWalkR,
		// 立ちポーズ
		_vboInfoBodyKohakuMotionLess,
		_vboInfoHeadKohakuMotionLess,
		// 歩く（左足上げ）ポーズ
		_vboInfoBodyKohakuWalkL,
		_vboInfoHeadKohakuWalkL,
	};

	private VboInfo[] _vboInfos_kohaku_uniform = new VboInfo[]{
		// 歩く（右足上げ）ポーズ
		_vboInfoBodyUniformWalkR,
		_vboInfoHeadKohakuWalkR,
		// 立ちポーズ
		_vboInfoBodyUniformMotionLess,
		_vboInfoHeadKohakuMotionLess,
		// 歩く（左足上げ）ポーズ
		_vboInfoBodyUniformWalkL,
		_vboInfoHeadKohakuWalkL,
	};

	private VboInfo[] _vboInfos_misaki = new VboInfo[]{
		// 歩く（右足上げ）ポーズ
		_vboInfoBodyUniformWalkR,
		_vboInfoHeadMisakiWalkR,
		// 立ちポーズ
		_vboInfoBodyUniformMotionLess,
		_vboInfoHeadMisakiMotionLess,
		// 歩く（左足上げ）ポーズ
		_vboInfoBodyUniformWalkL,
		_vboInfoHeadMisakiWalkL,
	};

	private VboInfo[] _vboInfos_yuko = new VboInfo[]{
		// 歩く（右足上げ）ポーズ
		_vboInfoBodyUniformWalkR,
		_vboInfoHeadYukoWalkR,
		// 立ちポーズ
		_vboInfoBodyUniformMotionLess,
		_vboInfoHeadYukoMotionLess,
		// 歩く（左足上げ）ポーズ
		_vboInfoBodyUniformWalkL,
		_vboInfoHeadYukoWalkL,
	};

	// 3Dモデルを描画
	// 頂点ファイル名（頂点ファイル名、法線ファイル名、テクスチャファイル名）
	// Mは負数（マイナス）を表し、負数の方が前方へ伸びた状態を示す
	protected void loadRendererModel(){
		// 起動されたタイプによってテクスチャを変更する
		if (_service.get_charaNo() == R.drawable.gate_kohaku1_top){
			_faceTexture = _draw3DObject.loadTexture(R.drawable.bodylayout_kohaku1, _service.get_glViewWidth(), _service.get_glViewHeight());
			_dressTexture = _draw3DObject.loadTexture(R.drawable.bodylayout_kohaku1, _service.get_glViewWidth(), _service.get_glViewHeight());
		}else if (_service.get_charaNo() == R.drawable.gate_kohaku2_top){
			_faceTexture = _draw3DObject.loadTexture(R.drawable.bodylayout_kohaku2, _service.get_glViewWidth(), _service.get_glViewHeight());
			_dressTexture = _draw3DObject.loadTexture(R.drawable.bodylayout_kohaku2, _service.get_glViewWidth(), _service.get_glViewHeight());
		}else if (_service.get_charaNo() == R.drawable.gate_kohaku3_top){
			_faceTexture = _draw3DObject.loadTexture(R.drawable.bodylayout_kohaku1, _service.get_glViewWidth(), _service.get_glViewHeight());
			_dressTexture = _draw3DObject.loadTexture(R.drawable.bodylayout_unity_uniform1, _service.get_glViewWidth(), _service.get_glViewHeight());
		}else if (_service.get_charaNo() == R.drawable.gate_misaki1_top){
			_faceTexture = _draw3DObject.loadTexture(R.drawable.bodylayout_misaki1, _service.get_glViewWidth(), _service.get_glViewHeight());
			_dressTexture = _draw3DObject.loadTexture(R.drawable.bodylayout_unity_uniform1, _service.get_glViewWidth(), _service.get_glViewHeight());
		}else if (_service.get_charaNo() == R.drawable.gate_yuko1_top){
			_faceTexture = _draw3DObject.loadTexture(R.drawable.bodylayout_yuko1, _service.get_glViewWidth(), _service.get_glViewHeight());
			_dressTexture = _draw3DObject.loadTexture(R.drawable.bodylayout_unity_uniform1, _service.get_glViewWidth(), _service.get_glViewHeight());
		}else{
			_faceTexture = _draw3DObject.loadTexture(R.drawable.bodylayout_kohaku1, _service.get_glViewWidth(), _service.get_glViewHeight());
			_dressTexture = _draw3DObject.loadTexture(R.drawable.bodylayout_kohaku1, _service.get_glViewWidth(), _service.get_glViewHeight());
		}
	}

	// ビットマップ生成・キャッシュ化
	@Override
	public void setRendererBitmapCash(boolean init, int pixelWidth, int pixelHeight) {
		// テクスチャおよびモデルデータを読込む
		if (init){
			// プログレスバーの最大値を設定
			_service.setMaxValueProgressDialog(getDrawBitmapCount());
			loadRendererModel();
		}
		// レンダリングを実施しキャッシュに登録する
		boolean success = createRendererBitmap(init, pixelWidth, pixelHeight);
		// VBOの削除処理を中継関数を介して行う
		if (_service.get_charaNo() == R.drawable.gate_kohaku1_top || _service.get_charaNo() == R.drawable.gate_kohaku2_top){
			deleteVboInfos(_vboInfos_kohaku);
		}else if (_service.get_charaNo() == R.drawable.gate_kohaku3_top){
			deleteVboInfos(_vboInfos_kohaku_uniform);
		}else if (_service.get_charaNo() == R.drawable.gate_misaki1_top){
			deleteVboInfos(_vboInfos_misaki);
		}else if (_service.get_charaNo() == R.drawable.gate_yuko1_top){
			deleteVboInfos(_vboInfos_yuko);
		}else{
			deleteVboInfos(_vboInfos_kohaku);
		}
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
		// VBOへ登録するモデルを設定
		VboInfo[] vboInfos = null;
		if (_service.get_charaNo() == R.drawable.gate_kohaku1_top || _service.get_charaNo() == R.drawable.gate_kohaku2_top){
			vboInfos = _vboInfos_kohaku;
		}else if (_service.get_charaNo() == R.drawable.gate_kohaku3_top){
			vboInfos = _vboInfos_kohaku_uniform;
		}else if (_service.get_charaNo() == R.drawable.gate_misaki1_top){
			vboInfos = _vboInfos_misaki;
		}else if (_service.get_charaNo() == R.drawable.gate_yuko1_top){
			vboInfos = _vboInfos_yuko;
		}else{
			vboInfos = _vboInfos_kohaku;
		}

		// VBOへの登録処理を中継関数を介して行う
		for (int i = 0; i < vboInfos.length; i++){
			vboInfos[i] = setVboInfos(vboInfos[i], true);
			// VBOへの登録処理の結果を判定
			if (vboInfos[i] == null){
				return false;
			}else{
				// プログレスバーの現在値を更新
				_service.incrementProgressDialog(1);
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
				_service.incrementProgressDialog(1);
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
		_gl11.glEnable(GL11.GL_LIGHT_MODEL_AMBIENT);
//		if (_rendererType == 2){
//			_gl11.glEnable(GL11.GL_LIGHTING);
//		    // ライトの光源の設定
//			// 「GL_POSITION」は、「同次座標系」で記述する
//			// つまり、４つの要素は(x, y, z, w)の順番になっている。
//			// 通常の座標を求めたい場合は、(x/w, y/w, z/w)で得られる。
//			// [例]----------------------------------------------------------------------------
//			// (45,30,90,3) → (45/3, 30/3, 90/3) → (15, 10, 30) ※これは(15, 10, 30 ,1)と等しい。
//			// もし、w=0の場合は原点(0,0,0)から、(x,y,z)に向かって直線を引いた無限遠方の点となっている。
//			// --------------------------------------------------------------------------------
//			// つまり、w = 0 → 「無限遠方」　→　「太陽のような光源(並行光源)」
//			// それ以外 → 「位置が決まる」　→　「点光源」となる。
//
//			float[] position0 = { 0.0f, 0.25f, 3.0f, 0.0f };
//			float[] position1 = {-2.5f, 1.2f, 1.0f, 1.0f };
//			float[] position2 = { 2.5f, 1.2f, 1.0f, 1.0f };
//			float[] ambient = { 1.0f, 1.0f, 1.0f, 1.0f };
//			float[] diffuse = { 0.5f, 0.5f, 0.5f, 1.0f };
//
//			_gl11.glEnable(GL11.GL_LIGHT0);		// ライト０を使用する
//		    _gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_POSITION, position0, 0);
//		    _gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_AMBIENT, ambient, 0);
//
//			_gl11.glEnable(GL11.GL_LIGHT1);		// ライト１を使用する
//		    _gl11.glLightfv(GL11.GL_LIGHT1, GL11.GL_POSITION, position1, 0);
//		    _gl11.glLightfv(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, diffuse, 0);
//
//			_gl11.glEnable(GL11.GL_LIGHT2);		// ライト２を使用する
//		    _gl11.glLightfv(GL11.GL_LIGHT2, GL11.GL_POSITION, position2, 0);
//		    _gl11.glLightfv(GL11.GL_LIGHT2, GL11.GL_DIFFUSE, diffuse, 0);
//		}else{
//			_gl11.glEnable(GL11.GL_LIGHT_MODEL_AMBIENT);
//		}
	}

	private DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap> getBitmapWalkR(){
		return new DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>(){
			public Bitmap invoke(Boolean initPixels, Integer pixelWidth, Integer pixelHeight){
				// 描画情報を初期化
				_gl11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
				_gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

				// 歩く（右足上げ）ポーズ
				boolean resultDraw = true;
				if (_service.get_charaNo() == R.drawable.gate_kohaku1_top || _service.get_charaNo() == R.drawable.gate_kohaku2_top){
					_draw3DObject.draw3D(_dressTexture, _vboInfoBodyKohakuWalkR);
					resultDraw = _draw3DObject.draw3D(_faceTexture, _vboInfoHeadKohakuWalkR);
				}else if (_service.get_charaNo() == R.drawable.gate_kohaku3_top){
					_draw3DObject.draw3D(_dressTexture, _vboInfoBodyUniformWalkR);
					resultDraw = _draw3DObject.draw3D(_faceTexture, _vboInfoHeadKohakuWalkR);
				}else if (_service.get_charaNo() == R.drawable.gate_misaki1_top){
					_draw3DObject.draw3D(_dressTexture, _vboInfoBodyUniformWalkR);
					resultDraw = _draw3DObject.draw3D(_faceTexture, _vboInfoHeadMisakiWalkR);
				}else if (_service.get_charaNo() == R.drawable.gate_yuko1_top){
					_draw3DObject.draw3D(_dressTexture, _vboInfoBodyUniformWalkR);
					resultDraw = _draw3DObject.draw3D(_faceTexture, _vboInfoHeadYukoWalkR);
				}else{
					_draw3DObject.draw3D(_dressTexture, _vboInfoBodyKohakuWalkR);
					resultDraw = _draw3DObject.draw3D(_faceTexture, _vboInfoHeadKohakuWalkR);
				}

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
				boolean resultDraw = true;
				if (_service.get_charaNo() == R.drawable.gate_kohaku1_top || _service.get_charaNo() == R.drawable.gate_kohaku2_top){
					_draw3DObject.draw3D(_dressTexture, _vboInfoBodyKohakuMotionLess);
					resultDraw = _draw3DObject.draw3D(_faceTexture, _vboInfoHeadKohakuMotionLess);
				}else if (_service.get_charaNo() == R.drawable.gate_kohaku3_top){
					_draw3DObject.draw3D(_dressTexture, _vboInfoBodyUniformMotionLess);
					resultDraw = _draw3DObject.draw3D(_faceTexture, _vboInfoHeadKohakuMotionLess);
				}else if (_service.get_charaNo() == R.drawable.gate_misaki1_top){
					_draw3DObject.draw3D(_dressTexture, _vboInfoBodyUniformMotionLess);
					resultDraw = _draw3DObject.draw3D(_faceTexture, _vboInfoHeadMisakiMotionLess);
				}else if (_service.get_charaNo() == R.drawable.gate_yuko1_top){
					_draw3DObject.draw3D(_dressTexture, _vboInfoBodyUniformMotionLess);
					resultDraw = _draw3DObject.draw3D(_faceTexture, _vboInfoHeadYukoMotionLess);
				}else{
					_draw3DObject.draw3D(_dressTexture, _vboInfoBodyKohakuMotionLess);
					resultDraw = _draw3DObject.draw3D(_faceTexture, _vboInfoHeadKohakuMotionLess);
				}

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
				boolean resultDraw = true;
				if (_service.get_charaNo() == R.drawable.gate_kohaku1_top || _service.get_charaNo() == R.drawable.gate_kohaku2_top){
					_draw3DObject.draw3D(_dressTexture, _vboInfoBodyKohakuWalkL);
					resultDraw = _draw3DObject.draw3D(_faceTexture, _vboInfoHeadKohakuWalkL);
				}else if (_service.get_charaNo() == R.drawable.gate_kohaku3_top){
					_draw3DObject.draw3D(_dressTexture, _vboInfoBodyUniformWalkL);
					resultDraw = _draw3DObject.draw3D(_faceTexture, _vboInfoHeadKohakuWalkL);
				}else if (_service.get_charaNo() == R.drawable.gate_misaki1_top){
					_draw3DObject.draw3D(_dressTexture, _vboInfoBodyUniformWalkL);
					resultDraw = _draw3DObject.draw3D(_faceTexture, _vboInfoHeadMisakiWalkL);
				}else if (_service.get_charaNo() == R.drawable.gate_yuko1_top){
					_draw3DObject.draw3D(_dressTexture, _vboInfoBodyUniformWalkL);
					resultDraw = _draw3DObject.draw3D(_faceTexture, _vboInfoHeadYukoWalkL);
				}else{
					_draw3DObject.draw3D(_dressTexture, _vboInfoBodyKohakuWalkL);
					resultDraw = _draw3DObject.draw3D(_faceTexture, _vboInfoHeadKohakuWalkL);
				}

				if (resultDraw){
					return capture(initPixels, pixelWidth, pixelHeight);
				}else{
					return null;
				}
			}
		};
	}
}
