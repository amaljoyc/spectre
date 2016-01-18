function show(json) {
	var content = '<table class="table table-hover"><thead><tr><th>#</th><th>IBAN</th><th>BIC</th></tr></thead><tbody>';
	var counter;
	for (counter = 0; counter < json.length; counter++) {
		account_pk = json[counter]['id'];
		content += '<tr id =' + account_pk + ' class="edit_tr">';
		content += '<th scope="row">' + (counter + 1) + '</th>';

		content += '<td class = "edit_td">';
		content += '<span id = "iban_text_' + account_pk + '" class="text">'
				+ json[counter]['iban'] + '</span>';
		content += '<span><input type="text" value="' + json[counter]['iban']
				+ '" class="editbox"  id = "iban_' + account_pk + '" /></span>'
		content += '</td>';

		content += '<td class = "edit_td">';
		content += '<span id = "bic_text_' + account_pk + '" class="text">'
				+ json[counter]['bic'] + '</span>';
		content += '<span><input type="text" value="' + json[counter]['bic']
				+ '" class="editbox"  id = "bic_' + account_pk + '" /></span>'
		content += '</td>';

		content += '<td><button id="'
				+ account_pk
				+ '" type="button" class="btn btn-default delete_btn">Delete</button></td>';
		content += '</tr>'
	}
	content += '</tbody></table>';
	$('.banklist-grid').html(content);
}

function list() {
	$.ajax({
		url : "/spectre/list"
	}).then(function(data) {
		show(data);
	});
}

function edit(attr_id, value) {
	var type = attr_id.split("_")[0];
	var id = attr_id.split("_")[1];
	$.post("/spectre/edit", {
		"id" : id,
		"type" : type,
		"value" : value
	});
}

$(document).on(
		"click",
		".edit_td",
		function() {
			$(".text").show().next("span").hide();
			$(this).find(".text").hide().next("span").show().find("input:text")
					.focus();
		});

$(document).on("click", function(event) {
	var $target = $(event.target);
	if ($target.closest("table").length == 0) {
		var $input = $("input.editbox:text:visible");
		var value = $input.val();
		$input.closest("td").find(".text").text(value).show();
		$input.parent().hide();
	}
});

$(document).on("keyup", "input.editbox:text", function(e) {
	if (e.which === 13) {
		var value = $(this).val();
		var attr_id = $(this).attr('id');
		edit(attr_id, value);
		$(this).closest("td").find(".text").html(value).show();
		$(this).parent().hide();
		return false;
	}
});

$(document).on("click", ".delete_btn", function() {
	$.ajax({
		type : "POST",
		url : "/spectre/delete",
		data : {
			"id" : this.id
		},
		async : false
	});
	list();
});

$('#add_form').submit(function(event) {
	$.ajax({
		type : "POST",
		url : "/spectre/add",
		data : $(this).serialize(),
		async : false
	});
	this.reset();
	list();
	event.preventDefault();
});

$(document).ready(function() {
	list();
});