package com.quya.dao.diy;

import java.util.List;

import com.quya.model.Diy;

public interface DiyDao {

	void addDiy(Diy diy);

	List<Diy> toLoadDiy(int hostId);

	void delete(int diyId);

}
