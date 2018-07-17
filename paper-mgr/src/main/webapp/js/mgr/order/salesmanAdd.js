var id = decodeURI(enCode(getUrlParam("id")));
$(function(){
    loadDetails(id);
});

function loadDetails(id){
    if(!common.isNull(id)){
        var param={"id":id};
        function cb(data, textStatus) {
            if(data.status){
                var salesman = data.data;
                $("#primaryKey").val(salesman.id);
                $("#salesName").val(salesman.salesName);
                $("#address").val(salesman.address);
                $("#custType").val(salesman.type);
                $("#phone").val(salesman.phone);
                $("#email").val(salesman.email);
                $("input[name='sex'][value='"+salesman.sex+"']").attr("checked", true);
                $("#memo").text(salesman.memo);
            }
        }

        api_request('../../' + OAUTH_SALESMAN_DETAIL, param, cb, null, true, null);
    }
}

function salesmanAdd(){
    var salesName=$("#salesName").val();
    var address=$("#address").val();
    var salesType=$("#salesType").val();
    var phone=$("#phone").val();
    var sex = $("input[name='sex']:checked").val();
    var email = $('#email').val();
    var memo = $('#memo').val();

    var chkResult = validParameters(salesName,address,phone);
    if(!chkResult){
        return;
    }

    var param=[];
    param.push({'name':'id','value':$("#primaryKey").val()});
    param.push({'name':'salesName','value':salesName});
    param.push({'name':'address','value':address});
    param.push({'name':'type','value':salesType});
    param.push({'name':'phone','value':phone});
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

    api_request('../../' + OAUTH_SALESMAN_EDIT, param, cb, null, true, null);

}

function validParameters(salesName,address,phone) {
    var $errorInfo = $("#errorInfo");
    $errorInfo.hide();

    if(salesName.length == 0 ){
        displayNoneError();
        $errorInfo.find("li.1").css("display", "block");
        $errorInfo.show();
        return false;
    }
    if (salesName.length > 45) {
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
    if (phone.length > 11) {
        displayNoneError();
        $errorInfo.find("li.6").css("display", "block");
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