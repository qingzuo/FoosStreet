package com.jsu.campusordermeal.ui;

import java.util.HashMap;
import java.util.Map;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.db.DBOperate;
import com.jsu.campusordermeal.service.DataService;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private static final String TAG = "RegisterActivity";
	private static final int RESULT_LOAD_IMAGE = 100;
	private EditText register_username;
	private EditText register_pwd;
	private EditText register_pwd2;
	private EditText register_phone;
	private EditText register_email;
	private ImageView iv_register_head;
	private Button register_button;

	private String picturePath = "";	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);

	    if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
	        Uri selectedImage = data.getData();
	        String[] filePathColumn = { MediaStore.Images.Media.DATA };

	        Cursor cursor = getContentResolver().query(selectedImage,
	                filePathColumn, null, null, null);
	        cursor.moveToFirst();

	        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	        picturePath = cursor.getString(columnIndex);
System.out.println("picturePath: "+ picturePath);
	Toast.makeText(this, picturePath, 1).show();
	        cursor.close();
	        Bitmap decodeFile = BitmapFactory.decodeFile(picturePath);
	        iv_register_head.setImageBitmap(decodeFile);
	    }
	}

	public void onRegisterClick(View v) {
		switch (v.getId()) {
		case R.id.bt_register_photo: // 从图库得到一张图片
			Intent i = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(i, RESULT_LOAD_IMAGE);

			break;

		case R.id.bt_register_takePhoto: // 拍照得到一张图片

			break;

		default:
			break;
		}
	}

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity);
		// 设置操作栏
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.action_bar));
		bar.setDisplayHomeAsUpEnabled(true);

		register_username = (EditText) findViewById(R.id.register_username);
		register_pwd = (EditText) findViewById(R.id.register_pwd);
		register_pwd2 = (EditText) findViewById(R.id.register_pwd2);
		register_phone = (EditText) findViewById(R.id.register_phone);
		register_email = (EditText) findViewById(R.id.register_email);
		iv_register_head = (ImageView) findViewById(R.id.iv_register_head);
		register_button = (Button) findViewById(R.id.register_button);
		register_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String name = register_username.getText().toString();
				String pwd = register_pwd.getText().toString();
				String pwd2 = register_pwd2.getText().toString();
				String phone = register_phone.getText().toString();
				String email = register_email.getText().toString();
				if ("".equals(name)) {
					Toast.makeText(RegisterActivity.this, "姓名不能为空 ", 0).show();
					return;
				}
				if ("".equals(pwd) | "".equals(pwd2)) {
					Toast.makeText(RegisterActivity.this, "密码不能为空", 0).show();
					return;
				}
				if ("".equals(phone)) {
					Toast.makeText(RegisterActivity.this, "电话不能为空 ", 0).show();
					return;
				}
				if (!pwd.equals(pwd2)) {
					Toast.makeText(RegisterActivity.this, "两次密码不相同", 0).show();
					return;
				}
				if (picturePath==null || "".equals(picturePath)){

					Toast.makeText(RegisterActivity.this, "请选择图片作为头像", 0).show();
					return;
				}

				// 传数据到服务器
				final Map<String, String> parames = new HashMap<String, String>();
				parames.put("name", name);
				parames.put("phone", phone);
				parames.put("email", email);
				parames.put("password", pwd);
				
				final String hostUrl = getResources().getString(R.string.hostUrl);
				new AsyncTask<String, Double, String>() {

					@Override
					protected void onPostExecute(String result) {
						if (result==null || "".equals(result)){
							Toast.makeText(getApplicationContext(), "注册失败", 0).show();
						}else if ("ok".equals(result)){
							Toast.makeText(getApplicationContext(), "注册成功，请登陆", 0).show();
							Intent intent = new Intent(RegisterActivity.this,
									LoginActivity.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(intent);
							finish();
							overridePendingTransition(R.anim.enter_lefttoright,
									R.anim.out_lefttoright);
						}
						super.onPostExecute(result);
					}

					@Override
					protected String doInBackground(String... arg0) {
						String result = null;
						try {
							result = DataService.sendDataByHttpClientPostForRegister(hostUrl+"studentAction_add.action", parames, picturePath);
							Log.i(TAG, "result = "+result);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return result;
					}
				}.execute("");
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.foodinfo_menu, menu);
		menu.removeItem(R.id.menu_share);
		menu.removeItem(R.id.menu_search);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; go home
			finish();
			overridePendingTransition(R.anim.enter_lefttoright,
					R.anim.out_lefttoright);
			return true;
		case R.id.menu_search:
			Intent intent = new Intent(RegisterActivity.this,
					SearchActivity.class);
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
			Intent intent1 = new Intent(RegisterActivity.this,
					LoginActivity.class);
			intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent1);
			return true;
		case R.id.menu_user_register:
			Intent intent111 = new Intent(RegisterActivity.this,
					RegisterActivity.class);
			intent111.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent111);
			return true;
		case R.id.menu_soft_setting:
			Intent intent2 = new Intent(RegisterActivity.this,
					SetPreferenceActivity.class);
			intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent2);
			return true;
		case R.id.menu_offer_suggest:
			Intent intent3 = new Intent(RegisterActivity.this,
					OfferSuggestActivity.class);
			intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent3);
			return true;
		case R.id.menu_about_us:
			Intent intent4 = new Intent(RegisterActivity.this,
					AboutUsActivity.class);
			intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent4);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
