package com.quya.service.customer;

import java.util.List;

import com.quya.model.Address;
import com.quya.model.ObjectLine;

public interface CustomerService {

	List<ObjectLine> getShopsByAddress(Address address);

	

}
