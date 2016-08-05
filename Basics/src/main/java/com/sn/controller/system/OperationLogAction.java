package com.sn.controller.system;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sn.entity.OperationLog;
import com.sn.service.OperationLogService;
import com.sn.util.PagedResult;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("system/operatinglog")
public class OperationLogAction {
	
	
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private OperationLogService baseOperationLogService;
	
	
	@RequestMapping("show")
	public String show(Integer indexPage,Integer pageSize,String skey,Map<String,Object>model){ 
		OperationLog operationLog=new OperationLog();
		if(skey!=null&&skey.length()>0){
			operationLog.setUsername(skey);
		} 
		PagedResult<OperationLog> list=baseOperationLogService.findByPageCustom(operationLog, indexPage, pageSize,  "CREATEDATE desc");
		Integer totalPage=(int) list.getTotal();
		model.put("totalPage", totalPage);
		model.put("indexPage", list.getPageNo());
		model.put("pageSize", list.getPageSize());
		model.put("skey", skey);
		model.put("baseOperationLog_list", list.getDataList());
		return "system/OperatingLog";
	}
	@RequestMapping("edit")
	public void edit(String data){
		baseOperationLogService.saveLog(data);
	}

	 
	

	 
}
