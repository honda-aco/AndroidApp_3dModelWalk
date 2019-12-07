package com.ns.aco.sp.view.help;

import android.widget.ImageView;

public interface HelpContract {

    interface Presenter extends com.ns.aco.sp.Presenter {

        /**
         * フォーカス取得後のView設定
         * @param imageViewList
         * @param imageViewWidth
         */
        void setViewAfterFocuced(ImageView[] imageViewList, int imageViewWidth);
    }
}
