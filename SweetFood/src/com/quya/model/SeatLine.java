package com.quya.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SeatLine implements Serializable {
	
	private List<Seat> seats=new ArrayList<Seat>();

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	
	
}
