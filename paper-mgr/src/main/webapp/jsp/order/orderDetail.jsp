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
                        <a class="section" href="<%=basePath %>jsp/order/orderManagerList.jsp" >货单管理</a>
                        <div class="divider"> / </div>
                        <div class="section active">货单详情</div>
                    </div>
                    <!--面包屑 end-->
                </div>
                <div class="ui basic segment ">
                    <!--表单 start-->
                    <div class="ui form sc-form">
                        <div class="ui basic segment">
                            <!--列表 start-->
                            <table class="ui very basic very compact unstackable table center aligned">
                                <!--表头-->
                                <thead>
                                <tr>
                                    <th class="two wide"><h2>广州市纸联纸业有限公司（送货单）</h2></th>
                                </tr>
                                <tr>
                                    <th class="two wide">地址：广州市增城区新塘镇白江村广虎路1号  电话：020-32179875传真：020-32179876</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                        <div class="fields user-company-info">
                            <div class="two wide field">
                                <label>货单编号：</label>
                            </div>
                            <div class="three wide field" style="margin: 10px;">
                                <span id="orderNumber"></span>
                            </div>
                        </div>
                        <div class="fields user-company-info">
                            <div class="two wide field">
                                <label>客户名称：</label>
                            </div>
                            <div class="three wide field" style="margin: 10px;">
                                <span id="customerName"></span>
                            </div>
                        </div>
                        <div class="fields">
                            <div class="two wide field">
                                <label>客户联系方式：</label>
                            </div>
                            <div class="three wide field" style="margin: 10px;">
                                <span id="phone"></span>
                            </div>
                        </div>
                        <div class="fields">
                            <div class="two wide field">
                                <label>送货地址：</label>
                            </div>
                            <div class="ten wide field" style="margin: 10px;">
                                <span id="address"></span>
                            </div>
                        </div>
                        <div class="fields">
                            <div class="two wide field">
                                <label>送货日期：</label>
                            </div>
                            <div class="ui icon input" style="margin: 10px;">
                                <span id="createDateEnd"></span>
                            </div>
                        </div>
                        <div class="fields">
                            <div class="two wide field">
                                <label>付款方式：</label>
                            </div>
                            <div class="ten wide field" style="margin: 10px;">
                                <span id="paymentType"></span>
                            </div>
                        </div>
                        <div class="fields">
                            <div class="two wide field">
                                <label>送货方式：</label>
                            </div>
                            <div class="ui icon input" style="margin: 10px;">
                                <span id="deliverType"></span>
                            </div>
                        </div>
                        <div class="fields">
                            <div class="two wide field">
                                <label id="deliverPhoneMsg">送货员号码：</label>
                            </div>
                            <div class="ui icon input" style="margin: 10px;">
                                <span id="deliveryPhone"></span>
                            </div>
                        </div>
                        <div class="ui basic segment">
                            <!--列表 start-->
                            <table class="ui very basic very compact unstackable table center aligned">
                                <!--表头-->
                                <thead>
                                <tr>
                                    <th class="two wide">品名</th>
                                    <th class="one wide">克重（克）</th>
                                    <th class="two wide">规格（厘米）</th>
                                    <th class="one wide">数量</th>
                                    <th class="one wide">单位</th>
                                    <th class="one wide">单价（元/吨）</th>
                                    <th class="one wide">金额（元）</th>
                                    <th class="two wide">备注</th>
                                </tr>
                                </thead>
                                <tbody id="productList">
                                </tbody>
                            </table>
                        </div>

                        <div class="fields">
                            <div class="two wide field">
                                <label>合计大写(元)：</label>
                            </div>
                            <div class="six wide field" style="margin: 10px;">
                                <h4 id="moneyCountUpperName"></h4>
                            </div>
                            <div class="two wide field">
                                <label>合计小写(元)：</label>
                            </div>
                            <div class="three wide field" style="margin: 10px;">
                                <h4 id="moneyCountName"></h4>
                            </div>
                        </div>

                        <div class="ui basic segment">
                            <!--列表 start-->
                            <table class="ui very basic very compact unstackable table left aligned">
                                <!--表头-->
                                <thead>
                                <tr>
                                    <th class="two wide">1.此单经收方验收签字或盖章后即为有效交货凭证，我司可根据客户要求,在交货或请款时交付发票,但款项支付以收据或转帐为准；</th>
                                </tr>
                                <tr>
                                    <th class="two wide">2.如有质量异议请于三天内书面提出，逾期则视为产品合格不予退货；</th>
                                </tr>
                                <tr>
                                    <th class="two wide">3.货物验收后，收货方保证按时付款，逾期，收货方应支付逾期货款的5%作为利息，如拖欠货款，付款方需承担一切经济，法律责任，如双方发生纠纷，由供方所在地人民法院处理；</th>
                                </tr>
                                <tr>
                                    <th class="two wide">4.货款结算方式：现金</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                        <div class="fields">
                            <div class="two wide field">
                                <label>制单员：</label>
                            </div>
                            <div class="three wide field" style="margin: 10px;">
                                <h5 id="userName"></h5>
                            </div>
                            <div class="two wide field">
                                <label>业务员：</label>
                            </div>
                            <div class="three wide field" style="margin: 10px;">
                                <h5 id="salesName"></h5>
                            </div>
                            <div class="two wide field">
                                <label>创建时间：</label>
                            </div>
                            <div class="three wide field" style="margin: 10px;">
                                <h5 id="createTime"></h5>
                            </div>
                        </div>
                    </div>


                    <!--表单 end-->
                    <div class="ui secondary menu" style="margin-left: 50px;">
                        <input type="hidden" id="primaryKey">

                        <div class="fitted item">
                            <a class="ui blue button release" id="downButton" onclick="downOrder()">下载货单</a>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
            <!--右侧内容 end-->
        </div>
    </div>
</div>


<%@ include file="/common/footjs.jsp"%>
<script src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/mgr/order/orderDetail.js"></script>
</body>
</html>