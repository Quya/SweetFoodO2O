package com.quya.model;

import java.io.Serializable;

public class Shop implements Serializable{
	private int shopId;
	private int businessmanId;
	private String indroduce;
	private Address address;

	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public int getBusinessmanId() {
		return businessmanId;
	}
	public void setBusinessmanId(int businessmanId) {
		this.businessmanId = businessmanId;
	}
	public String getIndroduce() {
		return indroduce;
	}
	public void setIndroduce(String indroduce) {
		this.indroduce = indroduce;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Shop [shopId=" + shopId + ", businessmanId=" + businessmanId
				+ ", indroduce=" + indroduce + ", address=" + address + "]";
	}


}
