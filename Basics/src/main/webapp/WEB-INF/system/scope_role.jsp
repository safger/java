
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
	 jQuery(document).ready(function($) {
	 });
	 function serach(){
		 var skey=$("#mc").val();
		 var url="<%=basePath%>system/Usershow.html?skey="+skey+"&OrganizeId=${OrganizeId}";
		 window.location.href=url;
	 }
	 function add(){
		 var url="<%=basePath%>system/menu/menu_edit.html";
		 window.location.href=url;
	 }
	 function delpar(id){
		 $.ajax({
	    url: "<%=basePath%>system/menu/deletePar.html?parentid=" + id + "&time=" + new Date(),
	    async: false,
	    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
	    dataType: "text",
	    success: function(data) {
	        if (data == "0") {
	            alert("请先删除子菜单");
	        } else {
	             location.reload();
	        }
	    }
	});
	 }
	 
</script>
		<style>
</style>
	</head>
	<body>
	<div id="content">
		<div>
			<div class="manage-title">
				<div class="manage-title-l"
					style="width: 150px; border: 0px; padding: 0;">
					<img src="<%=basePath%>images/title_06.png" />
					<span class="hui-14-41-b">数据集权限管理</span>
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
						角色名称
						</td>
          				<td  style="width: 120px"   >
						路径
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
						  <td    ><strong>${list.menuName }</strong>&nbsp;
						</td>
						<td    >${list.menuUrl } &nbsp;
						</td>
						<td  >
							${list.menuOrder }  &nbsp;
						</td>
						<td   align="center" >
							<a class="blue-12"  href="<%=basePath%>system/menu/menu_edit.html?id=${list.fuid}">新增子目录</a>
							<a class="blue-12"  href="<%=basePath %>system/menu/update_show.html?id=${list.fuid}">修改</a>
							<a  class="blue-12" href="javascript:delpar('${list.fuid}')" >删除</a>
						</td>
					</tr>
					<c:if test="${list.children!=null }">
						<c:forEach items="${list.children }" var="clist" varStatus="s" >
							 <c:if test="${s.count%2 == '0'}">
								 <tr class="row1">
							  </c:if>
							  <c:if test="${obj.count%2 != '0'}">
							 	 <tr class="row2">
							  </c:if>
							  <td style="color: blue;">----${clist.menuName }&nbsp;
										</td>
										<td>${clist.menuUrl }&nbsp;
										</td>
										<td  >
											${clist.menuOrder } &nbsp;
										</td>
										<td   align="center" >
											<a class="blue-12"  href="<%=basePath%>system/menu/menu_edit.html?id=${clist.fuid}">新增子目录</a>
											<a class="blue-12"  href="<%=basePath %>system/menu/update_show.html?id=${clist.fuid}">修改</a>
											<a onclick="if(!confirm('确定删除！')){return false;}" class="blue-12"
												href="<%=basePath %>system/menu/delete.html?id=${clist.fuid}&skey=${skey}&OrganizeId=${OrganizeId}">删除</a>
										</td>
									</tr>
									<c:if test="${ clist.children!=null}">
										<c:forEach items="${clist.children}" var="cclist" varStatus="ss" >
										 <c:if test="${ss.count%2 == '0'}">
											 <tr class="row1">
										  </c:if>
										  <c:if test="${obj.count%2 != '0'}">
										 	 <tr class="row2">
										  </c:if>
										  	<td style="color: red;">---------${ cclist.menuName}&nbsp;
											</td>
											<td>${ cclist.menuUrl} &nbsp;
											</td>
											<td  >
												${ cclist.menuOrder}   &nbsp;
											</td>
											<td   align="center" >
												<a class="blue-12"  href="<%=basePath %>system/menu/update_show.html?id=${cclist.fuid }">修改</a>
												<a onclick="if(!confirm('确定删除！')){return false;}" class="blue-12"
													href="<%=basePath %>system/menu/delete.html?id=${cclist.fuid }&skey=${skey}&OrganizeId=${OrganizeId}">删除</a>
											</td>
										</tr>
										</c:forEach>
									</c:if>
									
						</c:forEach>
					</c:if>	  
						  
					</c:forEach>  
					 </table>
			</div>
		</div>
	</div>
	</body>

</html>