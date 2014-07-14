package com.jsu.campusordermeal.adapter;

import java.util.ArrayList;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.service.ImageOperate;
import com.jsu.campusordermeal.ui.ShowFoodInfoActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Tab2TypeGridViewAdapter extends BaseAdapter {
	private static final String TAG = "SortGridViewAdapter";
	private LayoutInflater inflater;
	private Context context;
	private ArrayList<FoodInfo> data;
	
	public Tab2TypeGridViewAdapter(Context context, ArrayList<FoodInfo> data) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		//init data
		this.data = data;
	}

	public final class ViewHolder {
		public TextView name; // 菜的名字
		public ImageView image; // 菜的logo
		public ImageButton more; // 加入菜单按钮
		public TextView content; // 菜的简介
		public TextView price; // 价格
		public TextView order_time; // 订购次数
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.tab2_type_grid_adapter_layout,
					null);
			holder.name = (TextView) convertView
					.findViewById(R.id.sort_adapter_layout_name);
			holder.image = (ImageView) convertView
					.findViewById(R.id.sort_adapter_layout_image);
			holder.more = (ImageButton) convertView
					.findViewById(R.id.sort_adapter_layout_more);
			holder.content = (TextView) convertView
					.findViewById(R.id.sort_adapter_layout_describe);
			holder.price = (TextView) convertView
					.findViewById(R.id.sort_adapter_layout_price);
			holder.order_time = (TextView) convertView
					.findViewById(R.id.sort_adapter_layout_order_time);
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
		holder.content.setText(data.get(position).getBrief_introduction());
		holder.price.setText("价格：" + data.get(position).getPrice());
		holder.order_time.setText("订购次数"+data.get(position).getOrder_time());
		holder.more.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Tab2TypeGridViewAdapter.this.context, ShowFoodInfoActivity.class);
				Bundle mBundle = new Bundle();  
		        mBundle.putParcelable("foodInfo", data.get(position));
		        intent.putExtras(mBundle);
		        Tab2TypeGridViewAdapter.this.context.startActivity(intent);
			}
		});

		return convertView;
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

}
