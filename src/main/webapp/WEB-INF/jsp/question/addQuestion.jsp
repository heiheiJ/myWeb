<%@page contentType="text/html;charset=utf-8" language="java"%>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>index</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/toolbar.jsp" flush="true"/>
	<form class="form-horizontal" action="addQuestion.action">
		<div class="form-group">
			<label class="col-sm-2 control-label">问题</label>
		    <div class="col-sm-10">
		    	<textarea class="form-control" rows="10" name="question"></textarea>
		    </div>
	  	</div>
	  	<div class="form-group">
 	 		<label class="col-sm-2 control-label"></label>
 	 		<div class="col-sm-10">
		 	 	<button type="submit" class="btn btn-default">提交</button>
		 	 	<a href="getQuestionList.action" class="btn btn-default" role="button">返回</a>
 	 		</div>
 	 	</div>
	</form>
</body>
</html>
