package com.quya.service.diy;

import java.util.List;

import com.quya.model.Diy;

public interface DiyService {

	void addDiy(Diy diy);

	List<Diy> loadDiy(int hostId);

	void deleteDiy(int diyId);

}
