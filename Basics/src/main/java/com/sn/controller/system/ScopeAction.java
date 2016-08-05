package com.sn.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.sn.common.UUIDCreater;
import com.sn.entity.Menu;
import com.sn.entity.Role;
import com.sn.entity.Scope;
import com.sn.service.MenuService;
import com.sn.service.OperationLogService;
import com.sn.service.RoleService;
import com.sn.service.ScopeService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("system/scope/")

public class ScopeAction {
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private  HttpServletResponse response;
	@Autowired
	private OperationLogService operationLogService;
	@Autowired
	private ScopeService scopeService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("show")
	public String show(){
		return "system/scope";
	}
	@RequestMapping("showScope")
	public String showScope(String OrganizeId,Map<String,Object>model){
		model.put("OrganizeId", OrganizeId);
		return "system/scope_menu";
	}
	@RequestMapping("showScopeMenu")
	@ResponseBody
	public String showScopeMenu(String OrganizeId) throws IOException{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		Role role=new Role();
		role.setDeletemark(0);
		role.setOrganizeid(OrganizeId);
		List<Role> baseRole_list=roleService.selectByColum(role, " REALNAME,ORGANIZEID,fuid");
		if(baseRole_list!=null){
			for(int a=0;a<baseRole_list.size();a++){
				List<Menu> baseMenu_list=menuService.selectMop(baseRole_list.get(a).getFuid());
				Role r=baseRole_list.get(a);
				if(baseMenu_list!=null){
					for(int b=0;b<baseMenu_list.size();b++){
						r.getChildren().add(baseMenu_list.get(b));
					}
				}
			}
		}
		out.print(JSON.toJSON(baseRole_list));
		return null;
	}
	@RequestMapping("ScopeQx")
	@ResponseBody
	public String ScopeQx(String id,String MenuId,String RoleId){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		if(id!=null){
			Scope scope=new Scope();
			scope.setMenuId(MenuId);
			scope.setRoleId(RoleId);
			scopeService.deleteByColum(scope);
			String fuid[]=id.split(",");
			if(fuid!= null){
				for(int a=0;a<fuid.length;a++){
					Scope baseScope=new Scope();
					baseScope.setFuid(UUIDCreater.getUUID());
					baseScope.setMenuId(MenuId);
					baseScope.setRoleId(RoleId);
					baseScope.setOrganizeId(fuid[a]);
					baseScope.setCreatedate(new Date());
					baseScope.setEnabled(1);
					baseScope.setDeletemark(0);
					baseScope.setCreateuserid(userid);
					baseScope.setCreateuserrealname(username);
					baseScope.setModifydate(new Date());
					baseScope.setModifyuserid(userid);
					baseScope.setModifyuserrealname(username);
					scopeService.insert(baseScope);
				}
				/*
				 * 记录操作日志
				 */
				Role  baseRole =roleService.findById(RoleId);
				Menu  baseMenu =menuService.findById(MenuId);
				operationLogService.saveLog("为角色"+baseRole.getRealname()+"分配"+baseMenu.getMenuName()+"菜单数据集权限");
				WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
		        ServletContext servletContext = webApplicationContext.getServletContext(); 
				CompetenceManager a=new CompetenceManager();
				a.RefreshDataScope(servletContext);
			}
		}
		return null;
	}
	@RequestMapping("scope_organize")
	public String scope_organize(){
		
		return "system/scope_organize";
	}
	@RequestMapping("edit")
	public String edit(Scope baseScope){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=baseScope.getFuid();
		if(id!=null&&id.length()>0){
			baseScope.setModifydate(new Date());
			baseScope.setModifyuserid(userid);
			baseScope.setModifyuserrealname(username);
			scopeService.updateSelective(baseScope);
		}else{
			baseScope.setFuid(UUIDCreater.getUUID());
			baseScope.setCreatedate(new Date());
			baseScope.setCreateuserid(userid);
			baseScope.setCreateuserrealname(username);
			baseScope.setModifydate(new Date());
			baseScope.setModifyuserid(userid);
			baseScope.setModifyuserrealname(username);
			scopeService.insert(baseScope);
		}
		return "show";
	}
	@RequestMapping("update_show")
	public String update_show(String id,Map<String,Object>model){
		Scope baseScope=scopeService.findById(id);
		model.put("baseScope", baseScope);
		model.put("id",id);
		return "baseScopeEditor";
	}
	public String delete(String id){
		scopeService.deleteById(id);
		return "";
	}
	
 
	
}
