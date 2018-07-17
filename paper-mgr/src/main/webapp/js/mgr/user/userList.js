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
    var param={"name":$("#searchTitle").val(),"account":$("#searchAccount").val(),"page_num":page,"page_size":size};
    function cb(data, textStatus) {
        var content = data.data.results;
        if(data.status){
            var t = "";
            if(common.isNotNull(content)){
                for ( var i = 0; i < content.length; i++) {
                    t+="<tr>";
                    t+="<td><a href='#' onclick='viewDetails("+content[i].id+")' title='"+content[i].account+"'>"+content[i].account+"</a></td>";
                    t+="<td>"+content[i].name+"</td>";
                    t+="<td>"+content[i].phone+"</td>";
                    t+="<td>"+content[i].description+"</td>";
                    t+="<td>"+common.downTimeFormatDate(content[i].createTime)+"</td>";
                    t+="<td><div class='button-link red delete'><a href='#' onclick='updateUser("+content[i].id+")'>修改</a></div>&nbsp;&nbsp;&nbsp;&nbsp;" +
                        "<div class='button-link red delete'><a href='#' onclick='deteleUser("+content[i].id+")'>删除</a></div>" +
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

    api_request('../../' + OAUTH_USER_LIST, param, cb, null, true, null);
}

function pageselectCallback(page_id, jq) {
    loadPage(page_id, size);
}

function viewDetails(id){
    window.location=basepath+"/jsp/user/userDetail.jsp?id="+id;
}

function search(){
    loadPage(page, size);
}

function deteleUser(id){
    $("#userId").val("")
    /*删除*/
    //删除提示
    $('#user_delete').modal('show');
    $("#userId").val(id)
}


function deleteConfirm(){
    var id=$("#userId").val();
    $('#user_delete').modal('hide');
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

    api_request('../../' + OAUTH_USER_DELETE, param, cb, null, true, null);
}


function addUser(){
    window.location=basepath+"/jsp/user/userAdd.jsp?id=";
}


function updateUser(id){
    window.location=basepath+"/jsp/user/userEdit.jsp?id="+id;
}
