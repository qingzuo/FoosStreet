package com.jsu.campusordermeal.service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.jsu.campusordermeal.dao.FoodInfo;

import android.R.integer;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.Xml;

public class GetFoodInfoFromXml {
	private static final String TAG = "FoodInfosOfTab1Data";

	public static ArrayList<FoodInfo> getFoodInfos(Context context, String url) throws Exception{
		ArrayList<FoodInfo> data = null;
		FoodInfo foodInfo = null;
		
		AssetManager am = context.getAssets();
		InputStream is = am.open("FoodInfos.xml");

		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		
		//tem
		int imageId = 1;
		
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				data = new ArrayList<FoodInfo>();
				break;
			case XmlPullParser.START_TAG:
				if ("foodinfo".equals(parser.getName())){
					foodInfo = new FoodInfo();
				}else if (parser.getName().equals("id")) {
					eventType = parser.next();
					foodInfo.set_id(Integer.parseInt(parser.getText()));
				}else if (parser.getName().equals("name")) {
					eventType = parser.next();
					foodInfo.setName(parser.getText());
				} else if (parser.getName().equals("iconpath")) {
					eventType = parser.next();
					String iconUrl = parser.getText();
					//Bitmap bm =  DownloadBitmap.getBitmap(iconUrl);
					foodInfo.setIconPath(foodInfo.get_id()+"_1.jpg");
				} else if (parser.getName().equals("price")) {
					eventType = parser.next();
					foodInfo.setPrice(Float.parseFloat(parser.getText()));
				}else if (parser.getName().equals("characteristic")) {
					eventType = parser.next();
					foodInfo.setCharacteristic(parser.getText());
				}else if (parser.getName().equals("source")) {
					eventType = parser.next();
					foodInfo.setSource(parser.getText());
				}else if (parser.getName().equals("brief_introduction")) {
					eventType = parser.next();
					foodInfo.setBrief_introduction(parser.getText());
				}else if (parser.getName().equals("detailed_introduction")) {
					eventType = parser.next();
					foodInfo.setDetailed_introduction(parser.getText());
				}else if (parser.getName().equals("start")) {
					eventType = parser.next();
					foodInfo.setStart(Float.parseFloat(parser.getText()));
				}else if (parser.getName().equals("good_reputation")) {
					eventType = parser.next();
					foodInfo.setGood_reputation(Integer.parseInt(parser.getText()));
				}else if (parser.getName().equals("middle_reputation")) {
					eventType = parser.next();
					foodInfo.setMiddle_reputation(Integer.parseInt(parser.getText()));
				}else if (parser.getName().equals("bad_reputation")) {
					eventType = parser.next();
					foodInfo.setBad_reputation(Integer.parseInt(parser.getText()));
				}else if (parser.getName().equals("making")) {
					eventType = parser.next();
					foodInfo.setMaking(parser.getText());
				}else if (parser.getName().equals("need_time")) {
					eventType = parser.next();
					foodInfo.setNeed_time(Integer.parseInt(parser.getText()));
				}else if (parser.getName().equals("order_time")) {
					eventType = parser.next();
					foodInfo.setOrder_time(Integer.parseInt(parser.getText()));
				}
				break;
			case XmlPullParser.END_TAG:
				if ("foodinfo".equals(parser.getName())){
					data.add(foodInfo);
					foodInfo = null;
				}
				break;
			}
			eventType = parser.next();
		}
		
		return data;
	}
}
