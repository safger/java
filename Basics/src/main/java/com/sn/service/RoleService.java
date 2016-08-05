package com.sn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sn.entity.Menu;
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

public interface  RoleService{ 
	
	public int deleteById(String id);

	public int insert(Role record);

	public int insertSelective(Role record);

	public Role findById(String fuid);

	public int updateSelective(Role record);

	public int update(Role record);
    
	public List<Role> selectByColum(Role record,String orderBy);
	
	public  int deleteByColum(Role record);
	
	public int deleteByByPrimaryKeys(List<String> ids);
	
	public List<Role>  selectLikeColum (String columName,String value);
	
	public PagedResult<Role> findByPage(Role record,Integer pageNo,Integer pageSize,String orderBy );
	
	public PagedResult<Role> findByPageCustom(Role record, Integer pageNo,Integer pageSize,String orderBy);
	
	public int countByColum(Role record);
	
	public List<Role> selectAll(String order);
	
	
}
