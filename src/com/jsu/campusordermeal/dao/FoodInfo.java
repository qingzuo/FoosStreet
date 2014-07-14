package com.jsu.campusordermeal.dao;

import java.util.Map;

import android.R.bool;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class FoodInfo implements Parcelable {
	private int _id;
	private String name; // 菜名
	private String iconPath; // 图片
	private float price; // 价格
	private String characteristic;	//特色
	private String source; // 来源
	private String brief_introduction; // 简介
	private String detailed_introduction; // 详细介绍
	private float start;	//几星
	private int good_reputation;	//好评
	private int middle_reputation;	//中评
	private int bad_reputation;	//差评
	private String making;	//制作材料
	private int need_time;	//耗时
	private int order_time;	//订购次数
	private float special_offer;	//特价

	public static final Parcelable.Creator<FoodInfo> CREATOR = new Creator<FoodInfo>() {

		@Override
		public FoodInfo createFromParcel(Parcel source) {
			FoodInfo foodInfo = new FoodInfo();
			foodInfo._id = source.readInt();
			foodInfo.name = source.readString();
			foodInfo.iconPath = source.readString();
			foodInfo.price = source.readFloat();
			foodInfo.characteristic = source.readString();
			foodInfo.source = source.readString();
			foodInfo.brief_introduction = source.readString();
			foodInfo.detailed_introduction = source.readString();
			foodInfo.start = source.readFloat();
			foodInfo.good_reputation = source.readInt();
			foodInfo.middle_reputation = source.readInt();
			foodInfo.bad_reputation = source.readInt();
			foodInfo.making = source.readString();
			foodInfo.need_time = source.readInt();
			foodInfo.order_time = source.readInt();
			foodInfo.special_offer = source.readFloat();

			return foodInfo;
		}

		@Override
		public FoodInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new FoodInfo[size];
		}

	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(_id);
		dest.writeString(name);
		dest.writeString(iconPath);
		dest.writeFloat(price);
		dest.writeString(characteristic);
		dest.writeString(source);
		dest.writeString(brief_introduction);
		dest.writeString(detailed_introduction);
		dest.writeFloat(start);
		dest.writeInt(good_reputation);
		dest.writeInt(middle_reputation);
		dest.writeInt(bad_reputation);
		dest.writeString(making);
		dest.writeInt(need_time);
		dest.writeInt(order_time);
		dest.writeFloat(special_offer);
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}

	public String getMaking() {
		return making;
	}

	public void setMaking(String making) {
		this.making = making;
	}

	public int getNeed_time() {
		return need_time;
	}

	public void setNeed_time(int need_time) {
		this.need_time = need_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getBrief_introduction() {
		return brief_introduction;
	}

	public void setBrief_introduction(String brief_introduction) {
		this.brief_introduction = brief_introduction;
	}

	public String getDetailed_introduction() {
		return detailed_introduction;
	}

	public void setDetailed_introduction(String detailed_introduction) {
		this.detailed_introduction = detailed_introduction;
	}

	public float getStart() {
		return start;
	}

	public void setStart(float start) {
		this.start = start;
	}

	public int getGood_reputation() {
		return good_reputation;
	}

	public void setGood_reputation(int good_reputation) {
		this.good_reputation = good_reputation;
	}

	public int getMiddle_reputation() {
		return middle_reputation;
	}

	public void setMiddle_reputation(int middle_reputation) {
		this.middle_reputation = middle_reputation;
	}

	public int getBad_reputation() {
		return bad_reputation;
	}

	public void setBad_reputation(int bad_reputation) {
		this.bad_reputation = bad_reputation;
	}

	public int getOrder_time() {
		return order_time;
	}

	public void setOrder_time(int order_time) {
		this.order_time = order_time;
	}

	public float getSpecial_offer() {
		return special_offer;
	}

	public void setSpecial_offer(float special_offer) {
		this.special_offer = special_offer;
	}
	
}
