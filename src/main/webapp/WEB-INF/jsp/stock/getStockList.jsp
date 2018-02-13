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
