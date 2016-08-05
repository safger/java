package com.sn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sn.entity.Operating;
import com.sn.entity.User;
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

public interface  OperatingService{ 
	
	public int deleteById(String id);

	public int insert(Operating record);

	public int insertSelective(Operating record);

	public Operating findById(String fuid);

	public int updateSelective(Operating record);

	public int update(Operating record);
    
	public List<Operating> selectByColum(Operating record);
	
	public  int deleteByColum(Operating record);
	
	public int deleteByByPrimaryKeys(List<String> ids);
	
	public List<Operating>  selectLikeColum (String columName,String value);
	
	public PagedResult<Operating> findByPage(Operating record,Integer pageNo,Integer pageSize,String orderBy );
	
	public PagedResult<Operating> findByPageCustom(Operating record, Integer pageNo,Integer pageSize,String orderBy);
	
	public int countByColum(Operating record);
	
	public List<Operating> selectOp(String OrganizeId,String menu_id);
	
	public  List<Operating>  selectOperationMenu(Operating record);
}
