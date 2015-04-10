package com.quya.dao.seat;

import java.util.List;

import com.quya.model.Seat;
import com.quya.model.SeatLine;

public interface SeatDao {

	List<SeatLine> findSeat(int hostId);

	void addSeat(Seat seat);

	void toSetSeatBusy(int seatId);

	void toSetSeatFree(int seatId);

	void deleteSeat(int seatId);

	void updateSeat(Seat seat);

}
