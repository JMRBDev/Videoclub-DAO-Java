package com.jmrbdev.videoclub.dao;

public interface IPeliculaDAO {
	public String listar(Boolean ordenar, String orden);

	public String alquilar(int id, String username);

	public String modificar(int id);

	String devolver(int id, String username);

	String sumarCantidad(int id);
}
