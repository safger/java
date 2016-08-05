
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String OrganizeId=request.getParameter("OrganizeId");		
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<title>后台维护信息</title>
		<link type="text/css" rel="stylesheet" href="<%=basePath%>css/manage-style.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/default/easyui.css" />
		<script type="text/javascript" src="<%=basePath %>js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/eayui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/manage.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/AutoSelect.js"></script>
		<script>
	 jQuery(document).ready(function($) {
	 });
	 function add(){
		 var url="<%=basePath%>system/role/add_show.html?OrganizeId=<%=OrganizeId%>";
		 window.location.href=url;
	 }
	 function showQx(RoleId){
	 	$("#ifr").attr("src","<%=basePath %>system/menu/ShowMenuRole.html?RoleId="+RoleId+"&OrganizeId=${OrganizeId}");
	 	 $('#dd').dialog({
		           	modal:true,
					title:"菜单列表"
		          });
	 }
	 function close(){
			$('#dd').dialog('close');
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
					<span class="hui-14-41-b">角色管理</span>
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
          				<td  style="width: 120px"   >
						名称
						</td>
						<td    >
							是否有效
						</td>
						<td    >
							备注
						</td>
						<td    >
							排序
						</td>
						<td align="center" class="noborder"  >
							操作
						</td>
       				 </tr>
       				  <c:forEach items="${baseRole_list }" var="list" varStatus="stuts" >
       				 	 <c:if test="${stuts.count%2 == '0'}">
							 <tr class="row1">
						  </c:if>
						  <c:if test="${obj.count%2 != '0'}">
						 	 <tr class="row2">
						  </c:if>
						  <td    >${list.realname }&nbsp;
						</td>
						<td>
							<c:choose>  
							   <c:when test="${list.enabled==1 }">   
							   	有效 
							   </c:when>  
							   <c:otherwise>  
							   	无效 
							   </c:otherwise>  
							</c:choose>  
						</td>
						<td  >
							${list.description }&nbsp;
						</td>
						<td  >
							${list.sortcode }&nbsp;
						</td>
						<td   align="center" >
							<a class="blue-12"  href="javascript:showQx('${list.fuid }','${OrganizeId }')">
							分配权限</a>
							<c:choose>  
							   <c:when test="${list.enabled==1 }">   
							   	<a class="blue-12"  href="<%=basePath %>system/role/valid.html?id=${list.fuid }&skey=0&OrganizeId=${OrganizeId}">禁用</a>
							   </c:when>  
							   <c:otherwise>  
							   	<a class="blue-12"  href="<%=basePath %>system/role/valid.html?id=${list.fuid }&skey=1&OrganizeId=${OrganizeId}">启用</a>
							   </c:otherwise>  
							</c:choose>  
							<a class="blue-12"  href="<%=basePath %>system/role/update_show.html?id=${list.fuid }&skey=${skey}&OrganizeId=${OrganizeId}">修改</a>
							<a onclick="if(!confirm('确定删除！')){return false;}" class="blue-12"
								href="<%=basePath %>system/role/delete.html?id=${list.fuid }&skey=${skey}&OrganizeId=${OrganizeId}">删除</a>
						</td>
					</tr>
       				 </c:forEach>
       				 
					
						
					 </table>
			</div>
			<div  style="display: none;">
				<div id="dd" style="top: 10px;height: 390px;">
					<iframe id="ifr" width="100%" height="100%" src="" scrolling="yes" frameborder="0"></iframe>
				</div>
			</div>
		</div>
	</div>
	</body>

</html>