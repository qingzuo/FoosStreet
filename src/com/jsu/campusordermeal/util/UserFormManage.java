package com.jsu.campusordermeal.util;

import java.util.ArrayList;

import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.db.DBOperate;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

public class UserFormManage {

	public static int insertFood(Context context, int id) {
		SharedPreferences sp = context.getSharedPreferences("uername-foods",
				Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		String ids = sp.getString("ids", "");
		if (!ids.contains(""+id)){	//没有包含新的id
			StringBuffer idBuf = new StringBuffer(ids);
			idBuf.append(id + "-");
			edit.putString("ids", idBuf.toString());
			edit.commit();
			return 1;	//加入id成功
		}else{
			
			return 0;	//不成功，已经存在id
		}
	}

	public static int deleteFood(Context context, int id) {
		SharedPreferences sp = context.getSharedPreferences("uername-foods",
				Context.MODE_PRIVATE);
		String ids = sp.getString("ids", "");
		if (ids.contains("" + id)) {
			Editor edit = sp.edit();
			StringBuffer idBuf = new StringBuffer(ids);
			// delete food id
			int start = idBuf.indexOf("" + id);
			int end = idBuf.indexOf("-", start);
			idBuf.delete(start, end + 1);
			edit.putString("ids", idBuf.toString());
			edit.commit();
			return 1;	//删除成功，返回1
		}else{
			return 0;	//不成功，不存在该id，返回0
		}
	}

	public static void deleteAllFood(Context context) {
		SharedPreferences sp = context.getSharedPreferences("uername-foods",
				Context.MODE_PRIVATE);
		sp.edit().putString("ids", "").commit();
	}

	public static ArrayList<FoodInfo> getFoods(Context context) {
		SharedPreferences sp = context.getSharedPreferences("uername-foods",
				Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		String ids = sp.getString("ids", "");
		if ("".equals(ids)){
			return new ArrayList<FoodInfo>();
		}
		StringBuffer idsBuff = new StringBuffer(ids);
		String[] idsArr = ids.split("-");
//System.out.println("--------first :"+ids);
		if (idsArr.length >= 3){
			idsBuff.delete(ids.lastIndexOf("-"), ids.length());
		}
//System.out.println("--------second :"+ids);
		idsArr = ids.split("-");
		int[] temIds = new int[idsArr.length];
		for (int i = 0; i < idsArr.length; i++) {
// System.out.printf("--------IdsArr[%d]=%s", i, idsArr[i]);
			temIds[i] = Integer.parseInt(idsArr[i]);
		}

		ArrayList<FoodInfo> tData = DBOperate.getDataFromDB(context);
		ArrayList<FoodInfo> data = new ArrayList<FoodInfo>();
		for (FoodInfo food : tData) {
// System.out.println("UserFormManage---"+food.get_id()+"--"+food.getName());
			for (int i = 0; i < temIds.length; i++) {
				if (temIds[i] == food.get_id()) {
					data.add(food);
					break;
				}
			}
		}

		return data;
	}
}
