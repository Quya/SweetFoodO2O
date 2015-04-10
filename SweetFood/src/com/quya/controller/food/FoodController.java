package com.quya.controller.food;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quya.common.utils.json.ObjectJSONBean;
import com.quya.controller.BaseJsonController;
import com.quya.model.Food;
import com.quya.model.NewShop;
import com.quya.model.ObjectLine;
import com.quya.service.food.FoodService;

@Controller
@RequestMapping("foodController")
public class FoodController extends BaseJsonController {
	private FoodService foodService;

	public FoodService getFoodService() {
		return foodService;
	}

	public void setFoodService(FoodService foodService) {
		this.foodService = foodService;
	}

	@RequestMapping("/loadFood.do")
	public String loadFood(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attr) {
		JSONObject reqJson = getJSONParams(request);
		int hostId = Integer.valueOf(reqJson.get("hostid").toString());
		System.out.println(hostId);
		List<Food> foodList = foodService.viewFood(hostId);
		JSONArray ja = null;
		try {
			ja = ObjectJSONBean.instance().javaCollectionToJSONArray(foodList);
			System.out.println(ja.toString());
			this.sendMessageForJSONArray(response, "1", ja);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping("/addFood.do")
	public String addFood(MultipartFile photo, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("========================================");
		String realPath = request.getSession().getServletContext()
				.getRealPath("/sweetfood/common/image");
		// 上传文件的原名(即上传前的文件名字)
		String originalFilename = null;

		originalFilename = photo.getOriginalFilename();
		System.out.println("文件原名: " + originalFilename);
		System.out.println("文件名称: " + photo.getName());
		System.out.println("文件长度: " + photo.getSize());
		System.out.println("文件类型: " + photo.getContentType());
		System.out.println("========================================");
		Food food = new Food();
		try {
			if (photo.getSize() != 0) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				String newFileName = sdf.format(new Date())
						+ originalFilename.substring(originalFilename
								.lastIndexOf('.'));

				FileUtils.writeByteArrayToFile(new File(realPath, newFileName),
						photo.getBytes());

				food.setPhotoUrl("/SweetFood/sweetfood/common/image/"
						+ newFileName);

			}

			String foodName = new String(request.getParameter("foodname")
					.getBytes("iso-8859-1"), "utf-8");
			String description = new String(request.getParameter("description")
					.getBytes("iso-8859-1"), "utf-8");
			food.setFoodName(foodName);
			food.setDescription(description);
			food.setHostId(Integer.valueOf(request.getParameter("hostid")));
			food.setPrice(Integer.valueOf(request.getParameter("price")));
			food.setState(request.getParameter("state"));
			foodService.addFood(food);
			this.sendMessageForObject(response, "1", "添加成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping("/updateFood.do")
	public String updateFood(MultipartFile photo, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("========================================");
		String realPath = request.getSession().getServletContext()
				.getRealPath("/sweetfood/common/image");
		// 上传文件的原名(即上传前的文件名字)
		String originalFilename = null;
		originalFilename = photo.getOriginalFilename();
		System.out.println("文件原名: " + originalFilename);
		System.out.println("文件名称: " + photo.getName());
		System.out.println("文件长度: " + photo.getSize());
		System.out.println("文件类型: " + photo.getContentType());
		System.out.println("========================================");
		Food food = new Food();
		try {
			if (photo.getSize() != 0 && photo != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				String newFileName = sdf.format(new Date())
						+ originalFilename.substring(originalFilename
								.lastIndexOf('.'));

				FileUtils.writeByteArrayToFile(new File(realPath, newFileName),
						photo.getBytes());

				food.setPhotoUrl("/SweetFood/sweetfood/common/image/"
						+ newFileName);

			}

			String foodName = new String(request.getParameter("foodname")
					.getBytes("iso-8859-1"), "utf-8");
			String description = new String(request.getParameter("description")
					.getBytes("iso-8859-1"), "utf-8");
			food.setFoodName(foodName);
			food.setDescription(description);
			food.setId(Integer.valueOf(request.getParameter("foodid")));
			food.setHostId(Integer.valueOf(request.getParameter("hostid")));
			food.setPrice(Integer.valueOf(request.getParameter("price")));
			food.setState(request.getParameter("state"));
			foodService.updateFood(food);
			this.sendMessageForObject(response, "1", "修改成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping("/saleStop.do")
	public String saleStop(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attr) {
		JSONObject reqJson = getJSONParams(request);
		int foodid = Integer.valueOf(reqJson.get("foodid").toString());
		foodService.saleStop(foodid);
		this.sendMessageForObject(response, "1", "修改成功！");
		return null;

	}

	@RequestMapping("/saleStart.do")
	public String saleStart(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attr) {
		JSONObject reqJson = getJSONParams(request);
		int foodid = Integer.valueOf(reqJson.get("foodid").toString());
		foodService.saleStart(foodid);
		this.sendMessageForObject(response, "1", "修改成功！");
		return null;

	}

	@RequestMapping("/deleteFood.do")
	public String deleteFood(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attr) {
		JSONObject reqJson = getJSONParams(request);
		int foodid = Integer.valueOf(reqJson.get("foodid").toString());
		foodService.deleteFood(foodid);
		this.sendMessageForObject(response, "1", "成功删除！");
		return null;
	}

	@RequestMapping("/getShopFoods.do")
	public String getShopFoods(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attr) {
		JSONObject reqJson = getJSONParams(request);
		Integer businessmanId = Integer.valueOf(reqJson.get("businessmanId")
				.toString());
		NewShop shop = new NewShop();
		shop.setBusinessmanId(businessmanId);
		shop.setShopName(reqJson.get("shopName").toString());
		shop.setAddress(reqJson.get("address").toString());
		shop.setIndroduce(reqJson.get("indroduce").toString());
		shop.setShopPhone(reqJson.get("shopPhone").toString());
		shop.setShopPhoto(reqJson.get("shopPhoto").toString());

		try {
			HttpSession session = request.getSession();
			session.setAttribute("shop", shop);
			this.sendMessageForObject(response, "1", "跳转成功");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping("/loadShopInfo.do")
	public String loadShopInfo(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attr) {
		

		try {
			HttpSession session = request.getSession();
			NewShop shop = (NewShop)session.getAttribute("shop");
			this.sendMessageForObject(response, "1", shop);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	@RequestMapping("/loadShopFoods.do")
	public String loadShopFoods(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attr) {

		JSONArray ja = null;
		try {
			HttpSession session = request.getSession();
			NewShop shop = (NewShop) session.getAttribute("shop");
			List<ObjectLine> foodList = foodService.getShopFoods(shop
					.getBusinessmanId());
			ja = ObjectJSONBean.instance().javaCollectionToJSONArray(foodList);
			System.out.println(ja.toString());
			this.sendMessageForJSONArray(response, "1", ja);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping("/addFoodToShopCar.do")
	public String addFoodToShopCar(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attr) {
		JSONObject reqJson = getJSONParams(request);
		Food food = new Food();
		food.setFoodName(reqJson.get("name").toString());
		food.setId(Integer.valueOf(reqJson.get("id").toString()));
		food.setHostId(Integer.valueOf(reqJson.get("hostId").toString()));
		food.setPhotoUrl(reqJson.get("photo").toString());
		food.setPrice(Integer.valueOf(reqJson.get("price").toString()));
		try {
			HttpSession session = request.getSession();
			List<Food> foodList = (List<Food>) session.getAttribute("foodList");
			if (foodList == null) {
				foodList = new ArrayList<Food>();
				foodList.add(food);
			} else {
				foodList.add(food);
			}
			System.out.println("list:" + foodList.size());
			session.setAttribute("foodList", foodList);
			this.sendMessageForObject(response, "1", "添加成功");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping("/deleteFoodFromShopCar.do")
	public String deleteFoodFromShopCar(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attr) {
		JSONObject reqJson = getJSONParams(request);
		int foodId = Integer.valueOf(reqJson.get("foodId").toString());

		try {
			HttpSession session = request.getSession();
			List<Food> foodList = (List<Food>) session.getAttribute("foodList");
			Iterator<Food> it = foodList.iterator();
			System.out.println("list:" + foodList.size());
			while (it.hasNext()) {
				Food food = it.next();
				if (food.getId() == foodId) {
					it.remove();
					break;
				}
			}
			System.out.println("list:" + foodList.size());
			session.setAttribute("foodList", foodList);
			this.sendMessageForObject(response, "1", "刪除成功");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/loadShopCarFoods.do")
	public String loadShopCarFoods(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attr) {
		try {
			HttpSession session = request.getSession();
			List<Food> foodList = (List<Food>) session.getAttribute("foodList");

			if (foodList != null && foodList.size() != 0) {
				JSONArray ja = ObjectJSONBean.instance()
						.javaCollectionToJSONArray(foodList);
				System.out.println(foodList.get(0).toString());
				this.sendMessageForJSONArray(response, "1", ja);
			} else {
				this.sendMessageForObject(response, "0", "美食车没有任何美食");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
