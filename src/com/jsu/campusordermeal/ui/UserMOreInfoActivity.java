package com.jsu.campusordermeal.ui;

import com.jsu.campusordermeal.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

public class UserMOreInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar bar = getActionBar();
		bar.setTitle("用户信息管理");
		bar.setDisplayHomeAsUpEnabled(true);
	    bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar));
	    // 布局
		setContentView(R.layout.user_moreinfo_activity);
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			overridePendingTransition(R.anim.enter_lefttoright, R.anim.out_lefttoright);
			break;

		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
