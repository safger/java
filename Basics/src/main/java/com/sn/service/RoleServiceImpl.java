package com.sn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sn.entity.Menu;
import com.sn.entity.User;
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


@Service("roleService")
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleMapper roleMapper;

	public RoleMapper getRoleMapper() {
		return roleMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.roleMapper = roleMapper;
	} 

	 

	@Override
	public int deleteById(String fuid) {
		// TODO Auto-generated method stub
		return roleMapper.deleteByPrimaryKey(fuid);
	}

	@Override
	public int insert(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.insert(record);
	}

	@Override
	public int insertSelective(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.insertSelective(record);
	}

	@Override
	public Role findById(String fuid) {
		// TODO Auto-generated method stub
		return roleMapper.selectByPrimaryKey(fuid);
	}

	@Override
	public int updateSelective(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int update(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public  int deleteByColum(Role record){
		// TODO Auto-generated method stub
		return roleMapper.deleteByColum(record);
	}
	
	@Override
	public List<Role> selectByColum(Role record,String orderBy) {
		// TODO Auto-generated method stub
		PageHelper.orderBy(orderBy);
		return roleMapper.selectByColum(record);
	}
	
	@Override
	public int deleteByByPrimaryKeys(List<String> ids){
		// TODO Auto-generated method stub
		return roleMapper.deleteByByPrimaryKeys(ids);
	}
	
	@Override
	public List<Role>  selectLikeColum (String columName,String value){
		// TODO Auto-generated method stub
		return roleMapper.selectLikeColum(columName,value);
	}
	
	@Override
	public PagedResult<Role> findByPage(Role record, Integer pageNo,
			Integer pageSize,String orderBy) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.orderBy(orderBy);
		PageHelper.startPage(pageNo, pageSize);
		return BeanUtil.toPagedResult(roleMapper.selectByColum(record));
	}

	@Override
	public PagedResult<Role> findByPageCustom(Role record, Integer pageNo,Integer pageSize,String orderBy) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.orderBy(orderBy);
		PageHelper.startPage(pageNo, pageSize);
		return BeanUtil.toPagedResult(roleMapper.selectByCustom(record));
	}
	@Override
	public int countByColum(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.countByColum(record);
	}
	@Override
	public List<Role> selectAll(String order){
		return roleMapper.selectAll(order);
	}

	
}
