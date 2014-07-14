package com.jsu.campusordermeal.ui;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.service.CopyAssetsFileToSDCard;

import android.net.Uri;
import android.net.NetworkInfo.State;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.R.layout;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends Activity {
	private static final String TAG = "SplashActivity";
	private LinearLayout ll_splash_main;
	private TextView tv_splash_version;
	private String version;

	private String copyDBToPath;
	private String copyDbFromPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);

		AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);
		aa.setDuration(1500);
		ll_splash_main = (LinearLayout) findViewById(R.id.ll_splash_main);
		ll_splash_main.setAnimation(aa);

		tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);
		version = getVersion();
		tv_splash_version.setText("�汾�� " + version);

		// �õ�ǰ��activity��ʱ������ ������
		new Thread() {

			@Override
			public void run() {
				super.run();
				try {
					sleep(700);
					loadMainUI();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}.start();

		// �ж��Ƿ���sdcard
		if (android.os.Environment.getExternalStorageState().equals(  
    android.os.Environment.MEDIA_MOUNTED)){
			
		}else{
			Toast.makeText(this, "SDcard�����ã�����SDcard", 0).show();
			finish();
			overridePendingTransition(R.anim.enter_righttoleft, R.anim.out_righttoleft);
		}
		copyDBToPath = Environment.getExternalStorageDirectory()
				+ "/CampusOrderMeal/db/MyDB.db";
		copyDbFromPath = "MyDB.db";

		AsyncTask<String, Float, String> asyncTask = new AsyncTask<String, Float, String>() {

			@Override
			protected void onPostExecute(String result) {
				// �������
				if ("Yes".equals(result)) {
					Log.i(TAG, "������ݿ⸴��");
				} else {

					Log.i(TAG, "���ݿ⸴��ʧ��");
				}
				super.onPostExecute(result);
			}

			@Override
			protected String doInBackground(String... params) {
				// ��ʼ�������ݿ�
				try {
					CopyAssetsFileToSDCard.copyDBToSdCard(SplashActivity.this,
							copyDbFromPath, copyDBToPath);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return "No";
				}
				return "Yes";
			}
		};

		File file = new File(copyDBToPath);
		if (!file.exists()) {
			// ���ܵ�½��Ҫ���ƺ����ݿ���ܵ�½
			File newFile = new File(Environment.getExternalStorageDirectory()
					+ "/CampusOrderMeal/db/");
			if (!newFile.exists()) {
				newFile.mkdirs();
			}
			Toast.makeText(this, "��ʼ��", 1).show();
			asyncTask.execute("");
		}
	}

	private void loadMainUI() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.enter_righttoleft, R.anim.out_righttoleft);
	}

	private String getVersion() {
		try {
			PackageManager manager = getPackageManager();
			PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
			return info.versionName;
		} catch (Exception e) {
			e.printStackTrace();
			return "�汾��δ֪";
		}
	}

}
