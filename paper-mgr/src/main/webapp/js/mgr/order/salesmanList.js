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


//业务员列表
function loadPage(page,size){
    $html.empty();
    $htmlPage.empty();
    var param={
        "sales_name":$("#searchSalesName").val(),
        "page_num":page,
        "page_size":size,
        "phone":$("#searchPhone").val()
    };
    function cb(data, textStatus) {
        var content = data.data.results;
        if(data.status){
            var t = "";
            if(common.isNotNull(content)){
                for ( var i = 0; i < content.length; i++) {
                    t+="<tr>";
                    t+="<td><a href='#' onclick='viewDetails("+content[i].id+")' title='"+content[i].salesName+"'>"+content[i].salesName+"</a></td>";
                    if(content[i].type === 1){
                        t+="<td>业务员</td>";
                    }else if(content[i].type === 2){
                        t+="<td>业务经理</td>";
                    }else{
                        t+="<td>其他</td>";
                    }
                    t+="<td>"+content[i].phone+"</td>";
                    var address = content[i].address.substring(0,20);
                    if(content[i].address.length>20){
                        t+="<td title='"+address+"'>"+address+"...</td>";
                    }else{
                        t+="<td>"+content[i].address+"</td>";
                    }
                    t+="<td><div class='button-link red delete'><a href='#' onclick='updateSalesman("+content[i].id+")'>修改</a></div>&nbsp;&nbsp;&nbsp;&nbsp;" +
                        "<div class='button-link red delete'><a href='#' onclick='deteleSalesman("+content[i].id+")'>删除</a></div>" +
                        "<div class='button-link red delete'><a href='#' onclick='addCustomer("+content[i].id+")'>添加客户</a></div></td>";

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

    api_request('../../' + OAUTH_SALESMAN_LIST, param, cb, null, true, null);
}

function pageselectCallback(page_id, jq) {
    loadPage(page_id, size);
}

function viewDetails(id){
    window.location=basepath+"/jsp/order/salesmanDetail.jsp?id="+id;
}

function search(){
    loadPage(page, size);
}

function deteleSalesman(id){
    $("#salesId").val("")
    /*删除*/
    //删除提示
    $('#salesman_delete').modal('show');
    $("#salesId").val(id)
}


function deleteConfirm(){
    var id=$("#salesId").val();
    $('#salesman_delete').modal('hide');
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

    api_request('../../' + OAUTH_SALESMAN_DELETE, param, cb, null, true, null);
}


function addSalesman(){
    window.location=basepath+"/jsp/order/salesmanAdd.jsp?id=";
}


function updateSalesman(id){
    window.location=basepath+"/jsp/order/salesmanEdit.jsp?id="+id;
}


function addCustomer(salesId){
    window.location=basepath+"/jsp/order/customerAdd.jsp?id=&salesId="+salesId;
}