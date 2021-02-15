$(document).ready(() => {
	$("#form-login").on("submit", (event) => {
		event.preventDefault();
		iniciarSesion();
	});

	$("#form-register").on("submit", (event) => {
		event.preventDefault();
		registrarUsuario();
	});
});

const iniciarSesion = () => {
	let username = $("#input-username").val();
	let contrasena = $("#input-contrasena").val();

	$.ajax({
		type: "GET",
		dataType: "html",
		url: "./ServletUsuarioLogin",
		data: $.param({
			username: username,
			contrasena: contrasena,
		}),
		success: (result) => {
			let parsedResult = JSON.parse(result);

			if (parsedResult === false) {
				$("#login-error").removeClass("d-none");
			} else {
				$("#login-error").addClass("d-none");
				document.location.href = `home.html?username=${parsedResult['username']}`;
			}
		},
		error: (result) => {
			console.log(result);
		},
	});
}

const registrarUsuario = () => {
	let username = $("#input-username").val();
	let contrasena = $("#input-contrasena").val();
	let contrasenaConfirmacion = $("#input-contrasena-repeat").val();
	let nombre = $("#input-nombre").val();
	let apellidos = $("#input-apellidos").val();
	let email = $("#input-email").val();
	let saldo = $("#input-saldo").val();
	let premium = $("#input-premium").prop('checked');

	if (contrasena == contrasenaConfirmacion) {
		$.ajax({
			type: "GET",
			dataType: "html",
			url: "./ServletUsuarioRegister",
			data: $.param({
				username: username,
				contrasena: contrasena,
				nombre: nombre,
				apellidos: apellidos,
				email: email,
				saldo: saldo,
				premium: premium,
			}),
			success: (result) => {
				let parsedResult = JSON.parse(result);

				if (parsedResult === false) {
					$("#register-error").removeClass("d-none");
				} else {
					$("#register-error").addClass("d-none");
					document.location.href = `home.html?username=${parsedResult['username']}`;
				}
			},
			error: (result) => {
				let parsedResult = JSON.parse(result);

				console.log("Error en la creación de la cuenta con resultado: " + parsedResult);
			},
		});
	} else {
		$("#register-error").removeClass("d-none");
	}
}