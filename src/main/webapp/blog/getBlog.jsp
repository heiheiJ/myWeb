<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.jhyarrow.myWeb.domain.Blog" %>
<% Blog blog = (Blog)request.getAttribute("blog"); %>
<html>
<script type="text/javascript">
	function clickIndex(){
		window.location.href = "/myWeb/HelloWorld";
	}
	
	function clickBookRecord(){
		window.location.href = "/myWeb/GetBlogList";
	}
	
</script>
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
	  			<li role="presentation" ><a href="/myWeb/HelloWorld">首页</a></li>
	  			<li role="presentation" class="active"><a href="/myWeb/GetBlogList">读书笔记</a></li>
			</ul>
	</nav>
	<label><h1><%=blog.getTitle() %></h1></label>
	<label><%=blog.getInput_date() %></label><br>
	<label><%=blog.getInfo() %></label>
</body>
</html>
