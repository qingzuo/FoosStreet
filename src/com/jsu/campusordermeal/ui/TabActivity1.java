package com.jsu.campusordermeal.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.adapter.HomeImageAdapter;
import com.jsu.campusordermeal.adapter.Tab1BaseAdapter;
import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.db.DBOperate;
import com.jsu.campusordermeal.ui.base.BaseActivity;
import com.jsu.campusordermeal.util.DownloadImageUtil;
import com.jsu.campusordermeal.util.JsonDownloadUtil;
import com.jsu.campusordermeal.widget.MyGallery;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class TabActivity1 extends Activity {
	private MyGallery gl_tab1_image;
	private ListView tab1_list_view;
	private static List<FoodInfo> data = new ArrayList<FoodInfo>();
	private int index = 0;
	
	public static Map<Integer, Bitmap> allImage = new HashMap<Integer, Bitmap>();

	private int topImages[] = { R.drawable.recommend01, R.drawable.recommend02,
			R.drawable.recommend03, R.drawable.recommend04, R.drawable.recommend05};
	private int currentImage = 0;

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				gl_tab1_image.setSelection(index);
				break;
			}
			super.handleMessage(msg);
		}

	};

	@Override
	protected void onResume() {
		super.onResume();
		MainActivity.index = 0;
		// 刷新数据
//		new AsyncTask<String, Double, String>() {
//
//			@Override
//			protected void onPostExecute(String result) {
//				// 为ListView设置Adapter
//				tab1_list_view.setAdapter(new Tab1BaseAdapter(TabActivity1.this, data));
//				super.onPostExecute(result);
//			}
//
//			@Override
//			protected String doInBackground(String... params) {
//				try {
//					String hostUrl = getResources().getString(R.string.hostUrl);
//					data = JsonDownloadUtil.getFoodInfo(hostUrl+"goodsAction_list2.action");
//					for (int i=0; i<data.size(); i++){
//						String path = hostUrl+data.get(i).getIconPath();
//						try {
//							byte[] imageByte = DownloadImageUtil.getImage(path);
//							Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
//							allImage.put(data.get(i).get_id(), bitmap);
//						} catch (Exception e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//					}
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				return null;
//			}
//		}.execute("");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab1);
		initView();

		startService();
	}

	private void startService() {
		// 组合数据
		HomeImageAdapter adapter = new HomeImageAdapter(this,
				topImages);
		gl_tab1_image.setAdapter(adapter);
		gl_tab1_image.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				for (int i = 0; i < parent.getChildCount(); i++) {
					if (parent.getChildAt(i) == view) {
						((ImageView) view).setAlpha(250);
					} else {
						((ImageView) parent.getChildAt(i)).setAlpha(100);
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		gl_tab1_image.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 判断是否选择的是中间的图片
				if (position == gl_tab1_image.getSelectedItemPosition()) {
					Toast.makeText(getApplicationContext(), "惊喜不断" + position, 0)
							.show();
				}
			}

		});
		// 每隔0.5秒改变一次图片
		Timer time = new Timer();
		time.schedule(new TimerTask() {

			@Override
			public void run() {
				Message message = new Message();
				message.what = 1;
				index = gl_tab1_image.getSelectedItemPosition()+1;
				index = index%topImages.length;
				handler.sendMessage(message);
			}
		}, 0, 3500);

		data = DBOperate.getDataFromDB(this);
		tab1_list_view.setAdapter(new Tab1BaseAdapter(TabActivity1.this, data));
//		new AsyncTask<String, Double, String>() {
//
//			@Override
//			protected void onPostExecute(String result) {
//				// 为ListView设置Adapter
//				tab1_list_view.setAdapter(new Tab1BaseAdapter(TabActivity1.this, data));
//				super.onPostExecute(result);
//			}
//
//			@Override
//			protected String doInBackground(String... params) {
//				try {
//					data = JsonDownloadUtil.getFoodInfo("http://192.168.1.100:8080/CampusOrderMeal/goodsAction_list2.action");
//					for (int i=0; i<data.size(); i++){
//						String hostUrl = getResources().getString(R.string.hostUrl);
//						String path = hostUrl+data.get(i).getIconPath();
//						try {
//							byte[] imageByte = DownloadImageUtil.getImage(path);
//							Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
//							allImage.put(data.get(i).get_id(), bitmap);
//						} catch (Exception e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//					}
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				return null;
//			}
//		}.execute("");
		
		

		tab1_list_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(TabActivity1.this,
						ShowFoodInfoActivity.class);
				Bundle mBundle = new Bundle();
				mBundle.putParcelable("foodInfo", data.get(position));
				intent.putExtras(mBundle);
				startActivity(intent);
			}
		});
	}

	private void initView() {
		gl_tab1_image = (MyGallery) findViewById(R.id.gl_tab1_image);
		tab1_list_view = (ListView) findViewById(R.id.tab1_list_view);

	}

}
