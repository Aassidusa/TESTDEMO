	$(function(){
				
		 
	  $('form').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                account: {
                    message: '注册失败',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 16,
                            message: '用户名长度必须在6到18位之间'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '用户名只能包含大写、小写、数字和下划线'
                        }
                    }
                },
               password:{
               	  message: '注册失败',
                          validators: {
                           notEmpty: {
                            message: '密码不能为空'
                           },
                          stringLength: {
                            min: 6,
                            max: 16,
                            message: '密码长度必须在6到18位之间'
                           },
                          regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '密码只能包含大写、小写、数字和下划线'
                         }
                    }
               },
               rpassword:{ 
                 message: '注册失败',
                 
                    validators: {
               identical: {
                      field: 'password',
                      message: '输入密码不一致，请重新输入'
                     }
                     
                    }
               },
               phone:{
               	message:'注册失败',
                validators: {
                       notEmpty: {
                            message: '手机号不能为空'
                        },
                          stringLength: {
                            min: 11,
                            max: 11,
                            message: '请输入正确的手机号'
                        }
                     
                    }
               },
              nickname:{
               	 message: '注册失败',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 16,
                            message: '用户名长度必须在6到18位之间'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '用户名只能包含大写、小写、数字和下划线'
                        }
                    }
              },
                 email: {
                    validators: {
                        notEmpty: {
                            message: '邮箱不能为空'
                        },
                        emailAddress: {
                            message: '邮箱地址格式有误'
                        }
                    }
                }
               
               
            },
        });
	  
	
	  
			});
	
	
	function tijiao(){
		var xb=null;
	   	var account=document.getElementById("account").value;
	   	var phone=document.getElementById("phone").value;
	   	var password=document.getElementById("password").value;
	   	var nickname=document.getElementById("nickname").value;
	   	var realname=document.getElementById("realname").value;
	   	var gender=document.getElementsByName("sex");
	   	for(k in gender){
	   		if(gender[k].checked){
	   			xb=gender[k].value;
	   		}
	   	}
	   	var email=document.getElementById("email").value;
	   	var address=document.getElementById("address").value;
	 
       
	 var jsarray=new Array();
	  jsarray={
				
			    "account":account,
				"phone":phone,
				"password":password,
				"nickname":nickname,
				"realname":realname,
				"gender":xb,
				"email":email,
				"address":address
			   
			};
		$.post(
				"http://localhost:8080/personal/zhuce/insertYH.shtml",
				{'zhuceinfo':JSON.stringify(jsarray)},
				function(data){
				if(data){
					 
					alert("注册成功，跳转登录页面 进行登录");
					  window.location.href = "http://localhost:8080/personal/VIEWS/Login.html";  
				}
				
		});
	   	
	   }