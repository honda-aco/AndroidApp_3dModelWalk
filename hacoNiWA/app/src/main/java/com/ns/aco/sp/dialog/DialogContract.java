package com.ns.aco.sp.dialog;

import android.app.FragmentManager;

import java.io.File;

public interface DialogContract<T> {

    void setPresenter(T presenter);
    void show(FragmentManager manager, String tag);

    interface SizePosition extends DialogContract<SizePosition.Presenter>{

        void setDefaultSizeId(String value);
        void setDefaultPositionId(String value);

        interface Presenter{

            void result(int size, int position);

        }
    }

    interface Progress extends DialogContract<Progress.Presenter>{

        void setMax(int value);
        void incrementProgressBy(int value);
        void setProgress(int value);
        void dismiss();

        interface Presenter{

            void result();

        }
    }

    interface SelectObjFile extends DialogContract<SelectObjFile.Presenter>{

        interface Presenter{

            void result_selectObjFile(File file);

        }
    }

    interface SelectPngFile extends DialogContract<SelectPngFile.Presenter>{

        interface Presenter{

            void result_selectPngFile(File file);

        }
    }
}
