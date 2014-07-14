package com.jsu.campusordermeal.adapter;

import java.util.ArrayList;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.adapter.Tab6IFindAdapter.ViewHolder;
import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.service.ImageOperate;
import com.jsu.campusordermeal.ui.ShowFoodInfoActivity;
import com.jsu.campusordermeal.util.UserFormManage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Tab6WeFindAdapter extends BaseAdapter {
	private ArrayList<FoodInfo> data;
	private LayoutInflater inflater;
	private Context context;

	public final class ViewHolder {
		public TextView name;
		public ImageView image; // 菜的logo
		public ImageButton hold; // 加入菜单按钮
		public TextView content; // 菜的简介
		public TextView number; // 支持数量
		public TextView source; // 来源
	}

	public Tab6WeFindAdapter(Context context, ArrayList<FoodInfo> data) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		// init data
		this.data = data;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.tab6_wefind_list_item_layout,
					null);
			holder.name = (TextView) convertView
					.findViewById(R.id.tab6_list_item_title);
			holder.image = (ImageView) convertView
					.findViewById(R.id.tab6_list_item_image);
			holder.hold = (ImageButton) convertView
					.findViewById(R.id.tab6_list_item_hold);
			holder.content = (TextView) convertView
					.findViewById(R.id.tab6_list_item_describe);
			holder.number = (TextView) convertView
					.findViewById(R.id.tab6_list_item_hold_number);
			holder.source = (TextView) convertView
					.findViewById(R.id.tab6_list_item_source);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		try {
			holder.image.setImageBitmap(ImageOperate.getImageFromExternalStorage(this.context, data.get(position).getIconPath()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		holder.name.setText(data.get(position).getName());
		holder.content.setText("  " + data.get(position).getDetailed_introduction());
		holder.number.setText("支持数量：" + (int)data.get(position).getPrice()*20+"+");
		holder.source.setText(data.get(position).getSource());
		holder.hold.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(Tab6WeFindAdapter.this.context, "hold", 0).show();
			}
		});

		return convertView;
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

