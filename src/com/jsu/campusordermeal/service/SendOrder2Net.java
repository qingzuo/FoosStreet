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
			String json = new String(result);
			Log.i(TAG, json);
			JSONObject jsonObject = new JSONObject(json);
			
			return jsonObject.getBoolean("result");
		} else {
			throw new IllegalStateException("������״̬�쳣");
		}
		
	}
}
