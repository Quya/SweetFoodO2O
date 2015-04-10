package com.quya.service.user;

import com.quya.common.utils.Md5PasswordEncryption;
import com.quya.common.utils.exception.BusinessException;
import com.quya.dao.user.UserDao;
import com.quya.model.Shop;
import com.quya.model.User;

public class UserServiceImpl implements UserService{


	private UserDao userDao;
	private  Md5PasswordEncryption encryption=new Md5PasswordEncryption();
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void regist(User user) {
		// TODO Auto-generated method stub
		
		boolean isEmailRegisted=userDao.isEmailRegisted(user.getEmail());
		if(isEmailRegisted){
			throw new BusinessException("该邮箱已经被注册过，请使用新的邮箱",0);
		}else{
			String encryptionPassword=encryption.encrypt(user.getPassword());
			user.setPassword(encryptionPassword);
			userDao.regist(user);
		}
	}

	@Override
	public void login(User user) {
		// TODO Auto-generated method stub
		
		User loginUser=userDao.findUserByEamil(user.getEmail());
		if(loginUser!=null){
			String encryptionPassword=encryption.encrypt(user.getPassword());
			if(!encryptionPassword.equals(loginUser.getPassword())){
				throw new BusinessException("密码错误，请输入正确密码",0);
			}else{
				user.setCredits(loginUser.getCredits());
				user.setId(loginUser.getId());
				user.setName(loginUser.getName());
				user.setPhone(loginUser.getPhone());
				user.setPhotoUrl(loginUser.getPhotoUrl());
				user.setPower(loginUser.getPower());
				user.setSex(loginUser.getSex());
				if(user.getPower()==1){
					Shop shop=userDao.getShopByUserId(user.getId());
					user.setShop(shop);
				}
			}
					
		}else{
			throw new BusinessException("用户名不存在，请输入正确密码或注册新账号",1);
		}
	}

	
	



}
