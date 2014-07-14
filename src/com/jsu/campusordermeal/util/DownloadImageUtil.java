package com.jsu.campusordermeal.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImageUtil {

	public String getImageFromNet(String path) {

		return null;
	}

	public static byte[] getImage(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("GET");
		InputStream inStream = conn.getInputStream();
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			return StreamTool.getBytes(inStream);
		}
		return null;
	}
	
	
}
