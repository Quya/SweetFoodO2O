package com.quya.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;

import com.quya.common.utils.json.ObjectJSONBean;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newFileName=sdf.format(new Date());
        System.out.println(newFileName);
//		List<Seat>list=new ArrayList<>();
//		for(int i=0;i<39;i++){
//			Seat seat=new Seat();
//			seat.setSeatId(i);
//			list.add(seat);
//		}
//		List<SeatLine>lineList=new ArrayList<>();
//		for(int i=0,p=0;i<=list.size()/5;i++){
//			SeatLine seatLine=new SeatLine();
//			for(int j=1;p<list.size();j++,p++){
//				System.out.println(j);
//				System.out.println(p);
//				if(j%6!=0){
//					seatLine.getSeats().add(list.get(p));
//				}else{
//					
//					break;
//				}
//			}
//			lineList.add(seatLine);
//		}
//		JSONArray ja = null;
//		 try {
//			ja = ObjectJSONBean.instance().javaCollectionToJSONArray(lineList);
//			System.out.println(ja.toString());
//			 
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
