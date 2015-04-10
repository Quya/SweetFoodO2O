package com.quya.service.food;

import java.util.List;

import com.quya.model.Food;
import com.quya.model.ObjectLine;

public interface FoodService {

	void addFood(Food food);

	List<Food> viewFood(int hostId);

	void updateFood(Food food);

	void saleStop(int foodid);

	void saleStart(int foodid);

	void deleteFood(int foodid);
	
	List<ObjectLine> getShopFoods(int businessmanId);
}
