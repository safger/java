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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.sn.common.UUIDCreater;
import com.sn.entity.Organize;
import com.sn.entity.OrganizeMenu;
import com.sn.entity.Scope;
import com.sn.service.OperationLogService;
import com.sn.service.OrganizeMenuService;
import com.sn.service.OrganizeService;
import com.sn.service.ScopeService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("system/organize/")
public class OrganizeAction{
	
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private OperationLogService operationLogService;
	@Autowired
	private OrganizeService organizeService;
	@Autowired
	private OrganizeMenuService organizeMenuService;
	private ComData com;
	@Autowired
	private ScopeService baseScopeService;
	
	public String add(){
		
		return null;
	}
	@RequestMapping("show")
	public String Organize(){
		return "system/Organize";
	}
	@RequestMapping("o_competence")
	public String o_competence(){
		return "system/Organize_competence";
	}
	@RequestMapping("edit")
	@ResponseBody
	public String edit(Organize baseOrganize){
		String id=baseOrganize.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "system/organize/show.html");
			if(!com.getHisSelect()){
				return "error";
			}
			baseOrganize.setParentid(null);
			organizeService.updateSelective(baseOrganize);
		}else{
			com=CompetenceManager.getCom(roleid, "system/organize/show.html");
			if(!com.getHisSelect()){
				return "error";
			}
			baseOrganize.setFuid(UUIDCreater.getUUID());
			if(baseOrganize.getParentid()!=null){
				String parentid=baseOrganize.getParentid();
				 Organize Organize=organizeService.findById(parentid);
				 if(Organize.getParentid()!=null){
					 baseOrganize.setLayer(Organize.getLayer()+1);
				 } 
			}else{
				baseOrganize.setLayer(1);
			}
			baseOrganize.setDeletemark(0);
			baseOrganize.setEnabled(1);
			baseOrganize.setCreatedate(new Date());
			baseOrganize.setModifydate(new Date());
			organizeService.insert(baseOrganize);
		}
		CompetenceManager a=new CompetenceManager();
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext servletContext = webApplicationContext.getServletContext();  
		a.RefreshDataOrganize(servletContext);
		return null;
	}
	@RequestMapping("update_show")
	public String update_show(String id,Map<String,Object> model){
		Organize baseOrganize=organizeService.findById(id);
		model.put("id",id);
		model.put("baseOrganize",baseOrganize);
		return "system/OrganizeEditor";
	}
	@RequestMapping("add_show")
	public String add_show(String parentid,Map<String,Object>model){
		model.put("parentid", parentid);
		return "system/OrganizeEditor";
	}
	
	@RequestMapping("tree")
	@ResponseBody
	public String tree() throws IOException{
		response.setCharacterEncoding("UTF-8");
		String departmentid=(String)request.getSession().getAttribute("departmentid");
		int layer=organizeService.findById(departmentid).getLayer();
		PrintWriter out=response.getWriter();
		List<Organize> parentList=new ArrayList<Organize>();
		List<Organize> baseOrganize_list=organizeService.selectOrg(departmentid,layer);
		for(int i=0;i<baseOrganize_list.size();i++){
			Organize m = (Organize) baseOrganize_list.get(i); 
			if(m.getLayer()==layer){
				m = getChild(baseOrganize_list, m.getFuid(), m);
				parentList.add(m);
			} 
		}
		String strs = JSON.toJSONString(parentList);
		out.print(strs);
		return null;
	}
	@RequestMapping("ScopeTree")
	@ResponseBody
	public String ScopeTree(String RoleId,String MenuId) throws IOException{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		List<Organize> parentList=new ArrayList<Organize>();
		Organize organize=new Organize();
		organize.setDeletemark(0);
		List<Organize> baseOrganize_list=organizeService.selectByColum(organize,"SortCode");
		Scope scope=new Scope();
		scope.setRoleId(RoleId);
		scope.setMenuId(MenuId);
		List<Scope> baseScope_list=baseScopeService.selectByColum(scope);
		
		for(int i=0;i<baseOrganize_list.size();i++){
			baseOrganize_list.get(i).setText(baseOrganize_list.get(i).getFullname());
			baseOrganize_list.get(i).setId(baseOrganize_list.get(i).getFuid());
			if(baseScope_list!=null){
				for(int a=0;a<baseScope_list.size();a++){
					if(baseScope_list.get(a).getOrganizeId().equals(baseOrganize_list.get(i).getFuid())){
						baseOrganize_list.get(i).setChecked(true);
					}
				}
			}
			Organize m = (Organize) baseOrganize_list.get(i);
    		if(m.getParentid().equals("1")){
    			m = getChild(baseOrganize_list, m.getFuid(), m);
    			parentList.add(m);
    		}
    	}
		String strs = JSON.toJSONString(parentList);
		out.print(strs);
		return null;
	}
	 public Organize getChild(List<?> list,String id,Organize organize){
	    	for(int i=0;i<list.size();i++){
	    		Organize m = (Organize) list.get(i);
	    		if(!m.getParentid().equals("1")&&m.getParentid().equals(id)){
	    			m = getChild(list, m.getFuid(), m);
	    			organize.getChildren().add(m);
	    		}
	    	}
	    	return organize;
	    }
	 @RequestMapping("delete")
	 @ResponseBody
	 public String delete(String id) throws IOException{
		 PrintWriter out=response.getWriter();
		 Organize  baseOrganize =organizeService.findById(id);
		 baseOrganize.setDeletemark(1);
		 organizeService.update(baseOrganize);
		 out.print(1);
		 CompetenceManager a=new CompetenceManager();
		 WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
	     ServletContext servletContext = webApplicationContext.getServletContext();  
		 a.RefreshDataOrganize(servletContext);
		 return null;
	 }
	 @RequestMapping("Assign")
	public String Assign(String OrganizeId,String id,Map<String,Object>model){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		if(id!=null){
			OrganizeMenu organizeMenu=new OrganizeMenu();
			organizeMenu.setOrganizeId(OrganizeId);
			organizeMenuService.deleteByColum(organizeMenu);
			String fuid[]=id.split(",");
			if(fuid!=null){
				for(int a=0;a<fuid.length;a++){
					String temp[]=fuid[a].trim().split("~");
					OrganizeMenu baseOrganizeMenu=new OrganizeMenu();
					baseOrganizeMenu.setFuid(UUIDCreater.getUUID());
					baseOrganizeMenu.setOrganizeId(OrganizeId);
					baseOrganizeMenu.setMenuId(temp[0]);
					baseOrganizeMenu.setOperationid(temp[1]);
					baseOrganizeMenu.setCreatedate(new Date());
					baseOrganizeMenu.setCreateuserid(userid);
					baseOrganizeMenu.setCreateuserrealname(username);
					baseOrganizeMenu.setModifydate(new Date());
					baseOrganizeMenu.setModifyuserid(userid);
					baseOrganizeMenu.setModifyuserrealname(username);
					organizeMenuService.insert(baseOrganizeMenu);
				}
			}
		}
		/*
		 * 记录操作日志
		 */
		Organize  baseOrganize =organizeService.findById(OrganizeId);
		operationLogService.saveLog("为部门"+baseOrganize.getFullname()+"分配部门权限");
		model.put("OrganizeId", OrganizeId);
		return "redirect:/system/menu/showMenu.html";
	}
 
	 
}
