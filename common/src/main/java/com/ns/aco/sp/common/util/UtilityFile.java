package com.ns.aco.sp.common.util;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class UtilityFile {

	// ディレクトリ内のすべてのファイルを削除する
    public static void delete(File directory){
        if(directory.exists() == false) {
            return;
        }else if(directory.isFile()) {
       	 directory.delete();
        }else{
            File[] files = directory.listFiles();
            for(File file: files) {
                delete(file);
            }
            directory.delete();
        }
    }

    // ファイルコピー
    public static void copyFile(File inputFile, File outputFile){
        try{
            InputStream input = new FileInputStream(inputFile);
            OutputStream output = new FileOutputStream(outputFile);

            int DEFAULT_BUFFER_SIZE = 1024 * 4;
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int size = -1;

            while ((size = input.read(buffer)) != -1) {
                output.write(buffer, 0, size);
            }
            input.close();
            output.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // ファイルコピー
    public static void copyFile(Context context, Uri inputFile, File outputFile){
        try{
            InputStream inStream = context.getContentResolver().openInputStream(inputFile);
            OutputStream outStream = new FileOutputStream(outputFile);

            int DEFAULT_BUFFER_SIZE = 1024 * 4;
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int size = -1;

            while ((size = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, size);
            }
            inStream.close();
            outStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
