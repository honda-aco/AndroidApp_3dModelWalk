package com.ns.aco.sp.view.help;

import android.app.Activity;
import android.content.res.Configuration;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PresenterHelp implements HelpContract.Presenter {

    public PresenterHelp(Activity activity){
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
    public void setViewAfterFocuced(ImageView[] imageViewList, int imageViewWidth){
        for (ImageView imageView : imageViewList){
            imageView.setLayoutParams(new LinearLayout.LayoutParams(imageViewWidth, imageViewWidth));
        }
    }
}
