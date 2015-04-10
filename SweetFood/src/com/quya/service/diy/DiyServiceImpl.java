package com.quya.service.diy;

import java.util.List;

import com.quya.dao.diy.DiyDao;
import com.quya.model.Diy;

public class DiyServiceImpl implements DiyService {
	private DiyDao diyDao;

	public DiyDao getDiyDao() {
		return diyDao;
	}

	public void setDiyDao(DiyDao diyDao) {
		this.diyDao = diyDao;
	}

	@Override
	public void addDiy(Diy diy) {
		// TODO Auto-generated method stub
		diyDao.addDiy(diy);
	}

	@Override
	public List<Diy> loadDiy(int hostId) {
		// TODO Auto-generated method stub
		return diyDao.toLoadDiy(hostId);
	}

	@Override
	public void deleteDiy(int diyId) {
		// TODO Auto-generated method stub
		diyDao.delete(diyId);
	}
}
