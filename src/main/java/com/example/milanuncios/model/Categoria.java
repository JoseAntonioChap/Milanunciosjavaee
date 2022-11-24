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

	public Categoria(int id_categoria, String descripcion, List<Anuncio> anuncios) {
		super();
		this.id_categoria = id_categoria;
		this.descripcion = descripcion;
		this.anuncios = anuncios;
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

	public List<Anuncio> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}
	
	
	
	
	
}
