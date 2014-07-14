package com.jsu.campusordermeal.ui;

import com.jsu.campusordermeal.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class HealthTestActivity extends Activity {

	private TextView tv_healthtest_state; // 底部状态栏
	private TextView tv_healthtest_question;
	private RadioGroup rg_healthtest_select;
	private RadioButton rb_healthtest_select1;
	private RadioButton rb_healthtest_select2;
	private RadioButton rb_healthtest_select3;
	private RadioButton rb_healthtest_select4;
	private RadioButton rb_healthtest_select5;
	private Button bt_healthtest_pre;
	private Button bt_healthtest_next;
	
	private int selectId = 1;
	public static int[] result = new int[16];

	String[] questionArray = {"", "您喜欢安静懒得说话吗？", "您面色晦暗或容易出现褐斑吗？", "您容易有黑眼圈吗？",
			"您口唇颜色偏暗吗？", "您口唇的颜色比一般人红吗？", "您皮肤或口唇干吗？", "您面部两颧潮红或偏红吗？",
			"您两颧部有细微红丝吗？", "您腹部肥满松软吗？", "您有额部油脂分泌多的现象吗？",
			"您上眼睑比别人肿（上眼睑有轻微隆起的现象）吗？", "您面部或鼻部有油腻感或者油亮发光吗？", "您容易生痤疮或疮疖吗？",
			"您的皮肤在不知不觉中会出现青紫瘀斑（皮下出血）吗？", "您的皮肤一抓就红，并出现抓痕吗？	"};

	public void OnHealthTestClick(View v) {
		int state = Integer.parseInt(tv_healthtest_state.getText().toString());
		switch (v.getId()) {
		case R.id.bt_healthtest_pre:
			if (state == 1){
				Toast.makeText(this, "已经是第一个了", 0).show();
				return ;
			}
			state = state-1;
			if (state==1){
				bt_healthtest_pre.setClickable(false);
			}
			tv_healthtest_state.setText(""+state);
			tv_healthtest_question.setText(questionArray[state]);
			break;
		case R.id.bt_healthtest_next:
			result[state] = selectId;
			// 保存结果
			if (state == 15){
				// 结束
				Toast.makeText(this, "查看结果", 0).show();
				Intent intent = new Intent(this, HealthTestResultActivity.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.enter_righttoleft, R.anim.out_righttoleft);
				return ;
			}
			state = state+1;
			if (state>1){
				bt_healthtest_pre.setClickable(true);
			}
			tv_healthtest_state.setText(""+state);
			tv_healthtest_question.setText(questionArray[state]);
			break;

		default:
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		bar.setTitle("体质测试");
		// 布局
		setContentView(R.layout.health_test_activity);
		
		initView();
	}

	/**
	 * 初始化组件
	 */
	private void initView() {
		tv_healthtest_state = (TextView)findViewById(R.id.tv_healthtest_state); 
		tv_healthtest_question = (TextView)findViewById(R.id.tv_healthtest_question);
		rg_healthtest_select = (RadioGroup)findViewById(R.id.rg_healthtest_select);
		rb_healthtest_select1 = (RadioButton)findViewById(R.id.rb_healthtest_select1);
		rb_healthtest_select2 = (RadioButton)findViewById(R.id.rb_healthtest_select2);
		rb_healthtest_select3 = (RadioButton)findViewById(R.id.rb_healthtest_select3);
		rb_healthtest_select4 = (RadioButton)findViewById(R.id.rb_healthtest_select4);
		rb_healthtest_select5 = (RadioButton)findViewById(R.id.rb_healthtest_select5);
		bt_healthtest_pre = (Button)findViewById(R.id.bt_healthtest_pre);
		bt_healthtest_next = (Button)findViewById(R.id.bt_healthtest_next);

		tv_healthtest_question.setText(questionArray[1]);
		rg_healthtest_select.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				switch (arg0.getCheckedRadioButtonId()) {
				case R.id.rb_healthtest_select1:
					selectId = 1;
					break;
				case R.id.rb_healthtest_select2:
					selectId = 2;
					break;
				case R.id.rb_healthtest_select3:
					selectId = 3;
					break;
				case R.id.rb_healthtest_select4:
					selectId = 4;
					break;
				case R.id.rb_healthtest_select5:
					selectId = 5;
					break;

				default:
					break;
				}
			}
		});
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			overridePendingTransition(R.anim.enter_lefttoright,
					R.anim.out_lefttoright);
			break;

		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
