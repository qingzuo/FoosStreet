package com.jsu.campusordermeal.test;

import java.util.ArrayList;

import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.util.UserFormManage;

import android.test.AndroidTestCase;

public class TestUserFormManage extends AndroidTestCase {

	public void testGetFoods() throws Exception{
		ArrayList<FoodInfo> foods = UserFormManage.getFoods(getContext());
		for (FoodInfo food:foods){
			System.out.println(food.get_id()+"-name:"+food.getName());
		}
	}
}
