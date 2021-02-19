package com.jmrbdev.videoclub.vo;

import java.sql.Date;

public class Pelicula {
	private int id;
	private String titulo;
	private String genero;
	private String director;
	private String actor_principal;
	private int copias;
	private Boolean estreno;
	private Date fechaAlquiler;

	public Date getFechaAlquiler() {
		return fechaAlquiler;
	}

	public void setFechaAlquiler(Date fechaAlquiler) {
		this.fechaAlquiler = fechaAlquiler;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor_principal() {
		return actor_principal;
	}

	public void setActor_principal(String actor_principal) {
		this.actor_principal = actor_principal;
	}

	public int getCopias() {
		return copias;
	}

	public void setCopias(int copias) {
		this.copias = copias;
	}

	public Boolean getEstreno() {
		return estreno;
	}

	public void setEstreno(Boolean estreno) {
		this.estreno = estreno;
	}

	public Pelicula(int id, String titulo, String genero, String director, String actor_principal, int copias,
			Boolean estreno) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.genero = genero;
		this.director = director;
		this.actor_principal = actor_principal;
		this.copias = copias;
		this.estreno = estreno;
	}

	public Pelicula(String titulo, String genero, String director, String actor_principal, int copias,
			Boolean estreno) {
		super();
		this.titulo = titulo;
		this.genero = genero;
		this.director = director;
		this.actor_principal = actor_principal;
		this.copias = copias;
		this.estreno = estreno;
	}

	public Pelicula(int id, String titulo, String genero, Boolean estreno, Date fechaAlquiler) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.genero = genero;
		this.estreno = estreno;
		this.fechaAlquiler = fechaAlquiler;
	}
	
	public Pelicula(int id, int copias) {
		this.id = id;
		this.copias = copias;
	}

	public Pelicula() {
		super();
	}
}
