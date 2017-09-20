package com.spring.dynamicdatasource.test;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.spring.datasourceaop.DataSourceManage;

public interface UserMapper {
	
	@DataSourceManage(name="dataSourceOne")
	List<User> findAllUser();
	
	List<User> selectMyPage(RowBounds rowBounds);
	
	int insert(User user);
}
