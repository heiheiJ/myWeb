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
	<div class="Header">
		<p>
			<h1>沉重纪念2017年12月24日考研失败，痛定思痛，特建此站。stay sense of crisis,stay freshman.</h1>
			<br>
			<h1>您是第<%=request.getAttribute("cnt") %>位拜访本站游客</h1>
			<h1>您的ip地址是<%=request.getRemoteAddr() %></h1>
			<hr>
		</p>
	</div>
	<div class="body">
		<menu>
		<button type="button" name="clickIndex" onclick="clickIndex()">首页</button>
		<button type="button" name="clickBookRecord" onclick="clickBookRecord()">读书笔记</button>
	</menu>
	</div>
</body>
</html>
