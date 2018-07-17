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
                        <a class="section" href="<%=basePath %>jsp/order/customerManagerList.jsp" >客户管理</a>
                        <div class="divider"> / </div>
                        <div class="section active">添加客户</div>
                    </div>
                    <!--面包屑 end-->
                </div>
                <div class="ui basic segment">
                    <!--表单 start-->
                    <div class="ui form sc-form">
                        <div class="required fields">
                            <div class="two wide field">
                                <label>所属业务员</label>
                            </div>
                            <div class="two wide field">
                                <select id="salesId" style="border: 1px solid #d6d8dd;width: 110px;height: 34px;border-radius: 4px;">
                                </select>
                            </div>
                            <div class="two wide field ">
                                <a class="ui blue button release" onclick="selectSalesman();">选择业务员</a>
                            </div>
                        </div>
                        <div class="required fields">
                            <div class="two wide field">
                                <label>客户名称</label>
                            </div>
                            <div class="ten wide field">
                                <input name="custName" id="custName" type="text" value="" placeholder="请输入客户名称，不超过45个字">
                            </div>
                        </div>
                        <div class="required fields">
                            <div class="two wide field">
                                <label>客户地址</label>
                            </div>
                            <div class="ten wide field">
                                <input name="address" id="address" type="text" value="" placeholder="请输入客户地址">
                            </div>
                        </div>
                        <div class="required fields">
                            <div class="two wide field">
                                <label>客户类型</label>
                            </div>
                            <div class="ten wide field">
                                <select id="custType" style="border: 1px solid #d6d8dd;width: 110px;height: 34px;border-radius: 4px;">
                                    <option value="P">批发</option>
                                    <option value="L">零售</option>
                                </select>
                            </div>
                        </div>
                        <div class="required fields">
                            <div class="two wide field">
                                <label>手机号码</label>
                            </div>
                            <div class="ten wide field">
                                <input name="phone" id="phone" type="text" value="" placeholder="请输入手机号码">
                            </div>
                        </div>
                        <div class="fields">
                            <div class="two wide field">
                                <label>实际送货地址</label>
                            </div>
                            <div class="ten wide field">
                                <input name="deliveryAddress" id="deliveryAddress" type="text" value="" placeholder="请输入实际送货地址">
                            </div>
                        </div>


                        <div class="fields">
                            <div class="two wide field" style="width: 30%;line-height: 35px;">
                                <label style="color: #000">性别</label>
                            </div>
                            <div class="ten wide field w-55" style="line-height: 35px">
                                <input type="radio"  name="sex" value="M" checked/>男  &nbsp;<input type="radio" name="sex" value="F"/>女
                            </div>
                        </div>

                        <div class="fields">
                            <div class="two wide field">
                                <label>email</label>
                            </div>
                            <div class="ten wide field">
                                <input name="email" id="email" type="text" value="" placeholder="请输入邮箱地址">
                            </div>
                        </div>

                        <div class="fields">
                            <div class="two wide field">
                                <label>备注</label>
                            </div>
                            <div class="ten wide field">
                                <textarea name="memo" id="memo" ></textarea>
                            </div>
                        </div>

                        <div class="required fields">
                            <div class="two wide field"></div>
                            <div class="ten wide field">
                                <!--错误提示 start-->
                                <div class="ui negative small message d-n" id="errorInfo">
                                    <div class="header">提示，请修改后重试！</div>
                                    <ul class="list">
                                        <li class="1">客户名称不能为空；</li>
                                        <li class="2">客户名称字数超出限制；</li>
                                        <li class="3">客户地址不能为空；</li>
                                        <li class="4">客户地址字数超出限制；</li>
                                        <li class="5">客户手机号码不能为空；</li>
                                        <li class="6">客户手机号码不正确；</li>
                                    </ul>
                                </div>
                                <!--错误提示 end-->
                            </div>
                        </div>

                    </div>
                    <!--表单 end-->
                    <div class="ui secondary menu" style="margin-left: 50px;">
                        <div class="fitted item">
                            <a class="ui blue button release" onclick="customerAdd();">确认</a>
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
            该客户已新增成功！
        </h4>
    </div>
    <div class="actions">
        <div class="ui cancel button" onclick="location='<%=basePath %>/jsp/order/customerManagerList.jsp'">
            我知道了
        </div>
    </div>
</div>
<div class="ui tiny modal confirmTips" id="failure"  style="height: 200px;">
    <div class="header">提示</div>
    <div class="content">
        <h4 class="ui red header">
            <i class="remove icon"></i>
            该客户已新增失败！
        </h4>
    </div>
    <div class="actions">
        <div class="ui cancel button" onclick="hideInfo();">
            我知道了
        </div>
    </div>
</div>
<%@ include file="/common/footjs.jsp"%>
<script type="text/javascript" src="<%=basePath %>/js/mgr/order/customerAdd.js"></script>
</body>
</html>