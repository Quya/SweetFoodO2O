package com.quya.controller.seat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quya.common.utils.json.ObjectJSONBean;
import com.quya.controller.BaseJsonController;
import com.quya.model.Seat;
import com.quya.model.SeatLine;
import com.quya.service.seat.SeatService;

@Controller
@RequestMapping("seatController")
public class SeatController extends BaseJsonController {
	private SeatService seatService;

	public SeatService getSeatService() {
		return seatService;
	}

	public void setSeatService(SeatService seatService) {
		this.seatService = seatService;
	}
	@RequestMapping("/loadSeat.do")
	public String loadSeat(HttpServletRequest request, HttpServletResponse response ,RedirectAttributes attr){
		JSONObject reqJson = getJSONParams(request);
		int hostId=Integer.valueOf(reqJson.get("hostid").toString());
		List<SeatLine> seatList=seatService.getSeats(hostId);
		JSONArray ja = null;
		 try {
			ja = ObjectJSONBean.instance().javaCollectionToJSONArray(seatList);
			System.out.println(ja.toString());
			   this.sendMessageForJSONArray(response,"1", ja);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/addSeat.do")
	public String addSeat(HttpServletRequest request, HttpServletResponse response ,RedirectAttributes attr){
		JSONObject reqJson = getJSONParams(request);
	
		Seat seat=new Seat();
		seat.setHostId(Integer.valueOf(reqJson.get("hostid").toString()));
		seat.setSeatName(reqJson.get("seatname").toString());
		seat.setSeatNum(Integer.valueOf(reqJson.get("seatnum").toString()));
		seat.setState(reqJson.get("state").toString());
		 try {
			 seatService.addSeat(seat);
			 sendMessageForObject(response, "1", "添加成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/updateSeat.do")
	public String updateSeat(HttpServletRequest request, HttpServletResponse response ,RedirectAttributes attr){
		JSONObject reqJson = getJSONParams(request);
	
		Seat seat=new Seat();
		seat.setSeatId(Integer.valueOf(reqJson.get("seatid").toString()));
		seat.setSeatName(reqJson.get("seatname").toString());
		seat.setSeatNum(Integer.valueOf(reqJson.get("seatnum").toString()));
		seat.setState(reqJson.get("state").toString());
		 try {
			 seatService.updateSeat(seat);
			 sendMessageForObject(response, "1", "修改成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/onSeat.do")
	public String onSeat(HttpServletRequest request, HttpServletResponse response ,RedirectAttributes attr){
		JSONObject reqJson = getJSONParams(request);
	
		int seatId=Integer.valueOf(reqJson.get("seatid").toString());
		 try {
			 seatService.onSeat(seatId);
			 sendMessageForObject(response, "1", "就座成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/offSeat.do")
	public String offSeat(HttpServletRequest request, HttpServletResponse response ,RedirectAttributes attr){
		JSONObject reqJson = getJSONParams(request);
	
		int seatId=Integer.valueOf(reqJson.get("seatid").toString());
		 try {
			 seatService.offSeat(seatId);
			 sendMessageForObject(response, "1", "让座成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/deleteSeat.do")
	public String deleteSeat(HttpServletRequest request, HttpServletResponse response ,RedirectAttributes attr){
		JSONObject reqJson = getJSONParams(request);
	
		int seatId=Integer.valueOf(reqJson.get("seatid").toString());
		 try {
			 seatService.deleteSeat(seatId);
			 sendMessageForObject(response, "1", "删除成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
