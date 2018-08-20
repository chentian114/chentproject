var OAUTH_LOGIN = '/admin/login.do';

//客户管理
OAUTH_CUSTOMER_LIST="/customer/customerList.do";			//客户列表
OAUTH_CUSTOMER_DELETE="/customer/deleteCustomer.do";	    //删除客户
OAUTH_CUSTOMER_DETAIL="/customer/customerDetail.do";		//客户详情
OAUTH_CUSTOMER_EDIT="/customer/customerAddOrUpdate.do";		//添加或修改客户

//业务员管理
OAUTH_SALESMAN_LIST="/salesman/salesmanList.do";			//业务员列表
OAUTH_SALESMAN_DELETE="/salesman/deleteSalesman.do";	    //删除业务员
OAUTH_SALESMAN_DETAIL="/salesman/salesmanDetail.do";		//业务员详情
OAUTH_SALESMAN_EDIT="/salesman/salesmanAddOrUpdate.do";		//添加或修改业务员

//产品管理
OAUTH_PRODUCT_LIST="/product/productList.do";			    //产品列表
OAUTH_PRODUCT_DELETE="/product/deleteProduct.do";	        //删除产品
OAUTH_PRODUCT_DETAIL="/product/productDetail.do";		    //产品详情
OAUTH_PRODUCT_EDIT="/product/productAddOrUpdate.do";		//添加或修改产品
OAUTH_PRODUCT_ALL="/product/productAll.do";			    	//所有产品
OAUTH_PRODUCT_ATTRS="/product/productAttrs.do";				//产品属性配置

//订单管理
OAUTH_ORDER_ADD="/order/orderAdd.do";						//订单新增
OAUTH_ORDER_DEL="/order/orderDel.do";						//订单删除
OAUTH_ORDER_LIST="/order/orderList.do";						//订单列表
OAUTH_ORDER_DETAIL="/order/orderDetail.do";		    		//订单详情
OAUTH_ORDER_DOWNLOAD="/order/downloadOrder.do";				//订单下载

//用户管理
OAUTH_USER_LIST="/admin/userList.do";						//用户列表
OAUTH_USER_DELETE="/admin/deleteUser.do";	    			//删除用户
OAUTH_USER_DETAIL="/admin/userDetail.do";					//用户详情
OAUTH_USER_EDIT="/admin/userAddOrUpdate.do";				//添加或修改用户


function api_request(name, params, cb, scope, async, el) {
	if (async == null){
		async = true;
	}
     if(el==null){
    	 el = "POST";
     }
	console.log('调用接口:\n%s,\n参数列表:', REQUEST_URL+name, params);
	
	$.ajax( {
		url : REQUEST_URL+name,
		async : async,
		data : params,
		type : el,
		dataType:'json',
		cache : false,
		timeout:70000,
		success : function(data, textStatus) {
			if (data.resultCode == 0001) {
			    return;

			}
			cb.call(scope || window, data, textStatus);
		},
		error:function(xhr){
			// alert("readyState: " + xhr.readyState + "\nstatus: " + xhr.status);
		}
	});
};
