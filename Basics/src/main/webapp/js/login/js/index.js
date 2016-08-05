jQuery(document).ready(function($) {
	$("#username").attr("value","");
	$("#password").attr("value","");
	$("#username").focus(function (){
		hs=true;
	})
	$("#password").focus(function (){
		hs=true;
	})
	// If firefox
	if(navigator.userAgent.toLowerCase().match(/firefox/)) {
		$('.browser-warning').removeClass('hidden');
		setTimeout(function() {
			$('.browser-warning').addClass('hidden');
		}, 6*1000);
	}
	
	$('#window').attr('style', '');
	var cookie_check=$.cookie('login_cookie_checked');
	if(cookie_check){
		$("#jzmm").attr("checked","checked")
	}
	initAnimation();
	
	

	function initAnimation() {
		var isT=true;
		var cookie_username=$.cookie('login_cookie_username');
		var cookie_userpwd=$.cookie('login_cookie_password');
		if(cookie_username!=null&&cookie_username!=""){
			$("#username").attr("data-fyll",cookie_username);
			isT=false;
		}
		if(cookie_userpwd!=null&&cookie_userpwd!=""){
			$("#password").attr("data-fyll",cookie_userpwd);
			isT=false;
		} 
		if(!isT&&$("#jzmm").attr("checked")){
			gofl();
		}
		
	} 
	function gofl(){
		setTimeout(function() {
			fyll.go('fill username then fill password then click submit', function() {
				$('#submit').addClass('loading');
				setTimeout(function() {
					$('#submit').addClass('done').closest('#window').addClass('flip');
					$("#submit").click();
				}, 1500);
			});
		}, 3*1000);
	}
	function resetAnimation() {
		var win = $('#window');

		win.stop().fadeOut(500, function() {

			// Reset things
			win.attr('style', '');
			win.find('input[type=text], input[type=password]').val('');
			win.find('.load-btn.loading').removeClass('loading done');

			// Clone and re-create window element to trigger animation restart
			win.removeClass('flip');
			win.before(win.clone(true)).remove();

			// Restart animation
			initAnimation();
		});
	}

});