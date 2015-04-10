package com.quya.service.seat;

import java.util.List;

import com.quya.dao.seat.SeatDao;
import com.quya.model.Seat;
import com.quya.model.SeatLine;

public class SeatServiceImpl implements SeatService {
	private SeatDao seatDao;

	public SeatDao getSeatDao() {
		return seatDao;
	}

	public void setSeatDao(SeatDao seatDao) {
		this.seatDao = seatDao;
	}

	@Override
	public List<SeatLine> getSeats(int hostId) {
		// TODO Auto-generated method stub
		return seatDao.findSeat(hostId);
	}

	@Override
	public void addSeat(Seat seat) {
		// TODO Auto-generated method stub
		seatDao.addSeat(seat);
	}

	@Override
	public void onSeat(int seatId) {
		// TODO Auto-generated method stub
		seatDao.toSetSeatBusy(seatId);
	}

	@Override
	public void offSeat(int seatId) {
		// TODO Auto-generated method stub
		seatDao.toSetSeatFree(seatId);
	}

	@Override
	public void deleteSeat(int seatId) {
		// TODO Auto-generated method stub
		seatDao.deleteSeat(seatId);
	}

	@Override
	public void updateSeat(Seat seat) {
		// TODO Auto-generated method stub
		seatDao.updateSeat(seat);
	}
	
}
