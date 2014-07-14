package com.jsu.campusordermeal.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.util.Log;

import com.jsu.campusordermeal.R.string;
import com.jsu.campusordermeal.dao.UserInfo;
import com.jsu.campusordermeal.util.StreamTool;

public class SendOrder2Net {
	public static HttpClient httpClient = new DefaultHttpClient();

	private static final String TAG = "SendOrder2Net";

	public static boolean send(String path, int id) throws Exception {
		String param1 = URLEncoder.encode(""+id);
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		String data = "id=" + param1;

		conn.setRequestMethod("GET");
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
			String json = new String(result);
			Log.i(TAG, json);
			JSONObject jsonObject = new JSONObject(json);
			
			return jsonObject.getBoolean("result");
		} else {
			throw new IllegalStateException("服务器状态异常");
		}
		
	}
}
