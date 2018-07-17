var id = decodeURI(enCode(getUrlParam("id")));
$(function(){
    getSalesmanInfo(id);
});

function getSalesmanInfo(){
    var param={"id":id};
    function cb(data, textStatus){
        if(data.status){
            var salesman = data.data;

            $("#salesName").text(salesman.salesName);
            $("#address").text(salesman.address);
            if(salesman.type === 1){
                $("#salesType").text("业务员");
            }else if(salesman.type === 2){
                $("#salesType").text("业务经理");
            }else{
                $("#salesType").text("其它");
            }
            $("#phone").text(salesman.phone);
            if(salesman.sex === 'M'){
                $("#sex").text("男");
            }else if(salesman.sex ==='F'){
                $("#sex").text("女");
            }else{
                $("#sex").text("其它");
            }
            $("#email").text(salesman.email);
            $("#memo").text(salesman.memo);
            $("#createTime").text(common.downTimeFormatSeconds(salesman.createTime));
            $("#updateTime").text(common.downTimeFormatSeconds(salesman.updateTime));
        }
    }
    api_request('../../' + OAUTH_SALESMAN_DETAIL, param, cb, null, true, null);
}