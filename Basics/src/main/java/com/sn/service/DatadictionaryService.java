package com.sn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sn.entity.Datadictionary;
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

public interface  DatadictionaryService{ 
	
	public int deleteById(String id);

	public int insert(Datadictionary record);

	public int insertSelective(Datadictionary record);

	public Datadictionary findById(String fuid);

	public int updateSelective(Datadictionary record);

	public int update(Datadictionary record);
    
	public List<Datadictionary> selectByColum(Datadictionary record,String orderBy);
	
	public  int deleteByColum(Datadictionary record);
	
	public int deleteByByPrimaryKeys(List<String> ids);
	
	public List<Datadictionary>  selectLikeColum (String columName,String value);
	
	public PagedResult<Datadictionary> findByPage(Datadictionary record,Integer indexPage,Integer pageSize,String orderBy );
	
	public PagedResult<Datadictionary> findByPageCustom(Datadictionary record, Integer indexPage,Integer pageSize,String orderBy);
	
	public int countByColum(Datadictionary record);
	
	public List<Datadictionary> selectAll(String order);
	
}
