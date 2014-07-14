package com.jsu.campusordermeal.adapter;

import java.io.File;
import java.util.ArrayList;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.service.ImageOperate;
import com.jsu.campusordermeal.ui.ShowFoodInfoActivity;
import com.jsu.campusordermeal.util.UserFormManage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Tab4BaseAdapter extends BaseAdapter {
	private static final String TAG = "Tab1BaseAdapter";
	private ArrayList<FoodInfo> data;
	private LayoutInflater inflater;
	private Context context;

	public Tab4BaseAdapter(Context context, ArrayList<FoodInfo> data) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		// init data
		this.data = data;  
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

	public final class ViewHolder {
		public TextView name;
		public ImageView image;
		public Button add;
		public Button more;
		public TextView content; // 菜的简介
		public TextView price; // 价格
		public TextView number;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.tab4_list_adapter_layout,
					null);
			holder.name = (TextView) convertView
					.findViewById(R.id.tab4_list_item_title);
			holder.image = (ImageView) convertView
					.findViewById(R.id.tab4_list_item_image);
			holder.add = (Button) convertView
					.findViewById(R.id.tab4_list_item_add);
			holder.more = (Button) convertView
					.findViewById(R.id.tab4_list_item_more);
			holder.content = (TextView) convertView
					.findViewById(R.id.tab4_list_item_describe);
			holder.price = (TextView) convertView
					.findViewById(R.id.tab4_list_item_food_price);
			holder.number = (TextView) convertView
					.findViewById(R.id.tab4_list_item_order_number);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.name.setText((position+1)+"."+data.get(position).getName());
		try {
			holder.image.setImageBitmap(ImageOperate.getImageFromExternalStorage(this.context, data.get(position).getIconPath()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		holder.content.setText(data.get(position).getCharacteristic());
		holder.price.setText("价格：" + data.get(position).getPrice());
		holder.number.setText(Html.fromHtml("订购次数：<font color=#ff0000>"+data.get(position).getOrder_time()+"</font>"));
		holder.add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				addFood(data.get(position).get_id());
				/*Button button = (Button) v;
				if ("加入订单".equals(button.getText())) {
					// add a food to foodMenu
					addFood(data.get(position).get_id());
					button.setText("退订订单");
				} else {
					deleteFood(data.get(position).get_id());
					button.setText("加入订单");
				}*/
			}
		});
		holder.more.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Tab4BaseAdapter.this.context, ShowFoodInfoActivity.class);
				Bundle mBundle = new Bundle();  
		        mBundle.putParcelable("foodInfo", data.get(position));
		        intent.putExtras(mBundle);
		        Tab4BaseAdapter.this.context.startActivity(intent);
			}
		});

		return convertView;
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

}
