package com.example.milanuncios.interfaces;

import java.util.List;

import com.example.milanuncios.model.Usuario;



public interface IUsuarioService {
	public List<Usuario> usuarios_by_role(String role);
	public void delete_user(String usuario);
	public Usuario find_by_user(String usuario);
	public void save(Usuario usuario);
}
