package com.quya.service.food;

import java.util.List;

import com.quya.dao.food.FoodDao;
import com.quya.model.Food;
import com.quya.model.ObjectLine;

public class FoodServiceImpl implements FoodService{
	private FoodDao foodDao;

	public FoodDao getFoodDao() {
		return foodDao;
	}

	public void setFoodDao(FoodDao foodDao) {
		this.foodDao = foodDao;
	}

	@Override
	public void addFood(Food food) {
		// TODO Auto-generated method stub
		foodDao.addFood(food);
	}

	@Override
	public List<Food> viewFood(int hostId) {
		// TODO Auto-generated method stub
		
		return foodDao.findFood(hostId);
	}

	@Override
	public void updateFood(Food food) {
		// TODO Auto-generated method stub
		foodDao.updateFood(food);
	}

	@Override
	public void saleStop(int foodid) {
		// TODO Auto-generated method stub
		foodDao.toSaleStop(foodid);
	}

	@Override
	public void saleStart(int foodid) {
		// TODO Auto-generated method stub
		foodDao.toSaleStart(foodid);
	}

	@Override
	public void deleteFood(int foodid) {
		// TODO Auto-generated method stub
		foodDao.deleteFood(foodid);
	}
	@Override
	public List<ObjectLine> getShopFoods(int businessmanId) {
		// TODO Auto-generated method stub
		return foodDao.findFoodsByUserId(businessmanId);
	}
}
