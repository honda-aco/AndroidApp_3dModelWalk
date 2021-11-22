package com.ns.aco.sp.view.menu;

import android.app.FragmentManager;
import androidx.fragment.app.Fragment;
import androidx.core.app.ActivityCompat;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public interface MenuContract {

    interface View {
        /**
         * サイズボタンのテキスト設定
         * @param value
         */
        void setText_sizeButton(int value);

        /**
         * 開始ボタンの活性・非活性設定
         * @param value
         */
        void setEnabled_startButton(boolean value);

        /**
         * 終了ボタンの活性・非活性設定
         * @param value
         */
        void setEnabled_stopButton(boolean value);

        /**
         * 省エネモード設定チェックボックスのチェック設定
         * @param value
         */
        void setChecked_savingEnergyCheckBox(boolean value);

        /**
         * サイズボタンのタグ設定
         * @param tag
         */
        void setTag_sizeButton(Object tag);

        /**
         * ポジションボタンのテキスト設定
         * @param value
         */
        void setText_positionButton(int value);

        /**
         * ポジションボタンのタグ設定
         * @param tag
         */
        void setTag_positionButton(Object tag);

        /**
         * トーク用チェックボックスの有効設定
         * @param value
         */
        void setEnabled_talkCheckBox(boolean value);

        /**
         * サービスの起動
         */
        void startService();

        /**
         * サービスの停止
         */
        void stopService();

        /**
         * 透明度のサービスへの送信
         */
        void sendBroadcast_transparency();

        /**
         * Fragmentの取得
         * @return
         */
        Fragment getFragment();

        /**
         * サービスの起動状態確認
         * @return
         */
        boolean isServiceRunning();
    }

    interface View_Fragment1 extends View{
        /**
         * Presenterの設定
         * @param presenter
         */
        void setPresenter(Presenter_Fragment1 presenter);

        /**
         * キャラクタイメージの設定
         * @param resourceId
         */
        void setResource_characterImageView(int resourceId);

        /**
         * 上部のImageViewに画像を設定
         * @param resourceList
         */
        void setResource_topImageView(int[] resourceList);
    }

    interface View_Fragment2 extends View{
        /**
         * Presenterの設定
         * @param presenter
         */
        void setPresenter(Presenter_Fragment2 presenter);

        /**
         * 座標範囲設定テキストのテキスト設定
         * @param value
         */
        void setText_rangeEdit(float value);

        /**
         * 表示間隔設定テキストのテキスト設定
         * @param value
         */
        void setText_intervalEdit(float value);

        /**
         * アクティビティの起動
         * @param aClass
         */
        void startActivity(Class<?> aClass);
    }

    interface Handler{
        /**
         * カスタム進行状況ダイアログに最大値を設定
         * @param value
         */
        void setMaxValue_progressDialog(final int value);

        /**
         * カスタム進行状況ダイアログの進行状況に引数値を加算
         * @param value
         */
        void increment_progressDialog(final int value);

        /**
         * 進行状況ダイアログの終了
         */
        void close_progressDialog();
    }

    interface Presenter extends com.ns.aco.sp.Presenter {

        /**
         * 省エネモード設定用コンポーネントの初期化
         */
        void initializeComponents();

       /**
         * SeekBarによる透明度変更
         * @param seekBar
         * @param textView
         */
        void progressChanged_transparency(SeekBar seekBar, TextView textView);

        /**
         * 省エネモードのチェック変更
         * @param checkBox
         */
        void check_savingEnergy(CheckBox checkBox);

        /**
         * サイズ＆ポジション設定ダイアログの表示
         * @param size
         * @param position
         * @param fragmentManager
         */
        void showSizePositionDialog(String size, String position, FragmentManager fragmentManager);

        /**
         * プログレスバー表示ダイアログの表示
         * @param fragmentManager
         */
        void showProgressDialog(FragmentManager fragmentManager);

        /**
         * サービスの起動
         */
        void startService();

        /**
         * サービスの停止
         */
        void stopService();

        /**
         * 起動回数カウントアップ
         * @param resId
         */
        void countUpStartCount(int resId);
    }

    interface Presenter_Fragment1 extends Presenter {
        /**
         * トークモード設定用コンポーネントの初期化
         * @param checkBox
         */
        void initialize_talkActionCheckBox(CheckBox checkBox);

        /**
         * トークモードのチェック変更
         * @param checkBox
         */
        void check_talkAction(CheckBox checkBox);

        /**
         * ImageView選択
         * @param imageViewList
         * @param imageView
         * @param color
         */
        void selectImageView(ImageView[] imageViewList, ImageView imageView, int color);

        /**
         * ImageView選択
         * @param imageViewList
         * @param imageView
         * @param color
         */
        void selectTopImageView(ImageView[] imageViewList, ImageView imageView, int color);
    }

    interface Presenter_Fragment2 extends Presenter {

        /**
         * EditTextのフォーカス変更イベント
         * @param editText
         * @param b
         */
        void onFocusChange_rangeEdit(EditText editText, boolean b);

        /**
         * EditTextのフォーカス変更イベント
         * @param editText
         * @param b
         */
        void onFocusChange_intervalEdit(EditText editText, boolean b);

        /**
         * アクティビティの起動
         * @param aClass
         */
        void startActivity(Class<?> aClass);
    }
}
