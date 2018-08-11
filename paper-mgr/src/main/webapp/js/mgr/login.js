$(function(){
	$("input").keyup(function(e){
		down( e );
	});
	setLastLoginAccount();
	$('input[id=account]').focus();
});
function down( e ){
	var kc=e.which||e.keyCode;
	if (kc == 13)    {
		login();
	}
}

function login(){
	var password=$("#password").val();
	var account=$("#account").val();
	var checkFlag = validParameterCheck(account,password);
	if(!checkFlag){
		return;
	}

	var params={"account":account,"password":md5(password).toUpperCase()};
	function cb(data, textStatus) {
		if(data.status){
			var result = JSON.parse(data.data);
			var user = JSON.parse(result.user);
			var menu = result.mainMenu;

			localStorage.setItem("userId" , user.id);
			localStorage.setItem("account" , user.account);
			localStorage.setItem("menu" , menu);
			localStorage.setItem("name",  user.name);
			localStorage.setItem("phone" , user.phone);
			localStorage.setItem("loginState" , user.state);

	  		window.location.href = basepath + '/common/meau.jsp?_d='+new Date().getTime();
		}else{
			$("#errorOne").attr("class", "ui negative small message");	
			$(".one").show();
			$(".two").hide();
			$(".three").hide();
		}
	}

	api_request('../../paper-mgr' + OAUTH_LOGIN, params, cb, null, true, null);
}

function validParameterCheck(account,password) {
	if (account == "") {
		$("#errorOne").attr("class", "ui negative small message");
		$(".two").show();
		$(".one").hide();
		$(".three").hide();
		return false;
	} else {
		$("#errorOne").attr("class", "ui negative small message d-n");
	}

	if (password == "") {
		$("#errorOne").attr("class", "ui negative small message");
		$(".three").show();
		$(".one").hide();
		$(".two").hide();
		 return false;
	} else {
		$("#errorOne").attr("class", "ui negative small message d-n");
	}

	return true;
}
