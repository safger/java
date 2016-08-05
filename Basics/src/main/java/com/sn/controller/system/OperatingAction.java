package com.sn.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sn.common.UUIDCreater;
import com.sn.entity.Menu;
import com.sn.entity.Operating;
import com.sn.service.MenuService;
import com.sn.service.OperatingService;
import com.sn.service.OperationLogService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("system/operating/")
public class OperatingAction {
	
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private  HttpServletResponse response;
	@Autowired
	private OperationLogService operationLogService;
	@Autowired
	private OperatingService operatingService;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("show")
	public String show(){
		return "system/operating";
	}
	@RequestMapping("Operatingshow")
	public String Operatingshow(String MenuId,String OrganizeId,Map<String,Object>model){
		Operating operating=new Operating();
		operating.setMenuid(MenuId);
		List<Operating> baseOperating_list=operatingService.selectOperationMenu(operating);
		model.put("baseOperating_list", baseOperating_list);
		model.put("MenuId", MenuId);
		model.put("OrganizeId", OrganizeId);
		 return "system/operating_info";
	}
	@RequestMapping("operating_info_edit")
	public String operating_info_edit(){
		return "system/operating_info_edit";
	}
	@RequestMapping("edit")
	public String edit(Operating baseOperating,String MenuId,Map<String,Object>model){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=baseOperating.getFuid();
		if(id!=null&&id.length()>0){
			baseOperating.setModifydate(new Date());
			baseOperating.setModifyuserid(userid);
			baseOperating.setModifyuserrealname(username);
			operatingService.updateSelective(baseOperating);
			/*
			 * 记录操作日志
			 */
			Menu  baseMenu =menuService.findById(MenuId);
			operationLogService.saveLog("修改菜单"+baseMenu.getMenuName()+baseOperating.getFullname()+"的操作权限");
		}else{
			baseOperating.setFuid(UUIDCreater.getUUID());
			baseOperating.setDeletemark(0);
			baseOperating.setMenuid(MenuId);
			baseOperating.setCreatedate(new Date());
			baseOperating.setCreateuserid(userid);
			baseOperating.setCreateuserrealname(username);
			baseOperating.setModifydate(new Date());
			baseOperating.setModifyuserid(userid);
			baseOperating.setModifyuserrealname(username);
			operatingService.insert(baseOperating);
			/*
			 * 记录操作日志
			 */
			Menu  baseMenu =menuService.findById(MenuId);
			operationLogService.saveLog("新增菜单"+baseMenu.getMenuName()+baseOperating.getFullname()+"的操作权限");
		}
		model.put("MenuId", MenuId);
		return "redirect:/system/operating/Operatingshow.html";
	}
	@RequestMapping("update_show")
	public String update_show(String id,Map<String,Object>model){
		Operating baseOperating=operatingService.findById(id);
		model.put("baseOperating", baseOperating);
		model.put("id", id);
		return "system/operating_info_edit";
	}
	@RequestMapping("delete")
	public String delete(String id,String MenuId){
		Operating baseOperating= operatingService.findById(id);
		baseOperating.setDeletemark(1);
		operatingService.update(baseOperating);
		/*
		 * 记录操作日志
		 */
		Menu  baseMenu =menuService.findById(MenuId);
		operationLogService.saveLog("删除菜单"+baseMenu.getMenuName()+baseOperating.getFullname()+"的操作权限");
		return "redirect:/system/operating/Operatingshow.html";
	}
	@RequestMapping("tree")
	@ResponseBody
	public String tree() throws IOException{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		List<Menu> parentList=new ArrayList<Menu>();
		List<Menu> baseMenu_list = menuService.selectAll("MENU_ORDER,fuid");
		for(int i=0;i<baseMenu_list.size();i++){
			Menu m = (Menu) baseMenu_list.get(i);
    		if(m.getMenuParentid().equals("1")){
    			m = getChild(baseMenu_list, m.getFuid(), m);
    			parentList.add(m);
    		}
    	}
		String strs = JSON.toJSONString(parentList);
		out.print(strs);
		return null;
	}
	 public Menu getChild(List<?> list,String id,Menu baseMenu){
	    	for(int i=0;i<list.size();i++){
	    		Menu m = (Menu) list.get(i);
	    		if(!m.getMenuParentid().equals("1")&&m.getMenuParentid().equals(id)){
	    			m = getChild(list, m.getFuid(), m);
	    			baseMenu.getChildren().add(m);
	    		}
	    	}
	    	return baseMenu;
	    }
	 
	 
 
}
