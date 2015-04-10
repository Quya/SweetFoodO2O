package com.quya.dao.seat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.quya.dao.BaseDao;
import com.quya.model.Seat;
import com.quya.model.SeatLine;

public class SeatDaoImpl extends BaseDao implements SeatDao {
	@Override
	public List<SeatLine> findSeat(int hostId){
		List<Seat>seatList=toGetSeats(hostId);
		List<SeatLine>lineList=new ArrayList<>();
		int seatsNum=seatList.size();
		for(int i=0,p=0;i<=seatsNum/5;i++){
			SeatLine seatLine=new SeatLine();
			for(int j=1;p<seatsNum;j++,p++){
				if(j%6!=0){
					seatLine.getSeats().add(seatList.get(p));
				}else{
					break;
				}
			}
			lineList.add(seatLine);
		}
		return lineList;		
	}
	private List<Seat> toGetSeats(int hostId){
		String querySql="select * from tb_seat where businessman_id=?";
		List<Seat> seatList=getJdbcTemplate().query(querySql, new Object[] {hostId }, new RowMapper() {
            public Object mapRow(ResultSet rs, int pRowNum) throws SQLException {
            	Seat seat =new Seat();
               	seat.setHostId(rs.getInt("businessman_id"));
            	seat.setSeatName(rs.getString("seat_name"));
            	seat.setSeatId(rs.getInt("seat_id"));
            	seat.setSeatNum(rs.getInt("seat_num"));
            	seat.setState(rs.getString("seat_state"));
            	return seat;
            }
        });
		return seatList;
		
	}
	@Override
	public void addSeat(Seat seat) {
		// TODO Auto-generated method stub
		String addSql="insert into tb_seat (seat_name,seat_state,seat_num,businessman_id)values(?,?,?,?)";
		int line=getJdbcTemplate().update(addSql,new Object[] {seat.getSeatName(),seat.getState(),seat.getSeatNum(),seat.getHostId()});
		System.out.println("line="+line);
	}
	@Override
	public void toSetSeatBusy(int seatId) {
		// TODO Auto-generated method stub
		String onSeatSql="update tb_seat set seat_state=? where seat_id=?";
		getJdbcTemplate().update(onSeatSql,new Object[] {"busy",seatId});
	
	}
	@Override
	public void toSetSeatFree(int seatId) {
		// TODO Auto-generated method stub
		String onSeatSql="update tb_seat set seat_state=?where seat_id=?";
		getJdbcTemplate().update(onSeatSql,new Object[] {"free",seatId});
	}
	@Override
	public void deleteSeat(int seatId) {
		// TODO Auto-generated method stub
		String deleteSql="delete from tb_seat where seat_id=?";
		getJdbcTemplate().update(deleteSql,new Object[] {seatId});
	}
	@Override
	public void updateSeat(Seat seat) {
		// TODO Auto-generated method stub
		String updateSql="update tb_seat set seat_name=?,seat_state=?,seat_num=? where seat_id=?";
		int line=getJdbcTemplate().update(updateSql,new Object[] {seat.getSeatName(),seat.getState(),seat.getSeatNum(),seat.getSeatId()});
		System.out.println("update="+line);
	}
}
