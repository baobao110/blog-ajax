<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<jsp:include page="common/head.jsp"></jsp:include>

<body>
<h1>个人中心页面</h1>
<img src="/user/showPicture.do" width="66px" height="66px" onerror="javascript:this.src='/images/dayuan.jpg';" ><br>
<a href="/user/toUpload.do">上传头像</a>
<h1>用户:${username}</h1>
<a href="/blog/toOpenAccount.do" target="_blank">开户</a><br>
<a href="/user/back.do" target="_blank">退出</a><br>

</body>

	<tr>
		<td>卡号</td>
		<td>时间</td>
		<td>手续</td>
	</tr>
<table id="blog">
	
</table>

		<button onclick="first()">首页</button>
		<button onclick="pre()">上一页</button>
		<button onclick="next()">下一页</button>
		<button onclick="last()">尾页</button>
		<span id="pag">1/0</span>

<jsp:include page="common/foot.jsp"></jsp:include>
</body>

<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/js/json2.js"></script>
<script type="text/javascript">
	
	
	var currentPage = 1;

	var totalPage = 0;
	function next() {
		if (currentPage == totalPage) {
			return false;
		}
		
		currentPage = parseInt(currentPage) + 1;
		flow();
	}
	
	function pre() {
		
		if (currentPage == 1) {
			return false;
		}
		
		currentPage = parseInt(currentPage) - 1;
		flow();
		
	}
	
	function first() {
		currentPage = 1;
		flow();
	}
	
	function last() {
		currentPage = totalPage;
		flow();
	}
		
	 function flow(){
		var param = {
				currentPage:currentPage
		};
		
	$.post('/user/ToFlow.do',param, callback);
	}
	
	function callback(data, status) {
		alert('点击');
		//alert(ajaxDAO);
		var ajaxDAO = JSON.parse(data);
		//var ajaxDAO=data;
		//alert(ajaxDAO);
		alert(ajaxDAO.success);
		alert(">>>"+ajaxDAO.data);
		var result=ajaxDAO.data.object;
		//var result=data.data.obj;
		alert(result);
		var msg='<tr><td>卡号</td><td>时间</td><td>手续</td></tr>';
		for (var i=0; i<result.length;i++) {
			msg+='<tr>';
			msg+='<td>'+result[i].number+'</td>';
			msg+='<td>'+result[i].createtime+'</td>';
			msg+='<td>';
			msg+='<a href="/blog/toWrite.do?number='+result[i].number+'" target="_blank">写博客</a>';
			msg+='<a href="/blog/toList.do?number='+result[i].number+'" target="_blank">进入</a>';
			msg+='<a href="/blog/toChangePassword.do?number='+result[i].number+'" target="_blank">修改密码</a>';
			msg+='<a href="/blog/toDelete.do?number='+result[i].number+'" target="_blank">销户</a>';
			msg+='</td></tr>';
			totalPage = ajaxDAO.data.totalPage;
			currentPage=ajaxDAO.data.currentPage;
			$('#blog').html(msg);
			$('#pag').html(currentPage+ '/' + totalPage);
		} 
		alert(msg);



	} 

	flow();//注意这个方法为什么要在js中直接调用，它的作用就是在登录页面跳转到//用户中心页面时它会自动调用这样就不需要担心表单如何和其它部分分解，这也是之前自己一//直困惑的地方
	
</script>


</html>

//这里ajax通过这个例子可以知道在前端的页面显示部分在用户中心界面进行一些局部的刷新
//可以直接调用方法方法通过传递前端的参数给后台,在后台通过相关的ajax命令可以将它以
//对象的形式返回到前端,前端取值时需要特别注意的一点是在前端进行取值时要注意它的取值//方式和后台的取值不一样，在前端取值直接以"."变量的方式取值而并不是像后台那样通过
//方法取,这里需要特别的注意同时在获取前端值时可以看浏览器的回应报文,这样就不容易出错
//还有就是从登录跳转到用户中心时需要在js中写自动调用的方法,这样就可以实现表单的加载