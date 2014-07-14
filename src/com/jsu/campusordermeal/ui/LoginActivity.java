package com.jsu.campusordermeal.ui;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.dao.UserInfo;
import com.jsu.campusordermeal.db.DBOperate;
import com.jsu.campusordermeal.service.DataService;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class LoginActivity extends Activity implements OnClickListener {
	private EditText name;
	private EditText pwd;
	private Button login_button;
	private Button register_button;
	private CheckBox login_checkbox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		// 设置操作栏
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.action_bar));
		bar.setDisplayHomeAsUpEnabled(true);

		name = (EditText) findViewById(R.id.login_user_name);
		pwd = (EditText) findViewById(R.id.login_user_pwd);
		login_button = (Button) findViewById(R.id.login_button);
		register_button = (Button) findViewById(R.id.login_register_button);
		login_checkbox = (CheckBox) findViewById(R.id.login_checkbox);
		login_button.setOnClickListener(this);
		register_button.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_button:
			final String uName = name.getText().toString();
			final String uPwd = pwd.getText().toString();
			if ("".equals(uName)) {
				Toast.makeText(LoginActivity.this, "姓名不能为空 ",
						0).show();
				return;
			}
			if ("".equals(uPwd)) {
				Toast.makeText(LoginActivity.this, "密码不能为空",
						0).show();
				return;
			}
			
			final String hostUrl = getResources().getString(R.string.hostUrl);
			new AsyncTask<String, Double, UserInfo>() {

				@Override
				protected void onPostExecute(UserInfo result) {
					if (result==null){
						// 登陆失败
						Toast.makeText(LoginActivity.this, "登录失败！",Toast.LENGTH_LONG).show();
					}else{
						// 登陆成功
						UserInfo userInfo =  result;
						SharedPreferences sp = getSharedPreferences("UserLoginInfo", Context.MODE_PRIVATE);
						Editor editor = sp.edit();
						if (login_checkbox.isChecked()) {
							editor.putBoolean("isSavePwd", true);
							editor.putString("password", uPwd);
						} else {
							editor.putBoolean("isSavePwd", false);
							editor.remove("password");
						}
						editor.putBoolean("isLogin", true);
						editor.putString("name", uName);
						editor.putString("head", userInfo.getHead());
						editor.putString("email", userInfo.getEmail());
						editor.putString("phone", userInfo.getPhone());
						editor.putInt("id", userInfo.getId());
						//must
						editor.commit();
						
						Toast.makeText(LoginActivity.this, "登录成功！",Toast.LENGTH_LONG).show();
						finish();
						overridePendingTransition(R.anim.enter_lefttoright, R.anim.out_lefttoright);
					}
					super.onPostExecute(result);
				}

				@Override
				protected UserInfo doInBackground(String... arg0) {
					// 
					UserInfo userInfo = null;
					try {
						userInfo = DataService.sendDataByPost(hostUrl+"studentAction_login.action", uName, uPwd);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return userInfo;
				}
			}.execute("");
			
			break;
		case R.id.login_register_button:
			Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		}
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
			// app icon in action bar clicked; go home
			finish();
			overridePendingTransition(R.anim.enter_lefttoright, R.anim.out_lefttoright);
			return true;
		case R.id.menu_search:
			Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
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
			Intent intent1 = new Intent(LoginActivity.this, LoginActivity.class);
			intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent1);
			return true;
		case R.id.menu_user_register:
			Intent intent111 = new Intent(LoginActivity.this, RegisterActivity.class);
			intent111.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent111);
			return true;
		case R.id.menu_soft_setting:
			Intent intent2 = new Intent(LoginActivity.this,
					SetPreferenceActivity.class);
			intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent2);
			return true;
		case R.id.menu_offer_suggest:
			Intent intent3 = new Intent(LoginActivity.this,
					LoginActivity.class);
			intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent3);
			return true;
		case R.id.menu_about_us:
			Intent intent4 = new Intent(LoginActivity.this,
					AboutUsActivity.class);
			intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent4);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
