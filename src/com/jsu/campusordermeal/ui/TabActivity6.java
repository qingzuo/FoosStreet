package com.jsu.campusordermeal.ui;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.ui.base.BaseActivity;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class TabActivity6 extends TabActivity implements OnClickListener {
	private Button tab6_wefind;
	private Button tab6_ifind;
	private Button tab6_newfind;
	private TabHost tabhost;

	@Override
	protected void onResume() {
		super.onResume();
		MainActivity.index = 5;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab6);

		tab6_wefind = (Button) findViewById(R.id.tab6_wefind);
		tab6_ifind = (Button) findViewById(R.id.tab6_ifind);
		tab6_newfind = (Button)findViewById(R.id.tab6_newfind);
		tab6_ifind.setOnClickListener(this);
		tab6_wefind.setOnClickListener(this);
		tab6_newfind.setOnClickListener(this);

		tabhost = getTabHost();
		// 添加选项卡的标题和内容
		TabSpec spec1 = tabhost.newTabSpec("tab1");
		spec1.setIndicator("大家的发现");
		spec1.setContent(new Intent(this, Tab6WeFindActivity.class));
		tabhost.addTab(spec1);

		TabSpec spec2 = tabhost.newTabSpec("tab2");
		spec2.setIndicator("我的发现");
		spec2.setContent(new Intent(this, Tab6IFindActivity.class));
		tabhost.addTab(spec2);

		TabSpec spec3 = tabhost.newTabSpec("tab3");
		spec3.setIndicator("发布新发现");
		spec3.setContent(new Intent(this, Tab6NewFindActivity.class));
		tabhost.addTab(spec3);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tab6_wefind:
			//Toast.makeText(this, "tab6_wefind", 0).show();
			tabhost.setCurrentTab(0);
			tab6_wefind.setBackgroundResource(R.drawable.button_selected02);
			tab6_ifind.setBackgroundResource(R.drawable.button_no_select);
			tab6_newfind.setBackgroundResource(R.drawable.button_no_select);
			break;
		case R.id.tab6_ifind:
			//Toast.makeText(this, "tab6_ifind", 0).show();
			tabhost.setCurrentTab(1);
			tab6_ifind.setBackgroundResource(R.drawable.button_selected02);
			tab6_wefind.setBackgroundResource(R.drawable.button_no_select);
			tab6_newfind.setBackgroundResource(R.drawable.button_no_select);
			break;
		case R.id.tab6_newfind:
			//Toast.makeText(this, "tab6_ifind", 0).show();
			tabhost.setCurrentTab(2);
			tab6_newfind.setBackgroundResource(R.drawable.button_selected02);
			tab6_ifind.setBackgroundResource(R.drawable.button_no_select);
			tab6_wefind.setBackgroundResource(R.drawable.button_no_select);
			break;
		default:
			break;
		}
	}

}
