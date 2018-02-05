<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Insert title here</title>
	<link rel='stylesheet' href='webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
	<script type='text/javascript' src='webjars/bootstrap/3.3.7/js/bootstrap.min.js'></script>
	<script type='text/javascript' src='webjars/jquery/1.11.1/jquery.min.js'></script>
</head>
<body>
	<nav class="navbar navbar-default">
		<ul class="nav nav-tabs">
  			<li role="presentation" ><a href="getMain.action">首页</a></li>
  			<li role="presentation"><a href="getBlogList.action">读书笔记</a></li>
  			<li role="presentation" class="active"><a href="getSupport.action">股票预测</a></li>
		</ul>
	</nav>
	<table class="table table-hover table-bordered">
		<tr class="active">
			<td>股票代码</td>
			<td>股票名称</td>
			<td>原因</td>
		<tr>
		<c:forEach items="${stockList}" var="stock">
		<tr>
			<td>${stock.code}</td>
			<td>${stock.name}</td>
			<td>${stock.reason}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>
