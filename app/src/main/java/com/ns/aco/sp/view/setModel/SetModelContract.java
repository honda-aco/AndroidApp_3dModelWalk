package com.ns.aco.sp.view.setModel;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;

import java.io.File;
import java.util.List;

public interface SetModelContract {

    interface View {
        /**
         * Presenterの設定
         * @param presenter
         */
        void setPresenter(Presenter presenter);

        /**
         * Fragmentの取得
         * @return
         */
        Fragment get_fragment();
    }

    interface SetModelView extends View {
        /**
         * ファイル選択Viewの追加
         * @param view
         */
        void addFragmentFileSelect(SetModelContract.FileSelectView view);

        /**
         * ファイル選択Viewの削除
         * @param view
         */
        void removeFragmentFileSelect(SetModelContract.FileSelectView view);

        /**
         * ファイル選択Viewの通番更新
         */
        void updateFileSelectNo();

        /**
         * 全コントロールの有効化設定
         * @param enabled
         */
        void setAllControlsEnabled(boolean enabled);

        /**
         * 保存完了トーストの表示
         */
        void showToast_saveFinished();
    }

    interface FileSelectView extends View {
        /**
         * 全コントロールの有効化設定
         * @param enabled
         */
        void setAllControlsEnabled(boolean enabled);

        /**
         * 参照のみステータスの設定
         * @param statusReferenceOnly
         */
        void setStatusReferenceOnly(boolean statusReferenceOnly);

        /**
         * 参照のみステータスの取得
         * @return
         */
        boolean getStatusReferenceOnly();

        /**
         * 削除済ステータスの取得
         * @return
         */
        boolean getStatusDeleted();

        /**
         * OBJファイルの設定
         * @param objFile
         */
        void setObjFile(File objFile);

        /**
         * OBJファイルの取得
         * @return
         */
        File getObjFile();

        /**
         * PNGファイルの設定
         * @param pngFile
         */
        void setPngFile(File pngFile);

        /**
         * PNGファイルの取得
         * @return
         */
        File getPngFile();

        /**
         * NOの設定
         * @param no
         */
        void setTextNo(int no);

        /**
         * 参照先ファイルの通番を設定
         * @param referenceFileNo
         */
        void setReferenceFileNo(int referenceFileNo);

        /**
         * 参照先ファイルの通番を取得
         * @return
         */
        int getReferenceFileNo();

        /**
         * OBJファイルのパスを設定
         * @param value
         */
        void setObjFileName_editText(String value);

        /**
         * OBJファイルのパスを取得
         * @return
         */
        String getObjFileName_editText();

        /**
         * PNGファイルのパスを設定
         * @param value
         */
        void setPngFileName_editText(String value);

        /**
         * PNGファイルのパスを取得
         * @return
         */
        String getPngFileName_editText();

        /**
         * 登録済のOBJファイルのパスを設定
         * @param objPath
         */
        void setObjFilePath(String objPath);

        /**
         * 登録済のPNGファイルのパスを設定
         * @param pngPath
         */
        void setPngFilePath(String pngPath);

        /**
         * 削除ボタン押下による非表示設定
         */
        void hide();
    }

    interface Presenter extends com.ns.aco.sp.Presenter {

        /**
         * Viewの初期設定
         * @param directoryPath
         */
        void initialize(String directoryPath);

        /**
         * ファイル選択フラグメントの追加
         */
        void addFragmentFileSelect();

        /**
         * ファイル選択フラグメントの削除
         * @param fileSelectView
         */
        void removeFragmentFileSelect(SetModelContract.FileSelectView fileSelectView);

//        /**
//         * ファイル選択フラグメントの削除
//         * @param _fileSelectList
//         */
//        void removeFragmentFileSelect(List<SetModelContract.FileSelectView> _fileSelectList);

        /**
         * ファイル選択フラグメントの仮削除
         * @param fileSelectView
         */
        void hideFragmentFileSelect(SetModelContract.FileSelectView fileSelectView);

        /**
         * OBJファイル・PNGファイルの保存
         * @param _fileSelectList
         * @param directoryPath
         */
        void saveSelectFiles(List<SetModelContract.FileSelectView> _fileSelectList, String directoryPath);

        /**
         * OBJファイル選択ダイアログの表示
         * @param fileSelectView
         * @param fragmentManager
         */
        void showDialogSelectObjFile(SetModelContract.FileSelectView fileSelectView, FragmentManager fragmentManager);

        /**
         * PNGファイル選択ダイアログの表示
         * @param fileSelectView
         * @param fragmentManager
         */
        void showDialogSelectPngFile(SetModelContract.FileSelectView fileSelectView, FragmentManager fragmentManager);

        /**
         * ファイル選択Viewの通番更新
         */
        void updateFileSelectNo();
    }
}
