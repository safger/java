$.fn.AutoPage = function(options) {
	var defaults = $.extend({
		totalPage : 0, // 总条数
		currentPage: 1,// 当前页
		pageSize: 10,// 每页显示数
		showNum : 6,  // 显示数字
		show:1,       //显示类型 1默认显示 0只显示上一页下一页 
		url:'javascript:void(0)'
	}, options);
	var opts = $.extend(defaults, options);
	var id = $(this).attr("id");
	this.AutoPage.form(id,options);
	this.AutoPage.show(id,options);
};
$.fn.AutoPage.show = function(id,options) {
	if(options.show==null){
		options.show=1;
	}
	var div=$("#"+id);
	var url=options.url;
	var index=parseInt(options.totalPage/options.pageSize);
	if(options.totalPage%options.pageSize!=0){
		index+=1;
	}
	var content='<div class="span12">';
	content+='<div class="dataTables_info" id="DataTables_Table_0_info">';
	if(options.currentPage==index){
		content+="第"+((options.currentPage-1)*options.pageSize+1)+"到"+options.totalPage+"条数据  共计 "+options.totalPage+"条";
	}else{
		content+="第"+((options.currentPage-1)*options.pageSize+1)+"到"+((options.currentPage)*options.pageSize)+"条数据  共计 "+options.totalPage+"条";
	}
	content+='</div>';
	content+='</div>';
	
	content+='<div class="span12 center">';
	content+='<div class="dataTables_paginate paging_bootstrap pagination">';
	content+='<ul>';
	if(options.currentPage==1){
		content+='<li class="prev disabled"><a href="#">← 上一页</a></li>';
	}else{
		content+='<li class="prev"><a href="javascript:AutoSubmit('+(parseInt(options.currentPage)-1)+','+index+')">← 上一页</a></li>';
	}
	
	if(index<=options.showNum&&options.show==1){
		for(var a=1;a<index+1;a++){
			//content+="<a href='javascript:AutoSubmit("+(a)+","+index+")'>"+a+"</a>";
			if(a==options.currentPage){
				content+='<li class="active"><a href="javascript:void(0)">'+a+'</a></li>';
			}else{
				content+='<li ><a href="javascript:AutoSubmit('+(a)+','+index+')">'+a+'</a></li>';
			}
			
		}
	}
	if(index>options.showNum&&options.show==1){
		if(options.currentPage<=options.showNum){
			for(var a=1;a<options.showNum+1;a++){
				content+="<li ><a href='javascript:AutoSubmit("+(a)+","+index+")')>"+a+"</a></li>";
			}
		}else{  
			var t=parseInt((index)/options.showNum);                       
			var j=parseInt(options.currentPage/options.showNum);     
			if(options.currentPage%options.showNum==0){
				j-=1;
			}
			if(j>=1&&j<t){
				for(var a=(parseInt(options.showNum)*j+1);a<(parseInt(options.showNum)*(j+1)+1);a++){
					content+="<li><a href='javascript:AutoSubmit("+(a)+","+index+")')>"+a+"</a></li>";
				}
			}else{ 
				for(var a=(parseInt(options.showNum)*j+1);a<=index;a++){
					content+="<li><a href='javascript:AutoSubmit("+(a)+","+index+")')>"+a+"</a></li>";
				}
			}
		}
	}
	if(index==options.currentPage){
		content+="<li class='next disabled'><a href='javascript:AutoSubmit("+(parseInt(options.currentPage)+1)+","+index+")'>下一页 →</a></li>";
	}else{
		content+="<li class='next'><a href='javascript:AutoSubmit("+(parseInt(options.currentPage)+1)+","+index+")'>下一页 →</a></li>";
		
	}
	content+='</ul>';
	content+='</div>';
	content+='</div>';
	
	
	div.append(content);
	
	$('#cur').keypress(function (e) {  
	    var key = e.which;  
	    if (key == 13) {
	    	AutoSure(index);
	    }
	});
};
$.fn.AutoPage.form = function(id,options) {
	var content="<div><form id='Autoform' method='post' action='"+options.url+"' >";
	content+="<input type='hidden' name='indexPage' value='"+options.currentPage+"'>";
	content+="</form></div>";
	$("#"+id).append(content);
};

function AutoSubmit(currentPage,index){
	if(currentPage>index){
		return;
	}
	if(currentPage<1){
		return;
	}
	$("input[name='indexPage']").attr("value",currentPage);
	$("#Autoform").submit();
}
function AutoSure(index){
	var currentPage=$("#cur").val();
	if(currentPage>index){
		return;
	}
	if(isNaN(currentPage)){
		return;
	}
	AutoSubmit(currentPage,index);
}