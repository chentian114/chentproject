var id = decodeURI(enCode(getUrlParam("id")));
$(function(){
    getProductInfo(id);
});

function getProductInfo(){
    var param={"id":id};
    function cb(data, textStatus){
        if(data.status){
            var product = data.data;
            $("#prodName").text(product.prodName);
            $("#memo").text(product.memo);
            $("#createTime").text(common.downTimeFormatSeconds(product.createTime));
            $("#updateTime").text(common.downTimeFormatSeconds(product.updateTime));
        }
    }
    api_request('../../' + OAUTH_PRODUCT_DETAIL, param, cb, null, true, null);
}