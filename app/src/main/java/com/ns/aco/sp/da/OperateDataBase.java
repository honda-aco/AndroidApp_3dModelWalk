package com.ns.aco.sp.da;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ns.aco.sp.R;
import com.ns.aco.sp.common.da.DatabaseAdapter;
import com.ns.aco.sp.da.entity.DataRowCharaPoseBitmap;
import com.ns.aco.sp.da.entity.DataRowCharaWalkBitmap;

public class OperateDataBase implements DataAccessContract{
	private Context _context = null;
	private ArrayList<String[]> _sqlResult = null;
	private DatabaseAdapter _dbAdapter = null;
	private AssetManager _assetManager = null;
	private InputStream assetText = null;
	private final String _dbName;
	private final int _dbVersion;
	private final String _sqlFileCreateTbl = "50_Sql/01_CreateTable/01.txt";
	private final String _sqlFileDropTbl = "50_Sql/02_DropTable/01.txt";
	private final String _sqlFileInsertCHARA_TALK = "50_Sql/03_CHARA_TALK/{TALKID}.txt";
	private Chara[] chara;
	private Map<Chara, String> _charaId = new HashMap<OperateDataBase.Chara, String>();
	private Map<Chara, String> _charaName = new HashMap<Chara, String>();
	private Map<Chara, String> _talkID = new HashMap<Chara, String>();
	private Map<String, String> _talkTextPath = new HashMap<String, String>();
	private Integer _recordCount = null;
	private Random _random = new Random();

	public OperateDataBase(Context context){
		_context = context;
		_dbName = _context.getString(R.string.db_name);
		_dbVersion = Integer.parseInt(context.getString(R.string.db_version));
		_dbAdapter = new DatabaseAdapter();

		// 頂点情報を格納したtextファイルを読込む
		_assetManager = context.getResources().getAssets();

		// キャラクタ定義用列挙型をすべて取得
		chara = new Chara[]{Chara.Unity, Chara.Droid, Chara.Onepiece, Chara.Waso,
							Chara.Cardigan,Chara.Frog, Chara.Frograin, Chara.Jersey,
							Chara.Witch, Chara.Original};

		// ハッシュマップの値を設定
		initAllHashmap();
	}

	@Override
	public void open(){
		_dbAdapter.open(_context, _dbName, _dbVersion);
	}

	@Override
	public void close(){
		_dbAdapter.close();
	}

	@Override
	public void initialize() {
		//dropTable();
		createTable();
		initTbl_CHARA_START_COUNT();
		initTbl_CHARA_TALK_MAP();
		initTbl_CHARA_TALK();
		init_SIZE_In_SETTING(6);
		init_DOORPOSITION_In_SETTING(0);
		init_SAVINGENERGY_In_SETTING(0);
		init_TALKACTION_In_SETTING(0);
		init_LANGUAGE_In_SETTING(1);
		init_TRANSPARENCY_In_SETTING(255);
		init_COORDINATE_RANGE_In_SETTING_REAL(5.0f);
		init_INTERVAL_In_SETTING_REAL(0.3f);
	}

	@Override
	public int get_LANGUAGE() {
		return get_SETTING("LANGUAGE");
	}

	@Override
	public int get_TRANSPARENCY() {
		return get_SETTING("TRANSPARENCY");
	}

	@Override
	public boolean get_SAVINGENERGY() {
		return get_SETTING_Boolean("SAVINGENERGY");
	}

	@Override
	public boolean get_TALKACTION() {
		return get_SETTING_Boolean("TALKACTION");
	}

	@Override
	public int get_SIZE() {
		return get_SETTING("SIZE");
	}

	@Override
	public int get_DOORPOSITION() {
		return get_SETTING("DOORPOSITION");
	}

	@Override
	public float get_COORDINATE_RANGE() {
		return get_SETTING_REAL("COORDINATE_RANGE");
	}

	@Override
	public float get_INTERVAL() {
		return get_SETTING_REAL("INTERVAL");
	}

	@Override
	public boolean set_LANGUAGE(int value) {
		// 1 = 英語、0 = 日本語
		return _dbAdapter.ExecuteSql(
				"UPDATE SETTING SET VALUE = ? WHERE ID = 'LANGUAGE'", new Object[]{value});
	}

	@Override
	public boolean set_TRANSPARENCY(int value) {
		return _dbAdapter.ExecuteSql(
				"UPDATE SETTING SET VALUE = ? WHERE ID = 'TRANSPARENCY'", new Object[]{value});
	}

	@Override
	public boolean set_SAVINGENERGY(boolean value) {
		int updateInt = 0;
		if (value){
			updateInt = 1;
		}else{
			updateInt = 0;
		}
		return _dbAdapter.ExecuteSql(
				"UPDATE SETTING SET VALUE = ? WHERE ID = 'SAVINGENERGY'", new Object[]{updateInt});
	}

	@Override
	public boolean set_TALKACTION(boolean value) {
		int updateInt = 0;
		if (value){
			updateInt = 1;
		}else{
			updateInt = 0;
		}
		return _dbAdapter.ExecuteSql(
				"UPDATE SETTING SET VALUE = ? WHERE ID = 'TALKACTION'", new Object[]{updateInt});
	}

	@Override
	public boolean set_SIZE(int value) {
		return _dbAdapter.ExecuteSql(
				"UPDATE SETTING SET VALUE = ? WHERE ID = 'SIZE'", new Object[]{value});
	}

	@Override
	public boolean set_DOORPOSITION(int value) {
		return _dbAdapter.ExecuteSql(
				"UPDATE SETTING SET VALUE = ? WHERE ID = 'DOORPOSITION'", new Object[]{value});
	}

	@Override
	public boolean set_COORDINATE_RANGE(float value) {
		return _dbAdapter.ExecuteSql(
				"UPDATE SETTING_REAL SET VALUE = ? WHERE ID = 'COORDINATE_RANGE'", new Object[]{value});
	}

	@Override
	public boolean set_INTERVAL(float value) {
		return _dbAdapter.ExecuteSql(
				"UPDATE SETTING_REAL SET VALUE = ? WHERE ID = 'INTERVAL'", new Object[]{value});
	}

	@Override
	public int countUp_START_COUNT_UNITY() {
		return countUp_CHARA_START_COUNT(Chara.Unity);
	}

	@Override
	public int countUpSTART_COUNT_DROID() {
		return countUp_CHARA_START_COUNT(Chara.Droid);
	}

	@Override
	public int countUp_START_COUNT_ONEPIECE() {
		return countUp_CHARA_START_COUNT(Chara.Onepiece);
	}

	@Override
	public int countUp_START_COUNT_WASO() {
		return countUp_CHARA_START_COUNT(Chara.Waso);
	}

	@Override
	public int countUp_START_COUNT_CARDIGAN() {
		return countUp_CHARA_START_COUNT(Chara.Cardigan);
	}

	@Override
	public int countUp_START_COUNT_FROG() {
		return countUp_CHARA_START_COUNT(Chara.Unity);
	}

	@Override
	public int countUp_START_COUNT_FROGRAIN() {
		return countUp_CHARA_START_COUNT(Chara.Frograin);
	}

	@Override
	public int countUp_START_COUNT_JERSEY() {
		return countUp_CHARA_START_COUNT(Chara.Jersey);
	}

	@Override
	public int countUp_START_COUNT_WITCH() {
		return countUp_CHARA_START_COUNT(Chara.Witch);
	}

	@Override
	public int countUp_START_COUNT_ORIGINAL() {
		return countUp_CHARA_START_COUNT(Chara.Original);
	}

	/**
	 * CHARA_WALK_BITMAPテーブルからBitmapを取得
	 * @param charaId
	 * @param size
	 * @param direction
	 * @return
	 */
	@Override
	public ArrayList<DataRowCharaWalkBitmap> get_CHARA_WALK_BITMAP(String charaId, int size, int direction){
		// IDに対応するレコードを取得する
		Cursor cursor = _dbAdapter.SelectSql(
				"SELECT CHARAID, SIZE, NO, SUBNO, BITMAP FROM CHARA_WALK_BITMAP WHERE CHARAID = '?' AND SIZE = ? AND DIRECTION = ?",
				// "SELECT CHARAID, SIZE, NO, SUBNO, BITMAP FROM CHARA_WALK_BITMAP WHERE SIZE = ? AND DIRECTION = ?",
				new Object[]{ charaId, size, direction});

		ArrayList<DataRowCharaWalkBitmap> charaWalkBitmapList = new ArrayList<DataRowCharaWalkBitmap>();

		if (cursor.getCount() >= 1) {
			// ループの最初に1動かすため、-1から始める
			cursor.moveToPosition(-1);
			while (cursor.moveToNext()){
				DataRowCharaWalkBitmap charaWalkBitmap = new DataRowCharaWalkBitmap();
				charaWalkBitmap.set_charaId(cursor.getString(0));
				charaWalkBitmap.set_direct(cursor.getInt(1));
				charaWalkBitmap.set_no(cursor.getInt(2));
				charaWalkBitmap.set_subNo(cursor.getInt(3));
				byte[] bitmap = cursor.getBlob(4);
				charaWalkBitmap.set_bitmap(BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length));
				charaWalkBitmapList.add(charaWalkBitmap);
			}
		}
		return  charaWalkBitmapList;
	}

	/**
	 * CHARA_WALK_BITMAPテーブルにBitmapを設定
	 * @param charaId
	 * @param size
	 * @param direction
	 * @param no
	 * @param subno
	 * @param bitmap
	 * @return
	 */
	@Override
	public boolean set_CHARA_WALK_BITMAP(String charaId, int size, int direction, int no, int subno, Bitmap bitmap) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream);
		byte[] bytes = byteStream.toByteArray();

		return _dbAdapter.ExecuteSql(
				"INSERT INTO CHARA_WALK_BITMAP ( CHARAID, SIZE, DIRECTION, NO, SUBNO, BITMAP ) VALUES ( ?, ?, ?, ?, ?, ? )",
				new Object[]{ charaId, size, direction, no, subno, bytes });
	}

	/**
	 * CHARA_WALK_BITMAPテーブルから全Bitmapを削除
	 * @return
	 */
	@Override
	public boolean delete_CHARA_WALK_BITMAP(){
		return _dbAdapter.ExecuteSql(
				"DELETE FROM CHARA_WALK_BITMAP",
				new Object[]{});
	}

	/**
	 * CHARA_POSE_BITMAPテーブルからBitmapを取得
	 * @param charaId
	 * @param size
	 * @param poseId
	 * @return
	 */
	@Override
	public ArrayList<DataRowCharaPoseBitmap> get_CHARA_POSE_BITMAP(String charaId, int size, int poseId){
		// IDに対応するレコードを取得する
		Cursor cursor = _dbAdapter.SelectSql(
				"SELECT CHARAID, SIZE, NO, SUBNO, BITMAP FROM CHARA_POSE_BITMAP WHERE CHARAID = '?' AND SIZE = ? AND POSEID = ?",
				new Object[]{ charaId, size, poseId});

		ArrayList<DataRowCharaPoseBitmap> charaPoseBitmapList = new ArrayList<DataRowCharaPoseBitmap>();

		if (cursor.getCount() >= 1) {
			// ループの最初に1動かすため、-1から始める
			cursor.moveToPosition(-1);
			while (cursor.moveToNext()){
				DataRowCharaPoseBitmap charaPoseBitmap = new DataRowCharaPoseBitmap();
				charaPoseBitmap.set_charaId(cursor.getString(0));
				charaPoseBitmap.set_size(cursor.getInt(1));
				charaPoseBitmap.set_no(cursor.getInt(2));
				charaPoseBitmap.set_subNo(cursor.getInt(3));
				byte[] bitmap = cursor.getBlob(4);
				charaPoseBitmap.set_bitmap(BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length));
				charaPoseBitmapList.add(charaPoseBitmap);
			}
		}
		return  charaPoseBitmapList;
	}

	/**
	 * CHARA_POSE_BITMAPテーブルにBitmapを設定
	 * @param charaId
	 * @param poseId
	 * @param size
	 * @param no
	 * @param subno
	 * @param bitmap
	 * @return
	 */
	@Override
	public boolean set_CHARA_POSE_BITMAP(String charaId, int poseId, int size, int no, int subno, Bitmap bitmap) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream);
		byte[] bytes = byteStream.toByteArray();

		return _dbAdapter.ExecuteSql(
				"INSERT INTO CHARA_POSE_BITMAP ( CHARAID, POSEID, SIZE, NO, SUBNO, BITMAP ) VALUES ( ?, ?, ?, ?, ?, ? )",
				new Object[]{ charaId, poseId, size, no, subno, bytes });
	}

	/**
	 * CHARA_POSE_BITMAPテーブルの全Bitmap削除
	 * @return
	 */
	@Override
	public boolean delete_CHARA_POSE_BITMAP(){
		return _dbAdapter.ExecuteSql(
				"DELETE FROM CHARA_POSE_BITMAP",
				new Object[]{});
	}

	/**
	 * キャラクタのセリフをランダムに1件取得（日本語・英語）
	 * @return
	 */
	@Override
	public String[] getRandom_CHARA_TALK_YUCCO(){
		return getRandom_CHARA_TALK(Chara.Onepiece);
	};

	/**
	 * テーブルの作成
	 * @return
	 * @throws IOException
     */
	private boolean createTable(){

		try {
			assetText = _assetManager.open(_sqlFileCreateTbl);
			BufferedReader bufText = new BufferedReader(new InputStreamReader(assetText));
			String[] sqlQuery = bufText.readLine().split(";");
		    _dbAdapter.MultiExecuteSql(sqlQuery);
		    return true;
		} catch (Exception e) {
			return false;
		} finally{
			try{
				assetText.close();
			}catch (Exception e){
				return false;
			}
		}
	}

	/**
	 * テーブルの削除
	 * @return
	 * @throws IOException
	 */
	private boolean dropTable() throws IOException{

		try {
			assetText = _assetManager.open(_sqlFileDropTbl);
			BufferedReader bufText = new BufferedReader(new InputStreamReader(assetText));
			String[] sqlQuery = bufText.readLine().split(";");
		    _dbAdapter.MultiExecuteSql(sqlQuery);
		    return true;
		} catch (Exception e) {
			return false;
		} finally{
			assetText.close();
		}
	}

	/**
	 * CHARA_START_COUNTテーブルデータの初期化
	 * @return
     */
	private boolean initTbl_CHARA_START_COUNT(){
		boolean result = false;
		// キャラクタIDごとにレコードを取得する
		for (Chara charaKind : chara){
			_sqlResult = _dbAdapter.SelectSql(
				"SELECT CHARAID, STARTCOUNT FROM CHARA_START_COUNT WHERE CHARAID = ?",
				new String[] {_charaId.get(charaKind)},
				2);
			// レコードのないキャラクタIDに対してのみレコードを追加する
			if (_sqlResult.size() == 0){
				_dbAdapter.ExecuteSql(
					"INSERT INTO CHARA_START_COUNT (CHARAID, STARTCOUNT) VALUES (?, ?)",
					new Object[] {_charaId.get(charaKind), 0});
			}
		}
		return result;
	}

	/**
	 * CHARA_START_COUNTテーブルデータのカウントアップ（カウントアップ後の値を返す）
	 * @param charaKind
	 * @return
     */
	private int countUp_CHARA_START_COUNT(Chara charaKind){
		// キャラクタIDごとにレコードを取得する
		_sqlResult = _dbAdapter.SelectSql(
			"SELECT STARTCOUNT FROM CHARA_START_COUNT WHERE CHARAID = ?",
			new String[] {_charaId.get(charaKind)},
			1);

		int startCount = Integer.parseInt(_sqlResult.get(0)[0]);
		startCount += 1;

		_dbAdapter.ExecuteSql(
			"UPDATE CHARA_START_COUNT SET STARTCOUNT = ? WHERE CHARAID = ?",
			new Object[] {startCount, _charaId.get(charaKind)});

		return startCount;
	}

	/**
	 * CHARA_TALKテーブルデータの初期化
	 * @return
     */
	private boolean initTbl_CHARA_TALK_MAP(){
		boolean result = false;
		// キャラクタIDごとにレコードを取得する
		for (Chara charaKind : chara){
			_sqlResult = _dbAdapter.SelectSql(
				"SELECT CHARAID, TALKID FROM CHARA_TALK_MAP WHERE CHARAID = ?",
				new String[] {_charaId.get(charaKind)},
				2);
			// レコードのないキャラクタIDに対してのみレコードを追加する
			if (_sqlResult.size() == 0){
				_dbAdapter.ExecuteSql(
					"INSERT INTO CHARA_TALK_MAP (CHARAID, TALKID) VALUES (?, ?)",
					new Object[] {_charaId.get(charaKind), _talkID.get(charaKind)});
			}
		}
		return result;
	}

	/**
	 * CHARA_TALKテーブルデータの初期化
	 * @return
	 * @throws IOException
     */
	private boolean initTbl_CHARA_TALK(){
		boolean result = true;
		// トークIDごとにレコードを取得する
		//for (Chara charaKind : chara){
		_sqlResult = _dbAdapter.SelectSql(
			"SELECT COUNT(*) FROM CHARA_TALK WHERE TALKID = ?",
			new String[] {_talkID.get(Chara.Onepiece)}, 1);
		// ゆっこのトークIDに対してのみレコードを追加する
		try {
			assetText = _assetManager.open(_talkTextPath.get(_talkID.get(Chara.Onepiece)));
			BufferedReader bufText = new BufferedReader(new InputStreamReader(assetText,"sjis"));
			String[] sqlQuery = bufText.readLine().split(";");
			// テーブルデータがファイルの項目数より少ない場合はDrop&Insert
			if (Integer.parseInt(_sqlResult.get(0)[0]) < sqlQuery.length){
				_dbAdapter.ExecuteSql("DROP TABLE IF EXISTS CHARA_TALK", null);
				_dbAdapter.ExecuteSql("CREATE TABLE IF NOT EXISTS CHARA_TALK (" +
									  "TALKID text, SEQ integer, TALK_TEXT_JPN text, TALK_TEXT_ENG text, " +
									  "primary key(SEQ))",
									  null);
				_dbAdapter.MultiExecuteSql(sqlQuery);
			}
		} catch (IOException e) {
			result = false;
		} finally{
			try{
				assetText.close();
			}catch (Exception e){
				return false;
			}
		}
		return result;
	}

	/**
	 * CHARA_TALKテーブルのレコードをランダムに1件取得
	 * @param chara
	 * @return
     */
	private String[] getRandom_CHARA_TALK(Chara chara){
		// トークIDに対応するレコードを取得する
		if (_recordCount == null){
			_sqlResult = _dbAdapter.SelectSql(
				"SELECT COUNT(*) FROM CHARA_TALK WHERE TALKID = ?",
				new String[] {_talkID.get(chara)}, 1);
			// レコード数を設定する
			_recordCount = Integer.parseInt(_sqlResult.get(0)[0]);
		}

		int searchNo = 1 + _random.nextInt(_recordCount - 1);
		_sqlResult = _dbAdapter.SelectSql(
				"SELECT TALK_TEXT_JPN, TALK_TEXT_ENG FROM CHARA_TALK WHERE SEQ = ?",
				new String[] {String.valueOf(searchNo)}, 2);

		if (_sqlResult.size() > 0){
			return _sqlResult.get(0);
		}else{
			return new String[]{"", ""};
		}
	}

	/**
	 * SETTINGテーブルの指定したIDの値を取得
	 * @param Id
	 * @return
     */
	private int get_SETTING(String Id){
		// IDに対応するレコードを取得する
		_sqlResult = _dbAdapter.SelectSql(
			"SELECT VALUE FROM SETTING WHERE ID = ?", new String[] {Id}, 1);

		return Integer.parseInt(_sqlResult.get(0)[0]);
	}

	/**
	 * SETTINGテーブルの指定したIDの値を取得（0以下はfalse、0より大きければture）
	 * @param Id
	 * @return
     */
	private boolean get_SETTING_Boolean(String Id){
		// IDに対応するレコードを取得する
		_sqlResult = _dbAdapter.SelectSql(
			"SELECT VALUE FROM SETTING WHERE ID = ?", new String[] {Id}, 1);

		if (Integer.parseInt(_sqlResult.get(0)[0]) <= 0){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * SETTING_REALテーブルの指定したIDの値を取得
	 * @param Id
	 * @return
     */
	private float get_SETTING_REAL(String Id){
		// IDに対応するレコードを取得する
		_sqlResult = _dbAdapter.SelectSql(
			"SELECT VALUE FROM SETTING_REAL WHERE ID = ?", new String[] {Id}, 1);

		return Float.parseFloat(_sqlResult.get(0)[0]);
	}

	/**
	 * SETTINGテーブルのSIZEデータの初期化
	 * @param insertValue
	 * @return
     */
	private boolean init_SIZE_In_SETTING(int insertValue){
		boolean result = true;
		// SIZE用レコードの総数を取得する
		_sqlResult = _dbAdapter.SelectSql(
			"SELECT COUNT(*) FROM SETTING WHERE ID = 'SIZE'", new String[] {}, 1);
		// レコードがない場合のみレコードを追加する
		if (Integer.parseInt(_sqlResult.get(0)[0]) == 0){
			result = _dbAdapter.ExecuteSql(
				"INSERT INTO SETTING(ID, VALUE) VALUES ('SIZE', ?)", new Object[]{insertValue});
		}
		return result;
	}

	/**
	 * SETTINGテーブルのDOORPOSITIONデータの初期化
	 * @param insertValue
	 * @return
     */
	private boolean init_DOORPOSITION_In_SETTING(int insertValue){
		boolean result = true;
		// DOORPOSITION用レコードの総数を取得する
		_sqlResult = _dbAdapter.SelectSql(
			"SELECT COUNT(*) FROM SETTING WHERE ID = 'DOORPOSITION'", new String[] {}, 1);
		// レコードがない場合のみレコードを追加する
		if (Integer.parseInt(_sqlResult.get(0)[0]) == 0){
			result = _dbAdapter.ExecuteSql(
				"INSERT INTO SETTING(ID, VALUE) VALUES ('DOORPOSITION', ?)", new Object[]{insertValue});
		}
		return result;
	}

	/**
	 * SETTINGテーブルのSAVINGENERGYデータの初期化
	 * @param insertValue
	 * @return
     */
	private boolean init_SAVINGENERGY_In_SETTING(int insertValue){
		boolean result = true;
		// SAVINGENERGY用レコードの総数を取得する
		_sqlResult = _dbAdapter.SelectSql(
			"SELECT COUNT(*) FROM SETTING WHERE ID = 'SAVINGENERGY'", new String[] {}, 1);
		// レコードがない場合のみレコードを追加する
		if (Integer.parseInt(_sqlResult.get(0)[0]) == 0){
			result = _dbAdapter.ExecuteSql(
				"INSERT INTO SETTING(ID, VALUE) VALUES ('SAVINGENERGY', ?)", new Object[]{insertValue});
		}
		return result;
	}

	/**
	 * SETTINGテーブルのTALKACTIONデータの初期化
	 * @param insertValue
	 * @return
     */
	private boolean init_TALKACTION_In_SETTING(int insertValue){
		boolean result = true;
		// TALKACTION用レコードの総数を取得する
		_sqlResult = _dbAdapter.SelectSql(
			"SELECT COUNT(*) FROM SETTING WHERE ID = 'TALKACTION'", new String[] {}, 1);
		// レコードがない場合のみレコードを追加する
		if (Integer.parseInt(_sqlResult.get(0)[0]) == 0){
			result = _dbAdapter.ExecuteSql(
				"INSERT INTO SETTING(ID, VALUE) VALUES ('TALKACTION', ?)", new Object[]{insertValue});
		}
		return result;
	}

	/**
	 * SETTINGテーブルのLANGUAGEデータの初期化
	 * @param insertValue
	 * @return
     */
	private boolean init_LANGUAGE_In_SETTING(int insertValue){
		boolean result = true;
		// LANGUAGE用レコードの総数を取得する
		_sqlResult = _dbAdapter.SelectSql(
			"SELECT COUNT(*) FROM SETTING WHERE ID = 'LANGUAGE'", new String[] {}, 1);
		// レコードがない場合のみレコードを追加する
		if (Integer.parseInt(_sqlResult.get(0)[0]) == 0){
			result = _dbAdapter.ExecuteSql(
				"INSERT INTO SETTING(ID, VALUE) VALUES ('LANGUAGE', ?)", new Object[]{insertValue});
		}
		return result;
	}

	/**
	 * SETTINGテーブルのTRANSPARENCYデータの初期化
	 * @param insertValue
	 * @return
     */
	private boolean init_TRANSPARENCY_In_SETTING(int insertValue){
		boolean result = true;
		// TRANSPARENCY用レコードの総数を取得する
		_sqlResult = _dbAdapter.SelectSql(
			"SELECT COUNT(*) FROM SETTING WHERE ID = 'TRANSPARENCY'", new String[] {}, 1);
		// レコードがない場合のみレコードを追加する
		if (Integer.parseInt(_sqlResult.get(0)[0]) == 0){
			result = _dbAdapter.ExecuteSql(
				"INSERT INTO SETTING(ID, VALUE) VALUES ('TRANSPARENCY', ?)", new Object[]{insertValue});
		}
		return result;
	}

	/**
	 * SETTING_REALテーブルのCOORDINATE_RANGEデータの初期化
	 * @param insertValue
	 * @return
     */
	private boolean init_COORDINATE_RANGE_In_SETTING_REAL(float insertValue){
		boolean result = true;
		// TRANSPARENCY用レコードの総数を取得する
		_sqlResult = _dbAdapter.SelectSql(
			"SELECT COUNT(*) FROM SETTING_REAL WHERE ID = 'COORDINATE_RANGE'", new String[] {}, 1);
		// レコードがない場合のみレコードを追加する
		if (Integer.parseInt(_sqlResult.get(0)[0]) == 0){
			result = _dbAdapter.ExecuteSql(
				"INSERT INTO SETTING_REAL(ID, VALUE) VALUES ('COORDINATE_RANGE', ?)", new Object[]{insertValue});
		}
		return result;
	}

	/**
	 * SETTING_REALテーブルのINTERVALデータの初期化
	 * @param insertValue
	 * @return
     */
	private boolean init_INTERVAL_In_SETTING_REAL(float insertValue){
		boolean result = true;
		// TRANSPARENCY用レコードの総数を取得する
		_sqlResult = _dbAdapter.SelectSql(
			"SELECT COUNT(*) FROM SETTING_REAL WHERE ID = 'INTERVAL'", new String[] {}, 1);
		// レコードがない場合のみレコードを追加する
		if (Integer.parseInt(_sqlResult.get(0)[0]) == 0){
			result = _dbAdapter.ExecuteSql(
				"INSERT INTO SETTING_REAL(ID, VALUE) VALUES ('INTERVAL', ?)", new Object[]{insertValue});
		}
		return result;
	}

	// 全ハッシュマップの初期化
	private void initAllHashmap(){
		// キャラクタIDをマッピング
		_charaId.clear();
		_charaId.put(Chara.Unity,    _context.getString(R.string.charaId_unity));
		_charaId.put(Chara.Droid,    _context.getString(R.string.charaId_droid));
		_charaId.put(Chara.Onepiece, _context.getString(R.string.charaId_onepiece));
		_charaId.put(Chara.Waso,     _context.getString(R.string.charaId_waso));
		_charaId.put(Chara.Cardigan, _context.getString(R.string.charaId_cardigan));
		_charaId.put(Chara.Frog,     _context.getString(R.string.charaId_frog));
		_charaId.put(Chara.Frograin, _context.getString(R.string.charaId_frogRain));
		_charaId.put(Chara.Jersey,   _context.getString(R.string.charaId_jersey));
		_charaId.put(Chara.Witch,    _context.getString(R.string.charaId_witch));
		_charaId.put(Chara.Original, _context.getString(R.string.charaId_original));

		// キャラクタの日本語名をマッピング
		_charaName.clear();
		_charaName.put(Chara.Unity,    _context.getString(R.string.charaName_unity));
		_charaName.put(Chara.Droid,    _context.getString(R.string.charaName_droid));
		_charaName.put(Chara.Onepiece, _context.getString(R.string.charaName_yucco));
		_charaName.put(Chara.Waso,     _context.getString(R.string.charaName_yucco));
		_charaName.put(Chara.Cardigan, _context.getString(R.string.charaName_yucco));
		_charaName.put(Chara.Frog,     _context.getString(R.string.charaName_frog));
		_charaName.put(Chara.Frograin, _context.getString(R.string.charaName_frog));
		_charaName.put(Chara.Jersey,   _context.getString(R.string.charaName_yucco));
		_charaName.put(Chara.Witch,    _context.getString(R.string.charaName_yucco));
		_charaName.put(Chara.Original, _context.getString(R.string.charaName_original1));

		// トークIDをマッピング
		_talkID.clear();
		_talkID.put(Chara.Unity,    _context.getString(R.string.talkId_unity));
		_talkID.put(Chara.Droid,    _context.getString(R.string.talkId_droid));
		_talkID.put(Chara.Onepiece, _context.getString(R.string.talkId_yucco));
		_talkID.put(Chara.Waso,     _context.getString(R.string.talkId_yucco));
		_talkID.put(Chara.Cardigan, _context.getString(R.string.talkId_yucco));
		_talkID.put(Chara.Frog,     _context.getString(R.string.talkId_frog));
		_talkID.put(Chara.Frograin, _context.getString(R.string.talkId_frog));
		_talkID.put(Chara.Jersey,   _context.getString(R.string.talkId_yucco));
		_talkID.put(Chara.Witch,    _context.getString(R.string.talkId_yucco));
		_talkID.put(Chara.Original, _context.getString(R.string.talkId_original1));

		// キャラクタのトーク内容インサート用テキストパスをマッピング
		_talkTextPath.clear();
		_talkTextPath.put(_context.getString(R.string.talkId_unity),     _sqlFileInsertCHARA_TALK.replace("{TALKID}", _context.getString(R.string.talkId_unity)));
		_talkTextPath.put(_context.getString(R.string.talkId_droid),     _sqlFileInsertCHARA_TALK.replace("{TALKID}", _context.getString(R.string.talkId_droid)));
		_talkTextPath.put(_context.getString(R.string.talkId_yucco),     _sqlFileInsertCHARA_TALK.replace("{TALKID}", _context.getString(R.string.talkId_yucco)));
		_talkTextPath.put(_context.getString(R.string.talkId_frog),      _sqlFileInsertCHARA_TALK.replace("{TALKID}", _context.getString(R.string.talkId_frog)));
		_talkTextPath.put(_context.getString(R.string.talkId_original1), _sqlFileInsertCHARA_TALK.replace("{TALKID}", _context.getString(R.string.talkId_original1)));
	}

	private static enum Chara{
		Unity,
		Droid,
		Onepiece,
		Waso,
		Cardigan,
		Frog,
		Frograin,
		Jersey,
		Witch,
		Original
	}
}
