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
