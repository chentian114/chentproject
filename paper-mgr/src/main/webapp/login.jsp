<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--<meta name="viewport" content="width=device-width, initial-scale=1">-->
    <title>管理平台</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="apple-mobile-web-app-capable" content="no" />
    <meta name="format-detection" content="telephone=no" />
    <script type="text/javascript">
	    if (window != top){
			top.location.href = location.href;
		}
	</script>
    <%@ include file="/common/headCss.jsp"%>
</head>
<body class="bg">

<!--容器 start-->
<div class="wp-wrapper">
    <!--头部 start-->
    <div class="wp-head ui gird">
    <div class="ui container">
        <div class="ui secondary borderless menu">
            <div class="item sc-logo">
                <img src="<%=basePath %>/img/logo.png" alt="">
                <h3>管理平台</h3>
            </div>
        </div>
    </div>
</div>
    <!--头部 end-->
    <!--登录 start-->
    <div class="wp-content">
        <form class="ui form sc-login">
            <div class="field">
                <h3 class="ui horizontal divider header">欢迎使用管理平台</h3>
                <div class="ui hidden divider"></div>
            </div>
            <div class="field">
                <div class="ui fluid left input">
                    <input type="text" placeholder="用户账号" id="account">
                </div>
            </div>
            <div class="field">
                <div class="ui fluid left input">
                    <input type="password" placeholder="密码" id="password">
                </div>
            </div>
            <div class="field">
                <div class="ui grid">
                    <div class="eight wide column left aligned">
                        <!-- <div class="ui checkbox">
                            <input type="checkbox">
                            <label>记住密码</label>
                        </div> -->
                    </div>
                    <div class="eight wide column right aligned">
                        <a href="<%=basePath %>/jsp/password/safty.jsp">忘记密码？</a>
                    </div>
                </div>
            </div>
            <div class="field">
                <div class="ui negative small message d-n" id="errorOne">
                    <!-- <div class="header">出现了一些错误，可能原因如下！</div> -->
                    <ul class="list">
                         <li class="one" style="display: none;">用户名或密码错误，请重新输入！</li>
                         <li class="two" style="display: none;">请输入账号！</li>
                         <li class="three" style="display: none;">请输入密码！</li>
                    </ul>
                </div>
            </div>
            <div class="ui hidden divider"></div>
            <a href="#" onclick="login();" class="ui fluid blue button">登录</a>
        </form>
    </div>
    <!--登录 end-->
</div>
<!--容器 end-->

<%@ include file="/common/foot.jsp"%>
<%@ include file="/common/footjs.jsp"%>
<script src="<%=basePath %>/js/MD5/md5.min.js"></script>
<script src="<%=basePath %>/js/mgr/login.js"></script>
</body>
</html>