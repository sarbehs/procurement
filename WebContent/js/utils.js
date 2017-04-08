var baseUrl = "http://59.110.11.74:8080/procurement/v1/";

function errorMsg() {
	alert("Server error!");
}

function filter(input, listId) {
	var rows = $("#" + listId).find("li");
	var key = $(input).val();
	if(key == "") {
		$.each(rows, function(i, li) {
			li.hidden = false;
		});
	} else {
		$.each(rows, function(i, li) {
			if(li.innerText.toUpperCase().indexOf(key.toUpperCase()) < 0)
				li.hidden = true;
			else
				li.hidden = false;
		});
	}
}

//$.ajaxSetup({
//	beforeSend: function(xhr) {
//		xhr.setRequestHeader("Access-Control-Allow-Origin", "*");
//	}
//});