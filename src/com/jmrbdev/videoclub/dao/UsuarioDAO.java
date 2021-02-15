package com.jmrbdev.videoclub.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.jmrbdev.videoclub.connection.DBConnection;
import com.jmrbdev.videoclub.vo.Pelicula;
import com.jmrbdev.videoclub.vo.Usuario;

public class UsuarioDAO implements IUsuarioDAO {

	@Override
	public String login(String username, String contrasena) {
		Gson gson = new Gson();

		// SELECT * FROM usuarios WHERE username = [username] AND contrasena =
		// [contrasena];

		DBConnection con = new DBConnection();
		String sql = "SELECT * FROM usuarios WHERE username = '" + username + "' AND contrasena = '" + contrasena
				+ "';";

		if ((username != null || username != "") && (contrasena != null && contrasena != "")) {
			try {
				Statement st = con.getConnection().createStatement();
				ResultSet rs = st.executeQuery(sql);

				while (rs.next()) {
					String nombre = rs.getString("nombre");
					String apellidos = rs.getString("apellidos");
					String email = rs.getString("email");
					Double saldo = rs.getDouble("saldo");
					Boolean premium = rs.getBoolean("premium");

					Usuario usuario = new Usuario(username, contrasena, nombre, apellidos, email, saldo, premium);

					return gson.toJson(usuario);
				}
			} catch (Exception e) {
				return e.getMessage();
			}
		}

		return "false";
	}

	@Override
	public String register(String username, String contrasena, String nombre, String apellidos, String email,
			Double saldo, Boolean premium) {
		Gson gson = new Gson();

		// INSERT INTO usuarios VALUES (username, contrasena, nombre, apellidos, email,
		// saldo, premium);

		DBConnection con = new DBConnection();
		String sql = "INSERT INTO usuarios VALUES ('" + username + "', '" + contrasena + "', '" + nombre + "', '"
				+ apellidos + "', '" + email + "', " + saldo + ", " + premium + ");";

		if ((username != null && username != "") && (contrasena != null && contrasena != "")
				&& (nombre != null && nombre != "") && (apellidos != null && apellidos != "")
				&& (email != null && email != "")) {
			try {
				Statement st = con.getConnection().createStatement();
				st.executeQuery(sql);

				Usuario usuario = new Usuario(username, contrasena, nombre, apellidos, email, saldo, premium);

				st.close();

				return gson.toJson(usuario);
			} catch (Exception e) {
				return "false";
			}
		}

		return "false";
	}

	@Override
	public String pedir(String username) {
		Gson gson = new Gson();

		// SELECT * FROM usuarios WHERE username = [username];

		DBConnection con = new DBConnection();
		String sql = "SELECT * FROM usuarios WHERE username = '" + username + "';";

		if (username != null && username != "") {
			try {
				Statement st = con.getConnection().createStatement();
				ResultSet rs = st.executeQuery(sql);

				while (rs.next()) {
					String contrasena = rs.getString("contrasena");
					String nombre = rs.getString("nombre");
					String apellidos = rs.getString("apellidos");
					String email = rs.getString("email");
					Double saldo = rs.getDouble("saldo");
					Boolean premium = rs.getBoolean("premium");

					Usuario usuario = new Usuario(username, contrasena, nombre, apellidos, email, saldo, premium);
					return gson.toJson(usuario);
				}
			} catch (Exception e) {
				return "false";
			}
		}

		return "false";
	}

	@Override
	public String modificar(String username, String nuevaContrasena, String nuevoNombre, String nuevosApellidos,
			String nuevoEmail, Double nuevoSaldo, Boolean nuevoPremium) {

		// UPDATE usuarios SET contrasena = [nuevaContrasena], nombre = [nuevoNombre],
		// apellidos = [nuevosApellidos], email = [nuevoEmail], saldo = [nuevoSaldo],
		// premium = [nuevoPremium] WHERE username = [username];

		DBConnection con = new DBConnection();
		String sql = "UPDATE usuarios SET contrasena = '" + nuevaContrasena + "', nombre = '" + nuevoNombre
				+ "', apellidos = '" + nuevosApellidos + "', email = '" + nuevoEmail + "', saldo = '" + nuevoSaldo
				+ "', premium = '" + (nuevoPremium == true ? 1 : 0) + "' WHERE username = '" + username + "';";

		if ((username != null && username != "") && (nuevaContrasena != null && nuevaContrasena != "")
				&& (nuevoNombre != null && nuevoNombre != "") && (nuevosApellidos != null && nuevosApellidos != "")
				&& (nuevoEmail != null && nuevoEmail != "")) {
			try {
				Statement st = con.getConnection().createStatement();
				st.executeQuery(sql);

				return "true";
			} catch (Exception e) {
				return "false";
			}
		}

		return "false";
	}

	@Override
	public String eliminar(String username) {

		// DELETE FROM usuarios WHERE username = [username];

		DBConnection con = new DBConnection();
		String sql = "DELETE FROM usuarios WHERE username = '" + username + "';";

		if (username != null && username != "") {
			try {
				Statement st = con.getConnection().createStatement();
				st.executeQuery(sql);

				return "true";
			} catch (Exception e) {
				return e.getMessage();
			}
		}
		return "false";
	}

	@Override
	public String restarDinero(String username, Double nuevoSaldo) {

		// UPDATE usuarios SET saldo = [nuevoSaldo] WHERE username = [username];

		DBConnection con = new DBConnection();
		String sql = "UPDATE usuarios SET saldo = " + nuevoSaldo + " WHERE username = '" + username + "';";

		if (username != null && username != "") {
			try {
				Statement st = con.getConnection().createStatement();
				st.executeQuery(sql);

				return "true";
			} catch (Exception e) {
				return e.getMessage();
			}
		}

		return "false";
	}

	@Override
	public String listarPeliculas(String username) {
		Gson gson = new Gson();

		// SELECT titulo, genero FROM peliculas INNER JOIN alquilar ON peliculas.id =
		// alquilar.id INNER JOIN usuarios ON alquilar.username = usuarios.username
		// WHERE alquilar.username = [username];

		DBConnection con = new DBConnection();
		String sql = "SELECT peliculas.id, titulo, genero, estreno, alquilar.fecha FROM peliculas INNER JOIN alquilar ON peliculas.id = alquilar.id INNER JOIN usuarios ON alquilar.username = usuarios.username WHERE alquilar.username = '"
				+ username + "';";

		ArrayList<String> peliculas = new ArrayList<String>();

		try {
			Statement st = con.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String genero = rs.getString("genero");
				Boolean estreno = rs.getBoolean("estreno");
				Date fechaAlquiler = rs.getDate(5);

				Pelicula pelicula = new Pelicula(id, titulo, genero, estreno, fechaAlquiler);

				peliculas.add(gson.toJson(pelicula));
			}
		} catch (Exception e) {
			return e.getMessage();
		}

		return gson.toJson(peliculas);
	}
}