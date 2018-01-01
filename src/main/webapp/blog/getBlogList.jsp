<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.jhyarrow.myWeb.domain.Blog" %>
<% ArrayList<Blog> blogs = (ArrayList<Blog>)request.getAttribute("blogs"); %>
<!DOCTYPE html>
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
</head>
<body>
	<menu>
		<button type="button" name="clickIndex" onclick="clickIndex()">首页</button>
		<button type="button" name="clickBookRecord" onclick="clickBookRecord()">读书笔记</button>
	</menu>
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
