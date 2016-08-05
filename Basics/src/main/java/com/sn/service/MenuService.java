package com.sn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sn.entity.Menu;
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

public interface  MenuService{ 
	
	public int deleteById(String id);

	public int insert(Menu record);

	public int insertSelective(Menu record);

	public Menu findById(String fuid);

	public int updateSelective(Menu record);

	public int update(Menu record);
    
	public List<Menu> selectByColum(Menu record);
	
	public  int deleteByColum(Menu record);
	
	public int deleteByByPrimaryKeys(List<String> ids);
	
	public List<Menu>  selectLikeColum (String columName,String value);
	
	public PagedResult<Menu> findByPage(Menu record,Integer pageNo,Integer pageSize );
	
	public List<Menu> selectView(String userid);
	
	public List<Menu> selectAll(String order);
	
	public int countByColum(Menu record);
	
	public List<Menu> selectMenuOrg(String organizeId);
	
	public List<Menu> selectMop(String roleid);
}
