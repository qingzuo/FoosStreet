package com.jsu.campusordermeal.test;

import java.util.ArrayList;

import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.db.DBOperate;


import com.jsu.campusordermeal.service.ImageOperate;

import android.graphics.Bitmap;
import android.test.AndroidTestCase;

public class TestImageOperate extends AndroidTestCase {
	
	public void testGetImageFromExternalStorage() throws Exception{
		ArrayList<FoodInfo> data = DBOperate.getDataFromDB(getContext());
		Bitmap bitmap = ImageOperate.getImageFromExternalStorage(getContext(), "zxy.jpg");
		System.out.println(data.get(1).getIconPath()+"---"+bitmap.getWidth()+":"+bitmap.getHeight());
	}
	
	public void testGetBitmapArraysByFoodId() throws Exception {
		ArrayList<Bitmap> data = ImageOperate.getBitmapArraysByFoodId(getContext(), 100);
		assertEquals(5, data.size());
		
	}
}
