package com.ns.aco.sp.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by ns on 2016/10/30.
 */

public class UtilityBitmap {

    // ビットマップの四方を丸める
    public static Bitmap getCroppedBitmap(Bitmap bitmap) {
        int width  = bitmap.getWidth();
        int height = bitmap.getHeight();

        final Rect rect   = new Rect(0, 0, width, height);
        final RectF rectf = new RectF(15, 15, width - 15, height - 15);

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        paint.setAntiAlias(true);

        canvas.drawRoundRect(rectf, width / 15, height / 15, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    // ビットマップを正方形にする
    public static  Bitmap trimSquare(Bitmap bitmap){
        int left = 0;
        int top = 0;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int scale = 0;
        if (width < height){
            scale = width;
            top = ((height - width) / 2) - 1;
        }else{
            scale = height;
            left = ((width - height) / 2) - 1;
        }
        Matrix matrix = new Matrix();
//        matrix.postScale(scale, scale);
        return Bitmap.createBitmap(bitmap, left, top, scale, scale, matrix, true);
    }

    // イメージファイルをビットマップにする
    public static Bitmap getBitmapFromFile(String fullFilePath){
        File file = new File(fullFilePath);
        Bitmap bitmapAccount = null;
        if (file.exists()){
            try{
                InputStream inStream = new FileInputStream(file);
                bitmapAccount = BitmapFactory.decodeStream(inStream);
                inStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  bitmapAccount;
    }

    // Uriのビットマップを取得する
    public static Bitmap getBitmapFromUri(Context context, Uri uri){
        Bitmap bitmap = null;

        try{
            InputStream inStream = context.getContentResolver().openInputStream(uri);
            bitmap = BitmapFactory.decodeStream(inStream);
            inStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }
}
