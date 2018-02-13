<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<jsp:include page="common/head.jsp"></jsp:include>

<body>
<h1>ATM--开户</h1>

<form action="/blog/openAccount.do" method="post">	
	密码：<input type="password" name="password">
	<input type="submit" value="开户">
</form>

</body>

<a href="/blog/toUsercenter.do">返回个人中心</a>

<jsp:include page="common/foot.jsp"></jsp:include>
</html>