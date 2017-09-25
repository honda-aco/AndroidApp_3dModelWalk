package com.ns.aco.sp.common.view;

import android.content.Context;
import android.os.Build;
import android.view.DragEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ns.aco.sp.common.R;
import com.ns.aco.sp.common.listener.OnDropListenerIF;

/**
 * Created by ns on 2017/05/02.
 */
public class ViewDragAndDrop{
        
    private Context _context = null;
    private View _root = null;
    private FrameLayout _frameDragDrop = null;
    private ImageView _imgTopLeft = null;
    private ImageView _imgTopRight = null;
    private ImageView _imgBottomLeft = null;
    private ImageView _imgBottomRight = null;
    private OnDropListenerIF.OnDropListener _onDropListener = null;
//    private DropArea _dropArea = DropArea.None;

    public Context get_context() {
        return _context;
    }

    public void set_context(Context _context) {
        this._context = _context;
    }

    public View get_root() {
        return _root;
    }

    public void set_root(View _root) {
        this._root = _root;
    }

    public FrameLayout get_frameDragDrop() {
        return _frameDragDrop;
    }

    public void set_frameDragDrop(FrameLayout _frameDragDrop) {
        this._frameDragDrop = _frameDragDrop;
    }

    public ImageView get_imgTopLeft() {
        return _imgTopLeft;
    }

    public void set_imgTopLeft(ImageView _imgTopLeft) {
        this._imgTopLeft = _imgTopLeft;
    }

    public ImageView get_imgTopRight() {
        return _imgTopRight;
    }

    public void set_imgTopRight(ImageView _imgTopRight) {
        this._imgTopRight = _imgTopRight;
    }

    public ImageView get_imgBottomLeft() {
        return _imgBottomLeft;
    }

    public void set_imgBottomLeft(ImageView _imgBottomLeft) {
        this._imgBottomLeft = _imgBottomLeft;
    }

    public ImageView get_imgBottomRight() {
        return _imgBottomRight;
    }

    public void set_imgBottomRight(ImageView _imgBottomRight) {
        this._imgBottomRight = _imgBottomRight;
    }

    public ViewDragAndDrop(Context context, OnDropListenerIF.OnDropListener onDropListener) {
        _context = context;
        _onDropListener = onDropListener;
        initialize();
    }

    private void initialize(){
        _root = View.inflate(_context, R.layout.drag_and_drop, null);
        _frameDragDrop = (FrameLayout) _root.findViewById(R.id.frameDragDrop);
        _frameDragDrop.setLayoutParams(
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                )
        );

        _imgTopLeft = (ImageView) _root.findViewById(R.id.imgTopLeftArea);
        _imgTopRight = (ImageView) _root.findViewById(R.id.imgTopRightArea);
        _imgBottomLeft = (ImageView) _root.findViewById(R.id.imgBottomLeftArea);
        _imgBottomRight = (ImageView) _root.findViewById(R.id.imgBottomRightArea);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            _imgTopLeft.setImageDrawable(_context.getDrawable(R.drawable.drop_area));
            _imgTopRight.setImageDrawable(_context.getDrawable(R.drawable.drop_area));
            _imgBottomLeft.setImageDrawable(_context.getDrawable(R.drawable.drop_area));
            _imgBottomRight.setImageDrawable(_context.getDrawable(R.drawable.drop_area));
        }else{
            _imgTopLeft.setImageDrawable(_context.getResources().getDrawable(R.drawable.drop_area));
            _imgTopRight.setImageDrawable(_context.getResources().getDrawable(R.drawable.drop_area));
            _imgBottomLeft.setImageDrawable(_context.getResources().getDrawable(R.drawable.drop_area));
            _imgBottomRight.setImageDrawable(_context.getResources().getDrawable(R.drawable.drop_area));
        }

        _imgTopLeft.setOnDragListener(onDragListener);
        _imgTopRight.setOnDragListener(onDragListener);
        _imgBottomLeft.setOnDragListener(onDragListener);
        _imgBottomRight.setOnDragListener(onDragListener);
    }

    private View.OnDragListener onDragListener = new View.OnDragListener(){
        @Override
        public boolean onDrag(View v, DragEvent event) {
            ImageView imageView = (ImageView) v;
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    _onDropListener.dragEnded();
                    return false;

                case DragEvent.ACTION_DRAG_LOCATION:
                    // Dragカーソルが来たら画像を変更
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        imageView.setImageDrawable(_context.getDrawable(R.drawable.drop_target));
                    }else {
                        imageView.setImageDrawable(_context.getResources().getDrawable(R.drawable.drop_target));
                    }
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    // Dragカーソルが離れたら画像を変更
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        imageView.setImageDrawable(_context.getDrawable(R.drawable.drop_area));
                    }else {
                        imageView.setImageDrawable(_context.getResources().getDrawable(R.drawable.drop_area));
                    }
                    return true;

                case DragEvent.ACTION_DROP:
                    _onDropListener.drop(imageView);
                    return true;
            }
            return false;
        }
    };

//    private enum DropArea{
//        TopLeft,
//        TopRight,
//        BottomLeft,
//        BottomRight,
//        None
//    }
}
