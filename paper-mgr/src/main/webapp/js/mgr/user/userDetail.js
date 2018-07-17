var id = decodeURI(enCode(getUrlParam("id")));
$(function(){
    getUserInfo();
});

function getUserInfo(){
    var param={"id":id};
    function cb(data, textStatus){
        if(data.status){
            var user = data.data;
            $("#account").text(user.account);
            $("#name").text(user.name);
            $("#phone").text(user.phone);
            $("#description").text(user.description);
            $("#role").text(user.role.name);
            $("#createTime").text(common.downTimeFormatSeconds(user.createTime));
            $("#updateTime").text(common.downTimeFormatSeconds(user.updateTime));
        }
    }
    api_request('../../' + OAUTH_USER_DETAIL, param, cb, null, true, null);
}