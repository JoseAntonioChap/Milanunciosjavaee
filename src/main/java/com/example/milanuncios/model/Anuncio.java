package com.example.milanuncios.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="ANUNCIOS")
public class Anuncio {
	@Id
	private int id_anuncio;
	private int id_categoria;
	private Date fecha;
	private String titulo;
	private String descripcion;
	private double precio;
	private String user;
	
	@ManyToOne
	@JoinColumn(name="id_categoria",referencedColumnName ="id_categoria" , insertable=false,updatable=false)
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="user",referencedColumnName ="user" , insertable=false,updatable=false)
	private Usuario usuario;
	
	
	
	public Anuncio() {
		super();
	}



	public Anuncio(int id_anuncio, int id_categoria, Date fecha, String titulo, String descripcion, double precio,
			String user) {
		super();
		this.id_anuncio = id_anuncio;
		this.id_categoria = id_categoria;
		this.fecha = fecha;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.user = user;
		this.categoria = categoria;
		this.usuario = usuario;
	}



	public Anuncio(int id_categoria, Date fecha, String titulo, String descripcion, double precio, String user) {
		super();
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



	public Categoria getCategoria() {
		return categoria;
	}



	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	@Override
	public String toString() {
		return "Anuncio [id_anuncio=" + id_anuncio + ", id_categoria=" + id_categoria + ", fecha=" + fecha + ", titulo="
				+ titulo + ", descripcion=" + descripcion + ", precio=" + precio + ", user=" + user + ", categoria="
				+ categoria + ", usuario=" + usuario + "]";
	}








}
