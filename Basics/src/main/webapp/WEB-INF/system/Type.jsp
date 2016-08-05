<%@ page language="java"
	import="java.util.*,com.sn.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<html>
	<head>
		<title>数据字典</title>
		<link type="text/css" rel="stylesheet" href="<%=basePath%>css/manage-style.css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/AutoSelect.js"></script>
		<script>
	 jQuery(document).ready(function($) {
		 $("#page").AutoPage({
					totalPage : '${totalPage}', // 总页数
					currentPage: '${indexPage}',//当前页
					pageSize:'${pageSize}',    //每页显示条数
					url:'<%=basePath%>system/data/show.html?code=${code}&skey=${skey}',
					showNum : 6  // 显示数字
					,show:1
				});
	 });
	 function serach(){
	 var skey=$("#mc").val();
	 var url="<%=basePath%>system/data/show.html?skey="+skey;
	 window.location.href=url;
	 }
	 function add(){
	 var url="<%=basePath %>system/data/add_show.html";
	 window.location.href=url;
	 }
	</script>
		<style>
 
</style>
	</head>
	<body>
		<div id="content">
		<div class="content-center" >
			<div class="manage-title">
				<div class="manage-title-l"
					style="width: 150px; border: 0px; padding: 0;">
					<img src="<%=basePath%>images/title_06.png" />
					<span class="hui-14-41-b">数据字典</span>
				</div>
				<div class="manage-title-r">
					<ul>
						<c:if test="${com.hisAdd==true }">
						<li class="btn">
							<input type="button" onclick="add()" class="common" value="新增一级" />
						</li>
						</c:if>
						<li class="btn">
							<input type="button" class="common" value="查询" onclick="serach()"/>
						</li>
						<li class="input-box" style="width: 240px">
						名称:<input type="text" class="input-text" onfocus="javascript:this.value=''" id="mc" value="${skey}" />
						</li>
					</ul>
				</div>
			</div>
			<div class="manage-content" style="margin-top: 20px">
				<table width="100%" cellSpacing="0" cellPadding="0" class="table">
       			 <tr class="title">
          				<td    >
						名称
						</td>
          				<td    >
						代码
						</td>
						<td >
						排序
						</td>
						<td align="center" class="noborder"  >
							操作
						</td> 
       				 </tr>
       				  <c:forEach items="${baseDatadictionary_list }" var="list" varStatus="stuts" >
       				 	 <c:if test="${stuts.count%2 == '0'}">
							 <tr class="row1">
						  </c:if>
						  <c:if test="${obj.count%2 != '0'}">
						 	 <tr class="row2">
						  </c:if>
						  <td>${ list.fullname}&nbsp;</td>
						<td    >${ list.code}&nbsp;
						</td>
						<td    >
						${ list.sequence} &nbsp;
						</td>
						<td   align="center" >
						<c:if test="${com.hisUpdate==true }">
							<a class="blue-12"  href="<%=basePath%>system/data/update_show.html?id=${list.fuid}&skey=${skey}">修改</a>
						</c:if>
						<c:if test="${com.hisDelete==true }">
							<a onclick="if(!confirm('确定删除！')){return false;}" class="blue-12"
								href="<%=basePath%>system/data/delete.html?id=${list.fuid}&skey=${skey}">删除</a>
						</c:if>
							<a href="<%=basePath%>system/data/showChild2.html?parentcode=${list.code }" class="blue-12" >编辑子目录</a>
							
						</td>
					</tr>
						  
					</c:forEach>	  
					
						
					 </table>
			</div>
			<div id="page"></div>
		</div>
	</div>	
			
	</body>
</html>