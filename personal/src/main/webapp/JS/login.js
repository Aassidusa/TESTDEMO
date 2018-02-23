			$(function(){
				
				$(window).resize();
	  $('form').bootstrapValidator({
            message: 'This value is not valid',
           
            fields: {
                username: {
                    message: '用户名验证失败',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        }
                    }
                },
               password:{
               	  message: '用户名验证失败',
                    validators: {
                        notEmpty: {
                            message: '密码不能为空，请输入密码'
                        }
                    }
               }
            }
        });
			});
			$(window).resize(function(){
				$("#login_main").css({
					position:"absolute",
					 left: ($(window).width() - $("#login_main").outerWidth())/2, 
                     top: ($(window).height() - $("#login_main").outerHeight())/2 
				});
		   	
			});
		
		
   function yanzhen(){
   	var username=document.getElementById("userName").value;
   	var password=document.getElementById("userPassword").value;
   	var jsarray=new Array();

	  jsarray={
				
			    "username":username,
				"password":password
			   
			};
	
		$.ajax({
			  type: 'POST',
			  url: "http://localhost:8080/personal/zhuce/yanzheng.shtml",
			  data: {'yanzheng':JSON.stringify(jsarray)},
			  success: function(data){
               alert(data.cuowu);
               alert(data.chongfu);
               if(data.cuowu=="no"){
            	   alert("密码或者帐号错误");
               }else{
            	   if(data.chongfu=="ok"){
                	   alert("已经登录");
                	   
                   }else if(data.chongfu=="yes"){
                	   alert("已经在其他地方登录 是否强制登录 ");
                	   window.location.href = "http://localhost:8080/personal/VIEWS/home_page.html"; 
                   }else{
                	   alert("成功登录");
                	   window.location.href = "http://localhost:8080/personal/VIEWS/home_page.html"; 
                	   
                   }
               }
            
				
				
			  },
			  dataType: 'json'
			});
   	
   	
   }