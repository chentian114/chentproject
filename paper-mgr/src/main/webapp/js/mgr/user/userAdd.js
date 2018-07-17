var id = decodeURI(enCode(getUrlParam("id")));
$(function(){
    loadDetails(id);
});

function loadDetails(id){
    if(!common.isNull(id)){
        var param={"id":id};
        function cb(data, textStatus) {
            if(data.status){
                var user = data.data;
                $("#primaryKey").val(user.id);
                $("#account").val(user.account);
                $("#name").val(user.name);
                $("#phone").val(user.phone);
                $("#roleName").text(user.role.name);
                $("#roleId").val(user.role.id);
                $("#description").text(user.description);
            }
        }

        api_request('../../' + OAUTH_USER_DETAIL, param, cb, null, true, null);
    }
}

function userAdd(){
    var account=$("#account").val();
    var password = $("#password").val();
    var rePassword = $("#rePassword").val();
    var name=$("#name").val();
    var phone=$("#phone").val();
    var roleId=$("#roleId").val();
    var description=$("#description").val();

    var chkResult = validParameters(account,password,rePassword,name);
    if(!chkResult){
        return;
    }

    var param=[];
    param.push({'name':'id','value':$("#primaryKey").val()});
    param.push({'name':'account','value':account});
    param.push({'name':'password','value': md5(password).toUpperCase()});
    param.push({'name':'name','value':name});
    param.push({'name':'phone','value':phone});
    param.push({'name':'roleId','value':roleId});
    param.push({'name':'createId','value':localStorage.getItem("userId")});
    param.push({'name':'description','value':description});


    function cb(data, textStatus) {
        if(data.status){
            $('#success').show();
            $("#primaryKey").val(data.data.id);
        }else{
            $('#failure').show();
        }

    }

    api_request('../../' + OAUTH_USER_EDIT, param, cb, null, true, null);

}

function validParameters(account,password,rePassword,name) {
    var errorInfo = $("#errorInfo");
    errorInfo.hide();
    var errli = $("#errInfoList");
    errli.empty();
    var flag =true;

    if(account.length == 0 ){
        flag = false;
        errli.append("<li class='1'>用户账号不能为空；</li>");
    }
    if (account.length > 10) {
        flag = false;
        errli.append("<li class='2'>用户账号字数超出限制；最多10个；</li>");
    }

    var reg1 = new RegExp(/^[0-9A-Za-z]+$/);
    if (!reg1.test(account)) {
        flag = false;
        errli.append("<li class='3'>用户账号只能是字母与数字；</li>");
    }

    if(password != rePassword){
        flag = false;
        errli.append("<li class='4'>两次输入密码不一致；</li>");
    }

    if(name.length == 0 ){
        flag = false;
        errli.append("<li class='5'>用户名称不能为空；</li>");
    }
    if (name.length > 10) {
        flag = false;
        errli.append("<li class='6'>用户名称字数超出限制；最多10个；</li>");
    }

    if(!flag){
        errorInfo.show();
    }
    return flag;
}




function hideInfo(){
    $('#failure').hide();
}