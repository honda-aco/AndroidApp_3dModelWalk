package com.ns.aco.sp.common.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public  class UtilityImageView {
	public static Bitmap getBitmapSize(Context context, int iDrawableID, int viewWeight, int viewHeight){
		Resources res = context.getResources();

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, iDrawableID, options);

		int imageWidth = options.outWidth;
		int imageHeight = options.outHeight;
		double inSampleSize = 1;

		if (imageHeight > viewHeight && imageWidth > viewWeight) {
	        if (viewWeight > viewHeight) {
	        	inSampleSize = Math.floor((float)imageWidth / (float)viewWeight);
	        } else {
	        	inSampleSize = Math.floor((float)imageHeight / (float)viewHeight);
	        }
	    }
	    // inSampleSize をセットしてデコード
	    options.inSampleSize = (int) inSampleSize;
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeResource(res, iDrawableID, options);
	}

	public static Bitmap getBitmapSize(Context context, String filePath, int viewWeight, int viewHeight){
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);

		int imageWidth = options.outWidth;
		int imageHeight = options.outHeight;
		double inSampleSize = 1;

		if (imageHeight > viewHeight && imageWidth > viewWeight) {
	        if (viewWeight > viewHeight) {
	        	inSampleSize = Math.floor((float)imageWidth / (float)viewWeight);
	        } else {
	        	inSampleSize = Math.floor((float)imageHeight / (float)viewHeight);
	        }
	    }
	    // inSampleSize をセットしてデコード
	    options.inSampleSize = (int) inSampleSize;
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeFile(filePath, options);
	}

	public static Bitmap getViewBitmap(View view){
	    view.setDrawingCacheEnabled(true);
	    view.destroyDrawingCache();
	    Bitmap drawingCache = view.getDrawingCache();

	    if(drawingCache == null){
	        return null;
	    }
	    Bitmap bitmap = Bitmap.createBitmap(drawingCache);
	    view.setDrawingCacheEnabled(false);
	    return bitmap;
	}
}
