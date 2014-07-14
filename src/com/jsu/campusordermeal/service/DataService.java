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
	 * 通过get请求提交数据到服务器
	 * 
	 * @param path
	 *            服务器servlet的地址
	 * @param name
	 *            用户名
	 * @param password
	 *            密码
	 * @return 服务器返回回来的string数据
	 */
	public static String sendDataByGet(String path, String name, String password)
			throws Exception {
		String param1 = URLEncoder.encode(name);
		String param2 = URLEncoder.encode(password);
		URL url = new URL(path + "?name=" + param1 + "&password=" + param2);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");
		conn.setReadTimeout(5000);
		// 数据并没有发送给服务器
		// 获取服务器返回的流信息
		InputStream is = conn.getInputStream();
		byte[] result = StreamTool.getBytes(is);

		return new String(result);
	}

	// get post
	// get 一次提交的数据数据量比较小 4K 内部其实通过组拼url的方式
	// post 可以提交比较大的数据 form表单的形式 流的方式写到服务器
	/**
	 * 采用post的方式 提交数据到服务器
	 * 
	 * @param path
	 *            服务器servlet的地址
	 * @param name
	 *            用户名
	 * @param password
	 *            密码
	 * @return 服务器返回的数据信息
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
		// 设置 http协议可以向服务器写数据
		conn.setDoOutput(true);
		// 设置http协议的消息头
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", data.length() + "");
		// 把我们准备好的data数据写给服务器
		OutputStream os = conn.getOutputStream();
		os.write(data.getBytes());
		// httpurlconnection 底层实现 outputstream 是一个缓冲输出流
		// 只要我们获取任何一个服务器返回的信息 , 数据就会被提交给服务器 , 得到服务器返回的流信息
		int code = conn.getResponseCode();
		if (code == 200) {
			InputStream is = conn.getInputStream();
			byte[] result = StreamTool.getBytes(is);
			JSONObject jsonObject = new JSONObject(new String(result));
			UserInfo info = null;
			if (jsonObject.getBoolean("result")){
				info = new UserInfo();
				// 解析对象
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
			throw new IllegalStateException("服务器状态异常");
		}
	}

	/**
	 * httpclient 浏览器的简单包装 new HttpClient 就相当于得到了一个浏览器
	 */
	public static String sendDataByHttpClientGet(String path, String name,
			String password) throws Exception {

		// 1. 获取到一个浏览器的实例
		HttpClient client = new DefaultHttpClient();
		// 2. 准备请求的地址
		String param1 = URLEncoder.encode(name);
		String param2 = URLEncoder.encode(password);
		HttpGet httpGet = new HttpGet(path + "?name=" + param1 + "&password="
				+ param2);

		// 3. 敲回车 发请求
		HttpResponse ressponse = client.execute(httpGet);
		int code = ressponse.getStatusLine().getStatusCode();
		if (code == 200) {
			InputStream is = ressponse.getEntity().getContent();
			byte[] result = StreamTool.getBytes(is);
			return new String(result);
		} else {
			throw new IllegalStateException("服务器状态异常");
		}
	}

	public static String sendDataByHttpClientPost(String path, String name,
			String password) throws Exception {

		// 1. 获取到一个浏览器的实例
		HttpClient client = new DefaultHttpClient();
		// 2. 准备要请求的 数据类型
		HttpPost httppost = new HttpPost(path);
		// 键值对
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();

		parameters.add(new BasicNameValuePair("name", name));
		parameters.add(new BasicNameValuePair("password", password));

		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters,
				"utf-8");

		// 3.设置post请求的数据实体
		httppost.setEntity(entity);

		// 4. 发送数据给服务器
		HttpResponse ressponse = client.execute(httppost);
		int code = ressponse.getStatusLine().getStatusCode();
		if (code == 200) {
			InputStream is = ressponse.getEntity().getContent();
			byte[] result = StreamTool.getBytes(is);
			return new String(result);
		} else {
			throw new IllegalStateException("服务器状态异常");
		}
	}

	/**
	 * 提交数据给服务器 带一个文件
	 * 
	 * @param path
	 * @param name
	 * @param password
	 * @param filepath
	 *            文件在手机上的路径
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String sendDataByHttpClientPost(String address,
			String username, String password, String filepath) throws Exception {
		// 实例化上传数据的 数组 part []
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
		 * IllegalStateException("服务器状态异常"); }
		 */

		String boundary = "---------------------------7db1c523809b2";
		// 分割线
		File file = new File(filepath);
		// 要上传的文件
		Uri uri = Uri.parse(address);
		// 用来解析主机名和端口
		URL url = new URL(address);
		// 用来开启连接
		StringBuilder sb = new StringBuilder();
		// 用来拼装请求

		// username字段
		sb.append("--" + boundary + "\r\n");
		sb.append("Content-Disposition: form-data; name=\"username\"" + "\r\n");
		sb.append("\r\n");
		sb.append(username + "\r\n");

		// password字段
		sb.append("--" + boundary + "\r\n");
		sb.append("Content-Disposition: form-data; name=\"password\"" + "\r\n");
		sb.append("\r\n");
		sb.append(password + "\r\n");

		// 文件部分
		sb.append("--" + boundary + "\r\n");
		sb.append("Content-Disposition: form-data; name=\"file\"; filename=\""
				+ filepath + "\"" + "\r\n");
		sb.append("Content-Type: application/octet-stream" + "\r\n");
		sb.append("\r\n");

		// 将开头和结尾部分转为字节数组，因为设置Content-Type时长度是字节长度
		byte[] before = sb.toString().getBytes("UTF-8");
		byte[] after = ("\r\n--" + boundary + "--\r\n").getBytes("UTF-8");

		// 打开连接, 设置请求头
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

		// 获取输入输出流
		OutputStream out = conn.getOutputStream();
		FileInputStream fis = new FileInputStream(file);

		// 将开头部分写出
		out.write(before);

		// 写出文件数据
		byte[] buf = new byte[1024];
		int len;
		while ((len = fis.read(buf)) != -1)
			out.write(buf, 0, len);

		// 将结尾部分写出
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
	 * 提交数据给服务器 带一个文件
	 * 
	 * @param path
	 * @param name
	 * @param password
	 * @param filepath
	 *            文件在手机上的路径
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String sendDataByHttpClientPostForRegister(String address,
			Map<String, String> parames, String filepath) throws Exception {

		// 实例化上传数据的 数组  part []
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
					throw new IllegalStateException("服务器状态异常");
				}
		 

	}
}
