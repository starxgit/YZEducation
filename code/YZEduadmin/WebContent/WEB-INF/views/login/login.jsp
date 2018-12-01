<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<meta name="keywords" content="">
	<meta name="description" content="">
<title>登录界面</title>
<link rel="stylesheet" href="resources/css/layui.css">
<link rel="stylesheet" href="resources/css/sccl.css">
<link href="resources/statiic/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="resources/statiic/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="resources/statiic/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="resources/liib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
</head>
<body class="login-bg">

    <div class="login-box">
        <header>
            <h1>云智教育平台后台登录</h1>
        </header> 
        <div class="login-main">
			<form  class="layui-form" method="post">
				<input name="__RequestVerificationToken" type="hidden" value="">                
				<div class="layui-form-item">
				    <label class="Hui-iconfont">
						&#xe60d;用户名：
					</label>
					<input type="text" name="admin_name" lay-verify="admin_name" autocomplete="off" placeholder="这里输入用户名" class="layui-input">
				</div>
				<div class="layui-form-item">
					<label class="Hui-iconfont">
						&#xe60e;密码：
					</label>
					<input type="password" name="admin_password" lay-verify="admin_password" autocomplete="off" placeholder="这里输入密码" class="layui-input">
				</div>
				<div class="layui-form-item">
					
					<div class="pull-center">
						<button class="layui-btn layui-btn-primary"  lay-submit="" lay-filter="login">
							 登&nbsp;&nbsp;&nbsp;&nbsp;录
						</button>
						<button type="reset" class="layui-btn layui-btn-primary"  lay-submit="" lay-filter="">
							清空用户
						</button>
						<h5 style="color:#101010;">${error}</h5>
					</div>
					
					<div class="clear"></div>
				</div>
			</form>       
		</div>
    </div>
</body>
</html>