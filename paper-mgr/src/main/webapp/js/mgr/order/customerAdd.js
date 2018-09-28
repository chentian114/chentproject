var id = decodeURI(enCode(getUrlParam("id")));
var salesId = decodeURI(enCode(getUrlParam("salesId")));
$(function(){
    loadDetails(id);
});

function loadPartSalesman(salesId,querySalesId){
    var page=1;
    var size=50;
    var param={
        "page_num":page,
        "page_size":size
    };
    var salesIdHtml = $("#salesId");

    function cb(data, textStatus) {
        var content = data.data.results;
        if (data.status) {
            if (common.isNotNull(content)) {
                for (var i = 0; i < content.length; i++) {
                    salesIdHtml.append("<option value="+content[i].id+">"+content[i].salesName+"</option>");
                }

                if(!common.isNull(querySalesId)){
                    $("#salesId").val(querySalesId);
                }else if(!common.isNull(salesId)){
                    $("#salesId").val(salesId);
                }

            }
        }
    }

    api_request('../../' + OAUTH_SALESMAN_LIST, param, cb, null, true, null);

}

function loadDetails(id){
    if(!common.isNull(id)){
        var param={"id":id,"salesId":salesId};
        function cb(data, textStatus) {
            if(data.status){
                var customer = data.data;
                $("#primaryKey").val(customer.id);
                $("#custName").val(customer.custName);
                $("#address").val(customer.address);
                $("#custType").val(customer.type);
                $("#phone").val(customer.phone);
                $("#salesId").val(customer.salesId);
                $("#deliveryAddress").val(customer.deliveryAddress);
                $("input[name='sex'][value='"+customer.sex+"']").attr("checked", true);
                $("#email").val(customer.email);
                $("#memo").text(customer.memo);


                loadPartSalesman(customer.salesId,customer.querySalesId);
            }
        }

        api_request('../../' + OAUTH_CUSTOMER_DETAIL, param, cb, null, true, null);
    }else{
        loadPartSalesman(salesId);
    }
}

function customerAdd(){
    var custName=$("#custName").val();
    var address=$("#address").val();
    var custType=$("#custType").val();
    var phone=$("#phone").val();
    var salesId =  $("#salesId").find("option:selected").val();
    var deliveryAddress = $('#deliveryAddress').val();
    var sex = $("input[name='sex']:checked").val();
    var email = $('#email').val();
    var memo = $('#memo').val();

    var chkResult = validParameters(custName,address,phone);
    if(!chkResult){
        return;
    }

    var param=[];
    param.push({'name':'id','value':$("#primaryKey").val()});
    param.push({'name':'custName','value':custName});
    param.push({'name':'address','value':address});
    param.push({'name':'type','value':custType});
    param.push({'name':'phone','value':phone});
    param.push({'name':'salesId','value':salesId});
    param.push({'name':'deliveryAddress','value':deliveryAddress});
    param.push({'name':'sex','value':sex});
    param.push({'name':'email','value':email});
    param.push({'name':'memo','value':memo});

    function cb(data, textStatus) {
        if(data.status){
            $('#success').show();
            $("#primaryKey").val(data.data.id);
        }else{
            $('#failure').show();
        }

    }

    api_request('../../' + OAUTH_CUSTOMER_EDIT, param, cb, null, true, null);

}

function validParameters(custName,address,phone) {
    var $errorInfo = $("#errorInfo");
    $errorInfo.hide();

    if(custName.length == 0 ){
        displayNoneError();
        $errorInfo.find("li.1").css("display", "block");
        $errorInfo.show();
        return false;
    }
    if (custName.length > 45) {
        displayNoneError();
        $errorInfo.find("li.2").css("display", "block");
        $errorInfo.show();
        return false;
    }

    if(address.length == 0 ){
        displayNoneError();
        $errorInfo.find("li.3").css("display", "block");
        $errorInfo.show();
        return false;
    }
    if (address.length > 1500) {
        displayNoneError();
        $errorInfo.find("li.4").css("display", "block");
        $errorInfo.show();
        return false;
    }

    if(phone.length == 0 ){
        displayNoneError();
        $errorInfo.find("li.5").css("display", "block");
        $errorInfo.show();
        return false;
    }
    return true;
}

function displayNoneError(){
    var $errorInfo = $("#errorInfo");
    $errorInfo.find("li.1").css("display", "none");
    $errorInfo.find("li.2").css("display", "none");
    $errorInfo.find("li.3").css("display", "none");
    $errorInfo.find("li.4").css("display", "none");
    $errorInfo.find("li.5").css("display", "none");
    $errorInfo.find("li.6").css("display", "none");
}


function hideInfo(){
    $('#failure').hide();
}

function selectSalesman(){
    window.location=basepath+"/jsp/order/salesmanListByCustomer.jsp?customerId="+id;
}