/**
 * js file for post.html
 * Please use modern web browser as this file will not attempt to be
 * compatible with older browsers. Use Chrome and open javascript console
 * or Firefox with developer console.
 * 
 * jquery is required
 */
$(document).ready(function() {
	
	getInventory();
	
	$(document.body).on('click', ':button, .DELETE_BTN', function(e) {
		//console.log(this);
		var $this = $(this)
			, id = $this.val()
			, obj = {id : id}
			, $tr = $this.closest('tr')
			, id = $tr.find('.id').text();
		
		deleteInventory(obj, id);
	});
});

function deleteInventory(obj, code) {
	
	ajaxObj = {  
			type: "DELETE",
			url: "http://localhost:7001/com.genc/api/v1/delete/" + code,
			data: JSON.stringify(obj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) {
				//console.log(data);
				$('#delete_response').text( data[0].MSG );
			},
			complete: function(XMLHttpRequest) {
				getInventory();
			}, 
			dataType: "json"
		};
		
	return $.ajax(ajaxObj);
}

function getInventory() {
	
	var d = new Date()
		, n = d.getTime();
	
	ajaxObj = {  
			type: "GET",
			url: "http://localhost:7001/com.genc/api/v1/getproduct", 
			data: "ts="+n,
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) { 
				//console.log(data);
				var html_string = "";
				
				$.each(data, function(index1, val1) {
					//console.log(val1);
					html_string = html_string + templateGetInventory(val1);
				});
				
				$('#get_inventory').html("<table border='1'>" + html_string + "</table>");
			},
			complete: function(XMLHttpRequest) {
			}, 
			dataType: "json" 
		};
		
	return $.ajax(ajaxObj);
}

function templateGetInventory(param) {
	return '<tr>' +
				'<td class="rTitle">' + param.title + '</td>' +				
				'<td style="display:none;" class="id">' + param.id + '</td>' +
				'<td class="rBody_style">' + param.body_style + '</td>' +
				'<td class="rMiles">' + param.miles + '</td>' +
				'<td class="rDoors">' + param.doors + '</td>' +
				'<td class="rDoors">' + param.passengers + '</td>' +
				'<td class="rDoors">' + param.stock + '</td>' +
				'<td class="rDoors">' + param.vin + '</td>' +
				'<td class="rDoors">' + param.fuel_mileage + '</td>' +
				'<td class="rDoors">' + param.fuel_type + '</td>' +
				'<td class="DELETE_CAR"> <button class="DELETE_BTN" value=" ' + param.id + ' " type="button">Delete</button> </td>' +
			'</tr>';
}