<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <title>管理平台</title>
   <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
</head>
<body>
<!--FOOTER start-->

<!--脚部 start-->
<div class="wp-footer login">
    <div class="ui padded grid">
        <div class="eight wide column">
        </div>
        <div class="eight wide right aligned column">
            Copyright © chentian All rights reserved.
        </div>
    </div>
</div>

<!--退出登录-->
<div class="ui tiny modal exit" id="login_out_modal" style="height: 160px;">
    <div class="header">提示</div>
    <div class="content">
        是否退出登录？
    </div>
    <div class="actions">
        <div class="ui cancel button">取消</div>
        <div class="ui blue button" onclick="loginOut();">确定</div>
    </div>
</div>

<div class="ui tiny modal exit" id="userCancel" style="height: 160px;">
    <div class="header">提示</div>
    <div class="content">
                    登录信息已经失效，请重新登录！
    </div>
    <div class="actions">
        <div class="ui blue button" onclick="loginOut();">确定</div>
    </div>
</div>

</body>