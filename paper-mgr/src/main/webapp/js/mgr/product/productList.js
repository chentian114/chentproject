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


//产品列表
function loadPage(page,size){
    $html.empty();
    $htmlPage.empty();
    var param={"name":$("#searchTitle").val(),"page_num":page,"page_size":size};
    function cb(data, textStatus) {
        var content = data.data.results;
        if(data.status){
            var t = "";
            if(common.isNotNull(content)){
                for ( var i = 0; i < content.length; i++) {
                    t+="<tr>";
                    t+="<td><a href='#' onclick='viewDetails("+content[i].id+")' title='"+content[i].prodName+"'>"+content[i].prodName+"</a></td>";
                    t+="<td>"+content[i].memo+"</td>";
                    t+="<td>"+common.downTimeFormatDate(content[i].createTime)+"</td>";
                    t+="<td><div class='button-link red delete'><a href='#' onclick='updateProduct("+content[i].id+")'>修改</a></div>&nbsp;&nbsp;&nbsp;&nbsp;" +
                        "<div class='button-link red delete'><a href='#' onclick='deteleProduct("+content[i].id+")'>删除</a></div>" +
                        "</td>";
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

    api_request('../../' + OAUTH_PRODUCT_LIST, param, cb, null, true, null);
}

function pageselectCallback(page_id, jq) {
    loadPage(page_id, size);
}

function viewDetails(id){
    window.location=basepath+"/jsp/product/productDetail.jsp?id="+id;
}

function search(){
    loadPage(page, size);
}

function deteleProduct(id){
    $("#productId").val("")
    /*删除*/
    //删除提示
    $('#product_delete').modal('show');
    $("#productId").val(id)
}


function deleteConfirm(){
    var id=$("#productId").val();
    $('#product_delete').modal('hide');
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

    api_request('../../' + OAUTH_PRODUCT_DELETE, param, cb, null, true, null);
}


function addProduct(){
    window.location=basepath+"/jsp/product/productAdd.jsp?id=";
}


function updateProduct(id){
    window.location=basepath+"/jsp/product/productEdit.jsp?id="+id;
}
