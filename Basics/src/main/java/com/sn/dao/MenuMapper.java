package com.sn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sn.entity.Menu;
/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */



public interface MenuMapper {
	
	 int deleteByPrimaryKey(String fuid);

	    int insert(Menu record);

	    int insertSelective(Menu record);

	    Menu selectByPrimaryKey(String fuid);

	    int updateByPrimaryKeySelective(Menu record);

	    int updateByPrimaryKey(Menu record);
	    
	    List<Menu> selectByColum(Menu record);
	    
	    int deleteByColum(Menu record);
	 
	    int deleteByByPrimaryKeys(List<String> ids);
	    
	    List<Menu>  selectLikeColum (String columName,String value);
	    
	    List<Menu> selectMenuView(String  userid);
	   
	    List<Menu> selectAll(String order);
	    
	    int countByColum(Menu record);
	    
	    List<Menu> selectMenuOrg(String organizeId);
	    
	    List<Menu> selectMop(String roleid);
}
