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
			<td>日期</td>
			<td>第二天涨跌</td>
		<tr>
		<c:forEach items="${stockList}" var="stock">
		<tr>
			<td>${stock.code}</td>
			<td>${stock.name}</td>
			<td>${stock.reason}</td>
			<td>${stock.date}</td>
			<c:choose>
				<c:when test="${stock.up >= 0}">
					<td><font color="red">${stock.up}</font></td>
				</c:when>
				<c:otherwise>
					<td><font color="green">${stock.up}</font></td>
				</c:otherwise>
			</c:choose>
		</tr>
		</c:forEach>
	</table>
</body>
</html>
