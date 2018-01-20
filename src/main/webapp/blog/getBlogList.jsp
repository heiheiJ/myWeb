<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.jhyarrow.myWeb.domain.Blog" %>
<% ArrayList<Blog> blogs = (ArrayList<Blog>)request.getAttribute("blogs"); %>
<html>
<script type="text/javascript">
	function clickIndex(){
		window.location.href = "/myWeb/HelloWorld";
	}
	
	function clickBookRecord(){
		window.location.href = "/myWeb/GetBlogList";
	}
	
	function viewBlog(id){
		window.location.href = "/myWeb/GetBlog?id="+id;
	}
	
	function clickAddBlog(){
		window.location.href = "/myWeb/blog/addBlog.jsp";
	}
</script>
<head>
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
	<table border="1">
		<%for (int i=0;i<blogs.size();i++){ 
			Blog blog = blogs.get(i);
		%>
		<tr>
			<td><%=blog.getId() %></td>
			<td><%=blog.getTitle() %></td>
			<td><%=blog.getInput_date() %></td>
			<td><button type="button" name="view" onclick="viewBlog(<%=blog.getId()%>)">查看</button></td>
		</tr>
		<%} %>
	</table>
	<button type="button" name="addBlog" onclick="clickAddBlog()">添加</button>
</body>
</html>
