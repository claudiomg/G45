$('document').ready(function() {
	/* validation */
	$("#login-form").validate({
		rules : {
			password : {
				required : true,
			},
			user : {
				required : true,
			},
		},
		messages : {
			password : {
				required : "Ingrese la contraseña"
			},
			user : {
				required : "Ingrese el usuario"
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
					$("#btn-login").html('<img src="btn-ajax-loader.gif" /> &nbsp; Signing In ...');
					setTimeout('window.location.href = "my_home_page"; ',1000);
				} else {
					$("#error").fadeIn(1000,loginError(response));
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