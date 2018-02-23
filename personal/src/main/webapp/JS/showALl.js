   $(function(){
	   $('.container').hide();
   	$('body').addClass('container ');
       $('.l-wrapper').remove();
       $('.container').show();
	  	$.ajax({
	  		  type: 'POST',
	  		  url: "http://localhost:8080/personal/write/showAll.shtml",
	  		  success: function(data){
	  			  var sd=1;
	  			var title='';
	  			var htmlstr='';
	  			$.each(data,function(n,value){
	  				
	  				if(value.title==undefined){
	  					var hnickname='';
		                  hnickname=value.nickname;
		                  hnickname=hnickname+"  Welcome Back!!"
		                  $("#hnickname").text(hnickname);
	  				}else{
	  					
	  					 title=value.title;
	  					 htmlstr=value.strhtml;
	  					 htmlstr=htmlstr.replace(/<[^>]+>/g, "");
	  					 htmlstr=htmlstr.replace(/[ ]|[&nbsp;]/g, ''); 
	  					 
	  					var shortstr='';
	  				   if(htmlstr.length>100){
	  					   shortstr=htmlstr.substring(0,100)+"...";
	  				   }	
	  			
	  				    var h3s="jumbotronh3_"+sd;
	  				   var pp="jumbotronPdata_"+sd;
	  				   var shanchu="<button class=\"btnss\">delete</button>"
	  				   var ssd="<h2 style=\" display: none;\">"+htmlstr+"</h2>"
	  					 $('#masthead').append( 
	  					"<div class=\"jumbotron\"><h3 id="+h3s+">"+title+"</h3><p class="+pp+">"+shortstr+"</p>"+ "<a class=\"btn btn-primary\"    role=\"button\">查看全部 &raquo;"+ssd+"</a>"+shanchu+"</div>"	 
	  					 );
	  				
	  					 sd++;
	  					 
	  				}
	  			
	  				
	  				
	  				

	  			});
	  		
	  			 
	  			
	  		  },
	  		  dataType: 'json'
	      	});
	   
	  	
	  	
	  	
	   var shortstr='';
	   var str='';
	   if(str.length>200){
		   shortstr=str.substring(0,200)+"...";
	   }
	   
	  var i=1;
	   var middle='';
	   $('#masthead').on("click",".btn-primary",function(){
		   var sd=$(this).parent().find("h2");
		  var sdd=$(this).parent().find("p");
		  var ssd=$(this).parent().find("a");
		   var str=sd.text();
		   var shot=sdd.text();
		   if(str>shot){
			   middle=shot;
		   }
		 
		
		   ssd.click(function(){
			
			     if(i==0&&shot.length<str.length){
			    	
			    	 sdd.text(str);
	                 i=0;
	                 middle=shot;
	            }else  {
	            	sdd.text(middle);
	                 i=0;
	            }

		   });
		   
	   });
	 
	   
	   $("#masthead").on("click",".btnss",function(){
		   var btn=$(this).parent();
		   var sd=$(this).parent().find("h3");
		   var title=sd.text();
		  
		   var jsarray=new Array();
			  jsarray={
						
					    "title":title,
						
					   
					};
		   
				$.post(
						"http://localhost:8080/personal/write/delete.shtml",
						{'shanchu':JSON.stringify(jsarray)},
						function(data){
						if(data){
							
						}
						
				});
		   
		   btn.remove();
	   });
	

   });