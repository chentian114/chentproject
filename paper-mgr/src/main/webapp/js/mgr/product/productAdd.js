var id = decodeURI(enCode(getUrlParam("id")));
$(function(){
    loadDetails(id);
});

function loadDetails(id){
    if(!common.isNull(id)){
        var param={"id":id};
        function cb(data, textStatus) {
            if(data.status){
                var product = data.data;
                $("#primaryKey").val(product.id);
                $("#prodName").val(product.prodName);
                $("#memo").text(product.memo);
            }
        }

        api_request('../../' + OAUTH_PRODUCT_DETAIL, param, cb, null, true, null);
    }
}

function productAdd(){
    var prodName=$("#prodName").val();
    var memo=$("#memo").val();

    var chkResult = validParameters(prodName);
    if(!chkResult){
        return;
    }

    var param=[];
    param.push({'name':'id','value':$("#primaryKey").val()});
    param.push({'name':'prodName','value':prodName});
    param.push({'name':'memo','value':memo});


    function cb(data, textStatus) {
        if(data.status){
            $('#success').show();
            $("#primaryKey").val(data.data.id);
        }else{
            $('#failure').show();
        }

    }

    api_request('../../' + OAUTH_PRODUCT_EDIT, param, cb, null, true, null);

}

function validParameters(prodName) {
    var $errorInfo = $("#errorInfo");
    $errorInfo.hide();

    if(prodName.length == 0 ){
        displayNoneError();
        $errorInfo.find("li.1").css("display", "block");
        $errorInfo.show();
        return false;
    }
    if (prodName.length > 45) {
        displayNoneError();
        $errorInfo.find("li.2").css("display", "block");
        $errorInfo.show();
        return false;
    }
    return true;
}

function displayNoneError(){
    var $errorInfo = $("#errorInfo");
    $errorInfo.find("li.1").css("display", "none");
    $errorInfo.find("li.2").css("display", "none");
}


function hideInfo(){
    $('#failure').hide();
}