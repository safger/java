package com.sn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sn.dao.OrganizeMapper;
import com.sn.dao.UserMapper;
import com.sn.entity.Organize;
import com.sn.util.BeanUtil;
import com.sn.util.PagedResult;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

@Service("organizeService")
public class OrganizeServiceImpl implements OrganizeService {

	@Autowired
	private OrganizeMapper organizeMapper;

	public OrganizeMapper getOrganizeMapper() {
		return organizeMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.organizeMapper = organizeMapper;
	}

	@Override
	public int deleteById(String fuid) {
		// TODO Auto-generated method stub
		return organizeMapper.deleteByPrimaryKey(fuid);
	}

	@Override
	public int insert(Organize record) {
		// TODO Auto-generated method stub
		return organizeMapper.insert(record);
	}

	@Override
	public int insertSelective(Organize record) {
		// TODO Auto-generated method stub
		return organizeMapper.insertSelective(record);
	}

	@Override
	public Organize findById(String fuid) {
		// TODO Auto-generated method stub
		return organizeMapper.selectByPrimaryKey(fuid);
	}

	@Override
	public int updateSelective(Organize record) {
		// TODO Auto-generated method stub
		return organizeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int update(Organize record) {
		// TODO Auto-generated method stub
		return organizeMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByColum(Organize record) {
		// TODO Auto-generated method stub
		return organizeMapper.deleteByColum(record);
	}

	@Override
	public List<Organize> selectByColum(Organize record,String order) {
		// TODO Auto-generated method stub
		PageHelper.orderBy(order);
		return organizeMapper.selectByColum(record);
	}

	@Override
	public int deleteByByPrimaryKeys(List<String> ids) {
		// TODO Auto-generated method stub
		return organizeMapper.deleteByByPrimaryKeys(ids);
	}

	@Override
	public List<Organize> selectLikeColum(String columName, String value) {
		// TODO Auto-generated method stub
		return organizeMapper.selectLikeColum(columName, value);
	}

	@Override
	public PagedResult<Organize> findByPage(Organize record, Integer pageNo,
			Integer pageSize, String orderBy) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.orderBy(orderBy);
		PageHelper.startPage(pageNo, pageSize);
		return BeanUtil.toPagedResult(organizeMapper.selectByColum(record));
	}

	@Override
	public PagedResult<Organize> findByPageCustom(Organize record,
			Integer pageNo, Integer pageSize, String orderBy) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.orderBy(orderBy);
		PageHelper.startPage(pageNo, pageSize);
		return BeanUtil.toPagedResult(organizeMapper.selectByCustom(record));
	}

	@Override
	public int countByColum(Organize record) {
		// TODO Auto-generated method stub
		return organizeMapper.countByColum(record);
	}

	@Override
	public List<Organize> selectOrg(String departmentid, Integer layer) {
		// TODO Auto-generated method stub
		return organizeMapper.selectOrg(departmentid, layer);
	}

	@Override
	public List<Organize> selectAll(String order) {
		return organizeMapper.selectAll(order);
	}
}
