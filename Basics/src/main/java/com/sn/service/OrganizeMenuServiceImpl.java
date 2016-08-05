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


@Service("organizeMenuService")
public class OrganizeMenuServiceImpl implements OrganizeMenuService{
	
	@Autowired
	private OrganizeMenuMapper organizeMenuMapper;

	public OrganizeMenuMapper getOrganizeMenuMapper() {
		return organizeMenuMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.organizeMenuMapper = organizeMenuMapper;
	} 

	 

	@Override
	public int deleteById(String fuid) {
		// TODO Auto-generated method stub
		return organizeMenuMapper.deleteByPrimaryKey(fuid);
	}

	@Override
	public int insert(OrganizeMenu record) {
		// TODO Auto-generated method stub
		return organizeMenuMapper.insert(record);
	}

	@Override
	public int insertSelective(OrganizeMenu record) {
		// TODO Auto-generated method stub
		return organizeMenuMapper.insertSelective(record);
	}

	@Override
	public OrganizeMenu findById(String fuid) {
		// TODO Auto-generated method stub
		return organizeMenuMapper.selectByPrimaryKey(fuid);
	}

	@Override
	public int updateSelective(OrganizeMenu record) {
		// TODO Auto-generated method stub
		return organizeMenuMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int update(OrganizeMenu record) {
		// TODO Auto-generated method stub
		return organizeMenuMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public  int deleteByColum(OrganizeMenu record){
		// TODO Auto-generated method stub
		return organizeMenuMapper.deleteByColum(record);
	}
	
	@Override
	public List<OrganizeMenu> selectByColum(OrganizeMenu record) {
		// TODO Auto-generated method stub
		return organizeMenuMapper.selectByColum(record);
	}
	
	@Override
	public int deleteByByPrimaryKeys(List<String> ids){
		// TODO Auto-generated method stub
		return organizeMenuMapper.deleteByByPrimaryKeys(ids);
	}
	
	@Override
	public List<OrganizeMenu>  selectLikeColum (String columName,String value){
		// TODO Auto-generated method stub
		return organizeMenuMapper.selectLikeColum(columName,value);
	}
	
	@Override
	public PagedResult<OrganizeMenu> findByPage(OrganizeMenu record, Integer pageNo,
			Integer pageSize) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		return BeanUtil.toPagedResult(organizeMenuMapper.selectByColum(record));
	}

}
