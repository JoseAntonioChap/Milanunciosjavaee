package com.example.milanuncios.util;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class Usuario_v {

	private String user;
	private String password;
	private String email;
	private String role;
	
	
	public Usuario_v() {
		super();
	}


	public Usuario_v(String user, String password, String email) {
		super();
		this.user = user;
		this.password = password;
		this.email = email;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRol() {
		return role;
	}


	public void setRol(String role) {
		this.role = role;
	}

	
	public boolean validate(Errors errors) {
		if (StringUtils.hasText(user)) {

		} else {
			errors.rejectValue("user", "badFormat", "rellene el user");
		}
		if (StringUtils.hasText(password)) {

		} else {
			errors.rejectValue("password", "badFormat", "rellene el password");
		}
		if (StringUtils.hasText(email)) {

		} else {
			errors.rejectValue("email", "badFormat", "rellene el email");
		}
		
		if (!role.equals("admin") && !role.equals("normal")) {
			errors.rejectValue("role", "badFormat", "El role debe ser admin o normal");
		}

		
		return errors.hasErrors();
	}
	
	
}
