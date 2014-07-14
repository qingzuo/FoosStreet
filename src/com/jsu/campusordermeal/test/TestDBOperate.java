package com.jsu.campusordermeal.test;

import java.util.ArrayList;

import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.db.DBOperate;

import android.test.AndroidTestCase;
import android.util.Log;

import junit.framework.TestCase;

public class TestDBOperate extends AndroidTestCase  {

	public void testGetDataFromDB() throws Exception{
		ArrayList<FoodInfo> data = DBOperate.getDataFromDB(getContext());
		for (FoodInfo food : data){
			Log.i("testGetDataFromDB", "id"+food.get_id());
			Log.i("testGetDataFromDB", "name"+food.getName());
			Log.i("testGetDataFromDB", "icon"+food.getIconPath());
			Log.i("testGetDataFromDB", "price"+food.getPrice());
			Log.i("testGetDataFromDB", "characteristic"+food.getCharacteristic());
			Log.i("testGetDataFromDB", "source"+food.getSource());
			Log.i("testGetDataFromDB", "brief_introduction"+food.getBrief_introduction());
			Log.i("testGetDataFromDB", "detailed_introduction"+food.getDetailed_introduction());
			Log.i("testGetDataFromDB", "start"+food.getStart());
			Log.i("testGetDataFromDB", "good_reputation"+food.getGood_reputation());
			Log.i("testGetDataFromDB", "middle_reputation"+food.getMiddle_reputation());
			Log.i("testGetDataFromDB", "bad_reputation"+food.getBad_reputation());
			Log.i("testGetDataFromDB", "making"+food.getMaking());
			Log.i("testGetDataFromDB", "need_time"+food.getNeed_time());
			Log.i("testGetDataFromDB", "order_time"+food.getOrder_time());
			Log.i("testGetDataFromDB", "-------------------------------------------------");
		}
	}
	
	public void testInsertData2DB() throws Exception{
		DBOperate.insertData2DB(getContext());
		ArrayList<FoodInfo> data = DBOperate.getDataFromDB(getContext());
		System.out.println("size:"+data.size());
	}
	
	public void testCheckUserInfo() throws Exception{
		/*String result = DBOperate.checkUserInfo(getContext(), "ÖÜ½ÜÂ×", "123");
		assertEquals(1, result);*/
	}
	
	public void testGetTypeFoodDataFromDB() throws Exception{
		ArrayList<FoodInfo> data = DBOperate.getTypeFoodDataFromDB(getContext(), "»ð¹ø");
		System.out.println("food name "+data.get(0).getName()+";"+data.size());
	}
	
	public void testInsertUserInfo2DB() throws Exception{
		DBOperate.insertUserInfo2DB(getContext(), "zuo", "123", "wlh");
		String result = DBOperate.checkUserInfo(getContext(), "zuo", "123");
		assertEquals("wlh", result);
	}
}
