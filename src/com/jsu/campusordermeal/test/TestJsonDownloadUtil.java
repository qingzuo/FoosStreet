package com.jsu.campusordermeal.test;

import java.util.List;
import java.util.Map;

import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.util.JsonDownloadUtil;

import android.test.AndroidTestCase;

public class TestJsonDownloadUtil extends AndroidTestCase {

	public void testGetScheduleInfo() throws Exception{
		List<FoodInfo> list = JsonDownloadUtil.getFoodInfo("http://192.168.1.100:8080/CampusOrderMeal/goodsAction_list2.action");
		for (FoodInfo foodInfo:list){
			System.out.println("id"+foodInfo.get_id());
			System.out.println("id"+foodInfo.getBad_reputation());
			System.out.println("id"+foodInfo.getBrief_introduction());
			System.out.println("id"+foodInfo.getCharacteristic());
			System.out.println("id"+foodInfo.getDetailed_introduction());
			System.out.println("id"+foodInfo.getGood_reputation());
			System.out.println("getIconPath :"+foodInfo.getIconPath());
			System.out.println("id"+foodInfo.getMaking());
			System.out.println("id"+foodInfo.getMiddle_reputation());
			System.out.println("id"+foodInfo.getName());
			System.out.println("id"+foodInfo.getNeed_time());
			System.out.println("id"+foodInfo.getOrder_time());
			System.out.println("id"+foodInfo.getPrice());
		}
	}
	
	public void testGetImages() throws Exception{
		Map<Integer, String> map = JsonDownloadUtil.getImages("http://192.168.1.100:8080/CampusOrderMeal/goodsAction_getGoodsPictures.action?id=1");
		for (int id:map.keySet()){
			System.out.println(map.get(id));
		}
	}
}
