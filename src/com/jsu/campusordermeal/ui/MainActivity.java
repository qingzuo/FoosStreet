package com.jsu.campusordermeal.ui;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.ui.base.BaseActivity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.GestureDetector.OnGestureListener;
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

@SuppressLint("NewApi")
public class MainActivity extends TabActivity implements OnGestureListener {
	static int flag = 0;
	static Bundle bundle = new Bundle();
	static TabHost tabhost;
	int count;
	GestureDetector gestureDetector;
	private boolean canExit = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 设置操作栏
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.action_bar));

		tabhost = getTabHost();
		// 添加选项卡的标题和内容
		TabSpec spec1 = tabhost.newTabSpec("tab1");
		spec1.setIndicator("推荐");
		spec1.setContent(new Intent(this, TabActivity1.class));
		tabhost.addTab(spec1);

		TabSpec spec2 = tabhost.newTabSpec("tab2");
		spec2.setIndicator("分类");
		spec2.setContent(new Intent(this, TabActivity2.class));
		tabhost.addTab(spec2);

		TabSpec spec3 = tabhost.newTabSpec("tab3");
		spec3.setIndicator("订单");
		spec3.setContent(new Intent(this, TabActivity3.class));
		tabhost.addTab(spec3);

		TabSpec spec4 = tabhost.newTabSpec("tab4");
		spec4.setIndicator("排名");
		spec4.setContent(new Intent(this, TabActivity4.class));
		tabhost.addTab(spec4);

		TabSpec spec5 = tabhost.newTabSpec("tab5");
		spec5.setIndicator("特价");
		spec5.setContent(new Intent(this, TabActivity5.class));
		tabhost.addTab(spec5);

		TabSpec spec6 = tabhost.newTabSpec("tab6");
		spec6.setIndicator("发现");
		spec6.setContent(new Intent(this, TabActivity6.class));
		tabhost.addTab(spec6);

		TabSpec spec7 = tabhost.newTabSpec("tab7");
		spec7.setIndicator("健康饮食");
		spec7.setContent(new Intent(this, HealthDietActivity.class));
		tabhost.addTab(spec7);

		// 获取控件数量
		TabWidget tabWidget = (TabWidget) findViewById(android.R.id.tabs);
		count = tabWidget.getChildCount();
//Log.v("TAG", "数量:" + count);
		// 判断是否要更改控件宽度
		if (count < 4) {
			// 获取屏幕宽度
			int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
			Log.v("TAG", screenWidth + "");
			// 循环更新控件的宽度
			for (int i = 0; i < count; i++) {
				Log.v("TAG", "控件" + (i + 1));
				tabWidget.getChildTabViewAt(i).setMinimumWidth(
						screenWidth / count);
			}
		}

		gestureDetector = new GestureDetector(this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 当触摸的时候,交给手势事件去处理
		gestureDetector.onTouchEvent(event);
		return true;
	}

	// 按下
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	static int index = 0;

	// 滑动
	// 参数1,按下的时候
	// 参数2,松开的时候
	// 参数3,x轴滑动的速度,每毫秒多少像素
	// 参数4,y轴滑动的速度,每毫秒多少像素
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		/*if (e1.getX() > e2.getX()) {
			// 向左滑动,选项卡前进到下一个
			if (index < count) {
				tabhost.setCurrentTab(++index);
			}
		} else if (e1.getX() < e2.getX()) {
			// 向右滑动,选项卡后退到前一个
			if (index > 0) {
				tabhost.setCurrentTab(--index);
			}
		}*/
		return false;
	}

	// 长按
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	// 拖动
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	// 松开之后
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	// 松开那一刹那
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_search:
			Intent intent = new Intent(MainActivity.this, SearchActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
			// 以下不是操作栏的菜单相应
		case R.id.menu_user_login:
			Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
			intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent1);
			return true;
		case R.id.menu_user_register:
			Intent intent11 = new Intent(MainActivity.this, RegisterActivity.class);
			intent11.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent11);
			return true;
		case R.id.menu_soft_setting:
			Intent intent2 = new Intent(MainActivity.this,
					SetPreferenceActivity.class);
			intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent2);
			return true;
		case R.id.menu_offer_suggest:
			Intent intent3 = new Intent(MainActivity.this,
					OfferSuggestActivity.class);
			intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent3);
			return true;
		case R.id.menu_about_us:
			Intent intent4 = new Intent(MainActivity.this,
					AboutUsActivity.class);
			intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent4);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
