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

function selectPaymentType(paymentType){
    if(paymentType== -1){
        $("#paymentTypeSelfDiv").attr("class","three wide field show");
    }else{
        $("#paymentTypeSelfDiv").attr("class","hidden");
    }
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
var MONEY_DIGIT_2=2;             //金额精确到分
var PROD_MAX_COUNT = 15;        //添加产品数量最多为15个

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
    $("#unitPrice").val(""); //产品修改，单价重置
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
    var errorInfo = $("#errorInfo2");
    errorInfo.hide();
    var errli = $("#errInfoList2");
    errli.empty();
    var flag =true;

    if(getRealProductCount()<1 ){
        flag = false;
        errli.append("<li class='14'>暂未添加货单记录；</li>");
    }

    if(getRealProductCount()>PROD_MAX_COUNT ){
        flag = false;
        errli.append("<li class='15'>最多添加"+PROD_MAX_COUNT+"个货单记录；</li>");
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

    var weightmemo = $("#weightmemo").val();
    weightmemo = weightmemo.trim();
    weightmemo = weightmemo.replace(/^,+|,+$/gm, "");  //去掉前后,
    weightmemo = weightmemo.replace(/^\.+|\.+$/gm, "");  //去掉前后.
    weightmemo = weightmemo.replace(/^,+|,+$/gm, "");  //去掉前后,  两遍为了处理清除所有
    weightmemo = weightmemo.replace(/^\.+|\.+$/gm, "");  //去掉前后.

    var weightmemoCount = 0;
    if(weightmemo != ""){
        var wgArrays = weightmemo.split(".");
        for(var pointIndex=0;pointIndex< wgArrays.length; pointIndex++){
            var wgArrays2 = wgArrays[pointIndex].split(",");

            for(var commaIndex=0;commaIndex<wgArrays2.length; commaIndex++){
                if(isNaN(wgArrays2[commaIndex])){
                    flag = false;
                    errli.append("<li class='17'>重量备注有误[分隔符只能为(,或.)其余为数字]请检查；</li>");
                    break;
                }
                weightmemoCount = weightmemoCount+ parseInt(wgArrays2[commaIndex]);
            }

            if(!flag){
                break;
            }
        }
        if(flag){
            if(weightmemoCount != amount){
                flag = false;
                errli.append("<li class='18'>重量备注计算结果"+weightmemoCount+"与数量"+amount+"不一致，请检查；</li>");
            }
        }
    }

    if(!flag){
        errorInfo.show();
    }
    return flag;
}

function checkProdAddParams(){
    var errorInfo = $("#errorInfo2");
    errorInfo.hide();
    var errli = $("#errInfoList2");
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

  /*  var deliveryCarNo = $("#deliveryCarNo").val();
    if(deliveryCarNo.length == 0 ){
        flag = false;
        errli.append("<li class='3'>送货车牌号不能为空；</li>");
    }*/

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
    var specType = $("#specType").val().trim();
    var amount = $("#amount").val().trim();
    var unitPrice = $("#unitPrice").val().trim();
    var gweight = $("#gweight").val().trim();
    var spec = $("#spec").val().trim();

    if(gweight == PRODUCT_ATTR_SELFDEFINE){
        gweight = $("#gweightSelf").val().trim();
    }
    if(spec == PRODUCT_ATTR_SELFDEFINE){
        spec = $("#specSelf").val().trim();
    }

    var money = parseFloat(0.0);
    if(specType == SPEC_TYPE_AREA){ //长*宽
        gweight = parseFloat(gweight);
        gweight = gweight/1000;
        spec = spec.trim();
        var specArrays = spec.split("*");
        var specNum1 = parseFloat(specArrays[0].trim());
        var specNum2 = parseFloat(specArrays[1].trim());
        var specCount = specNum1 * specNum2 ;
        specCount = specCount/1000000;
        unitPrice = unitPrice/1000;
        money = gweight * specCount * amount * unitPrice;
    }else if(specType == SPEC_TYPE_WIDE){//宽幅
        amount = amount/1000;
        money = amount * unitPrice;
    }
    var money = common.formatFloatDigit_2(money);
    $("#money").val(money);
    $("#moneyName").text(money);
}

var productCount = 0;           //合计产品总数
var productCountOffset = 0 ;    //每次修改后+1，(productCount-productCountOffset)求得真实添加的产品数量
var moneyCount = parseFloat(0.00) ;  //合计金额总数
var prodParamList = [];         //添加产品列表
var DELIVER_TYPE_SELF = 4;      //送货方式自定义常量
function addProduct(){
    if(getRealProductCount() == PROD_MAX_COUNT){
        alert("一个货单最多添加"+PROD_MAX_COUNT+"个产品!");
        return;
    }
    var addProductDiv = $('#addProductDiv');
    addProductDiv.attr("class","ui basic segment show");
}

//获取真实的产品数量
function getRealProductCount(){
    return productCount-productCountOffset;
}

function resetProductAdd(){
   // $("#productId option:first").prop("selected","selected");
   // selectProdName($("#productId").val());
   //克重和规格不重置
   // $("#gweight option:first").prop("selected","selected");
   // $("#specType option:first").prop("selected","selected");
   // $("#spec option:first").prop("selected","selected");
   // selectgweight($("#gweight").val());
   // selectSpecType($("#specType").val());
    $("#moneyName").text("");
    $("#amount").val("");
  //  $("#unitPrice").val("");
    $("#memo").val("");
    $("#weightmemo").val("");
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
    var weightmemo = $("#weightmemo").val();

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

    var prodParam = {
        'productCount':productCount,
        'productId': productId,
        'prodName':finalprodName,
        'gweight':finalgweight,
        'specType':specType,
        'spec':finalspec,
        'amount':amount,
        'unit':unit,
        'unitPrice':unitPrice,
        'money':money,
        'memo':memo,
        'weightmemo':weightmemo
    };

    var productListHtml = $("#productList");
    productListHtml.append('<tr>');
    productListHtml.append('<input type="hidden" id=productPara'+productCount+' value='+parseParam(prodParam)+' />');
    productListHtml.append("<td class='one wide' onclick=updateProd("+productCount+","+spec+","+gweight+") name=prod"+productCount+"><a href='#'>修改</a></td>");
    productListHtml.append("<td class='two wide' name=prod"+productCount+">"+finalprodName+"</td>");
    productListHtml.append("<td class='one wide' name=prod"+productCount+">"+finalgweight+"</td>");
    productListHtml.append("<td class='two wide' name=prod"+productCount+">"+finalspec+"</td>");
    productListHtml.append("<td class='one wide' name=prod"+productCount+">"+amount+"</td>");
    productListHtml.append("<td class='one wide' name=prod"+productCount+">"+unit+"</td>");
    productListHtml.append("<td class='one wide' name=prod"+productCount+">"+unitPrice+"</td>");
    productListHtml.append("<td class='one wide' name=prod"+productCount+">"+money+"</td>");
    productListHtml.append("<td class='two wide' style='word-break:break-all;' name=prod"+productCount+">"+memo+"</td>");
    productListHtml.append("<td class='two wide' style='word-break:break-all;' name=prod"+productCount+">"+weightmemo+"</td>");
    productListHtml.append('</tr>');
    productCancel();
    countOrderMoney(money);

    addProductParamList(prodParam);

}

var parseParam=function(paramObj, key){
    var paramStr="";
    if(paramObj instanceof String||paramObj instanceof Number||paramObj instanceof Boolean){
        paramStr+="&"+key+"="+encodeURIComponent(paramObj);
    }else{
        $.each(paramObj,function(i){
                   var k=key==null?i:key+(paramObj instanceof Array?"["+i+"]":"."+i);
                   paramStr+='&'+parseParam(this, k);
        });
    }
    return paramStr.substr(1);
};
function getUrlParamValue(requestParas,name) {
     var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
     var r = requestParas.match(reg);
     if (r != null) return decodeURIComponent(r[2]);
     return null;
 }


//修改产品
function updateProd(prodNum,spec,gweight){
    productCountOffset++;   //修改后数量+
    var productPara = $("#productPara"+prodNum).val();

    var productId = getUrlParamValue(productPara,"productId");
    var finalgweight = getUrlParamValue(productPara,"gweight");
    var specType = getUrlParamValue(productPara,"specType");
    var finalspec = getUrlParamValue(productPara,"spec");
    var amount = getUrlParamValue(productPara,"amount");
    var unitPrice = getUrlParamValue(productPara, "unitPrice");
    var money = getUrlParamValue(productPara,"money");
    var memo = getUrlParamValue(productPara,"memo");
    var weightmemo = getUrlParamValue(productPara,"weightmemo");

    //自定义参数处理
    if(gweight == PRODUCT_ATTR_SELFDEFINE){
        finalgweight = gweight;
        $("#gweightSelfDiv").attr("class","show");
    }
    if(spec == PRODUCT_ATTR_SELFDEFINE){
        finalspec = spec;
        $("#specSelfDiv").attr("class","show");
    }
    if(productId == PRODUCT_ID_SELFDEFINE){
        $("#prodNameSelfDiv").attr("class","show");
    }

    $("#productId").val(productId);
    $("#gweight").val(finalgweight);
    $("#specType").val(specType);
    $("#spec").val(finalspec);
    $("#amount").val(amount);
    $("#unitPrice").val(unitPrice);
    $("#money").val(money);
    $("#memo").val(memo);
    $("#weightmemo").val(weightmemo);
    var addProductDiv = $('#addProductDiv');
    addProductDiv.attr("class","ui basic segment show");

    //总价减于产品价格
    countSubOrderMoney(money);
    //删除产品显示
    $("td[name=prod"+prodNum+"]").remove();
    //删除已添加的产品列表中对象
    delProductParamListByProductCount(prodNum);
}
function productCancel(){
    var addProductDiv = $('#addProductDiv');
    addProductDiv.attr("class","ui basic segment hidden")
    resetProductAdd();
}

function countOrderMoney(money) {
    moneyCount = moneyCount + parseFloat(money);
    var moneyCountDigit =  common.formatFloatDigit_2(moneyCount);
    if(parseFloat(moneyCountDigit) == "NaN"){
        moneyCount = 0;
    }
    var moneyUpper = common.convertCurrency(moneyCountDigit);
    $("#moneyCount").val(moneyCountDigit);
    $("#moneyCountName").text(moneyCountDigit);
    $("#moneyCountUpper").val(moneyUpper);
    $("#moneyCountUpperName").text(moneyUpper);
}

function countSubOrderMoney(money) {
    moneyCount = moneyCount - parseFloat(money);
    var moneyCountDigit =  common.formatFloatDigit_2(moneyCount);
    if(parseFloat(moneyCountDigit) == "NaN"){
        moneyCount = 0;
    }
    var moneyUpper = common.convertCurrency(moneyCountDigit);
    $("#moneyCount").val(moneyCountDigit);
    $("#moneyCountName").text(moneyCountDigit);
    $("#moneyCountUpper").val(moneyUpper);
    $("#moneyCountUpperName").text(moneyUpper);
}

function addProductParamList(prodParam){
    productCount++;
    prodParamList.push(prodParam);
}

//根据添加产品序号删除已添加的产品列表中对象
function delProductParamListByProductCount(productCount){
    var delIndex = -1;
    for(var index in prodParamList){
        var prod = prodParamList[index] ;
        for(var paramName in prod){
            if(paramName == "productCount" && prod[paramName] == productCount ){
                delIndex = index ;
                break;
            }
        }
        if(delIndex != -1){
            break;
        }
    }
    if(delIndex!=-1){
        prodParamList.splice(delIndex,1);
    }
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
    if(paymentType == "自定义"){
        paymentType = $("#paymentTypeSelf").val();
    }
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
