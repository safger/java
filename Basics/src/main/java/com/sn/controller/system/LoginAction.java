package com.sn.controller.system;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sn.entity.User;
import com.sn.entity.UserRole;
import com.sn.service.MenuService;
import com.sn.service.UserRoleService;
import com.sn.service.UserService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("/system")
public class LoginAction {
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private MenuService menuService;
	private String error;
	private List<User> baseUser_list;
	
	
	@RequestMapping("login")
	public String show(){
		return "system/login";
	}
	@RequestMapping("oplogin")
	public String login(String username,String password){
		if(username!=null&&password!=null){
			User user=new User();
			user.setUsername(username);
			user.setUserpassword(password);
			user.setDeletemark(0);
			baseUser_list=userService.selectByColum(user);
			if(baseUser_list!=null&&baseUser_list.size()>0){
				String username1=baseUser_list.get(0).getUsername();
				String userid=baseUser_list.get(0).getFuid();
				String departmentid=baseUser_list.get(0).getDepartmentid();
				String departmentname=baseUser_list.get(0).getDepartmentname();
				String superAdmin=baseUser_list.get(0).getCode();
				request.getSession().setMaxInactiveInterval(3600*4);
				request.getSession().setAttribute("username",username1);
				request.getSession().setAttribute("userid",userid);
				request.getSession().setAttribute("departmentid",departmentid);
				request.getSession().setAttribute("departmentname",departmentname);
				request.getSession().setAttribute("superAdmin",superAdmin);
				String roleid="";
				UserRole userRole=new UserRole();
				userRole.setUserid(userid);
				List<UserRole> baseUserRole_list=userRoleService.selectByColum(userRole);
				if(baseUserRole_list!=null){
					for(int a=0;a<baseUserRole_list.size();a++){
						roleid+=baseUserRole_list.get(a).getRoleid()+",";
					}
				}
				roleid=roleid.length()>0?roleid.substring(0,roleid.length()-1):"";
				request.getSession().setAttribute("roleid",roleid);
				//更新最后登录时间
				baseUser_list.get(0).setPreviousvisit(baseUser_list.get(0).getLastvisit());
				baseUser_list.get(0).setLastvisit(new Date());
				userService.updateSelective(baseUser_list.get(0));
				request.getSession().setAttribute("previousvisit",baseUser_list.get(0).getPreviousvisit());
				//----------------
				 return "redirect:/system/index.html"; 
				
			}else{
				error="用户名或密码错误";
				return "system/login";
			}
		}else{
			error="请输入用户名或密码";
			return "system/login";
		}
	}
	
	@RequestMapping("logout")
	 public String logout(){
	    	request.getSession().invalidate();
	    	return "login";
	    }

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}



	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<User> getBaseUser_list() {
		return baseUser_list;
	}

	public void setBaseUser_list(List<User> baseUser_list) {
		this.baseUser_list = baseUser_list;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public UserRoleService getUserRoleService() {
		return userRoleService;
	}
	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}
	public MenuService getMenuService() {
		return menuService;
	}
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

 
	 
}
