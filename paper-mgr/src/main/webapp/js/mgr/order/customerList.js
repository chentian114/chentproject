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


//客户列表
function loadPage(page,size){
    $html.empty();
    $htmlPage.empty();
    var param={"name":$("#searchTitle").val(),"page_num":page,"page_size":size,"address":$("#searchAddress").val(),"phone":$("#searchPhone").val(),"sales_name":$("#searchSalesman").val()};
    function cb(data, textStatus) {
        var content = data.data.results;
        if(data.status){
            var t = "";
            if(common.isNotNull(content)){
                for ( var i = 0; i < content.length; i++) {
                    t+="<tr>";
                    t+="<td><a href='#' onclick='viewDetails("+content[i].id+")' title='"+content[i].custName+"'>"+content[i].custName+"</a></td>";
                    var address = content[i].address.substring(0,20);
                    if(content[i].address.length>20){
                        t+="<td title='"+address+"'>"+address+"...</td>";
                    }else{
                        t+="<td>"+content[i].address+"</td>";
                    }
                    t+="<td>"+content[i].phone+"</td>";
                    if(content[i].type === 'P'){
                        t+="<td>批发</td>";
                    }else if(content[i].type === 'L'){
                        t+="<td>零售</td>";
                    }else{
                        t+="<td>其他</td>";
                    }
                    t+="<td>"+content[i].salesmanEntity.salesName+"</td>";
                    t+="<td><div class='button-link red delete'><a href='#' onclick='updateCustomer("+content[i].id+")'>修改</a></div>&nbsp;&nbsp;&nbsp;&nbsp;" +
                        "<div class='button-link red delete'><a href='#' onclick='deteleCustomer("+content[i].id+")'>删除</a></div>" +
                        "<div class='button-link red delete'><a href='#' onclick='addOrder("+content[i].id+")'>录入货单</a></div></td>";

                    t+="</tr>";
                }

                $html.append(t);
            }else{
                $html.append("<tr><td>暂无数据</td><tr>");
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

    api_request('../../' + OAUTH_CUSTOMER_LIST, param, cb, null, true, null);
}

function pageselectCallback(page_id, jq) {
    loadPage(page_id, size);
}

function viewDetails(id){
    window.location=basepath+"/jsp/order/customerDetail.jsp?id="+id;
}

function search(){
    loadPage(page, size);
}

function deteleCustomer(id){
    $("#customerId").val("")
    /*删除*/
    //删除提示
    $('#customer_delete').modal('show');
    $("#customerId").val(id)
}


function deleteConfirm(){
    var id=$("#customerId").val();
    $('#customer_delete').modal('hide');
    var param = {
        "id": id
    };

    function cb(data, textStatus) {
        if (data.status) {
            $('#success').modal('show');
            setTimeout(function() {
                loadPage(page,size);
            }, 1000);
        } else {
            $('#failure').modal('show');
        }
    }

    api_request('../../' + OAUTH_CUSTOMER_DELETE, param, cb, null, true, null);
}


function addCustomer(){
    window.location=basepath+"/jsp/order/customerAdd.jsp?id=&salesId=";
}


function updateCustomer(id){
    window.location=basepath+"/jsp/order/customerEdit.jsp?id="+id+"&salesId=";
}

function addOrder(id){
    window.location=basepath+"/jsp/order/orderAdd.jsp?id=&customerId="+id;
}