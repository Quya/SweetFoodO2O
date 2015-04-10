package com.quya.model;

import java.io.Serializable;

public class Seat implements Serializable {
	private int seatId;
	private String seatName;
	private int hostId;
	private int seatNum;
	private String state;
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public String getSeatName() {
		return seatName;
	}
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	public int getHostId() {
		return hostId;
	}
	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Seat [seatId=" + seatId + ", seatName=" + seatName
				+ ", hostId=" + hostId 
				+ ", seatNum=" + seatNum + ", state=" + state + "]";
	}

}
