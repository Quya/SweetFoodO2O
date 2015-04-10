package com.quya.dao.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.quya.common.utils.ReturnFiveObjectsToOneRow;
import com.quya.dao.BaseDao;
import com.quya.model.Address;
import com.quya.model.Food;
import com.quya.model.NewShop;
import com.quya.model.ObjectLine;

public class CustomerDaoImpl extends BaseDao implements CustomerDao {

	@Override
	public List<ObjectLine> findShopsByAddress(Address address) {
		// TODO Auto-generated method stub
		List<NewShop> shoplist=null;
		String sql;
		if(!address.getProvince().isEmpty()&&!address.getCity().isEmpty()&&!address.getDist().isEmpty()){
			sql="select user_id,user_name,user_photo,user_phone ,prov,city,dist,street ,indroduce from tb_user,tb_shop,tb_address where user_id in(select businessman_id from tb_shop where address in (select id from tb_address where (prov=? and city=? and dist=?))) and user_id= businessman_id and address=tb_address.id";
			shoplist=getJdbcTemplate().query(sql, new Object[] {address.getProvince(),address.getCity(),address.getDist() }, new RowMapper() {
	            public Object mapRow(ResultSet rs, int pRowNum) throws SQLException {
	            	NewShop shop=new NewShop();
	            	
	            	shop.setAddress(rs.getString("prov")+rs.getString("city")+rs.getString("dist")+rs.getString("street"));
	            	shop.setBusinessmanId(rs.getInt("user_id"));
	            	shop.setShopName(rs.getString("user_name"));
	            	shop.setShopPhone(rs.getString("user_phone"));
	            	shop.setShopPhoto(rs.getString("user_photo"));
	             	shop.setIndroduce(rs.getString("indroduce"));
	            	return shop;
	            }
	        });
		}else if(!address.getProvince().isEmpty()&&!address.getCity().isEmpty()){
			 sql="select user_id,user_name,user_photo,user_phone ,prov,city,dist,street,indroduce from tb_user,tb_shop,tb_address where user_id in(select businessman_id from tb_shop where address in (select id from tb_address where (prov=? and city=? ))) and user_id= businessman_id and address=tb_address.id";
			shoplist=getJdbcTemplate().query(sql, new Object[] {address.getProvince(),address.getCity() }, new RowMapper() {
	            public Object mapRow(ResultSet rs, int pRowNum) throws SQLException {
	            	NewShop shop=new NewShop();
	            	
	            	shop.setAddress(rs.getString("prov")+rs.getString("city")+rs.getString("dist")+rs.getString("street"));
	            	shop.setBusinessmanId(rs.getInt("user_id"));
	            	shop.setShopName(rs.getString("user_name"));
	            	shop.setShopPhone(rs.getString("user_phone"));
	            	shop.setShopPhoto(rs.getString("user_photo"));
	            	shop.setIndroduce(rs.getString("indroduce"));
	            	return shop;
	            }
	        });
		}
		
	System.out.println(address.toString()+shoplist.size());
		return ReturnFiveObjectsToOneRow.findObjects(shoplist);
	}

	
}
