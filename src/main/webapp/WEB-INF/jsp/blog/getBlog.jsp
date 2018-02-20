<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.jhyarrow.myWeb.domain.Blog" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/toolbar.jsp" flush="true"/>
	<p class="text-center h2">${blog.title}</p><br>
	<p class="text-center h4">${blog.inputDate}</p><br><br>
	<p class="h3">${blog.info}</p>
	<a href="getBlogList.action" class="btn btn-default" role="button">返回</a>
</body>
</html>
