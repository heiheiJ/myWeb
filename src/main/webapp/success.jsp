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
<h1>操作成功</h1>

<menu>
		<button type="button" name="clickIndex" onclick="clickIndex()">首页</button>
		<button type="button" name="clickBookRecord" onclick="clickBookRecord()">读书笔记</button>
	</menu>
</html>