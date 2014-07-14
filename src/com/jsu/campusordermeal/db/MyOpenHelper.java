package com.jsu.campusordermeal.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyOpenHelper extends SQLiteOpenHelper{

	//需要context参数和数据库路径
	public MyOpenHelper(Context context, String name) {
		super(context, name, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.v("TAG", "onCreate方法");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
