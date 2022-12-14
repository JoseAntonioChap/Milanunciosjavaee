package com.example.milanuncios.dto;

import java.sql.Date;

public class AnuncioDTO {

	private int id_anuncio;
	private int id_categoria;
	private Date fecha;
	private String titulo;
	private String descripcion;
	private double precio;
	private String user;
	

	
	
	public AnuncioDTO() {
		super();
	}
	
	public AnuncioDTO(int id_anuncio, int id_categoria, Date fecha, String titulo, String descripcion, double precio,
			String user) {
		super();
		this.id_anuncio = id_anuncio;
		this.id_categoria = id_categoria;
		this.fecha = fecha;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.user = user;
		
	}

	public int getId_anuncio() {
		return id_anuncio;
	}
	public void setId_anuncio(int id_anuncio) {
		this.id_anuncio = id_anuncio;
	}
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	
	
	
}
