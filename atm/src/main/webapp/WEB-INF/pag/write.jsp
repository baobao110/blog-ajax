<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<jsp:include page="common/head.jsp"></jsp:include>	<!-- 这里需要注意的公共部分可以做成模版 -->

<body>
<h1>博客系统-写博客</h1>

<form action="/blog/write.do" method="post">	
	卡号 ${number}
	<input type="hidden" name="number" value="${number}"> <br>
	密码：<input type="password" name="password">	<br>
	标题；<input type="text" name="title"> <br>
	正文；<input type="text" name="context"> <br>
	<input type="submit" value="写博客">
</form>

</body>

<a href="/blog/toUsercenter.do">返回个人中心</a>

<jsp:include page="common/foot.jsp"></jsp:include>

</html>