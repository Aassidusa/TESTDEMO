  $(function(){
	    $('.container').hide();
    	$('body').addClass('container ');
        $('.l-wrapper').remove();
        $('.container').show();
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
    
         var htmlstr='';
    	$.ajax({
		  type: 'POST',
		  url: "http://localhost:8080/personal/zhuce/HuoQuHomeLog.shtml",
		  success: function(data){
		
			  if(data[0].showlog=='no'){
				  $('.col-lg-4').hide();
		
				  $('.jumbotron').append("<h1>你还没写任何内容</h1>");
				  
			  }else{
				  $.each(data,function(n,value){
					  if(n==0){}else{
						  htmlstr=value.strhtml;
						  qhtmlstr=value.strhtml;
							 htmlstr=htmlstr.replace(/<[^>]+>/g, "");
							 htmlstr=htmlstr.replace(/[ ]|[&nbsp;]/g, ''); 
							 var shortstr='';
							  if(htmlstr.length>100){
								  shortstr=htmlstr.substring(0,100)+"...";
			  				   }else{
			  					 shortstr=htmlstr; 
			  				   }
							
					  }
				
						 if(data.length==4){
								if(n==1){
									  $("#h2head1").text(value.title);
									  $("#phead1").text(shortstr);
									  $("#modal-body1").html( qhtmlstr);
									  $("#modal-title1").text(value.title);
								}else if(n==2){
									 $("#h2head2").text(value.title);
									  $("#phead2").text(shortstr);
									  $("#modal-body2").html( qhtmlstr);
									  $("#modal-title2").text(value.title);
								}else if(n==3){
									 $("#h2head3").text(value.title);
									  $("#phead3").text(shortstr);
									  $("#modal-body3").html( qhtmlstr);
									  $("#modal-title3").text(value.title);
								}  
						 } else if(data.length==3){
								if(n==1){
									  $("#h2head1").text(value.title);
									  $("#phead1").text(shortstr);
									  $("#modal-body1").html( qhtmlstr);
									  $("#modal-title1").text(value.title);
								}else if(n==2){
									 $("#h2head2").text(value.title);
									  $("#phead2").text(shortstr);
									  $("#modal-body2").html( qhtmlstr);
									  $("#modal-title2").text(value.title);
									  $("#col-3").hide();
								}else if(n==3){
									 $("#col-3").hide();
									 
								}  
						 }else if(data.length==2){
								if(n==1){
									  $("#h2head1").text(value.title);
									  $("#phead1").text(shortstr);
									  $("#modal-body1").html( qhtmlstr);
									  $("#modal-title1").text(value.title);
									  $("#col-2").hide();
									  $("#col-3").hide();
								} 
						 }else{
							 $('.col-lg-4').hide();
						 }
		  
		  
					
					
				  });
			  }
		
		  },
		  dataType: 'json'
    	});
    	
    })