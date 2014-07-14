package com.jsu.campusordermeal.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jsu.campusordermeal.dao.FoodInfo;

import android.R.bool;
import android.content.res.Resources.Theme;
import android.util.Log;

public class JsonDownloadUtil {
	/**
	 * 获取"对象形式"的JSON数据，
	 * 数据形式：{"total":2,"success":true,"arrayData":[{"id":1,"name"
	 * :"小猪"},{"id":2,"name":"小猫"}]}
	 * 
	 * @param path
	 *            网页路径
	 * @return 返回List
	 * @throws Exception
	 */
	public static List<FoodInfo> getFoodInfo(String path)
			throws Exception {
		List<FoodInfo> list = new ArrayList<FoodInfo>();
		FoodInfo foodInfo = null;
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();// 利用HttpURLConnection对象,我们可以从网络中获取网页数据.
		conn.setConnectTimeout(5 * 1000); // 单位是毫秒，设置超时时间为5秒
		conn.setRequestMethod("GET"); // HttpURLConnection是通过HTTP协议请求path路径的，所以需要设置请求方式,可以不设置，因为默认为GET
		if (conn.getResponseCode() == 200) {// 判断请求码是否是200码，否则失败
			InputStream is = conn.getInputStream(); // 获取输入流
			byte[] data = readStream(is); // 把输入流转换成字符数组
			String json = new String(data); // 把字符数组转换成字符串

			JSONObject jsonObject = new JSONObject(json); // 返回的数据形式是一个Object类型，所以可以直接转换成一个Object
			boolean result = jsonObject.getBoolean("result");

			JSONArray jsonArray = jsonObject.getJSONArray("goodsList");// 里面有一个数组数据，可以用getJSONArray获取数组
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject item = jsonArray.getJSONObject(i); // 得到每个对象
				int id = item.getInt("id");
				String detailedIntroduction = item.getString("detailedIntroduction");
				String material = item.getString("material");
				String name = item.getString("name");
				String simpleIntroduction = item.getString("simpleIntroduction");
				String speciality = item.getString("speciality");
				String time = item.getString("time");
				double price = item.getDouble("price");
				double price2 = item.getDouble("price2");
				int sellerId = item.getInt("sellerId");
				int num = item.getInt("num");
				int fiveStar = item.getInt("fiveStar");
				int fourStar = item.getInt("fourStar");
				int threeStar = item.getInt("threeStar");
				int twoStar = item.getInt("twoStar");
				int oneStar = item.getInt("oneStar");
				String logoPicture = item.getString("logoPicture");

				foodInfo = new FoodInfo();
				foodInfo.set_id(id);
				foodInfo.setName(name);
				foodInfo.setIconPath(logoPicture);
				foodInfo.setPrice((float)price);
				foodInfo.setCharacteristic(speciality);
				foodInfo.setSource(""+sellerId);
				foodInfo.setBrief_introduction(simpleIntroduction);
				foodInfo.setDetailed_introduction(detailedIntroduction);
				foodInfo.setStart((float)twoStar);
				foodInfo.setGood_reputation(fiveStar);
				foodInfo.setMiddle_reputation(fourStar);
				foodInfo.setBad_reputation(threeStar);
				foodInfo.setMaking(material);
				foodInfo.setNeed_time(9);
				foodInfo.setOrder_time(num);
				foodInfo.setSpecial_offer((float)price2);
				list.add(foodInfo);
			}
		}

		return list;
	}

	public static Map<Integer, String> getImages(String path)
			throws Exception {
		Map<Integer, String> map = new HashMap<Integer, String>();
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();// 利用HttpURLConnection对象,我们可以从网络中获取网页数据.
		conn.setConnectTimeout(5 * 1000); // 单位是毫秒，设置超时时间为5秒
		conn.setRequestMethod("GET"); // HttpURLConnection是通过HTTP协议请求path路径的，所以需要设置请求方式,可以不设置，因为默认为GET
		if (conn.getResponseCode() == 200) {// 判断请求码是否是200码，否则失败
			InputStream is = conn.getInputStream(); // 获取输入流
			byte[] data = readStream(is); // 把输入流转换成字符数组
			String json = new String(data); // 把字符数组转换成字符串

			JSONObject jsonObject = new JSONObject(json); // 返回的数据形式是一个Object类型，所以可以直接转换成一个Object
			boolean result = jsonObject.getBoolean("result");

			JSONArray jsonArray = jsonObject.getJSONArray("pictureList");// 里面有一个数组数据，可以用getJSONArray获取数组
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject item = jsonArray.getJSONObject(i); // 得到每个对象
				int pid = item.getInt("id");
				String imagePath = item.getString("path");
				System.out.println("id:"+pid);
				System.out.println("imagePath:"+imagePath);
				
				map.put(pid, imagePath);
			}
		}

		return map;
	}


	/**
	 * 获取类型复杂的JSON数据 数据形式： {"name":"小猪", "age":23,
	 * "content":{"questionsTotal":2, "questions": [ { "question":
	 * "what's your name?", "answer": "小猪"},{"question": "what's your age",
	 * "answer": "23"}] } }
	 * 
	 * @param path
	 *            网页路径
	 * @return 返回List
	 * @throws Exception
	 */
	public static List<Map<String, String>> getJSON(String path)
			throws Exception {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();// 利用HttpURLConnection对象,我们可以从网络中获取网页数据.
		conn.setConnectTimeout(5 * 1000); // 单位是毫秒，设置超时时间为5秒
		conn.setRequestMethod("GET"); // HttpURLConnection是通过HTTP协议请求path路径的，所以需要设置请求方式,可以不设置，因为默认为GET
		if (conn.getResponseCode() == 200) {// 判断请求码是否是200码，否则失败
			InputStream is = conn.getInputStream(); // 获取输入流
			byte[] data = readStream(is); // 把输入流转换成字符数组
			String json = new String(data); // 把字符数组转换成字符串

			/*
			 * 数据形式： {"name":"小猪", "age":23, "content":{"questionsTotal":2,
			 * "questions": [ { "question": "what's your name?", "answer":
			 * "小猪"},{"question": "what's your age", "answer": "23"}] } }
			 */
			JSONObject jsonObject = new JSONObject(json); // 返回的数据形式是一个Object类型，所以可以直接转换成一个Object
			String name = jsonObject.getString("name");
			int age = jsonObject.getInt("age");
			Log.i("abc", "name:" + name + " | age:" + age); // 测试数据

			JSONObject contentObject = jsonObject.getJSONObject("content"); // 获取对象中的对象
			String questionsTotal = contentObject.getString("questionsTotal"); // 获取对象中的一个值
			Log.i("abc", "questionsTotal:" + questionsTotal); // 测试数据

			JSONArray contentArray = contentObject.getJSONArray("questions"); // 获取对象中的数组
			for (int i = 0; i < contentArray.length(); i++) {
				JSONObject item = contentArray.getJSONObject(i); // 得到每个对象
				String question = item.getString("question"); // 获取对象对应的值
				String answer = item.getString("answer");

				map = new HashMap<String, String>(); // 存放到MAP里面
				map.put("question", question);
				map.put("answer", answer);
				list.add(map);
			}
		}

		// ***********测试数据******************

		for (Map<String, String> list2 : list) {
			String question = list2.get("question");
			String answer = list2.get("answer");
			Log.i("abc", "question:" + question + " | answer:" + answer);
		}
		return list;
	}

	/**
	 * 把输入流转换成字符数组
	 * 
	 * @param inputStream
	 *            输入流
	 * @return 字符数组
	 * @throws Exception
	 */
	public static byte[] readStream(InputStream inputStream) throws Exception {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			bout.write(buffer, 0, len);
		}
		bout.close();
		inputStream.close();

		return bout.toByteArray();
	}
}
