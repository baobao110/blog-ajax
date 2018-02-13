<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<jsp:include page="common/head.jsp"></jsp:include>

<body>
<h1>博客系统-销户</h1>

<form action="/blog/delete.do" method="post">	
	卡号；${number}
	<input type="hidden" name="number" value="${number}"> <br>
	密码：<input type="password" name="password">	<br>
	<input type="submit" value="销户">
</form>
<!--  这里的action等同于href需要注意的是以后的url设置都如上面这样"/xxx.do? action=xxx"-->
</body>

<a href="/card/toUsercenter.do">返回个人中心</a>

<jsp:include page="common/foot.jsp"></jsp:include>
</html>