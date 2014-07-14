package com.jsu.campusordermeal.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.jsu.campusordermeal.R;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class ImageOperate {
	
	public static Bitmap getImageFromExternalStorage(Context context,
			String name) throws Exception {
		// String imagePath =
		// Environment.getExternalStorageDirectory().getAbsolutePath() +
		// "/CampusOrderMeal/images/"+name;;
		// InputStream is = new FileInputStream(new File(imagePath));
		AssetManager manager = context.getResources().getAssets();
		InputStream is = manager.open("images/" + name);
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		options.inPurgeable = true;  
		options.inInputShareable = true;
		return BitmapFactory.decodeStream(is,null,options);
	}
	
	public static ArrayList<Bitmap> getBitmapArraysByFoodId(Context context, int id){
		ArrayList<Bitmap> data = new ArrayList<Bitmap>();
		
		AssetManager manager = context.getResources().getAssets();
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		options.inPurgeable = true;  
		options.inInputShareable = true;
		InputStream is = null;
		for (int i=1; true; i++){
			String name = id+"_"+i+".jpg";
//System.out.println("---name:"+name);
			try {
				is = manager.open("images/" + name);
			} catch (IOException e) {
				break;
			}
			data.add(BitmapFactory.decodeStream(is,null,options));
		}
		
		return data;
	}
	
	public static ArrayList<Bitmap> getBigBitmapArraysByFoodId(Context context, int id){
		ArrayList<Bitmap> data = new ArrayList<Bitmap>();
		
		AssetManager manager = context.getResources().getAssets();
		InputStream is = null;
		for (int i=1; true; i++){
			String name = id+"_"+i+".jpg";
//System.out.println("---name:"+name);
			try {
				is = manager.open("images/" + name);
			} catch (IOException e) {
				break;
			}
			data.add(BitmapFactory.decodeStream(is));
		}
		
		return data;
	}
}
