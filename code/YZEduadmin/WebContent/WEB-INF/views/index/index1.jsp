<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />

<link rel="stylesheet" type="text/css" href="resources/statiic/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="resources/statiic/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="resources/liib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="resources/statiic/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="resources/statiic/h-ui.admin/css/style.css" />

<title>运营管理端</title>
<meta name="keywords">
<meta name="description">
</head>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl">
		 <a class="logo navbar-logo f-l mr-10 hidden-xs" href="#">
		 	<img width="40px" height="40px" src="resources/img/logo.png">
		 </a>
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			
		<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			<ul class="cl">
				<li class="dropDown dropDown_hover">
				<!-- <span>超级管理员</span> -->
					<a href="#" class="dropDown_A">${admin_name} <i class="Hui-iconfont">&#xe6d5;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li></li>
						<li><a href="http://localhost:8080/YZEduadmin/school/loginout">退出</a></li>
				</ul>
			</li>
				
				<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
						<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
						<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
						<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
						<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>
</header>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">

		<dl id="menu-comments">
			<dt><i class="Hui-iconfont">&#xe61d;</i> 系统管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li>
						<a data-href="http://localhost:8080/YZEduadmin/school" data-title="院校管理" href="javascript:;">
							院校管理
						</a>
					</li>
					<li>
						<a data-href="http://localhost:8080/YZEduadmin/school/orglist" data-title="机构管理" href="javascript:;">
							机构管理
						</a>
					</li>
					<li>
						<a data-href="http://localhost:8080/YZEduadmin/admin/adlist" data-title="管理员管理" href="javascript:;">
							管理员管理
						</a>
					</li>
					<li>
						<a data-href="http://localhost:8080/YZEduadmin/school/mflogin" data-title="修改密码" href="javascript:;">修改密码</a>
					</li>
				</ul>
			</dd>
		</dl>

		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe67f;</i> 门户管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li>
						<a data-href="http://localhost:8080/YZEduadmin/banner/balist" data-title="轮播图管理" href="javascript:void(0)">轮播图管理</a>
					</li>
					<li>
						<a data-href="http://localhost:8080/YZEduadmin/pinfor/plist" data-title="资讯管理" href="javascript:void(0)">资讯管理</a>
					</li>
					<li><a data-href="http://localhost:8080/YZEduadmin/pcourse/pclist" data-title="展示课程" href="javascript:void(0)">展示课程</a></li>
				</ul>
			</dd>
		</dl>
		
		<dl id="menu-admin">
			<dt><i class="Hui-iconfont">&#xe62e;</i> 交流监管<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li>
						<a data-href="http://localhost:8080/YZEduadmin/sword/swlist" data-title="敏感词管理" href="javascript:void(0)">敏感词管理</a>
					</li>
					<!-- <li><a data-href="admin-role.html" data-title="全文搜索" href="javascript:void(0)">全文搜索</a></li> -->
				</ul>
			</dd>
		</dl>


		<dl id="menu-admin">
			<dt><i class="Hui-iconfont">&#xe6df;</i> 实时数据统计<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="peoplenum.html" data-title="统计" href="javascript:void(0)">实时在线人数统计</a></li>
					<!-- <li><a data-href="admin-role.html" data-title="统计" href="javascript:void(0)">实时服务器负载状况</a></li> -->
				</ul>
			</dd>
		</dl>

		<dl id="menu-admin">
			<dt><i class="Hui-iconfont">&#xe643;</i> 入驻管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li>
						<a data-href="http://localhost:8080/YZEduadmin/enter/eilist" data-title="入驻管理" href="javascript:void(0)">申请列表</a>
					</li>
					<li>
						<a data-href="http://localhost:8080/YZEduadmin/enter/edlist" data-title="入驻管理" href="javascript:void(0)">已处理申请</a>
					</li>
				</ul>
			</dd>
		</dl>

		<dl id="menu-admin">
			<dt><i class="Hui-iconfont">&#xe639;</i> 财务管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<!-- <li><a data-href="admin-role.html" data-title="财务管理" href="javascript:void(0)">平台资金总额</a></li> -->
					<li>
						<a data-href="http://localhost:8080/YZEduadmin/bill/blist" data-title="财务管理" href="javascript:void(0)">账单查询</a>
					</li>
				</ul>
			</dd>
		</dl>

		


</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="我的桌面">我的桌面</span>
					<em></em></li>
		</ul>
	</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
	</div>
</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
</ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="resources/liib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="resources/liib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="resources/statiic/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="resources/statiic/h-ui.admin/js/H-ui.admin.js"></script> 
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="resources/liib/jquery.contextmenu/jquery.contextmenu.r2.js">
	
</script>
<script type="text/javascript">

/*个人信息*/
function myselfinfo(){
	layer.open({
		type: 1,
		area: ['300px','200px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: '查看信息',
		content: '<div>管理员信息</div>'
	});
}

</script> 

</body>
</html>