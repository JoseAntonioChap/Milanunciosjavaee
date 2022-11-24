package com.example.milanuncios.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	private String user;
	private String email;
	private String password;
	
	
	
	
	@ManyToMany()
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "user"), inverseJoinColumns = @JoinColumn(name = "role"))
	private Set<Role> roles;

	public Usuario() {
		super();
	}

	
}
