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
	private String password;
	private String email;
	
	
	
	@ManyToMany()
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "user"), inverseJoinColumns = @JoinColumn(name = "role"))
	private Set<Role> roles;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="user",referencedColumnName ="user" )
	private List<Anuncio> anuncios;
	
	
	public Usuario() {
		super();
	}


	


	public Usuario(String user, String password, String email) {
		super();
		this.user = user;
		this.password = password;
		this.email = email;
	}





	public Usuario(String user, String password, String email, Set<Role> roles, List<Anuncio> anuncios) {
		super();
		this.user = user;
		this.password = password;
		this.email = email;
		this.roles = roles;
		this.anuncios = anuncios;
	}





	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public List<Anuncio> getAnuncios() {
		return anuncios;
	}


	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}


	@Override
	public String toString() {
		return "Usuario [user=" + user + ", email=" + email + ", password=" + password + ", roles=" + roles
				+ ", anuncios=" + anuncios + "]";
	}

	
	
	
}
