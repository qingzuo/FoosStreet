package com.jsu.campusordermeal.service;

import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

public class CopyAssetsFileToSDCard {
	
	public static void copyDBToSdCard(Context context, String from, String to) throws Exception{
//		String to = Environment.getExternalStorageDirectory()+"/MaxWellOA/db/maxwelloa.db";
//		String from = "maxwelloa.db";
		AssetManager assetManager = context.getAssets();
		InputStream is = assetManager.open(from);
		FileOutputStream out = new FileOutputStream(to);
		byte[] buff = new byte[1024];
		int total = -1;
		while ( (total=is.read(buff)) != -1){
			out.write(buff, 0, total);
		}
		out.close();
		is.close();
	}

	public static void copyFile(Context context, String from, String to) throws Exception{
		AssetManager assetManager = context.getAssets();
		InputStream is = assetManager.open(from);
		FileOutputStream out = new FileOutputStream(to);
		byte[] buff = new byte[1024];
		int total = -1;
		while ( (total=is.read(buff)) != -1){
			out.write(buff, 0, total);
		}
		out.close();
		is.close();
	}
}
