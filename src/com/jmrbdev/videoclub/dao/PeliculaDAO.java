package com.jmrbdev.videoclub.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.jmrbdev.videoclub.connection.DBConnection;
import com.jmrbdev.videoclub.vo.Pelicula;

public class PeliculaDAO implements IPeliculaDAO {

	@Override
	public String listar(Boolean ordenar, String orden) {
		Gson gson = new Gson();

		// SELECT * FROM peliculas;
		// SELECT * FROM peliculas SORT BY genero DESC;

		DBConnection con = new DBConnection();
		String sql = "SELECT * FROM peliculas" + (ordenar == true ? " ORDER BY genero " + orden + ";" : ";");

		ArrayList<String> peliculas = new ArrayList<String>();

		try {
			Statement st = con.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String genero = rs.getString("genero");
				String director = rs.getString("director");
				String actor_principal = rs.getString("actor_principal");
				int copias = rs.getInt("copias");
				Boolean estreno = rs.getBoolean("estreno");

				Pelicula pelicula = new Pelicula(id, titulo, genero, director, actor_principal, copias, estreno);

				peliculas.add(gson.toJson(pelicula));
			}
		} catch (Exception e) {
			return e.getMessage();
		}

		return gson.toJson(peliculas);
	}

	public String alquilar(int id, String username) {
		// INSERT INTO alquilar VALUES ([id], [username]);

		Timestamp fecha = new java.sql.Timestamp(new Date().getTime());

		DBConnection con = new DBConnection();
		String sql = "INSERT INTO alquilar VALUES ('" + id + "', '" + username + "', '" + fecha + "');";

		try {
			Statement st = con.getConnection().createStatement();
			st.executeQuery(sql);

			String modificar = this.modificar(id);

			if (modificar == "true") {
				return "true";
			} else {
				return "false";
			}

		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String modificar(int id) {
		// UPDATE peliculas SET copias = (copias - 1) WHERE id = [id];

		DBConnection con = new DBConnection();
		String sql = "UPDATE peliculas SET copias = (copias - 1) WHERE id = " + id + ";";

		try {
			Statement st = con.getConnection().createStatement();
			st.executeQuery(sql);

			return "true";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
