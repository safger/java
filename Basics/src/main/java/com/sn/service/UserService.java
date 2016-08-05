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

public interface  UserService{ 
	
	public int deleteById(String id);

	public int insert(User record);

	public int insertSelective(User record);

	public User findById(String fuid);

	public int updateSelective(User record);

	public int update(User record);
    
	public List<User> selectByColum(User record);
	
	public  int deleteByColum(User record);
	
	public int deleteByByPrimaryKeys(List<String> ids);
	
	public List<User>  selectLikeColum (String columName,String value);
	
	public PagedResult<User> findByPage(User record,Integer pageNo,Integer pageSize );
	
	public PagedResult<User> findByPageCustom(User record, Integer pageNo,Integer pageSize);
	
	public  int countByColum(User record);
}
