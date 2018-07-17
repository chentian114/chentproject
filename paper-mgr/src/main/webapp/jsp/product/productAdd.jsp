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
            <a class="section" href="<%=basePath %>jsp/product/productManagerList.jsp" >产品管理</a>
            <div class="divider"> / </div>
            <div class="section active">添加产品</div>
          </div>
          <!--面包屑 end-->
        </div>
        <div class="ui basic segment">
          <!--表单 start-->
          <div class="ui form sc-form">

            <div class="required fields">
              <div class="two wide field">
                <label>产品名称</label>
              </div>
              <div class="ten wide field">
                <input name="prodName" id="prodName" type="text" value="" placeholder="请输入客户名称，不超过45个字">
              </div>
            </div>
            <div class="fields">
              <div class="two wide field">
                <label>描述</label>
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
                    <li class="1">产品名称不能为空；</li>
                    <li class="2">产品名称字数超出限制；</li>
                  </ul>
                </div>
                <!--错误提示 end-->
              </div>
            </div>

          </div>
          <!--表单 end-->
          <div class="ui secondary menu" style="margin-left: 50px;">
            <div class="fitted item">
              <a class="ui blue button release" onclick="productAdd();">确认</a>
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
      该产品已新增成功！
    </h4>
  </div>
  <div class="actions">
    <div class="ui cancel button" onclick="location='<%=basePath %>/jsp/product/productManagerList.jsp'">
      我知道了
    </div>
  </div>
</div>
<div class="ui tiny modal confirmTips" id="failure"  style="height: 200px;">
  <div class="header">提示</div>
  <div class="content">
    <h4 class="ui red header">
      <i class="remove icon"></i>
      该产品已新增失败！
    </h4>
  </div>
  <div class="actions">
    <div class="ui cancel button" onclick="hideInfo();">
      我知道了
    </div>
  </div>
</div>
<%@ include file="/common/footjs.jsp"%>
<script type="text/javascript" src="<%=basePath %>/js/mgr/product/productAdd.js"></script>
</body>
</html>