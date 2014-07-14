package com.jsu.campusordermeal.ui;

import java.util.ArrayList;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.adapter.Tab6WeFindAdapter;
import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.db.DBOperate;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class Tab6WeFindActivity extends Activity {
	private ListView tab6_wefind_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab6_wefind_activity);
		
		tab6_wefind_list = (ListView)findViewById(R.id.tab6_wefind_list);
		
		ArrayList<FoodInfo> data = DBOperate.getDataFromDB(this);
		tab6_wefind_list.setAdapter(new Tab6WeFindAdapter(this, data));
	}
}
