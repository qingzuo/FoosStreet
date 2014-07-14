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

	private TextView tv_healthtest_state; // �ײ�״̬��
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

	String[] questionArray = {"", "��ϲ����������˵����", "����ɫ�ް������׳��ְֺ���", "�������к���Ȧ��",
			"���ڴ���ɫƫ����", "���ڴ�����ɫ��һ���˺���", "��Ƥ����ڴ�����", "���沿��ȧ�����ƫ����",
			"����ȧ����ϸ΢��˿��", "����������������", "���ж��֬���ڶ��������",
			"���������ȱ����ף�����������΢¡���������", "���沿��ǲ�������л�������������", "���������������",
			"����Ƥ���ڲ�֪�����л�����������ߣ�Ƥ�³�Ѫ����", "����Ƥ��һץ�ͺ죬������ץ����	"};

	public void OnHealthTestClick(View v) {
		int state = Integer.parseInt(tv_healthtest_state.getText().toString());
		switch (v.getId()) {
		case R.id.bt_healthtest_pre:
			if (state == 1){
				Toast.makeText(this, "�Ѿ��ǵ�һ����", 0).show();
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
			// ������
			if (state == 15){
				// ����
				Toast.makeText(this, "�鿴���", 0).show();
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
		bar.setTitle("���ʲ���");
		// ����
		setContentView(R.layout.health_test_activity);
		
		initView();
	}

	/**
	 * ��ʼ�����
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
