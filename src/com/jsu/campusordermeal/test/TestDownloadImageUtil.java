package com.jsu.campusordermeal.test;

import com.jsu.campusordermeal.util.DownloadImageUtil;

import android.test.AndroidTestCase;

public class TestDownloadImageUtil extends AndroidTestCase {

	public void test() throws Exception{
		byte[] result = DownloadImageUtil.getImage("http://192.168.1.113:8080/tomcat.gif");
		System.out.print(result.length);
	}
}
