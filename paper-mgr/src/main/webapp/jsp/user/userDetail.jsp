<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
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
                        <a class="section" href="<%=basePath %>/jsp/user/userManagerList.jsp" >用户管理</a>
                        <div class="divider"> / </div>
                        <div class="section active">用户详情</div>
                    </div>
                    <!--面包屑 end-->
                </div>
                <div class="ui basic segment">
                    <!--产品详情 start-->
                    <!--内容 start-->
                    <div class="ui form sc-form__show">
                        <div class="fields">
                            <div class="six wide field user-info">
                                <h3 class="ui header">用户信息</h3>
                                <div class="ui hidden divider"></div>

                                <div class="fields user-company-info">
                                    <div class="six wide field">
                                        <label>登录账号：</label>
                                    </div>
                                    <div class="ten wide field">
                                        <span id="account"></span>
                                    </div>
                                </div>
                                <div class="fields user-company-info">
                                    <div class="six wide field">
                                        <label>姓名：</label>
                                    </div>
                                    <div class="ten wide field">
                                        <span id="name"></span>
                                    </div>
                                </div>
                                <div class="fields user-company-info">
                                    <div class="six wide field">
                                        <label>用户角色：</label>
                                    </div>
                                    <div class="ten wide field">
                                        <span id="role"></span>
                                    </div>
                                </div>
                                <div class="fields user-company-info">
                                    <div class="six wide field">
                                        <label>联系方式：</label>
                                    </div>
                                    <div class="ten wide field">
                                        <span id="phone"></span>
                                    </div>
                                </div>
                                <div class="fields">
                                    <div class="six wide field">
                                        <label>备注：</label>
                                    </div>
                                    <div class="ten wide field">
                                        <span id="description"></span>
                                    </div>
                                </div>
                                <div class="fields">
                                    <div class="six wide field">
                                        <label>添加时间：</label>
                                    </div>
                                    <div class="ten wide field" >
                                        <span id="createTime"></span>
                                    </div>
                                </div>
                                <div class="fields">
                                    <div class="six wide field">
                                        <label>修改时间：</label>
                                    </div>
                                    <div class="ten wide field" >
                                        <span id="updateTime"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--内容 end-->
                    <!--用户详情 end-->
                </div>
            </div>
            <!--右侧内容 end-->
        </div>
    </div>
</div>
<%@ include file="/common/footjs.jsp"%>
<script type="text/javascript" src="<%=basePath %>/js/mgr/user/userDetail.js"></script>

</body>
</html>