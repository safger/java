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


@Service("scopeService")
public class ScopeServiceImpl implements ScopeService{
	
	@Autowired
	private ScopeMapper scopeMapper;

	public ScopeMapper getScopeMapper() {
		return scopeMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.scopeMapper = scopeMapper;
	} 

	 

	@Override
	public int deleteById(String fuid) {
		// TODO Auto-generated method stub
		return scopeMapper.deleteByPrimaryKey(fuid);
	}

	@Override
	public int insert(Scope record) {
		// TODO Auto-generated method stub
		return scopeMapper.insert(record);
	}

	@Override
	public int insertSelective(Scope record) {
		// TODO Auto-generated method stub
		return scopeMapper.insertSelective(record);
	}

	@Override
	public Scope findById(String fuid) {
		// TODO Auto-generated method stub
		return scopeMapper.selectByPrimaryKey(fuid);
	}

	@Override
	public int updateSelective(Scope record) {
		// TODO Auto-generated method stub
		return scopeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int update(Scope record) {
		// TODO Auto-generated method stub
		return scopeMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public  int deleteByColum(Scope record){
		// TODO Auto-generated method stub
		return scopeMapper.deleteByColum(record);
	}
	
	@Override
	public List<Scope> selectByColum(Scope record) {
		// TODO Auto-generated method stub
		return scopeMapper.selectByColum(record);
	}
	
	@Override
	public int deleteByByPrimaryKeys(List<String> ids){
		// TODO Auto-generated method stub
		return scopeMapper.deleteByByPrimaryKeys(ids);
	}
	
	@Override
	public List<Scope>  selectLikeColum (String columName,String value){
		// TODO Auto-generated method stub
		return scopeMapper.selectLikeColum(columName,value);
	}
	
	@Override
	public PagedResult<Scope> findByPage(Scope record, Integer pageNo,
			Integer pageSize) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		return BeanUtil.toPagedResult(scopeMapper.selectByColum(record));
	}

	@Override
	public List<Scope> selectScope() {
		// TODO Auto-generated method stub
		return scopeMapper.selectScope();
	}

}
