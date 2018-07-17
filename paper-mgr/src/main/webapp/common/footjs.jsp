    <!--loading JS-->
    <script type="text/javascript" src="<%=basePath %>/js/jquery.1.9.1.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>/js/jquery.validate.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>/js/jquery.validate.common_rules.js"></script>
    <script type="text/javascript" src="<%=basePath %>/js/semantic/semantic.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>/js/echarts.js"></script>
    <script type="text/javascript" src="<%=basePath %>/js/semantic/sidebar-menu.js"></script>
    <script type="text/javascript" src="<%=basePath %>/js/jquery.pagination.mine.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>/js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=basePath %>/js/common.js"></script>
    <script type="text/javascript" src="<%=basePath %>/js/global.js"></script>
    <script type="text/javascript" src="<%=basePath %>/js/mgr/api.js"></script>

    <script>
    var basepath = "<%=basePath %>";
    /*退出登录*/
    //退出登录提示//
    $('#exit').click(function(){  //点击按钮
        $('#login_out_modal').modal('show');  //找到并显示窗口//
    });
    
	/**
	 * 说明：此处是调用ajax方法时，判断session是否过期 
	 *  
	 * 注：如果不想让ajax方法受这个影响，可以在ajax方法中写： global:false 
	 * 如下： 
	 * $.ajax({ 
	 *    url:"test.html", 
	 *    global:false    //不触发全局ajax事件 
	 * }) 
	 *  
	 */  	  
	$(document).ajaxComplete(function(event, xhr, settings) {
	    if(xhr.getResponseHeader("sessionstatus") == "timeOut"){   
	        $('#userCancel').modal('show');  //找到并显示窗口// 
	    }
	}); 
    
    function loginOut(){
        var url = basepath + "/admin/logout.do";
		$.post(url,"",function(data){
			localStorage.clear();
			top.window.location.href=basepath;
		},'json');
    }
</script>