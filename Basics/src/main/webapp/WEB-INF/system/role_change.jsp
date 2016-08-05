﻿
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<title>后台维护信息</title>
		<link type="text/css" rel="stylesheet" href="<%=basePath%>css/manage-style.css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/manage.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/AutoSelect.js"></script>
		<script>
	 function change(){
	 	var val=$("input[name=uch]:checked");
	 	 if(val.length==0){
	 	 	alert("请先选择人员");
	 	 	return;
	 	 }else{
	 	 	var roleid="";
	 	 	 val.each(function (i){
	 	 		 roleid+=val.eq(i).val()+",";
	 	 	 });
	 	 	 roleid=roleid.length>0?roleid.substring(0,roleid.length-1):roleid;
		 	 $("#roleid", window.parent.document).val(roleid);
	 		 window.parent.Allocation();
		 }
	 }
	 
</script>
		<style>
</style>
	</head>
	<body>
	<div id="content">
		<div>
			<div class="manage-content">
				<table  cellSpacing="0" cellPadding="0" class="table" style="min-width:376px;border-right: 0px"  >
					
					 <c:forEach items="${baseRole_list }" var="list" varStatus="stuts" >
       				 	 <c:if test="${stuts.count%2 == '0'}">
							 <tr class="row1">
						  </c:if>
						  <c:if test="${obj.count%2 != '0'}">
						 	 <tr class="row2">
						  </c:if>
						  <td > <input   type="checkbox" name="uch" value="${list.fuid }"/>${list.realname }&nbsp;
						</td>
					</tr>
					</c:forEach>  
					<tr>
					<td  align="center">
					<input  type="button" onclick="change()" id="saveButton" value="分配" class="input-page-btn" />
					</td>
					</tr>
					 </table>
			</div>
		</div>
	</div>
	</body>

</html>