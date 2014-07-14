package com.jsu.campusordermeal.test;

import java.util.List;

import org.json.JSONObject;

import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.util.ObjectToJson;
import com.jsu.campusordermeal.util.UserFormManage;

import android.test.AndroidTestCase;

public class TestJsonPackagingUtil extends AndroidTestCase {

	public void testObject2json() throws Exception{
		List<FoodInfo> list = UserFormManage.getFoods(getContext());
		System.out.println(list.size());
		String result = ObjectToJson.List2Json(list);
		System.out.println(result);
	}
}
