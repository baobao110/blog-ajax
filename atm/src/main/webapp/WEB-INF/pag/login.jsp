<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>


<jsp:include page="common/head.jsp"></jsp:include>

<body>
<h1>博客系统-登录</h1>

<form method="post">	
	用户；<input type="text" name="username" id="username"> <br>
	密码：<input type="password" name="password" id="password">	<br>
	<input type="submit" value="登录" onclick="login()">
</form>

<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/js/json2.js"></script>
<script type="text/javascript">
	function login(){
		var param = {
				username : $('#username').val(),
				password : $('#password').val()
		};
		
	$.post('/user/login.do',param, callback);
	}
	

	function callback(data, status) {
		alert('点击');
		
		//data = eval('(' + data + ')');
		var ajaxDAO = JSON.parse(data);
		if (ajaxDAO.success) {
			alert('成功');
			window.location.href='/user//toUsercenter.do';
		} else {
			alert('用户已存在');
		}
	}
	</script>
	
<a href="/user/toRegister.do" target="_blank">注册</a>
</body>

<jsp:include page="common/foot.jsp"></jsp:include>
</html>