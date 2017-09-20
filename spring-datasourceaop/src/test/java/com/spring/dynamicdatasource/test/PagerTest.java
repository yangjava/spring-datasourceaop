package com.spring.dynamicdatasource.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class PagerTest {
	
	
    @Autowired
	UserMapper userMapper;


	@Test
	public void testSelectByUserNameAndPwd() throws Throwable {
		List<User> findAllUser = userMapper.findAllUser();
		System.out.println(findAllUser.size());
	}
}
