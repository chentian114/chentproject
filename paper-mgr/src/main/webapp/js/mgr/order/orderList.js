/**
 * Created by Administrator on 2018/5/11.
 */
var t="",page="1",size="10",$html=$("#listData"),$htmlPage=$("#listPage");
$(function(){
    loadPage(page,size);
    $("input").keyup(function(e){
        down( e );
    });

});


function down( e ){
    var kc=e.which||e.keyCode;
    if (kc == 13)    {
        loadPage(page,size);
    }
}


//货单列表
function loadPage(page,size){
    $html.empty();
    $htmlPage.empty();
    var param={"customerName":$("#searchTitle").val(),"page_num":page,"page_size":size,"orderNumber":$("#searchOrderNum").val(),"customerPhone":$("#searchPhone").val(),"salesName":$("#searchSalesman").val()};
    function cb(data) {
        var content = data.data.results;
        if(data.status){
            var t = "";
            if(common.isNull(content)) {
                $html.append("<tr><td>暂无数据</td><tr>");
            }else{
                for ( var i = 0; i < content.length; i++) {
                    t+="<tr>";
                    t+="<td><a href='#' onclick='viewDetails("+content[i].id+")' title='"+content[i].orderNumber+"'>"+content[i].orderNumber+"</a></td>";
                    var custName = content[i].customerEntity.custName.substring(0,10);
                    if(content[i].customerEntity.custName.length>20){
                        t+="<td title='"+content[i].customerEntity.custName+"'>"+custName+"...</td>";
                    }else{
                        t+="<td>"+content[i].customerEntity.custName+"</td>";
                    }

                    t+="<td>"+content[i].customerPhone+"</td>";
                    var address = content[i].deliveryAddress.substring(0,20);
                    if(content[i].deliveryAddress.length>20){
                        t+="<td title='"+content[i].deliveryAddress+"'>"+address+"...</td>";
                    }else{
                        t+="<td>"+content[i].deliveryAddress+"</td>";
                    }
                    t+="<td>"+content[i].salesmanEntity.salesName+"</td>";
                    t+="<td>"+common.downTimeFormatDate(content[i].deliverDate)+"</td>";
                    t+="<td>"+content[i].moneyCount+"</td>";

                    t+="<td><div class='button-link red delete'><a href='#' onclick='downOrder("+content[i].id+")'>下载货单</a></div></td>";
                    t+="</tr>";
                }
                $html.append(t);
            }
            $("#totalData").text(data.data.totalElements)
            $htmlPage.pagination(data.data.totalElements,{
                callback: pageselectCallback,
                prev_text: '上一页',
                next_text: '下一页 ',
                items_per_page: size,
                num_display_entries: 4,
                current_page: page,
                start_page_num:1,
                num_edge_entries: 1
            });
        }

    }

    api_request('../../' + OAUTH_ORDER_LIST, param, cb, null, true, null);
}

function pageselectCallback(page_id, jq) {
    loadPage(page_id, size);
}

function viewDetails(id){
    window.location=basepath+"/jsp/order/orderDetail.jsp?id="+id;
}

function search(){
    loadPage(page, size);
}

function addOrder(){
    window.location=basepath+"/jsp/order/orderAdd.jsp?id=&customerId=";
}

function downOrder(id){
    var url = basepath+"/order/downloadOrder.do?id="+id;
    var eleIF = document.createElement("iframe");
    eleIF.src = url;
    eleIF.style.display = "none";
    document.body.appendChild(eleIF);
}