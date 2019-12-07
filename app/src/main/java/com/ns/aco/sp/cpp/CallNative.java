package com.ns.aco.sp.cpp;

import android.content.res.AssetManager;

public class CallNative {

	// Native呼出し
	static {
		System.loadLibrary ("NativeOpenGL");
	}

	public class OperateOpenGL {

		public OperateOpenGL(AssetManager assetManager) {
			initAsset(assetManager);
		}
		// Assset操作用オブジェクト引き渡し用関数
		public native void initAsset(AssetManager assetManager);
		public native int setVboFromAsset(String fileVertexPath, String fileNormalPath, String fileCoodPath, String split, int vboAdress);
		public native int setVbo(String fileVertexPath, String fileNormalPath, String fileCoodPath, String split, int vboAdress);
		public native boolean draw3D(int textureID, int faceCount, int vboVertex, int vboNormal, int vboCood);
	}

	public class ConvertObjFile {
		public native boolean convertObjFile(String objFilePath, String outputVertexPath, String outputNormalPath, String outputTexturePath);
	}

	public class Bitmap {
		public native void specificPixelToClear(int[] pixels, int arraySize, int rgba);
	}
}
