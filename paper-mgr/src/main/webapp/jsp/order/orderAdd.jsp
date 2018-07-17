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
                        <div class="section active">录入货单</div>
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
                        <div class="required fields">
                            <div class="two wide field">
                                <label>客户名称:</label>
                            </div>
                            <div class="three wide field">
                                <select id="customerId" onchange="loadDefaultCustomer(this.options[this.options.selectedIndex].value)" style="border: 1px solid #d6d8dd;width: 110px;height: 34px;border-radius: 4px;">
                                </select>
                            </div>
                            <div class="two wide field ">
                                <a class="ui blue button release" onclick="selectCustomer();">选择客户</a>
                            </div>
                        </div>
                        <div class="required fields">
                            <div class="two wide field">
                                <label>客户联系方式</label>
                            </div>
                            <div class="three wide field">
                                <input name="phone" id="phone" type="text" value="" placeholder="请输入手机号码">
                            </div>
                        </div>
                        <div class="required fields">
                            <div class="two wide field">
                                <label>送货地址</label>
                            </div>
                            <div class="ten wide field">
                                <input name="address" id="address" type="text" value="" placeholder="请输入客户地址或实际送货地址">
                            </div>
                        </div>
                        <div class="required fields">
                            <div class="two wide field">
                                <label>送货日期</label>
                            </div>
                            <div class="ui icon input">
                                <input type="text"  class="nav-search-input" placeholder="送货日期"  id="createDateEnd" name="createDateEnd" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createDateEnd\')}'})"/>
                            </div>
                        </div>
                        <div class="required fields">
                            <div class="two wide field">
                                <label>付款方式</label>
                            </div>
                            <div class="ten wide field">
                                <select id="paymentType" style="border: 1px solid #d6d8dd;width: 110px;height: 34px;border-radius: 4px;">
                                    <option value="1">货到付款</option>
                                    <option value="2">款到发货</option>
                                    <option value="3">预付定金尾款货到结清</option>
                                    <option value="4">当月结清</option>
                                    <option value="5">月结30天</option>
                                </select>
                            </div>
                        </div>
                        <div class="required fields">
                            <div class="two wide field">
                                <label>送货方式</label>
                            </div>
                            <div class="ui icon input">
                                <select id="deliverType"  onchange="selectDeliverType(this.options[this.options.selectedIndex].value)" style="border: 1px solid #d6d8dd;width: 110px;height: 34px;border-radius: 4px;">
                                    <option value="1">秦超</option>
                                    <option value="2">客户自提</option>
                                    <option value="3">货拉拉</option>
                                    <option value="4">自定义</option>
                                </select>
                            </div>
                        </div>
                        <div class="required fields">
                            <div class="two wide field">
                                <label id="deliverTypeMsg">送货车牌号</label>
                            </div>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <div class="ui icon input">
                                <input name="deliveryCarNo" id="deliveryCarNo" type="text" value="A5QB65" placeholder="">
                            </div>
                        </div>
                        <div class="required fields">
                            <div class="two wide field">
                                <label id="deliverPhoneMsg">送货员号码</label>
                            </div>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <div class="ui icon input">
                                <input name="deliveryPhone" id="deliveryPhone" type="text" value="13922177479" placeholder="">
                            </div>
                        </div>

                        <div class="two wide field ">
                            <a class="ui blue button release" onclick="addProduct();">添加货品</a>
                        </div>
                        <div class="ui basic segment hidden" id="addProductDiv">
                            <div class="required fields">
                                <div class="two wide field">
                                    <label>品名:</label>
                                </div>
                                <div class="three wide field">
                                    <select id="productId" onblur="countMoney()" onchange="selectProdName(this.options[this.options.selectedIndex].value)" style="border: 1px solid #d6d8dd;width: 110px;height: 34px;border-radius: 4px;">
                                    </select>
                                </div>
                                <div class="two wide field hidden" id="prodNameSelfDiv">
                                    <input name="prodNameSelf" id="prodNameSelf" onblur="countMoney()" type="text" value="" placeholder="品名">
                                </div>
                                <div class="two wide field">
                                    <label>克重(g):</label>
                                </div>
                                <div class="three wide field">
                                    <select id="gweight" onblur="countMoney()" onchange="selectgweight(this.options[this.options.selectedIndex].value)" style="border: 1px solid #d6d8dd;width: 110px;height: 34px;border-radius: 4px;">
                                    </select>
                                </div>
                                <div class="two wide field hidden" id="gweightSelfDiv">
                                    <input name="gweightSelf" id="gweightSelf" onblur="countMoney()" type="text" value="" placeholder="克重(g)">
                                </div>
                            </div>
                            <div class="required fields">
                                <div class="two wide field">
                                    <label>规格类型:</label>
                                </div>
                                <div class="two wide field">
                                    <select id="specType" onchange="selectSpecType(this.options[this.options.selectedIndex].value)" style="border: 1px solid #d6d8dd;width: 110px;height: 34px;border-radius: 4px;">
                                    </select>
                                </div>
                                <div class="two wide field">
                                    <label>规格(cm):</label>
                                </div>
                                <div class="three wide field">
                                    <select id="spec" onblur="countMoney()" onchange="selectSpec(this.options[this.options.selectedIndex].value)" style="border: 1px solid #d6d8dd;width: 110px;height: 34px;border-radius: 4px;">
                                    </select>
                                </div>
                                <div class="three wide field hidden" id="specSelfDiv">
                                    <input name="specSelf" id="specSelf" type="text" value="" placeholder="规格(cm)">
                                </div>
                            </div>
                            <div class="required fields">
                                <div class="two wide field">
                                    <label>数量:</label>
                                </div>
                                <div class="two wide field">
                                    <input name="amount" id="amount" onblur="countMoney()" type="text" value="" placeholder="">
                                </div>

                                <div class="two wide field">
                                    <label>单位:</label>
                                </div>
                                <div class="one wide field" style="margin: 10px;">
                                    <span id="unitName">张</span>
                                    <input name="unit" id="unit" type="hidden" value="" placeholder="">
                                </div>

                                <div class="two wide field">
                                    <label>单价（元/吨）:</label>
                                </div>
                                <div class="two wide field">
                                    <input name="unitPrice" id="unitPrice" onblur="countMoney()" type="text" value="" placeholder="">
                                </div>
                            </div>

                            <div class=" fields" >
                                <div class="two wide field">
                                    <label>金额(元):</label>
                                </div>
                                <div class="two wide field" style="margin: 10px;">
                                    <span id="moneyName">0</span>
                                    <input name="money" id="money" type="hidden" value="" placeholder="">
                                </div>

                                <div class="two wide field">
                                    <label>备注:</label>
                                </div>
                                <div class="seven wide field">
                                    <input name="memo" id="memo" type="text" value="" placeholder="">
                                </div>
                            </div>
                            <div class=" left fields" >
                                <div class="four wide field"></div>
                                <div class="seven wide field">
                                    <a class="ui blue button release" onclick="productCancel();">取消</a>
                                    <a class="ui blue button release" onclick="productSave();">确认</a>
                                </div>
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
                                <label>合计大写(元):</label>
                            </div>
                            <div class="six wide field" style="margin: 10px;">
                                <h4 id="moneyCountUpperName"></h4>
                                <input name="email" id="moneyCountUpper" type="hidden" value="" placeholder="">
                            </div>
                            <div class="two wide field">
                                <label>合计小写(元):</label>
                            </div>
                            <div class="three wide field" style="margin: 10px;">
                                <h4 id="moneyCountName"></h4>
                                <input name="email" id="moneyCount" type="hidden" value="" placeholder="">
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
                                <label>制单员:</label>
                            </div>
                            <div class="three wide field">
                                <input name="userId" id="userId" type="hidden" value="${sessionScope.loginUserInfo.id}" placeholder="">
                                <h5 id="userName">${sessionScope.loginUserInfo.name}</h5>
                            </div>
                            <div class="two wide field">
                                <label>业务员:</label>
                            </div>
                            <div class="three wide field">
                                <input name="salesId" id="salesId" type="hidden" value="" placeholder="">
                                <h5 id="salesName"></h5>
                            </div>
                        </div>


                        <div class="required fields">
                            <div class="two wide field"></div>
                            <div class="ten wide field">
                                <!--错误提示 start-->
                                <div class="ui negative small message d-n" id="errorInfo">
                                    <div class="header">提示，请修改后重试！</div>
                                    <ul class="list"  id="errInfoList">
                                    </ul>
                                </div>
                                <!--错误提示 end-->
                            </div>
                        </div>

                    </div>


                    <!--表单 end-->
                    <div class="ui secondary menu" style="margin-left: 50px;">
                        <input type="hidden" id="primaryKey">

                        <div class="fitted item">
                            <a class="ui blue button release" onclick="orderAdd();">生成货单</a>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
            <!--右侧内容 end-->
        </div>
    </div>
</div>

<div class="ui tiny modal confirmTips" id="waiting" style="height: 200px;">
    <div class="header">提示</div>
    <div class="content">
        <h4 class="ui green header">
            <i class="checkmark icon"></i>
            正在生成货单，请耐心等待！
        </h4>
    </div>
</div>

<div class="ui tiny modal confirmTips" id="success" style="height: 200px;">
    <div class="header">提示</div>
    <div class="content">
        <h4 class="ui green header">
            <i class="checkmark icon"></i>
            该货单已新增成功！
        </h4>
    </div>
    <div class="actions">
        <div class="ui cancel button" onclick="location='<%=basePath %>/jsp/order/orderManagerList.jsp'">
            我知道了
        </div>
    </div>
</div>
<div class="ui tiny modal confirmTips" id="failure"  style="height: 200px;">
    <div class="header">提示</div>
    <div class="content">
        <h4 class="ui red header">
            <i class="remove icon"></i>
            该货单已新增失败！
        </h4>
    </div>
    <div class="actions">
        <div class="ui cancel button" onclick="hideInfo();">
            我知道了
        </div>
    </div>
</div>


<%@ include file="/common/footjs.jsp"%>
<script src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/mgr/order/orderAdd.js"></script>
</body>
</html>