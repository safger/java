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


@Service("datadictionaryService")
public class DatadictionaryServiceImpl implements DatadictionaryService{
	
	@Autowired
	private DatadictionaryMapper datadictionaryMapper;

	public DatadictionaryMapper getDatadictionaryMapper() {
		return datadictionaryMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.datadictionaryMapper = datadictionaryMapper;
	} 

	 

	@Override
	public int deleteById(String fuid) {
		// TODO Auto-generated method stub
		return datadictionaryMapper.deleteByPrimaryKey(fuid);
	}

	@Override
	public int insert(Datadictionary record) {
		// TODO Auto-generated method stub
		return datadictionaryMapper.insert(record);
	}

	@Override
	public int insertSelective(Datadictionary record) {
		// TODO Auto-generated method stub
		return datadictionaryMapper.insertSelective(record);
	}

	@Override
	public Datadictionary findById(String fuid) {
		// TODO Auto-generated method stub
		return datadictionaryMapper.selectByPrimaryKey(fuid);
	}

	@Override
	public int updateSelective(Datadictionary record) {
		// TODO Auto-generated method stub
		return datadictionaryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int update(Datadictionary record) {
		// TODO Auto-generated method stub
		return datadictionaryMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public  int deleteByColum(Datadictionary record){
		// TODO Auto-generated method stub
		return datadictionaryMapper.deleteByColum(record);
	}
	
	@Override
	public List<Datadictionary> selectByColum(Datadictionary record,String orderBy) {
		// TODO Auto-generated method stub
		PageHelper.orderBy(orderBy);
		return datadictionaryMapper.selectByColum(record);
	}
	
	@Override
	public int deleteByByPrimaryKeys(List<String> ids){
		// TODO Auto-generated method stub
		return datadictionaryMapper.deleteByByPrimaryKeys(ids);
	}
	
	@Override
	public List<Datadictionary>  selectLikeColum (String columName,String value){
		// TODO Auto-generated method stub
		return datadictionaryMapper.selectLikeColum(columName,value);
	}
	
	@Override
	public PagedResult<Datadictionary> findByPage(Datadictionary record, Integer pageNo,
			Integer pageSize,String orderBy) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.orderBy(orderBy);
		PageHelper.startPage(pageNo, pageSize);
		return BeanUtil.toPagedResult(datadictionaryMapper.selectByColum(record));
	}

	@Override
	public PagedResult<Datadictionary> findByPageCustom(Datadictionary record, Integer pageNo,Integer pageSize,String orderBy) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.orderBy(orderBy);
		PageHelper.startPage(pageNo, pageSize);
		return BeanUtil.toPagedResult(datadictionaryMapper.selectByCustom(record));
	}
	@Override
	public int countByColum(Datadictionary record) {
		// TODO Auto-generated method stub
		return datadictionaryMapper.countByColum(record);
	}
	@Override
	public List<Datadictionary> selectAll(String order){
		return datadictionaryMapper.selectAll(order);
	}
}
