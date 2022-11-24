package com.example.milanuncios.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "roles")
public class Role {

	@Id
	@Column(name = "role")
	private String role;
	private String funciones;
	
	@ManyToMany()
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "role"), inverseJoinColumns = @JoinColumn(name = "user"))
	private Set<Usuario> usuarios;

	public Role() {
		super();
	}

	public Role(String role, String funciones, Set<com.example.milanuncios.model.Usuario> usuarios) {
		super();
		this.role = role;
		this.funciones = funciones;
		this.usuarios = usuarios;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFunciones() {
		return funciones;
	}

	public void setFunciones(String funciones) {
		this.funciones = funciones;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
