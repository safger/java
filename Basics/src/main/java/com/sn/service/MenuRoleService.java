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

public interface  MenuRoleService{ 
	
	public int deleteById(String id);

	public int insert(MenuRole record);

	public int insertSelective(MenuRole record);

	public MenuRole findById(String fuid);

	public int updateSelective(MenuRole record);

	public int update(MenuRole record);
    
	public List<MenuRole> selectByColum(MenuRole record);
	
	public  int deleteByColum(MenuRole record);
	
	public int deleteByByPrimaryKeys(List<String> ids);
	
	public List<MenuRole>  selectLikeColum (String columName,String value);
	
	public PagedResult<MenuRole> findByPage(MenuRole record,Integer pageNo,Integer pageSize,String orderBy );
	
	public PagedResult<MenuRole> findByPageCustom(MenuRole record, Integer pageNo,Integer pageSize,String orderBy);
	
	public int countByColum(MenuRole record);

public  List<MenuRole>  selectMenuOp();
	
}
