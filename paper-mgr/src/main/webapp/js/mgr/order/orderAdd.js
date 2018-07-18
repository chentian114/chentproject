var id = decodeURI(enCode(getUrlParam("id")));
var customerId = decodeURI(enCode(getUrlParam("customerId")));

$(function(){
    loadDetails(id);
    loadPartCustomer(customerId);
    loadDefaultCustomer(customerId);
    loadDefaultOrderInfo();
    loadAllProduct();
});

function loadDetails(id){
    if(common.isNull(id)){
        return;
    }
}
function selectCustomer(){
    window.location=basepath+"/jsp/order/customerListByOrder.jsp?orderId="+id;
}
function loadPartCustomer(customerId){
    var page=1;
    var size=50;
    var param={
        "page_num":page,
        "page_size":size
    };
    function cb(data, textStatus) {
        var content = data.data.results;
        if (data.status) {
            filterDefaultCustomerAndAppendCustomers(content,customerId);
            if(common.isNull(customerId)){
                setDefaultCustomer(content[0]);
            }
        }
    }
    api_request('../../' + OAUTH_CUSTOMER_LIST, param, cb, null, true, null);
}

function filterDefaultCustomerAndAppendCustomers(content,customerId){
    if (common.isNull(content)) {
        return ;
    }
    var salesIdHtml = $("#customerId");
    for (var i = 0; i < content.length; i++) {
        if (customerId == null || customerId != content[i].id) {
            salesIdHtml.append("<option value=" + content[i].id + ">" + content[i].custName + "</option>");
        }
    }
}

function loadDefaultCustomer(customerId){
    if(common.isNull(customerId)){
        return;
    }
    var param={"id":customerId};
    function cb(data, textStatus) {
        if(data.status){
            var customer = data.data;
            setDefaultCustomer(customer);
        }
    }
    api_request('../../' + OAUTH_CUSTOMER_DETAIL, param, cb, null, true, null);
}

function setDefaultCustomer(customer){
    var salesIdHtml = $("#customerId");
    salesIdHtml.append("<option value=" + customer.id + ">" + customer.custName + "</option>");
    $("#customerId").val(customer.id);
    $("#phone").val(customer.phone);
    if(!common.isNull(customer.deliveryAddress)){
        $("#address").val(customer.deliveryAddress);
    }else{
        $("#address").val(customer.address);
    }
    $("#salesName").text(customer.salesmanEntity.salesName);
    $("#salesId").val(customer.salesId);
}

function loadDefaultOrderInfo(){
    $("#createDateEnd").val(common.downTimeFormatDate(new Date()));
}

function selectDeliverType(deliverType){
    if(deliverType == 1){
        $("#deliveryCarNo").val("A5QB65");
        $("#deliveryPhone").val("13922177479");
    }else{
        $("#deliveryCarNo").val("");
        $("#deliveryPhone").val("");
    }

    if(deliverType == 4){
        $("#deliverTypeMsg").text("送货员及方式");
    }else{
        $("#deliverTypeMsg").text("送货车牌号");
    }
}

function loadAllProduct(){
    function cb(data, textStatus) {
        if(data.status){
            var content = data.data;
            if(common.isNull(content)){
                return;
            }
            for (var i = 0; i < content.length; i++) {
                var productHtml = $("#productId");
                productHtml.append("<option value=" + content[i].id + ">" + content[i].prodName + "</option>");
            }

            var prodAttrs = content[0].prodAttrs;
            for(var i=0 ; i< prodAttrs.length; i++){
                fillProdAttr(prodAttrs[i]);
            }
        }
    }
    api_request('../../' + OAUTH_PRODUCT_ALL, null, cb, null, true, null);
}

var PROD_ATTR_GWEIGHT_ID=1;       //产品属性克重ID常量
var PROD_ATTR_SPEC_ID=2;          //产品属性规格ID常量
var PROD_ATTR_UNIT_ID=4;          //产品属性单位ID常量
var PROD_ATTR_SPEC_TYPE_ID=8;     //产品属性规格类型ID常量
var PRODUCT_ID_SELFDEFINE=20;     //产品自定义常量
var PRODUCT_ATTR_SELFDEFINE="-1"; //产品属性自定义常量
var SPEC_TYPE_AREA="长*宽";     //规格类型常量
var SPEC_TYPE_WIDE="宽幅";      //规格类型常量
var UNIT_ACOUNT="张";           //单位类型常量
var UNIT_KG="KG";              //单位类型常量
var MONEY_DIGIT_0=0;             //总金额精确到元
var MONEY_DIGIT_2=2;             //金额精确到分

function fillProdAttr(prodAttr){
    var valuesList = prodAttr.valuesList;
    var valueDescriptionList = prodAttr.valueDescriptionList;
    if(common.isNull(valuesList)){
        return;
    }
    var gweightHtml = $("#gweight");
    var uspecTypeHtml = $("#specType");
    for(var i=0 ; i< valuesList.length ; i++){
        if(prodAttr.id == PROD_ATTR_GWEIGHT_ID) { //克重
            gweightHtml.append("<option value=" + valuesList[i] + ">" + valueDescriptionList[i] + "</option>");
        }else if(prodAttr.id == PROD_ATTR_UNIT_ID){ //单位
            $("#unitName").text(valueDescriptionList[0]);
            $("#unit").val(valuesList[0]);
        }else if(prodAttr.id == PROD_ATTR_SPEC_TYPE_ID){ //规格类型
            uspecTypeHtml.append("<option value=" + valuesList[i] + ">" + valueDescriptionList[i] + "</option>");
        }
    }
    if(prodAttr.id == PROD_ATTR_SPEC_ID){ //规格
        var specAreaList = prodAttr.specAreaList;
        var specAreaDescriptionList = prodAttr.specAreaDescriptionList;
        for(var i=0 ; i< specAreaList.length ; i++){
            var specHtml = $("#spec");
            specHtml.append("<option value=" + specAreaList[i] + ">" + specAreaDescriptionList[i] + "</option>");
        }
    }
}

function selectProdName(productId){
    if(productId == PRODUCT_ID_SELFDEFINE){
        $("#prodNameSelfDiv").attr("class","three wide field show");
    }else{
        $("#prodNameSelfDiv").attr("class","hidden");
    }
}
function selectgweight(gweight){
    if(gweight == PRODUCT_ATTR_SELFDEFINE){
        $("#gweightSelfDiv").attr("class","two wide field show");
    }else{
        $("#gweightSelfDiv").attr("class","hidden");
    }
}

function fillProdAttrSpec(prodAttr,specType){
    var specHtml = $("#spec");
    specHtml.empty();
    var specWideList = prodAttr.specWideList;
    var specWideDescriptionList = prodAttr.specWideDescriptionList;
    var specAreaList = prodAttr.specAreaList;
    var specAreaDescriptionList = prodAttr.specAreaDescriptionList;
    if(specType == SPEC_TYPE_AREA){
        for(var j=0 ; j< specAreaList.length ; j++){
            specHtml.append("<option value=" + specAreaList[j] + ">" + specAreaDescriptionList[j] + "</option>");
        }
    }else if(specType == SPEC_TYPE_WIDE){
        for(var j=0 ; j< specAreaList.length ; j++){
            specHtml.append("<option value=" + specWideList[j] + ">" + specWideDescriptionList[j] + "</option>");
        }
    }
}
function fillProdAttrUnit(prodAttr,specType){
    var valuesList = prodAttr.valuesList;
    var valueDescriptionList = prodAttr.valueDescriptionList;
    for(var j=0 ; j< valuesList.length ; j++){
        if(specType == SPEC_TYPE_AREA && valuesList[j] == UNIT_ACOUNT){
            $("#unitName").text(valueDescriptionList[j]);
            $("#unit").val(valuesList[j]);
        }else if(specType == SPEC_TYPE_WIDE && valuesList[j] == UNIT_KG){
            $("#unitName").text(valueDescriptionList[j]);
            $("#unit").val(valuesList[j]);
        }
    }
}

function selectSpecType(specType){
    $("#specSelfDiv").attr("class","hidden");
    function cb(data, textStatus) {
        if(data.status){
            var content = data.data;
            if(common.isNull(content)){
                return  ;
            }
            var prodAttrs = content[0].prodAttrs;
            for(var i=0 ; i< prodAttrs.length; i++){
                if(prodAttrs[i].id == PROD_ATTR_SPEC_ID){ //规格
                    fillProdAttrSpec( prodAttrs[i],specType);
                }else if(prodAttrs[i].id == PROD_ATTR_UNIT_ID){ //单位
                    fillProdAttrUnit(prodAttrs[i],specType);
                }
            }
        }
    }
    api_request('../../' + OAUTH_PRODUCT_ALL, null, cb, null, true, null);
}

function selectSpec(specValue){
    if(specValue == PRODUCT_ATTR_SELFDEFINE){
        $("#specSelfDiv").attr("class","show");
    }else{
        $("#specSelfDiv").attr("class","hidden");
    }
}

function checkProdCount(){
    var errorInfo = $("#errorInfo");
    errorInfo.hide();
    var errli = $("#errInfoList");
    errli.empty();
    var flag =true;

    if(productCount<1 ){
        flag = false;
        errli.append("<li class='14'>暂未添加货单记录；</li>");
    }

    if(productCount>6 ){
        flag = false;
        errli.append("<li class='15'>最多添加6个货单记录；</li>");
    }
    if(!flag){
        errorInfo.show();
    }
    return flag;
}

function checkProdSave(){
    var errorInfo = $("#errorInfo");
    errorInfo.hide();
    var errli = $("#errInfoList");
    errli.empty();
    var flag =true;

    var prodName = $("#productId").find("option:selected").text();
    var prodNameSelf = $("#prodNameSelf").val();
    var productId = $("#productId").val();
    var finalprodName = prodName;
    if(productId == PRODUCT_ID_SELFDEFINE){
        finalprodName = prodNameSelf;
    }
    if(finalprodName.length == 0 ){
        flag = false;
        errli.append("<li class='5'>品名不能为空；</li>");
    }

    var gweight = $("#gweight").val();
    var gweightSelf = $("#gweightSelf").val();
    var finalgweight = gweight;
    if(gweight == PRODUCT_ATTR_SELFDEFINE){
        finalgweight = gweightSelf;
    }
    if(finalgweight.length == 0 ){
        flag = false;
        errli.append("<li class='6'>克重不能为空；</li>");
    }

    if(isNaN(finalgweight)){
        flag = false;
        errli.append("<li class='7'>克重不能为字符；</li>");
    }

    var specType = $("#specType").val();
    var spec = $("#spec").val();
    var specSelf = $("#specSelf").val();
    var finalspec = spec;
    if(spec == PRODUCT_ATTR_SELFDEFINE){
        finalspec = specSelf;
    }

    if(finalspec.length == 0){
        flag = false;
        errli.append("<li class='8'>规格不能为空；</li>");
    }

    if(specType == SPEC_TYPE_AREA){
        var specArrays = finalspec.split("*");
        var specNum1 = specArrays[0];
        var specNum2 = specArrays[1];
        if(specArrays.length !=2){
            flag = false;
            errli.append("<li class='9'>规格格式不正确；</li>");
        }
        if(isNaN(specNum1)){
            flag = false;
            errli.append("<li class='10'>规格不能为字符；</li>");
        }
        if(isNaN(specNum2)){
            flag = false;
            errli.append("<li class='11'>规格不能为字符；</li>");
        }
    }

    if(specType == SPEC_TYPE_WIDE){
        if(isNaN(finalspec)){
            flag = false;
            errli.append("<li class='12'>规格不能为字符；</li>");
        }
    }

    var amount = $("#amount").val();
    if(amount.length == 0 ){
        flag = false;
        errli.append("<li class='13'>数量不能为空；</li>");
    }

    if(isNaN(amount)){
        flag = false;
        errli.append("<li class='14'>数量不能为字符；</li>");
    }

    var unitPrice = $("#unitPrice").val();
    if(unitPrice.length == 0 ){
        flag = false;
        errli.append("<li class='15'>单价不能为空；</li>");
    }
    if(isNaN(unitPrice)){
        flag = false;
        errli.append("<li class='16'>单价不能为字符；</li>");
    }
    if(!flag){
        errorInfo.show();
    }
    return flag;
}

function checkProdAddParams(){
    var errorInfo = $("#errorInfo");
    errorInfo.hide();
    var errli = $("#errInfoList");
    errli.empty();
    var flag =true;

    var customerPhone = $("#phone").val();

    if(customerPhone.length == 0 ){
        flag = false;
        errli.append("<li class='1'>客户号码不能为空；</li>");
    }

    var deliveryAddress = $("#address").val();
    if(deliveryAddress.length == 0 ){
        flag = false;
        errli.append("<li class='2'>送货地址不能为空；</li>");
    }

    var deliveryCarNo = $("#deliveryCarNo").val();
    if(deliveryCarNo.length == 0 ){
        flag = false;
        errli.append("<li class='3'>送货车牌号不能为空；</li>");
    }

    var deliveryPhone = $("#deliveryPhone").val();
    if(deliveryPhone.length == 0 ){
        flag = false;
        errli.append("<li class='4'>送货员号码不能为空；</li>");
    }
    if(!flag){
        errorInfo.show();
    }
    return flag;
}

function countMoney(){
    var specType = $("#specType").val();
    var amount = $("#amount").val();
    var unitPrice = $("#unitPrice").val();
    var gweight = $("#gweight").val();
    var spec = $("#spec").val();

    if(gweight == PRODUCT_ATTR_SELFDEFINE){
        gweight = $("#gweightSelf").val();
    }
    if(spec == PRODUCT_ATTR_SELFDEFINE){
        spec = $("#specSelf").val();
    }

    var money = 0;
    if(specType == SPEC_TYPE_AREA){ //长*宽
        gweight = common.formatFloatDigit(gweight,MONEY_DIGIT_2);
        gweight = gweight/1000;
        var specArrays = spec.split("*");
        var specNum1 = specArrays[0];
        var specNum2 = specArrays[1];
        var specCount = specNum1 * specNum2 ;
        specCount = specCount/10000;
        unitPrice = unitPrice/1000;
        money = gweight * specCount * amount * unitPrice;
    }else if(specType == SPEC_TYPE_WIDE){//宽幅
        amount = amount/1000;
        money = amount * unitPrice;
    }
    money = common.formatFloatDigit(money,MONEY_DIGIT_2);
    $("#money").val(money);
    $("#moneyName").text(money);
}

var productCount = 0;           //合计产品总数
var moneyCount = parseFloat(0.00) ;  //合计金额总数
var prodParamList = [];         //添加产品列表
var DELIVER_TYPE_SELF = 4;      //送货方式自定义常量
function addProduct(){
    if(productCount == 6){
        alert("一个货单最多添加6个产品!");
        return;
    }
    var addProductDiv = $('#addProductDiv');
    addProductDiv.attr("class","ui basic segment show");
}

function resetProductAdd(){
    $("#productId option:first").prop("selected","selected");
    $("#gweight option:first").prop("selected","selected");
    $("#specType option:first").prop("selected","selected");
    $("#spec option:first").prop("selected","selected");
    selectProdName($("#productId").val());
    selectgweight($("#gweight").val());
    selectSpecType($("#specType").val());
    $("#amount").val("");
    $("#unitPrice").val("");
    $("#memo").val("");
}

function productSave(){
    var result = checkProdSave();
    if(!result){
        return;
    }

    var productId = $("#productId").val();
    var prodName = $("#productId").find("option:selected").text();
    var prodNameSelf = $("#prodNameSelf").val();
    var gweight = $("#gweight").val();
    var gweightSelf = $("#gweightSelf").val();

    var specType = $("#specType").val();
    var spec = $("#spec").val();
    var specSelf = $("#specSelf").val();

    var amount = $("#amount").val();
    var unit = $("#unit").val();
    var unitPrice = $("#unitPrice").val();
    var money = $("#money").val();
    var memo = $("#memo").val();

    var finalprodName = prodName;
    if(productId == PRODUCT_ID_SELFDEFINE){
        finalprodName = prodNameSelf;
    }
    var finalgweight = gweight;
    if(gweight == PRODUCT_ATTR_SELFDEFINE){
        finalgweight = gweightSelf;
    }
    var finalspec = spec;
    if(spec == PRODUCT_ATTR_SELFDEFINE){
        finalspec = specSelf;
    }

    var productListHtml = $("#productList");
    productListHtml.append("<tr>");
    productListHtml.append("<td class='two wide'>"+finalprodName+"</td>");
    productListHtml.append("<td class='one wide'>"+finalgweight+"</td>");
    productListHtml.append("<td class='two wide'>"+finalspec+"</td>");
    productListHtml.append("<td class='one wide'>"+amount+"</td>");
    productListHtml.append("<td class='one wide'>"+unit+"</td>");
    productListHtml.append("<td class='one wide'>"+unitPrice+"</td>");
    productListHtml.append("<td class='one wide'>"+money+"</td>");
    productListHtml.append("<td class='two wide'>"+memo+"</td>");
    productListHtml.append("</tr>");
    productCancel();
    countOrderMoney(money);

    var prodParam = {
        'productId': productId,
        'prodName':finalprodName,
        'gweight':finalgweight,
        'specType':specType,
        'spec':finalspec,
        'amount':amount,
        'unit':unit,
        'unitPrice':unitPrice,
        'money':money,
        'memo':memo
    };

    addProductParamList(prodParam);

}
function productCancel(){
    var addProductDiv = $('#addProductDiv');
    addProductDiv.attr("class","ui basic segment hidden")
    resetProductAdd();
}

function countOrderMoney(money) {
    moneyCount = common.formatFloatDigit(moneyCount, MONEY_DIGIT_2) + common.formatFloatDigit(money, MONEY_DIGIT_2);
    var moneyCountInt = common.formatFloatDigit(moneyCount,MONEY_DIGIT_0);
    var moneyUpper = common.convertCurrency(moneyCountInt);
    $("#moneyCount").val(moneyCountInt);
    $("#moneyCountName").text(moneyCountInt);
    $("#moneyCountUpper").val(moneyUpper);
    $("#moneyCountUpperName").text(moneyUpper);
}
function addProductParamList(prodParam){
    productCount++;
    prodParamList.push(prodParam);
}

function orderAdd(){
    var chkResult = checkProdAddParams();
    if(!chkResult){
        return;
    }
    if(!checkProdCount()){
        return;
    }

    //货单信息
    var customerId = $("#customerId").val();
    var customerPhone = $("#phone").val();
    var deliveryAddress = $("#address").val();
    var deliverDate = $("#createDateEnd").val();
    var paymentType = $("#paymentType").find("option:selected").text();
    var deliverType = $("#deliverType").find("option:selected").text();
    var deliveryCarNo = $("#deliveryCarNo").val();
    var deliveryPhone = $("#deliveryPhone").val();
    var userId = $("#userId").val();
    var salesId = $("#salesId").val();
    var moneyCountUpper = $("#moneyCountUpper").val();
    var moneyCount = $("#moneyCount").val();

    var finaldeliverType = deliverType + "/" + deliveryCarNo;
    if(deliverType == DELIVER_TYPE_SELF){
        finaldeliverType = deliveryCarNo;
    }

    var param=[];
    param.push({'name':'id','value':$("#primaryKey").val()});
    param.push({'name':'customerId','value':customerId});
    param.push({'name':'customerPhone','value':customerPhone});
    param.push({'name':'deliveryAddress','value':deliveryAddress});
    param.push({'name':'deliverDate','value':deliverDate});
    param.push({'name':'paymentType','value':paymentType});
    param.push({'name':'deliverType','value':finaldeliverType});
    param.push({'name':'deliveryPhone','value':deliveryPhone});
    param.push({'name':'moneyCountUpper','value':moneyCountUpper});
    param.push({'name':'moneyCount','value':moneyCount});
    param.push({'name':'userId','value':userId});
    param.push({'name':'salesId','value':salesId});

    //产品信息
    param.push({'name':'prodParamList','value': JSON.stringify(prodParamList)});

    function cb(data, textStatus) {
        $("#waiting").hide();
        if(data.status){
            $('#success').show();
            $("#primaryKey").val(data.data.id);
        }else{
            $('#failure').show();
        }

    }
    api_request('../../' + OAUTH_ORDER_ADD, param, cb, null, true, null);
    $("#waiting").show();
}

function hideInfo(){
    $('#failure').hide();
}
