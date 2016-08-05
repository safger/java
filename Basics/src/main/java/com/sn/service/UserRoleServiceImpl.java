package com.sn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sn.util.BeanUtil;
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


@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService{
	
	@Autowired
	private UserRoleMapper userRoleMapper;

	public UserRoleMapper getUserRoleMapper() {
		return userRoleMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userRoleMapper = userRoleMapper;
	} 

	 

	@Override
	public int deleteById(String fuid) {
		// TODO Auto-generated method stub
		return userRoleMapper.deleteByPrimaryKey(fuid);
	}

	@Override
	public int insert(UserRole record) {
		// TODO Auto-generated method stub
		return userRoleMapper.insert(record);
	}

	@Override
	public int insertSelective(UserRole record) {
		// TODO Auto-generated method stub
		return userRoleMapper.insertSelective(record);
	}

	@Override
	public UserRole findById(String fuid) {
		// TODO Auto-generated method stub
		return userRoleMapper.selectByPrimaryKey(fuid);
	}

	@Override
	public int updateSelective(UserRole record) {
		// TODO Auto-generated method stub
		return userRoleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int update(UserRole record) {
		// TODO Auto-generated method stub
		return userRoleMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public  int deleteByColum(UserRole record){
		// TODO Auto-generated method stub
		return userRoleMapper.deleteByColum(record);
	}
	
	@Override
	public List<UserRole> selectByColum(UserRole record) {
		// TODO Auto-generated method stub
		return userRoleMapper.selectByColum(record);
	}
	
	@Override
	public int deleteByByPrimaryKeys(List<String> ids){
		// TODO Auto-generated method stub
		return userRoleMapper.deleteByByPrimaryKeys(ids);
	}
	
	@Override
	public List<UserRole>  selectLikeColum (String columName,String value){
		// TODO Auto-generated method stub
		return userRoleMapper.selectLikeColum(columName,value);
	}
	
	@Override
	public PagedResult<UserRole> findByPage(UserRole record, Integer pageNo,
			Integer pageSize) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		return BeanUtil.toPagedResult(userRoleMapper.selectByColum(record));
	}

	@Override
	public List<UserRole> selectView(String userid) {
		// TODO Auto-generated method stub
		return userRoleMapper.selectView(userid);
	}

}
