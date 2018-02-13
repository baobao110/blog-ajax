<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<jsp:include page="common/head.jsp"></jsp:include>

<body>
<h1>博客系统-业务办理成功</h1>
账户 ${blog.getNumber()} <br>
总数   ${blog.getTotal()}  <br>

<a href="/blog/toUsercenter.do">返回个人中心</a>
 
</body>


<jsp:include page="common/foot.jsp"></jsp:include>
</html>