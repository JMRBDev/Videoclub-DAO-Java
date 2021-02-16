const username = new URL(location.href).searchParams.get("username");
let user;

$(document).ready(() => {
	// Activar tooltips de Bootstrap
	$(() => {
		$('[data-toggle="tooltip"]').tooltip();
	});

	if (/\/profile.html/.test(location.href)) {
		getUsuario(true);
	} else {
		getUsuario(false).then(() => {
			$("#saludo").append(`${username}`);
			$("#user-saldo").html(`${user.saldo.toFixed(2)}€`);

			$("#mi-perfil-btn").attr("href", `profile.html?username=${username}`);

			getPeliculas(false, "ASC");
			
			
			$("#ordenar-genero").click(() => ordenarPeliculas());
		});
	}
});

const getUsuario = async (isProfile) => {
	await $.ajax({
		type: "GET",
		dataType: "html",
		url: "./ServletUsuarioPedir",
		data: $.param({
			username: username,
		}),
		success: (result) => {
			let parsedResult = JSON.parse(result);

			if (parsedResult === false) {
				console.log("Error recuperando los datos del usuario")
			} else {
				user = parsedResult;
				if (isProfile) {
					$("#input-username").val(parsedResult.username);
					$("#input-contrasena").val(parsedResult.contrasena);
					$("#input-nombre").val(parsedResult.nombre);
					$("#input-apellidos").val(parsedResult.apellidos);
					$("#input-email").val(parsedResult.email);
					$("#input-saldo").val(parsedResult.saldo.toFixed(2));
					$("#input-premium").prop('checked', parsedResult.premium);
				}
			}
		},
		error: (result) => {
			let parsedResult = JSON.parse(result);

			console.log("Error recuperando los datos del usuario con resultado: " + parsedResult);
		},
	});
}

const ordenarPeliculas = () => {
	if ($("#icono-ordenar").hasClass("fa-sort")) {
		getPeliculas(true, "ASC");
		$("#icono-ordenar").removeClass("fa-sort");
		$("#icono-ordenar").addClass("fa-sort-down");
	} else if ($("#icono-ordenar").hasClass("fa-sort-down")) {
		getPeliculas(true, "DESC");
		$("#icono-ordenar").removeClass("fa-sort-down");
		$("#icono-ordenar").addClass("fa-sort-up");
	} else if ($("#icono-ordenar").hasClass("fa-sort-up")) {
		getPeliculas(false, "ASC");
		$("#icono-ordenar").removeClass("fa-sort-up");
		$("#icono-ordenar").addClass("fa-sort");
	}

}

const getPeliculas = (ordenar, orden) => {
	$.ajax({
		type: "GET",
		dataType: "html",
		url: "./ServletPeliculaListar",
		data: $.param({
			ordenar: ordenar,
			orden: orden,
		}),
		success: (result) => {
			let parsedResult = JSON.parse(result);

			if (parsedResult === false) {
				console.log("Error recuperando los datos de las películas")
			} else {
				mostrarPeliculas(parsedResult);
			}
		},
		error: (result) => {
			let parsedResult = JSON.parse(result);

			console.log("Error recuperando los datos de las películas con resultado: " + parsedResult);
		},
	});
}

const mostrarPeliculas = (peliculas) => {
	let contenido = "";
	$.each(peliculas, (index, pelicula) => {
		pelicula = JSON.parse(pelicula);
		let precio;
		if (pelicula.copias > 0) {
			if (user.premium) {
				if (pelicula.estreno) {
					precio = (2 - (2 * 0.1));
				} else {
					precio = (1 - (1 * 0.1));
				}
			} else {
				if (pelicula.estreno) {
					precio = 2;
				} else {
					precio = 1;
				}
			}
			contenido += `<tr>
                <th scope="row">${pelicula.id}</th>
                <td>${pelicula.titulo}</td>
                <td>${pelicula.genero}</td>
                <td>${pelicula.director}</td>
                <td>${pelicula.actor_principal}</td>
                <td>${pelicula.copias}</td>
                <td><input type="checkbox" name="estreno" id="estreno${pelicula.id}" disabled ${pelicula.estreno ? "checked" : ""}></td>
                <td>${precio}€</td>
                <td><button onclick="alquilarPelicula(${pelicula.id}, ${precio});"
                        class="btn btn-success ${user.saldo < precio ? "disabled" : null}"
						${user.saldo < precio ? "disabled" : null}
                        ${user.saldo < precio ? 'data-toggle="tooltip" data-placement="top" title="No dispone de créditos suficientes"' : null}
                        >Alquilar</button></td>
                </tr>
            `;
		}
	});
	$("#peliculas-tbody").html(
		contenido
	);
	$(() => {
		$('[data-toggle="tooltip"]').tooltip();
	});
}

const alquilarPelicula = (id, precio) => {
	$.ajax({
		type: "GET",
		dataType: "html",
		url: "./ServletPeliculaAlquilar",
		data: $.param({
			id: id,
			username: username,
		}),
		success: (result) => {
			if (result === false) {
				console.log("Error recuperando los datos de las películas");
			} else {
				restarDinero(precio).then(() => {
					location.reload();
				});
			}
		},
		error: (result) => {
			console.log("Error recuperando los datos de las películas con resultado: " + result);
		},
	});
}

const restarDinero = async (precio) => {
	await $.ajax({
		type: "GET",
		dataType: "html",
		url: "./ServletUsuarioRestarDinero",
		data: $.param({
			username: username,
			saldo: parseFloat(user.saldo - precio),
		}),
		success: (result) => {
			if (result == "false") {
				console.log("Error en el proceso de pago");
			}
		},
		error: (result) => {
			console.log("Error en el proceso de pago con resultado: " + result);
		},
	});
}