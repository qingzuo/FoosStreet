package com.jsu.campusordermeal.dao;

import android.graphics.Bitmap;

public class UserInfo {
	private int id;
	private String email;
	private String phone;
	private String password;
	private String name;
	private String head;

	public UserInfo() {
	}

	public UserInfo(int id, String email, String phone, String password,
			String name, String head) {
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.name = name;
		this.head = head;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}


}
