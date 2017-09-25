package com.ns.aco.sp.view.title;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.ns.aco.sp.R;
import com.ns.aco.sp.common.util.UtilityImageView;

public class TitlePresenter implements TitleContract.Presenter {

    private TitleContract.View _view;

    public TitlePresenter(TitleContract.View view) {
        _view = view;
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    }

    @Override
    public void onWindowFocusChanged() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void setViewAfterFocuced(Context context, ImageView imageView){

        int bitmapSize = 0;
        if (imageView.getWidth() > imageView.getHeight()){
            bitmapSize = imageView.getWidth();
        }else{
            bitmapSize = imageView.getHeight();
        }

        // タイトル画面のImageViewに設定する画像を取得する
        int drawables[] = new int[]{
                R.drawable.icon_op01,
                R.drawable.icon_op02};
        Bitmap bitmapList[] = new Bitmap[drawables.length];

        for (int i = 0; i < drawables.length; i++) {
            bitmapList[i] = UtilityImageView.getBitmapSize(
                    context,
                    drawables[i],
                    bitmapSize,
                    bitmapSize);
            bitmapList[i] = Bitmap.createScaledBitmap(bitmapList[i], bitmapSize, bitmapSize, false);
        };

        _view.setBackImage(bitmapList[0]);
        _view.startTimerTask(bitmapList);
    }

    @Override
    public void startActivity(){
        _view.startActivity();
    }
}
