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

<title>入驻管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 
<span class="c-gray en">&gt;</span> 入驻管理 
<span class="c-gray en">&gt;</span> 申请管理 
<!-- <a  class="btn btn-success radius" style="margin-left:50px;" data-title="添加轮播图" href="http://localhost:8080/YZEduadmin/entering/add" >添加申请</a>
 -->
<a class="btn btn-success radius r" target="_blank" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
<i class="Hui-iconfont">&#xe68f;</i></a></nav>

<div class="page-container">

	<div class="mt-20">
		
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th ><input type="checkbox" name="" value=""></th>
					<th >名称</th>
					<th >类型</th>
					<th >校徽</th>
					<th >简介</th>
					<th >城市</th>
					<th >省份</th>
					<th >执照</th>
					<th >申请人姓名</th>
					<th >通知手机号</th>
					<th >通知邮箱</th>
					<th >操作</th>
				</tr>
			</thead>
			<tbody>
			   <core:forEach var="entering" items="${enterings}">
				<tr class="text-c">
					<td><input type="checkbox" value="" name=""></td>
					<td>${entering.school_name}</td>
					<td>${entering.school_type}</td>
					<td><img style="width:100px;" src="http://www.fstechnology.cn:8080/YZEduFileServer/showImg?myfile=${entering.school_badge}"></td>
					<td>${entering.school_introduce}</td>
					<td>${entering.school_city}</td>
					<td>${entering.school_province}</td>
					<td><img style="width:100px;" src="http://www.fstechnology.cn:8080/YZEduFileServer/showImg?myfile=${entering.school_license}"></td>
					<td>${entering.applicant_user_name}</td>
					<td>${entering.receiver_phone}</td>
					<td>${entering.receiver_mail}</td>
					<td>
					<a name="edit" href="../enter/addto?enter_id=${entering.enter_id}" class="Hui-iconfont" title="通过审核">
					&#xe600;
					</a> 
					<a name="delete" href="../enter/del?enter_id=${entering.enter_id}" class="Hui-iconfont" title="不通过审核">
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
	  {"orderable":false,"aTargets":[0,3]}// 不参与排序的列
	]
});

</script> 
</body>
</html>