package com.ns.aco.sp.view.setModel;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.ns.aco.sp.common.util.UtilityActivity;
import java.util.ArrayList;
import java.util.List;

public abstract class SetModelFragment extends Fragment implements SetModelContract.SetModelView{

    protected SetModelContract.Presenter _presenter;
    protected LinearLayout _linearLayout;
    protected Button _addFileButton;
    protected Button _saveButton;
    protected List<SetModelContract.FileSelectView> _fileSelectList = new ArrayList<SetModelContract.FileSelectView>();

//    abstract public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    abstract protected String getDirectoryPath();
    abstract protected String getSaveFinishedMessage();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        for (SetModelContract.FileSelectView fileSelectView : _fileSelectList){
            UtilityActivity.removeFragmentToActivity(
                    getActivity().getSupportFragmentManager(),
                    fileSelectView.get_fragment());
        }
        _fileSelectList.clear();
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        _presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        _presenter.onPause();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        _presenter.onConfigurationChanged(newConfig);
    }

//    @Override
//    public void onDestroyView(){
//        _presenter.removeFragmentFileSelect(_fileSelectList);
//        super.onDestroyView();
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        _presenter.onDestroy();
    }

    @Override
    public void setPresenter(@NonNull SetModelContract.Presenter presenter) {
        _presenter = presenter;
    }

    @Override
    public void addFragmentFileSelect(SetModelContract.FileSelectView view){
        // リストに追加する
        _fileSelectList.add(view);
        view.setTextNo(_fileSelectList.size());
        // 画面のレイアウトとして追加する
        UtilityActivity.addFragmentToActivity(
                getActivity().getSupportFragmentManager(),
                view.get_fragment(),
                _linearLayout.getId());
    }

    @Override
    public void removeFragmentFileSelect(SetModelContract.FileSelectView view){
        // リストから削除する
        for (int i = 0; i < _fileSelectList.size(); i++){
            if (_fileSelectList.get(i) == view){
                _fileSelectList.remove(i);
                break;
            }
        }
        // 画面のレイアウトから削除する
        UtilityActivity.removeFragmentToActivity(
                getActivity().getSupportFragmentManager(),
                view.get_fragment());
    }

    @Override
    public void updateFileSelectNo(){
        for (int i = 0; i < _fileSelectList.size(); i++){
            // 選択リストの番号を振る
            _fileSelectList.get(i).setTextNo(i + 1);
//            // 偶数Noの背景色をやや薄くする
//            if (i % 2 == 0){
//                _fileSelectList.get(i).getlinearSelection()
//                        .setBackgroundColor(Color.argb(255, 0, 0, 0));
//            }else{
//                _fileSelectList.get(i).getlinearSelection()
//                        .setBackgroundColor(Color.argb(255, 30, 30, 30));
//            }
        }
    }

    @Override
    public void setAllControlsEnabled(boolean enabled){
        _addFileButton.setEnabled(enabled);
        _saveButton.setEnabled(enabled);
    }

    @Override
    public Fragment get_fragment() {
        return this;
    }

    @Override
    public void showToast_saveFinished(){
        // トーストで保存完了を表示
        Toast.makeText(
                getActivity(),
                getSaveFinishedMessage(),
                Toast.LENGTH_SHORT).show();
    }
}
