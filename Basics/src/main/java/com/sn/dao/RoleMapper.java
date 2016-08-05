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



public interface RoleMapper {
	
	 int deleteByPrimaryKey(String fuid);

	    int insert(Role record);

	    int insertSelective(Role record);

	    Role selectByPrimaryKey(String fuid);

	    int updateByPrimaryKeySelective(Role record);

	    int updateByPrimaryKey(Role record);
	    
	    List<Role> selectByColum(Role record);
	    
	    int deleteByColum(Role record);
	 
	    int deleteByByPrimaryKeys(List<String> ids);
	    
	    List<Role>  selectLikeColum (String columName,String value);
	    
	    List<Role>  selectByCustom (Role record);
	    
	    int countByColum(Role record);
	    
	    List<Role> selectAll(String order);

}
