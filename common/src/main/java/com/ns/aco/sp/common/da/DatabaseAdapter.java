package com.ns.aco.sp.common.da;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Fragment;
import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter extends Fragment {
    // データベース用のオブジェクトを格納するフィールド変数
	private SQLiteDatabase _dbObjectReader;
	private SQLiteDatabase _dbObjectWriter;
    private DatabaseHelper _databaseHelper;
    //private final Context _context;

	// DB使用の初期処理
	public DatabaseAdapter(){
	}

//    // DB使用の初期処理
//    public DatabaseAdapter(Context context, String DBName, int DBVersion){
//        //this._context = context;
//        _databaseHelper = new DatabaseHelper(context, DBName, DBVersion);
//	}

    // DBオープン
    public void open(Context context, String DBName, int DBVersion){
		if (_databaseHelper == null){
			_databaseHelper = new DatabaseHelper(context, DBName, DBVersion);
		}
		_dbObjectWriter = _databaseHelper.getWritableDatabase();
		_dbObjectReader = _databaseHelper.getReadableDatabase();
    }
    
    // DBクローズ
    public void close() {
    	_databaseHelper.close();
    }

    // SELECT SQL実行関数
    // 実行したSQLで取得した結果が1件以上あればtrueを返す
    public Boolean ExistsSelectSql(String selectSql, String[] bindParams){
    	Cursor cursor = _dbObjectReader.rawQuery(selectSql, bindParams);
        if (cursor.getCount() >= 1) {
        	return true;
        }else{
        	return false;        	
        }
    }
        
    // SELECT SQL実行関数
    // getCollumCountで指定した項目数の要素を持つ配列をArrayListで返す
    // SQL内の「?」はバインドパラメータ（bindParams）の値に置き換える
    // getCollumCountはSQLで取得する項目数
    public ArrayList<String[]> SelectSql(String selectSql, String[] bindParams, int getCollumCount){
//    	// SQL文発行
//    	StringBuilder stringBuild = new StringBuilder();
//    	stringBuild.append(" SELECT *");
//    	stringBuild.append(" FROM " + DB_TABLE1);
    	ArrayList<String[]> resultList = new ArrayList<String[]>();
    	Cursor cursor = _dbObjectReader.rawQuery(selectSql, bindParams);

        if (cursor.getCount() >= 1) {
			// ループの最初に1動かすため、-1から始める
        	cursor.moveToPosition(-1);
			while (cursor.moveToNext()){
	        	String[] resultRow = new String[getCollumCount];
				for (int j = 0; j <= getCollumCount - 1; j++){
					resultRow[j] = cursor.getString(j);
				}
				resultList.add(resultRow);
			}
        }
        return resultList;
    }
    
    // SELECT SQL実行関数
    // getCollumCountで指定した項目数の要素を持つ配列をArrayListで返す
    // SQL内の「?」はバインドパラメータ（bindParams）の値に置き換える
    // getCollumCountはSQLで取得する項目数
    public ArrayList<String[]> SelectSql(String selectSql, int[] bindParams, int getCollumCount){

    	ArrayList<String[]> resultList = new ArrayList<String[]>();

    	for(int bindParam : bindParams){
    		// 正規表現を解除して置き換えを行う
        	Pattern pattern = Pattern.compile("\\?");
        	Matcher matcher = pattern.matcher(selectSql);
    		selectSql = matcher.replaceFirst(String.valueOf(bindParam));
    	}
    	
    	Cursor cursor = _dbObjectReader.rawQuery(selectSql, null);

        if (cursor.getCount() >= 1) {
        	// ループの最初に1動かすため、-1から始める
        	cursor.moveToPosition(-1);
			while (cursor.moveToNext()){
	        	String[] resultRow = new String[getCollumCount];
				for (int j = 0; j <= getCollumCount - 1; j++){
					resultRow[j] = cursor.getString(j);
				}
				resultList.add(resultRow);
			}
        }
        return resultList;
    }

	// SELECT SQL実行関数
	// getCollumCountで指定した項目数の要素を持つ配列をArrayListで返す
	// SQL内の「?」はバインドパラメータ（bindParams）の値に置き換える
	// 汎用性を高めるためCursorを返す
	public Cursor SelectSql(String selectSql, Object[] bindParams){

		ArrayList<byte[][]> resultList = new ArrayList<byte[][]>();

		for(Object bindParam : bindParams){
			// 正規表現を解除して置き換えを行う
			Pattern pattern = Pattern.compile("\\?");
			Matcher matcher = pattern.matcher(selectSql);
			selectSql = matcher.replaceFirst(String.valueOf(bindParam));
		}

		return  _dbObjectReader.rawQuery(selectSql, null);
	}

    //SQL一括実行
    public boolean MultiExecuteSql(String[] executeSql){ 
    	_dbObjectWriter.beginTransaction();
    	try{
        	for (int i = 0; i <= executeSql.length - 1; i++) {
        		_dbObjectWriter.execSQL(executeSql[i], new Object[]{});
    		}     	
    		_dbObjectWriter.setTransactionSuccessful();
    		return true;
    	}catch(Exception ex){
    		return false;
    	}finally{
    		_dbObjectWriter.endTransaction();  
    	}
    }  
    
    //SQL一括実行
    public boolean MultiExecuteSql(String[] executeSql, ArrayList<Object[]> bindParams){ 
        boolean bindSwitch = true;
        
        if (bindParams == null){
        	bindSwitch = false;
        }       
    	_dbObjectWriter.beginTransaction(); 	
    	
    	try{
        	for (int i = 0; i <= executeSql.length - 1; i++) {
        		// パラメータの有無によりSQLの実行引数を変更
        		if (bindSwitch){
        			if (i <= bindParams.size() - 1){
        				_dbObjectWriter.execSQL(executeSql[i], bindParams.get(i));
        			}else{
        				_dbObjectWriter.execSQL(executeSql[i], new Object[]{});
        				bindSwitch = false;
        			}
        		}else{
        			_dbObjectWriter.execSQL(executeSql[i], new Object[]{});
        		}      		
    		}
    		_dbObjectWriter.setTransactionSuccessful();
    		return true;
    	}catch(Exception ex){
    		return false;
       	}finally{
    		_dbObjectWriter.endTransaction();  
    	}
    }  
    
    // INSERT || UPDATE || DELETE SQL実行関数
    // SQL内の「?」はバインドパラメータ（bindParams）の値に置き換える
    public boolean ExecuteSql(String executeSql, Object[] bindParams){
    	//      // レコードを作成
//    	StringBuilder stringBuild = new StringBuilder();
//    	stringBuild.append(" INSERT INTO " + DB_TABLE1);
//    	stringBuild.append(" ( bookname ) VALUES ( ");
//    	stringBuild.append("'" + bookName + "'");
//    	stringBuild.append(" ) ");
    	try{
    		if (bindParams == null){
    			_dbObjectWriter.execSQL(executeSql, new Object[]{});
    		}else{
    			_dbObjectWriter.execSQL(executeSql, bindParams);
    		}
    		return true;
    	}catch(Exception ex){
    		return false;
    	}
    }
    
    // ヘルパークラスの定義
    private static class DatabaseHelper extends SQLiteOpenHelper {
        // データベース名を定数に登録
        //private final static String DB_NAME = "hacoNiWA.db";
        // データベースのバージョンを登録
        //private final static int DB_VERSION = 1;
    	// テーブル作成用SQL文
    	//private String _firstExecSql[];
    	
    	// データベースを作成、または開く、管理するための処理
        public DatabaseHelper(Context context, String DBName, int DBVersion) {
    	    // ヘルパークラスクラスのコンストラクターの呼び出し        	
            super(context, DBName, null, DBVersion);
        }
        
        // テーブルを作成するメソッドの定義
        @Override
        public void onCreate(SQLiteDatabase sqliteDB) {
        }

        // データベースをアップグレードするメソッドの定義
        @Override
        public void onUpgrade(SQLiteDatabase sqliteDB, int oldVersion,int newVersion) {
        	// ALTER TABLEでテーブル定義を変える方法を検討する
//	    // 古いバージョンのテーブルが存在する場合はこれを削除
//            sqliteDB.execSQL("DROP TABLE IF EXISTS "+DB_TABLE1);
//            sqliteDB.execSQL("DROP TABLE IF EXISTS "+DB_TABLE2);
//	    // 新規テーブルの作成
//            onCreate(sqliteDB);
        }
    }
}