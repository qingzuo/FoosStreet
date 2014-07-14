package com.jsu.campusordermeal.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.jsu.campusordermeal.dao.UserInfo;
import com.jsu.campusordermeal.util.StreamTool;

import android.R.bool;
import android.net.Uri;
import android.util.Log;

public class DataService {

	private static final String TAG = "DataService";

	/**
	 * ͨ��get�����ύ���ݵ�������
	 * 
	 * @param path
	 *            ������servlet�ĵ�ַ
	 * @param name
	 *            �û���
	 * @param password
	 *            ����
	 * @return ���������ػ�����string����
	 */
	public static String sendDataByGet(String path, String name, String password)
			throws Exception {
		String param1 = URLEncoder.encode(name);
		String param2 = URLEncoder.encode(password);
		URL url = new URL(path + "?name=" + param1 + "&password=" + param2);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");
		conn.setReadTimeout(5000);
		// ���ݲ�û�з��͸�������
		// ��ȡ���������ص�����Ϣ
		InputStream is = conn.getInputStream();
		byte[] result = StreamTool.getBytes(is);

		return new String(result);
	}

	// get post
	// get һ���ύ�������������Ƚ�С 4K �ڲ���ʵͨ����ƴurl�ķ�ʽ
	// post �����ύ�Ƚϴ������ form������ʽ ���ķ�ʽд��������
	/**
	 * ����post�ķ�ʽ �ύ���ݵ�������
	 * 
	 * @param path
	 *            ������servlet�ĵ�ַ
	 * @param name
	 *            �û���
	 * @param password
	 *            ����
	 * @return ���������ص�������Ϣ
	 * @throws Exception
	 */
	public static UserInfo sendDataByPost(String path, String name,
			String password) throws Exception {
		String param1 = URLEncoder.encode(name);
		String param2 = URLEncoder.encode(password);
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		String data = "name=" + param1 + "&password=" + param2;
	Log.i(TAG, path +"?"+data);

		conn.setRequestMethod("POST");
		conn.setConnectTimeout(5000);
		// ���� httpЭ������������д����
		conn.setDoOutput(true);
		// ����httpЭ�����Ϣͷ
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", data.length() + "");
		// ������׼���õ�data����д��������
		OutputStream os = conn.getOutputStream();
		os.write(data.getBytes());
		// httpurlconnection �ײ�ʵ�� outputstream ��һ�����������
		// ֻҪ���ǻ�ȡ�κ�һ�����������ص���Ϣ , ���ݾͻᱻ�ύ�������� , �õ����������ص�����Ϣ
		int code = conn.getResponseCode();
		if (code == 200) {
			InputStream is = conn.getInputStream();
			byte[] result = StreamTool.getBytes(is);
			JSONObject jsonObject = new JSONObject(new String(result));
			UserInfo info = null;
			if (jsonObject.getBoolean("result")){
				info = new UserInfo();
				// ��������
				JSONObject stu = jsonObject.getJSONObject("student");
				int id = stu.getInt("id");
				String email = stu.getString("email");
				String phone = stu.getString("phone");
				String head = stu.getString("head");
				Log.i(TAG, "email: "+ email);
				
				info.setId(id);
				info.setEmail(email);
				info.setHead(head);
				info.setName(name); 
				info.setPassword(password);
				info.setPhone(phone);
			}
			return info;
		} else {
			throw new IllegalStateException("������״̬�쳣");
		}
	}

	/**
	 * httpclient ������ļ򵥰�װ new HttpClient ���൱�ڵõ���һ�������
	 */
	public static String sendDataByHttpClientGet(String path, String name,
			String password) throws Exception {

		// 1. ��ȡ��һ���������ʵ��
		HttpClient client = new DefaultHttpClient();
		// 2. ׼������ĵ�ַ
		String param1 = URLEncoder.encode(name);
		String param2 = URLEncoder.encode(password);
		HttpGet httpGet = new HttpGet(path + "?name=" + param1 + "&password="
				+ param2);

		// 3. �ûس� ������
		HttpResponse ressponse = client.execute(httpGet);
		int code = ressponse.getStatusLine().getStatusCode();
		if (code == 200) {
			InputStream is = ressponse.getEntity().getContent();
			byte[] result = StreamTool.getBytes(is);
			return new String(result);
		} else {
			throw new IllegalStateException("������״̬�쳣");
		}
	}

	public static String sendDataByHttpClientPost(String path, String name,
			String password) throws Exception {

		// 1. ��ȡ��һ���������ʵ��
		HttpClient client = new DefaultHttpClient();
		// 2. ׼��Ҫ����� ��������
		HttpPost httppost = new HttpPost(path);
		// ��ֵ��
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();

		parameters.add(new BasicNameValuePair("name", name));
		parameters.add(new BasicNameValuePair("password", password));

		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters,
				"utf-8");

		// 3.����post���������ʵ��
		httppost.setEntity(entity);

		// 4. �������ݸ�������
		HttpResponse ressponse = client.execute(httppost);
		int code = ressponse.getStatusLine().getStatusCode();
		if (code == 200) {
			InputStream is = ressponse.getEntity().getContent();
			byte[] result = StreamTool.getBytes(is);
			return new String(result);
		} else {
			throw new IllegalStateException("������״̬�쳣");
		}
	}

	/**
	 * �ύ���ݸ������� ��һ���ļ�
	 * 
	 * @param path
	 * @param name
	 * @param password
	 * @param filepath
	 *            �ļ����ֻ��ϵ�·��
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String sendDataByHttpClientPost(String address,
			String username, String password, String filepath) throws Exception {
		// ʵ�����ϴ����ݵ� ���� part []
		/*
		 * Part[] parts = {new StringPart("name", name), new
		 * StringPart("password", password), new FilePart("file", new
		 * File(filepath))};
		 */

		/*
		 * Part[] parts = { new StringPart("name", name), new
		 * StringPart("password", password), new FilePart("file", new
		 * File(filepath)) };
		 * 
		 * PostMethod filePost = new PostMethod(path);
		 * 
		 * filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost
		 * .getParams())); org.apache.commons.httpclient.HttpClient client = new
		 * org.apache.commons.httpclient.HttpClient();
		 * client.getHttpConnectionManager().getParams()
		 * .setConnectionTimeout(5000); int status =
		 * client.executeMethod(filePost); if (status == 200) {
		 * 
		 * System.out.println(filePost.getResponseCharSet()); String result =
		 * new String(filePost.getResponseBodyAsString()); String ha = new
		 * String(result.getBytes("ISO-8859-1"), "UTF-8");
		 * System.out.println(ha);
		 * 
		 * System.out.println("--" + result); return result; } else { throw new
		 * IllegalStateException("������״̬�쳣"); }
		 */

		String boundary = "---------------------------7db1c523809b2";
		// �ָ���
		File file = new File(filepath);
		// Ҫ�ϴ����ļ�
		Uri uri = Uri.parse(address);
		// ���������������Ͷ˿�
		URL url = new URL(address);
		// ������������
		StringBuilder sb = new StringBuilder();
		// ����ƴװ����

		// username�ֶ�
		sb.append("--" + boundary + "\r\n");
		sb.append("Content-Disposition: form-data; name=\"username\"" + "\r\n");
		sb.append("\r\n");
		sb.append(username + "\r\n");

		// password�ֶ�
		sb.append("--" + boundary + "\r\n");
		sb.append("Content-Disposition: form-data; name=\"password\"" + "\r\n");
		sb.append("\r\n");
		sb.append(password + "\r\n");

		// �ļ�����
		sb.append("--" + boundary + "\r\n");
		sb.append("Content-Disposition: form-data; name=\"file\"; filename=\""
				+ filepath + "\"" + "\r\n");
		sb.append("Content-Type: application/octet-stream" + "\r\n");
		sb.append("\r\n");

		// ����ͷ�ͽ�β����תΪ�ֽ����飬��Ϊ����Content-Typeʱ�������ֽڳ���
		byte[] before = sb.toString().getBytes("UTF-8");
		byte[] after = ("\r\n--" + boundary + "--\r\n").getBytes("UTF-8");

		// ������, ��������ͷ
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(3000);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type",
				"multipart/form-data; boundary=" + boundary);
		conn.setRequestProperty("Content-Length", before.length + file.length()
				+ after.length + "");
		conn.setRequestProperty("HOST", uri.getHost() + ":" + uri.getPort());
		// 192.168.1.100:8080
		conn.setDoOutput(true);

		// ��ȡ���������
		OutputStream out = conn.getOutputStream();
		FileInputStream fis = new FileInputStream(file);

		// ����ͷ����д��
		out.write(before);

		// д���ļ�����
		byte[] buf = new byte[1024];
		int len;
		while ((len = fis.read(buf)) != -1)
			out.write(buf, 0, len);

		// ����β����д��
		out.write(after);

		fis.close();
		out.close();
		if (conn.getResponseCode() == 200) {
			return "ok";
		} else {
			return "bad";
		}

	}

	/**
	 * �ύ���ݸ������� ��һ���ļ�
	 * 
	 * @param path
	 * @param name
	 * @param password
	 * @param filepath
	 *            �ļ����ֻ��ϵ�·��
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String sendDataByHttpClientPostForRegister(String address,
			Map<String, String> parames, String filepath) throws Exception {

		// ʵ�����ϴ����ݵ� ����  part []
				Part[] parts = {new StringPart("name", parames.get("name")), 
						  new StringPart("phone", parames.get("phone")), 
						  new StringPart("email", parames.get("email")), 
						  new FilePart("upload", new File(filepath)), 
						  new StringPart("password", parames.get("password"))};
				
				PostMethod filePost = new PostMethod(address);
				
			  
				filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
				org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
		        client.getHttpConnectionManager().getParams()
		          .setConnectionTimeout(5000);
				int status = client.executeMethod(filePost);
				if(status==200){
					
					String result = new String(filePost.getResponseBodyAsString());
					String ha = new String ( result.getBytes("ISO-8859-1"),"UTF-8");
					System.out.println(ha);
					
					System.out.println("--"+result);System.out.println( filePost.getResponseCharSet());
					
					return "ok";
				}
				else{
					throw new IllegalStateException("������״̬�쳣");
				}
		 

	}
}
