<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.jhyarrow.myWeb.domain.Blog" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/toolbar.jsp" flush="true"/>
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
