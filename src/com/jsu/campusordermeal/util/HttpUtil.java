package com.jsu.campusordermeal.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class HttpUtil {
	public static HttpClient httpClient = new DefaultHttpClient();
	/**
	 * 通过url从服务器获取请求字段
	 * @param url 发�?请求的url
	 * @return 返回服务器请求字�?
	 */
	public static String getRequst(String url) throws Exception{
		System.out.println(url);
		// 请求超时
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 8000);
        // 读取超时
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
		HttpGet httpGet = new HttpGet(url); // 创建HttpGet 对象
		// 发�?get请求
		HttpResponse httpResponse = httpClient.execute(httpGet);
		// 如果服务端成功返回响�?
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			// 获取服务器响应字符串
			String result = EntityUtils.toString(httpResponse.getEntity());
			return result;
		}
		return null;
	}
	/**
	 * post请求方法
	 * @param url 发�?请求的URL
	 * @param rawParams 请求参数
	 * @return 服务器响应字符串
	 * @throws Exception
	 */
	public static String postRequest(String url,Map<String, String> rawParams) throws Exception{
		// 请求超时
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 8000);
        // 读取超时
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
		// 创建HttpPost对象
		HttpPost post = new HttpPost(url);
		// 如果请求参数较多，对请求参数进行封装
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (String key : rawParams.keySet()) {
			params.add(new BasicNameValuePair(key, rawParams.get(key)));
		}
		// 设置请求参数
		post.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
		// 发�?post请求
		HttpResponse httpResponse = httpClient.execute(post);
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			// 获取服务器响应字符串
/*			String result = EntityUtils.toString(httpResponse.getEntity());
			Log.i("HttpUtil", "result: "+result);
			return result;*/
			InputStream is = httpResponse.getEntity().getContent();
			byte[] result = StreamTool.getBytes(is);
			return new String(result);
		}
		return null;
	}
}
