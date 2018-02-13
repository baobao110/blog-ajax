<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<jsp:include page="common/head.jsp"></jsp:include>
<h1>上传头像</h1>
<form method="POST" enctype="multipart/form-data" action="/user/load.do">
选择上传文件 : <input type="file" name="file"><br/>
文件名 : <input type="text" name="name"/>
 <input type="submit" value="上传">
 </form>
 <a href="/blog/toUsercenter.do">返回个人中心</a>
<jsp:include page="common/foot.jsp"></jsp:include>
</html>