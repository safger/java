package com.sn.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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

import com.sn.common.UUIDCreater;
import com.sn.entity.Datadictionary;
import com.sn.service.DatadictionaryService;
import com.sn.util.PagedResult;

@Controller
@RequestMapping("system/data/")
public class DatadictionaryAction  {
	
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private  HttpServletResponse response;
	@Autowired
	private DatadictionaryService datadictionaryService;
	private ComData com;
	
	@RequestMapping("show")
	public String show(String skey,String parentcode,Integer indexPage,Integer pageSize,Map<String,Object>model){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "system/data/show.html");
		if(!com.getHisSelect()){
			return "error";
		}
		Datadictionary datadictionary=new Datadictionary();
		datadictionary.setFuid("3");
		if(skey!=null&&skey.length()>0){
			datadictionary.setFullname(skey);
		}
		if(parentcode!=null&&parentcode.length()>0){
			datadictionary.setCode(parentcode);
		}
		PagedResult<Datadictionary> page_list=datadictionaryService.findByPageCustom(datadictionary, indexPage, pageSize, " Sequence");
		long totalPage= page_list.getTotal();
		model.put("totalPage", totalPage);
		model.put("skey", skey);
		model.put("pageSize", page_list.getPageSize());
		model.put("indexPage", page_list.getPageNo());
		model.put("com", com);
		model.put("baseDatadictionary_list", page_list.getDataList());
		return "system/Type";
	}
	@RequestMapping("add_show")
	public String add(){
		
		return "system/TypeEditor";
	}
	@RequestMapping("showChild2")
	public String showChild2(String skey,Integer indexPage,String parentcode,Integer pageSize,Map<String,Object>model){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "system/data/show.html");
		if(!com.getHisSelect()){
			return "error";
		}
		Datadictionary datadictionary=new Datadictionary();
		datadictionary.setFuid("6");
		datadictionary.setCode(parentcode);
		if(skey!=null&&skey.length()>0){
			datadictionary.setFullname(skey);
		} 
		PagedResult<Datadictionary> page_list=datadictionaryService.findByPageCustom(datadictionary, indexPage, pageSize, " Sequence");
		long totalPage= page_list.getTotal();
		model.put("totalPage", totalPage);
		model.put("parentcode", parentcode);
		model.put("skey", skey);
		model.put("pageSize", page_list.getPageSize());
		model.put("indexPage", page_list.getPageNo());
		model.put("com", com);
		model.put("baseDatadictionary_list", page_list.getDataList());
		return "system/Type2";
	}
	@RequestMapping("showChild3")
	public String showChild3(String skey,Integer indexPage,String parentcode,Integer pageSize,Map<String,Object>model){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "system/data/show.html");
		if(!com.getHisSelect()){
			return "error";
		}
		Datadictionary datadictionary=new Datadictionary();
		datadictionary.setFuid("9");
		datadictionary.setCode(parentcode);
		if(skey!=null&&skey.length()>0){
			datadictionary.setFullname(skey);
		} 
		PagedResult<Datadictionary> page_list=datadictionaryService.findByPageCustom(datadictionary, indexPage, pageSize, " Sequence");
		long totalPage= page_list.getTotal();
		model.put("totalPage", totalPage);
		model.put("parentcode", parentcode);
		model.put("pageSize", page_list.getPageSize());
		model.put("indexPage", page_list.getPageNo());
		model.put("skey", skey);
		model.put("com", com);
		model.put("baseDatadictionary_list", page_list.getDataList());
		 return "system/Type3";
	}
	@RequestMapping("add")
	public String add(Datadictionary baseDatadictionary,String parentcode,Map<String,Object>model){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "system/data/show.html");
		if(!com.getHisSelect()){
			return "error";
		}
		baseDatadictionary.setFuid(UUIDCreater.getUUID());
		String code1=baseDatadictionary.getCode();
		if(parentcode!=null&&!parentcode.equals("null")){
			code1=parentcode+baseDatadictionary.getCode();
		}
		baseDatadictionary.setCode(code1);
		datadictionaryService.insert(baseDatadictionary);
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext servletContext = webApplicationContext.getServletContext(); 
		CompetenceManager a=new CompetenceManager();
		a.RefreshDatadictionary(servletContext);
		model.put("parentcode", parentcode);
		if(parentcode!=null&&parentcode.length()==3){
			return "redirect:/system/data/showChild2.html"; 
		}else if(parentcode!=null&&parentcode.length()==6){
			return "redirect:/system/data/showChild3.html"; 
		}else{
			return "redirect:/system/data/show.html"; 
		}
	}
	@RequestMapping("addChild")
	public String addChild(Datadictionary baseDatadictionary,String parentcode,Map<String,Object>model){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "system/data/show.html");
		if(!com.getHisSelect()){
			return "error";
		}
		if(parentcode!=null&&parentcode.length()>0){
			baseDatadictionary.setFuid(UUIDCreater.getUUID());
			String code1=parentcode+baseDatadictionary.getCode(); 
			baseDatadictionary.setCode(code1);
			datadictionaryService.insert(baseDatadictionary);
		}
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext servletContext = webApplicationContext.getServletContext(); 
		CompetenceManager a=new CompetenceManager();
		a.RefreshDatadictionary(servletContext);
		model.put("parentcode", parentcode);
		if(parentcode!=null&&parentcode.length()==3){
			return "redirect:/system/data/showChild2.html"; 
		}else if(parentcode!=null&&parentcode.length()==6){
			return "redirect:/system/data/showChild3.html"; 
		}else{
			return "redirect:/system/data/show.html"; 
		}
	}
	@RequestMapping("update")
	public String update(String id,String parentcode,Datadictionary baseDatadictionary,Map<String,Object>model){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "system/data/show.html");
		if(!com.getHisSelect()){
			return "error";
		}
		Datadictionary baseDatadictionary_new=datadictionaryService.findById(id);
		if(parentcode!=null){
			String code1=parentcode+baseDatadictionary.getCode();
			baseDatadictionary_new.setCode(code1);
		}else{
			baseDatadictionary_new.setCode(baseDatadictionary.getCode());
		}
		baseDatadictionary_new.setFullname(baseDatadictionary.getFullname());
		baseDatadictionary_new.setSequence(baseDatadictionary.getSequence());
		baseDatadictionary_new.setDescription(baseDatadictionary.getDescription());
		baseDatadictionary_new.setModifydate(new Date());
		datadictionaryService.update(baseDatadictionary_new);
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext servletContext = webApplicationContext.getServletContext(); 
		CompetenceManager a=new CompetenceManager();
		a.RefreshDatadictionary(servletContext);
		
		model.put("parentcode", parentcode);
		if(parentcode!=null&&parentcode.length()==3){
			return "redirect:/system/data/showChild2.html"; 
		}else if(parentcode!=null&&parentcode.length()==6){
			return "redirect:/system/data/showChild3.html"; 
		}else{
			return "redirect:/system/data/show.html"; 
		}
	}
	@RequestMapping("update_show")
	public String update_show(String id,Map<String,Object>model){
		Datadictionary baseDatadictionary=datadictionaryService.findById(id);
		model.put("baseDatadictionary", baseDatadictionary);
		return "system/TypeEditor";
	}	
	@RequestMapping("delete")
	public String delete(String id,String parentcode,Map<String,Object>model){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "system/data/show.html");
		if(!com.getHisSelect()){
			return "error";
		}
		datadictionaryService.deleteById(id);
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext servletContext = webApplicationContext.getServletContext(); 
		CompetenceManager a=new CompetenceManager();
		a.RefreshDatadictionary(servletContext);
		model.put("parentcode", parentcode);
		if(parentcode!=null&&parentcode.length()==3){
			return "redirect:/system/data/showChild2.html"; 
		}else if(parentcode!=null&&parentcode.length()==6){
			return "redirect:/system/data/showChild3.html"; 
		}else{
			return "redirect:/system/data/show.html"; 
		}
	}
	@RequestMapping("IsExist")
	@ResponseBody
	public String IsExist(String code) throws IOException{
		PrintWriter out = response.getWriter();
		Datadictionary datadictionary=new Datadictionary();
		datadictionary.setCode(code);
		int a=datadictionaryService.countByColum(datadictionary);
		if (a>0) {
			out.print("true");
		} else {
			out.print("false");
		}
		return null;
	}
	
}
