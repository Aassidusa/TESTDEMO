$(function(){
	$("#out").click(function(){
		$.post(
				"http://localhost:8080/personal/zhuce/loginOut.shtml",
				
				function(data){
					 
					alert(" 退出成功，跳转登录页面 进行重新登录");
					  window.location.href = "http://localhost:8080/personal/VIEWS/Login.html"; 
				
		});
	});
})