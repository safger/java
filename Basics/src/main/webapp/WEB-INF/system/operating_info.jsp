
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
				$("#all").change(function() {
		            if (!$("#all").attr("checked")) {
		               $("input[name=uch]").removeAttr('checked');
		            }else if($("#all").attr("checked")){
		            	 $("input[name=uch]").attr('checked','checked');
		            }
		           }); 
	 });
	 function add(){
		 var url="<%=basePath%>system/operating/operating_info_edit.html?MenuId=${MenuId}";
		 window.location.href=url;
	 }
	 
</script>
		<style>
</style>
	</head>
	<body>
	<div id="content">
		<div class="content-center">
			<div class="manage-title">
				<div class="manage-title-l"
					style="width: 150px; border: 0px; padding: 0;">
					<img src="<%=basePath%>images/title_06.png" />
					<span class="hui-14-41-b">操作权限信息</span>
				</div>
				<div class="manage-title-r">
					<ul>
						<li class="btn">
							<input type="button" onclick="add()" class="common" value="新增" />
						</li>
					</ul>
				</div>
			</div>
			<div class="manage-content">
				<table width="100%" cellSpacing="0" cellPadding="0" class="table">
       			 <tr class="title">
          				<td>
						名称
						</td>
          				<td>
						备注
						</td>
						<td align="center" class="noborder"  >
							操作
						</td>
       				 </tr>
       				 
       				 
       				 <c:forEach items="${baseOperating_list}" var="list" varStatus="stuts" >
						 	 	<c:if test="${stuts.count%2 == '0'}">
									 <tr class="row1">
								  </c:if>
								  <c:if test="${obj.count%2 != '0'}">
								 	 <tr class="row2">
								  </c:if>
								  <td>
									${list.fullname }&nbsp;
									</td>
									<td  >
									${list.description }	&nbsp;
									</td>
									<td   align="center" >
										<c:if test="${!fn:startsWith(list.code, 'base')}">
											<a class="blue-12"  href="<%=basePath%>system/operating/update_show.html?id=${list.fuid }&MenuId=${MenuId}">修改</a>
											<a onclick="if(!confirm('确定删除！')){return false;}" class="blue-12"
											href="<%=basePath%>system/operating/delete.html?id=${list.fuid }&MenuId=${MenuId}">删除</a>
										</c:if>
										<c:if test="${fn:startsWith(list.code, 'base')}">
												基础操作不允许修改
										</c:if>
									</td>
								</tr>
						</c:forEach>		  
					 </table>
			</div>
		</div>
	</div>
	<form id="form2" method="post" action="<%=basePath %>system/AssignRoles.html?OrganizeId=${OrganizeId}">
			<input type="hidden" name="userid"  id="userid" />
			<input type="hidden" name="roleid"  id="roleid" />
	</form>
	<div style="display: none;">
		<div id="dd" data-options="iconCls:'icon-save'" style="padding:5px;width:400px;height:600px;">
			<iframe width="100%" height="100%" frameborder="0" src="<%=basePath%>Role!showChange"></iframe>
		</div>
	</div>
	</body>

</html>