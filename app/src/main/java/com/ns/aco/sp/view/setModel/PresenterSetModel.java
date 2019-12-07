package com.ns.aco.sp.view.setModel;

import android.app.FragmentManager;
import android.content.res.Configuration;
import com.ns.aco.sp.R;
import com.ns.aco.sp.common.util.UtilityFile;
import com.ns.aco.sp.cpp.CallNative;
import com.ns.aco.sp.dialog.DialogContract;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PresenterSetModel implements SetModelContract.Presenter, DialogContract.SelectObjFile.Presenter, DialogContract.SelectPngFile.Presenter {

    private final SetModelContract.SetModelView _setModelView;
    private final DialogContract.SelectObjFile _selectObjFileDialog;
    private final DialogContract.SelectPngFile _selectPngFileDialog;
    private final CallNative.ConvertObjFile _convertObjFile = new CallNative().new ConvertObjFile();
    private SetModelContract.FileSelectView _fileSelectView = null;

   public PresenterSetModel(SetModelContract.SetModelView view, DialogContract.SelectObjFile selectObjFileDialog, DialogContract.SelectPngFile selectPngFileDialog){
        _setModelView = view;
        _selectObjFileDialog = selectObjFileDialog;
        _selectPngFileDialog = selectPngFileDialog;
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
    public void initialize(String directoryPath){

        File file = new File(directoryPath);
        // フォルダが存在しなければファイル選択フラグメントを一つ追加して終了
        if (file.exists() == false || file.list().length == 0){
            addFragmentFileSelect();
            return;
        }

        for (String mapFile : file.list()){
            String mark = mapFile.substring(0, 1);
            if (mark.equals("_")){
                int delimiter1 = mapFile.indexOf(".obj^");
                int delimiter2 = mapFile.lastIndexOf("^");
                String objFile = mapFile.substring(1, delimiter1 + 4);
                String pngFile = mapFile.substring(delimiter1 + 5, delimiter2);
                String no = mapFile.substring(delimiter2 + 1);

                SetModelContract.FileSelectView fragmentFileSelect = new FileSelectFragment();
                fragmentFileSelect.setPresenter(this);
                fragmentFileSelect.setReferenceFileNo(Integer.valueOf(no));
                fragmentFileSelect.setObjFilePath(objFile);
                fragmentFileSelect.setPngFilePath(pngFile);
                fragmentFileSelect.setStatusReferenceOnly(true);

                _setModelView.addFragmentFileSelect(fragmentFileSelect);
            }
        }
    }

    @Override
    public void addFragmentFileSelect(){
        SetModelContract.FileSelectView fragmentFileSelect = new FileSelectFragment();
        fragmentFileSelect.setPresenter(this);
        _setModelView.addFragmentFileSelect(fragmentFileSelect);
    }

    @Override
    public void removeFragmentFileSelect(SetModelContract.FileSelectView fileSelectView){
        _setModelView.removeFragmentFileSelect(fileSelectView);
    }

    @Override
    public void hideFragmentFileSelect(SetModelContract.FileSelectView fileSelectView){
        if (fileSelectView.getStatusReferenceOnly()){
            fileSelectView.hide();
        }else{
            _setModelView.removeFragmentFileSelect(fileSelectView);
        }
    }

    @Override
    public void saveSelectFiles(List<SetModelContract.FileSelectView> _fileSelectList, String directoryPath){
        // コントロールを無効化
        setAllControlsEnabled(_fileSelectList, false);
        // 未使用領域・重複領域を削除
        deleteUselessSelection(_fileSelectList, directoryPath);
        // ファイル保存先フォルダを作成
        createDirectory(directoryPath);

        // 頂点系ファイルを作成
        int i = 0;
        for (SetModelContract.FileSelectView fragmentFileSelect : _fileSelectList){
            // 参照のみは保存対象外
            if (fragmentFileSelect.getStatusReferenceOnly()){
                continue;
            }

            File file = new File(directoryPath + "/" + _setModelView.get_fragment().getString(R.string.convert_vertex_file).replaceFirst("\\?", String.valueOf(i)));
            while (file.exists()){
                i += 1;
                file = new File(directoryPath + "/" + _setModelView.get_fragment().getString(R.string.convert_vertex_file).replaceFirst("\\?", String.valueOf(i)));
            }

            // OBJファイルの変換を行う
            _convertObjFile.convertObjFile(
                    fragmentFileSelect.getObjFile().getAbsolutePath(),
                    directoryPath + "/" + _setModelView.get_fragment().getString(R.string.convert_vertex_file).replaceFirst("\\?", String.valueOf(i)),
                    directoryPath + "/" + _setModelView.get_fragment().getString(R.string.convert_normal_file).replaceFirst("\\?", String.valueOf(i)),
                    directoryPath + "/" + _setModelView.get_fragment().getString(R.string.convert_texture_file).replaceFirst("\\?", String.valueOf(i)));

            String map = directoryPath + "/" + "_" + fragmentFileSelect.getObjFile().getName() + "^";

            if (fragmentFileSelect.getPngFile() != null){
                // テクスチャファイルのコピー先を用意する
                File textureFile = new File(directoryPath + "/" + _setModelView.get_fragment().getString(R.string.convert_png_file).replaceFirst("\\?", String.valueOf(i)));
                // テクスチャファイルをコピーする
                UtilityFile.copyFile(fragmentFileSelect.getPngFile(), textureFile);
                map = map + fragmentFileSelect.getPngFile().getName();
            }

            map = map + "^" + String.valueOf(i);
            File mapFile = new File(map);
            // /data/data/com.ns.aco.sp/?/?/_v_1.txt^xxxxxx.objのようなファイルを作成し、
            // 次回起動時に登録済みファイル情報を保持する。
            try {
                mapFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // トーストで保存完了を表示
        _setModelView.showToast_saveFinished();
        // コントロールを有効化
        setAllControlsEnabled(_fileSelectList, true);
    };

    @Override
    public void showDialogSelectObjFile(SetModelContract.FileSelectView fileSelectView, FragmentManager fragmentManager){
        _fileSelectView = fileSelectView;
        _selectObjFileDialog.setPresenter(this);
        _selectObjFileDialog.show(fragmentManager, null);
    }

    @Override
    public void showDialogSelectPngFile(SetModelContract.FileSelectView fileSelectView, FragmentManager fragmentManager){
        _fileSelectView = fileSelectView;
        _selectPngFileDialog.setPresenter(this);
        _selectPngFileDialog.show(fragmentManager, null);
    }

    @Override
    public void result_selectObjFile(File file) {
        _fileSelectView.setObjFile(file);
        _fileSelectView.setObjFileName_editText(file.getName());
        _fileSelectView = null;
    }

    @Override
    public void result_selectPngFile(File file) {
        _fileSelectView.setPngFile(file);
        _fileSelectView.setPngFileName_editText(file.getName());
        _fileSelectView = null;
    }

    private void setAllControlsEnabled(List<SetModelContract.FileSelectView> _fileSelectList, boolean enabled){
        _setModelView.setAllControlsEnabled(enabled);
        for (SetModelContract.FileSelectView fileSelectView : _fileSelectList){
            fileSelectView.setAllControlsEnabled(enabled);
        }
    }

    private void deleteUselessSelection(List<SetModelContract.FileSelectView> fileSelectList, String directoryPath){
        List<SetModelContract.FileSelectView> deleteFileSelects = new ArrayList<SetModelContract.FileSelectView>();
        List<String> objPath = new ArrayList<String>();

        for (SetModelContract.FileSelectView view : fileSelectList){
            // 参照のみはセクション削除時にファイル削除をしようとしたが、
            // 保存ボタンを押下する前に削除が確定するのは不自然なのでこのタイミングとする
            if (view.getStatusReferenceOnly()){
                if (view.getStatusDeleted() == false){
                    continue;
                }else{
                    deleteFiles(view, directoryPath);
                }
            }

            File objFile = view.getObjFile();
            if (objFile == null){
                // OBJファイルの指定がないセクションは削除する
                deleteFileSelects.add(view);
            }else{
                boolean existSamePath = false;
                for (String path : objPath){
                    if (path.equals(objFile.getAbsolutePath())){
                        existSamePath = true;
                        break;
                    }
                }
                if (existSamePath){
                    // 重複するOBJファイルを指定するセクションは削除する
                    deleteFileSelects.add(view);
                }else{
                    objPath.add(objFile.getAbsolutePath());
                }
            }
        }
        // ループ途中で削除するとカウンタがずれるから最後に全て削除する
        for (SetModelContract.FileSelectView fileSelectView : deleteFileSelects){
            _setModelView.removeFragmentFileSelect(fileSelectView);
        }
    };

    private void deleteFiles(SetModelContract.FileSelectView fileSelectView, String directoryPath){
        // 存在し得るファイルのパスを取得する
        int no = fileSelectView.getReferenceFileNo();
        File[] files = new File[]{
                new File(directoryPath + "/" + _setModelView.get_fragment().getString(R.string.convert_vertex_file).replaceFirst("\\?", String.valueOf(no))),
                new File(directoryPath + "/" + _setModelView.get_fragment().getString(R.string.convert_normal_file).replaceFirst("\\?", String.valueOf(no))),
                new File(directoryPath + "/" + _setModelView.get_fragment().getString(R.string.convert_texture_file).replaceFirst("\\?", String.valueOf(no))),
                new File(directoryPath + "/" + _setModelView.get_fragment().getString(R.string.convert_png_file).replaceFirst("\\?", String.valueOf(no))),
                new File(directoryPath + "/" + "_" + fileSelectView.getObjFileName_editText() + "^" + fileSelectView.getPngFileName_editText() + "^" + String.valueOf(no))};

        // 存在するファイルは削除する
        for (File file : files){
            if (file.exists()){
                file.delete();
            }
        }
    }

    private void createDirectory(String directoryPath){
        File file = new File(directoryPath);
        if (file.exists() == false){
            // 出力先のフォルダを作成
            file.mkdirs();
        }
    }

    @Override
    public void updateFileSelectNo(){
        _setModelView.updateFileSelectNo();
    }
}
