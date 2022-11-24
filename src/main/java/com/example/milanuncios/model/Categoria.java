package com.example.milanuncios.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="CATEGORIAS")
public class Categoria {
	@Id
	private int id_categoria;
	private String descripcion;
	
	public Categoria() {
		super();
	}
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_categoria",referencedColumnName ="id_categoria" )
	private List<Anuncio> anuncios;
	
	
	public Categoria(int id_categoria, String descripcion) {
		super();
		this.id_categoria = id_categoria;
		this.descripcion = descripcion;
	}


	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	@Override
	public String toString() {
		return "Categoria [id_categoria=" + id_categoria + ", descripcion=" + descripcion + "]";
	}
	
	
	
}
