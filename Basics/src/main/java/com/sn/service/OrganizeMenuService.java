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

public interface  OrganizeMenuService{ 
	
	public int deleteById(String id);

	public int insert(OrganizeMenu record);

	public int insertSelective(OrganizeMenu record);

	public OrganizeMenu findById(String fuid);

	public int updateSelective(OrganizeMenu record);

	public int update(OrganizeMenu record);
    
	public List<OrganizeMenu> selectByColum(OrganizeMenu record);
	
	public  int deleteByColum(OrganizeMenu record);
	
	public int deleteByByPrimaryKeys(List<String> ids);
	
	public List<OrganizeMenu>  selectLikeColum (String columName,String value);
	
	public PagedResult<OrganizeMenu> findByPage(OrganizeMenu record,Integer pageNo,Integer pageSize );
	
	
}
