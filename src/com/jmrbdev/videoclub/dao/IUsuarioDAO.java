package com.jmrbdev.videoclub.dao;

import java.util.HashMap;

public interface IUsuarioDAO {
	public String login(String username, String contrasena);

	public String register(String username, String contrasena, String nombre, String apellidos, String email,
			Double saldo, Boolean premium);

	public String pedir(String username);

	public String modificar(String username, String nuevaContrasena, String nuevoNombre, String nuevosApellidos,
			String nuevoEmail, Double nuevoSaldo, Boolean nuevoPremium);

	public String eliminar(String username);

	public String verCopias(String username);

	public String devolverPeliculas(String username, HashMap<Integer, Integer> peliculas);

	public String restarDinero(String username, Double nuevoSaldo);

	public String listarPeliculas(String username);

}
