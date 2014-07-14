package com.jsu.campusordermeal.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsu.campusordermeal.MyApplication;
import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.adapter.Tab3BaseAdapter;
import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.service.ImageOperate;
import com.jsu.campusordermeal.ui.base.BaseActivity;
import com.jsu.campusordermeal.util.DownloadImageUtil;
import com.jsu.campusordermeal.util.UserFormManage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TabActivity3 extends Activity implements OnClickListener {
	private static final String TAG = "TabActivity3";
	private ListView tab3_listview;
	private Button tab3_relative_histore;
	private Button tab3_relative_submit;
	private Button tab3_relative_delete;
	private TextView tab3_relative_total_number;
	private TextView tab3_relative_total_price;
	private ImageView tab3_relative_head;
	private TextView tab3_relative_username;

	private SharedPreferences sp = null;
	private String hostUrl;

	private Tab3BaseAdapter tab3Adapter;
	public static ArrayList<FoodInfo> data = new ArrayList<FoodInfo>();

	public static Map<Integer, Bitmap> allImage = new HashMap<Integer, Bitmap>();

	public void onTab3Click(View v) {
		switch (v.getId()) {
		case R.id.tab3_top_head:
			;
		case R.id.tab3_top_username:
			if (sp.getBoolean("isLogin", false)) {
				// �����û��������
				Intent intent = new Intent(this, UserMOreInfoActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.enter_righttoleft,
						R.anim.out_righttoleft);
			}

			break;

		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "onResume");
		MainActivity.index = 2;
		
//		if (MyApplication.OrderFoods.size() != data.size()){
//			// �����б仯,��������
//			data.clear();
//			for (FoodInfo foodInfo : MyApplication.OrderFoods) {
//				data.add(foodInfo);
//			}
//			tab3Adapter.notifyDataSetChanged();
//		}
		ArrayList<FoodInfo> tem = UserFormManage.getFoods(this);
		if (tem.size() != data.size()){
			data.clear();
			for (FoodInfo food:tem){
				data.add(food);
			}
			tab3Adapter.notifyDataSetChanged();
			updateOrderBaseInfo();
		}
		// �����û���Ϣ
		refreshUserState();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab3);
		Log.i(TAG, "onCreate");

		initView(); //
		sp = getSharedPreferences("UserLoginInfo", Context.MODE_PRIVATE);
		hostUrl = getResources().getString(R.string.hostUrl);
		startService();
	}

	/**
	 * ��ʼ�����֣�����ÿ���������
	 */
	private void initView() {
		tab3_listview = (ListView) findViewById(R.id.tab3_list);
		tab3_relative_histore = (Button) findViewById(R.id.tab3_top_histore);
		tab3_relative_submit = (Button) findViewById(R.id.tab3_top_submit);
		tab3_relative_delete = (Button) findViewById(R.id.tab3_top_delete);
		tab3_relative_total_number = (TextView) findViewById(R.id.tab3_top_total_number);
		tab3_relative_total_price = (TextView) findViewById(R.id.tab3_top_total_price);
		tab3_relative_username = (TextView) findViewById(R.id.tab3_top_username);
		tab3_relative_head = (ImageView) findViewById(R.id.tab3_top_head);

		tab3_listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(TabActivity3.this,
						ShowFoodInfoActivity.class);
				Bundle mBundle = new Bundle();
				mBundle.putParcelable("foodInfo", data.get(position));
				intent.putExtras(mBundle);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		tab3_relative_histore.setOnClickListener(this);
		tab3_relative_submit.setOnClickListener(this);
		tab3_relative_delete.setOnClickListener(this);
	}

	/**
	 * ��ʼ���񣬸������������
	 */
	private void startService() {


		// ���¶���
		// ���õ�����
//		for (FoodInfo foodInfo : MyApplication.OrderFoods) {
//			data.add(foodInfo);
//		}
		data = UserFormManage.getFoods(this);
		tab3Adapter = new Tab3BaseAdapter(TabActivity3.this, data,
				tab3_relative_total_number, tab3_relative_total_price);
		tab3_listview.setAdapter(tab3Adapter);
		// ���¶����ܼ۸��������
		updateOrderBaseInfo();

		// ���¶���
//		asyncTask.execute("");
	}

	// ���¶�����ƷlogoͼƬ���߳�
	AsyncTask<String, String, String> asyncTask = new AsyncTask<String, String, String>() {

		@Override
		protected void onPostExecute(String result) {
			tab3Adapter = new Tab3BaseAdapter(TabActivity3.this, data,
					tab3_relative_total_number, tab3_relative_total_price);
			tab3_listview.setAdapter(tab3Adapter);
			super.onPostExecute(result);
		}

		@Override
		protected String doInBackground(String... arg0) {
			for (int i = 0; i < data.size(); i++) {
				String path = hostUrl + data.get(i).getIconPath();
				try {
					byte[] imageByte = DownloadImageUtil.getImage(path);
					Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0,
							imageByte.length);
					allImage.put(data.get(i).get_id(), bitmap);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return null;
		}
	};

	/**
	 * ����û�״̬�����½���
	 */
	private void refreshUserState() {
		// �ж��Ƿ��½
		if (sp.getBoolean("isLogin", false)) { // ��½����ʾ�û�����
			String name = sp.getString("name", "");
			final String head = sp.getString("head", "");
			tab3_relative_username.setText(name);
			tab3_relative_histore.setText("��ʷ��¼");
			// �������õ�ͼƬ
			new AsyncTask<String, Double, Bitmap>() {

				@Override
				protected void onPostExecute(Bitmap result) {
					tab3_relative_head.setImageBitmap(result);
					super.onPostExecute(result);
				}

				@Override
				protected Bitmap doInBackground(String... params) {
					try {
						byte[] imgByte = DownloadImageUtil.getImage(hostUrl
								+ head);
						Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte,
								0, imgByte.length);
						return bitmap;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
			}.execute("");
		} else { // δ��¼
			tab3_relative_head.setImageResource(R.drawable.ic_launcher);
			tab3_relative_username.setText("�û�δ��¼");
			tab3_relative_histore.setText("��½");
		}
	}

	/**
	 * ���¶��������������ܼ�Ǯ
	 */
	public void updateOrderBaseInfo() {
		// get data
		int foodNum = 0;
		float foodPrice = 0.0f;
		if (data != null || data.size() != 0) {
			foodNum = data.size();
			for (FoodInfo food : data) {
				foodPrice += food.getPrice();
			}
		}
		// update
		tab3_relative_total_number.setText("����" + foodNum + "����");
		tab3_relative_total_price.setText("�ܼ�Ǯ" + foodPrice + "Ԫ");
	}

	@Override
	public void onClick(View v) {
		// Toast.makeText(this, "id:"+((Button)v).getText(), 0).show();
		switch (v.getId()) {
		case R.id.tab3_top_delete:
			Editor editor = sp.edit();
			editor.putBoolean("isLogin", false);
			editor.commit();
			refreshUserState();
			if (data == null || data.size() == 0) {
				return;
			}
			AlertDialog.Builder AD = new AlertDialog.Builder(TabActivity3.this);
			AD.setTitle("����");
			AD.setMessage("ȷ��Ҫɾ�����в���");
			AD.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
//					MyApplication.OrderFoods.clear();
					data.clear();
					TabActivity3.this.tab3Adapter.notifyDataSetChanged();
					Toast.makeText(TabActivity3.this, "ɾ���ɹ�", 0).show();
				}
			});
			AD.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			});

			AD.create().show();
			break;
		case R.id.tab3_top_submit:
			if (!sp.getBoolean("isLogin", false)) {
				Toast.makeText(this, "���ȵ�½", 0).show();// start Login activity
				// Intent intent1 = new Intent(TabActivity3.this,
				// LoginActivity.class);
				// startActivity(intent1);
				return;
			}
			Intent intent = new Intent(TabActivity3.this,
					SubmitMenuActivity.class);
			intent.putParcelableArrayListExtra("food", data);
			startActivity(intent);
			break;
		case R.id.tab3_top_histore:
			if (!sp.getBoolean("isLogin", false)) {
				// start Login activity
				Intent intent1 = new Intent(TabActivity3.this,
						LoginActivity.class);
				startActivity(intent1);
			} else {
				// ������ʷ��¼����
				Intent intent1 = new Intent(TabActivity3.this,
						UserFormHistoryActivity.class);
				startActivity(intent1);
			}
			break;
		}
	}

}
