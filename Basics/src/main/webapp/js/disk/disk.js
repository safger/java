
$.fn.disk = function(options) {
	var defaults = $.extend({
		initAddress: '',    // 初始地址
		changeMenu:true,   //能否选择其他文件夹
		dataName:''        //接收数据的name   
	}, options);
	var opts = $.extend(defaults, options);
	var id = $(this).attr("id");
	this.disk.show(id,options);
};
$.fn.disk.show = function(id,options) {        
	var ht='';  
		ht+='<input type="text"  style="width: 300px;height: 28px" readonly="readonly"  name="'+options.dataName+'" value="" />&nbsp;';
		ht+='<button type="button"  style="padding:5px 10px;font-size:14px" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">选择</button>';
		ht+='<div class="modal fade" id="myModal">';  
		ht+='<div class="modal-dialog">';
		ht+='<div class="modal-content">';
		ht+='<div class="modal-header">';
		ht+='<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
		ht+='  <h4 class="modal-title">选择文件</h4>';
		ht+='</div>'; 
		ht+=' <div class="modal-body" style="border: 0px">';
		ht+='		<div class="disk_all">';
		ht+='			<div id="disk_content">';
		ht+='				<div id="disk_left">';
		ht+='					<p id="disk_path">&nbsp;';
		ht+='						<a href="javascript:void(0);" class="location_show" id="to_root_nav">&nbsp;我的电脑</a>';
		ht+='					</p>';
		ht+='					<div id="content_menu">';
		ht+='						<!-- <a href="javascript:void(0);" id="menu_photo" class="menuleft"></a> <a href="javascript:void(0);" id="menu_word" class="menuleft"></a> <a href="javascript:void(0);" id="menu_music" class="menuleft"></a> <a href="javascript:void(0);" id="menu_movie" class="menuleft"></a> <a href="javascript:void(0);" id="menu_floder" class="menuleft"></a> <a href="javascript:void(0);" id="menu_other" class="menuleft"></a> <a href="javascript:void(0);" id="menu_list"></a> -->';
		ht+='					</div>';
		ht+='					<div id="showContent">';
		ht+='						<a href="javascript:void(0);" id="get_root_a"><img src="/images/disk/workgroup-icon.png" title="我的电脑" style="width:100px; height:100px;" /> </a>';
		ht+='					</div>';
		ht+='				</div>';
		ht+='			</div>';
		ht+='			<div id="disk_bottomline"></div>';
		ht+='		</div>'; 
		ht+='  </div>';
		ht+='  <div class="modal-footer">';
		ht+='    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>';
		ht+='  <!--  <button type="button" class="btn btn-primary">确定</button> -->';
		ht+='  </div>';
		ht+=' </div> ';
		ht+=' </div> ';
		ht+='</div> ';
		
		$("#" + id).html(ht);
		diskOpen(options);
}


function diskOpen(options){
	if(options.initAddress!=null||options.initAddress.length>0){
		getFileDetail(options.initAddress);
	}
	
	//首先将#back-to-top隐藏
	$("#back-to-top").hide();
	//当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失
	$(function () {
		$(window).scroll(function(){
		if ($(window).scrollTop()>100){
		$("#back-to-top").fadeIn(1500);
		}
		else
		{
		$("#back-to-top").fadeOut(1500);
		}
		});
		//当点击跳转链接后，回到页面顶部位置
		$("#back-to-top").click(function(){
		$('body,html').animate({scrollTop:0},500);
		return false;
		});
		});
	//点击视图切换图片
	$("#menu_list").toggle(
        function(){
            $(this).addClass("menu_right");
        },
        function(){
            $(this).removeClass("menu_right");
        }
    );
	$('#toMyShare').click(function(){
		$('#disk_nav_arrow').addClass('arrow_share');
	});
	$('#toMyComputer').click(function(){
		$('#disk_nav_arrow').removeClass('arrow_share');
	});
	//触发ajax内容单击事件.
	$(document).on("click",".clickMe",function(){
		var nowId=$(this).attr('id');
		var nowPath=$('#'+nowId+' input').val();
		getFileDetail(nowPath);
	});
	
	//根据状态栏获取目录目录
	$(document).on("click",".location_show.other",function(){
		//$('#disk_path').html('<a href="javascript:void(0);" class="location_show" id="to_root_nav">我的电脑</a>');
		if(!options.changeMenu){
			return;  
		}  
		getFileDetail($(this).attr("path"));
	});
	 
	$('#get_root_a').click(getRootDetail);
	if(options.changeMenu){
		$(document).on("click","#to_root_nav",getRootDetail);
	}  
	//用tooltip显示文件信息
	//$('#showContent .fileDetail').tooltip();
		
	function getRootDetail(){
		$('#showContent').before('<p id="reload_gif"><img src="/images/disk/load.gif" width="25px"  height="25px"  align="absmiddle"/>&nbsp;&nbsp;正在加载……</p>');
		$.ajax({
			type:"post",
			url:"/system/disk/showRoot.html?date="+new Date(),
			async:true,
			dataType:"JSON",
			success:function(data){
				$('#reload_gif').remove();    
				$('#showContent').html("");
				$('#disk_path').html('<a href="javascript:void(0);" class="location_show" id="to_root_nav">&nbsp;我的电脑</a>');
				$.each(data,function(i,item){
					if(item.diskName=='C'){
						$('#showContent').append('<a href="javascript:void(0);" class="clickMe" id="disk_'+i+'"><p><img src="/images/disk/disk_c.png" class="disk_c_img" align="absmiddle"/>&nbsp;&nbsp;&nbsp;&nbsp;容量：'+item.diskSize+'&nbsp;&nbsp&nbsp;&nbsp;剩余空间：'+item.avilableSize+'<input type="hidden" value='+item.diskPath+'/></p></a>');
					}else{
						$('#showContent').append('<a href="javascript:void(0);" class="clickMe" id="disk_'+i+'"><p ><img src="/images/disk/disk_other.png" class="disk_c_img" align="absmiddle"/>&nbsp;&nbsp;&nbsp;&nbsp;容量：'+item.diskSize+'&nbsp;&nbsp;&nbsp;&nbsp;剩余空间：'+item.avilableSize+'<input type="hidden" value='+item.diskPath+'/></p></a>');
					}
				});
			},
			error:function(data){
				$('#reload_gif').remove();   
			}
		});
	}
	function getFileDetail(nowPath){
		$('#showContent').before('<p id="reload_gif"><img src="/images/disk/load.gif" width="25px"  height="25px"  align="absmiddle"/>&nbsp;&nbsp;正在加载……</p>');
		$.ajax({
			type:"post",
			url:"/system/disk/showDir.html?date="+new Date(),
			data: "path="+nowPath, 
			async:true,
			dataType:"JSON",
			success:function(data){
				$('#reload_gif').remove();   
				$('#disk_path').html('<a href="javascript:void(0);" class="location_show" id="to_root_nav">&nbsp;我的电脑&nbsp;&nbsp;</a>');
				$('#showContent').html("");
					if(data==null){
						return;
					}
					var pp=data.dirPath;   
					var pa=pp.split("\\");
					var ht='';
					var path="";   
					for(var a=0;a<pa.length;a++){
						if(pa[a].length>0){
							ht='<a href="javascript:void(0);" name="pa'+a+'" class="location_show other">'+pa[a]+'\\</a>';
							$('#disk_path').append(ht);
							path+=pa[a]+"\\";   
							$("a[name='pa"+a+"']").attr("path",path);
						}
					}    
					
					$.each(data.files,function(n,file){ 
						if(file.fileType=='DIR'){
							$('#showContent').append('<div class="fileDetail"><a href="javascript:void(0);" class="clickMe" id="file_'+n+'"><img src="/images/disk/floder.png" title="'+file.fileName+'"/><span>'+file.fileName+'</span><input type="hidden" value='+file.filePath+'/></a></div>');
						}
						else if(file.fileType=='TXT'||file.fileType=='JAVA'||file.fileType=='PDF'){
							$('#showContent').append('<div class="fileDetail"><a href="javascript:void(0);"><img name="dirfile" src="/images/disk/txt.png" fileName="'+file.fileName+'" title="大小：'+file.fileSize+'&#10;'+file.fileName+'"/><span>'+file.fileName+'</span></a></div>');
						}
						else if(file.fileType=='MP3'||file.fileType=='WMA'||file.fileType=='WAV'||file.fileType=='MOD'){
							$('#showContent').append('<div class="fileDetail"><a href="javascript:void(0);"><img  name="dirfile" src="/images/disk/music.png" fileName="'+file.fileName+'" title="大小：'+file.fileSize+'&#10;'+file.fileName+'"/><span>'+file.fileName+'</span></a></div>');
						}
						else if(file.fileType=='HTML'||file.fileType=='HTM'||file.fileType=='JSP'){
							$('#showContent').append('<div class="fileDetail"><a href="javascript:void(0);"><img  name="dirfile" src="/images/disk/html.png" fileName="'+file.fileName+'" title="大小：'+file.fileSize+'&#10;'+file.fileName+'"/><span>'+file.fileName+'</span></a></div>');
						}
						else if(file.fileType=='RAR'||file.fileType=='JAR'||file.fileType=='ZIP'){
							$('#showContent').append('<div class="fileDetail"><a href="javascript:void(0);"><img  name="dirfile" src="/images/disk/zip.png" fileName="'+file.fileName+'" title="大小：'+file.fileSize+'&#10;'+file.fileName+'"/><span>'+file.fileName+'</span></a></div>');
						}
						else if(file.fileType=='DOC'||file.fileType=='WPS'||file.fileType=='DOCX'){
							$('#showContent').append('<div class="fileDetail"><a href="javascript:void(0);"><img  name="dirfile"  src="/images/disk/word.png" fileName="'+file.fileName+'" title="大小：'+file.fileSize+'&#10;'+file.fileName+'"/><span>'+file.fileName+'</span></a></div>');
						}
						else if(file.fileType=='XLS'){
							$('#showContent').append('<div class="fileDetail"><a href="javascript:void(0);"><img name="dirfile" src="/images/disk/excel.png" fileName="'+file.fileName+'" title="大小：'+file.fileSize+'&#10;'+file.fileName+'"/><span>'+file.fileName+'</span></a></div>');
						}
						else if(file.fileType=='PPT'){
							$('#showContent').append('<div class="fileDetail"><a href="javascript:void(0);"><img  name="dirfile" src="/images/disk/ppt.png" fileName="'+file.fileName+'" title="大小：'+file.fileSize+'&#10;'+file.fileName+'"/><span>'+file.fileName+'</span></a></div>');
						}
						else if(file.fileType=='EXE'||file.fileType=='BAT'){
							$('#showContent').append('<div class="fileDetail"><a href="javascript:void(0);"><img  name="dirfile" src="/images/disk/exe.png" fileName="'+file.fileName+'" title="大小：'+file.fileSize+'&#10;'+file.fileName+'"/><span>'+file.fileName+'</span></a></div>');
						}
						else if(file.fileType=='BMP'||file.fileType=='PNG'||file.fileType=='GIF'||file.fileType=='JPEG'||file.fileType=='JPG'){
							$('#showContent').append('<div class="fileDetail"><a href="javascript:void(0);"><img  name="dirfile" src="/images/disk/photo.png" fileName="'+file.fileName+'" title="大小：'+file.fileSize+'&#10;'+file.fileName+'"/><span>'+file.fileName+'</span></a></div>');
						}
						else if(file.fileType=='RMVB'||file.fileType=='MKV'||file.fileType=='MP4'||file.fileType=='AVI'||file.fileType=='WMV'||file.fileType=='3GP'){
							$('#showContent').append('<div class="fileDetail"><a href="javascript:void(0);"><img  name="dirfile" src="/images/disk/movie.png" fileName="'+file.fileName+'" title="大小：'+file.fileSize+'&#10;'+file.fileName+'"/><span>'+file.fileName+'</span></a></div>');
						}
						else{
							$('#showContent').append('<div class="fileDetail"><a href="javascript:void(0);"><img  name="dirfile" src="/images/disk/config.png" fileName="'+file.fileName+'" title="大小：'+file.fileSize+'&#10;'+file.fileName+'"/><span>'+file.fileName+'</span></a></div>');
						}
					});
					$("img[name='dirfile']").click(function(){
						$("input[name='"+options.dataName+"']").val(pp+$(this).attr("fileName"));
						$('#myModal').modal("toggle");  
					});	   
			},
			error:function(data){ 
				$('#reload_gif').remove();   
			}
		});
	}
}
 