<%@page contentType="text/html;charset=utf-8" language="java"%>

<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>index</title>
	</head>
	<jsp:include page="/WEB-INF/jsp/toolbar.jsp"></jsp:include>
	<body>
		<div class="container">
			<form class="form-signin" action="login.action">
		    	<h2 class="form-signin-heading">请登录</h2>
		        <label for="inputEmail" class="sr-only">用户名</label>
		        <input type="text" id="inputUsername" class="form-control" placeholder="用户名" required autofocus name="username">
		        <label for="inputPassword" class="sr-only">密码</label>
		        <input type="password" id="inputPassword" class="form-control" placeholder="密码" required name="password">
				<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
			</form>
		</div>
	</body>
</html>