package com.ns.aco.sp.common.listener;

import android.view.View;

import com.ns.aco.sp.common.delegate.DelegateIF;

/**
 * Created by ns on 2017/05/02.
 */
public interface OnDropListenerIF {

    public interface OnDropListener{
        public void drop(View view);
        public void dragEnded();
    };

}
