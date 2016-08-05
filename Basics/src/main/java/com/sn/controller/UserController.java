package com.sn.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sn.entity.User;
import com.sn.service.UserService;
import com.sn.util.PagedResult;

@Controller
@RequestMapping("/userController")
public class UserController {
	
	@Autowired
	private UserService userService;
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private User user;
	@RequestMapping("{id}/showUser")
	public String getUser(@PathVariable String id,HttpServletRequest request){
		user=userService.findById(id);
		request.setAttribute("user", user);
		return "showUser";
	}

	/**
	 * 显示首页
	 * @author linbingwen
	 * @since  2015年10月23日  
	 * @return
	 */
	@RequestMapping("/bootstrapTest1")  
	public String bootStrapTest1(){
		return "bootstrap/bootstrapTest1";
	}
	 /**
     * 分页查询用户信息
     * @author linbingwen
     * @since  2015年10月23日 
     * @param page
     * @return
     */
    @RequestMapping(value="/list.do", method= RequestMethod.POST)
    @ResponseBody
    public String list(Integer pageNumber,Integer pageSize ,String userName) {
		try {
			PagedResult<User> pageResult = userService.findByPage(null, pageNumber,pageSize);
			 return JSON.toJSONStringWithDateFormat(pageResult, "yyyy-MM-dd");
    	} catch (Exception e) {
    		 return JSON.toJSONString(e.getMessage());
		}
    }

}
