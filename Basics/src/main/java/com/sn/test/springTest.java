package com.sn.test;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.sn.controller.system.CompetenceManager;
import com.sn.entity.User;
import com.sn.service.MenuService;
import com.sn.service.OperationLogService;
import com.sn.service.RoleService;
import com.sn.service.UserService;
import com.sn.util.PagedResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml","classpath:spring-mybatis.xml"})
public class springTest {

	@Autowired
    private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private OperationLogService operationLogService; 
    public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Test
    public void test(){
		User user1=new User();
		user1.setFuid("0656b2f87d8c4cf980a1cc19374084ae");
		PagedResult<User> user=userService.findByPage(user1, 1, 10);
    	System.out.println(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd"));
    }
	@Test
    public void test1() throws ServletException{
		roleService.selectByColum(null, "SYSTEMID");
		//menuService.findById("0656b2f87d8c4cf980a1cc19374084ae");
    }
	
}