var menu = JSON.parse(localStorage.getItem("menu"));
function loadMenu(){
	if(menu && menu != '[]'){
		for(var i = 0; i < menu.length; i++){
			var m = menu[i] ;
			if(menu[i].show){
				$('#menuList').append(getLiElement(menu[i]));
			}
		}
	}
}
function getLiElement(item) {
	var html ;
	if(item.menuId == 1){
		html  = "<li class='#placeholder_class# active_sub active' id='"+item.menuId+"'>";
	}else{
		html  = "<li class='#placeholder_class#' id='"+item.menuId+"'>";
	}
	html += 	"<a href='" + item.url + "' id='toUrl'>";

	if (item.subMenu != undefined){
		html += 		"<i class='cube icon'></i>" + item.name+ "<i class='angle right icon'></i>";
	}else{
		html += 		"<i class='cube icon'></i>" + item.name+ "";
	}

	html+="</a>";
	if (item.subMenu != undefined){
		if(item.menuId == 1){
			html += "<ul class='sidebar-submenu' style='display: block;' >";
		}else {
			html += "<ul class='sidebar-submenu' >";
		}
		for (var k = 0; k <=  item.subMenu.length-1; k++) {//alert(item.subMenu[k].url);
			if(item.subMenu[k].show){

				html += "<li name='child_menu_li' id='"+item.subMenu[k].menuId+"'>"

				html += "<a href='" + item.subMenu[k].url + "' id='toUrl'><i class='cube icon'></i>" + item.subMenu[k].name  + "</a></li>";
			}
		}
		html += 	"</ul>";
	}

	html += "</li>";

	return html;
}


$('#menuList').on('click', '#toUrl', function(e) {
	  $('#menuList li').removeClass('active');
	  $('#menuList li').removeClass('active_sub');
	  $(this).parent().parent().parent('li').addClass('active');
	  $(this).parent('li').addClass('active_sub');
	  var hrefStr=$(this).attr('href')
	  var loc = hrefStr.substring(hrefStr.lastIndexOf('=')+1, hrefStr.length);
	  if(loc=="#"){
		  
	  }else{
		  if(loc != 42){
			  $('#iframe').attr({
			    'src': $(this).attr('href')
			  });
		  }else {
			  window.open($(this).attr('href'))
		  }
	  }
	 
	  e.preventDefault();
	  return false;
});