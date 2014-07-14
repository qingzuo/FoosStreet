package com.jsu.campusordermeal.ui;

import java.util.ArrayList;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.adapter.Tab4BaseAdapter;
import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.db.DBOperate;
import com.jsu.campusordermeal.ui.base.BaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Toast;

public class TabActivity4 extends Activity{
	private ListView tab4_list_view;
	private ArrayList<FoodInfo> data;
	
	@Override
	protected void onResume() {
		super.onResume();
		MainActivity.index=3;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab4);
		data = DBOperate.getSortDataFromDB(this);
		initView();
		startService();
	}

	private void startService() {
		tab4_list_view.setAdapter(new Tab4BaseAdapter(this, this.data));
		tab4_list_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(TabActivity4.this, ShowFoodInfoActivity.class);
				Bundle mBundle = new Bundle();  
		        mBundle.putParcelable("foodInfo", data.get(position));
		        intent.putExtras(mBundle);
				startActivity(intent);
			}
		});
	}

	private void initView() {
		tab4_list_view = (ListView)findViewById(R.id.tab4_list_view);
	}

}
