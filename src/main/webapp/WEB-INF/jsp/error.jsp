<%@page contentType="text/html;charset=utf-8" language="java"%>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>错误提示</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/toolbar.jsp" flush="true"/>
		<h1>${message}</h1>
	</body>
</html>