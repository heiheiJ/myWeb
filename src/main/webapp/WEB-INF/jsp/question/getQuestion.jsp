<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.jhyarrow.myWeb.domain.Blog" %>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Insert title here</title>
	<link rel='stylesheet' href='webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
	<script type='text/javascript' src='webjars/bootstrap/3.3.7/js/bootstrap.min.js'></script>
	<script type='text/javascript' src='webjars/jquery/1.11.1/jquery.min.js'></script>
</head>
<body>
	<nav class="navbar navbar-default">
			<ul class="nav nav-tabs">
	  			<li role="presentation" ><a href="getMain.action">首页</a></li>
	  			<li role="presentation" ><a href="getBlogList.action">读书笔记</a></li>
	  			<li role="presentation" class="active"><a href="getQuestionList.action">问答</a></li>
	  			<li role="presentation" ><a href="getSupport.action">股票预测</a></li>
			</ul>
	</nav>
	<form class="form-horizontal" action="updateQuestion.action">
	  	<div class="form-group">
		<label class="col-sm-2 control-label">Q:</label>
		<div class="col-sm-10">
			<label class="text-center h2">${question.question}</label>
		</div>	
 	 	</div>
 	 	<div class="form-group">
 	 	<label class="col-sm-2 control-label">A:</label>
		    <div class="col-sm-10">
		      <textarea class="form-control" rows="10" name="answer">${question.answer}</textarea>
		    </div>
 	 	</div>
 	 	<div class="form-group">
 	 		<input type="text" hidden="true" name="id" value="${question.id}"/>
 	 	</div>
 	 	<div class="form-group">
 	 		<label class="col-sm-2 control-label"></label>
 	 		<div class="col-sm-10">
		 	 	<button type="submit" class="btn btn-default">回答</button>
		 	 	<a href="getQuestionList.action" class="btn btn-default" role="button">返回</a>
 	 		</div>
 	 	</div>
	</form>
</body>
</html>
