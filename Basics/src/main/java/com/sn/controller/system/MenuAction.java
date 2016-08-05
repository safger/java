package com.sn.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.sn.common.FileUpload;
import com.sn.common.UUIDCreater;
import com.sn.entity.Menu;
import com.sn.entity.MenuRole;
import com.sn.entity.Operating;
import com.sn.entity.OrganizeMenu;
import com.sn.service.MenuRoleService;
import com.sn.service.MenuService;
import com.sn.service.OperatingService;
import com.sn.service.OperationLogService;
import com.sn.service.OrganizeMenuService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("system/menu/")
public class MenuAction {
	
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private  HttpServletResponse response;
	@Autowired
	private OperationLogService operationLogService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private OperatingService operatingService;
	@Autowired
	private MenuRoleService menuRoleService;
	@Autowired
	private OrganizeMenuService organizeMenuService;
	
	private List<Menu> baseMenu_all;
	
	private List<MenuRole> MenuRole_list;
	
	private List<OrganizeMenu> baseOrganizeMenu_list;
	
	@RequestMapping("show")
	public String show(Map<String,Object> model){
		List<Menu> baseMenu_list=menuService.selectAll("MENU_ORDER");
		List<Menu> parentList=new ArrayList<Menu>();
		if(baseMenu_list!=null&&baseMenu_list.size()>0){
			for(int a=0;a<baseMenu_list.size();a++){
				Menu m = baseMenu_list.get(a);
				if(m.getMenuParentid()!=null&&m.getMenuParentid().equals("1")){
					m = getChildMenu(baseMenu_list, m.getFuid(), m);
					System.out.println(m.getMenuName());
					parentList.add(m);
				}
			}
		}
		model.put("baseMenu_list",baseMenu_list);
		model.put("parentList",parentList);
		
		return "system/menu";
	}
	@RequestMapping("showMenu")
	public String showMenu(String OrganizeId,Map<String,Object>model){
		model.put("OrganizeId", OrganizeId);
		 
		 return "system/menu_organize";
	}
	@RequestMapping("edit")
	public String edit(@RequestParam("uploadpic")MultipartFile[] uploadpic,Menu baseMenu,String parentid,Map<String,Object> model) throws IOException{
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=baseMenu.getFuid();
		if(id!=null&&id.length()>0){
			baseMenu.setModifydate(new Date());
			baseMenu.setModifyuserid(userid); 
			baseMenu.setModifyuserrealname(username);
			ArrayList<String> img=FileUpload.uploadFile(uploadpic, request,false);
			if(img!=null&&img.size()>0){
				baseMenu.setImages(img.get(0));
			}
			menuService.updateSelective(baseMenu);
			/*
			 * 记录操作日志
			 */
			operationLogService.saveLog("修改菜单"+baseMenu.getMenuName()+"的信息");
		}else{
			baseMenu.setFuid(UUIDCreater.getUUID());
			if(parentid!=null&&!parentid.equals("null")&&parentid.length()>0){
				baseMenu.setMenuParentid(parentid);
			}else{
				baseMenu.setMenuParentid("1");
			}
			baseMenu.setCreatedate(new Date());
			baseMenu.setCreateuserid(userid);
			baseMenu.setCreateuserrealname(username);
			baseMenu.setModifydate(new Date());
			baseMenu.setModifyuserid(userid);
			baseMenu.setModifyuserrealname(username);
			ArrayList<String> img=FileUpload.uploadFile(uploadpic, request,false);
			if(img!=null&&img.size()>0){
				baseMenu.setImages(img.get(0));
			}
			menuService.insert(baseMenu);
			/*
			 * 记录操作日志
			 */
			operationLogService.saveLog("新增菜单"+baseMenu.getMenuName()+"的信息");
		}
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext servletContext = webApplicationContext.getServletContext(); 
		CompetenceManager a=new CompetenceManager();
		a.refreshRoleMenu(servletContext);
		return "redirect:/system/menu/show.html"; 
	}
	@RequestMapping("updateShow")
	public String update_show(String parentid,String id,Map<String,Object>model){
		Menu baseMenu=menuService.findById(id);
		model.put("baseMenu",baseMenu);
		model.put("id",id);
		model.put("parentid",parentid);
		return "system/menu_edit";
	}
	@RequestMapping("delete")
	public String delete(String id,Menu baseMenu){
		MenuRole menuRole=new MenuRole();
		menuRole.setMenuId(id);
		 List<MenuRole>baseMenuRole_ist =menuRoleService.selectByColum(menuRole);
		 if(baseMenuRole_ist!=null&&baseMenuRole_ist.size()>0){
			 List <String> ids=new ArrayList<String>();
			 for(int a=0;a<baseMenuRole_ist.size();a++){
				 ids.add(baseMenuRole_ist.get(a).getFuid());
			 }
			 menuRoleService.deleteByByPrimaryKeys(ids);
		 }
		menuService.deleteById(id);
		/*
		 * 记录操作日志
		 */
		operationLogService.saveLog("删除菜单"+baseMenu.getMenuName());
		return "redirect:/system/menu/show.html"; 
	}
	@RequestMapping("menu_edit")
	public String menu_edit(Map<String,Object>model,String id,String parentid){
		model.put("id",id);
		model.put("parentid",parentid);
		return "system/menu_edit";
	}
	@RequestMapping("deletePar")
	public String deletePar(String parentid) throws IOException{
		PrintWriter out=response.getWriter();
		Menu m=new Menu();
		m.setMenuParentid(parentid);
		int a=menuService.countByColum(m);
		if(a>0){
			out.print("0");
		}else{
			menuService.deleteById(parentid);
			out.print("1");
		}
		return "redirect:/system/menu/show.html"; 
	}
	
	
	@RequestMapping("ShowMenuRole")
	public String ShowMenuRole(){
		return "system/menu_role";
	}
	@RequestMapping("treeRole")
	@ResponseBody
	public String treeRole(String OrganizeId,String RoleId) throws IOException{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		List<Menu> parentList=new ArrayList<Menu>();
		String superAdmin=(String)request.getSession().getAttribute("superAdmin");
		List<Menu> baseMenu_list=new ArrayList<Menu>();
		if(superAdmin!=null&&superAdmin.equals("spadmin")){
			baseMenu_list=menuService.selectAll("menu_order");
		}else{
			baseMenu_list=menuService.selectMenuOrg(OrganizeId);
		}
		List<Menu> baseMenu_all=menuService.selectAll("fuid");
				for(int i=0;i<baseMenu_list.size();i++){
					Menu n=hasParent(baseMenu_all,baseMenu_list.get(i).getMenuParentid());
					if(n!=null){
						if(!isexit(n.getFuid(),baseMenu_list)){
							baseMenu_list.add(n);
						}
					}
				} 
		MenuRole menuRole=new MenuRole(); 
		menuRole.setRoleId(RoleId);
		MenuRole_list =menuRoleService.selectByColum(menuRole);
		for(int i=0;i<baseMenu_list.size();i++){
			Menu m = baseMenu_list.get(i);
			m.setText(m.getMenuName());
			m.setId(m.getFuid());
			if(m.getMenuParentid().equals("1")){
				Menu m1 = getChildRole(baseMenu_list, m.getFuid(), m,superAdmin);
				parentList.add(m1);
			}
		}
		String strs = JSON.toJSONString(parentList);
		out.print(strs);
		return null;
	}
	public Menu hasParent(List<Menu> list,String parentid){
		Menu m=null;
		if(list!=null){
			for(int i=0;i<list.size();i++){
				if(list.get(i).getFuid().equals(parentid)){
					m=list.get(i); 
				}
			}
		} 
		return m;
	}
	public Boolean isexit(String id,List<Menu> list){
		Boolean s=false;
		if(list!=null){
			for(int i=0;i<list.size();i++){
				if(list.get(i).getFuid().equals(id)){
					s=true;
					break;
				} 
			}
		} 
		return s;
	}
	public int hasParent(String id,List<Menu> list){
		int s=0;
		if(list!=null){
			for(int i=0;i<list.size();i++){
				if(list.get(i).getMenuParentid().equals(id)){
					s++;
				} 
			}
		} 
		return s;
	}
	public Menu getChildRole(List<?> list,String id,Menu menu,String superAdmin){
		String OrganizeId=(String)request.getAttribute("OrganizeId");
		for(int i=0;i<list.size();i++){
			Menu m = (Menu) list.get(i);
			if(!m.getMenuParentid().equals("1")&&m.getMenuParentid().equals(id)){
				m = getChildRole(list, m.getFuid(), m,superAdmin);
				int g=this.hasParent(m.getFuid(), baseMenu_all);
				if(g==0){
					List<Operating> Op_lst=new ArrayList<Operating>();
					if(superAdmin!=null&&superAdmin.equals("spadmin")){
						Operating operating=new Operating();
						operating.setDeletemark(0);
						Op_lst=operatingService.selectByColum(operating);
					}else{
						Op_lst=operatingService.selectOp(OrganizeId,m.getFuid());
					}
					if(Op_lst!=null){
						for(int d=0;d<Op_lst.size();d++){
							String code=Op_lst.get(d).getCode();
							String menuid=Op_lst.get(d).getMenuid();
							//判断操作是否该菜单下面 包含基础操作
							if((code!=null&&code.startsWith("base"))||(menuid!=null&&menuid.equals(m.getFuid()))){
								Op_lst.get(d).setText(Op_lst.get(d).getFullname());
								Op_lst.get(d).setId(Op_lst.get(d).getFuid());
								Op_lst.get(d).setAttributes(Op_lst.get(d).getCode());
								Op_lst.get(d).setChecked(false);
								String opid=Op_lst.get(d).getFuid();
								if(MenuRole_list!=null){
									for(int f=0;f<MenuRole_list.size();f++){
										String menu_id=MenuRole_list.get(f).getMenuId();
										String operating_id=MenuRole_list.get(f).getOperatingId();
										//判断该菜单是否有该操作
										if(menu_id!=null&&menu_id.equals(m.getFuid())&&operating_id!=null&&operating_id.equals(opid)){
											Op_lst.get(d).setChecked(true);
										}
									}
								}
								m.getChildren().add(Op_lst.get(d));
							}
							
						}
					}
				} 
				menu.getChildren().add(m);
			} 
		}
		return menu;
	}
	@RequestMapping("treeOrg")
	@ResponseBody
	public String treeOrg() throws IOException{
		response.setCharacterEncoding("UTF-8");
		String OrganizeId=(String)request.getAttribute("OrganizeId");
		PrintWriter out=response.getWriter();
		List<Menu> parentList=new ArrayList<Menu>();
		List<Menu> baseMenu_list=menuService.selectAll("menu_order");
		OrganizeMenu organizeMenu=new OrganizeMenu();
		organizeMenu.setOrganizeId(OrganizeId);
		 baseOrganizeMenu_list=organizeMenuService.selectByColum(organizeMenu);
		for(int i=0;i<baseMenu_list.size();i++){
			Menu m = (Menu) baseMenu_list.get(i);
			m.setText(m.getMenuName());
			m.setId(m.getFuid());
			if(m.getMenuParentid().equals("1")){
    			m = getChildOrg(baseMenu_list, m.getFuid(), m);
    			parentList.add(m);
    		}
    	}
		String strs = JSON.toJSONString(parentList);
		out.print(strs);
		return null;
	}
	public Menu getChildOrg(List<?> list,String id,Menu menu){
		for(int i=0;i<list.size();i++){
			Menu m = (Menu) list.get(i);
			if(!m.getMenuParentid().equals("1")&&m.getMenuParentid().equals(id)){
				m = getChildOrg(list, m.getFuid(), m);
				int g=this.hasParent(m.getFuid(), baseMenu_all);
				if(g==0){
					Operating o=new Operating();
					o.setDeletemark(0);
					List<Operating> Op_lst=operatingService.selectByColum(o);
					for(int d=0;d<Op_lst.size();d++){
						String code=Op_lst.get(d).getCode();
						String op_menuid=Op_lst.get(d).getMenuid();
						//判断操作是否该菜单下面 包含基础操作
						if((code!=null&&code.startsWith("base"))||(op_menuid!=null&&op_menuid.equals(m.getFuid()))){
							Op_lst.get(d).setText(Op_lst.get(d).getFullname());
							Op_lst.get(d).setId(Op_lst.get(d).getFuid());
							Op_lst.get(d).setAttributes(Op_lst.get(d).getCode());
							Op_lst.get(d).setChecked(false);
							String opid=Op_lst.get(d).getFuid();
							if(baseOrganizeMenu_list!=null){
								for(int f=0;f<baseOrganizeMenu_list.size();f++){
									String menu_id=baseOrganizeMenu_list.get(f).getMenuId();
									String operating_id=baseOrganizeMenu_list.get(f).getOperationid();
									//判断该菜单是否有该操作
									if(menu_id!=null&&menu_id.equals(m.getFuid())&&operating_id!=null&&operating_id.equals(opid)){
										Op_lst.get(d).setChecked(true);
									}
								}
							}
							m.getChildren().add(Op_lst.get(d));
						}
						
					}
				}
				menu.getChildren().add(m);
			}
		}
		return menu;
	}
	//获取菜单的子菜单
	public Menu getChildMenu(List<?> list,String id,Menu menu){
    	for(int i=0;i<list.size();i++){
    		Menu m = (Menu) list.get(i);
    		if(!m.getMenuParentid().equals("1")&&m.getMenuParentid().equals(id)){
    			m = getChildOrg(list, m.getFuid(), m);
    			menu.getChildren().add(m);
    		}
    	}
    	return menu;
    }
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public OperationLogService getOperationLogService() {
		return operationLogService;
	}
	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
	public MenuService getMenuService() {
		return menuService;
	}
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	public OperatingService getOperatingService() {
		return operatingService;
	}
	public void setOperatingService(OperatingService operatingService) {
		this.operatingService = operatingService;
	}
	public MenuRoleService getMenuRoleService() {
		return menuRoleService;
	}
	public void setMenuRoleService(MenuRoleService menuRoleService) {
		this.menuRoleService = menuRoleService;
	}
	public OrganizeMenuService getOrganizeMenuService() {
		return organizeMenuService;
	}
	public void setOrganizeMenuService(OrganizeMenuService organizeMenuService) {
		this.organizeMenuService = organizeMenuService;
	}
	public List<Menu> getBaseMenu_all() {
		return baseMenu_all;
	}
	public void setBaseMenu_all(List<Menu> baseMenu_all) {
		this.baseMenu_all = baseMenu_all;
	}
	public List<MenuRole> getMenuRole_list() {
		return MenuRole_list;
	}
	public void setMenuRole_list(List<MenuRole> menuRole_list) {
		MenuRole_list = menuRole_list;
	}
	public List<OrganizeMenu> getBaseOrganizeMenu_list() {
		return baseOrganizeMenu_list;
	}
	public void setBaseOrganizeMenu_list(List<OrganizeMenu> baseOrganizeMenu_list) {
		this.baseOrganizeMenu_list = baseOrganizeMenu_list;
	}

 
}
