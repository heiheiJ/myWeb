<%@page contentType="text/html;charset=utf-8" language="java"%>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>index</title>
	<link rel='stylesheet' href='webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
	<script type='text/javascript' src='webjars/bootstrap/3.3.7/js/bootstrap.min.js'></script>
	<script type='text/javascript' src='webjars/jquery/1.11.1/jquery.min.js'></script>
</head>
<body>
	<nav class="navbar navbar-default">
			<ul class="nav nav-tabs">
	  			<li role="presentation" ><a href="getMain.action">首页</a></li>
  				<li role="presentation" class="active"><a href="getBlogList.action">读书笔记</a></li>
  				<li role="presentation" ><a href="getSupport.action">股票预测</a></li>
			</ul>
	</nav>
	<form class="form-horizontal" action="addBlog.action">
		<div class="form-group">
	    	<label class="col-sm-2 control-label">类型</label>
		    <div class="col-sm-10">
		    	<select class="form-control" name="type">
					<option value="1">读书笔记</option>
				    <option value="2">工作计划</option>
				</select>
		    </div>
	  	</div>
	  	<div class="form-group">
	    	<label class="col-sm-2 control-label">标题</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" name="title">
		    </div>
 	 	</div>
 	 	<div class="form-group">
 	 		<label class="col-sm-2 control-label">内容</label>
 	 		<div class="col-sm-10">
		 	 	<textarea class="form-control" rows="10" name="info"></textarea>
 	 		</div>
 	 	</div>
 	 	<div class="form-group">
 	 		<label class="col-sm-2 control-label"></label>
 	 		<div class="col-sm-10">
		 	 	<button type="submit" class="btn btn-default">提交</button>
		 	 	<a href="getBlogList.action" class="btn btn-default" role="button">返回</a>
 	 		</div>
 	 	</div>
	</form>
</body>
</html>
