package com.jsu.campusordermeal.test;

import java.util.HashMap;
import java.util.Map;

import com.jsu.campusordermeal.service.DataService;
import com.jsu.campusordermeal.util.HttpUtil;

import android.test.AndroidTestCase;

public class TestHttpUtil extends AndroidTestCase {

	public void testPostRequest() throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "zuo");
		map.put("password", "1234");
		String result = DataService.sendDataByHttpClientPost("http://10.0.0.20:8080/CampusOrderMeal/studentAction_loginUI.action", "zuo","1234");
		System.out.print("result: "+result);
	}
}
