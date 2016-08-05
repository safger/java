<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=8" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>主页</title>
    <link href="<%=basePath %>js/marvel/assets/css/bootstrap.css" rel="stylesheet" />
    <link href="<%=basePath %>js/marvel/assets/css/custom-styles.css" rel="stylesheet" />
    <link href="<%=basePath %>js/marvel/assets/css/font-awesome.css" rel="stylesheet" />
</head>

<body style="background-color: #EDEDED">
	<div class="header">
		<h1 class="page-header">
			主页 <small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a target="right" href="<%=basePath%>system/home.html">主页</a></li>
		</ol>
	</div>
	<div id="page-inner">
		<div class="row">
			<div class="col-xs-6 col-md-3">
				<div class="panel panel-default">
					<div class="panel-body easypiechart-panel">
						<h4>Profit</h4>
						<div class="easypiechart" id="easypiechart-blue" data-percent="82" ><span class="percent">82%</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-6 col-md-3">
				<div class="panel panel-default">
					<div class="panel-body easypiechart-panel">
						<h4>Sales</h4>
						<div class="easypiechart" id="easypiechart-orange" data-percent="55" ><span class="percent">55%</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-6 col-md-3">
				<div class="panel panel-default">
					<div class="panel-body easypiechart-panel">
						<h4>Customers</h4>
						<div class="easypiechart" id="easypiechart-teal" data-percent="84" ><span class="percent">84%</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-6 col-md-3">
				<div class="panel panel-default">
					<div class="panel-body easypiechart-panel">
						<h4>No. of Visits</h4>
						<div class="easypiechart" id="easypiechart-red" data-percent="46" ><span class="percent">46%</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
		   	<div class="col-md-3 col-sm-12 col-xs-12">
				<div class="panel panel-primary text-center no-boder blue">
					<div class="panel-left pull-left blue">
					    <i class="fa fa-eye fa-5x"></i>
					</div>
					<div class="panel-right">
						<h3>16,150</h3>
					   	<strong> Daily Visits</strong>
					</div>
				</div>
		   	</div>
			<div class="col-md-3 col-sm-12 col-xs-12">
				<div class="panel panel-primary text-center no-boder blue">
					<div class="panel-left pull-left blue">
						<i class="fa fa-shopping-cart fa-5x"></i>
					</div>
					<div class="panel-right">
						<h3>25,550 </h3>
						<strong> Sales</strong>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12 col-xs-12">
			    <div class="panel panel-primary text-center no-boder blue">
			        <div class="panel-left pull-left blue">
			            <i class="fa fa fa-comments fa-5x"></i>
					</div>
					<div class="panel-right">
						<h3>11,225 </h3>
						<strong> Comments </strong>
					</div>
				</div>
			</div>
		    <div class="col-md-3 col-sm-12 col-xs-12">
		        <div class="panel panel-primary text-center no-boder blue">
		            <div class="panel-left pull-left blue">
		                <i class="fa fa-users fa-5x"></i>
					</div>
					<div class="panel-right">
						<h3>72,525 </h3>
						<strong>No. of Visits</strong>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
