package com.ns.aco.sp.view.menu;

import android.graphics.Color;
import android.widget.CheckBox;
import android.widget.ImageView;
import com.ns.aco.sp.R;
import com.ns.aco.sp.da.DataAccessContract;
import com.ns.aco.sp.dialog.DialogContract;

public class MenuPresenter1 extends MenuPresenterBase implements MenuContract.Presenter_Fragment1{
    
    private final MenuContract.View_Fragment1 _viewFragment1;

    public MenuPresenter1(MenuContract.View_Fragment1 view, DataAccessContract dataAccess, DialogContract.SizePosition sizePositionDialog, DialogContract.Progress progressDialog) {
        super(view, dataAccess, sizePositionDialog, progressDialog);
        _viewFragment1 = view;
        _viewFragment1.setPresenter(this);
    }

    @Override
    public void initialize_talkActionCheckBox(CheckBox checkBox){
        checkBox.setChecked(_dataAccess.get_TALKACTION());
    }

    @Override
    public void checkTalkAction(CheckBox checkBox){
        _dataAccess.set_TALKACTION(checkBox.isChecked());
    }

    @Override
    public void selectImageView(ImageView[] imageViewList, ImageView imageView, int color){
        for (ImageView view : imageViewList) {
            view.setBackgroundColor(Color.TRANSPARENT);
        }
        imageView.setBackgroundColor(color);

        switch (imageView.getId()){
            case R.id.imageLeft1:
                _viewFragment1.setResource_topImageView(new int[] {
                        R.drawable.gate_droid1_top, R.drawable.gate_droid2_top, R.drawable.gate_droid3_top});
                _viewFragment1.setEnabled_talkCheckBox(false);
                break;
            case R.id.imageLeft2:
                _viewFragment1.setResource_topImageView(new int[] {
                        R.drawable.gate_kohaku1_top, R.drawable.gate_kohaku2_top, R.drawable.gate_kohaku3_top,
                        R.drawable.gate_misaki1_top, R.drawable.gate_yuko1_top});
                _viewFragment1.setEnabled_talkCheckBox(false);
                break;
            case R.id.imageLeft3:
                _viewFragment1.setResource_topImageView(new int[] {
                        R.drawable.gate_onepiece1_top, R.drawable.gate_onepiece2_top});
                _viewFragment1.setEnabled_talkCheckBox(true);
                break;
            case R.id.imageLeft4:
                _viewFragment1.setResource_topImageView(new int[] {
                        R.drawable.gate_waso1_top, R.drawable.gate_waso2_top});
                _viewFragment1.setEnabled_talkCheckBox(true);
                break;
            case R.id.imageLeft5:
                _viewFragment1.setResource_topImageView(new int[] {
                        R.drawable.gate_cardigan1_top, R.drawable.gate_cardigan2_top});
                _viewFragment1.setEnabled_talkCheckBox(true);
                break;
            case R.id.imageLeft6:
                _viewFragment1.setResource_topImageView(new int[] {
                        R.drawable.gate_jersey1_top, R.drawable.gate_jersey2_top, R.drawable.gate_cosplay1_top});
                _viewFragment1.setEnabled_talkCheckBox(true);
                break;
            case R.id.imageLeft7:
                _viewFragment1.setResource_topImageView(new int[] {
                        R.drawable.gate_witch1_top, R.drawable.gate_witch2_top, R.drawable.gate_witch3_top});
                _viewFragment1.setEnabled_talkCheckBox(true);
                break;
            case R.id.imageLeft8:
                _viewFragment1.setResource_topImageView(new int[] {
                        R.drawable.gate_frog1_top, R.drawable.gate_frog2_top});
                _viewFragment1.setEnabled_talkCheckBox(false);
                break;
            case R.id.imageLeft9:
                _viewFragment1.setResource_topImageView(new int[] {
                        R.drawable.gate_frograin1_top, R.drawable.gate_frograin2_top});
                _viewFragment1.setEnabled_talkCheckBox(false);
                break;
            default:
                // キャラクタイメージを設定
                setResource_characterImage((int)imageView.getTag());
                break;
        }
    }

    @Override
    public void selectTopImageView(ImageView[] imageViewList, ImageView imageView, int color){
        for (ImageView view : imageViewList) {
            view.setBackgroundColor(Color.TRANSPARENT);
        }
        imageView.setBackgroundColor(color);

        // キャラクタイメージを設定
        setResource_characterImage((int)imageView.getTag());
    }

    private void setResource_characterImage(int drawableId_gate){
        switch (drawableId_gate){
            case R.drawable.gate_droid1_top:
                _viewFragment1.setResource_characterImageView(R.drawable.droid1);
                break;
            case R.drawable.gate_droid2_top:
                _viewFragment1.setResource_characterImageView(R.drawable.droid2);
                break;
            case R.drawable.gate_droid3_top:
                _viewFragment1.setResource_characterImageView(R.drawable.droid3);
                break;
            case R.drawable.gate_kohaku1_top:
                _viewFragment1.setResource_characterImageView(R.drawable.kohaku1);
                break;
            case R.drawable.gate_kohaku2_top:
                _viewFragment1.setResource_characterImageView(R.drawable.kohaku2);
                break;
            case R.drawable.gate_kohaku3_top:
                _viewFragment1.setResource_characterImageView(R.drawable.kohaku3);
                break;
            case R.drawable.gate_misaki1_top:
                _viewFragment1.setResource_characterImageView(R.drawable.misaki1);
                break;
            case R.drawable.gate_yuko1_top:
                _viewFragment1.setResource_characterImageView(R.drawable.yuko1);
                break;
            case R.drawable.gate_onepiece1_top:
                _viewFragment1.setResource_characterImageView(R.drawable.yucco_onepiece1);
                break;
            case R.drawable.gate_onepiece2_top:
                _viewFragment1.setResource_characterImageView(R.drawable.yucco_onepiece2);
                break;
            case R.drawable.gate_waso1_top:
                _viewFragment1.setResource_characterImageView(R.drawable.yucco_waso1);
                break;
            case R.drawable.gate_waso2_top:
                _viewFragment1.setResource_characterImageView(R.drawable.yucco_waso2);
                break;
            case R.drawable.gate_cardigan1_top:
                _viewFragment1.setResource_characterImageView(R.drawable.yucco_cardigan1);
                break;
            case R.drawable.gate_cardigan2_top:
                _viewFragment1.setResource_characterImageView(R.drawable.yucco_cardigan2);
                break;
            case R.drawable.gate_jersey1_top:
                _viewFragment1.setResource_characterImageView(R.drawable.yucco_jersey1);
                break;
            case R.drawable.gate_jersey2_top:
                _viewFragment1.setResource_characterImageView(R.drawable.yucco_jersey2);
                break;
            case R.drawable.gate_cosplay1_top:
                _viewFragment1.setResource_characterImageView(R.drawable.yucco_cosplay1);
                break;
            case R.drawable.gate_witch1_top:
                _viewFragment1.setResource_characterImageView(R.drawable.yucco_witch1);
                break;
            case R.drawable.gate_witch2_top:
                _viewFragment1.setResource_characterImageView(R.drawable.yucco_witch2);
                break;
            case R.drawable.gate_witch3_top:
                _viewFragment1.setResource_characterImageView(R.drawable.yucco_witch3);
                break;
            case R.drawable.gate_frog1_top:
                _viewFragment1.setResource_characterImageView(R.drawable.frog1);
                break;
            case R.drawable.gate_frog2_top:
                _viewFragment1.setResource_characterImageView(R.drawable.frog2);
                break;
            case R.drawable.gate_frograin1_top:
                _viewFragment1.setResource_characterImageView(R.drawable.frog_raincoat1);
                break;
            case R.drawable.gate_frograin2_top:
                _viewFragment1.setResource_characterImageView(R.drawable.frog_raincoat2);
                break;
        }
    }
}
