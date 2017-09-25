package com.ns.aco.sp.renderer;

import android.graphics.Bitmap;

public interface RendererInfo {
		void setLight();
	    void setRendererBitmapCash(boolean init, int pixelWidth, int pixelHeight);
//		Bitmap getRendererBitmapCash();
		Bitmap getRendererBitmapDB(int direct);
//		Bitmap getBitmapPoseCash();
		Bitmap getBitmapPoseDB();
		int getDrawBitmapCount();
		int getPendulumCount3();
		boolean isFinishCashSet();
		boolean isPose();
}

