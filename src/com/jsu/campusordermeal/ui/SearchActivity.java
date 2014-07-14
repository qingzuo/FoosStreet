package com.jsu.campusordermeal.ui;

import java.util.ArrayList;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.adapter.SearchActivityListAdapter;
import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.db.DBOperate;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends Activity {
	private EditText search_edit_text;
	private Button search_button;
	private TextView search_result_title;
	private ListView search_result_list;
	private ArrayList<FoodInfo> data;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);
		//设置操作栏
		ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar));
	    bar.setDisplayHomeAsUpEnabled(true);
		
		initView();
		//用历史记录填充list
	}
	
	private void initView(){
		search_edit_text = (EditText)findViewById(R.id.search_edit_text);
		search_result_title = (TextView)findViewById(R.id.search_result_title);
		search_result_list = (ListView)findViewById(R.id.search_result_list);
		search_button = (Button)findViewById(R.id.search_button);
		search_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String keyWord = search_edit_text.getText().toString().trim();
				if (!"".equals(keyWord)){
					data = DBOperate.getQueryDataFromDB(SearchActivity.this, keyWord);
					search_result_list.setAdapter(new SearchActivityListAdapter(SearchActivity.this, data));
					search_result_title.setText("搜索结果");
				}else{
					//Toast.makeText(this, , duration)
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		MenuInflater inflater = getMenuInflater();
//		inflater.inflate(R.menu.foodinfo_menu, menu);
//		//menu.removeItem(R.id.menu_search);
//		menu.removeItem(R.id.menu_search);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; go home
			finish();
			overridePendingTransition(R.anim.enter_lefttoright, R.anim.out_lefttoright);
			return true;
		case R.id.menu_search:
			Intent intent = new Intent(SearchActivity.this, SearchActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		case R.id.menu_share:
			Intent intent11 = new Intent(Intent.ACTION_SEND);

			intent11.setType("text/plain");
			intent11.putExtra(Intent.EXTRA_SUBJECT, "分享");
			intent11.putExtra(Intent.EXTRA_TEXT,
					"美食街软件不错，分享给你，下载地址http://w3.school.com/app/xiaoyuandincan.app");
			startActivity(Intent.createChooser(intent11, getTitle()));
			return true;
		// 以下不是操作栏的菜单相应
		case R.id.menu_user_login:
			Intent intent1 = new Intent(SearchActivity.this, LoginActivity.class);
			intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent1);
			return true;
		case R.id.menu_user_register:
			Intent intent111 = new Intent(SearchActivity.this, RegisterActivity.class);
			intent111.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent111);
			return true;
		case R.id.menu_soft_setting:
			Intent intent2 = new Intent(SearchActivity.this,
					SetPreferenceActivity.class);
			intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent2);
			return true;
		case R.id.menu_offer_suggest:
			Intent intent3 = new Intent(SearchActivity.this,
					OfferSuggestActivity.class);
			intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent3);
			return true;
		case R.id.menu_about_us:
			Intent intent4 = new Intent(SearchActivity.this,
					AboutUsActivity.class);
			intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent4);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
}
