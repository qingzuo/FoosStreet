package com.jsu.campusordermeal.ui;

import java.util.ArrayList;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.adapter.BigImageAdapter;
import com.jsu.campusordermeal.adapter.ImageAdapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Gallery;

public class ShowFoodImagesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.show_food_images_layout);

		ArrayList<Bitmap> data = getIntent().getParcelableArrayListExtra(
				"images");

		Gallery show_food_images_gallery = (Gallery) findViewById(R.id.show_food_images_gallery);
		show_food_images_gallery.setAdapter(new BigImageAdapter(this, data));
	}
}
