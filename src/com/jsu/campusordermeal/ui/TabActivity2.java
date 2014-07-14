package com.jsu.campusordermeal.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.db.DBOperate;
import com.jsu.campusordermeal.ui.base.BaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class TabActivity2 extends Activity {
	private ListView tab2_list_view;
	private ArrayList<FoodInfo> data;
	
	// Activity有一系列on方法,这些方法会在Activity整个生命周期的特定时候被调用
	// 我们之前一直使用的onCreate方法是在Activity被创建的时候,也是第一个被调用的方法
	// 而onResume方法则是当Activity开始和用户进行交互(完全呈现在用户面前的时候)调用

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MainActivity.index = 1;
		String msg = MainActivity.bundle.getString("msg");
		// TextView text=(TextView)findViewById(R.id.tab2_textview);
		// text.setText(msg);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab2);

		initView();
		startService();
	}

	private void startService() {
		//set adapter for tab2_list_view
		final String [] items = this.getResources().getStringArray(R.array.tab2_list_view);
		List<Map<String, String>> ldata = new ArrayList<Map<String,String>>();
		for (String item:items){
			Map<String, String> m = new HashMap<String, String>();
            m.put("tab2_list_adapter_item_layout_tv", item);
			ldata.add(m);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, ldata, R.layout.tab2_list_adapter_item_layout, new String[]{"tab2_list_adapter_item_layout_tv"}, new int[]{R.id.tab2_list_adapter_item_layout_tv});
		tab2_list_view.setAdapter(adapter);
		tab2_list_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//得到类型，并查询数据
				String foodType = items[position];
				data = DBOperate.getTypeFoodDataFromDB(TabActivity2.this, foodType);
				//启动浏览分类的页面Tab2TypeActivity,并传递数据
				Intent intent = new Intent(TabActivity2.this,
						Tab2TypeActivity.class);
				intent.putParcelableArrayListExtra("foods", data);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
	}

	private void initView() {
		tab2_list_view = (ListView) findViewById(R.id.tab2_list_view);
	}

}
