package com.jsu.campusordermeal.ui;

import com.jsu.campusordermeal.R;
import com.jsu.campusordermeal.adapter.Tab1BaseAdapter.ViewHolder;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class HealthDietActivity extends Activity {
	private ListView lv_healthdiet_foods;

	private String[] titleArray = { "��ҽ��ʳ�������Ĵ���ɫ", "�����֮��������ʳ��", "ʳ���ؼ�������ʳ�н�",
			"��ʳ�ơ���ҩ��", "�ི����ʳ�����磬��ʳ���˳٣�ҹʳ�������ԭ��", "�����֮��������ʳ��", "ʳ���ؼ�������ʳ�н�",
			"��ʳ�ơ���ҩ��", "�ི����ʳ�����磬��ʳ���˳٣�ҹʳ�������ԭ��" };
	private String[] bodyArray = {
			"�׻�˵��������ʳΪ�족������ũ�ϳ��ٲݵ���ζ��ˮȪ�ĸʿ࣬�������ҹ�Զ������Ѱ����ʶҩ����ʳ�ﳤ��ʵ����Ե�ʳ��һҳ��",
			"���ҽѧһֱ��Ϊ��Ƣθ������ĺ���֮�����ʳ��������ر���ʳ������Ҳ����ࡢ���꿪ʼ��������ʳ�����Ա���ƢθʵΪ��������֮�󷨡���ζ�ʵ���Ҳ�����������࣬��Ȱ�˾����ٳ����䡢���ȡ��ػ���������ʳ������������Ƣθ�����ܳ��������˻���������Ƣθ�����Ĺ��ܣ���ǡ����ʳ������������١�",
			"������ʳ��Ҫ��ؼ����ڡ����١��󡢽����ɡ����֡���ʳƷ����ǡ��������ʳ�����˹�����ÿ��������ʳ����Ʒ�෱�࣬Ҫʮ��ע�����õ���ʳϰ�ߺͽ����������������ȼ���ʳ��ʳ��������δ����ֹ���ȿʶ������������࣬������ҹ���ȡ����⣬����ƫʳ����ʳҲ�����ˡ�",
			"ʳ����ȴ���μ����������ڳ���ʹ�á�������������ˣ����������˥������Ѫ���𣬼�֮Ƣθ�˻����ܼ��ˣ���������ʳ���θ���ȡ����ҩ�����ѻ񼰵Ĺ�Ч���Ǵ������������˻��г̶Ȳ�һ�����Բ�������������һ���Ѽ�ֳ��ڷ�ҩ�������еĲ�̫ϰ�ߣ������׷���������Ӧ������ʳ�ƶ����Ҫʱ��ҩ����׵���",
			"ʳ��ϸ�����ʣ��ɻ������̣�����ѡʳ�ͽ�����ʳ���Ը��ܡ����͡����ȡ�ճӲ�����������˳���Ũ����ζ��ʳ�����ٽ�����ʳ�����ˣ���������ʳ��Ϊ�ã�ʳ��ů����ů�಻��̫�̿ڣ����Ȳ��ƴ����䲻����Ϊ�ˣ���Ӳ����͡�����֮��Ʒ����������ʳ���������˸�ֱ��",
			"���ҽѧһֱ��Ϊ��Ƣθ������ĺ���֮�����ʳ��������ر���ʳ������Ҳ����ࡢ���꿪ʼ��������ʳ�����Ա���ƢθʵΪ��������֮�󷨡���ζ�ʵ���Ҳ�����������࣬��Ȱ�˾����ٳ����䡢���ȡ��ػ���������ʳ������������Ƣθ�����ܳ��������˻���������Ƣθ�����Ĺ��ܣ���ǡ����ʳ������������١�",
			"������ʳ��Ҫ��ؼ����ڡ����١��󡢽����ɡ����֡���ʳƷ����ǡ��������ʳ�����˹�����ÿ��������ʳ����Ʒ�෱�࣬Ҫʮ��ע�����õ���ʳϰ�ߺͽ����������������ȼ���ʳ��ʳ��������δ����ֹ���ȿʶ������������࣬������ҹ���ȡ����⣬����ƫʳ����ʳҲ�����ˡ�",
			"ʳ����ȴ���μ����������ڳ���ʹ�á�������������ˣ����������˥������Ѫ���𣬼�֮Ƣθ�˻����ܼ��ˣ���������ʳ���θ���ȡ����ҩ�����ѻ񼰵Ĺ�Ч���Ǵ������������˻��г̶Ȳ�һ�����Բ�������������һ���Ѽ�ֳ��ڷ�ҩ�������еĲ�̫ϰ�ߣ������׷���������Ӧ������ʳ�ƶ����Ҫʱ��ҩ����׵���",
			"ʳ��ϸ�����ʣ��ɻ������̣�����ѡʳ�ͽ�����ʳ���Ը��ܡ����͡����ȡ�ճӲ�����������˳���Ũ����ζ��ʳ�����ٽ�����ʳ�����ˣ���������ʳ��Ϊ�ã�ʳ��ů����ů�಻��̫�̿ڣ����Ȳ��ƴ����䲻����Ϊ�ˣ���Ӳ����͡�����֮��Ʒ����������ʳ���������˸�ֱ��" };

	public void onHealDietClick(View v) {
		switch (v.getId()) {
		case R.id.bt_healthdiet_healthTest:
			Intent intent = new Intent(HealthDietActivity.this,
					HealthTestActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ����
		setContentView(R.layout.health_diet_activity);

		lv_healthdiet_foods = (ListView) findViewById(R.id.lv_healthdiet_foods);

		lv_healthdiet_foods.setAdapter(new MyBaseAdapter());
	}
	
	private class MyBaseAdapter extends BaseAdapter{

		public final class ViewHolder {
			public TextView title; 
			public Button more; 
			public TextView content; 
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return titleArray.length;
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

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null){
				holder = new ViewHolder();
				convertView = View.inflate(HealthDietActivity.this, R.layout.health_diet_item, null);
				holder.title = (TextView) convertView.findViewById(R.id.tv_healthdiet_title);
				holder.content = (TextView)convertView.findViewById(R.id.tv_healthdiet_content);
				holder.more = (Button)convertView.findViewById(R.id.bt_healthdiet_more);
				convertView.setTag(holder);
			}else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			
			holder.title.setText(titleArray[position]);
			holder.content.setText(bodyArray[position]);
			holder.more.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(HealthDietActivity.this, "test", 0).show();
				}
			});
			
			return convertView;
		}
		
	}
}
