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
	 * ��ȡ"������ʽ"��JSON���ݣ�
	 * ������ʽ��{"total":2,"success":true,"arrayData":[{"id":1,"name"
	 * :"С��"},{"id":2,"name":"Сè"}]}
	 * 
	 * @param path
	 *            ��ҳ·��
	 * @return ����List
	 * @throws Exception
	 */
	public static List<FoodInfo> getFoodInfo(String path)
			throws Exception {
		List<FoodInfo> list = new ArrayList<FoodInfo>();
		FoodInfo foodInfo = null;
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();// ����HttpURLConnection����,���ǿ��Դ������л�ȡ��ҳ����.
		conn.setConnectTimeout(5 * 1000); // ��λ�Ǻ��룬���ó�ʱʱ��Ϊ5��
		conn.setRequestMethod("GET"); // HttpURLConnection��ͨ��HTTPЭ������path·���ģ�������Ҫ��������ʽ,���Բ����ã���ΪĬ��ΪGET
		if (conn.getResponseCode() == 200) {// �ж��������Ƿ���200�룬����ʧ��
			InputStream is = conn.getInputStream(); // ��ȡ������
			byte[] data = readStream(is); // ��������ת�����ַ�����
			String json = new String(data); // ���ַ�����ת�����ַ���

			JSONObject jsonObject = new JSONObject(json); // ���ص�������ʽ��һ��Object���ͣ����Կ���ֱ��ת����һ��Object
			boolean result = jsonObject.getBoolean("result");

			JSONArray jsonArray = jsonObject.getJSONArray("goodsList");// ������һ���������ݣ�������getJSONArray��ȡ����
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject item = jsonArray.getJSONObject(i); // �õ�ÿ������
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
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();// ����HttpURLConnection����,���ǿ��Դ������л�ȡ��ҳ����.
		conn.setConnectTimeout(5 * 1000); // ��λ�Ǻ��룬���ó�ʱʱ��Ϊ5��
		conn.setRequestMethod("GET"); // HttpURLConnection��ͨ��HTTPЭ������path·���ģ�������Ҫ��������ʽ,���Բ����ã���ΪĬ��ΪGET
		if (conn.getResponseCode() == 200) {// �ж��������Ƿ���200�룬����ʧ��
			InputStream is = conn.getInputStream(); // ��ȡ������
			byte[] data = readStream(is); // ��������ת�����ַ�����
			String json = new String(data); // ���ַ�����ת�����ַ���

			JSONObject jsonObject = new JSONObject(json); // ���ص�������ʽ��һ��Object���ͣ����Կ���ֱ��ת����һ��Object
			boolean result = jsonObject.getBoolean("result");

			JSONArray jsonArray = jsonObject.getJSONArray("pictureList");// ������һ���������ݣ�������getJSONArray��ȡ����
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject item = jsonArray.getJSONObject(i); // �õ�ÿ������
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
	 * ��ȡ���͸��ӵ�JSON���� ������ʽ�� {"name":"С��", "age":23,
	 * "content":{"questionsTotal":2, "questions": [ { "question":
	 * "what's your name?", "answer": "С��"},{"question": "what's your age",
	 * "answer": "23"}] } }
	 * 
	 * @param path
	 *            ��ҳ·��
	 * @return ����List
	 * @throws Exception
	 */
	public static List<Map<String, String>> getJSON(String path)
			throws Exception {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();// ����HttpURLConnection����,���ǿ��Դ������л�ȡ��ҳ����.
		conn.setConnectTimeout(5 * 1000); // ��λ�Ǻ��룬���ó�ʱʱ��Ϊ5��
		conn.setRequestMethod("GET"); // HttpURLConnection��ͨ��HTTPЭ������path·���ģ�������Ҫ��������ʽ,���Բ����ã���ΪĬ��ΪGET
		if (conn.getResponseCode() == 200) {// �ж��������Ƿ���200�룬����ʧ��
			InputStream is = conn.getInputStream(); // ��ȡ������
			byte[] data = readStream(is); // ��������ת�����ַ�����
			String json = new String(data); // ���ַ�����ת�����ַ���

			/*
			 * ������ʽ�� {"name":"С��", "age":23, "content":{"questionsTotal":2,
			 * "questions": [ { "question": "what's your name?", "answer":
			 * "С��"},{"question": "what's your age", "answer": "23"}] } }
			 */
			JSONObject jsonObject = new JSONObject(json); // ���ص�������ʽ��һ��Object���ͣ����Կ���ֱ��ת����һ��Object
			String name = jsonObject.getString("name");
			int age = jsonObject.getInt("age");
			Log.i("abc", "name:" + name + " | age:" + age); // ��������

			JSONObject contentObject = jsonObject.getJSONObject("content"); // ��ȡ�����еĶ���
			String questionsTotal = contentObject.getString("questionsTotal"); // ��ȡ�����е�һ��ֵ
			Log.i("abc", "questionsTotal:" + questionsTotal); // ��������

			JSONArray contentArray = contentObject.getJSONArray("questions"); // ��ȡ�����е�����
			for (int i = 0; i < contentArray.length(); i++) {
				JSONObject item = contentArray.getJSONObject(i); // �õ�ÿ������
				String question = item.getString("question"); // ��ȡ�����Ӧ��ֵ
				String answer = item.getString("answer");

				map = new HashMap<String, String>(); // ��ŵ�MAP����
				map.put("question", question);
				map.put("answer", answer);
				list.add(map);
			}
		}

		// ***********��������******************

		for (Map<String, String> list2 : list) {
			String question = list2.get("question");
			String answer = list2.get("answer");
			Log.i("abc", "question:" + question + " | answer:" + answer);
		}
		return list;
	}

	/**
	 * ��������ת�����ַ�����
	 * 
	 * @param inputStream
	 *            ������
	 * @return �ַ�����
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
