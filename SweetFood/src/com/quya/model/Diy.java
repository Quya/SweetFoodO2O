package com.quya.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Diy implements Serializable {
	private int diyId;
	private int businessmanId;
	private String topicName;
	private String diyContent;
	private int readTimes;
	private Timestamp pushDate;
	public int getDiyId() {
		return diyId;
	}
	public void setDiyId(int diyId) {
		this.diyId = diyId;
	}
	public int getBusinessmanId() {
		return businessmanId;
	}
	public void setBusinessmanId(int businessmanId) {
		this.businessmanId = businessmanId;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getDiyContent() {
		return diyContent;
	}
	public void setDiyContent(String diyContent) {
		this.diyContent = diyContent;
	}
	public int getReadTimes() {
		return readTimes;
	}
	public void setReadTimes(int readTimes) {
		this.readTimes = readTimes;
	}

	public Timestamp getPushDate() {
		return pushDate;
	}
	public void setPushDate(Timestamp pushDate) {
		this.pushDate = pushDate;
	}
	@Override
	public String toString() {
		return "Diy [diyId=" + diyId + ", businessmanId=" + businessmanId
				+ ", topicName=" + topicName + ", diyContent=" + diyContent
				+ ", readTimes=" + readTimes + ", pushDate=" + pushDate + "]";
	}

}
