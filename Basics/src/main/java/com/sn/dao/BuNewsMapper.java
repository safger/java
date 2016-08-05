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



public interface BuNewsMapper {
	
	 int deleteByPrimaryKey(String fuid);

	    int insert(BuNews record);

	    int insertSelective(BuNews record);

	    BuNews selectByPrimaryKey(String fuid);

	    int updateByPrimaryKeySelective(BuNews record);

	    int updateByPrimaryKey(BuNews record);
	    
	    List<BuNews> selectByColum(BuNews record);
	    
	    int deleteByColum(BuNews record);
	 
	    int deleteByByPrimaryKeys(List<String> ids);
	    
	    List<BuNews>  selectLikeColum (String columName,String value);
	    

}
