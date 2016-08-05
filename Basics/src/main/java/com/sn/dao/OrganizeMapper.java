package com.sn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sn.entity.Organize;



public interface OrganizeMapper {
	
	 int deleteByPrimaryKey(String fuid);

	    int insert(Organize record);

	    int insertSelective(Organize record);

	    Organize selectByPrimaryKey(String fuid);

	    int updateByPrimaryKeySelective(Organize record);

	    int updateByPrimaryKey(Organize record);
	    
	    List<Organize> selectByColum(Organize record);
	    
	    int deleteByColum(Organize record);
	 
	    int deleteByByPrimaryKeys(List<String> ids);
	    
	    List<Organize>  selectLikeColum (String columName,String value);
	    
	    List<Organize>  selectByCustom (Organize record);
	    
	    int countByColum(Organize record);

		  List<Organize> selectOrg(@Param("departmentid")String departmentid,@Param("layer")Integer layer);
		  
		  List<Organize> selectAll(@Param("order")String order );
}
