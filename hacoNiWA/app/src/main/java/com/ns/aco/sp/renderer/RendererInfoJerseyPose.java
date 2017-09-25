package com.ns.aco.sp.renderer;

import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.graphics.Bitmap;

import com.ns.aco.sp.R;
import com.ns.aco.sp.common.delegate.DelegateIF;
import com.ns.aco.sp.service.ServiceContract;

public class RendererInfoJerseyPose extends RendererInfoJersey {
	// コンストラクタ
	public RendererInfoJerseyPose(GL11 gl11, ServiceContract.Service service) {
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
		"00_Yucco/Hair/TwinTail/V_HairTwinTailCrouch.txt",
		"00_Yucco/Hair/TwinTail/VN_HairTwinTailCrouch.txt",
		"00_Yucco/Hair/TwinTail/VT_HairTwinTailCrouch.txt"
	);
	//衣装（しゃがみポーズ）
	private final VboInfo _vboInfoDressCrouch_Miku = new VboInfo (
		"00_Yucco/Dress/Miku/V_DressMikuCrouch.txt",
		"00_Yucco/Dress/Miku/VN_DressMikuCrouch.txt",
		"00_Yucco/Dress/Miku/VT_DressMikuCrouch.txt"
	);
	private final VboInfo _vboInfoDressCrouch_Jersey = new VboInfo (
		"00_Yucco/Dress/Jersey/V_DressJerseyCrouch.txt",
		"00_Yucco/Dress/Jersey/VN_DressJerseyCrouch.txt",
		"00_Yucco/Dress/Jersey/VT_DressJerseyCrouch.txt"
	);
	//靴（しゃがみポーズ）
	private final VboInfo _vboInfoShoeCrouch = new VboInfo (
		"00_Yucco/Shoe/Loafer/V_ShoeLoaferCrouch.txt",
		"00_Yucco/Shoe/Loafer/VN_ShoeLoaferCrouch.txt",
		"00_Yucco/Shoe/Loafer/VT_ShoeLoaferCrouch.txt"
	);
	//アームカバー（しゃがみポーズ）
	private final VboInfo _vboInfoSleeveCrouch = new VboInfo (
		"00_Yucco/Sleeve/Miku/V_SleeveMikuCrouch.txt",
		"00_Yucco/Sleeve/Miku/VN_SleeveMikuCrouch.txt",
		"00_Yucco/Sleeve/Miku/VT_SleeveMikuCrouch.txt"
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
		"00_Yucco/Hair/TwinTail/V_HairTwinTailBanzai.txt",
		"00_Yucco/Hair/TwinTail/VN_HairTwinTailBanzai.txt",
		"00_Yucco/Hair/TwinTail/VT_HairTwinTailBanzai.txt"
	);
	//衣装（万歳ポーズ）
	private final VboInfo _vboInfoDressBanzai_Miku = new VboInfo (
		"00_Yucco/Dress/Miku/V_DressMikuBanzai.txt",
		"00_Yucco/Dress/Miku/VN_DressMikuBanzai.txt",
		"00_Yucco/Dress/Miku/VT_DressMikuBanzai.txt"
	);
	private final VboInfo _vboInfoDressBanzai_Jersey = new VboInfo (
		"00_Yucco/Dress/Jersey/V_DressJerseyBanzai.txt",
		"00_Yucco/Dress/Jersey/VN_DressJerseyBanzai.txt",
		"00_Yucco/Dress/Jersey/VT_DressJerseyBanzai.txt"
	);
	//靴（万歳ポーズ）
	private final VboInfo _vboInfoShoeBanzai = new VboInfo (
		"00_Yucco/Shoe/Loafer/V_ShoeLoaferBanzai.txt",
		"00_Yucco/Shoe/Loafer/VN_ShoeLoaferBanzai.txt",
		"00_Yucco/Shoe/Loafer/VT_ShoeLoaferBanzai.txt"
	);
	//アームカバー（万歳ポーズ）
	private final VboInfo _vboInfoSleeveBanzai = new VboInfo (
		"00_Yucco/Sleeve/Miku/V_SleeveMikuBanzai.txt",
		"00_Yucco/Sleeve/Miku/VN_SleeveMikuBanzai.txt",
		"00_Yucco/Sleeve/Miku/VT_SleeveMikuBanzai.txt"
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
		"00_Yucco/Hair/TwinTail/V_HairTwinTailHandUp.txt",
		"00_Yucco/Hair/TwinTail/VN_HairTwinTailHandUp.txt",
		"00_Yucco/Hair/TwinTail/VT_HairTwinTailHandUp.txt"
	);
	//衣装（片手上げポーズ）
	private final VboInfo _vboInfoDressHandUp_Miku = new VboInfo (
		"00_Yucco/Dress/Miku/V_DressMikuHandUp.txt",
		"00_Yucco/Dress/Miku/VN_DressMikuHandUp.txt",
		"00_Yucco/Dress/Miku/VT_DressMikuHandUp.txt"
	);
	private final VboInfo _vboInfoDressHandUp_Jersey = new VboInfo (
		"00_Yucco/Dress/Jersey/V_DressJerseyHandUp.txt",
		"00_Yucco/Dress/Jersey/VN_DressJerseyHandUp.txt",
		"00_Yucco/Dress/Jersey/VT_DressJerseyHandUp.txt"
	);
	//靴（片手上げポーズ）
	private final VboInfo _vboInfoShoeHandUp = new VboInfo (
		"00_Yucco/Shoe/Loafer/V_ShoeLoaferHandUp.txt",
		"00_Yucco/Shoe/Loafer/VN_ShoeLoaferHandUp.txt",
		"00_Yucco/Shoe/Loafer/VT_ShoeLoaferHandUp.txt"
	);
	//アームカバー（片手上げポーズ）
	private final VboInfo _vboInfoSleeveHandUp = new VboInfo (
		"00_Yucco/Sleeve/Miku/V_SleeveMikuHandUp.txt",
		"00_Yucco/Sleeve/Miku/VN_SleeveMikuHandUp.txt",
		"00_Yucco/Sleeve/Miku/VT_SleeveMikuHandUp.txt"
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
		"00_Yucco/Hair/TwinTail/V_HairTwinTailHozueL.txt",
		"00_Yucco/Hair/TwinTail/VN_HairTwinTailHozueL.txt",
		"00_Yucco/Hair/TwinTail/VT_HairTwinTailHozueL.txt"
	);
	//衣装（頬杖左ポーズ）
	private final VboInfo _vboInfoDressHozueL_Miku = new VboInfo (
		"00_Yucco/Dress/Miku/V_DressMikuHozueL.txt",
		"00_Yucco/Dress/Miku/VN_DressMikuHozueL.txt",
		"00_Yucco/Dress/Miku/VT_DressMikuHozueL.txt"
	);
	private final VboInfo _vboInfoDressHozueL_Jersey = new VboInfo (
		"00_Yucco/Dress/Jersey/V_DressJerseyHozueL.txt",
		"00_Yucco/Dress/Jersey/VN_DressJerseyHozueL.txt",
		"00_Yucco/Dress/Jersey/VT_DressJerseyHozueL.txt"
	);
	//アームカバー（頬杖左ポーズ）
	private final VboInfo _vboInfoSleeveHozueL = new VboInfo (
		"00_Yucco/Sleeve/Miku/V_SleeveMikuHozueL.txt",
		"00_Yucco/Sleeve/Miku/VN_SleeveMikuHozueL.txt",
		"00_Yucco/Sleeve/Miku/VT_SleeveMikuHozueL.txt"
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
		"00_Yucco/Hair/TwinTail/V_HairTwinTailHozueR.txt",
		"00_Yucco/Hair/TwinTail/VN_HairTwinTailHozueR.txt",
		"00_Yucco/Hair/TwinTail/VT_HairTwinTailHozueR.txt"
	);
	//衣装（頬杖右ポーズ）
	private final VboInfo _vboInfoDressHozueR_Miku = new VboInfo (
		"00_Yucco/Dress/Miku/V_DressMikuHozueR.txt",
		"00_Yucco/Dress/Miku/VN_DressMikuHozueR.txt",
		"00_Yucco/Dress/Miku/VT_DressMikuHozueR.txt"
	);
	private final VboInfo _vboInfoDressHozueR_Jersey = new VboInfo (
		"00_Yucco/Dress/Jersey/V_DressJerseyHozueR.txt",
		"00_Yucco/Dress/Jersey/VN_DressJerseyHozueR.txt",
		"00_Yucco/Dress/Jersey/VT_DressJerseyHozueR.txt"
	);
	//アームカバー（頬杖右ポーズ）
	private final VboInfo _vboInfoSleeveHozueR = new VboInfo (
		"00_Yucco/Sleeve/Miku/V_SleeveMikuHozueR.txt",
		"00_Yucco/Sleeve/Miku/VN_SleeveMikuHozueR.txt",
		"00_Yucco/Sleeve/Miku/VT_SleeveMikuHozueR.txt"
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
	//髪（口元隠しポーズ）
	private final VboInfo _vboInfoHairCoverMouth = new VboInfo (
		"00_Yucco/Hair/TwinTail/V_HairTwinTailCoverMouth.txt",
		"00_Yucco/Hair/TwinTail/VN_HairTwinTailCoverMouth.txt",
		"00_Yucco/Hair/TwinTail/VT_HairTwinTailCoverMouth.txt"
	);
	//頭（口元隠しポーズ 頭右向き）
	private final VboInfo _vboInfoHeadCoverMouthR = new VboInfo (
		"00_Yucco/Head/V_HeadCoverMouthR.txt",
		"00_Yucco/Head/VN_HeadCoverMouthR.txt",
		"00_Yucco/Head/VT_HeadCoverMouthR.txt"
	);
	//髪（口元隠しポーズ 頭右向き）
	private final VboInfo _vboInfoHairCoverMouthR = new VboInfo (
		"00_Yucco/Hair/TwinTail/V_HairTwinTailCoverMouthR.txt",
		"00_Yucco/Hair/TwinTail/VN_HairTwinTailCoverMouthR.txt",
		"00_Yucco/Hair/TwinTail/VT_HairTwinTailCoverMouthR.txt"
	);
	//衣装（口元隠しポーズ）
	private final VboInfo _vboInfoDressCoverMouth_Miku = new VboInfo (
		"00_Yucco/Dress/Miku/V_DressMikuCoverMouth.txt",
		"00_Yucco/Dress/Miku/VN_DressMikuCoverMouth.txt",
		"00_Yucco/Dress/Miku/VT_DressMikuCoverMouth.txt"
	);
	private final VboInfo _vboInfoDressCoverMouth_Jersey = new VboInfo (
		"00_Yucco/Dress/Jersey/V_DressJerseyCoverMouth.txt",
		"00_Yucco/Dress/Jersey/VN_DressJerseyCoverMouth.txt",
		"00_Yucco/Dress/Jersey/VT_DressJerseyCoverMouth.txt"
	);
	//アームカバー（口元隠しポーズ）
	private final VboInfo _vboInfoSleeveCoverMouth = new VboInfo (
		"00_Yucco/Sleeve/Miku/V_SleeveMikuCoverMouth.txt",
		"00_Yucco/Sleeve/Miku/VN_SleeveMikuCoverMouth.txt",
		"00_Yucco/Sleeve/Miku/VT_SleeveMikuCoverMouth.txt"
	);

	//ファイル名一覧取得
	private VboInfo[] _vboInfos_Miku = new VboInfo[]{
		// しゃがみポーズ
		_vboInfoHeadCrouch,
		_vboInfoBodyCrouch,
		_vboInfoHairCrouch,
		_vboInfoDressCrouch_Miku,
		_vboInfoShoeCrouch,
		_vboInfoSleeveCrouch,
		// 万歳ポーズ
		_vboInfoHeadBanzai,
		_vboInfoBodyBanzai,
		_vboInfoHairBanzai,
		_vboInfoDressBanzai_Miku,
		_vboInfoShoeBanzai,
		_vboInfoSleeveBanzai,
		// 片手上げポーズ
		_vboInfoHeadHandUp,
		_vboInfoBodyHandUp,
		_vboInfoHairHandUp,
		_vboInfoDressHandUp_Miku,
		_vboInfoShoeHandUp,
		_vboInfoSleeveHandUp,
		// 頬杖左ポーズ
		_vboInfoHeadHozueL,
		_vboInfoBodyHozueL,
		_vboInfoHairHozueL,
		_vboInfoDressHozueL_Miku,
		_vboInfoSleeveHozueL,
		// 頬杖右ポーズ
		_vboInfoHeadHozueR,
		_vboInfoBodyHozueR,
		_vboInfoHairHozueR,
		_vboInfoDressHozueR_Miku,
		_vboInfoSleeveHozueR,
		// 口元隠しポーズ
		_vboInfoHeadCoverMouth,
		_vboInfoHeadCoverMouthR,
		_vboInfoBodyCoverMouth,
		_vboInfoHairCoverMouth,
		_vboInfoHairCoverMouthR,
		_vboInfoDressCoverMouth_Miku,
		_vboInfoSleeveCoverMouth,
		// 頬杖時の足
		_vboInfoShoeMotionLess,
	};

	//ファイル名一覧取得
	private VboInfo[] _vboInfos_Jersey = new VboInfo[]{
		// しゃがみポーズ
		_vboInfoHeadCrouch,
		_vboInfoBodyCrouch,
		_vboInfoHairCrouch,
		_vboInfoDressCrouch_Jersey,
		_vboInfoShoeCrouch,
		// 万歳ポーズ
		_vboInfoHeadBanzai,
		_vboInfoBodyBanzai,
		_vboInfoHairBanzai,
		_vboInfoDressBanzai_Jersey,
		_vboInfoShoeBanzai,
		// 片手上げポーズ
		_vboInfoHeadHandUp,
		_vboInfoBodyHandUp,
		_vboInfoHairHandUp,
		_vboInfoDressHandUp_Jersey,
		_vboInfoShoeHandUp,
		// 頬杖左ポーズ
		_vboInfoHeadHozueL,
		_vboInfoBodyHozueL,
		_vboInfoHairHozueL,
		_vboInfoDressHozueL_Jersey,
		// 頬杖右ポーズ
		_vboInfoHeadHozueR,
		_vboInfoBodyHozueR,
		_vboInfoHairHozueR,
		_vboInfoDressHozueR_Jersey,
		// 口元隠しポーズ
		_vboInfoHeadCoverMouth,
		_vboInfoHeadCoverMouthR,
		_vboInfoBodyCoverMouth,
		_vboInfoHairCoverMouth,
		_vboInfoHairCoverMouthR,
		_vboInfoDressCoverMouth_Jersey,
		// 頬杖時の足
		_vboInfoShoeMotionLess,
	};

	// 描画するVBO数取得
	@Override
	public int getDrawBitmapCount(){
		int setCount = 0;
		if (_service.get_charaNo() == R.drawable.gate_cosplay1_top){
			setCount = _vboInfos_Miku.length;
		}else{
			setCount = _vboInfos_Jersey.length;
		}
		int drawCount = _getBitmapBanzai.size()
					  + _getBitmapHandUp.size()
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
			_service.setMaxValueProgressDialog(getDrawBitmapCount());
			loadRendererModel();
		}
		boolean success = createBitmapList(init, pixelWidth, pixelHeight);
		// 描画が成功した場合のみ、親クラスの処理を行う
		if (success){
			super.setRendererBitmapCash(false, pixelWidth, pixelHeight);
		}
		// VBOの削除処理を中継関数を介して行う
		deleteVboInfos(_vboInfos_Jersey);
		deleteVboInfos(_vboInfos_Miku);
	}

	// 3Dモデルの描画
	private boolean createBitmapList(boolean initPixels, int pixelWidth, int pixelHeight){
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

		// ☆☆☆万歳ポーズ格納☆☆☆
		Bitmap[] banzaiPoseList = new Bitmap[_getBitmapBanzai.size()];
		for (int i = 0; i < _getBitmapBanzai.size(); i++){
			banzaiPoseList[i] = _getBitmapBanzai.get(i).invoke(initPixels, pixelWidth, pixelHeight);
			if (banzaiPoseList[i] == null){
				return false;
			}else{
				// プログレスバーの現在値を更新
				_service.incrementProgressDialog(1);
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
				_service.incrementProgressDialog(1);
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
				_service.incrementProgressDialog(1);
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
				_service.incrementProgressDialog(1);
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

				boolean resultDraw = true;
				if (_service.get_charaNo() == R.drawable.gate_cosplay1_top){
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressCrouch_Miku);
					_draw3DObject.draw3D(_faceTexture, _vboInfoHeadCrouch);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyCrouch);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairCrouch);
					_draw3DObject.draw3D(_sleeveTexture, _vboInfoSleeveCrouch);
					resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeCrouch);
				}else{
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressCrouch_Jersey);
					_draw3DObject.draw3D(_faceTexture, _vboInfoHeadCrouch);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyCrouch);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairCrouch);
					resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeCrouch);
				}

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

				boolean resultDraw = true;
				if (_service.get_charaNo() == R.drawable.gate_cosplay1_top){
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressBanzai_Miku);
					_draw3DObject.draw3D(_faceTexture_swagger, _vboInfoHeadBanzai);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyBanzai);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairBanzai);
					_draw3DObject.draw3D(_sleeveTexture, _vboInfoSleeveBanzai);
					resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeBanzai);
				}else{
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressBanzai_Jersey);
					_draw3DObject.draw3D(_faceTexture_swagger, _vboInfoHeadBanzai);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyBanzai);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairBanzai);
					resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeBanzai);
				}

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

				boolean resultDraw = true;
				if (_service.get_charaNo() == R.drawable.gate_cosplay1_top){
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressHandUp_Miku);
					_draw3DObject.draw3D(_faceTexture_wink, _vboInfoHeadHandUp);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyHandUp);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairHandUp);
					_draw3DObject.draw3D(_sleeveTexture, _vboInfoSleeveHandUp);
					resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeHandUp);
				}else{
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressHandUp_Jersey);
					_draw3DObject.draw3D(_faceTexture_wink, _vboInfoHeadHandUp);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyHandUp);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairHandUp);
					resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeHandUp);
				}

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

				boolean resultDraw = true;
				if (_service.get_charaNo() == R.drawable.gate_cosplay1_top){
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressHozueL_Miku);
					_draw3DObject.draw3D(_faceTexture_swagger, _vboInfoHeadHozueL);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyHozueL);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairHozueL);
					_draw3DObject.draw3D(_sleeveTexture, _vboInfoSleeveHozueL);
					resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeMotionLess);
				}else{
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressHozueL_Jersey);
					_draw3DObject.draw3D(_faceTexture_swagger, _vboInfoHeadHozueL);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyHozueL);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairHozueL);
					resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeMotionLess);
				}

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

				boolean resultDraw = true;
				if (_service.get_charaNo() == R.drawable.gate_cosplay1_top){
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressHozueR_Miku);
					_draw3DObject.draw3D(_faceTexture_swagger, _vboInfoHeadHozueR);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyHozueR);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairHozueR);
					_draw3DObject.draw3D(_sleeveTexture, _vboInfoSleeveHozueR);
					resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeMotionLess);
				}else{
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressHozueR_Jersey);
					_draw3DObject.draw3D(_faceTexture_swagger, _vboInfoHeadHozueR);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyHozueR);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairHozueR);
					resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeMotionLess);
				}

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

				boolean resultDraw = true;
				if (_service.get_charaNo() == R.drawable.gate_cosplay1_top){
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressCoverMouth_Miku);
					_draw3DObject.draw3D(_faceTexture, _vboInfoHeadCoverMouth);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyCoverMouth);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairCoverMouth);
					_draw3DObject.draw3D(_sleeveTexture, _vboInfoSleeveCoverMouth);
					resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeMotionLess);
				}else{
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressCoverMouth_Jersey);
					_draw3DObject.draw3D(_faceTexture, _vboInfoHeadCoverMouth);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyCoverMouth);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairCoverMouth);
					resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeMotionLess);
				}

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

				boolean resultDraw = true;
				if (_service.get_charaNo() == R.drawable.gate_cosplay1_top){
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressCoverMouth_Miku);
					_draw3DObject.draw3D(_faceTexture_away_right, _vboInfoHeadCoverMouthR);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyCoverMouth);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairCoverMouthR);
					_draw3DObject.draw3D(_sleeveTexture, _vboInfoSleeveCoverMouth);
					resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeMotionLess);
				}else{
					_draw3DObject.draw3D(_dressTexture, _vboInfoDressCoverMouth_Jersey);
					_draw3DObject.draw3D(_faceTexture_away_right, _vboInfoHeadCoverMouthR);
					_draw3DObject.draw3D(_bodyTexture, _vboInfoBodyCoverMouth);
					_draw3DObject.draw3D(_hairTexture, _vboInfoHairCoverMouthR);
					resultDraw = _draw3DObject.draw3D(_shoeTexture, _vboInfoShoeMotionLess);
				}

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
