$('document').ready(function() {
	/* validation */
	$("#login-form").validate({
		rules : {
			password : {
				required : false,
			},
			user : {
				required : false,
			},
		},
		submitHandler : submitForm
	});
	/* validation */
	/* login submit */
	function submitForm() {
		var data = $("#login-form").serialize();

		$.ajax({
			type : 'POST',
			url : 'login_process',
			data : data,
			beforeSend : function() {
				$("#error").fadeOut();
				$("#btn-login").html('<span class="glyphicon glyphicon-transfer"></span> &nbsp; enviando ...');
			},
			success : function(response) {
				if (response == "ok") {
					$("#btn-login").html('<img class="glyphicon" src="images/btn-ajax-loader2.gif" /> &nbsp; Autenticando...');
					setTimeout('window.location.href = "my_home_page"; ',300);
				} else {
					$("#error").fadeIn(300,loginError(response));
				}
			}
		});
		return false;
	}
	/* login submit */
	function loginError(response) {
		$("#error")
			.html(
					'<div class="alert alert-danger"> <span class="glyphicon glyphicon-info-sign"></span> &nbsp; '
					+ response
					+ ' !</div>'
			);
		$("#btn-login")
			.html('<span class="glyphicon glyphicon-log-in"></span> &nbsp; Ingresar');
	}
});