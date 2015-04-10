package com.quya.controller.diy;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.quya.model.Diy;
import com.quya.service.diy.DiyService;

@Controller
@RequestMapping("diyController")
public class DiyController extends BaseJsonController {
	private DiyService diyService;

	public DiyService getDiyService() {
		return diyService;
	}

	public void setDiyService(DiyService diyService) {
		this.diyService = diyService;
	}
	@RequestMapping("/addDiy.do")
	public String addDiy(HttpServletRequest request, HttpServletResponse response ,RedirectAttributes attr){
		JSONObject reqJson = getJSONParams(request);
		try {
		Diy diy =new Diy();
		diy.setBusinessmanId(Integer.valueOf(reqJson.get("userid").toString()));
		diy.setDiyContent(reqJson.get("diycontent").toString());
		diy.setTopicName(reqJson.get("topicname").toString());
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		diy.setPushDate(Timestamp.valueOf(myFmt.format(new Timestamp(System.currentTimeMillis()))));
		System.out.println(diy.toString());
		diyService.addDiy(diy);
		sendMessageForObject(response, "1", "添加成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/loadDiy.do")
	public String loadDiy(HttpServletRequest request, HttpServletResponse response ,RedirectAttributes attr){
		JSONObject reqJson = getJSONParams(request);
		int hostId=Integer.valueOf(reqJson.get("hostid").toString());
		 try {
			 List<Diy>diyList=diyService.loadDiy(hostId);
			 JSONArray ja = ObjectJSONBean.instance().javaCollectionToJSONArray(diyList);
			 this.sendMessageForJSONArray(response,"1", ja);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/deleteDiy.do")
	public String deleteDiy(HttpServletRequest request, HttpServletResponse response ,RedirectAttributes attr){
		JSONObject reqJson = getJSONParams(request);
		int diyId=Integer.valueOf(reqJson.get("diyId").toString());
		 try {
			diyService.deleteDiy(diyId);
			
			sendMessageForObject(response, "1", "删除成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
