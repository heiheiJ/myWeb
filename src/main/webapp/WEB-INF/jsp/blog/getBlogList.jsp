<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/toolbar.jsp" flush="true"/>
	<table class="table table-hover table-bordered">
		<tr class="active">
			<td>序号</td>
			<td>标题</td>
			<td>日期</td>
			<td>类型</td>
			<td></td>
		<tr>
		<c:forEach items="${blogList}" var="blog">
		<tr>
			<td>${blog.rowno}</td>
			<td>${blog.title}</td>
			<td>${blog.input_date}</td>
			<td>${blog.type}</td>
			<td><a href="getBlog.action?id=${blog.id}"><button>查看</button></a></td>
		</tr>
		</c:forEach>
	</table>
	<a href="addBlogView.action" class="btn btn-primary" role="button">添加</a>
</body>
</html>
