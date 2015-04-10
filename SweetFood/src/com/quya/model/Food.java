package com.quya.model;

import java.io.Serializable;

public class Food implements Serializable {
	private int id;
	private String foodName;//美食名
	private String photoUrl;//美食图
	private int price;//美食价钱
	private int saleVolume;//美食销量
	private int level;//美食评价等级
	private int commentId;//美食评论Id
	private int hostId;//美食拥有者
	private String description;//美食描述
	private String state;//美食状态
	private int count;//美食数量
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSaleVolume() {
		return saleVolume;
	}
	public void setSaleVolume(int saleVolume) {
		this.saleVolume = saleVolume;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getHostId() {
		return hostId;
	}
	public void setHostId(int hostId) {
		this.hostId = hostId;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Food [id=" + id + ", foodName=" + foodName + ", photoUrl="
				+ photoUrl + ", price=" + price + ", saleVolume=" + saleVolume
				+ ", level=" + level + ", commentId=" + commentId + ", hostId="
				+ hostId + ", description=" + description + ", state=" + state
				+ ", count=" + count + "]";
	}

	
	
}
