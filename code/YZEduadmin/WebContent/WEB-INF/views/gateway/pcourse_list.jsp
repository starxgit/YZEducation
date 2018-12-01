<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="stylesheet" type="text/css" href="../resources/statiic/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="../resources/statiic/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="../resources/liib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../resources/statiic/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="../resources/statiic/h-ui.admin/css/style.css" />

<style type="text/css">  
#mytable {  
    table-layout: fixed;  
    width: 98% border:0px;  
    margin: 0px;  
}  

#mytable tr td {  
    text-overflow: ellipsis; /* for IE */  
    -moz-text-overflow: ellipsis; /* for Firefox,mozilla */  
    overflow: hidden;  
    white-space: nowrap;  
    text-align: center;  
}  
</style> 

<title>门户管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 
<span class="c-gray en">&gt;</span> 门户管理 
<span class="c-gray en">&gt;</span> 平台展示课程管理 
<a  class="btn btn-success radius" style="margin-left:50px;" data-title="添加资讯" href="http://localhost:8080/YZEduadmin/pcourse/clist" >添加展示课程</a>

<a class="btn btn-success radius r" target="_blank" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
<i class="Hui-iconfont">&#xe68f;</i></a></nav>

<div class="page-container">

	<div class="mt-20">
		
		<table id="mytable" class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th ><input type="checkbox" name="" value=""></th>
					<th >课程代码</th>
					<th >课程名称</th>
					<th >课程介绍</th>
					<th >课程教师</th>
					<th >操作</th>
				</tr>
			</thead>
			<tbody>
			   <core:forEach var="pcourse" items="${pcourses}">
				<tr class="text-c">
					<td><input type="checkbox" value="" name=""></td>
					<td>${pcourse.course_code}</td>
					<td>${pcourse.course_name}</td>
					<td>${pcourse.course_introduce}</td>
					<td>${pcourse.teacher_name}</td>
					<td>
					<%-- <a name="edit" href="../pcourse/edit?platform_course_id=${pcourse.platform_course_id}" class="Hui-iconfont" title="编辑">
					&#xe6df;
					</a>  --%>
					<a name="deliete" href="../pcourse/del?platform_course_id=${pcourse.platform_course_id}" class="Hui-iconfont" title="删除">
					&#xe6e2;
					</a>
					</td>
				</tr>
				</core:forEach>
			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript" src="../resources/liib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../resources/liib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../resources/statiic/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="../resources/statiic/h-ui.admin/js/H-ui.admin.js"></script> 

<script type="text/javascript" src="../resources/liib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="../resources/liib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="../resources/liib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"pading":false,
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,5]}// 不参与排序的列
	]
});

</script> 
</body>
</html>