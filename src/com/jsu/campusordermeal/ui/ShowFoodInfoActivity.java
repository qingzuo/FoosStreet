package com.jsu.campusordermeal.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.adapter.ImageAdapter;
import com.jsu.campusordermeal.adapter.Tab1BaseAdapter;
import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.service.ImageOperate;
import com.jsu.campusordermeal.ui.base.BaseActivity;
import com.jsu.campusordermeal.util.DownloadImageUtil;
import com.jsu.campusordermeal.util.JsonDownloadUtil;
import com.jsu.campusordermeal.util.UserFormManage;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ShowFoodInfoActivity extends BaseActivity implements
		OnClickListener {
	protected static final String TAG = "ShowFoodInfoActivity";
	private FoodInfo foodInfo;
	// 顶部的控件
	private ImageView show_foodinfo_middle_image;
	private TextView show_foodinfo_middle_title;
	private TextView show_foodinfo_middle_describe;
	private TextView show_foodinfo_middle_price;
	private Button show_foodinfo_middle_add;

	// 图片展示区域控件
	private Gallery show_foodinfo_gallery;
	// private ImageView show_foodinfo_imageshow;
	private RatingBar show_foodinfo_rating_bar;
	private TextView show_foodinfo_foodtime;
	private TextView show_foodinfo_order_time;
	private TextView show_foodinfo_price;

	// 详细介绍区域控件
	private TextView show_foodinfo_detailed_introduction; // 纤细介绍

	// 菜的基本信息控件
	private TextView show_food_characteristic;
	private TextView show_food_source;
	private TextView show_food_making;

	// private TextView show_food_need_time;
	
	private List<Bitmap> bitmapList = new ArrayList<Bitmap>();

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_foodinfo_layout);
		// 设置操作栏
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.action_bar));
		bar.setDisplayHomeAsUpEnabled(true);

		// get food
		foodInfo = getIntent().getParcelableExtra("foodInfo");

		initView();
	}

	private void initView() {
		//
		show_foodinfo_middle_image = (ImageView) findViewById(R.id.show_foodinfo_middle_image);
		show_foodinfo_middle_title = (TextView) findViewById(R.id.show_foodinfo_middle_title);
		show_foodinfo_middle_describe = (TextView) findViewById(R.id.show_foodinfo_middle_describe);
		show_foodinfo_middle_price = (TextView) findViewById(R.id.show_foodinfo_middle_price);
		show_foodinfo_middle_add = (Button) findViewById(R.id.show_foodinfo_middle_add);

		// 图片展示区域控件
		show_foodinfo_gallery = (Gallery) findViewById(R.id.show_foodinfo_gallery);
		// show_foodinfo_imageshow = (ImageView)
		// findViewById(R.id.show_foodinfo_imageshow);
		show_foodinfo_rating_bar = (RatingBar) findViewById(R.id.show_foodinfo_rating_bar);
		show_foodinfo_foodtime = (TextView) findViewById(R.id.show_foodinfo_foodtime);
		show_foodinfo_order_time = (TextView) findViewById(R.id.show_foodinfo_order_time);
		show_foodinfo_price = (TextView) findViewById(R.id.show_foodinfo_price);

		// 详细介绍区域控件
		show_foodinfo_detailed_introduction = (TextView) findViewById(R.id.show_foodinfo_detailed_introduction); // 纤细介绍

		// 菜的基本信息控件
		show_food_characteristic = (TextView) findViewById(R.id.show_food_characteristic);
		show_food_source = (TextView) findViewById(R.id.show_food_source);
		show_food_making = (TextView) findViewById(R.id.show_food_making);
		// show_food_need_time = (TextView)
		// findViewById(R.id.show_food_need_time);

		// set data
		// top
		try {
			show_foodinfo_middle_image.setImageBitmap(ImageOperate
					.getImageFromExternalStorage(this, foodInfo.getIconPath()));
//			show_foodinfo_middle_image.setImageBitmap(TabActivity1.allImage.get(foodInfo.get_id()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		show_foodinfo_middle_title.setText(foodInfo.getName());
		show_foodinfo_middle_describe.setText(foodInfo.getBrief_introduction());
		show_foodinfo_middle_price.setText(show_foodinfo_middle_price.getText()
				.toString() + foodInfo.getPrice());
		show_foodinfo_middle_add.setOnClickListener(this);

		// 图片展示区域控件
		final ArrayList<Bitmap> data = ImageOperate.getBitmapArraysByFoodId(this, foodInfo.get_id());
		show_foodinfo_gallery.setAdapter(new ImageAdapter(this, data));
		
		/*final ArrayList<Bitmap> data = new ArrayList<Bitmap>();
		new AsyncTask<String, Double, String>() {

			@Override
			protected void onPostExecute(String result) {
				// 为ListView设置Adapter
				show_foodinfo_gallery.setAdapter(new ImageAdapter(ShowFoodInfoActivity.this, data));
				Log.i(TAG, "data size: "+data.size());
				super.onPostExecute(result);
			}

			@Override
			protected String doInBackground(String... params) {
				String hostUrl = ShowFoodInfoActivity.this.getResources().getString(R.string.hostUrl);
				String path = hostUrl+"goodsAction_getGoodsPictures.action?id="+foodInfo.get_id();
				Log.i(TAG, "path: "+path);
				System.out.println("path: "+path);
				try {
					Map<Integer, String> map = JsonDownloadUtil.getImages(path);
					for (int id : map.keySet()){
						String imageUrl = hostUrl+map.get(id);
						byte[] imageByte = DownloadImageUtil.getImage(imageUrl);
						Log.i(TAG, "imageByte: "+imageByte.length);
						System.out.println("imageByte.length: "+imageByte.length);
						data.add(BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("exception: ");
					e.printStackTrace();
				}
				return null;
			}
		}.execute("");*/
		
		show_foodinfo_gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(ShowFoodInfoActivity.this, ShowFoodImagesActivity.class);
				intent.putParcelableArrayListExtra("images", data);	//ImageOperate.getBigBitmapArraysByFoodId(ShowFoodInfoActivity.this, foodInfo.get_id()));
				startActivity(intent);
			}
		});
		/*
		 * try { show_foodinfo_imageshow.setImageBitmap(ImageOperate
		 * .getImageFromExternalStorage(this, foodInfo.getIconPath())); } catch
		 * (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		show_foodinfo_rating_bar.setProgress((int) (foodInfo.getStart() * 2));
		show_foodinfo_foodtime.setText("2013-01-0"
				+ ((int) foodInfo.getStart()));
		show_foodinfo_order_time.setText("订购次数：" + foodInfo.getOrder_time()
				+ "+");
		show_foodinfo_price.setText("价格：" + foodInfo.getPrice() + " RMB");

		// 详细介绍区域控件
		show_foodinfo_detailed_introduction.setText(foodInfo
				.getDetailed_introduction());

		// 菜的基本信息控件
		show_food_characteristic.setText(foodInfo.getCharacteristic());
		show_food_source.setText(foodInfo.getSource());
		show_food_making.setText(foodInfo.getMaking());
		// show_food_need_time.setText(foodInfo.getNeed_time());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.show_foodinfo_middle_add: // 加入订单
			if (UserFormManage.insertFood(this, foodInfo.get_id()) == 1) {
				Toast.makeText(ShowFoodInfoActivity.this, "加入成功", 0).show();
			} else {
				Toast.makeText(ShowFoodInfoActivity.this, "亲，你已经订购了！", 0)
						.show();
			}
			break;

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.foodinfo_menu, menu);

		return true;
	}

	// 需要提前获取是否已经收藏
	private boolean isHeartWhite=true;
	
	/*
	 * 用于更新收藏状态
	 */
	private void initHeartState(){
		// 从服务器查询状态
		
		// 更新状态
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; go home
			finish();
			overridePendingTransition(R.anim.enter_lefttoright, R.anim.out_lefttoright);
			return true;
		case R.id.menu_collect:
			// 如果是白色，改为红心
			if (isHeartWhite){
				item.setIcon(R.drawable.ic_heart_red);
				isHeartWhite = false;
			}else{
				item.setIcon(R.drawable.ic_heart_white);
				isHeartWhite = true;
			}
			// 更改服务器数据，将收藏记录保存
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
			Intent intent1 = new Intent(ShowFoodInfoActivity.this,
					LoginActivity.class);
			intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent1);
			return true;
		case R.id.menu_user_register:
			Intent intent111 = new Intent(ShowFoodInfoActivity.this,
					RegisterActivity.class);
			intent111.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent111);
			return true;
		case R.id.menu_soft_setting:
			Intent intent2 = new Intent(ShowFoodInfoActivity.this,
					SetPreferenceActivity.class);
			intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent2);
			return true;
		case R.id.menu_offer_suggest:
			Intent intent3 = new Intent(ShowFoodInfoActivity.this,
					OfferSuggestActivity.class);
			intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent3);
			return true;
		case R.id.menu_about_us:
			Intent intent4 = new Intent(ShowFoodInfoActivity.this,
					AboutUsActivity.class);
			intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent4);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
