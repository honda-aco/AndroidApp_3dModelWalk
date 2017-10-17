package com.ns.aco.sp;

import android.content.res.Configuration;

public interface Presenter {

    void onResume();
    void onPause();
    void onConfigurationChanged(Configuration newConfig);
    void onWindowFocusChanged();
    void onDestroy();
}
