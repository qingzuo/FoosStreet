package com.jsu.campusordermeal.ui;

import com.jsu.campusordermeal.R;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class UserFormHistoryActivity extends Activity{

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置操作栏
		ActionBar bar = getActionBar();
	    bar.setDisplayHomeAsUpEnabled(true);
	    bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar));
	    bar.setTitle("历史订单");
	    // 布局
		setContentView(R.layout.user_form_history_activity);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.foodinfo_menu, menu);
		menu.removeItem(R.id.menu_share);
	    
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
			Intent intent = new Intent(UserFormHistoryActivity.this, SearchActivity.class);
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
			Intent intent1 = new Intent(UserFormHistoryActivity.this, LoginActivity.class);
			intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent1);
			return true;
		case R.id.menu_user_register:
			Intent intent111 = new Intent(UserFormHistoryActivity.this, RegisterActivity.class);
			intent111.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent111);
			return true;
		case R.id.menu_soft_setting:
			Intent intent2 = new Intent(UserFormHistoryActivity.this,
					SetPreferenceActivity.class);
			intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent2);
			return true;
		case R.id.menu_offer_suggest:
			Intent intent3 = new Intent(UserFormHistoryActivity.this,
					OfferSuggestActivity.class);
			intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent3);
			return true;
		case R.id.menu_about_us:
			Intent intent4 = new Intent(UserFormHistoryActivity.this,
					AboutUsActivity.class);
			intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent4);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
