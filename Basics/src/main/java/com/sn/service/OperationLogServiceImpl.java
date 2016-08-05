package com.sn.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageHelper;
import com.sn.common.UUIDCreater;
import com.sn.dao.OperationLogMapper;
import com.sn.dao.UserMapper;
import com.sn.entity.OperationLog;
import com.sn.util.BeanUtil;
import com.sn.util.PagedResult;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


@Service("operationLogService")
public class OperationLogServiceImpl implements OperationLogService{
	@Autowired
	private OperationLogMapper operationLogMapper;

	public OperationLogMapper getOperationLogMapper() {
		return operationLogMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.operationLogMapper = operationLogMapper;
	} 

	 

	@Override
	public int deleteById(String fuid) {
		// TODO Auto-generated method stub
		return operationLogMapper.deleteByPrimaryKey(fuid);
	}

	@Override
	public int insert(OperationLog record) {
		// TODO Auto-generated method stub
		return operationLogMapper.insert(record);
	}

	@Override
	public int insertSelective(OperationLog record) {
		// TODO Auto-generated method stub
		return operationLogMapper.insertSelective(record);
	}

	@Override
	public OperationLog findById(String fuid) {
		// TODO Auto-generated method stub
		return operationLogMapper.selectByPrimaryKey(fuid);
	}

	@Override
	public int updateSelective(OperationLog record) {
		// TODO Auto-generated method stub
		return operationLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int update(OperationLog record) {
		// TODO Auto-generated method stub
		return operationLogMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public  int deleteByColum(OperationLog record){
		// TODO Auto-generated method stub
		return operationLogMapper.deleteByColum(record);
	}
	
	@Override
	public List<OperationLog> selectByColum(OperationLog record) {
		// TODO Auto-generated method stub
		return operationLogMapper.selectByColum(record);
	}
	
	@Override
	public int deleteByByPrimaryKeys(List<String> ids){
		// TODO Auto-generated method stub
		return operationLogMapper.deleteByByPrimaryKeys(ids);
	}
	
	@Override
	public List<OperationLog>  selectLikeColum (String columName,String value){
		// TODO Auto-generated method stub
		return operationLogMapper.selectLikeColum(columName,value);
	}
	
	@Override
	public PagedResult<OperationLog> findByPage(OperationLog record, Integer pageNo,
			Integer pageSize,String orderBy) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.orderBy(orderBy);
		PageHelper.startPage(pageNo, pageSize);
		return BeanUtil.toPagedResult(operationLogMapper.selectByColum(record));
	}

	@Override
	public PagedResult<OperationLog> findByPageCustom(OperationLog record, Integer pageNo,Integer pageSize,String orderBy) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.orderBy(orderBy);
		PageHelper.startPage(pageNo, pageSize);
		return BeanUtil.toPagedResult(operationLogMapper.selectByCustom(record));
	}
	@Override
	public int countByColum(OperationLog record) {
		// TODO Auto-generated method stub
		return operationLogMapper.countByColum(record);
	}
	@Override
	public void saveLog(String data) {
		// TODO Auto-generated method stub
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		request.getSession().setAttribute("sessionMessage", "im'sessionMessage!");
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		OperationLog baseOperationLog=new OperationLog();
		baseOperationLog.setFuid(UUIDCreater.getUUID());
		baseOperationLog.setUsername(username);
		baseOperationLog.setUserid(userid);
		baseOperationLog.setOperating(data);
		baseOperationLog.setCreatedate(new Date());
		operationLogMapper.insert(baseOperationLog);
	}


	public void setOperationLogMapper(OperationLogMapper operationLogMapper) {
		this.operationLogMapper = operationLogMapper;
	}
}
