var id = decodeURI(enCode(getUrlParam("id")));
$(function(){
    getCustomerInfo(id);
});

function getCustomerInfo(){
    var param={"id":id};
    function cb(data, textStatus){
        if(data.status){
            var customer = data.data;

            $("#custName").text(customer.custName);
            $("#address").text(customer.address);
            if(customer.type == 'P'){
                $("#custType").text("批发");
            }else if(customer.type =='L'){
                $("#custType").text("零售");
            }else{
                $("#custType").text("其它");
            }
            $("#deliveryAddress").text(customer.deliveryAddress);
            $("#phone").text(customer.phone);
            $("#salesName").text(customer.salesmanEntity.salesName);
            if(customer.sex === 'M'){
                $("#sex").text("男");
            }else if(customer.sex ==='F'){
                $("#sex").text("女");
            }else{
                $("#sex").text("其它");
            }
            $("#email").text(customer.email);
            $("#memo").text(customer.memo);
            $("#createTime").text(common.downTimeFormatSeconds(customer.createTime));
            $("#updateTime").text(common.downTimeFormatSeconds(customer.updateTime));
        }
    }
    api_request('../../' + OAUTH_CUSTOMER_DETAIL, param, cb, null, true, null);
}