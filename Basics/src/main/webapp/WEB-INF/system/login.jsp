<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>系统登录</title>

<link rel="stylesheet" href="<%=basePath %>js/login/css/reset.css">

<link rel="stylesheet" href="<%=basePath %>js/login/css/style.css" media="screen" type="text/css" />

</head>

<body>

<div id="window" style="display:none;">
<form action="<%=basePath%>system/oplogin.html" method="post"  id="myform" name="myform">
	<div class="page page-front">
		<div class="page-content">
			<div class="input-row">
				<label class="label fadeIn">用户名</label>
				<input name="username" id="username" type="text" data-fyll="" value="" class="input fadeIn delay1"/>
			</div>
			<div class="input-row">
				<label class="label fadeIn delay2">密码</label>
				<input id="password" name="password" type="password" data-fyll="" value="" class="input fadeIn delay3"/>
			</div>
			<div style="margin-top: -10px;margin-bottom: 10px;font-size: 12px;float: left;">
				<label>
						<input name="" type="checkbox" id="jzmm" value="" style="float: left;" />
						<span style="display: block;line-height: 20px;height: 16px;width: 100px">自动登录</span>  
				</label>
			</div>
			
			<div class="input-row perspective">
				<button id="submit" class="button load-btn fadeIn delay4" onclick="login()"  >
					<span class="default"><i class="ion-arrow-right-b"></i>登录</span>
					<div class="load-state">
						<div class="ball"></div>
						<div class="ball"></div>
						<div class="ball"></div>
					</div>
				</button>
			</div>
		</div>
	</div>
</form>	
</div>




</body>
<script type="text/javascript" src='<%=basePath %>js/jquery.js'></script>
		<script type="text/javascript" src="<%=basePath%>js/common/jquery.cookie.js"></script>
<script type="text/javascript">

	function login(){ 
		 var check=$("#jzmm").attr("checked");
				if(check==true){
					var cookie_username=$("#username").val();
					var cookie_userpwd=$("#password").val();
					$.cookie('login_cookie_username',$.trim(cookie_username), { expires: 30 });
					$.cookie('login_cookie_password',cookie_userpwd, { expires: 30 });
					$.cookie('login_cookie_checked',$.trim(true), { expires: 30 });
				}else{
					$.cookie('login_cookie_username',null);
					$.cookie('login_cookie_password',null);
					$.cookie('login_cookie_checked',$.trim(false), { expires: 30 });
				}
			   	$("#myform").submit(); 
	}
	
	
</script>	 
<script type="text/javascript" src='<%=basePath %>js/login/js/fyll.js'></script>
<script type="text/javascript" src='<%=basePath %>js/login/js/index.js'></script>
</html>