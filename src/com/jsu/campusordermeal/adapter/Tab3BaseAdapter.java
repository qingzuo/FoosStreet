package com.jsu.campusordermeal.adapter;

import java.util.ArrayList;
import java.util.List;

import com.jsu.campusordermeal.MyApplication;
import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.service.ImageOperate;
import com.jsu.campusordermeal.ui.TabActivity3;
import com.jsu.campusordermeal.util.UserFormManage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Tab3BaseAdapter extends BaseAdapter {
	private static final String TAG = "Tab1BaseAdapter";
	private List<FoodInfo> data = new ArrayList<FoodInfo>();
	private LayoutInflater inflater;
	private Context context;
	private TextView number;
	private TextView money;

	public Tab3BaseAdapter(Context context, List<FoodInfo> data, TextView number, TextView money) {
		this.context = context;
		this.number = number;
		this.money = money;
		inflater = LayoutInflater.from(context);
		// init data
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
		public Button add; // 加入菜单按钮
		public TextView content; // 菜的简介
		public TextView price; // 价格
		public RatingBar bar; // 几星
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.tab3_list_adapter_layout, null);
			holder.name = (TextView) convertView.findViewById(R.id.tab3_list_item_title);
			holder.image = (ImageView) convertView
					.findViewById(R.id.tab3_list_item_image);
			holder.add = (Button) convertView
					.findViewById(R.id.tab3_list_item_cancel);
			holder.content = (TextView) convertView
					.findViewById(R.id.tab3_list_item_describe);
			holder.price = (TextView) convertView
					.findViewById(R.id.tab3_list_item_food_price);
			holder.bar = (RatingBar) convertView
					.findViewById(R.id.tab3_list_item_rating_bar);
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
//		holder.image.setImageBitmap(MyApplication.OrderFoodsImage.get(position));
		holder.content.setText(data.get(position).getBrief_introduction());
		holder.price.setText("价格：" + data.get(position).getPrice());
		holder.bar.setProgress((int) (2 * data.get(position).getStart()));
		holder.add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// remove position item
				removeItem(position);
			}
		});

		return convertView;
	}
	
	public void refresh(List<FoodInfo> data){
		// refresh data、
		this.data = data;
		notifyDataSetChanged();
	}
	
	public void refresh(){
		// refresh data
		notifyDataSetChanged();
	}

	public void removeItem(int position) {
		int id = data.get(position).get_id();
		UserFormManage.deleteFood(this.context, id);
		data.remove(position);
		refresh();
		updateFoodMenu();
	}
	
	//更新菜的总数量和总价钱
	public void updateFoodMenu()
	{
		//get data
		int foodNum = 0;
		float foodPrice = 0.0f;
		if (data==null || data.size()==0){
			
		}else{
			foodNum = data.size();
			for (FoodInfo food:data){
				foodPrice += food.getPrice();
			}
		}
		//update
		number.setText("共点"+foodNum+"个菜");
		money.setText("总价钱"+foodPrice+"元");
	}
}
