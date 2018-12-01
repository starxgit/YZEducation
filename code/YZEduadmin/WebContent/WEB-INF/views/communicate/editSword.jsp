<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,
			minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />

<link rel="stylesheet" type="text/css" href="../resources/statiic/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" 
	  href="../resources/statiic/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" 
	  href="../resources/liib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" 
	  href="../resources/statiic/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="../resources/static/h-ui.admin/css/style.css" />

<title>修改敏感词汇 </title>
<meta name="keywords" >
<meta name="description" >
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="editSword" method="post">
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">
			<span class="c-red">*</span>敏感词汇：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${sword.sensitive_word}"  id="sensitive_word" name="sensitive_word">
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onClick="article_save_submit();" class="btn btn-primary radius" type="submit">提交</button>
			</div>
		</div>
		
	</form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" 
		src="../resources/liib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" 
		src="../resources/liib/layer/2.4/layer.js"></script>
<script type="text/javascript" 
		src="../resources/statiic/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" 
		src="../resources/statiic/h-ui.admin/js/H-ui.admin.js"></script> 

<script type="text/javascript" 
		src="../resources/liib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" 
		src="../resources/liib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" 
		src="../resources/liib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" 
		src="../resources/liib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" 
		src="../resources/liib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" 
		src="../resources/liib/ueditor/1.4.3/ueditor.config.js"></script> 
<script type="text/javascript" 
		src="../resources/liib/ueditor/1.4.3/ueditor.all.min.js"> </script> 
<script type="text/javascript" 
		src="../resources/liib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
</body>
</html>