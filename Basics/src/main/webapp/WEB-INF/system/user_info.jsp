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
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/demo.css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/manage.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/AutoSelect.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/eayui/jquery.easyui.min.js"></script>
		<script>
	 jQuery(document).ready(function($) {
			$("#page").AutoPage({
					totalPage : '${totalPage}', // 总页数
					currentPage: '${indexPage}',//当前页
					pageSize:'${pageSize}',    //每页显示条数
					url:'<%=basePath%>system/usershow.html?skey=${skey}&OrganizeId=${OrganizeId}',
					showNum : 6  // 显示数字
					,show:1
				});
				$("#all").change(function() {
		            if (!$("#all").attr("checked")) {
		               $("input[name=uch]").removeAttr('checked');
		            }else if($("#all").attr("checked")){
		            	 $("input[name=uch]").attr('checked','checked');
		            }
		           }); 
	 });
	 function show(){
		 var val=$("input[name=uch]:checked");
		 	 if(val.length==0){
		 	 	alert("请先选择人员");
		 	 	return;
		 	 }else{
	 		 $('#dd').dialog({
		           	modal:true,
					title:"角色列表"
		           });
		       }    
	 }
	 function Allocation(){
	 	var val=$("input[name=uch]:checked");
	 	 if(val.length==0){
	 	 	alert("请先选择人员");
	 	 	return;
	 	 }else{
	 	 	var userid="";
	 	 	 val.each(function (i){
	 	 		 userid+=val.eq(i).val()+",";
	 	 	 });
	 	 	 userid=userid.length>0?userid.substring(0,userid.length-1):userid;
	 	 	$("#userid").val(userid);
	 	 	$("#form2").submit();
	 	 }
	 	
	 }
	 function serach(){
		 var skey=$("#mc").val();
		 var url="<%=basePath %>system/usershow?skey="+skey+"&OrganizeId=${OrganizeId}";
		 window.location.href=url;
	 }
	 function add(){
		 var url="<%=basePath%>system/addshow.html?OrganizeId=${OrganizeId}";
		 window.location.href=url;
	 }
	 
</script>
	</head>
	<body>
	<div id="content">
		<div class="content-center">
			<div class="manage-title">
				<div class="manage-title-l"
					style="width: 150px; border: 0px; padding: 0;">
					<img src="<%=basePath%>images/title_06.png" />
					<span class="hui-14-41-b">用户信息</span>
				</div>
				<div class="manage-title-r">
					<ul>
						<li class="btn">
							<input type="button" onclick="show()" class="common" value="分配角色" />
						</li>
						<li class="btn">
							<input type="button" onclick="add()" class="common" value="新增" />
						</li>
						<li class="btn">
							<input type="button" class="common" value="查询" onclick="serach()"/>
						</li>
						<li class="input-box" style="width: 200px">
						姓名：<input type="text" class="input-text" onfocus="javascript:this.value=''" id="mc" value="${skey}" />
						</li>
					</ul>
				</div>
			</div>
			<div class="manage-content">
				<table width="100%" cellSpacing="0" cellPadding="0" class="table">
       			 <tr class="title">
          				<td  style="width: 120px"   >
						<input type="checkbox" id="all" />姓名
						</td>
          				<td  style="width: 120px"   >
						登录帐号
						</td>
						<td    >
							性别
						</td>
						<td    >
							角色
						</td>
						<td    >
							备注
						</td>
						<td align="center" class="noborder"  >
							操作
						</td>
       				 </tr>
       				 <c:forEach items="${baseUser_list }" var="list" varStatus="stuts">
       				 	<c:if test="${stuts.count%2 == '0'}">
							 <tr class="row1">
						</c:if>
						<c:if test="${obj.count%2 != '0'}">
							<tr class="row2">
						</c:if>
						<td>
						<input   type="checkbox" name="uch" value=""/>${list.realname }&nbsp;
						</td>
						<td    >${list.username }&nbsp;
						</td>
						<td    >
							${list.sex }&nbsp;
						</td>
						<td  >
							${list.roleid }&nbsp;
						</td>
						<td  >
							${list.description }&nbsp;
						</td>
						<td   align="center" >
							<a class="blue-12"  href="<%=basePath %>system/update_show.html?id=${list.fuid }&skey=${skey}&OrganizeId=${OrganizeId}">修改</a>
							<a onclick="if(!confirm('确定删除！')){return false;}" class="blue-12"
								href="<%=basePath %>system/delete.html?id=${list.fuid }&skey=${skey}&OrganizeId=${OrganizeId}">删除</a>
						</td>
					</tr>
       				 </c:forEach>
					 </table>
			</div>
			<div id="page"></div>
		</div>
	</div>
	<form id="form2" method="post" action="<%=basePath %>system/AssignRoles.html?OrganizeId=${OrganizeId}">
			<input type="hidden" name="userid"  id="userid" />
			<input type="hidden" name="roleid"  id="roleid" />
	</form>
	<div style="display: none;">
		<div id="dd" data-options="iconCls:'icon-save'" style="position:relative;width:400px;height:400px;top: 5px">
			<iframe width="100%" height="100%" frameborder="0" src="<%=basePath%>system/role/showChange.html?OrganizeId=${OrganizeId}"></iframe>
		</div>
	</div>
	</body>

</html>