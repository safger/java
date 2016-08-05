package com.sn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
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


@Service("menuRoleService")
public class MenuRoleServiceImpl implements MenuRoleService{
	
	@Autowired
	private MenuRoleMapper menuRoleMapper;

	public MenuRoleMapper getMenuRoleMapper() {
		return menuRoleMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.menuRoleMapper = menuRoleMapper;
	} 

	 

	@Override
	public int deleteById(String fuid) {
		// TODO Auto-generated method stub
		return menuRoleMapper.deleteByPrimaryKey(fuid);
	}

	@Override
	public int insert(MenuRole record) {
		// TODO Auto-generated method stub
		return menuRoleMapper.insert(record);
	}

	@Override
	public int insertSelective(MenuRole record) {
		// TODO Auto-generated method stub
		return menuRoleMapper.insertSelective(record);
	}

	@Override
	public MenuRole findById(String fuid) {
		// TODO Auto-generated method stub
		return menuRoleMapper.selectByPrimaryKey(fuid);
	}

	@Override
	public int updateSelective(MenuRole record) {
		// TODO Auto-generated method stub
		return menuRoleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int update(MenuRole record) {
		// TODO Auto-generated method stub
		return menuRoleMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public  int deleteByColum(MenuRole record){
		// TODO Auto-generated method stub
		return menuRoleMapper.deleteByColum(record);
	}
	
	@Override
	public List<MenuRole> selectByColum(MenuRole record) {
		// TODO Auto-generated method stub
		return menuRoleMapper.selectByColum(record);
	}
	
	@Override
	public int deleteByByPrimaryKeys(List<String> ids){
		// TODO Auto-generated method stub
		return menuRoleMapper.deleteByByPrimaryKeys(ids);
	}
	
	@Override
	public List<MenuRole>  selectLikeColum (String columName,String value){
		// TODO Auto-generated method stub
		return menuRoleMapper.selectLikeColum(columName,value);
	}
	
	@Override
	public PagedResult<MenuRole> findByPage(MenuRole record, Integer pageNo,
			Integer pageSize,String orderBy) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.orderBy(orderBy);
		PageHelper.startPage(pageNo, pageSize);
		return BeanUtil.toPagedResult(menuRoleMapper.selectByColum(record));
	}

	@Override
	public PagedResult<MenuRole> findByPageCustom(MenuRole record, Integer pageNo,Integer pageSize,String orderBy) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.orderBy(orderBy);
		PageHelper.startPage(pageNo, pageSize);
		return BeanUtil.toPagedResult(menuRoleMapper.selectByCustom(record));
	}
	@Override
	public int countByColum(MenuRole record) {
		// TODO Auto-generated method stub
		return menuRoleMapper.countByColum(record);
	}
@Override
	public List<MenuRole> selectMenuOp() {
		// TODO Auto-generated method stub
		return menuRoleMapper.selectMenuOp();
	}

}
