package com.jsu.campusordermeal.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.dao.FoodInfo;
import com.jsu.campusordermeal.service.SendOrder2Net;
import com.jsu.campusordermeal.util.ObjectToJson;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * �ύ�������棬
 * 
 * @author zuo
 * 
 */
public class SubmitMenuActivity extends Activity implements OnClickListener {
	private static final String TAG = "SubmitMenuActivity";
	private List<FoodInfo> foods;
	private TextView submit_total_food_number;
	private TextView submit_total_price;
	private CheckBox submit_checkbox;
	private EditText submit_address;
	private EditText submit_remark;
	private Button submit_button;
	private ImageButton show_foodinfo_top_back;
	private ImageButton main_top_search;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.submit_menu_activity);
		// ���ò�����
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.action_bar));
		bar.setDisplayHomeAsUpEnabled(true);
		bar.setTitle("�ύ����");

		foods = TabActivity3.data;

		initView();
		startService();
	}

	private void initView() {
		submit_total_food_number = (TextView) findViewById(R.id.submit_total_food_number);
		submit_total_price = (TextView) findViewById(R.id.submit_total_price);
		submit_checkbox = (CheckBox) findViewById(R.id.submit_checkbox);
		submit_address = (EditText) findViewById(R.id.submit_address);
		submit_remark = (EditText) findViewById(R.id.submit_remark);
		submit_button = (Button) findViewById(R.id.submit_button);

		submit_button.setOnClickListener(this);
		submit_checkbox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							submit_address.setEnabled(true);
						} else {
							submit_address.setEnabled(false);
						}
					}
				});

	}

	private void startService() {
		int foodnum = 0;
		float foodPrices = 0.0f;
		StringBuffer foodAllName = new StringBuffer("");
		if (foods == null | foods.size() == 0) {
			Toast.makeText(this, "û��ʳ��", 0).show();
		} else {
			foodnum = foods.size();
			for (FoodInfo food : foods) {
				foodPrices += food.getPrice();
				foodAllName.append(food.getName() + ",");
			}
		}

		submit_total_food_number.setText("�˵�������" + foodnum + " ��");
		submit_total_price.setText("�ܼ۸�" + foodPrices + " Ԫ");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.submit_button:
			Toast.makeText(this, "�����ύ�ɹ�", 0).show();
			submit_button.setEnabled(false);
			// ���Ͷ�����������
			new AsyncTask<String, Double, String>() {

				@Override
				protected void onPostExecute(String result) {
					if ("ok".equals(result)){
						Toast.makeText(getApplicationContext(), "�ύ�ɹ�", 0).show();
					}

					super.onPostExecute(result);
				}

				@Override
				protected String doInBackground(String... params) {
					try {
						for (FoodInfo info : foods) {
							Log.i(TAG, "id = "+info.get_id());
							boolean isOk = SendOrder2Net
									.send("http://192.168.1.110:8080/CampusOrderMeal/goodsAction_add2Order",
											info.get_id());
							if (!isOk) return "bad";
						}
						return "ok";
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return "bad";
				}
			}.execute("");

			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; go home
			finish();
			overridePendingTransition(R.anim.enter_lefttoright,
					R.anim.out_lefttoright);
			return true;
		case R.id.menu_search:
			Intent intent = new Intent(SubmitMenuActivity.this,
					SearchActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		case R.id.menu_share:
			Intent intent11 = new Intent(Intent.ACTION_SEND);

			intent11.setType("text/plain");
			intent11.putExtra(Intent.EXTRA_SUBJECT, "����");
			intent11.putExtra(Intent.EXTRA_TEXT,
					"��ʳ���������������㣬���ص�ַhttp://w3.school.com/app/xiaoyuandincan.app");
			startActivity(Intent.createChooser(intent11, getTitle()));
			return true;
			// ���²��ǲ������Ĳ˵���Ӧ
		case R.id.menu_user_login:
			Intent intent1 = new Intent(SubmitMenuActivity.this,
					LoginActivity.class);
			intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent1);
			return true;
		case R.id.menu_user_register:
			Intent intent111 = new Intent(SubmitMenuActivity.this,
					RegisterActivity.class);
			intent111.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent111);
			return true;
		case R.id.menu_soft_setting:
			Intent intent2 = new Intent(SubmitMenuActivity.this,
					SetPreferenceActivity.class);
			intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent2);
			return true;
		case R.id.menu_offer_suggest:
			Intent intent3 = new Intent(SubmitMenuActivity.this,
					OfferSuggestActivity.class);
			intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent3);
			return true;
		case R.id.menu_about_us:
			Intent intent4 = new Intent(SubmitMenuActivity.this,
					AboutUsActivity.class);
			intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent4);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
