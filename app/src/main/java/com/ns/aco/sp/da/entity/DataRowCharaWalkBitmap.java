package com.ns.aco.sp.da.entity;

import android.graphics.Bitmap;

public class DataRowCharaWalkBitmap {

    private String _charaId = null;
    private int _size = 0;
    private int _direct = 0;
    private int _no = 0;
    private int _subNo = 0;
    private Bitmap _bitmap = null;

    public String get_charaId() {
        return _charaId;
    }

    public int get_size() {
        return _size;
    }

    public int get_direct() {
        return _direct;
    }

    public int get_no() {
        return _no;
    }

    public int get_subNo() {
        return _subNo;
    }

    public Bitmap get_bitmap() {
        return _bitmap;
    }

    public void set_charaId(String _charaId) {
        this._charaId = _charaId;
    }

    public void set_size(int _size) {
        this._size = _size;
    }

    public void set_direct(int _direct) {
        this._direct = _direct;
    }

    public void set_no(int _no) {
        this._no = _no;
    }

    public void set_subNo(int _subNo) {
        this._subNo = _subNo;
    }

    public void set_bitmap(Bitmap _bitmap) {
        this._bitmap = _bitmap;
    }
}
