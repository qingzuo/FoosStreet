package com.jsu.campusordermeal.test;

import com.jsu.campusordermeal.service.DataService;
import com.jsu.campusordermeal.service.SendOrder2Net;

import android.test.AndroidTestCase;

public class TestSendOrder2Net extends AndroidTestCase {

	public void test() throws Exception{
		DataService.sendDataByPost("http://192.168.1.110:8080/CampusOrderMeal/studentAction_login.action", "zuo", "1234");
		boolean result = SendOrder2Net.send("http://192.168.1.110:8080/CampusOrderMeal/goodsAction_add2Order", 1);
		assertEquals(true, result);
	}
}
