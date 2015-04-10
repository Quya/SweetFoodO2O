package com.quya.service.seat;

import java.util.List;

import com.quya.model.Seat;
import com.quya.model.SeatLine;

public interface SeatService {

	List<SeatLine> getSeats(int hostId);

	void addSeat(Seat seat);

	void onSeat(int seatId);

	void offSeat(int seatId);

	void deleteSeat(int seatId);

	void updateSeat(Seat seat);


}
