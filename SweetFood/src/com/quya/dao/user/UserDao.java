package com.quya.dao.user;

import com.quya.model.Shop;
import com.quya.model.User;

public interface UserDao {

	boolean isEmailRegisted(String email);

	void regist(User user);

	User findUserByEamil(String email);

	Shop getShopByUserId(int id);

}
