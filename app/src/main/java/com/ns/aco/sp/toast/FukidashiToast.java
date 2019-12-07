package com.ns.aco.sp.toast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ns.aco.sp.R;

public class FukidashiToast extends Toast {

    private TextView _fukidashiMiddleCenter = null;
    private String _message = null;

    public void setMessage(String message){
        _message = message;
    }

    public FukidashiToast(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = inflater.inflate(R.layout.toast_fukidashi, null);

        _fukidashiMiddleCenter = (TextView) root.findViewById(R.id.FukidashiCenter);

        setDuration(Toast.LENGTH_LONG);
        setView(root);
    }

    @Override
    public void show() {
        _fukidashiMiddleCenter.setText(_message);
        super.show();
    }
}
