package com.jsu.campusordermeal.ui;

import java.util.ArrayList;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.adapter.Tab6IFindAdapter;
import com.jsu.campusordermeal.adapter.Tab6WeFindAdapter;
import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.dao.UserInfo;
import com.jsu.campusordermeal.db.DBOperate;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Tab6IFindActivity extends Activity {
	private ListView tab6_ifind_list;;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab6_ifind_activity);
		
		tab6_ifind_list = (ListView)findViewById(R.id.tab6_ifind_list);
		
		ArrayList<FoodInfo> data = DBOperate.getSortDataFromDB(this);
		tab6_ifind_list.setAdapter(new Tab6IFindAdapter(this, data));
	}
}
