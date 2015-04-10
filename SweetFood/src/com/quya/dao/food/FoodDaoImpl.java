package com.quya.dao.food;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.quya.common.utils.ReturnFiveObjectsToOneRow;
import com.quya.dao.BaseDao;
import com.quya.model.Food;
import com.quya.model.ObjectLine;
import com.quya.model.Seat;
import com.quya.model.SeatLine;

public class FoodDaoImpl extends BaseDao implements FoodDao{

	@Override
	public void addFood(Food food) {
		// TODO Auto-generated method stub
		String foodSql="insert into tb_food (food_name,food_price,food_photo,food_description,food_host_id,food_state)values(?,?,?,?,?,?)";
		int line=getJdbcTemplate().update(foodSql,new Object[] {food.getFoodName(),food.getPrice(),food.getPhotoUrl(),food.getDescription(),food.getHostId(),food.getState()});
		System.out.println("影响行数"+line);
	}

	@Override
	public List<Food> findFood(int hostId) {
		// TODO Auto-generated method stub
		String foodSql="select * from tb_food where food_host_id=?";
		List<Food> foodList=getJdbcTemplate().query(foodSql, new Object[] {hostId }, new RowMapper() {
            public Object mapRow(ResultSet rs, int pRowNum) throws SQLException {
            	Food food=new Food();
            	food.setDescription(rs.getString("food_description"));
            	food.setFoodName(rs.getString("food_name"));
            	food.setHostId(rs.getInt("food_host_id"));
            	food.setId(rs.getInt("food_id"));
            	food.setPhotoUrl(rs.getString("food_photo"));
            	food.setPrice(rs.getInt("food_price"));
            	food.setState(rs.getString("food_state"));
            	return food;
            }
        });
		return foodList;
	}

	@Override
	public void updateFood(Food food) {
		// TODO Auto-generated method stub
		if(food.getPhotoUrl()!=null){
			String infoSql="update tb_food set food_name=?,food_price=?,food_photo=?,food_description=?,food_state=? where food_id=?";
			getJdbcTemplate().update(infoSql,new Object[] {food.getFoodName(),food.getPrice(),food.getPhotoUrl(),food.getDescription(),food.getState(),food.getId()});
		}else{
			String infoSql="update tb_food set food_name=?,food_price=?,food_description=?,food_state=? where food_id=?";
			getJdbcTemplate().update(infoSql,new Object[] {food.getFoodName(),food.getPrice(),food.getDescription(),food.getState(),food.getId()});
		
		}
	}

	@Override
	public void toSaleStop(int foodid) {
		// TODO Auto-generated method stub
		System.out.println(foodid);
		String infoSql="update tb_food set food_state='off' where food_id=?";
		getJdbcTemplate().update(infoSql,new Object[] {foodid});
	}

	@Override
	public void toSaleStart(int foodid) {
		// TODO Auto-generated method stub
		System.out.println(foodid);
		String infoSql="update tb_food set food_state='on' where food_id=?";
		getJdbcTemplate().update(infoSql,new Object[] {foodid});
	}

	@Override
	public void deleteFood(int foodid) {
		// TODO Auto-generated method stub
		System.out.println(foodid);
		String infoSql="delete from tb_food where food_id=?";
		getJdbcTemplate().update(infoSql,new Object[] {foodid});
	}

	@Override
	public List<ObjectLine> findFoodsByUserId(int businessmanId) {
		// TODO Auto-generated method stub
		String foodSql="select * from tb_food where food_host_id=?";
		List<Food>foodlist=getJdbcTemplate().query(foodSql, new Object[] {businessmanId }, new RowMapper() {
            public Object mapRow(ResultSet rs, int pRowNum) throws SQLException {
            	Food food=new Food();
            	food.setDescription(rs.getString("food_description"));
            	food.setFoodName(rs.getString("food_name"));
            	food.setHostId(rs.getInt("food_host_id"));
            	food.setId(rs.getInt("food_id"));
            	food.setLevel(rs.getInt("food_level"));
            	food.setPhotoUrl(rs.getString("food_photo"));
            	food.setPrice(rs.getInt("food_price"));
            	food.setSaleVolume(rs.getInt("food_sales_volume"));
            	food.setState(rs.getString("food_state"));
            	return food;
            }
        });
		return ReturnFiveObjectsToOneRow.findObjects(foodlist);
	}

}
