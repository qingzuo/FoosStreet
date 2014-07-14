package com.jsu.campusordermeal.ui;

import java.util.ArrayList;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.adapter.Tab2TypeGridViewAdapter;
import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.ui.base.BaseActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class Tab2TypeActivity extends Activity {
	private GridView gridview;
	private ArrayList<FoodInfo> data;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab2_type_layout);
		//ÉèÖÃ²Ù×÷À¸
		ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar));
	    bar.setDisplayHomeAsUpEnabled(true);
		
		data = getIntent().getParcelableArrayListExtra("foods");
		
		initView();
		startService();
	}
	
	private void startService(){
		gridview.setAdapter(new Tab2TypeGridViewAdapter(this, this.data));
		gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(Tab2TypeActivity.this, ShowFoodInfoActivity.class);
				Bundle mBundle = new Bundle();  
		        mBundle.putParcelable("foodInfo", data.get(position));
		        intent.putExtras(mBundle);
		        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
	}

	private void initView() {
		gridview = (GridView)findViewById(R.id.sort_layout_gridview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.foodinfo_menu, menu);
	    
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			overridePendingTransition(R.anim.enter_lefttoright, R.anim.out_lefttoright);
			return true;
		case R.id.menu_search:
			Intent intent1 = new Intent(Tab2TypeActivity.this, SearchActivity.class);
			intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent1);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
