package com.sn.controller.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.sn.common.UUIDCreater;
import com.sn.entity.MenuRole;
import com.sn.entity.Role;
import com.sn.service.MenuRoleService;
import com.sn.service.OperationLogService;
import com.sn.service.RoleService;
import com.sn.util.PagedResult;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("system/role/")
public class RoleAction  {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private OperationLogService operationLogService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuRoleService menuRoleService;
	
	
	@RequestMapping("show")
	public String show(String skey,Integer pageSize,Integer indexPage,Map<String,Object>model){
		String SqlWhere="";
		if(skey!=null&&skey.length()>0){
			if(SqlWhere.length()==0){
				SqlWhere+=" fname like '%"+skey+"%'";
			}else{
				SqlWhere+=" and fname like '%"+skey+"%'";
			}
		} 
		Role role=new Role();
		role.setRealname(skey);
		role.setDeletemark(0);
		PagedResult<Role> page_list=roleService.findByPageCustom(role, indexPage, pageSize," ORGANIZEID,REALNAME,fuid");
		List<Role>baseRole_list=page_list.getDataList();
		model.put("baseRole_list", baseRole_list);
		model.put("indexPage", indexPage);
		model.put("pageSize", pageSize);
		return "system/role";
	}
	@RequestMapping("showRole")
	public String showRole(String OrganizeId,Map<String,Object>model){
		Role role=new Role();
		role.setDeletemark(0);
		role.setOrganizeid(OrganizeId);
		List<Role> baseRole_list=roleService.selectByColum(role,"REALNAME,ORGANIZEID,fuid");
		model.put("OrganizeId", OrganizeId);
		model.put("baseRole_list", baseRole_list);
		return "system/role";
	}
	@RequestMapping("roleCompetence")
	public String roleCompetence(){
		return "system/role_competence";
	}
	@RequestMapping("scopeRole")
	public String scopeRole(String OrganizeId,Map<String,Object>model){
		Role role=new Role();
		role.setDeletemark(0);
		role.setOrganizeid(OrganizeId);
		List<Role> baseRole_list=roleService.selectByColum(role,"REALNAME,ORGANIZEID,fuid");
		model.put("OrganizeId", OrganizeId);
		model.put("baseRole_list", baseRole_list);
		return "scopeRole";
	}
	@RequestMapping("showChange")
	public String showChange(String OrganizeId,Map<String,Object>model){
		String departmentid=(String)request.getSession().getAttribute("departmentid");
		String superAdmin=(String)request.getSession().getAttribute("superAdmin");
		Role role=new Role();
		role.setDeletemark(0);
		if(superAdmin!=null&&superAdmin.equals("spadmin")){
			role.setOrganizeid(OrganizeId);
		}else{
			role.setOrganizeid(departmentid);
		}
		List<Role> baseRole_list=roleService.selectByColum(role,"REALNAME,ORGANIZEID,fuid");
		model.put("OrganizeId", OrganizeId);
		model.put("baseRole_list", baseRole_list);
		return "system/role_change";
	}
	@RequestMapping("edit")
	public String edit(Role baseRole,String OrganizeId,Map<String,Object>model){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=baseRole.getFuid();
		if(id!=null&&id.length()>0){
			baseRole.setModifydate(new Date());
			baseRole.setModifyuserid(userid);
			baseRole.setModifyuserrealname(username);
			baseRole.setEnabled(1);
			roleService.updateSelective(baseRole);
			/*
			 * 记录操作日志
			 */
			operationLogService.saveLog("修改角色"+baseRole.getRealname());
		}else{
			baseRole.setFuid(UUIDCreater.getUUID());
			baseRole.setDeletemark(0);
			baseRole.setOrganizeid(OrganizeId);
			baseRole.setCreatedate(new Date());
			baseRole.setCreateuserid(userid);
			baseRole.setEnabled(1);
			baseRole.setCreateuserrealname(username);
			baseRole.setModifydate(new Date());
			baseRole.setModifyuserid(userid);
			baseRole.setModifyuserrealname(username);
			roleService.insert(baseRole);
			/*
			 * 记录操作日志
			 */
			operationLogService.saveLog("新增角色"+baseRole.getRealname());
		}
		model.put("OrganizeId", OrganizeId);
		return "redirect:/system/role/showRole.html"; 
	}
	@RequestMapping("Enable")
	public String Enable(String id,Role baseRole,String OrganizeId,Map<String,Object>model){
		if(id!=null&&id.length()>0){
			baseRole.setEnabled(1);
			roleService.updateSelective(baseRole);
		} 
		model.put("OrganizeId", OrganizeId);
		return "redirect:/system/role/showRole.html"; 
	}
	@RequestMapping("update_show")
	public String update_show(String id,Map<String,Object>model){
		Role baseRole=roleService.findById(id);
		model.put("baseRole", baseRole);
		model.put("id", id);
		return "system/role_edit";
	}
	@RequestMapping("add_show")
	public String add_show(String OrganizeId,Map<String,Object>model){
		model.put("OrganizeId", OrganizeId);
		return "system/role_edit";
	}
	@RequestMapping("delete")
	public String delete(String id,String OrganizeId,Map<String,Object>model){
		roleService.deleteById(id);
		model.put("OrganizeId", OrganizeId);
		return "redirect:/system/role/showRole.html"; 
	}
	@RequestMapping("valid")
	public String valid(String skey,String id,String OrganizeId,Map<String,Object>model){
		Role  baseRole =roleService.findById(id);
		if(skey!=null&&skey.equals("1")){
			baseRole.setEnabled(1);
			roleService.update(baseRole);
		}else{
			baseRole.setEnabled(0);
			roleService.update(baseRole);
		}
		model.put("OrganizeId", OrganizeId);
		return "redirect:/system/role/showRole.html"; 
	}
	@RequestMapping("Assign")
	@ResponseBody
	public String Assign(String id,String RoleId){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		if(id!=null){
			MenuRole menuRole=new MenuRole();
			menuRole.setRoleId(RoleId);
			menuRoleService.deleteByColum(menuRole);
			String fuid[]=id.split(",");
			if(fuid!=null){
				for(int a=0;a<fuid.length;a++){
					String tt[]=fuid[a].trim().split(";");
					MenuRole baseMenuRole=new MenuRole();
					baseMenuRole.setFuid(UUIDCreater.getUUID());
					baseMenuRole.setRoleId(RoleId);
					baseMenuRole.setOperatingId(tt[1].trim());
					baseMenuRole.setMenuId(tt[0].trim());
					baseMenuRole.setCreatedate(new Date());
					baseMenuRole.setCreateuserid(userid);
					baseMenuRole.setCreateuserrealname(username);
					baseMenuRole.setModifydate(new Date());
					baseMenuRole.setModifyuserid(userid);
					baseMenuRole.setModifyuserrealname(username);
					menuRoleService.insert(baseMenuRole);
				}
				/*
				 * 记录操作日志
				 */
				Role baseRole =roleService.findById(RoleId);
				operationLogService.saveLog("为角色"+baseRole.getRealname()+"分配权限");
			}
		}
		/*
		 * 刷新缓存
		 */
		CompetenceManager a=new CompetenceManager();
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext servletContext = webApplicationContext.getServletContext(); 
		a.refreshRoleMenu(servletContext);
		return null;
	}
 
}
