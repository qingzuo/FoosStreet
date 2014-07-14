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

/**
 * 图片适配器，为gallery添加适配器，用于展示图片
 * @author zuo
 *
 */
public class HomeImageAdapter extends BaseAdapter {
	private Context mContext;
	private int[] picture;

	public HomeImageAdapter(Context c, int[] picture) {
		mContext = c;
		this.picture = picture;
	}

	public int getCount() {
		return picture.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new ImageView(mContext);
		imageView.setImageResource(picture[position % picture.length]);
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setLayoutParams(new Gallery.LayoutParams(
				Gallery.LayoutParams.WRAP_CONTENT,
				Gallery.LayoutParams.MATCH_PARENT));
		return imageView;
	}

}