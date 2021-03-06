<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>index</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/toolbar.jsp" flush="true"/>
	<form class="form-horizontal" action="addBlog.action">
		<div class="form-group">
	    	<label class="col-sm-2 control-label">类型</label>
		    <div class="col-sm-10">
		    	<select class="form-control" name="type">
		    		<c:forEach items="${typeList}" var="type">
		    			<option value="${type.key}">${type.value}</option>
		    		</c:forEach>
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
