package com.jsu.campusordermeal.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jsu.campusordermeal.dao.FoodInfo;

public class ObjectToJson {

	public static String List2Json(List<FoodInfo> list) {

		try {
			JSONArray headParams = new JSONArray();
			JSONObject headParam1 = null;
			for (FoodInfo foodInfo : list) {
				headParam1 = new JSONObject();

				headParam1.put("id", foodInfo.get_id());
				headParam1.put("name", foodInfo.getName());
				headParam1.put("iconPath", foodInfo.getIconPath());
				headParam1.put("price", foodInfo.getPrice());
				headParam1.put("characteristic", foodInfo.getCharacteristic());
				headParam1.put("source", foodInfo.getSource());
				headParam1.put("brief_introduction",
						foodInfo.getBrief_introduction());
				headParam1.put("detailed_introduction",
						foodInfo.getDetailed_introduction());
				headParam1.put("start", foodInfo.getStart());
				headParam1
						.put("good_reputation", foodInfo.getGood_reputation());
				headParam1.put("middle_reputation",
						foodInfo.getMiddle_reputation());
				headParam1.put("bad_reputation", foodInfo.getBad_reputation());
				headParam1.put("making", foodInfo.getMaking());
				headParam1.put("need_time", foodInfo.getNeed_time());
				headParam1.put("order_time", foodInfo.getOrder_time());
				headParam1.put("special_offer", foodInfo.getSpecial_offer());

				headParams.put(headParam1);
			}
			JSONObject json = new JSONObject();
			json.put("userId", 1);
			json.put("userName", "zuo");
			json.put("userPhone", "15274420593");
			json.put("goods", headParams);
			json.put("success", true);
			json.put("SellerId", 1);
			return json.toString();
		} catch (Exception e) {
		}

		return null;
	}
}
