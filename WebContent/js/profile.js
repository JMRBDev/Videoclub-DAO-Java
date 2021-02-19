$(document).ready(() => {
	getUsuario(true).then(() => {
		$("#user-saldo").html(`${user.saldo.toFixed(2)}€`);
		getAlquiladas(user.username);
		$("#navbar-brand").attr("href", `home.html?username=${user.username}`);
	});

	$("#form-modificar").on("submit", (event) => {
		event.preventDefault();
		modificarUsuario();
	});

	$("#aceptar-eliminar-cuenta-btn").click(() => {
		eliminarCuenta().then(() => {
			location.href = 'index.html';
		});
	});
});

const modificarUsuario = () => {
	let username = $("#input-username").val();
	let contrasena = $("#input-contrasena").val();
	let nombre = $("#input-nombre").val();
	let apellidos = $("#input-apellidos").val();
	let email = $("#input-email").val();
	let saldo = $("#input-saldo").val();
	let premium = $("#input-premium").prop('checked');

	$.ajax({
		type: "GET",
		dataType: "html",
		url: "./ServletUsuarioModificar",
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
			if (result == "false") {
				$("#modificar-error").removeClass("d-none");
				$("#modificar-exito").addClass("d-none");
			} else {
				$("#modificar-error").addClass("d-none");
				$("#modificar-exito").removeClass("d-none");
			}
		},
		error: (result) => {
			console.log("Error modificando los datos del usuario con resultado: " + result);
		},
	});
}

const eliminarCuenta = async () => {
	await $.ajax({
		type: "GET",
		dataType: "html",
		url: "./ServletUsuarioEliminar",
		data: $.param({
			username: username,
		}),
		success: (result) => {
			if (result == "false") {
				console.log("Error recuperando los datos de las películas");
			} else {
				console.log("Usuario eliminado");
			}
		},
		error: (result) => {
			console.log("Error modificando los datos del usuario con resultado: " + result);
		},
	});
}

const getAlquiladas = (username) => {
	$.ajax({
		type: "GET",
		dataType: "html",
		url: "./ServletUsuarioListarPeliculas",
		data: $.param({
			username: username,
		}),
		success: (result) => {
			let parsedResult = JSON.parse(result);

			if (parsedResult === false) {
				console.log("Error recuperando los datos de las películas")
			} else {
				mostrarHistorial(parsedResult);
			}
		},
		error: (result) => {
			let parsedResult = JSON.parse(result);

			console.log("Error recuperando los datos de las películas con resultado: " + parsedResult);
		},
	});
}

const mostrarHistorial = (peliculas) => {
	let contenido = "";
	if (peliculas.length >= 1) {
		$.each(peliculas, (index, pelicula) => {
			pelicula = JSON.parse(pelicula);
			contenido += `<tr>
        <th scope="row">${pelicula.id}</th>
        <td>${pelicula.titulo}</td>
        <td>${pelicula.genero}</td>
        <td><input type="checkbox" name="estreno" id="estreno${pelicula.id}" disabled ${pelicula.estreno ? "checked" : ""}></td>
        <td>${pelicula.fechaAlquiler}</td>
		<td><button class="btn btn-danger" id="devolver-btn"
		onclick="devolverPelicula(${pelicula.id})">Devolver película</button></td>
        </tr>
        `;
		});
		$("#historial-tbody").html(
			contenido
		);
		$("#historial-table").removeClass("d-none");
		$("#historial-vacio").addClass("d-none");
	} else {
		$("#historial-vacio").removeClass("d-none");
		$("#historial-table").addClass("d-none");
	}
}

const devolverPelicula = (id) => {
	$.ajax({
		type: "GET",
		dataType: "html",
		url: "./ServletPeliculaDevolver",
		data: $.param({
			username: username,
			id: id,
		}),
		success: (result) => {
			if (result === false) {
				console.log("Error devolviendo la película");
			} else {
				location.reload();
			}
		},
		error: (result) => {
			console.log("Error devolviendo la película con resultado: " + result);
		},
	});
}