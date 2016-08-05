<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<link href="<%=basePath%>css/layout.css" rel="stylesheet" type="text/css" />
	<script language="javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>

	<script>
		$(document).ready(function() {
			var mpar=$("a[name='par']");
		 	mpar.click(function (){
		 		var cl=$(this).attr("class");
		 		var div=$("div").find(".nav2");
		 		div.each(function (){
		 			$(this).css("display","none");
		 		});
		 		mpar.each(function (){
		 			$(this).attr("class","nav1");
		 		});
		 		if(cl=='nav1'){
		 			$(this).attr("class","active nav1");
		 			$(this).next(".nav2").css("display","block");
		 		}else{
		 			$(this).attr("class","nav1");
		 			$(this).next(".nav2").css("display","none");
		 		}
		 	});
		 	pageLoad();
	});
	
   function pageLoad(){
      var url  =$("a[name='par']").eq(0).attr("href");
      if(url=='javascript:void(0)'){
     	 url=$("a[name='cpar']").eq(0).attr("href");
     	 $("a[name='par']").eq(0).click();
      }else{
      	$("a[name='par']").eq(0).click();
      }
      window.parent.document.getElementById("right").src=url;
   }
	
	</script>
	
	
  </head>
  
  <body>
   <div class="inleft"  >
        	<h2>${skey }</h2>
            <div class="leftnav">
            	<c:forEach items="${parentList }" var="list">
            		 <a style="cursor: pointer;"id="par" name="par" href="<%=basePath%>${list.menuUrl }" target="right" class="nav1">
	                		<span style="background-image: url('<%=basePath%>${list.images }')" class="tip1">${list.menuName }</span></a>  <!--  class="active nav1" -->
		               		<c:choose>
								<c:when test="${var.children.size>0}">
									<div style="display: none;" class="nav2" >
										<c:forEach items="${list.children }" var="clist">
											<a target="right" name="cpar" href="<%=basePath%>${clist.menuUrl }">${clist.menuName}</a>
										</c:forEach>
					               	</div>
								</c:when>
							</c:choose>
            	</c:forEach> 
            </div>
        </div>
  </body>
</html>
