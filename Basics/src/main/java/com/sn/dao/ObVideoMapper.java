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



public interface ObVideoMapper {
	
	 int deleteByPrimaryKey(String fuid);

	    int insert(ObVideo record);

	    int insertSelective(ObVideo record);

	    ObVideo selectByPrimaryKey(String fuid);

	    int updateByPrimaryKeySelective(ObVideo record);

	    int updateByPrimaryKey(ObVideo record);
	    
	    List<ObVideo> selectByColum(ObVideo record);
	    
	    int deleteByColum(ObVideo record);
	 
	    int deleteByByPrimaryKeys(List<String> ids);
	    
	    List<ObVideo>  selectLikeColum (String columName,String value);
	    

}
