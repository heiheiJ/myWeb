<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.jhyarrow.myWeb.domain.Blog" %>
<% Blog blog = (Blog)request.getAttribute("blog"); %>
<!DOCTYPE html>
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
<title>Insert title here</title>
</head>
<body>
	<menu>
		<button type="button" name="clickIndex" onclick="clickIndex()">首页</button>
		<button type="button" name="clickBookRecord" onclick="clickBookRecord()">读书笔记</button>
	</menu>
	<label><h1><%=blog.getTitle() %></h1></label>
	<label><%=blog.getInput_date() %></label><br>
	<label><%=blog.getInfo() %></label>
</body>
</html>
