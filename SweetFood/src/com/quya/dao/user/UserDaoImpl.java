package com.quya.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.quya.dao.BaseDao;
import com.quya.model.Address;
import com.quya.model.Shop;
import com.quya.model.User;

public class UserDaoImpl extends BaseDao implements UserDao{

	@Override
	public boolean isEmailRegisted(String email) {
		 List<User> list = getUserByEmail(email);
	        if (list.isEmpty()) {
	            return false;
	        }
		return true;
	}

	private List<User> getUserByEmail(String email) {
		String sql = "select * from tb_user where user_email = ?";
	        List<User> list = getJdbcTemplate().query(sql, new Object[] {email }, new RowMapper() {
	            public Object mapRow(ResultSet rs, int pRowNum) throws SQLException {
	            	User user=new User();
	            	user.setId(rs.getInt("user_id"));
	            	user.setEmail(rs.getString("user_email"));
	            	user.setCredits(rs.getInt("user_credits"));
	            	user.setPhone(rs.getString("user_phone"));
	            
	            	user.setName(rs.getString("user_name"));
	            	user.setPower(rs.getInt("user_power"));
	            	user.setSex(rs.getString("user_sex"));
	            	user.setPassword(rs.getString("user_password"));
	            	user.setPhotoUrl(rs.getString("user_photo"));
	            	                return user;
	            }
	        });
		return list;
	}

	@Override
	public void regist(User user) {
		// TODO Auto-generated method stub
		String sql ="insert into tb_user (user_name,user_password,user_email,user_credits,user_sex,user_phone,user_power,user_photo)values(?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(
				sql,
				new Object[] { user.getName(),user.getPassword(),user.getEmail(),user.getCredits(),user.getSex(),user.getPhone(),user.getPower(),user.getPhotoUrl()});
		List<User> list = getUserByEmail(user.getEmail());
		user.setId(list.get(0).getId());		
	}

	@Override
	public User findUserByEamil(String email) {
		// TODO Auto-generated method stub
		List<User>list=getUserByEmail(email);
		if (!list.isEmpty()) {
	        return list.get(0);
        }
	return null;
	}

	@Override
	public Shop getShopByUserId(int id) {
		// TODO Auto-generated method stub
		String shopSql="select * from tb_shop where businessman_id=?";
		List<Shop> shoplist=getJdbcTemplate().query(shopSql, new Object[] {id }, new RowMapper() {
            public Object mapRow(ResultSet rs, int pRowNum) throws SQLException {
            	Shop shop=new Shop();
            	shop.setAddress(new Address());
            	shop.getAddress().setAddressId(rs.getInt("address"));
            	shop.setIndroduce(rs.getString("indroduce"));
            	shop.setShopId(rs.getInt("id"));
            	shop.setBusinessmanId(rs.getInt("businessman_id"));
            	                return shop;
            }
        });
		Shop shop=shoplist.get(0);
		String addressSql="select * from tb_address where id=?";
		List<Address>address=getJdbcTemplate().query(addressSql, new Object[] {shop.getAddress().getAddressId() }, new RowMapper() {
            public Object mapRow(ResultSet rs, int pRowNum) throws SQLException {
            	Address address=new Address();
            	address.setAddressId(rs.getInt("id"));
            	address.setCity(rs.getString("city"));
            	address.setDist(rs.getString("dist"));
            	address.setProvince(rs.getString("prov"));                
            	address.setStreet(rs.getString("street"));
            	return address;
      
            }
        });
		shop.setAddress(address.get(0));
		
		return shop;
	}
}
