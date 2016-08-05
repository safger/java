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


@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	} 

	 

	@Override
	public int deleteById(String fuid) {
		// TODO Auto-generated method stub
		return userMapper.deleteByPrimaryKey(fuid);
	}

	@Override
	public int insert(User record) {
		// TODO Auto-generated method stub
		return userMapper.insert(record);
	}

	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return userMapper.insertSelective(record);
	}

	@Override
	public User findById(String fuid) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(fuid);
	}

	@Override
	public int updateSelective(User record) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int update(User record) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public  int deleteByColum(User record){
		// TODO Auto-generated method stub
		return userMapper.deleteByColum(record);
	}
	
	@Override
	public List<User> selectByColum(User record) {
		// TODO Auto-generated method stub
		return userMapper.selectByColum(record);
	}
	
	@Override
	public int deleteByByPrimaryKeys(List<String> ids){
		// TODO Auto-generated method stub
		return userMapper.deleteByByPrimaryKeys(ids);
	}
	
	@Override
	public List<User>  selectLikeColum (String columName,String value){
		// TODO Auto-generated method stub
		return userMapper.selectLikeColum(columName,value);
	}
	
	@Override
	public PagedResult<User> findByPage(User record, Integer pageNo,
			Integer pageSize) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		return BeanUtil.toPagedResult(userMapper.selectByColum(record));
	}

	@Override
	public PagedResult<User> findByPageCustom(User record, Integer pageNo,Integer pageSize) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		return BeanUtil.toPagedResult(userMapper.selectByCustom(record));
	}

	@Override
	public int countByColum(User record) {
		// TODO Auto-generated method stub
		return userMapper.countByColum(record);
	}

}
