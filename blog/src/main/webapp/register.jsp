<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head>
	<meta charset="utf-8">
</head>
<body>
	<h2>登陆页</h2>
	<form method="post" action="register">
		<label for="user">用户名</label>
		<input type="text" name="username" id="user">
		<label for="pwd">密码</label>
		<input type="text" name="password" id="pwd">
		<label for="age">年龄</label>
		<input type="text" name="age" id="age">
		<label for="address">地址</label>
		<input type="text" name="address" id="address">
		<input type="submit" value="确定">
	</form>
</body>
</html>