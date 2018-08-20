<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%--<c:set var="menu_id" value='<%=request.getParameter("menu_id") %>'></c:set>--%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,user-scalable=no">
<title>管理平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<style type="text/css">
.active_sub {
	background-color: rgba(8, 137, 255, 0.1);
	color: #fff !important;
}

.active_sub a {
	background-color: rgba(8, 137, 255, 0.1);
	color: #fff !important;
	/* border-left-color: #3ba1ff !important; */
	border-radius: 0 5px 0 0 !important;

}
a{
   	text-decoration:none;
   	display：block;
}
#iframe{border:none;height:100%;}
#iframe1{border:none;height:100%;}
#iframe2{border:none;height:100%;}
html { overflow-x:hidden; }  
</style>
<%@ include file="/common/headCss.jsp"%>
</head>
<body class="bg">

	<div class="wp-wrapper">
		<!--头部 start-->
		<div class="wp-head login">
			<div class="ui padded grid">
				<div class="three wide column" style="width: 50% !important">
					<div class="ui secondary borderless inverted menu">
						<div class="fitted item sc-logo">
							<img src="<%=basePath %>/img/logo.png" alt="">
							<h3>管理平台</h3>
						</div>
					</div>
				</div>
				<div class="thirteen wide column" style="width: 50% !important">
		            <div class="ui secondary borderless inverted menu">
		                <!--个人信息 start-->
		                <div class="fitted right menu">
		                    <div class="item" id="loginInfo">${sessionScope.loginUserInfo.name}&nbsp;${sessionScope.loginUserInfo.account}</div>
		                    <a class="item" title="退出" id="exit"><i class="power icon"></i>退出</a>
		                    <input type="hidden" id="userId" value="${sessionScope.loginUserInfo.id}"/>
							<input type="hidden" id="account" value="${sessionScope.loginUserInfo.account}"/>
							<input type="hidden" id="roleId" value="${sessionScope.loginUserInfo.roleId}"/>
						</div>
		                <!--个人信息 end-->
		            </div>
		        </div>
			</div>
		</div>
		<!--头部 end-->
		<!--内容-->
		<div class="wp-main">
			<div class="ui padded grid" id="managerBody">
				<!--左侧菜单 start-->
				<div class="wp-sidebar three wide column">
					<ul class="sidebar-menu" id="menuList">
					</ul>
				</div>
				<!--左侧菜单 end-->
				<!--右侧内容 start-->
				<div class="wp-content_right thirteen wide column" style="font-size:0px;line-height: 0px;width: 100%;">
					<iframe name='right' id="iframe" src="<%=basePath %>/jsp/order/customerManagerList.jsp"
							frameborder="no"
							style="width:100%;display:block;"></iframe>
				</div>
				<!--右侧内容 end-->
			</div>
		</div>
	</div>

	<%@ include file="/common/foot.jsp"%>
	<%@ include file="/common/footjs.jsp"%>
	<script type="text/javascript" src="<%=basePath %>/js/mgr/menu.js"></script>
	<script type="text/javascript" src="<%=basePath %>/js/common.js"></script>
	<script type="text/javascript">
	var account = $("#account").val();
	$(function(){
		if(common.isNull(account)){ //没有登录跳转到登录页
			window.location = basepath + "/login.jsp";
		}else{
		    loadMenu();
		}

		 //初始化下拉菜单
    	$(".ui.dropdown").dropdown();
    	$.sidebarMenu($('.sidebar-menu'));
	});
	</script>
	<script type="text/javascript">
		autodivheight();
		function autodivheight(){ //函数：获取尺寸
			//获取浏览器窗口高度
			var winHeight=0;
			if (window.innerHeight)
				winHeight = window.innerHeight;
			else if ((document.body) && (document.body.clientHeight))
				winHeight = document.body.clientHeight;
			//通过深入Document内部对body进行检测，获取浏览器窗口高度
			if (document.documentElement && document.documentElement.clientHeight)
				winHeight = document.documentElement.clientHeight;
			//DIV高度为浏览器窗口的高度
			document.getElementById("managerBody").style.height= common.formatFloatDigit(winHeight*0.8,1) +"px";

		}
		window.onresize=autodivheight; //浏览器窗口发生变化时同时变化DIV高度
	</script>
</body>
</html>