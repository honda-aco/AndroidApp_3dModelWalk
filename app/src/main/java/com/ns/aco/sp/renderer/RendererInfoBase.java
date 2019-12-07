package com.ns.aco.sp.renderer;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.microedition.khronos.opengles.GL11;

import com.ns.aco.sp.cpp.CallNative;
import com.ns.aco.sp.da.entity.DataRowCharaPoseBitmap;
import com.ns.aco.sp.da.entity.DataRowCharaWalkBitmap;
import com.ns.aco.sp.da.OperateDataBase;
import com.ns.aco.sp.service.ServiceContract;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

public abstract class RendererInfoBase implements RendererInfo {
	// コンテキスト
	protected ServiceContract.Service _service = null;
	// OpenGLオブジェクト
	protected GL11 _gl11 = null;
	// モデル描画クラス
	protected Draw3dObject _draw3DObject = null;
	// DB操作クラス
	protected OperateDataBase _operateDataBase;
	// モデルの進行方向
	protected Integer[] forwardDirection = null;
	// 最後の進行方向
	protected DirectForCash _lastDirection = null;
	// 左右対称フラグ（true：画像を360度全て取得せず、0~180度の画像を反転させて使いまわす）
	private boolean _flipHorizontal = false;
	// ウォーク用ビットマップのキャッシュ
	ArrayList<DataRowCharaWalkBitmap> _charaWalkBitmapList = null;
	// 方向別ビットマップキャッシュのリスト
	private ArrayList<Bitmap[]> cashBitmap_direct0 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct15 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct30 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct45 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct60 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct75 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct90 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct105 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct120 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct135 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct150 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct165 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct180 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct195 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct210 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct225 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct240 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct255 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct270 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct285 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct300 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct315 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct330 = null;
	private ArrayList<Bitmap[]> cashBitmap_direct345 = null;
	// OpenGLの連続ポーズ格納用リスト
	private ArrayList<Bitmap[]> _cashBitmapPose = null;
	// ポーズの設定数
	private int _poseCount = 0;
	// OpenGLの連続ポーズ返却中ポーズビットマップ
	private Bitmap[] _bitmapPose = null;
	// 連続ポーズビットマップの取得済要素番号
	private int _cashBitmapPoseNo = 0;
	// ランダム値取得用オブジェクト
	private Random _random = new Random();
	// ビットマップキャッシュ設定完了フラグ
	protected  boolean _finishCashSet = false;
	// ピクセル情報の読込み先
	private int pixels[];
	// ピクセル情報の読込み先をラップするバッファ
	private IntBuffer pixelBuffer;
	// 振り子カウント3（0から2、2から0と順に増減するカウント変数）
	private int mPendulumCount3 = 1;
	// 振り子カウント3用フラグ（true時はカウントを増加させ、false時はカウントを減少させる）
	private boolean mPendulumFlg3 = true;
	// 振り子カウント5（0から4、4から0と順に増減するカウント変数）
	private int mPendulumCount5 = 2;
	// 振り子カウント5用フラグ（true時はカウントを増加させ、false時はカウントを減少させる）
	private boolean mPendulumFlg5 = true;
	// テクスチャを管理するためのID
	protected int _hatTexture;
	protected int _bodyTexture;
	protected int _faceTexture;
	protected int _faceTexture_close;
	protected int _faceTexture_swagger;
	protected int _faceTexture_away_right;
	protected int _faceTexture_wink;
	protected int _hairTexture;
	protected int _corsageTexture1;
	protected int _corsageTexture2;
	protected int _sleeveTexture;
	protected int _shoeTexture;
	protected int _dressTexture;
	// ピクセル変換用クラス
	private CallNative.Bitmap _bitmap = null;

	protected int[] _textures = new int[]{
		_bodyTexture,
		_faceTexture,
		_faceTexture_close,
		_faceTexture_swagger,
		_faceTexture_away_right,
		_faceTexture_wink,
		_hairTexture,
		_sleeveTexture,
		_shoeTexture,
		_dressTexture,
	};

	// コンストラクタ
	public RendererInfoBase(GL11 gl11, ServiceContract.Service service, boolean flipHorizontal){
		_draw3DObject= new Draw3dObject(service, gl11);
		_operateDataBase  = new OperateDataBase(service.get_service());
		_operateDataBase.open();
		_operateDataBase.delete_CHARA_WALK_BITMAP();
		_operateDataBase.delete_CHARA_POSE_BITMAP();
//		_context = context;
		_gl11 = gl11;
		_service = service;
//		_rendererType = rendererType;
		_flipHorizontal = flipHorizontal;
		// ビットマップキャッシュ設定完了フラグを折る
		_finishCashSet = false;
		// 省エネモードの場合、進行方向の候補を減らす
		if (_service.is_savingEnergy()){
			// 左右反転機能が有効な場合は195度以降は省略する
			if (flipHorizontal){
				forwardDirection = new Integer[]{
					0, 45, 90, 135, 180};
			}else{
				forwardDirection = new Integer[]{
					0, 45, 90, 135, 180, 225, 270, 315};
			}
		}else{
			// 左右反転機能が有効な場合は195度以降は省略する
			if (flipHorizontal){
				forwardDirection = new Integer[]{
					0, 30, 60, 90, 120, 150, 180};
			}else{
				forwardDirection = new Integer[]{
					0, 30, 60, 90, 120, 150, 180, 210, 240, 270, 300, 330};
			}
		}
		// ポーズ用キャッシュが存在する場合はクリアする
		if (_cashBitmapPose != null){
			_cashBitmapPose.clear();
		}
		// OpenGL用テクスチャ領域を初期化する
		_draw3DObject.deleteTexture( _textures);
		// キャッシュ領域を初期化
		// createDirectBitmapCash();
		// ピクセル変換用クラス
		_bitmap = new CallNative().new Bitmap();
	}

	// ビットマップキャッシュ設定完了フラグを取得
	public  boolean isFinishCashSet(){
		return _finishCashSet;
	}

    // VBO登録処理中継クラス
	public VboInfo setVboInfos(VboInfo vboInfo, boolean fromAsset) {

		// VBOに頂点情報を読込む
		long start = System.currentTimeMillis();
		if (vboInfo.getFaceCount() == 0){
			Integer[] setVboInfo = null;
			// Asset内のファイルかによって呼び出す関数を切り替える
			if (fromAsset){
				setVboInfo = _draw3DObject.setVboFromAsset(vboInfo);
			}else{
				setVboInfo = _draw3DObject.setVbo(vboInfo);
			}

			if (setVboInfo == null){
				return null;
			}else{
				//描画する面の数およびVbo格納先バッファ番号を登録する
				vboInfo.setFaceCount(setVboInfo[0]);
				vboInfo.setBuffNoVertex(setVboInfo[1]);
				vboInfo.setBuffNoNormal(setVboInfo[2]);
				vboInfo.setBuffNoCood(setVboInfo[3]);
			}
		}
		long stop = System.currentTimeMillis();
		Log.i("TimeCount", vboInfo.getVertexFilePath() + ":" + (stop - start) + "ミリ秒");
		return vboInfo;
	}

    // VBO削除処理中継クラス
	public void deleteVboInfos(VboInfo[] vboInfos) {
		if (vboInfos == null){
			return;
		}

		for (int i = 0; i < vboInfos.length; i++){
			if (vboInfos[i] != null){
				_draw3DObject.deleteVbo(vboInfos[i]);
			}
		}
	}

    // VBO削除処理中継クラス
	public void deleteVboInfos(List<VboInfo> vboInfos) {
		if (vboInfos == null){
			return;
		}

		for (int i = 0; i < vboInfos.size(); i++){
			if (vboInfos.get(i) != null){
				_draw3DObject.deleteVbo(vboInfos.get(i));
			}
		}
	}

	protected Bitmap capture(boolean initPixelBuffer, int pixelWidth, int pixelHeight) {
		if (initPixelBuffer){
			pixels = new int[pixelWidth * pixelHeight];
			pixelBuffer = IntBuffer.wrap(pixels);
		}
		pixelBuffer.position(0);
		_gl11.glReadPixels(0, 0, pixelWidth, pixelHeight, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixelBuffer);

		Bitmap bitmap = Bitmap.createBitmap(pixelWidth, pixelHeight, Bitmap.Config.ARGB_8888);

		// 黒色部分のみ透過させる
		_bitmap.specificPixelToClear(pixels, pixels.length, -16777216);
//		for (int i = 0; i < pixels.length; i++){
//			if (pixels[i] == -16777216){
//				pixels[i]  = 0;
//			}
//		}
		bitmap.copyPixelsFromBuffer(pixelBuffer);

		// 上下反転させる
		Matrix matrix = new Matrix();
		matrix.postScale(1.0f, -1.0f);

		return Bitmap.createBitmap(bitmap, 0, 0, pixelWidth, pixelHeight, matrix, false);
	}

	// ビットマップの左右反転
	private Bitmap changeX180(Bitmap bitmap){
		Matrix matrix = new Matrix();
		// 上下反転
		// matrix.preScale(1, -1);
		// 左右反転
		matrix.preScale(-1, 1);
		// 上下左右反転
		// matrix.preScale(-1, -1);
		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
	}

	private Bitmap createBitmap(int[] pixels, int width, int height, Bitmap.Config config) {
		// 取得したピクセルデータは R (赤) と B (青) が逆
		// また垂直方向も逆になっているので以下のように ColorMatrix と Matrix を使用して修正

		/*
		 * カラーチャネルを交換するために ColorMatrix と ColorMatrixFilter を使用
		 *
		 * 5x4 のマトリックス: [
		 *   a, b, c, d, e,
		 *   f, g, h, i, j,
		 *   k, l, m, n, o,
		 *   p, q, r, s, t
		 * ]
		 *
		 * RGBA カラーへ適用する場合、以下のように計算
		 *
		 * R' = a * R + b * G + c * B + d * A + e;
		 * G' = f * R + g * G + h * B + i * A + j;
		 * B' = k * R + l * G + m * B + n * A + o;
		 * A' = p * R + q * G + r * B + s * A + t;
		 *
		 * R (赤) と B (青) を交換したいので以下の様にする
		 *
		 * R' = B => 0, 0, 1, 0, 0
		 * G' = G => 0, 1, 0, 0, 0
		 * B' = R => 1, 0, 0, 0, 0
		 * A' = A => 0, 0, 0, 1, 0
		 */
		Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
		// R (赤) と B (青) が逆なので交換
		paint.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[] {
				// 3Dモデル以外の背景のみ透過させるため、Aを除いたRGBの乗算した値をAの値として使用する
				// 背景が黒（RGBが全て0）である場合、RGBを乗算した値を合算した値は0になるため、A=0により透過させることが可能
				// 背景は黒であること、3Dモデルに黒が使用されていないことが条件となる（苦肉の策のため、他の方法を見つけ次第修正する）
				0, 0, 1, 0, 0,
				0, 1, 0, 0, 0,
				1, 0, 0, 0, 0,
				0, 0, 0, 1, 0,
		})));

		Bitmap bitmap = Bitmap.createBitmap(width, height, config);
		Canvas canvas = new Canvas(bitmap);
		// 上下が逆さまなので垂直方向に反転
		Matrix matrix = new Matrix();
		matrix.postScale(1.0f, -1.0f);
		matrix.postTranslate(0, height);
		canvas.concat(matrix);

		canvas.drawBitmap(pixels, 0, width, 0, 0, width, height, true, paint);
		return bitmap;
	}

	// 振り子カウント3がカウントアップ中の場合はTrue
	protected boolean IsCountUpPendulum3(){
		return mPendulumFlg3;
	}

	// 振り子カウント5がカウントアップ中の場合はTrue
	protected boolean IsCountUpPendulum5(){
		return mPendulumFlg5;
	}

	// 振り子カウント3の現在値を取得
	public int getPendulumCount3(){
		return mPendulumCount3;
	}

	// 振り子カウント5の現在値を取得
	protected int getPendulumCount5(){
		return mPendulumCount5;
	}

	// 振り子カウント3を1つ進める
	// 振り子カウントとは0→1→2→3→4→3→2→1→2→3……のように、
	// 一定の値の幅を繰返す形式のカウンタ
	protected void countPendulumCount3(){
		if (mPendulumFlg3){
			if (mPendulumCount3 < 2){
				mPendulumCount3 += 1;
			}else{
				mPendulumFlg3 = false;
				mPendulumCount3 -= 1;
			}
		}else{
			if (mPendulumCount3 > 0){
				mPendulumCount3 -= 1;
			}else{
				mPendulumFlg3 = true;
				mPendulumCount3 += 1;
			}
		}
	}

	// 振り子カウント5を1つ進める
	// 振り子カウントとは0→1→2→3→4→3→2→1→2→3……のように、
	// 一定の値の幅を繰返す形式のカウンタ
	private void countPendulumCount5(){
		if (mPendulumFlg5){
			if (mPendulumCount5 < 4){
				mPendulumCount5 += 1;
			}else{
				mPendulumFlg5 = false;
				mPendulumCount5 -= 1;
			}
		}else{
			if (mPendulumCount5 > 0){
				mPendulumCount5 -= 1;
			}else{
				mPendulumFlg5 = true;
				mPendulumCount5 += 1;
			}
		}
	}

	// 振り子カウント3初期化
	protected void initPendulumCount3(int count, boolean countUpFlg){
		mPendulumCount3 = count;
		mPendulumFlg3 = countUpFlg;
	}

	// 振り子カウント5初期化
	protected void initPendulumCount5(int count, boolean countUpFlg){
		mPendulumCount5 = count;
		mPendulumFlg5 = countUpFlg;
	}

	// 5*3のリストとビットマップ配列による、OpenGLの方向別ビットマップキャッシュのインスタンスを返却
	private ArrayList<Bitmap[]> getOneDirectBitmapCash(){
		ArrayList<Bitmap[]> bitmapCash = new ArrayList<Bitmap[]>();
		bitmapCash.add(new Bitmap[]{null, null, null});
		bitmapCash.add(new Bitmap[]{null, null, null});
		bitmapCash.add(new Bitmap[]{null, null, null});
		return bitmapCash;
	}

	// OpenGLの方向別ビットマップキャッシュの初期セットアップ
	protected void createDirectBitmapCash(){
		// キャッシュ用の領域を確保
		if (cashBitmap_direct0 == null){
			cashBitmap_direct0 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct15 == null){
			cashBitmap_direct15 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct30 == null){
			cashBitmap_direct30 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct45 == null){
			cashBitmap_direct45 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct60 == null){
			cashBitmap_direct60 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct75 == null){
			cashBitmap_direct75 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct90 == null){
			cashBitmap_direct90 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct105 == null){
			cashBitmap_direct105 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct120 == null){
			cashBitmap_direct120 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct135 == null){
			cashBitmap_direct135 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct150 == null){
			cashBitmap_direct150 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct165 == null){
			cashBitmap_direct165 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct180 == null){
			cashBitmap_direct180 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct195 == null){
			cashBitmap_direct195 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct210 == null){
			cashBitmap_direct210 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct225 == null){
			cashBitmap_direct225 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct240 == null){
			cashBitmap_direct240 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct255 == null){
			cashBitmap_direct255 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct270 == null){
			cashBitmap_direct270 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct285 == null){
			cashBitmap_direct285 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct300 == null){
			cashBitmap_direct300 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct315 == null){
			cashBitmap_direct315 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct330 == null){
			cashBitmap_direct330 = getOneDirectBitmapCash();
		}
		if (cashBitmap_direct345 == null){
			cashBitmap_direct345 = getOneDirectBitmapCash();
		}
	}

//	// OpenGLの方向別ビットマップキャッシュの初期化
//	protected void clearDirectBitmapCash(){
//		if (cashBitmap_direct0 != null){
//			cashBitmap_direct0.clear();
//		}
//		if (cashBitmap_direct15 != null){
//			cashBitmap_direct15.clear();
//		}
//		if (cashBitmap_direct30 != null){
//			cashBitmap_direct30.clear();
//		}
//		if (cashBitmap_direct45 != null){
//			cashBitmap_direct45.clear();
//		}
//		if (cashBitmap_direct60 != null){
//			cashBitmap_direct60.clear();
//		}
//		if (cashBitmap_direct75 != null){
//			cashBitmap_direct75.clear();
//		}
//		if (cashBitmap_direct90 != null){
//			cashBitmap_direct90.clear();
//		}
//		if (cashBitmap_direct105 != null){
//			cashBitmap_direct105.clear();
//		}
//		if (cashBitmap_direct120 != null){
//			cashBitmap_direct120.clear();
//		}
//		if (cashBitmap_direct135 != null){
//			cashBitmap_direct135.clear();
//		}
//		if (cashBitmap_direct150 != null){
//			cashBitmap_direct150.clear();
//		}
//		if (cashBitmap_direct165 != null){
//			cashBitmap_direct165.clear();
//		}
//		if (cashBitmap_direct180 != null){
//			cashBitmap_direct180.clear();
//		}
//		if (cashBitmap_direct195 != null){
//			cashBitmap_direct195.clear();
//		}
//		if (cashBitmap_direct210 != null){
//			cashBitmap_direct210.clear();
//		}
//		if (cashBitmap_direct225 != null){
//			cashBitmap_direct225.clear();
//		}
//		if (cashBitmap_direct240 != null){
//			cashBitmap_direct240.clear();
//		}
//		if (cashBitmap_direct255 != null){
//			cashBitmap_direct255.clear();
//		}
//		if (cashBitmap_direct270 != null){
//			cashBitmap_direct270.clear();
//		}
//		if (cashBitmap_direct285 != null){
//			cashBitmap_direct285.clear();
//		}
//		if (cashBitmap_direct300 != null){
//			cashBitmap_direct300.clear();
//		}
//		if (cashBitmap_direct315 != null){
//			cashBitmap_direct315.clear();
//		}
//		if (cashBitmap_direct330 != null){
//			cashBitmap_direct330.clear();
//		}
//		if (cashBitmap_direct345 != null){
//			cashBitmap_direct345.clear();
//		}
//	}

	// 方向別ビットマップキャッシュの取得
	protected Bitmap getDirectBitmapDB(DirectForCash directForCash,
										CashListNo listNo,
										CashItemNo itemNo){
		int direct = 0;
		int no = 0;
		int subno = 0;
		boolean _horizontalFlg = false;

		if (_lastDirection != directForCash) {
			// 引数で指定された方向用のリストを取得する
			direct = convDirectForCashToDirect(directForCash);
			if (_flipHorizontal && direct > 180){
				direct = 360 - direct;
				_horizontalFlg = true;
			}
			// DBに登録
			_charaWalkBitmapList = _operateDataBase.get_CHARA_WALK_BITMAP("DUMMY", 0, direct);
		}
		// 引数で指定された番号の要素をリストから取得する
		no = convCashListNoToPendulumCount3(listNo);
		// 引数で指定された番号の要素をビットマップ配列から取得する
		// ※まばたき用の3階層目
		subno = convCashItemNoToIndex(itemNo);
		
		for (DataRowCharaWalkBitmap charaWalkBitmap : _charaWalkBitmapList) {
			if (charaWalkBitmap.get_no() == no && charaWalkBitmap.get_subNo() == subno) {
				if (_horizontalFlg){
					return changeX180(charaWalkBitmap.get_bitmap());
				}else{
					return charaWalkBitmap.get_bitmap();
				}
			}
		}
		return null;
	}

	// 方向別ビットマップキャッシュの設定
	protected boolean setDirectBitmapDB(DirectForCash directForCash,
										   CashListNo listNo,
										   CashItemNo itemNo,
										   Bitmap bitmap){
		if (bitmap == null){
			return false;
		}

		int direct = 0;
		int no = 0;
		int subno = 0;

		// 引数で指定された方向用のリストを取得する
		direct = convDirectForCashToDirect(directForCash);
		// 引数で指定された番号の要素をリストから取得する
		no = convCashListNoToPendulumCount3(listNo);
		// 引数で指定された番号の要素をビットマップ配列から取得する
		// ※まばたき用の3階層目
		subno = convCashItemNoToIndex(itemNo);
		// DBに登録
		_operateDataBase.set_CHARA_WALK_BITMAP("DUMMY", 0, direct, no, subno,bitmap);
		return true;
	}

	// 角度に近い方向定数を返す
	protected DirectForCash convDirectToDirectForCash(int direct){
		if (direct == 0){
			return DirectForCash.direct0;
		}else if(direct == 15){
			return DirectForCash.direct15;
		}else if(direct == 30){
			return DirectForCash.direct30;
		}else if(direct == 45){
			return DirectForCash.direct45;
		}else if(direct == 60){
			return DirectForCash.direct60;
		}else if(direct == 75){
			return DirectForCash.direct75;
		}else if(direct == 90){
			return DirectForCash.direct90;
		}else if(direct == 105){
			return DirectForCash.direct105;
		}else if(direct == 120){
			return DirectForCash.direct120;
		}else if(direct == 135){
			return DirectForCash.direct135;
		}else if(direct == 150){
			return DirectForCash.direct150;
		}else if(direct == 165){
			return DirectForCash.direct165;
		}else if(direct == 180){
			return DirectForCash.direct180;
		}else if(direct == 195){
			return DirectForCash.direct195;
		}else if(direct == 210){
			return DirectForCash.direct210;
		}else if(direct == 225){
			return DirectForCash.direct225;
		}else if(direct == 240){
			return DirectForCash.direct240;
		}else if(direct == 255){
			return DirectForCash.direct255;
		}else if(direct == 270){
			return DirectForCash.direct270;
		}else if(direct == 285){
			return DirectForCash.direct285;
		}else if(direct == 300){
			return DirectForCash.direct300;
		}else if(direct == 315){
			return DirectForCash.direct315;
		}else if(direct == 330){
			return DirectForCash.direct330;
		}else if(direct == 345){
			return DirectForCash.direct345;
		}else{
			return DirectForCash.direct0;
		}
	}

	// 方向定数に対応する角度を返す
	protected int convDirectForCashToDirect(DirectForCash directForCash) {
		switch (directForCash) {
			case direct0:
				return 0;
			case direct15:
				return 15;
			case direct30:
				return 30;
			case direct45:
				return 45;
			case direct60:
				return 60;
			case direct75:
				return 75;
			case direct90:
				return 90;
			case direct105:
				return 105;
			case direct120:
				return 120;
			case direct135:
				return 135;
			case direct150:
				return 150;
			case direct165:
				return 165;
			case direct180:
				return 180;
			case direct195:
				return 195;
			case direct210:
				return 210;
			case direct225:
				return 225;
			case direct240:
				return 240;
			case direct255:
				return 255;
			case direct270:
				return 270;
			case direct285:
				return 285;
			case direct300:
				return 300;
			case direct315:
				return 315;
			case direct330:
				return 330;
			case direct345:
				return 345;
			default:
				return 0;
		}
	}

	// 方向定数に対応するBitmapキャッシュを返す
	private ArrayList<Bitmap[]> getCashBitmap_direct(DirectForCash directForCash){
		switch (directForCash) {
			case direct0:
				return cashBitmap_direct0;
			case direct15:
				return cashBitmap_direct15;
			case direct30:
				return cashBitmap_direct30;
			case direct45:
				return cashBitmap_direct45;
			case direct60:
				return cashBitmap_direct60;
			case direct75:
				return cashBitmap_direct75;
			case direct90:
				return cashBitmap_direct90;
			case direct105:
				return cashBitmap_direct105;
			case direct120:
				return cashBitmap_direct120;
			case direct135:
				return cashBitmap_direct135;
			case direct150:
				return cashBitmap_direct150;
			case direct165:
				return cashBitmap_direct165;
			case direct180:
				return cashBitmap_direct180;
			case direct195:
				if (_flipHorizontal) {
					return cashBitmap_direct165;
				} else {
					return cashBitmap_direct195;
				}
			case direct210:
				if (_flipHorizontal) {
					return cashBitmap_direct150;
				} else {
					return cashBitmap_direct210;
				}
			case direct225:
				if (_flipHorizontal) {
					return cashBitmap_direct135;
				} else {
					return cashBitmap_direct225;
				}
			case direct240:
				if (_flipHorizontal) {
					return cashBitmap_direct120;
				} else {
					return cashBitmap_direct240;
				}
			case direct255:
				if (_flipHorizontal) {
					return cashBitmap_direct105;
				} else {
					return cashBitmap_direct255;
				}
			case direct270:
				if (_flipHorizontal) {
					return cashBitmap_direct90;
				} else {
					return cashBitmap_direct270;
				}
			case direct285:
				if (_flipHorizontal) {
					return cashBitmap_direct75;
				} else {
					return cashBitmap_direct285;
				}
			case direct300:
				if (_flipHorizontal) {
					return cashBitmap_direct60;
				} else {
					return cashBitmap_direct300;
				}
			case direct315:
				if (_flipHorizontal) {
					return cashBitmap_direct45;
				} else {
					return cashBitmap_direct315;
				}
			case direct330:
				if (_flipHorizontal) {
					return cashBitmap_direct30;
				} else {
					return cashBitmap_direct330;
				}
			case direct345:
				if (_flipHorizontal) {
					return cashBitmap_direct15;
				} else {
					return cashBitmap_direct345;
				}
			default:
				return cashBitmap_direct0;
		}
	}

	// リストの要素番号指定用定数に相当する振り子カウント３の値を返す
	protected int convCashListNoToPendulumCount3(CashListNo cashListNo){
		if (cashListNo == CashListNo.no0){
			return 0;
		}else if(cashListNo == CashListNo.no1){
			return 1;
		}else{
			return 2;
		}
	}

	// 振り子カウント３の値に相当するリストの要素番号指定用定数を返す
	protected CashListNo convPendulumCount3ToCashListNo(int pendulumCount3){
		if (pendulumCount3 == 0){
			return CashListNo.no0;
		}else if(pendulumCount3 == 1){
			return CashListNo.no1;
		}else{
			return CashListNo.no2;
		}
	}

	// リストの要素番号指定用定数に相当する要素番号の値を返す
	protected int convCashItemNoToIndex(CashItemNo cashItemNo){
		if (cashItemNo == CashItemNo.no0){
			return 0;
		}else if(cashItemNo == CashItemNo.no1){
			return 1;
		}else{
			return 2;
		}
	}

	// 要素番号に相当するリストの要素番号指定用定数を返す
	protected CashItemNo convIndexToCashItemNo(int index){
		if (index == 0){
			return CashItemNo.no0;
		}else if(index == 1){
			return CashItemNo.no1;
		}else{
			return CashItemNo.no2;
		}
	}

	// ビットマップキャッシュ操作用パラメータ（方向）
	protected enum DirectForCash{
		direct0,   direct15,  direct30,  direct45,  direct60,
		direct75,  direct90,  direct105, direct120, direct135,
		direct150, direct165, direct180, direct195, direct210,
		direct225, direct240, direct255, direct270, direct285,
		direct300, direct315, direct330, direct345,
	}

	// ビットマップキャッシュ操作用パラメータ（リストの要素番号）
	// キャッシュ用リストは各要素としてビットマップ配列が格納された2次元配列構造
	// 本パラメータは2次元配列の1次元目の要素番号となる
	protected enum CashListNo{
		no0,
		no1,
		no2,
	}

	// ビットマップキャッシュ操作用パラメータ（リストの要素別項目番号）
	// キャッシュ用リストは各要素としてビットマップ配列が格納された2次元配列構造
	// 本パラメータは2次元配列の2次元目の要素番号となる
	protected enum CashItemNo{
		no0,
		no1,
		no2,
	}

	// ポーズ用ビットマップの設定
	protected void setBitmapPoseDB(Bitmap[] bitmap){
		for (int i = 0; i < bitmap.length; i++){
			// DBに登録
			_operateDataBase.set_CHARA_POSE_BITMAP("DUMMY", _poseCount, 0, i, 0, bitmap[i]);
		}
		_poseCount += 1;
	}

	// ウォーキング用ビットマップの取得
	@Override
	public Bitmap getRendererBitmapDB(int direct) {
		Bitmap cashBitmap = null;
//		int direct = (int) MyTimerMoveWindowManager.getRotateX();
		// キャッシュからビットマップを取得
		cashBitmap = getDirectBitmapDB(
				convDirectToDirectForCash(direct),
				convPendulumCount3ToCashListNo(getPendulumCount3()),
				CashItemNo.no0);
		// 振り子カウントを進める
		countPendulumCount3();
		return cashBitmap;
	}

	// ポーズ用ビットマップの取得
	@Override
	public  Bitmap getBitmapPoseDB(){
		Bitmap workBitmap = null;

		// ポーズ用ビットマップキャッシュが空の場合は中断
		if (_poseCount == 0){
			return null;
		}
		// 表示中のポーズ用ビットマップがない場合はキャッシュからランダムで取得
		if (_bitmapPose == null){
			 ArrayList<DataRowCharaPoseBitmap> charaPoseBitmapList =
					 _operateDataBase.get_CHARA_POSE_BITMAP("DUMMY", 0, _random.nextInt(_poseCount));
			if (charaPoseBitmapList.size() > 0){
				_bitmapPose = new Bitmap[charaPoseBitmapList.size()];
				for (int i = 0; i < charaPoseBitmapList.size(); i++){
					_bitmapPose[i] = charaPoseBitmapList.get(i).get_bitmap();
				}
			}
			_cashBitmapPoseNo = 0;
		}
		// 表示中のポーズ用ビットマップの要素数より、今回表示予定の要素番号を比較
		if (_cashBitmapPoseNo >=  _bitmapPose.length){
			_bitmapPose = null;
			_cashBitmapPoseNo = 0;
		}else{
			workBitmap = _bitmapPose[_cashBitmapPoseNo];
			_cashBitmapPoseNo += 1;
		}
		return workBitmap;
	}
}
