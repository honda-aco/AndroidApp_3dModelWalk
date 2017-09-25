package com.ns.aco.sp.da;

import android.graphics.Bitmap;
import com.ns.aco.sp.da.entity.DataRowCharaPoseBitmap;
import com.ns.aco.sp.da.entity.DataRowCharaWalkBitmap;
import java.util.ArrayList;

public interface DataAccessContract {

    /**
     * データアクセスの開始
     */
    void open();

    /**
     * データアクセスの終了
     */
    void close();

    /**
     * データの初期化
     * @return
     */
    void initialize();

    /**
     * 言語を取得
     * @return
     */
    int get_LANGUAGE();

    /**
     * 透明度を取得
     * @return
     */
    int get_TRANSPARENCY();

    /**
     * 省エネモードのON/OFFを取得
     * @return
     */
    boolean get_SAVINGENERGY();

    /**
     * トーク/OFFを取得
     * @return
     */
    boolean get_TALKACTION();

    /**
     * サイズを取得
     * @return
     */
    int get_SIZE();

    /**
     * ドアの位置を取得
     * @return
     */
    int get_DOORPOSITION();

    /**
     * 座標範囲を取得
     * @return
     */
    float get_COORDINATE_RANGE();

    /**
     * モデルの表示切替時間間隔を取得
     * @return
     */
    float get_INTERVAL();

    /**
     * 言語を取得
     * @return
     */
    boolean set_LANGUAGE(int value);

    /**
     * 透明度を取得
     * @return
     */
    boolean set_TRANSPARENCY(int value);

    /**
     * 省エネモードのON/OFFを取得
     * @return
     */
    boolean set_SAVINGENERGY(boolean value);

    /**
     * トーク/OFFを取得
     * @return
     */
    boolean set_TALKACTION(boolean value);

    /**
     * サイズを取得
     * @return
     */
    boolean set_SIZE(int value);

    /**
     * ドアの位置を取得
     * @return
     */
    boolean set_DOORPOSITION(int value);

    /**
     * 座標範囲を取得
     * @return
     */
    boolean set_COORDINATE_RANGE(int value);

    /**
     * モデルの表示切替時間間隔を取得
     * @return
     */
    boolean set_INTERVAL(int value);

    /**
     * キャラクタの呼出し回数の更新
     * @return
     */
    int countUp_START_COUNT_UNITY();

    /**
     * キャラクタの呼出し回数の更新
     * @return
     */
    int countUpSTART_COUNT_DROID();

    /**
     * キャラクタの呼出し回数の更新
     * @return
     */
    int countUp_START_COUNT_ONEPIECE();

    /**
     * キャラクタの呼出し回数の更新
     * @return
     */
    int countUp_START_COUNT_WASO();

    /**
     * キャラクタの呼出し回数の更新
     * @return
     */
    int countUp_START_COUNT_CARDIGAN();

    /**
     * キャラクタの呼出し回数の更新
     * @return
     */
    int countUp_START_COUNT_FROG();

    /**
     * キャラクタの呼出し回数の更新
     * @return
     */
    int countUp_START_COUNT_FROGRAIN();

    /**
     * キャラクタの呼出し回数の更新
     * @return
     */
    int countUp_START_COUNT_JERSEY();

    /**
     * キャラクタの呼出し回数の更新
     * @return
     */
    int countUp_START_COUNT_WITCH();

    /**
     * キャラクタの呼出し回数の更新
     * @return
     */
    int countUp_START_COUNT_ORIGINAL();

    /**
     * キャラクタのウォーク用Bitmap取得
     * @param charaId
     * @param size
     * @param direction
     * @return
     */
    ArrayList<DataRowCharaWalkBitmap> get_CHARA_WALK_BITMAP(String charaId, int size, int direction);

    /**
     * キャラクタのウォーク用Bitmap設定
     * @param charaId
     * @param size
     * @param direction
     * @param no
     * @param subno
     * @param bitmap
     * @return
     */
    boolean set_CHARA_WALK_BITMAP(String charaId, int size, int direction, int no, int subno, Bitmap bitmap);

    /**
     *  キャラクタのウォーク用Bitmap削除
     * @return
     */
    boolean delete_CHARA_WALK_BITMAP();

    /**
     * キャラクタのポーズ用Bitmap取得
     * @param charaId
     * @param size
     * @param poseId
     * @return
     */
    ArrayList<DataRowCharaPoseBitmap> get_CHARA_POSE_BITMAP(String charaId, int size, int poseId);

    /**
     * キャラクタのポーズ用Bitmap設定
     * @param charaId
     * @param poseId
     * @param size
     * @param no
     * @param subno
     * @param bitmap
     * @return
     */
    boolean set_CHARA_POSE_BITMAP(String charaId, int poseId, int size, int no, int subno, Bitmap bitmap);

    /**
     * キャラクタのポーズ用Bitmap削除
     * @return
     */
    boolean delete_CHARA_POSE_BITMAP();

    /**
     * キャラクタのセリフをランダムに1件取得（日本語・英語）
     * @return
     */
    public String[] getRandom_CHARA_TALK_YUCCO();
}
