package com.ns.aco.sp.renderer;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import com.ns.aco.sp.R;
import com.ns.aco.sp.common.delegate.DelegateIF;
import com.ns.aco.sp.service.ServiceContract;

import android.graphics.Bitmap;

public class RendererInfoJersey extends RendererInfoBase {

	public RendererInfoJersey(GL11 gl11, ServiceContract.Service service) {
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
	protected final VboInfo _vboInfoBodyWalkR = new VboInfo(
		"00_Yucco/Body/V_BodyWalkR.txt",
		"00_Yucco/Body/VN_BodyWalkR.txt",
		"00_Yucco/Body/VT_BodyWalkR.txt"
	);
	protected final VboInfo _vboInfoHeadWalkR = new VboInfo(
		"00_Yucco/Head/V_HeadWalkR.txt",
		"00_Yucco/Head/VN_HeadWalkR.txt",
		"00_Yucco/Head/VT_HeadWalkR.txt"
	);
	protected final VboInfo _vboInfoHairWalkR = new VboInfo(
		"00_Yucco/Hair/TwinTail/V_HairTwinTailWalkR.txt",
		"00_Yucco/Hair/TwinTail/VN_HairTwinTailWalkR.txt",
		"00_Yucco/Hair/TwinTail/VT_HairTwinTailWalkR.txt"
	);
	protected final VboInfo _vboInfoDressWalkR_Miku = new VboInfo(
		"00_Yucco/Dress/Miku/V_DressMikuWalkR.txt",
		"00_Yucco/Dress/Miku/VN_DressMikuWalkR.txt",
		"00_Yucco/Dress/Miku/VT_DressMikuWalkR.txt"
	);
	protected final VboInfo _vboInfoDressWalkR_Jersey = new VboInfo(
		"00_Yucco/Dress/Jersey/V_DressJerseyWalkR.txt",
		"00_Yucco/Dress/Jersey/VN_DressJerseyWalkR.txt",
		"00_Yucco/Dress/Jersey/VT_DressJerseyWalkR.txt"
	);
	protected final VboInfo _vboInfoShoeWalkR = new VboInfo(
		"00_Yucco/Shoe/Loafer/V_ShoeLoaferWalkR.txt",
		"00_Yucco/Shoe/Loafer/VN_ShoeLoaferWalkR.txt",
		"00_Yucco/Shoe/Loafer/VT_ShoeLoaferWalkR.txt"
	);
	protected final VboInfo _vboInfoSleeveWalkR = new VboInfo(
		"00_Yucco/Sleeve/Miku/V_SleeveMikuWalkR.txt",
		"00_Yucco/Sleeve/Miku/VN_SleeveMikuWalkR.txt",
		"00_Yucco/Sleeve/Miku/VT_SleeveMikuWalkR.txt"
	);
	//立ちポーズ
	protected final VboInfo _vboInfoBodyMotionLess = new VboInfo(
		"00_Yucco/Body/V_BodyMotionLess.txt",
		"00_Yucco/Body/VN_BodyMotionLess.txt",
		"00_Yucco/Body/VT_BodyMotionLess.txt"
	);
	protected final VboInfo _vboInfoHeadMotionLess = new VboInfo(
		"00_Yucco/Head/V_HeadMotionLess.txt",
		"00_Yucco/Head/VN_HeadMotionLess.txt",
		"00_Yucco/Head/VT_HeadMotionLess.txt"
	);
	protected final VboInfo _vboInfoHairMotionLess = new VboInfo(
		"00_Yucco/Hair/TwinTail/V_HairTwinTailMotionLess.txt",
		"00_Yucco/Hair/TwinTail/VN_HairTwinTailMotionLess.txt",
		"00_Yucco/Hair/TwinTail/VT_HairTwinTailMotionLess.txt"
	);
	protected final VboInfo _vboInfoDressMotionLess_Miku = new VboInfo(
		"00_Yucco/Dress/Miku/V_DressMikuMotionLess.txt",
		"00_Yucco/Dress/Miku/VN_DressMikuMotionLess.txt",
		"00_Yucco/Dress/Miku/VT_DressMikuMotionLess.txt"
	);
	protected final VboInfo _vboInfoDressMotionLess_Jersey = new VboInfo(
		"00_Yucco/Dress/Jersey/V_DressJerseyMotionLess.txt",
		"00_Yucco/Dress/Jersey/VN_DressJerseyMotionLess.txt",
		"00_Yucco/Dress/Jersey/VT_DressJerseyMotionLess.txt"
	);
	protected final VboInfo _vboInfoShoeMotionLess = new VboInfo(
		"00_Yucco/Shoe/Loafer/V_ShoeLoaferMotionLess.txt",
		"00_Yucco/Shoe/Loafer/VN_ShoeLoaferMotionLess.txt",
		"00_Yucco/Shoe/Loafer/VT_ShoeLoaferMotionLess.txt"
	);
	protected final VboInfo _vboInfoSleeveMotionLess = new VboInfo(
		"00_Yucco/Sleeve/Miku/V_SleeveMikuMotionLess.txt",
		"00_Yucco/Sleeve/Miku/VN_SleeveMikuMotionLess.txt",
		"00_Yucco/Sleeve/Miku/VT_SleeveMikuMotionLess.txt"
	);
	//歩く（左足上げ）ポーズ
	protected final VboInfo _vboInfoBodyWalkL = new VboInfo(
		"00_Yucco/Body/V_BodyWalkL.txt",
		"00_Yucco/Body/VN_BodyWalkL.txt",
		"00_Yucco/Body/VT_BodyWalkL.txt"
	);
	protected final VboInfo _vboInfoHeadWalkL = new VboInfo(
		"00_Yucco/Head/V_HeadWalkL.txt",
		"00_Yucco/Head/VN_HeadWalkL.txt",
		"00_Yucco/Head/VT_HeadWalkL.txt"
	);
	protected final VboInfo _vboInfoHairWalkL = new VboInfo(
		"00_Yucco/Hair/TwinTail/V_HairTwinTailWalkL.txt",
		"00_Yucco/Hair/TwinTail/VN_HairTwinTailWalkL.txt",
		"00_Yucco/Hair/TwinTail/VT_HairTwinTailWalkL.txt"
	);
	protected final VboInfo _vboInfoDressWalkL_Miku = new VboInfo(
		"00_Yucco/Dress/Miku/V_DressMikuWalkL.txt",
		"00_Yucco/Dress/Miku/VN_DressMikuWalkL.txt",
		"00_Yucco/Dress/Miku/VT_DressMikuWalkL.txt"
	);
	protected final VboInfo _vboInfoDressWalkL_Jersey = new VboInfo(
		"00_Yucco/Dress/Jersey/V_DressJerseyWalkL.txt",
		"00_Yucco/Dress/Jersey/VN_DressJerseyWalkL.txt",
		"00_Yucco/Dress/Jersey/VT_DressJerseyWalkL.txt"
	);
	protected final VboInfo _vboInfoShoeWalkL = new VboInfo(
		"00_Yucco/Shoe/Loafer/V_ShoeLoaferWalkL.txt",
		"00_Yucco/Shoe/Loafer/VN_ShoeLoaferWalkL.txt",
		"00_Yucco/Shoe/Loafer/VT_ShoeLoaferWalkL.txt"
	);
	protected final VboInfo _vboInfoSleeveWalkL = new VboInfo(
		"00_Yucco/Sleeve/Miku/V_SleeveMikuWalkL.txt",
		"00_Yucco/Sleeve/Miku/VN_SleeveMikuWalkL.txt",
		"00_Yucco/Sleeve/Miku/VT_SleeveMikuWalkL.txt"
	);

	// 描画するVBO数取得
	@Override
	public int getDrawBitmapCount(){
		int setCount = 0;
		if (_service.get_charaNo() == R.drawable.gate_cosplay1_top){
			setCount = _vboInfos_Miku.length;
		}else{
			setCount = _vboInfos_Jersey.length;
		}
		return 3 * forwardDirection.length + setCount;
	}

	@Override
	public boolean isPose() {
		return false;
	}

	//ファイル名一覧取得
	private VboInfo[] _vboInfos_Miku = new VboInfo[]{
		// 歩く（右足上げ）ポーズ
		_vboInfoHeadWalkR,
		_vboInfoBodyWalkR,
		_vboInfoHairWalkR,
		_vboInfoDressWalkR_Miku,
		_vboInfoShoeWalkR,
		_vboInfoSleeveWalkR,
		// 立ちポーズ
		_vboInfoHeadMotionLess,
		_vboInfoBodyMotionLess,
		_vboInfoHairMotionLess,
		_vboInfoDressMotionLess_Miku,
		_vboInfoShoeMotionLess,
		_vboInfoSleeveMotionLess,
		// 歩く（左足上げ）ポーズ
		_vboInfoHeadWalkL,
		_vboInfoBodyWalkL,
		_vboInfoHairWalkL,
		_vboInfoDressWalkL_Miku,
		_vboInfoShoeWalkL,
		_vboInfoSleeveWalkL
	};

	//ファイル名一覧取得
	private VboInfo[] _vboInfos_Jersey = new VboInfo[]{
		// 歩く（右足上げ）ポーズ
		_vboInfoHeadWalkR,
		_vboInfoBodyWalkR,
		_vboInfoHairWalkR,
		_vboInfoDressWalkR_Jersey,
		_vboInfoShoeWalkR,
		// 立ちポーズ
		_vboInfoHeadMotionLess,
		_vboInfoBodyMotionLess,
		_vboInfoHairMotionLess,
		_vboInfoDressMotionLess_Jersey,
		_vboInfoShoeMotionLess,
		// 歩く（左足上げ）ポーズ
		_vboInfoHeadWalkL,
		_vboInfoBodyWalkL,
		_vboInfoHairWalkL,
		_vboInfoDressWalkL_Jersey,
		_vboInfoShoeWalkL,
	};

	// 3Dモデルを描画
	// 頂点ファイル名（頂点ファイル名、法線ファイル名、テクスチャファイル名）
	// Mは負数（マイナス）を表し、負数の方が前方へ伸びた状態を示す
	protected void loadRendererModel(){
		// 起動されたタイプによってテクスチャを変更する
		if (_service.get_charaNo() == R.drawable.gate_jersey1_top){
			_faceTexture = _draw3DObject.loadTexture(R.drawable.face_yucco1, _service.get_windowWidth(), _service.get_windowHeight());
			_faceTexture_close = _draw3DObject.loadTexture(R.drawable.face_yucco1_close, _service.get_windowWidth(), _service.get_windowHeight());
			_faceTexture_swagger = _draw3DObject.loadTexture(R.drawable.face_yucco1_swagger, _service.get_windowWidth(), _service.get_windowHeight());
			_faceTexture_away_right = _draw3DObject.loadTexture(R.drawable.face_yucco1_awayright, _service.get_windowWidth(), _service.get_windowHeight());
			_faceTexture_wink = _draw3DObject.loadTexture(R.drawable.face_yucco1_wink, _service.get_windowWidth(), _service.get_windowHeight());
			_bodyTexture = _draw3DObject.loadTexture(R.drawable.bodylayout_loafer1, _service.get_windowWidth(), _service.get_windowHeight());
			_hairTexture = _draw3DObject.loadTexture(R.drawable.hair_twintail1, _service.get_windowWidth(), _service.get_windowHeight());
			_dressTexture = _draw3DObject.loadTexture(R.drawable.dress_jersey1, _service.get_windowWidth(), _service.get_windowHeight());
			_shoeTexture = _draw3DObject.loadTexture(R.drawable.shoe_sneakers1, _service.get_windowWidth(), _service.get_windowHeight());
		}else if (_service.get_charaNo() == R.drawable.gate_jersey2_top){
			_faceTexture = _draw3DObject.loadTexture(R.drawable.face_yucco1, _service.get_windowWidth(), _service.get_windowHeight());
			_faceTexture_close = _draw3DObject.loadTexture(R.drawable.face_yucco1_close, _service.get_windowWidth(), _service.get_windowHeight());
			_faceTexture_swagger = _draw3DObject.loadTexture(R.drawable.face_yucco1_swagger, _service.get_windowWidth(), _service.get_windowHeight());
			_faceTexture_away_right = _draw3DObject.loadTexture(R.drawable.face_yucco1_awayright, _service.get_windowWidth(), _service.get_windowHeight());
			_faceTexture_wink = _draw3DObject.loadTexture(R.drawable.face_yucco1_wink, _service.get_windowWidth(), _service.get_windowHeight());
			_bodyTexture = _draw3DObject.loadTexture(R.drawable.bodylayout_loafer1, _service.get_windowWidth(), _service.get_windowHeight());
			_hairTexture = _draw3DObject.loadTexture(R.drawable.hair_twintail1, _service.get_windowWidth(), _service.get_windowHeight());
			_dressTexture = _draw3DObject.loadTexture(R.drawable.dress_jersey2, _service.get_windowWidth(), _service.get_windowHeight());
			_shoeTexture = _draw3DObject.loadTexture(R.drawable.shoe_sneakers1, _service.get_windowWidth(), _service.get_windowHeight());
		}else{
			_faceTexture = _draw3DObject.loadTexture(R.drawable.face_miku1, _service.get_windowWidth(), _service.get_windowHeight());
			_faceTexture_close = _draw3DObject.loadTexture(R.drawable.face_yucco1_close, _service.get_windowWidth(), _service.get_windowHeight());
			_faceTexture_swagger = _draw3DObject.loadTexture(R.drawable.face_yucco1_swagger, _service.get_windowWidth(), _service.get_windowHeight());
			_faceTexture_away_right = _draw3DObject.loadTexture(R.drawable.face_miku1_awayright, _service.get_windowWidth(), _service.get_windowHeight());
			_faceTexture_wink = _draw3DObject.loadTexture(R.drawable.face_miku1_wink, _service.get_windowWidth(), _service.get_windowHeight());
			_bodyTexture = _draw3DObject.loadTexture(R.drawable.bodylayout_miku1, _service.get_windowWidth(), _service.get_windowHeight());
			_hairTexture = _draw3DObject.loadTexture(R.drawable.hair_twintail2, _service.get_windowWidth(), _service.get_windowHeight());
			_dressTexture = _draw3DObject.loadTexture(R.drawable.dress_miku1, _service.get_windowWidth(), _service.get_windowHeight());
			_shoeTexture = _draw3DObject.loadTexture(R.drawable.shoe_miku1, _service.get_windowWidth(), _service.get_windowHeight());
			_sleeveTexture = _draw3DObject.loadTexture(R.drawable.arm_miku1, _service.get_windowWidth(), _service.get_windowHeight());
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
		deleteVboInfos(_vboInfos_Jersey);
		deleteVboInfos(_vboInfos_Miku);
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
		if (_service.get_charaNo() == R.drawable.gate_cosplay1_top){
			vboInfos = _vboInfos_Miku;
		}else{
			vboInfos = _vboInfos_Jersey;
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
				if (_service.get_charaNo() == R.drawable.gate_cosplay1_top){
					_draw3DObject.draw3D(_faceTexture_close, _vboInfoHeadWalkR);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyWalkR);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairWalkR);
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressWalkR_Miku);
					_draw3DObject.draw3D(_shoeTexture, _vboInfoShoeWalkR);
					resultDraw = _draw3DObject.draw3D(_sleeveTexture, _vboInfoSleeveWalkR);
				}else{
					_draw3DObject.draw3D(_faceTexture_close, _vboInfoHeadWalkR);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyWalkR);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairWalkR);
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressWalkR_Jersey);
					resultDraw =_draw3DObject.draw3D(_shoeTexture, _vboInfoShoeWalkR);
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
				if (_service.get_charaNo() == R.drawable.gate_cosplay1_top){
					_draw3DObject.draw3D(_faceTexture, _vboInfoHeadMotionLess);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyMotionLess);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairMotionLess);
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressMotionLess_Miku);
					_draw3DObject.draw3D(_shoeTexture, _vboInfoShoeMotionLess);
					resultDraw = _draw3DObject.draw3D(_sleeveTexture, _vboInfoSleeveMotionLess);
				}else{
					_draw3DObject.draw3D(_faceTexture, _vboInfoHeadMotionLess);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyMotionLess);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairMotionLess);
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressMotionLess_Jersey);
					resultDraw =_draw3DObject.draw3D(_shoeTexture, _vboInfoShoeMotionLess);
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
				if (_service.get_charaNo() == R.drawable.gate_cosplay1_top){
					_draw3DObject.draw3D(_faceTexture, _vboInfoHeadWalkL);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyWalkL);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairWalkL);
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressWalkL_Miku);
					_draw3DObject.draw3D(_shoeTexture, _vboInfoShoeWalkL);
					resultDraw = _draw3DObject.draw3D(_sleeveTexture, _vboInfoSleeveWalkL);
				}else{
					_draw3DObject.draw3D(_faceTexture, _vboInfoHeadWalkL);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyWalkL);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairWalkL);
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressWalkL_Jersey);
					resultDraw =_draw3DObject.draw3D(_shoeTexture, _vboInfoShoeWalkL);
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
