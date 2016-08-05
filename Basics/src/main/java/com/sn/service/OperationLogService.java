package com.sn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sn.entity.User;
import com.sn.util.PagedResult;




import java.util.*;

import com.sn.entity.*;
import com.sn.dao.*;
import com.sn.service.*;  

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

public interface  OperationLogService{ 
	
	public int deleteById(String id);

	public int insert(OperationLog record);

	public int insertSelective(OperationLog record);

	public OperationLog findById(String fuid);

	public int updateSelective(OperationLog record);

	public int update(OperationLog record);
    
	public List<OperationLog> selectByColum(OperationLog record);
	
	public  int deleteByColum(OperationLog record);
	
	public int deleteByByPrimaryKeys(List<String> ids);
	
	public List<OperationLog>  selectLikeColum (String columName,String value);
	
	public PagedResult<OperationLog> findByPage(OperationLog record,Integer pageNo,Integer pageSize,String orderBy );
	
	public PagedResult<OperationLog> findByPageCustom(OperationLog record, Integer pageNo,Integer pageSize,String orderBy);
	
	public int countByColum(OperationLog record);
	
	public void saveLog(String data);
	
}
