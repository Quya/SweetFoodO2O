package com.quya.service.customer;

import java.util.List;

import com.quya.dao.customer.CustomerDao;
import com.quya.model.Address;
import com.quya.model.ObjectLine;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDao customerDao;

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public List<ObjectLine> getShopsByAddress(Address address) {
		// TODO Auto-generated method stub
		return customerDao.findShopsByAddress(address);
	}


}
