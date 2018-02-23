  	$(function(){
    	$.ajax({
  		  type: 'POST',
  		  url: "http://localhost:8080/personal/zhuce/HuoQuNickname.shtml",
  		  success: function(data){
  			  var hnickname='';
                hnickname=data.nickname;
                hnickname=hnickname+"  Welcome Back!!"
                $("#hnickname").text(hnickname);
  		  },
  		  dataType: 'json'
      	});
      
  		$('#summernote').summernote({
  			height:550,
  			minHeight:null,
  			maxHeight:null,
  			focus:true
  		});
        $('#tijiao').click(function(){
        	var title=$('#input_title').val();
        	var markupStr = $('#summernote').summernote('code');
        	
        	var jsonarray=new Array();
    		jsonarray={
    			"title":title,
    			
    			"markupStr":markupStr
    			
    		};
        	$.post(
					'http://localhost:8080/personal/write/insertData.shtml',
					{'datalog':JSON.stringify(jsonarray)},
					function(data){
						alert(data);
						if(data){
							alert("成功写入");
							 window.location.href = "http://localhost:8080/personal/VIEWS/home_page.html"; 
						}else{
							alert("失败 重新刷新页面");
							
						}
					}
					);
        });
        
  	})