package com.jsu.campusordermeal.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Gallery.LayoutParams;

import com.jsu.campusordermeal.R;

public class ImageAdapter extends BaseAdapter {
	private ArrayList<Bitmap> data;
	private Context mContext;

	public ImageAdapter(Context c, ArrayList<Bitmap> data) {
		mContext = c;
		this.data = data;
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView i = new ImageView(mContext);

		i.setImageBitmap(data.get(position));
		i.setAdjustViewBounds(true);
		i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		i.setBackgroundResource(R.drawable.ic_launcher);

		return i;
	}

}