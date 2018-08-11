var id = decodeURI(enCode(getUrlParam("id")));

$(function(){
    loadDetails(id);

});

function loadDetails(id){
    if(common.isNull(id)){
        return;
    }
    var param = {"id":id};
    function cb(data, textStatus) {
        if(data.status){
            var content = data.data;
            if(common.isNull(content)){
                return;
            }

            var order = data.data;
            $("#orderNumber").text(order.orderNumber);
            $("#customerName").text(order.customerEntity.custName);
            $("#phone").text(order.customerPhone);
            $("#address").text(order.deliveryAddress);
            $("#createDateEnd").text(common.downTimeFormatDate(order.deliverDate));
            $("#paymentType").text(order.paymentType);
            $("#deliverType").text(order.deliverType);
            $("#deliveryPhone").text(order.deliveryPhone);


            var productListHtml = $("#productList");

            for (var i = 0; i < order.orderProductList.length; i++) {
                var prod = order.orderProductList[i];
                productListHtml.append("<tr>");
                productListHtml.append("<td class='two wide'>"+prod.prodName+"</td>");
                productListHtml.append("<td class='one wide'>"+prod.gweight+"</td>");
                productListHtml.append("<td class='two wide'>"+prod.spec+"</td>");
                productListHtml.append("<td class='one wide'>"+prod.amount+"</td>");
                productListHtml.append("<td class='one wide'>"+prod.unit+"</td>");
                productListHtml.append("<td class='one wide'>"+prod.unitPrice+"</td>");
                productListHtml.append("<td class='one wide'>"+prod.money+"</td>");
                productListHtml.append("<td class='two wide' style='word-break:break-all;'>"+prod.memo+"</td>");
                productListHtml.append("<td class='two wide' style='word-break:break-all;'>"+prod.weightmemo+"</td>");
                productListHtml.append("</tr>");
            }

            $("#moneyCountUpperName").text(order.moneyCountUpper);
            $("#moneyCountName").text(order.moneyCount);
            $("#userName").text(order.user.name);
            $("#salesName").text(order.salesmanEntity.salesName);
            $("#createTime").text(common.downTimeFormatSeconds(order.createTime));
        }
    }
    api_request('../../' + OAUTH_ORDER_DETAIL, param, cb, null, true, null);
}

function downOrder(){
    var url = basepath+"/order/downloadOrder.do?id="+id;
    var eleIF = document.createElement("iframe");
    eleIF.src = url;
    eleIF.style.display = "none";
    document.body.appendChild(eleIF);
}