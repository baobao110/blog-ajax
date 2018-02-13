<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<jsp:include page="common/head.jsp"></jsp:include>	<!-- 这里需要注意的公共部分可以做成模版 -->

<body>
<h1>A博客系统-预览</h1><br>
<br> 标题: ${account.getTitle() }</br>
<br>作者: ${account.getNumber()}</br>
<br>时间:<fmt:formatDate value="${account.getCreatetime()}" pattern="yyyy-MM-dd HH:mm:ss"/></br>
<br>正文: ${account.getContext() }<br>

</body>

<a href="/blog/list.do?number=${account.getNumber()}&password=${password}">返回</a>

<jsp:include page="common/foot.jsp"></jsp:include>

</html>