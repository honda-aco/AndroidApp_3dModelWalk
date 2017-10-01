package com.ns.aco.sp.renderer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import com.ns.aco.sp.R;
import com.ns.aco.sp.common.delegate.DelegateIF;
import com.ns.aco.sp.service.ServiceContract;

import android.graphics.Bitmap;

public class RendererInfoOriginal extends RendererInfoBase {

	private  Map<String, List<VboInfo>> _vboInfoMap = new HashMap<String, List<VboInfo>>();
	private String[] _directories = new String[]{
			_service.get_service().getString(R.string.value0016),
			_service.get_service().getString(R.string.value0017),
			_service.get_service().getString(R.string.value0018)};
	private List<VboInfo> _vboInfosLeft = new ArrayList<VboInfo>();
	private List<VboInfo> _vboInfosCenter = new ArrayList<VboInfo>();
	private List<VboInfo> _vboInfosRight = new ArrayList<VboInfo>();

	// コンストラクタ
	public RendererInfoOriginal(GL11 gl11, ServiceContract.Service service) {
		super(gl11, service, false);
		_getBitmapWalk.add(getBitmapWalkR());
		_getBitmapWalk.add(getBitmapMotionLess());
		_getBitmapWalk.add(getBitmapWalkL());

		// 頂点系ファイルの取得
		_vboInfoMap.put(_directories[0], _vboInfosLeft);
		_vboInfoMap.put(_directories[1], _vboInfosCenter);
		_vboInfoMap.put(_directories[2], _vboInfosRight);

		// left, center, rightの順にループする
		for (String directory : _directories){
			// 読込みファイルの格納先を取得
			List<VboInfo> vboInfo = _vboInfoMap.get(directory);
			// 読込みディレクトリを取得
			String convertPath = _service.get_service().getString(R.string.convert_directory)
					.replaceFirst("\\?", "0")
						.replaceFirst("\\?", directory);
			// ディレクトリが存在しない場合は次へ進む
			File mapFile = new File(convertPath);
			if (mapFile.exists() == false){
				continue;
			}
			// 読込みファイルを取得
			String vertexPathFormat = convertPath + "/" + _service.get_service().getString(R.string.convert_vertex_file);
			String normalPathFormat = convertPath + "/" + _service.get_service().getString(R.string.convert_normal_file);
			String texturePathFormat = convertPath + "/" + _service.get_service().getString(R.string.convert_texture_file);
			String pngPathFormat = convertPath + "/" + _service.get_service().getString(R.string.convert_png_file);

			List<Integer> mapList = new ArrayList<Integer>();
			for (String map : mapFile.list()){
				if (map.substring(0, 1).equals("_")){
					int delimiter = map.lastIndexOf("^");
					String fileNo = map.substring(delimiter + 1);
					mapList.add(Integer.valueOf(fileNo));
				}
			}

			for (Integer fileNo : mapList){
				// 読込みファイルの存在確認
				File file = new File(vertexPathFormat.replaceFirst("\\?", String.valueOf(fileNo)));
				if (file.exists()){
					String vertexPath = vertexPathFormat.replaceFirst("\\?", String.valueOf(fileNo));
					String normalPath = normalPathFormat.replaceFirst("\\?", String.valueOf(fileNo));
					String texturePath = texturePathFormat.replaceFirst("\\?", String.valueOf(fileNo));
					String pngPath = pngPathFormat.replaceFirst("\\?", String.valueOf(fileNo));
					// 読込みファイルを格納
					vboInfo.add(new VboInfo(
							vertexPath, normalPath, texturePath, pngPath
					));
				}
			}
		}
	}

	// ビットマップ取得関数格納用
	ArrayList<DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>>
		_getBitmapWalk = new ArrayList<DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>>();

	// 描画するVBO数取得
	@Override
	public int getDrawBitmapCount(){
		int setCount = _vboInfosLeft.size() + _vboInfosCenter.size() + _vboInfosRight.size();
		return 3 * forwardDirection.length + setCount;
	}

	@Override
	public boolean isPose() {
		return false;
	}

	// テクスチャファイルをOpenGLに登録する
	protected void loadRendererModel(){
		// 起動されたタイプによってテクスチャを変更する
		for (VboInfo vboInfo : _vboInfosLeft){
			vboInfo.setGlTextureId(
					_draw3DObject.loadTexture(vboInfo.getPngFilePath(), _service.get_windowWidth(), _service.get_windowHeight()));
		}
		for (VboInfo vboInfo : _vboInfosCenter){
			vboInfo.setGlTextureId(
					_draw3DObject.loadTexture(vboInfo.getPngFilePath(), _service.get_windowWidth(), _service.get_windowHeight()));
		}
		for (VboInfo vboInfo : _vboInfosRight){
			vboInfo.setGlTextureId(
					_draw3DObject.loadTexture(vboInfo.getPngFilePath(), _service.get_windowWidth(), _service.get_windowHeight()));
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
		deleteVboInfos(_vboInfosLeft);
		deleteVboInfos(_vboInfosCenter);
		deleteVboInfos(_vboInfosRight);
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
		for (String directory : _directories){
			// 読込みファイルの格納先を取得
			List<VboInfo> vboInfo = _vboInfoMap.get(directory);
			for (int i = 0; i < vboInfo.size(); i++){
				vboInfo.set(i, setVboInfos(vboInfo.get(i), false));
				// VBOへの登録処理の結果を判定
				if (vboInfo.get(i) == null){
					return false;
				}else{
					// プログレスバーの現在値を更新
					_service.increment_progressDialog(1);
				}
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
				if (setDirectBitmapDB(convDirectToDirectForCash(forwardDirection[i]), cashListNo[j], CashItemNo.no0, bitmap) == false){
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
		_gl11.glEnable(GL11.GL_LIGHT_MODEL_AMBIENT);
	}

	private DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap> getBitmapWalkR(){
		return new DelegateIF.Delegate3<Boolean, Integer, Integer, Bitmap>(){
			public Bitmap invoke(Boolean initPixels, Integer pixelWidth, Integer pixelHeight){
				// 描画情報を初期化
				_gl11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
				_gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

				// 歩く（右足上げ）ポーズ
				boolean resultDraw = true;
				for (VboInfo vboInfo : _vboInfosRight){
					resultDraw = _draw3DObject.draw3D(vboInfo.getGlTextureId(), vboInfo);
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
				for (VboInfo vboInfo : _vboInfosCenter){
					resultDraw = _draw3DObject.draw3D(vboInfo.getGlTextureId(), vboInfo);
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
				for (VboInfo vboInfo : _vboInfosLeft){
					resultDraw = _draw3DObject.draw3D(vboInfo.getGlTextureId(), vboInfo);
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
