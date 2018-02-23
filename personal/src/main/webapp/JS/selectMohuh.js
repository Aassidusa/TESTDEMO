$(function(){
	
	$("#chaxun").click(function(){
	
		var shuru=document.getElementById("shuru").value;

		   var jsarray=new Array();
			  jsarray={
						
					    "shuru":shuru,
						
					   
					};
		   
				$.post(
						"http://localhost:8080/personal/write/selectMohu.shtml",
						{'chaxun':JSON.stringify(jsarray)},
						function(data){
							  window.location.href = "http://localhost:8080/personal/VIEWS/showAll.html";  
						
				});
				
				
		   
	});
})