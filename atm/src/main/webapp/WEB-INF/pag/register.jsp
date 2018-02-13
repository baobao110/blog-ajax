<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<jsp:include page="common/head.jsp"></jsp:include>

<body>
<h1>注册页面</h1>
<form  method="post">
用户名:<input type="text" name="username" id="username">
密码:<input type="password" name="password" id="password">
<input type="submit" value="注册" onclick="register()">
</form>

<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/js/json2.js"></script>
<script type="text/javascript">
	function register(){
		var param = {
				username : $('#username').val(),
				password : $('#password').val()
		};
		
	$.post('/user/Register.do',param, callback);
	}
	
	function callback(data, status) {
		alert('点击');
		var ajaxDAO = JSON.parse(data);
		if (ajaxDAO.success) {
			alert('成功');
			window.location.href='/user/toLogin.do';
		} else {
			alert('用户已存在');
		}
		
	}
</script>
<!--这里的ajax的整个过程是表单提交会触发相关的方法方法的参数就是要传到后台的数据，url地址就是原来表单的url地址,  -->
<!--回调函数的data就是后台传过来的数据需要做json转换,根据后台返回的数据决定是跳转到其它的页面还是不跳转  -->
<!-- 如果要跳转到其它的页面用 window.location.href 否则还在现在的页面-->
</body>

<jsp:include page="common/foot.jsp"></jsp:include>

</html>