/**
 * Created by lidongxu on 2016/11/30.
 */

// 判断浏览器跳转
function browers() {
    if(!+[1,])location.href="temp-ex/browers_tips.html";
}

var loginName = localStorage.getItem("name");
var loginId = localStorage.getItem("id");
var loginPhone = localStorage.getItem("phone");
var loginAccount = localStorage.getItem('account');
var loginRoleType = localStorage.getItem('roleType');


//获取地址栏参数
//若地址栏URL为：abc.html?id=123&url=http://www.maidq.com
//那么，但你用上面的方法去调用：alert(getUrlParam("url"));//'http://www.maidq.com'
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); //匹配目标参数
	if (r != null) return unescape(r[2]);
	return null; //返回参数值
}

function enCode(chineseStr) {
	return escape(chineseStr, 'utf-8');
	if (typeof callback === "function") {
		callback();
	}

}

var common = {
	isNotNull: function(object) {
		if (object == null || object == '' || object == undefined || object == 'undefined') {
			return false;
		}
		return true;
	},
	isNull: function(object) {
		return !common.isNotNull(object);
	},
	getByteLen : function(val) {
		var len = 0;
		for (var i = 0; i < val.length; i++) {
			if (val.charCodeAt(i) > 255)
				len += 2;
			else
				len += 1;
		}
		return len;
	},
	getByteVal : function(val, max) {
		var returnStr = '';
		var curbyte = 0;
		for (var i = 0; i < val.length; i++) {
			if (val.charCodeAt(i) > 255) {
				curbyte += 2;
			} else {
				curbyte += 1;
			}
			if (curbyte > max) {
				break;
			}
			returnStr += val.charAt(i);
		}
		return returnStr;
	},
	cutOutString : function(val, max) {
		var returnStr = '';
		var totalLength = 0;
		if (common.isNotNull(val)) {
			totalLength = common.getByteLen(val);
		}
		if (totalLength > max * 2) {
			returnStr += common.getByteVal(val, max * 2);
			returnStr += '……';
		} else {
			returnStr = val;
		}
		return returnStr;
	},
	longTimeFormat: function(ns) {
		if (ns == null || ns == '' || ns == undefined || ns == 'undefined') {
			return '';
		} else {
			var now = new Date(ns)
			var year = now.getFullYear();
			var month = now.getMonth() + 1;
			var date = now.getDate();
			var hours = now.getHours();
			var minutes = now.getMinutes(); // 获取分钟数(0-59)
			var seconds = now.getSeconds(); // 获取秒数(0-59)

			return year + "-" + (month.toString().length == 1 ? ('0' + month) : month) + "-" + (date.toString().length == 1 ? ('0' + date) : date) + ' ' + (hours.toString().length == 1 ? ('0' + hours) : hours) + ':' + (minutes.toString().length == 1 ? ('0' + minutes) : minutes) + ':' + (seconds.toString().length == 1 ? ('0' + seconds) : seconds);
		}
	},
	longTimeFormatYMDHM: function(ns) {
		if (ns == null || ns == '' || ns == undefined || ns == 'undefined') {
			return '';
		} else {
			var now = new Date(ns)
			var year = now.getFullYear();
			var month = now.getMonth() + 1;
			var date = now.getDate();
			var hours = now.getHours();
			var minutes = now.getMinutes(); // 获取分钟数(0-59)
			//var seconds = now.getSeconds();  // 获取秒数(0-59)

			return year + "-" + (month.toString().length == 1 ? ('0' + month) : month) + "-" + (date.toString().length == 1 ? ('0' + date) : date) + ' ' + (hours.toString().length == 1 ? ('0' + hours) : hours) + ':' + (minutes.toString().length == 1 ? ('0' + minutes) : minutes);
		}
	},
	downTimeFormat: function(ns) {
		if (ns == null || ns == '' || ns == undefined || ns == 'undefined') {
			return '';
		} else {
			var now = new Date(ns)
			var year = now.getFullYear();
			var month = now.getMonth() + 1;
			var date = now.getDate();
			// var hours = now.getHours();
			//  var minutes = now.getMinutes();  // 获取分钟数(0-59)
			//var seconds = now.getSeconds();  // 获取秒数(0-59)

			return year + (month.toString().length == 1 ? ('0' + month) : month) + (date.toString().length == 1 ? ('0' + date) : date);
		}

	},
	
	downTimeFormatDate: function(ns) {
		if (ns == null || ns == '' || ns == undefined || ns == 'undefined') {
			return '';
		} else {
			var now = new Date(ns)
			var year = now.getFullYear();
			var month = now.getMonth() + 1;
			var date = now.getDate();

			return year +"-"+ (month.toString().length == 1 ? ('0' + month) : month) +"-"+(date.toString().length == 1 ? ('0' + date) : date);
		}

	},
	
	downTimeFormatSeconds: function(ns) {
		if (ns == null || ns == '' || ns == undefined || ns == 'undefined') {
			return '';
		} else {
			var now = new Date(ns);
			var year = now.getFullYear();
			var month = now.getMonth() + 1;
			var date = now.getDate();
			var hours = now.getHours();
			var minutes = now.getMinutes();  
			var seconds = now.getSeconds();  
			return year +"-"+ (month.toString().length == 1 ? ('0' + month) : month) 
			     +"-"+(date.toString().length == 1 ? ('0' + date) : date)
			     +" "+(hours.toString().length == 1 ? ('0' + hours) : hours)
			     +":"+ (minutes.toString().length == 1 ? ('0' + minutes) : minutes)
			     +":"+ (seconds.toString().length == 1 ? ('0' + seconds) : seconds);
		}

	},
	
	downFormat: function(ns) {
		if (ns == null || ns == '' || ns == undefined || ns == 'undefined') {
			return '';
		} else {
			var now = new Date(ns)
			var year = now.getFullYear();
			var month = now.getMonth() + 1;
            console.log(year + (month.toString().length == 1 ? ('0' + month) : month));
			//var   date=now.getDate();
			// var hours = now.getHours();
			//  var minutes = now.getMinutes();  // 获取分钟数(0-59)
			//var seconds = now.getSeconds();  // 获取秒数(0-59)
			return year + (month.toString().length == 1 ? ('0' + month) : month);
		}

	},
	formatFloatDigit_2: function(num,digit){
		return Math.round(num*100)/100;  //四舍五入
	},
	convertCurrency: function(money) {
		var cnNums = new Array('零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖');  //汉字的数字
		var cnIntRadice = new Array('', '拾', '佰', '仟'); //基本单位
		var cnIntUnits = new Array('', '万', '亿', '兆');  //对应整数部分扩展单位
		var cnDecUnits = new Array('角', '分', '毫', '厘');//对应小数部分单位
		var cnInteger = '整';//整数金额时后面跟的字符
		var cnIntLast = '元';//整型完以后的单位
		var maxNum = 999999999999999.9999;//最大处理的数字
		var integerNum;//金额整数部分
		var decimalNum;//金额小数部分
		var chineseStr = '';//输出的中文金额字符串
		var parts;//分离金额后用的数组，预定义
		if (money == '') { return ''; }
		money = parseFloat(money);
		if (money >= maxNum) {
			return '';//超出最大处理数字
		}
		if (money == 0) {
			chineseStr = cnNums[0] + cnIntLast + cnInteger;
			return chineseStr;
		}
		money = money.toString();//转换为字符串
		if (money.indexOf('.') == -1) {
			integerNum = money;
			decimalNum = '';
		} else {
			parts = money.split('.');
			integerNum = parts[0];
			decimalNum = parts[1].substr(0, 4);
		}
		if (parseInt(integerNum, 10) > 0) {//获取整型部分转换
			var zeroCount = 0;
			var IntLen = integerNum.length;
			for (var i = 0; i < IntLen; i++) {
				var n = integerNum.substr(i, 1);
				var p = IntLen - i - 1;
				var q = p / 4;
				var m = p % 4;
				if (n == '0') {
					zeroCount++;
				} else {
					if (zeroCount > 0) {
						chineseStr += cnNums[0];
					}
					zeroCount = 0;//归零
					chineseStr += cnNums[parseInt(n)] + cnIntRadice[m];
				}
				if (m == 0 && zeroCount < 4) {
					chineseStr += cnIntUnits[q];
				}
			}
			chineseStr += cnIntLast;
		}
		if (decimalNum != '') {//小数部分
			var decLen = decimalNum.length;
			for (var i = 0; i < decLen; i++) {
				var n = decimalNum.substr(i, 1);
				if (n != '0') {
					chineseStr += cnNums[Number(n)] + cnDecUnits[i];
				}
			}
		}
		if (chineseStr == '') {
			chineseStr += cnNums[0] + cnIntLast + cnInteger;
		} else if (decimalNum == '') {
			chineseStr += cnInteger;
		}
		return chineseStr;
	}
}

function getFieldValue(obj, name) {
	var fieldValue = "";
	if (common.isNotNull(obj)) {
		if (common.isNotNull(obj[name])) {
			fieldValue = obj[name];
		}
	}
	return fieldValue;
}

function setFieldValue(name) {
	var fieldValue = "";
	if (common.isNotNull(name)) {
		fieldValue = name;
	}
	return fieldValue;
}

//隐藏提示框
function tipsHide() {
	$(".alert").hide();
}

function getUnfiedata(data) {
	var str = "";
	if (data == undefined) {
		str = "暂无";
	} else {
		str = data;
	}

	return str;
}

function getUnfiedataEmpty(data) {
	var str = "";
	if (data != undefined&&data!="undefined"&&data!="") {
        str = data;
    }
	return str;
}


function uploadFile(index) {
	$.ajaxFileUpload({
		contentType: "text/html",
		url: '../../file/upload.do',
		secureuri: false,
		fileElementId: "id-input-file-" + index,
		type: 'POST',
		dataType: 'content',
		data: null,
		async: false,
		error: function(data, status, e) {
			alert("上传失败!");
		},
		success: function(data, status) {
			var json = $.parseJSON(data);
			if(json.error=="0"){
				$("#prewImage").attr("src",json.url);
				$("#id-input-url-"+index).val(json.url);
			}else{
				if(common.isNotNull(json.message)){
					alert(json.message);
				} else {
					alert("上传失败!");
				}
			}
		}
	});
}

function Map() {
	this.keys = new Array();
	this.data = new Object();
	this.put = function(key, value) {
		if (this.data[key] == null) {
			this.keys.push(key);
		}
		this.data[key] = value;
	};
	this.get = function(key) {
		return this.data[key];
	};
	this.remove = function(key) {
		this.keys.remove(key);
		this.data[key] = null;
	};
	this.each = function(fn) {
		if (typeof fn != 'function') {
			return;
		}
		var len = this.keys.length;
		for (var i = 0; i < len; i++) {
			var k = this.keys[i];
			fn(k, this.data[k], i);
		}
	};
	this.entrys = function() {
		var len = this.keys.length;
		var entrys = new Array(len);
		for (var i = 0; i < len; i++) {
			entrys[i] = {
				key : this.keys[i],
				value : this.data[i]
			};
		}
		return entrys;
	};
	this.isEmpty = function() {
		return this.keys.length == 0;
	};
	this.size = function() {
		return this.keys.length;
	};
	this.toString = function() {
		var s = "{";
		for (var i = 0; i < this.keys.length; i++, s += ',') {
			var k = this.keys[i];
			s += k + "=" + this.data[k];
		}
		s += "}";
		return s;
	};
}
