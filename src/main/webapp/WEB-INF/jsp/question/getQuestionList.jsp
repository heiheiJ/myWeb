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
  			<li role="presentation" ><a href="getBlogList.action">读书笔记</a></li>
  			<li role="presentation" class="active"><a href="getQuestionList.action">问答</a></li>
  			<li role="presentation" ><a href="getSupport.action">股票预测</a></li>
		</ul>
	</nav>
	<table class="table table-hover table-bordered">
		<tr class="active">
			<td>序号</td>
			<td>提问</td>
			<td>日期</td>
			<td>状态</td>
			<td></td>
		<tr>
		<c:forEach items="${list}" var="question">
		<tr>
			<td>${question.rowno}</td>
			<td>${question.question}</td>
			<td>${question.date}</td>
			<td>${question.type}</td>
			<td><a href="getQuestion.action?id=${question.id}"><button>查看</button></a></td>
		</tr>
		</c:forEach>
	</table>
	<a href="addQuestionView.action" class="btn btn-primary" role="button">提问</a>
</body>
</html>
