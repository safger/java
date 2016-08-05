package com.sn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sn.entity.User;
import com.sn.entity.UserRole;
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

public interface  UserRoleService{ 
	
	public int deleteById(String id);

	public int insert(UserRole record);

	public int insertSelective(UserRole record);

	public UserRole findById(String fuid);

	public int updateSelective(UserRole record);

	public int update(UserRole record);
    
	public List<UserRole> selectByColum(UserRole record);
	
	public  int deleteByColum(UserRole record);
	
	public int deleteByByPrimaryKeys(List<String> ids);
	
	public List<UserRole>  selectLikeColum (String columName,String value);
	
	public PagedResult<UserRole> findByPage(UserRole record,Integer pageNo,Integer pageSize );
	
	public List<UserRole>  selectView(String userid);
	
	
}
