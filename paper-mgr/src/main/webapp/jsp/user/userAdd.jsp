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
    <%@ include file="/common/headCss.jsp"%>
</head>
<body class="bg">
<div class="wp-wrapper" style="background-color: #fff;">
    <!--内容-->
    <div class="wp-main">
        <div class="ui padded grid">
            <!--右侧内容 start-->
            <div class="wp-content_right thirteen wide column">
                <div class="ui basic segment">
                    <!--面包屑 start-->
                    <div class="ui breadcrumb">
                        <div class="section">当前位置：</div>
                        <a class="section" href="<%=basePath %>jsp/user/userManagerList.jsp" >用户管理</a>
                        <div class="divider"> / </div>
                        <div class="section active">添加用户</div>
                    </div>
                    <!--面包屑 end-->
                </div>
                <div class="ui basic segment">
                    <!--表单 start-->
                    <div class="ui form sc-form">

                        <div class="required fields">
                            <div class="two wide field">
                                <label>登录账号</label>
                            </div>
                            <div class="five wide field">
                                <input name="account" id="account" type="text" value="" placeholder="请输入登录账号，不超过10个英文或数字">
                            </div>
                        </div>
                        <div class="required fields">
                            <div class="two wide field">
                                <label>密码</label>
                            </div>
                            <div class="three wide field">
                                <input name="password" id="password" type="password" value="" >
                            </div>
                        </div>
                        <div class="required fields">
                            <div class="two wide field">
                                <label>确认密码</label>
                            </div>
                            <div class="three wide field">
                                <input name="rePassword" id="rePassword" type="password" value="" >
                            </div>
                        </div>
                        <div class="required fields">
                            <div class="two wide field">
                                <label>姓名</label>
                            </div>
                            <div class="three wide field">
                                <input name="name" id="name" type="text" value="" placeholder="请输入姓名，不超过10个字">
                            </div>
                        </div>
                        <div class="required fields">
                            <div class="two wide field">
                                <label>角色</label>
                            </div>
                            <div class="three wide field" style="margin: 10px;">
                                <span id="roleName">跟单</span>
                                <input name="roleId" id="roleId" type="hidden" value="2" >
                            </div>
                        </div>
                        <div class="fields">
                            <div class="two wide field">
                                <label>联系方式</label>
                            </div>
                            <div class="three wide field">
                                <input name="phone" id="phone" type="text" value="" placeholder="请输入联系方式">
                            </div>
                        </div>
                        <div class="fields">
                            <div class="two wide field">
                                <label>描述</label>
                            </div>
                            <div class="ten wide field">
                                <textarea name="description" id="description" ></textarea>
                            </div>
                        </div>

                        <div class="required fields">
                            <div class="two wide field"></div>
                            <div class="ten wide field">
                                <!--错误提示 start-->
                                <div class="ui negative small message d-n" id="errorInfo">
                                    <div class="header">提示，请修改后重试！</div>
                                    <ul class="list" id="errInfoList">
                                    </ul>
                                </div>
                                <!--错误提示 end-->
                            </div>
                        </div>

                    </div>
                    <!--表单 end-->
                    <div class="ui secondary menu" style="margin-left: 50px;">
                        <div class="fitted item">
                            <a class="ui blue button release" onclick="userAdd();">确认</a>
                        </div>
                        <input type="hidden" id="primaryKey">
                    </div>
                </div>
            </div>
            <!--右侧内容 end-->
        </div>
    </div>
</div>

<!--相关提示-->
<div class="ui tiny modal confirmTips" id="success" style="height: 200px;">
    <div class="header">提示</div>
    <div class="content">
        <h4 class="ui green header">
            <i class="checkmark icon"></i>
            该用户已新增成功！
        </h4>
    </div>
    <div class="actions">
        <div class="ui cancel button" onclick="location='<%=basePath %>/jsp/user/userManagerList.jsp'">
            我知道了
        </div>
    </div>
</div>
<div class="ui tiny modal confirmTips" id="failure"  style="height: 200px;">
    <div class="header">提示</div>
    <div class="content">
        <h4 class="ui red header">
            <i class="remove icon"></i>
            该用户已新增失败！
        </h4>
    </div>
    <div class="actions">
        <div class="ui cancel button" onclick="hideInfo();">
            我知道了
        </div>
    </div>
</div>
<%@ include file="/common/footjs.jsp"%>
<script src="<%=basePath %>/js/MD5/md5.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/mgr/user/userAdd.js"></script>
</body>
</html>