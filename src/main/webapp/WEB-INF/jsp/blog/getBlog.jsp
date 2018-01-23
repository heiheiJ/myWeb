<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.jhyarrow.myWeb.domain.Blog" %>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
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
	  			<li role="presentation" class="active"><a href="getBlogList.action">读书笔记</a></li>
			</ul>
	</nav>
	<p class="text-center h2">${blog.title}</p><br>
	<p class="text-center h4">${blog.input_date}</p><br><br>
	<p class="text-center h3">${blog.info}</p>
	<a href="getBlogList.action" class="btn btn-default" role="button">返回</a>
</body>
</html>
