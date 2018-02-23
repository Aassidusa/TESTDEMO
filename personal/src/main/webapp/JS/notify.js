	$(function(){
				
		$.ajax({
			  type: 'POST',
			  url: "http://localhost:8080/personal/zhuce/HuoQuInfo.shtml",
			  success: function(data){
				 var account=document.getElementById("account");
				 var phone=document.getElementById("phone");
				 var nickname=document.getElementById("nickname");
				 var realname=document.getElementById("realname");
				 var email=document.getElementById("email");
				 var address=document.getElementById("address");
				 var sex=data.sex;
				 if(sex=="男"){
					 $("#sex_m").attr("checked","chencked");
				 }else{
					 $("#sex_w").attr("checked","chencked");
				 }
				 account.value=data.account;
				 phone.value=data.phone;
				 nickname.value=data.nickname;
				 realname.value=data.realname;
				 email.value=data.email;
				 address.value=data.adress;
			
			  },
			  dataType: 'json'
	    	});
	    	
			
			
			
	  $('form').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                account: {
                    message: '查询失败',
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
           
          
               phone:{
               	message:'查询失败',
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
               	 message: '查询失败',
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
	  
	  $("#xiugai").click(function(){
		  
		 var account=document.getElementById("account").value;
		 var phone=document.getElementById("phone").value;
		 var nickname=document.getElementById("nickname").value;
		 var realname=document.getElementById("realname").value;
		 var email=document.getElementById("email").value;
		 var address=document.getElementById("address").value;
			var gender=document.getElementsByName("sex");
			
			var xb='';
		   	for(k in gender){
		   		if(gender[k].checked){
		   			xb=gender[k].value;
		   		}
		   	}
		  
			 var jsarray=new Array();
			  jsarray={
						
					    "account":account,
						"phone":phone,
					
						"nickname":nickname,
						"realname":realname,
						"gender":xb,
						"email":email,
						"address":address
					   
					};
				$.post(
						"http://localhost:8080/personal/zhuce/notifyInfo.shtml",
						{'xiugai':JSON.stringify(jsarray)},
						function(data){
					alert("修改成功 返回首页");
					  window.location.href = "http://localhost:8080/personal/VIEWS/home_page.html";  
						
				});
		  
		  
	  });
			});