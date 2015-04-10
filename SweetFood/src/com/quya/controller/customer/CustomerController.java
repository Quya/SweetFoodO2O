package com.quya.controller.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quya.common.utils.json.ObjectJSONBean;
import com.quya.controller.BaseJsonController;
import com.quya.model.Address;
import com.quya.model.ObjectLine;
import com.quya.service.customer.CustomerService;

@Controller
@RequestMapping("customerController")
public class CustomerController  extends BaseJsonController{
	private CustomerService customerService;

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	@RequestMapping("/searchShops.do")
	public String searchShops(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr){

         try {
        	 JSONObject reqJson = getJSONParams(request);  		
        	 String dist=reqJson.get("dist").toString();
			 String prov=reqJson.get("prov").toString();
	         String city=reqJson.get("city").toString();
	         Address address= new Address();
	         address.setCity(city);
	         address.setDist(dist);
	         address.setProvince(prov);
	         List<ObjectLine> seatList=customerService.getShopsByAddress(address);
	     	JSONArray ja = null;
	     	ja = ObjectJSONBean.instance().javaCollectionToJSONArray(seatList);
			System.out.println(ja.toString());
			this.sendMessageForJSONArray(response,"1", ja);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
       
		return null;
	}
	
	
}
