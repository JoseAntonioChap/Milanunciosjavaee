package com.example.milanuncios.util;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class Anuncio_v {
	
	private String id_categoria;
	private String titulo;
	private String descripcion;
	private String precio;
	private String user;
	
	public Anuncio_v() {
		super();
	}

	public Anuncio_v(String id_categoria, String fecha, String titulo, String descripcion,
			String precio, String user) {
		super();
		this.id_categoria = id_categoria;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.user = user;
	}

	public String getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(String id_categoria) {
		this.id_categoria = id_categoria;
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

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public boolean validate(Errors errors) {
		if (StringUtils.hasText(titulo)) {

		} else {
			errors.rejectValue("titulo", "badFormat", "rellene el titulo");
		}
		if (StringUtils.hasText(descripcion)) {

		} else {
			errors.rejectValue("descripcion", "badFormat", "rellene la descripcion");
		}
		
		try {
			Double.parseDouble(precio);
			if (Double.parseDouble(precio) < 1) {
				errors.rejectValue("precio", "badFormat", "el precio debe ser mayor a 0");
			}

		} catch (Exception e) {
			errors.rejectValue("precio", "badFormat", "rellene el precio correctamente");
		}

		
		return errors.hasErrors();
	}
	

}
