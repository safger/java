package com.sn.service;

import java.util.List;

import com.sn.entity.Organize;
import com.sn.util.PagedResult;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

public interface OrganizeService {

	public int deleteById(String id);

	public int insert(Organize record);

	public int insertSelective(Organize record);

	public Organize findById(String fuid);

	public int updateSelective(Organize record);

	public int update(Organize record);

	public List<Organize> selectByColum(Organize record,String order);

	public int deleteByColum(Organize record);

	public int deleteByByPrimaryKeys(List<String> ids);

	public List<Organize> selectLikeColum(String columName, String value);

	public PagedResult<Organize> findByPage(Organize record, Integer pageNo,
			Integer pageSize, String orderBy);

	public PagedResult<Organize> findByPageCustom(Organize record,
			Integer pageNo, Integer pageSize, String orderBy);

	public int countByColum(Organize record);

	public List<Organize> selectOrg(String departmentid, Integer layer);
	
	public  List<Organize> selectAll(String order);
}
