package com.sn.dao;

import java.util.*;
  
 

import com.sn.entity.*;
import com.sn.dao.*;
import com.sn.service.*;  

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


import java.util.List; 



public interface OperationLogMapper {
	
	 int deleteByPrimaryKey(String fuid);

	    int insert(OperationLog record);

	    int insertSelective(OperationLog record);

	    OperationLog selectByPrimaryKey(String fuid);

	    int updateByPrimaryKeySelective(OperationLog record);

	    int updateByPrimaryKey(OperationLog record);
	    
	    List<OperationLog> selectByColum(OperationLog record);
	    
	    int deleteByColum(OperationLog record);
	 
	    int deleteByByPrimaryKeys(List<String> ids);
	    
	    List<OperationLog>  selectLikeColum (String columName,String value);
	    
	    List<OperationLog>  selectByCustom (OperationLog record);
	    
	    int countByColum(OperationLog record);

}
