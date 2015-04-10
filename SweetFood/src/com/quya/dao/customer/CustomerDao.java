package com.quya.dao.customer;

import java.util.List;

import com.quya.model.Address;
import com.quya.model.ObjectLine;

public interface CustomerDao {

	List<ObjectLine> findShopsByAddress(Address address);



}
