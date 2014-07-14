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
		// ���ò�����
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.action_bar));

		tabhost = getTabHost();
		// ���ѡ��ı��������
		TabSpec spec1 = tabhost.newTabSpec("tab1");
		spec1.setIndicator("�Ƽ�");
		spec1.setContent(new Intent(this, TabActivity1.class));
		tabhost.addTab(spec1);

		TabSpec spec2 = tabhost.newTabSpec("tab2");
		spec2.setIndicator("����");
		spec2.setContent(new Intent(this, TabActivity2.class));
		tabhost.addTab(spec2);

		TabSpec spec3 = tabhost.newTabSpec("tab3");
		spec3.setIndicator("����");
		spec3.setContent(new Intent(this, TabActivity3.class));
		tabhost.addTab(spec3);

		TabSpec spec4 = tabhost.newTabSpec("tab4");
		spec4.setIndicator("����");
		spec4.setContent(new Intent(this, TabActivity4.class));
		tabhost.addTab(spec4);

		TabSpec spec5 = tabhost.newTabSpec("tab5");
		spec5.setIndicator("�ؼ�");
		spec5.setContent(new Intent(this, TabActivity5.class));
		tabhost.addTab(spec5);

		TabSpec spec6 = tabhost.newTabSpec("tab6");
		spec6.setIndicator("����");
		spec6.setContent(new Intent(this, TabActivity6.class));
		tabhost.addTab(spec6);

		TabSpec spec7 = tabhost.newTabSpec("tab7");
		spec7.setIndicator("������ʳ");
		spec7.setContent(new Intent(this, HealthDietActivity.class));
		tabhost.addTab(spec7);

		// ��ȡ�ؼ�����
		TabWidget tabWidget = (TabWidget) findViewById(android.R.id.tabs);
		count = tabWidget.getChildCount();
//Log.v("TAG", "����:" + count);
		// �ж��Ƿ�Ҫ���Ŀؼ����
		if (count < 4) {
			// ��ȡ��Ļ���
			int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
			Log.v("TAG", screenWidth + "");
			// ѭ�����¿ؼ��Ŀ��
			for (int i = 0; i < count; i++) {
				Log.v("TAG", "�ؼ�" + (i + 1));
				tabWidget.getChildTabViewAt(i).setMinimumWidth(
						screenWidth / count);
			}
		}

		gestureDetector = new GestureDetector(this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// ��������ʱ��,���������¼�ȥ����
		gestureDetector.onTouchEvent(event);
		return true;
	}

	// ����
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	static int index = 0;

	// ����
	// ����1,���µ�ʱ��
	// ����2,�ɿ���ʱ��
	// ����3,x�Ử�����ٶ�,ÿ�����������
	// ����4,y�Ử�����ٶ�,ÿ�����������
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		/*if (e1.getX() > e2.getX()) {
			// ���󻬶�,ѡ�ǰ������һ��
			if (index < count) {
				tabhost.setCurrentTab(++index);
			}
		} else if (e1.getX() < e2.getX()) {
			// ���һ���,ѡ����˵�ǰһ��
			if (index > 0) {
				tabhost.setCurrentTab(--index);
			}
		}*/
		return false;
	}

	// ����
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	// �϶�
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	// �ɿ�֮��
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	// �ɿ���һɲ��
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
			// ���²��ǲ������Ĳ˵���Ӧ
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
