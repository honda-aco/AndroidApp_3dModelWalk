package com.ns.aco.sp.view.title;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

public interface TitleContract{

    interface View{
        /**
         * アクティビティの遷移
         */
        void startActivity();

        /**
         * 背景画像の変更
         * @param bitmap
         */
        void setBackImage(Bitmap bitmap);

        /**
         * Viewの背景色＆画像切替用Timer起動
         * @param bitmapList
         */
        void startTimerTask(Bitmap[] bitmapList);

        /**
         * プライバシーポリシーへ遷移する
         */
        void movePolicyPage();
    }

    interface Handler{
        /**
         * Viewの背景色変更
         * @param targetView
         * @param setColor
         */
        void setBackColor(final ImageView targetView, final int setColor);

        /**
         * ImageViewの画像変更
         * @param targetView
         * @param bitmap
         */
        void setBackImage(final ImageView targetView, final Bitmap bitmap);
    }

    interface Presenter extends com.ns.aco.sp.Presenter {
        /**
         * アクティビティの遷移
         */
        void startActivity();

        /**
         * 画面の初期処理終了後のView設定
         */
        void setViewAfterFocuced(Context context, ImageView imageView);

        /**
         * プライバシーポリシー移動
         */
        void movePolicyPage();
    }
}
