
$(document).ready(function() {

	
	var $post_example = $('#post_example');
	
	
	$('#submit_it2').click(function(e) {
		
		e.preventDefault(); 
		
		var jsObj = $post_example.serializeObject()
			, ajaxObj = {};
		
		
		ajaxObj = {  
			type: "POST",
			url: "http://localhost:7001/com.genc/api/v1/update/new", 
			data: JSON.stringify(jsObj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
			},
			success: function(data) { 
				if(data[0].HTTP_CODE == 200) {
					$('#div_ajaxResponse').text( data[0].MSG );
				}
			},
			complete: function(XMLHttpRequest) {
			}, 
			dataType: "json" 
		};
		
		$.ajax(ajaxObj);
		
	});
	
});
