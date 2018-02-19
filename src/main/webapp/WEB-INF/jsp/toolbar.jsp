<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>index</title>
		<link rel='stylesheet' href='webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
		<script type='text/javascript' src='webjars/bootstrap/3.3.7/js/bootstrap.min.js'></script>
		<script type='text/javascript' src='webjars/jquery/1.11.1/jquery.min.js'></script>
	</head>
	<body>
		<nav class="navbar navbar-light">
			<ul class="nav navbar-nav">
		  		<li role="presentation" class="navbar-brand"><a href="getMain.action">首页</a></li>
		  		<li role="presentation" class="navbar-brand"><a href="getBlogList.action?page=0">读书笔记</a></li>
		  		<li role="presentation" class="navbar-brand"><a href="getQuestionList.action">问答</a></li>
		  		<li role="presentation" class="navbar-brand"><a href="getSupport.action">股票预测</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
		        	<c:when test="${sessionScope.username == null || sessionScope.username == ''}">
		        		<li role="presentation" class="navbar-brand"><a href="getLogIn.action">登录</a></li>
		        	</c:when>
		        	<c:otherwise>
		        		<li role="presentation" class="navbar-brand"><a href="logOff.action">注销</a></li>
		        	</c:otherwise>
		        </c:choose>
      		</ul>
		</nav>
	</body>
</html>