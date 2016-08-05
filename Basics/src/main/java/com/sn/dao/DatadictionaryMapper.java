package com.sn.dao;

import java.util.List;

import com.sn.entity.Datadictionary;
/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */



public interface DatadictionaryMapper {
	
	 int deleteByPrimaryKey(String fuid);

	    int insert(Datadictionary record);

	    int insertSelective(Datadictionary record);

	    Datadictionary selectByPrimaryKey(String fuid);

	    int updateByPrimaryKeySelective(Datadictionary record);

	    int updateByPrimaryKey(Datadictionary record);
	    
	    List<Datadictionary> selectByColum(Datadictionary record);
	    
	    int deleteByColum(Datadictionary record);
	 
	    int deleteByByPrimaryKeys(List<String> ids);
	    
	    List<Datadictionary>  selectLikeColum (String columName,String value);
	    
	    List<Datadictionary>  selectByCustom (Datadictionary record);
	    
	    int countByColum(Datadictionary record);
	    
	    List<Datadictionary> selectAll(String order);

}
