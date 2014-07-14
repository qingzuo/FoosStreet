package com.jsu.campusordermeal.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jsu.campusordermeal.MyApplication;
import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.service.ImageOperate;
import com.jsu.campusordermeal.ui.ShowFoodInfoActivity;
import com.jsu.campusordermeal.ui.TabActivity1;
import com.jsu.campusordermeal.util.DownloadImageUtil;
import com.jsu.campusordermeal.util.JsonDownloadUtil;
import com.jsu.campusordermeal.util.UserFormManage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Tab1BaseAdapter extends BaseAdapter {
	private static final String TAG = "Tab1BaseAdapter";
	private List<FoodInfo> data;
	private LayoutInflater inflater;
	private Context context;

	public final class ViewHolder {
		public TextView name; // 菜的名字
		public ImageView image; // 菜的logo
		public Button add; // 加入菜单按钮
		public Button more; // 加入菜单按钮
		public TextView content; // 菜的简介
		public TextView price; // 价格
		public RatingBar bar; // 几星
	}

	public Tab1BaseAdapter(Context context, List<FoodInfo> datafood) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		// init data
		data = datafood;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.tab1_list_adapter_layout,
					null);
			holder.name = (TextView) convertView
					.findViewById(R.id.tab1_list_item_title);
			holder.image = (ImageView) convertView
					.findViewById(R.id.tab1_list_item_image);
			holder.add = (Button) convertView
					.findViewById(R.id.tab1_list_item_add);
			holder.more = (Button) convertView
					.findViewById(R.id.tab1_list_item_more);
			holder.content = (TextView) convertView
					.findViewById(R.id.tab1_list_item_describe);
			holder.price = (TextView) convertView
					.findViewById(R.id.tab1_list_item_food_price);
			holder.bar = (RatingBar) convertView
					.findViewById(R.id.tab1_list_item_rating_bar);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.name.setText(data.get(position).getName());
//		holder.image.setImageBitmap(TabActivity1.allImage.get(data.get(position).get_id()));
		
		try {
			holder.image.setImageBitmap(ImageOperate.getImageFromExternalStorage(this.context, data.get(position).getIconPath()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		holder.content.setText(data.get(position).getBrief_introduction());
		holder.price.setText("价格：" + data.get(position).getPrice());
		holder.bar.setProgress((int)data.get(position).getStart()*2);
		holder.add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				addFood(data.get(position).get_id());
//				MyApplication.OrderFoods.add(data.get(position));
//				MyApplication.OrderFoodsImage.add(TabActivity1.allImage.get(data.get(position).get_id()));
//				Toast.makeText(Tab1BaseAdapter.this.context, "加入成功", 0).show();
			}
		});
		holder.more.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Tab1BaseAdapter.this.context, ShowFoodInfoActivity.class);
				Bundle mBundle = new Bundle();  
		        mBundle.putParcelable("foodInfo", data.get(position));
		        intent.putExtras(mBundle);
		        Tab1BaseAdapter.this.context.startActivity(intent);
			}
		});

		return convertView;
	}

	public void showMore() {
		Toast.makeText(this.context, "more", 0).show();
	}

	public void addFood(int id) {
		if (UserFormManage.insertFood(this.context, id)==1){
			Toast.makeText(this.context, "加入成功", 0).show();
		}else{
			Toast.makeText(this.context, "亲，已经订购了", 0).show();
		}
	}

	public void deleteFood(int id) {
		UserFormManage.deleteFood(this.context, id);
		Toast.makeText(this.context, "退订成功", 0).show();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();// data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

}
