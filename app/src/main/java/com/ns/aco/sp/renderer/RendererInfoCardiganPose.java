package com.ns.aco.sp.renderer;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.graphics.Bitmap;

import com.ns.aco.sp.common.delegate.DelegateIF;
import com.ns.aco.sp.service.ServiceContract;

public class RendererInfoCardiganPose extends RendererInfoCardigan {
	// コンストラクタ
	public RendererInfoCardiganPose(GL11 gl11, ServiceContract.Service service) {
		super(gl11, service);
		_getBitmapBanzai.add(getBitmapCrouchPoze());
		_getBitmapBanzai.add(getBitmapBanzaiPoze());
		_getBitmapHandUp.add(getBitmapHandUpPoze());
		_getBitmapHozue.add(getBitmapHozueLPoze());
		_getBitmapHozue.add(getBitmapHozueRPoze());
		_getBitmapCoverMouth.add(getBitmapCoverMouthPoze());
		_getBitmapCoverMouth.add(getBitmapCoverMouthRPoze());
	}

	// ビットマップ取得関数格納用
	ArrayList<DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>>
		_getBitmapBanzai = new ArrayList<DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>>();
	// ビットマップ取得関数格納用
	ArrayList<DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>>
		_getBitmapHandUp = new ArrayList<DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>>();
	// ビットマップ取得関数格納用
	ArrayList<DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>>
		_getBitmapHozue = new ArrayList<DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>>();
	// ビットマップ取得関数格納用
	ArrayList<DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>>
		_getBitmapCoverMouth = new ArrayList<DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>>();

	//頂点ファイル名（頂点ファイル名、法線ファイル名、テクスチャファイル名）
	//頭（しゃがみポーズ）
	private final VboInfo _vboInfoHeadCrouch = new VboInfo (
		"00_Yucco/Head/V_HeadCrouch.txt",
		"00_Yucco/Head/VN_HeadCrouch.txt",
		"00_Yucco/Head/VT_HeadCrouch.txt"
	);
	//全身（しゃがみポーズ）
	private final VboInfo _vboInfoBodyCrouch = new VboInfo (
		"00_Yucco/Body/V_BodyCrouch.txt",
		"00_Yucco/Body/VN_BodyCrouch.txt",
		"00_Yucco/Body/VT_BodyCrouch.txt"
	);
	//髪（しゃがみポーズ）
	private final VboInfo _vboInfoHairCrouch = new VboInfo (
		"00_Yucco/Hair/Long/V_HairLongCrouch.txt",
		"00_Yucco/Hair/Long/VN_HairLongCrouch.txt",
		"00_Yucco/Hair/Long/VT_HairLongCrouch.txt"
	);
	//衣装（しゃがみポーズ）
	private final VboInfo _vboInfoDressCrouch = new VboInfo (
		"00_Yucco/Dress/Cardigan/V_DressCardiganCrouch.txt",
		"00_Yucco/Dress/Cardigan/VN_DressCardiganCrouch.txt",
		"00_Yucco/Dress/Cardigan/VT_DressCardiganCrouch.txt"
	);
	//靴（しゃがみポーズ）
	private final VboInfo _vboInfoShoeCrouch = new VboInfo (
		"00_Yucco/Shoe/Loafer/V_ShoeLoaferCrouch.txt",
		"00_Yucco/Shoe/Loafer/VN_ShoeLoaferCrouch.txt",
		"00_Yucco/Shoe/Loafer/VT_ShoeLoaferCrouch.txt"
	);
	//頭（万歳ポーズ）
	private final VboInfo _vboInfoHeadBanzai = new VboInfo (
		"00_Yucco/Head/V_HeadBanzai.txt",
		"00_Yucco/Head/VN_HeadBanzai.txt",
		"00_Yucco/Head/VT_HeadBanzai.txt"
	);
	//全身（万歳ポーズ）
	private final VboInfo _vboInfoBodyBanzai = new VboInfo (
		"00_Yucco/Body/V_BodyBanzai.txt",
		"00_Yucco/Body/VN_BodyBanzai.txt",
		"00_Yucco/Body/VT_BodyBanzai.txt"
	);
	//髪（万歳ポーズ）
	private final VboInfo _vboInfoHairBanzai = new VboInfo (
		"00_Yucco/Hair/Long/V_HairLongBanzai.txt",
		"00_Yucco/Hair/Long/VN_HairLongBanzai.txt",
		"00_Yucco/Hair/Long/VT_HairLongBanzai.txt"
	);
	//衣装（万歳ポーズ）
	private final VboInfo _vboInfoDressBanzai = new VboInfo (
		"00_Yucco/Dress/Cardigan/V_DressCardiganBanzai.txt",
		"00_Yucco/Dress/Cardigan/VN_DressCardiganBanzai.txt",
		"00_Yucco/Dress/Cardigan/VT_DressCardiganBanzai.txt"
	);
	//靴（万歳ポーズ）
	private final VboInfo _vboInfoShoeBanzai = new VboInfo (
		"00_Yucco/Shoe/Loafer/V_ShoeLoaferBanzai.txt",
		"00_Yucco/Shoe/Loafer/VN_ShoeLoaferBanzai.txt",
		"00_Yucco/Shoe/Loafer/VT_ShoeLoaferBanzai.txt"
	);
	//頭（片手上げポーズ）
	private final VboInfo _vboInfoHeadHandUp = new VboInfo (
		"00_Yucco/Head/V_HeadHandUp.txt",
		"00_Yucco/Head/VN_HeadHandUp.txt",
		"00_Yucco/Head/VT_HeadHandUp.txt"
	);
	//全身（片手上げポーズ）
	private final VboInfo _vboInfoBodyHandUp = new VboInfo (
		"00_Yucco/Body/V_BodyHandUp.txt",
		"00_Yucco/Body/VN_BodyHandUp.txt",
		"00_Yucco/Body/VT_BodyHandUp.txt"
	);
	//髪（片手上げポーズ）
	private final VboInfo _vboInfoHairHandUp = new VboInfo (
		"00_Yucco/Hair/Long/V_HairLongHandUp.txt",
		"00_Yucco/Hair/Long/VN_HairLongHandUp.txt",
		"00_Yucco/Hair/Long/VT_HairLongHandUp.txt"
	);
	//衣装（片手上げポーズ）
	private final VboInfo _vboInfoDressHandUp = new VboInfo (
		"00_Yucco/Dress/Cardigan/V_DressCardiganHandUp.txt",
		"00_Yucco/Dress/Cardigan/VN_DressCardiganHandUp.txt",
		"00_Yucco/Dress/Cardigan/VT_DressCardiganHandUp.txt"
	);
	//靴（片手上げポーズ）
	private final VboInfo _vboInfoShoeHandUp = new VboInfo (
		"00_Yucco/Shoe/Loafer/V_ShoeLoaferHandUp.txt",
		"00_Yucco/Shoe/Loafer/VN_ShoeLoaferHandUp.txt",
		"00_Yucco/Shoe/Loafer/VT_ShoeLoaferHandUp.txt"
	);
	//頭（頬杖左ポーズ）
	private final VboInfo _vboInfoHeadHozueL = new VboInfo (
		"00_Yucco/Head/V_HeadHozueL.txt",
		"00_Yucco/Head/VN_HeadHozueL.txt",
		"00_Yucco/Head/VT_HeadHozueL.txt"
	);
	//全身（頬杖左ポーズ）
	private final VboInfo _vboInfoBodyHozueL = new VboInfo (
		"00_Yucco/Body/V_BodyHozueL.txt",
		"00_Yucco/Body/VN_BodyHozueL.txt",
		"00_Yucco/Body/VT_BodyHozueL.txt"
	);
	//髪（頬杖左ポーズ）
	private final VboInfo _vboInfoHairHozueL = new VboInfo (
		"00_Yucco/Hair/Long/V_HairLongHozueL.txt",
		"00_Yucco/Hair/Long/VN_HairLongHozueL.txt",
		"00_Yucco/Hair/Long/VT_HairLongHozueL.txt"
	);
	//衣装（頬杖左ポーズ）
	private final VboInfo _vboInfoDressHozueL = new VboInfo (
		"00_Yucco/Dress/Cardigan/V_DressCardiganHozueL.txt",
		"00_Yucco/Dress/Cardigan/VN_DressCardiganHozueL.txt",
		"00_Yucco/Dress/Cardigan/VT_DressCardiganHozueL.txt"
	);
	//頭（頬杖右ポーズ）
	private final VboInfo _vboInfoHeadHozueR = new VboInfo (
		"00_Yucco/Head/V_HeadHozueR.txt",
		"00_Yucco/Head/VN_HeadHozueR.txt",
		"00_Yucco/Head/VT_HeadHozueR.txt"
	);
	//全身（頬杖右ポーズ）
	private final VboInfo _vboInfoBodyHozueR = new VboInfo (
		"00_Yucco/Body/V_BodyHozueR.txt",
		"00_Yucco/Body/VN_BodyHozueR.txt",
		"00_Yucco/Body/VT_BodyHozueR.txt"
	);
	//髪（頬杖右ポーズ）
	private final VboInfo _vboInfoHairHozueR = new VboInfo (
		"00_Yucco/Hair/Long/V_HairLongHozueR.txt",
		"00_Yucco/Hair/Long/VN_HairLongHozueR.txt",
		"00_Yucco/Hair/Long/VT_HairLongHozueR.txt"
	);
	//衣装（頬杖右ポーズ）
	private final VboInfo _vboInfoDressHozueR = new VboInfo (
		"00_Yucco/Dress/Cardigan/V_DressCardiganHozueR.txt",
		"00_Yucco/Dress/Cardigan/VN_DressCardiganHozueR.txt",
		"00_Yucco/Dress/Cardigan/VT_DressCardiganHozueR.txt"
	);
	//頭（口元隠しポーズ）
	private final VboInfo _vboInfoHeadCoverMouth = new VboInfo (
		"00_Yucco/Head/V_HeadCoverMouth.txt",
		"00_Yucco/Head/VN_HeadCoverMouth.txt",
		"00_Yucco/Head/VT_HeadCoverMouth.txt"
	);
	//頭（口元隠しポーズ 頭右向き）
	private final VboInfo _vboInfoHeadCoverMouthR = new VboInfo (
		"00_Yucco/Head/V_HeadCoverMouthR.txt",
		"00_Yucco/Head/VN_HeadCoverMouthR.txt",
		"00_Yucco/Head/VT_HeadCoverMouthR.txt"
	);
	//全身（口元隠しポーズ）
	private final VboInfo _vboInfoBodyCoverMouth = new VboInfo (
		"00_Yucco/Body/V_BodyCoverMouth.txt",
		"00_Yucco/Body/VN_BodyCoverMouth.txt",
		"00_Yucco/Body/VT_BodyCoverMouth.txt"
	);
	//髪（口元隠しポーズ 頭右向き）
	private final VboInfo _vboInfoHairCoverMouthR = new VboInfo (
		"00_Yucco/Hair/Long/V_HairLongCoverMouthR.txt",
		"00_Yucco/Hair/Long/VN_HairLongCoverMouthR.txt",
		"00_Yucco/Hair/Long/VT_HairLongCoverMouthR.txt"
	);
	//髪（口元隠しポーズ）
	private final VboInfo _vboInfoHairCoverMouth = new VboInfo (
		"00_Yucco/Hair/Long/V_HairLongCoverMouth.txt",
		"00_Yucco/Hair/Long/VN_HairLongCoverMouth.txt",
		"00_Yucco/Hair/Long/VT_HairLongCoverMouth.txt"
	);
	//衣装（口元隠しポーズ）
	private final VboInfo _vboInfoDressCoverMouth = new VboInfo (
		"00_Yucco/Dress/Cardigan/V_DressCardiganCoverMouth.txt",
		"00_Yucco/Dress/Cardigan/VN_DressCardiganCoverMouth.txt",
		"00_Yucco/Dress/Cardigan/VT_DressCardiganCoverMouth.txt"
	);

	//ファイル名一覧取得
	private VboInfo[] _vboInfos = new VboInfo[]{
		// しゃがみポーズ
		_vboInfoHeadCrouch,
		_vboInfoBodyCrouch,
		_vboInfoHairCrouch,
		_vboInfoDressCrouch,
		_vboInfoShoeCrouch,
		// 万歳ポーズ
		_vboInfoHeadBanzai,
		_vboInfoBodyBanzai,
		_vboInfoHairBanzai,
		_vboInfoDressBanzai,
		_vboInfoShoeBanzai,
		// 片手上げポーズ
		_vboInfoHeadHandUp,
		_vboInfoBodyHandUp,
		_vboInfoHairHandUp,
		_vboInfoDressHandUp,
		_vboInfoShoeHandUp,
		// 頬杖左のポーズ
		_vboInfoHeadHozueL,
		_vboInfoBodyHozueL,
		_vboInfoHairHozueL,
		_vboInfoDressHozueL,
		// 頬杖右のポーズ
		_vboInfoHeadHozueR,
		_vboInfoBodyHozueR,
		_vboInfoHairHozueR,
		_vboInfoDressHozueR,
		// 口元隠しポーズ
		_vboInfoHeadCoverMouth,
		_vboInfoHeadCoverMouthR,
		_vboInfoBodyCoverMouth,
		_vboInfoHairCoverMouth,
		_vboInfoHairCoverMouthR,
		_vboInfoDressCoverMouth,
		// 頬杖時の足
		_vboInfoShoeMotionLess,
	};

	// 描画するVBO数取得
	@Override
	public int getDrawBitmapCount(){
		int setCount = _vboInfos.length;
		int drawCount = _getBitmapBanzai.size()
					  + _getBitmapHozue.size()
					  + _getBitmapCoverMouth.size();
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

		// ☆☆☆万歳ポーズ格納☆☆☆
		Bitmap[] banzaiPoseList = new Bitmap[_getBitmapBanzai.size()];
		for (int i = 0; i < _getBitmapBanzai.size(); i++){
			banzaiPoseList[i] = _getBitmapBanzai.get(i).invoke(initPixels, pixelWidth, pixelHeight);
			if (banzaiPoseList[i] == null){
				return false;
			}else{
				// プログレスバーの現在値を更新
				_service.increment_progressDialog(1);
			}
			initPixels = false;
		}

		// ☆☆☆片手上げポーズ格納☆☆☆
		Bitmap[] handUpPoseList = new Bitmap[_getBitmapHandUp.size() + 1];
		// しゃがみポーズを格納
		handUpPoseList[0] = banzaiPoseList[0];
		for (int i = 0; i < _getBitmapHandUp.size(); i++){
			handUpPoseList[i + 1] = _getBitmapHandUp.get(i).invoke(initPixels, pixelWidth, pixelHeight);
			if (handUpPoseList[i + 1] == null){
				return false;
			}else{
				// プログレスバーの現在値を更新
				_service.increment_progressDialog(1);
			}
			initPixels = false;
		}

		// ☆☆☆頬杖ポーズ格納☆☆☆
		Bitmap[] hozuePoseList = new Bitmap[_getBitmapHozue.size()];
		for (int i = 0; i < _getBitmapHozue.size(); i++){
			hozuePoseList[i] = _getBitmapHozue.get(i).invoke(initPixels, pixelWidth, pixelHeight);
			if (hozuePoseList[i] == null){
				return false;
			}else{
				// プログレスバーの現在値を更新
				_service.increment_progressDialog(1);
			}
			initPixels = false;
		}

		// ☆☆☆口元隠しポーズ格納☆☆☆
		Bitmap[] coverMouthPoseList = new Bitmap[_getBitmapCoverMouth.size()];
		for (int i = 0; i < _getBitmapCoverMouth.size(); i++){
			coverMouthPoseList[i] = _getBitmapCoverMouth.get(i).invoke(initPixels, pixelWidth, pixelHeight);
			if (coverMouthPoseList[i] == null){
				return false;
			}else{
				// プログレスバーの現在値を更新
				_service.increment_progressDialog(1);
			}
			initPixels = false;
		}

		// ポーズの画像を設定
		setBitmapPoseDB(handUpPoseList);
		setBitmapPoseDB(banzaiPoseList);
		setBitmapPoseDB(hozuePoseList);
		setBitmapPoseDB(coverMouthPoseList);
		return true;
	}

	private DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap> getBitmapCrouchPoze(){
		return new DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>(){
			public Bitmap invoke(Boolean initPixels, Integer pixelWidth, Integer pixelHeight){
				// 描画情報を初期化
				_gl11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
				_gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

				_gl11.glTranslatef(0.0f, -0.65f, 0.0f);
				_gl11.glRotatef(-0.5f, 0.0f, 0.0f, 1.0f );

				_draw3DObject.draw3D(_dressTexture, _vboInfoDressCrouch);
				_draw3DObject.draw3D(_faceTexture, _vboInfoHeadCrouch);
				_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyCrouch);
				_draw3DObject.draw3D(_hairTexture, _vboInfoHairCrouch);
				boolean resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeCrouch);

				_gl11.glRotatef(0.5f, 0.0f, 0.0f, 1.0f );
				_gl11.glTranslatef(0.0f, 0.65f, 0.0f);

				if (resultDraw){
					return capture(initPixels, pixelWidth, pixelHeight);
				}else{
					return null;
				}
			}
		};
	}

	private DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap> getBitmapBanzaiPoze(){
		return new DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>(){
			public Bitmap invoke(Boolean initPixels, Integer pixelWidth, Integer pixelHeight){
				// 描画情報を初期化
				_gl11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
				_gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

				_gl11.glTranslatef(0.0f, 0.25f, 0.0f);
				_gl11.glRotatef(-0.5f, 0.0f, 0.0f, 1.0f );

				_draw3DObject.draw3D(_dressTexture, _vboInfoDressBanzai);
				_draw3DObject.draw3D(_faceTexture_swagger, _vboInfoHeadBanzai);
				_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyBanzai);
				_draw3DObject.draw3D(_hairTexture, _vboInfoHairBanzai);
				boolean resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeBanzai);

				_gl11.glRotatef(0.5f, 0.0f, 0.0f, 1.0f );
				_gl11.glTranslatef(0.0f, -0.25f, 0.0f);

				if (resultDraw){
					return capture(initPixels, pixelWidth, pixelHeight);
				}else{
					return null;
				}
			}
		};
	}

	private DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap> getBitmapHandUpPoze(){
		return new DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>(){
			public Bitmap invoke(Boolean initPixels, Integer pixelWidth, Integer pixelHeight){
				// 描画情報を初期化
				_gl11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
				_gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

				_gl11.glTranslatef(0.0f, 0.25f, 0.0f);
				_gl11.glRotatef(-0.5f, 0.0f, 0.0f, 1.0f );

				_draw3DObject.draw3D(_dressTexture, _vboInfoDressHandUp);
				_draw3DObject.draw3D(_faceTexture_wink, _vboInfoHeadHandUp);
				_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyHandUp);
				_draw3DObject.draw3D(_hairTexture, _vboInfoHairHandUp);
				boolean resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeHandUp);

				_gl11.glRotatef(0.5f, 0.0f, 0.0f, 1.0f );
				_gl11.glTranslatef(0.0f, -0.25f, 0.0f);

				if (resultDraw){
					return capture(initPixels, pixelWidth, pixelHeight);
				}else{
					return null;
				}
			}
		};
	}

	private DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap> getBitmapHozueLPoze(){
		return new DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>(){
			public Bitmap invoke(Boolean initPixels, Integer pixelWidth, Integer pixelHeight){
				// 描画情報を初期化
				_gl11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
				_gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

				_gl11.glRotatef(-0.5f, 0.0f, 0.0f, 1.0f );

				_draw3DObject.draw3D(_dressTexture, _vboInfoDressHozueL);
				_draw3DObject.draw3D(_faceTexture_swagger, _vboInfoHeadHozueL);
				_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyHozueL);
				_draw3DObject.draw3D(_hairTexture, _vboInfoHairHozueL);
				boolean resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeMotionLess);

				_gl11.glRotatef(0.5f, 0.0f, 0.0f, 1.0f );

				if (resultDraw){
					return capture(initPixels, pixelWidth, pixelHeight);
				}else{
					return null;
				}
			}
		};
	}

	private DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap> getBitmapHozueRPoze(){
		return new DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>(){
			public Bitmap invoke(Boolean initPixels, Integer pixelWidth, Integer pixelHeight){
				// 描画情報を初期化
				_gl11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
				_gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

				_gl11.glRotatef(-0.5f, 0.0f, 0.0f, 1.0f );

				_draw3DObject.draw3D(_dressTexture, _vboInfoDressHozueR);
				_draw3DObject.draw3D(_faceTexture_swagger, _vboInfoHeadHozueR);
				_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyHozueR);
				_draw3DObject.draw3D(_hairTexture, _vboInfoHairHozueR);
				boolean resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeMotionLess);

				_gl11.glRotatef(0.5f, 0.0f, 0.0f, 1.0f );

				if (resultDraw){
					return capture(initPixels, pixelWidth, pixelHeight);
				}else{
					return null;
				}
			}
		};
	}

	private DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap> getBitmapCoverMouthRPoze(){
		return new DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>(){
			public Bitmap invoke(Boolean initPixels, Integer pixelWidth, Integer pixelHeight){
				// 描画情報を初期化
				_gl11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
				_gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

				_gl11.glRotatef(-0.5f, 0.0f, 0.0f, 1.0f );

				_draw3DObject.draw3D(_dressTexture, _vboInfoDressCoverMouth);
				_draw3DObject.draw3D(_faceTexture_away_right, _vboInfoHeadCoverMouthR);
				_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyCoverMouth);
				_draw3DObject.draw3D(_hairTexture, _vboInfoHairCoverMouthR);
				boolean resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeMotionLess);

				_gl11.glRotatef(0.5f, 0.0f, 0.0f, 1.0f );

				if (resultDraw){
					return capture(initPixels, pixelWidth, pixelHeight);
				}else{
					return null;
				}
			}
		};
	}

	private DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap> getBitmapCoverMouthPoze(){
		return new DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>(){
			public Bitmap invoke(Boolean initPixels, Integer pixelWidth, Integer pixelHeight){
				// 描画情報を初期化
				_gl11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
				_gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

				_gl11.glRotatef(-0.5f, 0.0f, 0.0f, 1.0f );

				_draw3DObject.draw3D(_dressTexture, _vboInfoDressCoverMouth);
				_draw3DObject.draw3D(_faceTexture, _vboInfoHeadCoverMouth);
				_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyCoverMouth);
				_draw3DObject.draw3D(_hairTexture, _vboInfoHairCoverMouth);
				boolean resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeMotionLess);

				_gl11.glRotatef(0.5f, 0.0f, 0.0f, 1.0f );

				if (resultDraw){
					return capture(initPixels, pixelWidth, pixelHeight);
				}else{
					return null;
				}
			}
		};
	}
}
