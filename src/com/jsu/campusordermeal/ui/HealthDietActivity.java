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

	private String[] titleArray = { "中医饮食养生的四大特色", "因后天之本，及早食养", "食养关键在于饮食有节",
			"先食疗、后药饵", "多讲究早食常宜早，晚食不宜迟，夜食反多损的原则", "因后天之本，及早食养", "食养关键在于饮食有节",
			"先食疗、后药饵", "多讲究早食常宜早，晚食不宜迟，夜食反多损的原则" };
	private String[] bodyArray = {
			"俗话说：“民以食为天”，自神农氏尝百草的滋味、水泉的甘苦，开拓了我国远古人找寻和认识药物与食物长期实践光辉的食养一页。",
			"祖国医学一直认为，脾胃是人体的后天之本，故倡导养生特别是食养至迟也须从青、中年开始，经过饮食调理以保养脾胃实为养生延年之大法。如味甘淡薄也足以滋养五脏，故劝人尽量少吃生冷、燥热、重滑、厚腻饮食，庶不致损伤脾胃。如能长期做到顾护中气（即脾胃生发的功能）而恰当地食养，则多可祛病长寿。",
			"节制饮食的要点关键在于“简、少、俭、谨、忌”五字。饮食品种宜恰当合理，进食量不宜过饱，每餐所进肉食不宜品类繁多，要十分注意良好的饮食习惯和讲究卫生，宜做到先饥而食，食不过饱，未饱先止；先渴而饮，饮不过多，并慎戒夜饮等。此外，过多偏食、杂食也不相宜。",
			"食疗在却病治疾方面有利于长期使用。此尤其对老年人，因多有五脏衰弱，气血耗损，加之脾胃运化功能减退，故先以饮食调治更易取得用药物所难获及的功效，盖此因大多数老年人患有程度不一的慢性病或身体虚弱，一则难坚持长期服药，二则有的不太习惯，三则易发生不良反应，故先食疗而后必要时用药多较妥当。",
			"食宜细嚼缓咽，忌虎咽狼吞；宜善选食和节制饮食，对腐败、腻油、荤腥、粘硬难消、香燥炙炒、浓醇厚味饮食更宜少进；淡食最宜人，以轻清甜淡食物为好；食宜暖，但暖亦不可太烫口，以热不灼唇，冷不冰齿为宜；坚硬或筋韧、半熟之肉品多难消化，食宜熟软，老人更直。",
			"祖国医学一直认为，脾胃是人体的后天之本，故倡导养生特别是食养至迟也须从青、中年开始，经过饮食调理以保养脾胃实为养生延年之大法。如味甘淡薄也足以滋养五脏，故劝人尽量少吃生冷、燥热、重滑、厚腻饮食，庶不致损伤脾胃。如能长期做到顾护中气（即脾胃生发的功能）而恰当地食养，则多可祛病长寿。",
			"节制饮食的要点关键在于“简、少、俭、谨、忌”五字。饮食品种宜恰当合理，进食量不宜过饱，每餐所进肉食不宜品类繁多，要十分注意良好的饮食习惯和讲究卫生，宜做到先饥而食，食不过饱，未饱先止；先渴而饮，饮不过多，并慎戒夜饮等。此外，过多偏食、杂食也不相宜。",
			"食疗在却病治疾方面有利于长期使用。此尤其对老年人，因多有五脏衰弱，气血耗损，加之脾胃运化功能减退，故先以饮食调治更易取得用药物所难获及的功效，盖此因大多数老年人患有程度不一的慢性病或身体虚弱，一则难坚持长期服药，二则有的不太习惯，三则易发生不良反应，故先食疗而后必要时用药多较妥当。",
			"食宜细嚼缓咽，忌虎咽狼吞；宜善选食和节制饮食，对腐败、腻油、荤腥、粘硬难消、香燥炙炒、浓醇厚味饮食更宜少进；淡食最宜人，以轻清甜淡食物为好；食宜暖，但暖亦不可太烫口，以热不灼唇，冷不冰齿为宜；坚硬或筋韧、半熟之肉品多难消化，食宜熟软，老人更直。" };

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
		// 布局
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
