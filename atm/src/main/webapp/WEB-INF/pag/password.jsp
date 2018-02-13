<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<jsp:include page="common/head.jsp"></jsp:include>

<body>
<h1>博客系统-修改密码</h1>

<form action="/blog/password.do" method="post">
	用户；${number}
	<input type="hidden" name="number" value="${number}"> <br>	
	原密码：<input type="password" name="oldPassword">	<br>
	新密码：<input type="password" name="newPassword"><br>
	<input type="submit" value="修改">
</form>
</body>

<a href="/blog/toUsercenter.do">返回个人中心</a>
<jsp:include page="common/foot.jsp"></jsp:include>
</html>