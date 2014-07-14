package com.jsu.campusordermeal.ui;

import java.util.ArrayList;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.adapter.Tab5GridAdapter;
import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.db.DBOperate;
import com.jsu.campusordermeal.ui.base.BaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class TabActivity5 extends Activity{
	private GridView tab5_gridview;
	private ArrayList<FoodInfo> data;
	
	@Override
	protected void onResume() {
		super.onResume();
		MainActivity.index=4;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab5);
		//获取数据
		data = DBOperate.getSpecialFoodFromDB(this);
		
		tab5_gridview = (GridView)findViewById(R.id.tab5_gridview);
		tab5_gridview.setAdapter(new Tab5GridAdapter(this, data));
		tab5_gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(TabActivity5.this, ShowFoodInfoActivity.class);
				Bundle mBundle = new Bundle();  
		        mBundle.putParcelable("foodInfo", data.get(position));
		        intent.putExtras(mBundle);
		        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
			
		});
	}

}
