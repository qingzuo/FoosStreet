package com.jsu.campusordermeal.test;

import java.util.ArrayList;

import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.service.GetFoodInfoFromXml;

import android.content.res.AssetManager;
import android.test.AndroidTestCase;
import android.util.Log;

public class TestGetFoodInfoFromXml extends AndroidTestCase {

	private static final String TAG = "TestFoodInfoOfTab1Data";

	public void testGetFoodInfos() throws Exception{
		ArrayList<FoodInfo> data = GetFoodInfoFromXml.getFoodInfos(getContext(), "");
		for (FoodInfo food: data){
			System.out.println("id"+food.get_id());
			System.out.println("name"+food.getName());
			System.out.println("icon"+food.getIconPath());
			System.out.println("price"+food.getPrice());
			System.out.println("characteristic"+food.getCharacteristic());
			System.out.println("source"+food.getSource());
			System.out.println("brief_introduction"+food.getBrief_introduction());
			System.out.println("detailed_introduction"+food.getDetailed_introduction());
			System.out.println("start"+food.getStart());
			System.out.println("good_reputation"+food.getGood_reputation());
			System.out.println("middle_reputation"+food.getMiddle_reputation());
			System.out.println("bad_reputation"+food.getBad_reputation());
			System.out.println("making"+food.getMaking());
			System.out.println("need_time"+food.getNeed_time());
			System.out.println("order_time"+food.getOrder_time());
			System.out.println("-------------------------------------------------");
		}
	}
}
