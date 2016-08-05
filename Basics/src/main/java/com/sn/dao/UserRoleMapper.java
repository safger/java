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



public interface UserRoleMapper {
	
	 int deleteByPrimaryKey(String fuid);

	    int insert(UserRole record);

	    int insertSelective(UserRole record);

	    UserRole selectByPrimaryKey(String fuid);

	    int updateByPrimaryKeySelective(UserRole record);

	    int updateByPrimaryKey(UserRole record);
	    
	    List<UserRole> selectByColum(UserRole record);
	    
	    int deleteByColum(UserRole record);
	 
	    int deleteByByPrimaryKeys(List<String> ids);
	    
	    List<UserRole>  selectLikeColum (String columName,String value);
	    
	    List<UserRole>  selectView(String userid);

}
