package com.quya.model;

import java.io.Serializable;

public class Address implements Serializable {
	private int addressId;
	private String province;
	private String city;
	private String dist;
	private String street;

	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", province=" + province
				+ ", city=" + city + ", dist=" + dist + ", street=" + street
				+ "]";
	}
	
	
}
