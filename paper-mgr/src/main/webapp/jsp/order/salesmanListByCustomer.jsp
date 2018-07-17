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
<div class="wp-wrapper"  style="background-color: #fff;" >
    <!--内容-->
    <div class="wp-main">
        <div class="ui padded grid">
            <!--右侧内容 start-->
            <div class="wp-content_right thirteen wide column">
                <div class="ui basic segment">
                    <!--面包屑 start-->
                    <div class="ui breadcrumb">
                        <div class="section">当前位置：</div>
                        <a class="section" href="<%=basePath %>/jsp/order/salesmanListByCustomer.jsp" >业务员添加客户</a>
                        <div class="divider"> / </div>
                        <div class="section active">业务员列表</div>
                    </div>
                    <!--面包屑 end-->
                </div>
                <div class="ui basic segment">
                    <!--工具条 start-->
                    <div class="ui secondary menu">
                        <div class="fitted item">
                            <button class="ui blue button" onclick="addSalesman();"><i class="add icon"></i> 添加业务员</button>
                        </div>
                        <!--查询-->
                        <div class="fitted item">
                            <div class="ui icon input">
                                <input type="text" placeholder="业务员名称模糊搜索" id="searchSalesName">
                                <i class="search link icon" onclick="search();"></i>
                            </div>
                        </div>
                        <div class="fitted item">
                            <div class="ui icon input">
                                <input type="text" placeholder="业务员号码模糊搜索" id="searchPhone">
                                <i class="search link icon" onclick="search();"></i>
                            </div>
                        </div>
                    </div>
                    <!--工具条 end-->
                </div>
                <div class="ui basic segment">
                    <!--列表 start-->
                    <table class="ui very basic very compact unstackable table center aligned">
                        <!--表头-->
                        <thead>
                        <tr>
                            <th class="two wide">业务员名称</th>
                            <th class="two wide">业务员类别</th>
                            <th class="two wide">联系电话</th>
                            <th class="five wide">业务员地址</th>
                            <th class="four wide">操作</th>
                        </tr>
                        </thead>
                        <!--表内容-->
                        <tbody id="listData">

                        </tbody>
                        <!--表底-->
                        <tfoot>
                        <tr>
                            <th colspan="8">
                                <div class="ui left floated text menu">
                                    <div class="item">总共<span id="totalData"></span>条记录</div>
                                </div>
                                <div class="ui right floated text menu" id="listPage">
                                   
                                </div>
                            </th>
                        </tr>
                        </tfoot>
                    </table>
                    <!--列表 end-->
                </div>
            </div>
            <!--右侧内容 end-->
        </div>
    </div>
</div>
<%@ include file="/common/footjs.jsp"%>
<script src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/mgr/order/salesmanListByCustomer.js"></script>
<!--注意：开发时删除 end-->

<!--脚部 end-->


</body>
</html>