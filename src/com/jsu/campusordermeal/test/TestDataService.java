package com.jsu.campusordermeal.test;

import java.util.HashMap;
import java.util.Map;

import com.jsu.campusordermeal.dao.UserInfo;
import com.jsu.campusordermeal.service.DataService;

import android.R.bool;
import android.test.AndroidTestCase;
import android.util.Log;

public class TestDataService extends AndroidTestCase {

	private static final String TAG = "TestDataService";

	public void testSendDataByHttpClientPostForRegister() throws Exception{
		Map<String, String> parames = new HashMap<String, String>();
		parames.put("name", "test");
		parames.put("phone", "15274420593");
		parames.put("email", "467275051@qq.com");
		parames.put("password", "1234");
		
		String result = DataService.sendDataByHttpClientPostForRegister("http://192.168.1.100:8080/CampusOrderMeal/studentAction_add.action", parames, "/mnt/sdcard/logo_seller.png");
		Log.i(TAG, "result = "+result );
	}
	
	public void testSendDataByPost() throws Exception{
		UserInfo info = DataService.sendDataByPost("http://192.168.1.100:8080/CampusOrderMeal/studentAction_login.action", "new", "1234");
		if (info != null ) Log.i(TAG, ""+info.getEmail());
	}
}
