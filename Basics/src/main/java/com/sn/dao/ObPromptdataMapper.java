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



public interface ObPromptdataMapper {
	
	 int deleteByPrimaryKey(String fuid);

	    int insert(ObPromptdata record);

	    int insertSelective(ObPromptdata record);

	    ObPromptdata selectByPrimaryKey(String fuid);

	    int updateByPrimaryKeySelective(ObPromptdata record);

	    int updateByPrimaryKey(ObPromptdata record);
	    
	    List<ObPromptdata> selectByColum(ObPromptdata record);
	    
	    int deleteByColum(ObPromptdata record);
	 
	    int deleteByByPrimaryKeys(List<String> ids);
	    
	    List<ObPromptdata>  selectLikeColum (String columName,String value);
	    

}
