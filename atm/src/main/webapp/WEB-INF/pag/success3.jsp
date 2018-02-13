<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<jsp:include page="common/head.jsp"></jsp:include>

<body>
<h1>博客系统-删除成功</h1>
账户 	${number} <br>
删除成功

<a href="/blog/toList.do?number=${number}">返回</a>
</body>

<jsp:include page="common/foot.jsp"></jsp:include>

</html>