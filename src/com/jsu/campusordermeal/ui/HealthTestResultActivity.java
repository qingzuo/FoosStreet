package com.jsu.campusordermeal.ui;

import com.jsu.campusordermeal.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

public class HealthTestResultActivity extends Activity {

	private TextView tv_healthresult_result;
	private ProgressBar pb_healthresult_data1;
	private ProgressBar pb_healthresult_data2;
	private ProgressBar pb_healthresult_data3;
	private ProgressBar pb_healthresult_data4;
	private ProgressBar pb_healthresult_data5;
	private ProgressBar pb_healthresult_data6;
	private ProgressBar pb_healthresult_data7;
	private ProgressBar pb_healthresult_data8;
	private ProgressBar pb_healthresult_data9;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar bar = getActionBar();
		bar.setTitle("²âÊÔ½á¹û");
		bar.setDisplayHomeAsUpEnabled(true);
		//
		setContentView(R.layout.healthtest_result_activity);

		initView();
	}

	private void initView() {
		tv_healthresult_result = (TextView) findViewById(R.id.tv_healthresult_result);
		pb_healthresult_data1 = (ProgressBar) findViewById(R.id.pb_healthresult_data1);
		pb_healthresult_data2 = (ProgressBar) findViewById(R.id.pb_healthresult_data2);
		pb_healthresult_data3 = (ProgressBar) findViewById(R.id.pb_healthresult_data3);
		pb_healthresult_data4 = (ProgressBar) findViewById(R.id.pb_healthresult_data4);
		pb_healthresult_data5 = (ProgressBar) findViewById(R.id.pb_healthresult_data5);
		pb_healthresult_data6 = (ProgressBar) findViewById(R.id.pb_healthresult_data6);
		pb_healthresult_data7 = (ProgressBar) findViewById(R.id.pb_healthresult_data7);
		pb_healthresult_data8 = (ProgressBar) findViewById(R.id.pb_healthresult_data8);
		pb_healthresult_data9 = (ProgressBar) findViewById(R.id.pb_healthresult_data9);
		

		pb_healthresult_data1.setProgress(70);
		pb_healthresult_data2.setProgress(50);
		pb_healthresult_data3.setProgress(40);
		pb_healthresult_data4.setProgress(20);
		pb_healthresult_data5.setProgress(40);
		pb_healthresult_data6.setProgress(60);
		pb_healthresult_data7.setProgress(40);
		pb_healthresult_data8.setProgress(20);
		pb_healthresult_data9.setProgress(30);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			overridePendingTransition(R.anim.enter_lefttoright,
					R.anim.out_lefttoright);
			break;

		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
