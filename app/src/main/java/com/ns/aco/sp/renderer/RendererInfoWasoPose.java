package com.ns.aco.sp.renderer;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.graphics.Bitmap;

import com.ns.aco.sp.common.delegate.DelegateIF;
import com.ns.aco.sp.service.ServiceContract;

public class RendererInfoWasoPose extends RendererInfoWaso {
	// コンストラクタ
	public RendererInfoWasoPose(GL11 gl11, ServiceContract.Service service) {
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
	//全身（しゃがみポーズ）
	private final VboInfo _vboInfoBodyCrouch = new VboInfo (
		"00_Yucco/Body/V_BodyCrouch.txt",
		"00_Yucco/Body/VN_BodyCrouch.txt",
		"00_Yucco/Body/VT_BodyCrouch.txt"
	);
	//頭（しゃがみポーズ）
	private final VboInfo _vboInfoHeadCrouch = new VboInfo (
		"00_Yucco/Head/V_HeadCrouch.txt",
		"00_Yucco/Head/VN_HeadCrouch.txt",
		"00_Yucco/Head/VT_HeadCrouch.txt"
	);
	//髪（しゃがみポーズ）
	private final VboInfo _vboInfoHairCrouch = new VboInfo (
		"00_Yucco/Hair/Waso/V_HairWasoCrouch.txt",
		"00_Yucco/Hair/Waso/VN_HairWasoCrouch.txt",
		"00_Yucco/Hair/Waso/VT_HairWasoCrouch.txt"
	);
	private final VboInfo _vboInfoCorsageCrouch1 = new VboInfo (
		"00_Yucco/Hair/Waso/V_CorsageWasoCrouch1.txt",
		"00_Yucco/Hair/Waso/VN_CorsageWasoCrouch1.txt",
		"00_Yucco/Hair/Waso/VT_CorsageWasoCrouch1.txt"
	);
	private final VboInfo _vboInfoCorsageCrouch2 = new VboInfo (
		"00_Yucco/Hair/Waso/V_CorsageWasoCrouch2.txt",
		"00_Yucco/Hair/Waso/VN_CorsageWasoCrouch2.txt",
		"00_Yucco/Hair/Waso/VT_CorsageWasoCrouch2.txt"
	);
	//衣装（しゃがみポーズ）
	private final VboInfo _vboInfoDressCrouch = new VboInfo (
		"00_Yucco/Dress/Waso/V_DressWasoCrouch.txt",
		"00_Yucco/Dress/Waso/VN_DressWasoCrouch.txt",
		"00_Yucco/Dress/Waso/VT_DressWasoCrouch.txt"
	);
	//靴（しゃがみポーズ）
	private final VboInfo _vboInfoShoeCrouch = new VboInfo (
		"00_Yucco/Shoe/Geta/V_ShoeGetaCrouch.txt",
		"00_Yucco/Shoe/Geta/VN_ShoeGetaCrouch.txt",
		"00_Yucco/Shoe/Geta/VT_ShoeGetaCrouch.txt"
	);
	//全身（万歳ポーズ）
	private final VboInfo _vboInfoBodyBanzai = new VboInfo (
		"00_Yucco/Body/V_BodyBanzai.txt",
		"00_Yucco/Body/VN_BodyBanzai.txt",
		"00_Yucco/Body/VT_BodyBanzai.txt"
	);
	//頭（万歳ポーズ）
	private final VboInfo _vboInfoHeadBanzai = new VboInfo (
		"00_Yucco/Head/V_HeadBanzai.txt",
		"00_Yucco/Head/VN_HeadBanzai.txt",
		"00_Yucco/Head/VT_HeadBanzai.txt"
	);
	//髪（万歳ポーズ）
	private final VboInfo _vboInfoHairBanzai = new VboInfo (
		"00_Yucco/Hair/Waso/V_HairWasoBanzai.txt",
		"00_Yucco/Hair/Waso/VN_HairWasoBanzai.txt",
		"00_Yucco/Hair/Waso/VT_HairWasoBanzai.txt"
	);
	private final VboInfo _vboInfoCorsageBanzai1 = new VboInfo (
		"00_Yucco/Hair/Waso/V_CorsageWasoBanzai1.txt",
		"00_Yucco/Hair/Waso/VN_CorsageWasoBanzai1.txt",
		"00_Yucco/Hair/Waso/VT_CorsageWasoBanzai1.txt"
	);
	private final VboInfo _vboInfoCorsageBanzai2 = new VboInfo (
		"00_Yucco/Hair/Waso/V_CorsageWasoBanzai2.txt",
		"00_Yucco/Hair/Waso/VN_CorsageWasoBanzai2.txt",
		"00_Yucco/Hair/Waso/VT_CorsageWasoBanzai2.txt"
	);
	//衣装（万歳ポーズ）
	private final VboInfo _vboInfoDressBanzai = new VboInfo (
		"00_Yucco/Dress/Waso/V_DressWasoBanzai.txt",
		"00_Yucco/Dress/Waso/VN_DressWasoBanzai.txt",
		"00_Yucco/Dress/Waso/VT_DressWasoBanzai.txt"
	);
	//靴（万歳ポーズ）
	private final VboInfo _vboInfoShoeBanzai = new VboInfo (
		"00_Yucco/Shoe/Geta/V_ShoeGetaBanzai.txt",
		"00_Yucco/Shoe/Geta/VN_ShoeGetaBanzai.txt",
		"00_Yucco/Shoe/Geta/VT_ShoeGetaBanzai.txt"
	);
	//全身（片手上げポーズ）
	private final VboInfo _vboInfoBodyHandUp = new VboInfo (
		"00_Yucco/Body/V_BodyHandUp.txt",
		"00_Yucco/Body/VN_BodyHandUp.txt",
		"00_Yucco/Body/VT_BodyHandUp.txt"
	);
	//頭（片手上げポーズ）
	private final VboInfo _vboInfoHeadHandUp = new VboInfo (
		"00_Yucco/Head/V_HeadHandUp.txt",
		"00_Yucco/Head/VN_HeadHandUp.txt",
		"00_Yucco/Head/VT_HeadHandUp.txt"
	);
	//髪（片手上げポーズ）
	private final VboInfo _vboInfoHairHandUp = new VboInfo (
		"00_Yucco/Hair/Waso/V_HairWasoHandUp.txt",
		"00_Yucco/Hair/Waso/VN_HairWasoHandUp.txt",
		"00_Yucco/Hair/Waso/VT_HairWasoHandUp.txt"
	);
	private final VboInfo _vboInfoCorsageHandUp1 = new VboInfo (
		"00_Yucco/Hair/Waso/V_CorsageWasoHandUp1.txt",
		"00_Yucco/Hair/Waso/VN_CorsageWasoHandUp1.txt",
		"00_Yucco/Hair/Waso/VT_CorsageWasoHandUp1.txt"
	);
	private final VboInfo _vboInfoCorsageHandUp2 = new VboInfo (
		"00_Yucco/Hair/Waso/V_CorsageWasoHandUp2.txt",
		"00_Yucco/Hair/Waso/VN_CorsageWasoHandUp2.txt",
		"00_Yucco/Hair/Waso/VT_CorsageWasoHandUp2.txt"
	);
	//衣装（片手上げポーズ）
	private final VboInfo _vboInfoDressHandUp = new VboInfo (
		"00_Yucco/Dress/Waso/V_DressWasoHandUp.txt",
		"00_Yucco/Dress/Waso/VN_DressWasoHandUp.txt",
		"00_Yucco/Dress/Waso/VT_DressWasoHandUp.txt"
	);
	//靴（片手上げポーズ）
	private final VboInfo _vboInfoShoeHandUp = new VboInfo (
		"00_Yucco/Shoe/Geta/V_ShoeGetaHandUp.txt",
		"00_Yucco/Shoe/Geta/VN_ShoeGetaHandUp.txt",
		"00_Yucco/Shoe/Geta/VT_ShoeGetaHandUp.txt"
	);
	//全身（頬杖左ポーズ）
	private final VboInfo _vboInfoBodyHozueL = new VboInfo (
		"00_Yucco/Body/V_BodyHozueL.txt",
		"00_Yucco/Body/VN_BodyHozueL.txt",
		"00_Yucco/Body/VT_BodyHozueL.txt"
	);
	//頭（頬杖左ポーズ）
	private final VboInfo _vboInfoHeadHozueL = new VboInfo (
		"00_Yucco/Head/V_HeadHozueL.txt",
		"00_Yucco/Head/VN_HeadHozueL.txt",
		"00_Yucco/Head/VT_HeadHozueL.txt"
	);
	//髪（頬杖左ポーズ）
	private final VboInfo _vboInfoHairHozueL = new VboInfo (
		"00_Yucco/Hair/Waso/V_HairWasoHozueL.txt",
		"00_Yucco/Hair/Waso/VN_HairWasoHozueL.txt",
		"00_Yucco/Hair/Waso/VT_HairWasoHozueL.txt"
	);
	private final VboInfo _vboInfoCorsageHozueL1 = new VboInfo (
		"00_Yucco/Hair/Waso/V_CorsageWasoHozueL1.txt",
		"00_Yucco/Hair/Waso/VN_CorsageWasoHozueL1.txt",
		"00_Yucco/Hair/Waso/VT_CorsageWasoHozueL1.txt"
	);
	private final VboInfo _vboInfoCorsageHozueL2 = new VboInfo (
		"00_Yucco/Hair/Waso/V_CorsageWasoHozueL2.txt",
		"00_Yucco/Hair/Waso/VN_CorsageWasoHozueL2.txt",
		"00_Yucco/Hair/Waso/VT_CorsageWasoHozueL2.txt"
	);
	//衣装（頬杖左ポーズ）
	private final VboInfo _vboInfoDressHozueL = new VboInfo (
		"00_Yucco/Dress/Waso/V_DressWasoHozueL.txt",
		"00_Yucco/Dress/Waso/VN_DressWasoHozueL.txt",
		"00_Yucco/Dress/Waso/VT_DressWasoHozueL.txt"
	);
	//全身（頬杖右ポーズ）
	private final VboInfo _vboInfoBodyHozueR = new VboInfo (
		"00_Yucco/Body/V_BodyHozueR.txt",
		"00_Yucco/Body/VN_BodyHozueR.txt",
		"00_Yucco/Body/VT_BodyHozueR.txt"
	);
	//頭（頬杖右ポーズ）
	private final VboInfo _vboInfoHeadHozueR = new VboInfo (
		"00_Yucco/Head/V_HeadHozueR.txt",
		"00_Yucco/Head/VN_HeadHozueR.txt",
		"00_Yucco/Head/VT_HeadHozueR.txt"
	);
	//髪（頬杖右ポーズ）
	private final VboInfo _vboInfoHairHozueR = new VboInfo (
		"00_Yucco/Hair/Waso/V_HairWasoHozueR.txt",
		"00_Yucco/Hair/Waso/VN_HairWasoHozueR.txt",
		"00_Yucco/Hair/Waso/VT_HairWasoHozueR.txt"
	);
	private final VboInfo _vboInfoCorsageHozueR1 = new VboInfo (
		"00_Yucco/Hair/Waso/V_CorsageWasoHozueR1.txt",
		"00_Yucco/Hair/Waso/VN_CorsageWasoHozueR1.txt",
		"00_Yucco/Hair/Waso/VT_CorsageWasoHozueR1.txt"
	);
	private final VboInfo _vboInfoCorsageHozueR2 = new VboInfo (
		"00_Yucco/Hair/Waso/V_CorsageWasoHozueR2.txt",
		"00_Yucco/Hair/Waso/VN_CorsageWasoHozueR2.txt",
		"00_Yucco/Hair/Waso/VT_CorsageWasoHozueR2.txt"
	);
	//衣装（頬杖右ポーズ）
	private final VboInfo _vboInfoDressHozueR = new VboInfo (
		"00_Yucco/Dress/Waso/V_DressWasoHozueR.txt",
		"00_Yucco/Dress/Waso/VN_DressWasoHozueR.txt",
		"00_Yucco/Dress/Waso/VT_DressWasoHozueR.txt"
	);
	//全身（口元隠しポーズ）
	private final VboInfo _vboInfoBodyCoverMouth = new VboInfo (
		"00_Yucco/Body/V_BodyCoverMouth.txt",
		"00_Yucco/Body/VN_BodyCoverMouth.txt",
		"00_Yucco/Body/VT_BodyCoverMouth.txt"
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
	//髪（口元隠しポーズ）
	private final VboInfo _vboInfoHairCoverMouth = new VboInfo (
		"00_Yucco/Hair/Waso/V_HairWasoCoverMouth.txt",
		"00_Yucco/Hair/Waso/VN_HairWasoCoverMouth.txt",
		"00_Yucco/Hair/Waso/VT_HairWasoCoverMouth.txt"
	);
	private final VboInfo _vboInfoCorsageCoverMouth1 = new VboInfo (
		"00_Yucco/Hair/Waso/V_CorsageWasoCoverMouth1.txt",
		"00_Yucco/Hair/Waso/VN_CorsageWasoCoverMouth1.txt",
		"00_Yucco/Hair/Waso/VT_CorsageWasoCoverMouth1.txt"
	);
	private final VboInfo _vboInfoCorsageCoverMouth2 = new VboInfo (
		"00_Yucco/Hair/Waso/V_CorsageWasoCoverMouth2.txt",
		"00_Yucco/Hair/Waso/VN_CorsageWasoCoverMouth2.txt",
		"00_Yucco/Hair/Waso/VT_CorsageWasoCoverMouth2.txt"
	);
	//髪（口元隠しポーズ 頭右向き）
	private final VboInfo _vboInfoHairCoverMouthR = new VboInfo (
		"00_Yucco/Hair/Waso/V_HairWasoCoverMouthR.txt",
		"00_Yucco/Hair/Waso/VN_HairWasoCoverMouthR.txt",
		"00_Yucco/Hair/Waso/VT_HairWasoCoverMouthR.txt"
	);
	private final VboInfo _vboInfoCorsageCoverMouthR1 = new VboInfo (
		"00_Yucco/Hair/Waso/V_CorsageWasoCoverMouthR1.txt",
		"00_Yucco/Hair/Waso/VN_CorsageWasoCoverMouthR1.txt",
		"00_Yucco/Hair/Waso/VT_CorsageWasoCoverMouthR1.txt"
	);
	private final VboInfo _vboInfoCorsageCoverMouthR2 = new VboInfo (
		"00_Yucco/Hair/Waso/V_CorsageWasoCoverMouthR2.txt",
		"00_Yucco/Hair/Waso/VN_CorsageWasoCoverMouthR2.txt",
		"00_Yucco/Hair/Waso/VT_CorsageWasoCoverMouthR2.txt"
	);
	//衣装（口元隠しポーズ）
	private final VboInfo _vboInfoDressCoverMouth = new VboInfo (
		"00_Yucco/Dress/Waso/V_DressWasoCoverMouth.txt",
		"00_Yucco/Dress/Waso/VN_DressWasoCoverMouth.txt",
		"00_Yucco/Dress/Waso/VT_DressWasoCoverMouth.txt"
	);

	//ファイル名一覧取得
	private VboInfo[] _vboInfos = new VboInfo[]{
		// しゃがみポーズ
		_vboInfoHeadCrouch,
		_vboInfoBodyCrouch,
		_vboInfoHairCrouch,
		_vboInfoCorsageCrouch1,
		_vboInfoCorsageCrouch2,
		_vboInfoDressCrouch,
		_vboInfoShoeCrouch,
		// 万歳ポーズ
		_vboInfoHeadBanzai,
		_vboInfoBodyBanzai,
		_vboInfoHairBanzai,
		_vboInfoCorsageBanzai1,
		_vboInfoCorsageBanzai2,
		_vboInfoDressBanzai,
		_vboInfoShoeBanzai,
		// 片手上げポーズ
		_vboInfoHeadHandUp,
		_vboInfoBodyHandUp,
		_vboInfoHairHandUp,
		_vboInfoCorsageHandUp1,
		_vboInfoCorsageHandUp2,
		_vboInfoDressHandUp,
		_vboInfoShoeHandUp,
		// 頬杖左ポーズ
		_vboInfoHeadHozueL,
		_vboInfoBodyHozueL,
		_vboInfoHairHozueL,
		_vboInfoCorsageHozueL1,
		_vboInfoCorsageHozueL2,
		_vboInfoDressHozueL,
		// 頬杖右ポーズ
		_vboInfoHeadHozueR,
		_vboInfoBodyHozueR,
		_vboInfoHairHozueR,
		_vboInfoCorsageHozueR1,
		_vboInfoCorsageHozueR2,
		_vboInfoDressHozueR,
		// 口元隠しポーズ
		_vboInfoHeadCoverMouth,
		_vboInfoHeadCoverMouthR,
		_vboInfoBodyCoverMouth,
		_vboInfoHairCoverMouth,
		_vboInfoCorsageCoverMouth1,
		_vboInfoCorsageCoverMouth2,
		_vboInfoHairCoverMouthR,
		_vboInfoCorsageCoverMouthR1,
		_vboInfoCorsageCoverMouthR2,
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
				_draw3DObject.draw3D(_corsageTexture1, _vboInfoCorsageCrouch1);
				_draw3DObject.draw3D(_corsageTexture2, _vboInfoCorsageCrouch2);
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
				_draw3DObject.draw3D(_corsageTexture1, _vboInfoCorsageBanzai1);
				_draw3DObject.draw3D(_corsageTexture2, _vboInfoCorsageBanzai2);
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
				_draw3DObject.draw3D(_corsageTexture1, _vboInfoCorsageHandUp1);
				_draw3DObject.draw3D(_corsageTexture2, _vboInfoCorsageHandUp2);
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
				_draw3DObject.draw3D(_corsageTexture1, _vboInfoCorsageHozueL1);
				_draw3DObject.draw3D(_corsageTexture2, _vboInfoCorsageHozueL2);
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
				_draw3DObject.draw3D(_corsageTexture1, _vboInfoCorsageHozueR1);
				_draw3DObject.draw3D(_corsageTexture2, _vboInfoCorsageHozueR2);
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
				_draw3DObject.draw3D(_corsageTexture1, _vboInfoCorsageCoverMouth1);
				_draw3DObject.draw3D(_corsageTexture2, _vboInfoCorsageCoverMouth2);
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
				_draw3DObject.draw3D(_corsageTexture1, _vboInfoCorsageCoverMouthR1);
				_draw3DObject.draw3D(_corsageTexture2, _vboInfoCorsageCoverMouthR2);
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
