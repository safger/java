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

@Service("menuService")
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuMapper menuMapper;

	public MenuMapper getMenuMapper() {
		return menuMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.menuMapper = menuMapper;
	} 

	 

	@Override
	public int deleteById(String fuid) {
		// TODO Auto-generated method stub
		return menuMapper.deleteByPrimaryKey(fuid);
	}

	@Override
	public int insert(Menu record) {
		// TODO Auto-generated method stub
		return menuMapper.insert(record);
	}

	@Override
	public int insertSelective(Menu record) {
		// TODO Auto-generated method stub
		return menuMapper.insertSelective(record);
	}

	@Override
	public Menu findById(String fuid) {
		// TODO Auto-generated method stub
		return menuMapper.selectByPrimaryKey(fuid);
	}

	@Override
	public int updateSelective(Menu record) {
		// TODO Auto-generated method stub
		return menuMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int update(Menu record) {
		// TODO Auto-generated method stub
		return menuMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public  int deleteByColum(Menu record){
		// TODO Auto-generated method stub
		return menuMapper.deleteByColum(record);
	}
	
	@Override
	public List<Menu> selectByColum(Menu record) {
		// TODO Auto-generated method stub
		return menuMapper.selectByColum(record);
	}
	
	@Override
	public int deleteByByPrimaryKeys(List<String> ids){
		// TODO Auto-generated method stub
		return menuMapper.deleteByByPrimaryKeys(ids);
	}
	
	@Override
	public List<Menu>  selectLikeColum (String columName,String value){
		// TODO Auto-generated method stub
		return menuMapper.selectLikeColum(columName,value);
	}
	
	@Override
	public PagedResult<Menu> findByPage(Menu record, Integer pageNo,
			Integer pageSize) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		return BeanUtil.toPagedResult(menuMapper.selectByColum(record));
	}

	@Override
	public List<Menu> selectView(String userid) {
		// TODO Auto-generated method stub
		return menuMapper.selectMenuView(userid);
	}

	@Override
	public List<Menu> selectAll(String order) {
		// TODO Auto-generated method stub
		PageHelper.orderBy(order);
		return menuMapper.selectAll(order);
	}

	@Override
	public int countByColum(Menu record) {
		// TODO Auto-generated method stub
		return menuMapper.countByColum(record);
	}

	@Override
	public List<Menu> selectMenuOrg(String organizeId) {
		// TODO Auto-generated method stub
		return menuMapper.selectMenuOrg(organizeId);
	}

	@Override
	public List<Menu> selectMop(String roleid) {
		// TODO Auto-generated method stub
		return menuMapper.selectMop(roleid);
	}

}
