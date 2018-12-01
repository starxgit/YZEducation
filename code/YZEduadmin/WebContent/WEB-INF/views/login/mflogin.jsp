<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<style type="text/css">
	*{
		font-family: 微软雅黑;margin:0px;padding: 0px;font-size: 14px;
	}
	#main{width: 300px;height: 200px;margin: 30px;line-height: 40px;}
</style>
<title>修改密码</title>
</head>
<body>
	<div id="main">
	<form method="post">
		用户名：<input class="form-control" readonly= "true" type="text" name="mname" value="${admin_name}"><br/>
		原密码：<input  class="form-control" type="password" name="pass"/>
		新密码：<input type="password" name="newpass" class="form-control">
		<input type="submit" class="btn btn-info" value="修改密码">
	</form>
	<div style="width: 200px;color: #FF0000;">
		${result}
	</div>
	</div>
</body>
</html>