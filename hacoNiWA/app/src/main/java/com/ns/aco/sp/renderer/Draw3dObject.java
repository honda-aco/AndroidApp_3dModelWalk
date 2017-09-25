package com.ns.aco.sp.renderer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.opengl.GLUtils;

//TODO 後で削除
import android.util.Log;
import com.ns.aco.sp.cpp.CallNative;
import com.ns.aco.sp.common.util.UtilityImageView;
import com.ns.aco.sp.service.ServiceContract;

public class Draw3dObject {
	ServiceContract.Service _service;
	private final GL11 _gl11;
	//モデルに対応するBufferを格納するハッシュテーブル
	//Vboとしてメモリに確保するモデルのリスト
	private Map<String, Integer> _mapVboData = new HashMap<String, Integer>();
	//Vboとしてメモリに確保するモデルのID（Dictionaryのkey）
	private String IDForModel3D = "model3d";
	//Vbo格納中の最大バッファ番号
	private int _vboBuffer = 1;
	private float[] _vertex;
	private float[] _normalVertex;
	private float[] _coodVertex;
	private FloatBuffer _vertexBuffer;
	private FloatBuffer _normalBuffer;
	private FloatBuffer _coodBuffer;
	private CallNative.OperateOpenGL _operateOpenGL = null;

	public Draw3dObject(ServiceContract.Service service, GL11 gl11){
		_gl11 = gl11;
		_service = service;
		_operateOpenGL = new CallNative().new OperateOpenGL(_service.get_service().getAssets());
	}

	private void setEmissionFrontAndBack(float r, float g, float b, float a)
	{
		float[] emission = { r, g, b, a };
		_gl11.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_EMISSION, emission, 0);
	}

	public int loadTexture(Drawable drawable) {
		int[] textures = new int[1];

		//Bitmapの作成
		Bitmap bmp = ((BitmapDrawable)drawable).getBitmap();
		if (bmp == null) {
			return 0;
		}

		// OpenGL用のテクスチャを生成します
		_gl11.glGenTextures(1, textures, 0);
		_gl11.glBindTexture(GL11.GL_TEXTURE_2D, textures[0]);
		GLUtils.texImage2D(GL11.GL_TEXTURE_2D, 0, bmp, 0);
		_gl11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		_gl11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		_gl11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

		//OpenGLへの転送が完了したので、VMメモリ上に作成したBitmapを破棄する
		bmp.recycle();
		return textures[0];
	}

	// Asset配下の画像ファイルのロード
	public int loadTexture(int iDrawableID, int width, int height) {
		//Bitmapの作成
		Bitmap bitmap = UtilityImageView.getBitmapSize(_service.get_service(), iDrawableID, width, height);
		if (bitmap == null) {
			return 0;
		}else{
			return loadTexture(bitmap);
		}
	}

	// Asset外の画像ファイルのロード
	public int loadTexture(String pngFilePath, int width, int height) {
		//Bitmapの作成
		Bitmap bitmap = UtilityImageView.getBitmapSize(_service.get_service(), pngFilePath, width, height);
//							(int)_globalWindowManager.getWindowWidth() / (_globalWindowManager.getGlViewSizePart() * 1),
//							(int)_globalWindowManager.getWindowHeight() / (_globalWindowManager.getGlViewSizePart() * 1));
		if (bitmap == null) {
			return 0;
		}else{
			return loadTexture(bitmap);
		}
	}

	// 縮尺変更しての画像読込み
	// 標準でもViewサイズまでリサイズされるが、Viewサイズに対してさらにＸＸ分の一を設定したい場合はこちらの関数を使う
	// ex)reducedScale = 2の場合、Viewサイズの半分のサイズで画像を読込む
	public int loadTexture(Bitmap bitmap) {

		// ビットマップのピクセル情報を取得
		int pixels[] = new int[bitmap.getWidth() * bitmap.getHeight()];
		IntBuffer pixelBuffer = IntBuffer.wrap(pixels);
		pixelBuffer.position(0);
		bitmap.copyPixelsToBuffer(pixelBuffer);

		// フィルター反映後のビットマップを準備
		Bitmap bitmapCanvas = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmapCanvas);

		// 上下が逆さまなので垂直方向に反転
		Matrix matrix = new Matrix();
		matrix.postScale(1.0f, -1.0f);
		matrix.postTranslate(0, bitmap.getHeight());
		canvas.concat(matrix);

		// カラーフィルターを指定
		Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
		paint.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[] {
			0, 0, 1, 0, 1,
			0, 1, 0, 0, 1,
			1, 0, 0, 0, 1,
			0, 0, 0, 1, 0,
		})));

		// 描画
		canvas.drawBitmap(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight(), true, paint);

		// OpenGL用のテクスチャを登録
		int[] textures = new int[1];
		_gl11.glGenTextures(1, textures, 0);
		_gl11.glBindTexture(GL11.GL_TEXTURE_2D, textures[0]);
		GLUtils.texImage2D(GL11.GL_TEXTURE_2D, 0, bitmapCanvas, 0);
		_gl11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		_gl11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

		//OpenGLへの転送が完了したので、VMメモリ上に作成したBitmapを破棄する
		bitmapCanvas.recycle();
		return textures[0];
	}

	public void deleteTexture(int[] iDrawableIDs) {
		_gl11.glDeleteTextures(1, iDrawableIDs, 0);
	}

	// 移動や角度を付与後の3Dモデル描画
	protected boolean draw3D(GlTranslatef glTranslatef,
							 GlRotatef glRotatef,
							 int textureID,
							 VboInfo vboInfo){

		boolean resultDraw = false;
		_gl11.glTranslatef(glTranslatef.x, glTranslatef.y, glTranslatef.z);
		_gl11.glRotatef(glRotatef.angle, glRotatef.x, glRotatef.y, glRotatef.z);
		resultDraw = draw3D(textureID, vboInfo);
		_gl11.glRotatef(glRotatef.angle, -glRotatef.x, -glRotatef.y, -glRotatef.z);
		_gl11.glTranslatef(-glTranslatef.x, -glTranslatef.y, -glTranslatef.z);
		return resultDraw;
	}

	// 3Dモデル描画
	public boolean draw3D(int textureID, VboInfo vboInfo){
		//VBO設定時に中断フラグを確認する
		if (_service.is_serviceStop()){
			return false;
		}

		//ログ出力
		Log.d(new Throwable().getStackTrace()[0].getMethodName(),
			  "描画開始:" +
			  vboInfo.getVertexFilePath() + ", " +
			  vboInfo.getCoodFilePath() + ", " +
			  vboInfo.getNormalFilePath() + ", ");

		boolean result = _operateOpenGL.draw3D(textureID,
										       vboInfo.getFaceCount(),
										       vboInfo.getBuffNoVertex(),
										       vboInfo.getBuffNoNormal(),
										       vboInfo.getBuffNoCood());

		//ログ出力
		Log.d(new Throwable().getStackTrace()[0].getMethodName(),
			  "描画終了:" +
			  vboInfo.getVertexFilePath() + ", " +
			  vboInfo.getCoodFilePath() + ", " +
			  vboInfo.getNormalFilePath() + ", ");

		return result;
	}

	public int draw3DWithoutVBO(int textureID, String[] FileNames) {
		try{
			BufferedReader bufVertex = null;
			BufferedReader bufNormal = null;
			BufferedReader bufTexture = null;

			// 頂点情報を格納したtextファイルを読込む
			AssetManager fileVertex = _service.get_service().getResources().getAssets();
			InputStream instVertex = fileVertex.open(FileNames[0]);
			bufVertex = new BufferedReader(new InputStreamReader(instVertex));

			// 法線情報を格納したtextファイルを読込む
			AssetManager fileNormal = _service.get_service().getResources().getAssets();
			InputStream instNormal = fileNormal.open(FileNames[1]);
			bufNormal = new BufferedReader(new InputStreamReader(instNormal));

			// テクスチャ情報を格納したtextファイルを読込む
			AssetManager fileTexture = _service.get_service().getResources().getAssets();
			InputStream instTexture = fileTexture.open(FileNames[2]);
			bufTexture = new BufferedReader(new InputStreamReader(instTexture));

			//VBO設定時に中断フラグを確認する
			if (_service.is_serviceStop()){
				return -1;
			}

			String[] strReadVertex = bufVertex.readLine().split(" ");
			_vertex = new float[strReadVertex.length];
			for (int i = 0; i < strReadVertex.length - 1; i++) {
				// 頂点データを１面（頂点３つ）ごとに分割して配列へ格納する
				_vertex[i] = Float.parseFloat(strReadVertex[i]);
			}

			//VBO設定時に中断フラグを確認する
			if (_service.is_serviceStop()){
				return -1;
			}

			String[] strReadNormal = bufNormal.readLine().split(" ");
			_normalVertex = new float[strReadNormal.length];
			for (int i = 0; i < strReadNormal.length; i++) {
				// 法線データを１面（頂点３つ）ごとに分割して配列へ格納する
				_normalVertex[i] = Float.parseFloat(strReadNormal[i]);
			}

			//VBO設定時に中断フラグを確認する
			if (_service.is_serviceStop()){
				return -1;
			}

			String[] strReadTexture = bufTexture.readLine().split(" ");
			_coodVertex = new float[strReadTexture.length];
			for (int i = 0; i < strReadTexture.length; i++) {
				// テクスチャデータを１面（頂点３つ）ごとに分割して配列へ格納する
				_coodVertex[i] = Float.parseFloat(strReadTexture[i]);
			}

			//VBO設定時に中断フラグを確認する
			if (_service.is_serviceStop()){
				return -1;
			}

			//面情報（頂点数）をVBOに保存
			int faceCount = strReadVertex.length / 9;

			_vertexBuffer = makeFloatBuffer(_vertex);
			_normalBuffer = makeFloatBuffer(_normalVertex);
			_coodBuffer = makeFloatBuffer(_coodVertex);

			_gl11.glVertexPointer(3, GL11.GL_FLOAT, 0, _vertexBuffer);
			_gl11.glEnableClientState(GL11.GL_VERTEX_ARRAY);

			_gl11.glNormalPointer(GL11.GL_FLOAT, 0, _normalBuffer);
			_gl11.glEnableClientState(GL11.GL_NORMAL_ARRAY);

			_gl11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
			_gl11.glTexCoordPointer(2, GL11.GL_FLOAT, 0, _coodBuffer);
			_gl11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);

			setEmissionFrontAndBack(0.3f, 0.3f, 0.3f, 0.1f);	// 本オブジェクトの発光値を設定
			for (int i = 0; i <= faceCount; i++)
			{
				_gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, i * 3 , 3);
			}
			bufVertex.close();
			bufNormal.close();
			bufTexture.close();

//			//バインド先のバッファを初期値に戻す
//			gl11.glBindBuffer(GL11.GL_ARRAY_BUFFER, 0);
			return faceCount;

		}catch(IOException e){
			return -1;
		}
	}

	// VBO格納用バッファ削除
	public void deleteVbo(VboInfo vboInfo){
		if (vboInfo.getBuffNoVertex() != null){
			_gl11.glDeleteBuffers(1, new int[]{vboInfo.getBuffNoVertex()}, 0);
		}
		if (vboInfo.getBuffNoNormal() != null){
			_gl11.glDeleteBuffers(1, new int[]{vboInfo.getBuffNoNormal()}, 0);
		}
		if (vboInfo.getBuffNoCood() != null){
			_gl11.glDeleteBuffers(1, new int[]{vboInfo.getBuffNoCood()}, 0);
		}
		//バインド先のバッファを初期値に戻す
		_gl11.glBindBuffer(GL11.GL_ARRAY_BUFFER, 0);
	}

	// VBO格納
	public Integer[] setVboFromAsset(VboInfo vboInfo) {
		//VBO設定時に中断フラグを確認する
		if (_service.is_serviceStop()){
			return null;
		}

		//ログ出力
		Log.d(new Throwable().getStackTrace()[0].getMethodName(),
			"VBO登録開始:" +
			vboInfo.getVertexFilePath() + ", " +
			vboInfo.getCoodFilePath() + ", " +
			vboInfo.getNormalFilePath() + ", ");

		//VBO格納先情報をマッピング
		_mapVboData.put(IDForModel3D, _mapVboData.size() + 1);

		// VBO格納先アドレスを取得
		int faceCount = _operateOpenGL.setVboFromAsset(
			vboInfo.getVertexFilePath(),
			vboInfo.getNormalFilePath(),
			vboInfo.getCoodFilePath(),
			" ",
			_vboBuffer);
		_vboBuffer += 3;

		//ログ出力
		Log.d(new Throwable().getStackTrace()[0].getMethodName(),
			  "VBO登録終了：" +
			  vboInfo.getVertexFilePath() + ", " +
			  vboInfo.getCoodFilePath() + ", " +
			  vboInfo.getNormalFilePath() + ", ");

		//ログ出力
		Log.d(new Throwable().getStackTrace()[0].getMethodName(), "登録面数：" + faceCount);

		//描画する面の数と頂点VBO格納バッファ番号を返す
		return new Integer[]{faceCount, _vboBuffer - 3, _vboBuffer - 2, _vboBuffer - 1};
	}

	// VBO格納
	public Integer[] setVbo(VboInfo vboInfo) {
		//VBO設定時に中断フラグを確認する
		if (_service.is_serviceStop()){
			return null;
		}

		//ログ出力
		Log.d(new Throwable().getStackTrace()[0].getMethodName(),
			"VBO登録開始:" +
			vboInfo.getVertexFilePath() + ", " +
			vboInfo.getCoodFilePath() + ", " +
			vboInfo.getNormalFilePath() + ", ");

		//VBO格納先情報をマッピング
		_mapVboData.put(IDForModel3D, _mapVboData.size() + 1);

		// VBO格納先アドレスを取得
		int faceCount = _operateOpenGL.setVbo(
			vboInfo.getVertexFilePath(),
			vboInfo.getNormalFilePath(),
			vboInfo.getCoodFilePath(),
			" ",
			_vboBuffer);
		_vboBuffer += 3;

		//ログ出力
		Log.d(new Throwable().getStackTrace()[0].getMethodName(),
			  "VBO登録終了：" +
			  vboInfo.getVertexFilePath() + ", " +
			  vboInfo.getCoodFilePath() + ", " +
			  vboInfo.getNormalFilePath() + ", ");

		//ログ出力
		Log.d(new Throwable().getStackTrace()[0].getMethodName(), "登録面数：" + faceCount);

		//描画する面の数と頂点VBO格納バッファ番号を返す
		return new Integer[]{faceCount, _vboBuffer - 3, _vboBuffer - 2, _vboBuffer - 1};
	}

	// FloatBufferを確保して返す
	public FloatBuffer makeFloatBuffer(float[] arr) {
		ByteBuffer bb = ByteBuffer.allocateDirect(arr.length*4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer fb = bb.asFloatBuffer();
		fb.put(arr);
		fb.position(0);
		return fb;
	}

	//OpenGL移動値設定クラス
	public class GlTranslatef{
		private float x = 0.0f, y = 0.0f, z = 0.0f;

		public GlTranslatef(float x, float y, float z){
			this.x = x; this.y = y; this.z = z;
		}
	}

	//OpenGL傾き設定クラス
	public class GlRotatef{
		private float angle = 0.0f, x = 0.0f, y = 0.0f, z = 0.0f;

		public GlRotatef(float angle, float x, float y, float z){
			this.angle = angle; this.x = x; this.y = y; this.z = z;
		}
	}
}
