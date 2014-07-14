package com.jsu.campusordermeal.adapter;

import java.util.ArrayList;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.service.ImageOperate;
import com.jsu.campusordermeal.ui.ShowFoodInfoActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Tab5GridAdapter extends BaseAdapter {
	private static final String TAG = "SortGridViewAdapter";
	private LayoutInflater inflater;
	private Context context;
	private ArrayList<FoodInfo> data;

	public final class ViewHolder {
		public TextView name; // 菜的名字
		public ImageView image; // 菜的logo
		public ImageButton button; // 加入菜单按钮
		public TextView content; // 菜的简介
		public TextView price; // 价格
		public TextView price2; // 价格
	}
	
	public Tab5GridAdapter(Context context, ArrayList<FoodInfo> data) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		//init data
		this.data = data;  
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.tab5_gridview_adapter_layout,
					null);
			holder.name = (TextView) convertView
					.findViewById(R.id.tab5_gridview_adapter_layout_name);
			holder.image = (ImageView) convertView
					.findViewById(R.id.tab5_gridview_adapter_layout_image);
			holder.button = (ImageButton) convertView
					.findViewById(R.id.tab5_gridview_adapter_button);
			holder.content = (TextView) convertView
					.findViewById(R.id.tab5_gridview_adapter_layout_describe);
			holder.price = (TextView) convertView
					.findViewById(R.id.tab5_gridview_adapter_layout_price);
			holder.price2 = (TextView) convertView
					.findViewById(R.id.tab5_gridview_adapter_layout_price2);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.name.setText(data.get(position).getName());
		try {
			holder.image.setImageBitmap(ImageOperate.getImageFromExternalStorage(this.context, data.get(position).getIconPath()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		holder.content.setText("订购次数："+(int)(data.get(position).getPrice()*3));
		holder.price.setText("价格:" + data.get(position).getPrice());
		holder.price2.setText("特价:" + data.get(position).getSpecial_offer());
		holder.button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Tab5GridAdapter.this.context, ShowFoodInfoActivity.class);
				Bundle mBundle = new Bundle();  
		        mBundle.putParcelable("foodInfo", data.get(position));
		        intent.putExtras(mBundle);
		        Tab5GridAdapter.this.context.startActivity(intent);
			}
		});

		return convertView;
	}

}
