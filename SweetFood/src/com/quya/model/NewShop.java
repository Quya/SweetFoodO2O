package com.quya.model;

import java.io.Serializable;

public class NewShop implements Serializable{
	private int businessmanId;
	private String shopName;
	private String shopPhoto;
	private String shopPhone;
	private String address;
	private String indroduce;
	public int getBusinessmanId() {
		return businessmanId;
	}
	public void setBusinessmanId(int businessmanId) {
		this.businessmanId = businessmanId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopPhoto() {
		return shopPhoto;
	}
	public void setShopPhoto(String shopPhoto) {
		this.shopPhoto = shopPhoto;
	}
	public String getShopPhone() {
		return shopPhone;
	}
	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getIndroduce() {
		return indroduce;
	}
	public void setIndroduce(String indroduce) {
		this.indroduce = indroduce;
	}
	@Override
	public String toString() {
		return "NewShop [businessmanId=" + businessmanId + ", shopName="
				+ shopName + ", shopPhoto=" + shopPhoto + ", shopPhone="
				+ shopPhone + ", address=" + address + ", indroduce="
				+ indroduce + "]";
	}
	
	
}
