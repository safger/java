package com.sn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sn.entity.Operating;
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


@Service("operatingService")
public class OperatingServiceImpl implements OperatingService{
	
	@Autowired
	private OperatingMapper operatingMapper;

	public OperatingMapper getOperatingMapper() {
		return operatingMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.operatingMapper = operatingMapper;
	} 

	 

	@Override
	public int deleteById(String fuid) {
		// TODO Auto-generated method stub
		return operatingMapper.deleteByPrimaryKey(fuid);
	}

	@Override
	public int insert(Operating record) {
		// TODO Auto-generated method stub
		return operatingMapper.insert(record);
	}

	@Override
	public int insertSelective(Operating record) {
		// TODO Auto-generated method stub
		return operatingMapper.insertSelective(record);
	}

	@Override
	public Operating findById(String fuid) {
		// TODO Auto-generated method stub
		return operatingMapper.selectByPrimaryKey(fuid);
	}

	@Override
	public int updateSelective(Operating record) {
		// TODO Auto-generated method stub
		return operatingMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int update(Operating record) {
		// TODO Auto-generated method stub
		return operatingMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public  int deleteByColum(Operating record){
		// TODO Auto-generated method stub
		return operatingMapper.deleteByColum(record);
	}
	
	@Override
	public List<Operating> selectByColum(Operating record) {
		// TODO Auto-generated method stub
		return operatingMapper.selectByColum(record);
	}
	
	@Override
	public int deleteByByPrimaryKeys(List<String> ids){
		// TODO Auto-generated method stub
		return operatingMapper.deleteByByPrimaryKeys(ids);
	}
	
	@Override
	public List<Operating>  selectLikeColum (String columName,String value){
		// TODO Auto-generated method stub
		return operatingMapper.selectLikeColum(columName,value);
	}
	
	@Override
	public PagedResult<Operating> findByPage(Operating record, Integer pageNo,
			Integer pageSize,String orderBy) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.orderBy(orderBy);
		PageHelper.startPage(pageNo, pageSize);
		return BeanUtil.toPagedResult(operatingMapper.selectByColum(record));
	}

	@Override
	public PagedResult<Operating> findByPageCustom(Operating record, Integer pageNo,Integer pageSize,String orderBy) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.orderBy(orderBy);
		PageHelper.startPage(pageNo, pageSize);
		return BeanUtil.toPagedResult(operatingMapper.selectByCustom(record));
	}
	@Override
	public int countByColum(Operating record) {
		// TODO Auto-generated method stub
		return operatingMapper.countByColum(record);
	}

	@Override
	public List<Operating> selectOp(String OrganizeId, String menu_id) {
		// TODO Auto-generated method stub
		return operatingMapper.selectOp(OrganizeId, menu_id);
	}
	@Override
	public  List<Operating>  selectOperationMenu(Operating record){
		return operatingMapper.selectOperationMenu(record); 
	}
}
