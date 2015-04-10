package com.quya.dao.food;

import java.util.List;

import com.quya.model.Food;
import com.quya.model.ObjectLine;
import com.quya.model.SeatLine;

public interface FoodDao {

	void addFood(Food food);

	List<Food> findFood(int hostId);

	void updateFood(Food food);

	void toSaleStop(int foodid);

	void toSaleStart(int foodid);

	void deleteFood(int foodid);
	
	List<ObjectLine> findFoodsByUserId(int businessmanId);
}
