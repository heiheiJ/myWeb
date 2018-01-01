<%@page contentType="text/html;charset=utf-8" language="java"%>
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
	<form method="post" action="/myWeb/AddBlog">
		<label>类型</label>
			<select name="type">
				<option value="1">读书笔记</option>
				<option value="2">工作计划</option>
			</select><br><br>
		<label>标题：</label><input type="text" name="title"></input><br><br>
		<label>内容：</label><textarea rows="20" cols="100" name="info"></textarea><br><br>
		<input type="submit"/>
	</form>
</body>
</html>
