
var REQUEST_URL = './';

if(typeof localStorage === 'undefined') {
	//alert('需要运行在支持HTML5的浏览器');
	localStorage = {
		setItem : function(n, v) {
			var o = {};
			try {o = JSON.parse(window.name);}catch(e){}
			o[n] = v;
			window.name = JSON.stringify(o);
		},
		getItem : function(n) {
			var o = {};
			try {o = JSON.parse(window.name);}catch(e){};
			return o[n];
		},
		clear : function() {
			window.name = '';
		}
	};
}

	

function urlParam(n) {
	var url = location.href;
	url = url.replace('#', '');// for a tag's href="#", by Ouyj 2012.4.28
	var paraString = url.substring(url.indexOf('?') + 1, url.length).split('&');
	var paraObj = {};
	var j = true;
	for(var i = 0; j = paraString[i]; i++) {
		paraObj[j.substring(0, j.indexOf('=')).toLowerCase()] = j.substring(j.indexOf('=') + 1, j.length);
	}
	var returnValue = paraObj[n.toLowerCase()];
	if( typeof (returnValue) == 'undefined') {
		return null;
	} else {
		return decodeURIComponent(returnValue);
	}
};
