package com.jmrbdev.videoclub.dao;

public interface IUsuarioDAO {
	public String login(String username, String contrasena);

	public String register(String username, String contrasena, String nombre, String apellidos, String email,
			Double saldo, Boolean premium);

	public String pedir(String username);

	String modificar(String username, String nuevaContrasena, String nuevoNombre, String nuevosApellidos,
			String nuevoEmail, Double nuevoSaldo, Boolean nuevoPremium);

	String restarDinero(String username, Double nuevoSaldo);

	String listarPeliculas(String username);

	String eliminar(String username);
}
