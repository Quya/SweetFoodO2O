package com.quya.dao.diy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.quya.dao.BaseDao;
import com.quya.model.Diy;
import com.quya.model.Food;

public class DiyDaoImpl extends BaseDao implements DiyDao {

	@Override
	public void addDiy(Diy diy) {
		// TODO Auto-generated method stub
		String addSql="insert into tb_push (businessman_id,push_topic,push_content,push_time)values(?,?,?,?)";
		getJdbcTemplate().update(addSql,new Object[] {diy.getBusinessmanId(),diy.getTopicName(),diy.getDiyContent(),diy.getPushDate()});
	}

	@Override
	public List<Diy> toLoadDiy(int hostId) {
		// TODO Auto-generated method stub
		String diySql="select * from tb_push where businessman_id=?";
		List<Diy> diyList=getJdbcTemplate().query(diySql, new Object[] {hostId }, new RowMapper() {
            public Object mapRow(ResultSet rs, int pRowNum) throws SQLException {
            	Diy diy=new Diy();
            	diy.setBusinessmanId(rs.getInt("businessman_id"));
            	diy.setDiyContent(rs.getString("push_content"));
            	diy.setDiyId(rs.getInt("push_id"));
            	diy.setPushDate(rs.getTimestamp("push_time"));
            	diy.setReadTimes(rs.getInt("push_readtime"));
            	diy.setTopicName(rs.getString("push_topic"));
            	return diy;
            }
        });
		return diyList;
	}

	@Override
	public void delete(int diyId) {
		// TODO Auto-generated method stub
		String deleteSql="delete from tb_push where push_id=?";
		getJdbcTemplate().update(deleteSql,new Object[] {diyId});
	
	}

}
