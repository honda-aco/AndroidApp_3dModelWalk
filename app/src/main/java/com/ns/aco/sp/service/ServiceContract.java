package com.ns.aco.sp.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.WindowManager;

import com.ns.aco.sp.renderer.RendererInfo;
import com.ns.aco.sp.view.menu.MenuContract;

public interface ServiceContract {

    interface Service extends ServiceContract.Handler, MenuContract.Handler{

        int get_charaNo();

        boolean is_savingEnergy();

        boolean is_talkAction();

        int get_language();

        int get_transparency();

        int get_windowWidth();

        int get_windowHeight();

        int get_glViewWidth();

        int get_glViewHeight();

        Context get_service();

        boolean is_serviceStop();

        void startServiceTimerTask(RendererInfo rendererInfo);
    }

    interface Handler{
        /**
         * 再描画結果をImageViewへセットし更新
         * @param bitmap
         * @param alpha
         * @param layoutParams
         */
        void updateWindow(final Bitmap bitmap, final float alpha, final WindowManager.LayoutParams layoutParams);

        /**
         * 吹き出し用トースト表示
         * @param message
         */
        void showFukidashiToast(final String message);

        /**
         * WindowManagerからViewを削除
         */
        void removeView_gLSurfaceView();

        /**
         * WindowManagerからViewを削除
         */
        void removeView_imageGlSurface();

        /**
         * WindowManagerからViewを削除
         */
        void removeView_imageFrontDoor();
    }
}
